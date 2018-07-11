package net.twasiplugin.songrequest.tsss.websocket;

import com.google.gson.Gson;
import net.twasiplugin.songrequest.tsss.api.SongrequestController;
import net.twasiplugin.songrequest.tsss.communication.BasePacket;
import net.twasiplugin.songrequest.tsss.communication.PacketType;
import net.twasiplugin.songrequest.tsss.communication.client.SignIn;
import net.twasiplugin.songrequest.tsss.communication.master.Seek;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.IOException;

public class SongrequestSocket extends WebSocketAdapter {
    private SongrequestController controller;

    @Override
    public void onWebSocketText(String message) {
        // Parse json
        BasePacket packet = new Gson().fromJson(message, BasePacket.class);

        try {
            switch (packet.getType()) {
                case signIn:
                    SignIn signInPacket = new Gson().fromJson(message, SignIn.class);
                    sendObject(controller.signIn(signInPacket.getJwt()));
                    break;
                case startPlayback:
                    sendObject(controller.startPlayback());
                    break;
                case stopPlayback:
                    controller.stopPlayback();
                    break;
                case togglePlayback:
                    controller.togglePlayback();
                    break;
                case seek:
                    Seek seekPacket = new Gson().fromJson(message, Seek.class);
                    controller.seek(seekPacket.getSeconds());
                    break;
                case skip:
                    controller.skip();
                    break;
                case revert:
                    controller.revert();
                    break;
                case requestStatus:
                    controller.requestStatus();
                    break;
                case ping:
                    sendObject(new BasePacket(PacketType.pong));
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendObject(Object o) throws IOException {
        getRemote().sendString(new Gson().toJson(o) + "\n");
    }
}
