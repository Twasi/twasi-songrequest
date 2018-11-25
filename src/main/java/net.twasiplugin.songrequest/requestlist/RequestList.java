package net.twasiplugin.songrequest.requestlist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.songrequest.database.SongrequestData;
import net.twasiplugin.songrequest.database.SongrequestRepo;
import net.twasiplugin.songrequest.object.Song;
import net.twasiplugin.songrequest.tsss.api.PlayerStatus;
import net.twasiplugin.songrequest.tsss.websocket.SongrequestSocket;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestList {
    private SongrequestData data;

    @JsonIgnore
    private List<SongrequestSocket> sockets = new ArrayList<>();

    // State to current playback
    private PlayerStatus playbackStatus = PlayerStatus.WAITING;

    @JsonIgnore
    private ObjectId userId;

    public RequestList() {
    }

    public void loadForUser(ObjectId userId) {
        SongrequestRepo repo = ServiceRegistry.get(DataService.class).get(SongrequestRepo.class);
        data = repo.getByUser(userId);

        this.userId = userId;
    }

    public void request(Song song) {
        data.getNextSongs().add(song);

        save();
        broadcastStatus();
    }

    public void save() {
        ServiceRegistry.get(DataService.class).get(SongrequestRepo.class).commit(data);

        // Reload data
        loadForUser(userId);
    }

    public void broadcastStatus() {
        sockets.forEach(socket -> {
            try {
                socket.sendObject("Status test: " + getPlaybackStatus().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public List<Song> getNextSongs() {
        return data.getNextSongs();
    }

    public List<Song> getPreviousSongs() {
        return data.getPreviousSongs();
    }

    public Song getCurrentSong() {
        return data.getCurrentSong();
    }

    public PlayerStatus getPlaybackStatus() {
        return playbackStatus;
    }

    public int getSeconds() {
        return data.getSeconds();
    }

    public ObjectId getUserId() {
        return userId;
    }

    public List<SongrequestSocket> getSockets() {
        return sockets;
    }
}
