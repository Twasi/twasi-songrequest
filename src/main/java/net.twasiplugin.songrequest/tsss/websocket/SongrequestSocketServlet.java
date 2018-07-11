package net.twasiplugin.songrequest.tsss.websocket;

import net.twasi.core.logger.TwasiLogger;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class SongrequestSocketServlet extends WebSocketServlet {
    @Override
    public void configure(WebSocketServletFactory factory) {
        // set a 10 second timeout
        factory.getPolicy().setIdleTimeout(10000);
        TwasiLogger.log.debug("Songrequest Websocket API configured.");
        factory.register(SongrequestSocket.class);
    }
}
