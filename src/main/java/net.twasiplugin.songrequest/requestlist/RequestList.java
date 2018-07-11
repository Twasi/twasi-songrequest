package net.twasiplugin.songrequest.requestlist;

import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.songrequest.database.SongrequestData;
import net.twasiplugin.songrequest.database.SongrequestRepo;
import net.twasiplugin.songrequest.object.Song;
import net.twasiplugin.songrequest.tsss.api.PlayerStatus;
import org.bson.types.ObjectId;

import java.util.List;

public class RequestList {
    private List<Song> nextSongs;
    private List<Song> previousSongs;

    private Song currentSong = null;

    // State to current playback
    private PlayerStatus status = PlayerStatus.WAITING;
    private int seconds = 0;

    private ObjectId userId;

    public void loadForUser(ObjectId userId) {
        SongrequestRepo repo = ServiceRegistry.get(DataService.class).get(SongrequestRepo.class);
        SongrequestData data = repo.getByUser(userId);

        this.userId = userId;
        nextSongs = data.getNextSongs();
        previousSongs = data.getPreviousSongs();
        currentSong = data.getCurrentSong();

        seconds = data.getSeconds();
    }

    public List<Song> getNextSongs() {
        return nextSongs;
    }

    public List<Song> getPreviousSongs() {
        return previousSongs;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public int getSeconds() {
        return seconds;
    }

    public ObjectId getUserId() {
        return userId;
    }
}
