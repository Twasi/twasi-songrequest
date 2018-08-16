package net.twasiplugin.songrequest.requestlist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasi.core.webinterface.dto.ApiDTO;
import net.twasiplugin.songrequest.database.SongrequestData;
import net.twasiplugin.songrequest.database.SongrequestRepo;
import net.twasiplugin.songrequest.object.Song;
import net.twasiplugin.songrequest.tsss.api.PlayerStatus;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class RequestList extends ApiDTO {
    private List<Song> nextSongs;
    private List<Song> previousSongs;

    private Song currentSong = null;

    // State to current playback
    private PlayerStatus playbackStatus = PlayerStatus.WAITING;
    private int seconds = 0;

    @JsonIgnore
    private ObjectId userId;

    public RequestList() {
        super(true);
    }

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
        if (nextSongs == null) {
            nextSongs = new ArrayList<>();
        }
        return nextSongs;
    }

    public List<Song> getPreviousSongs() {
        if (previousSongs == null) {
            previousSongs = new ArrayList<>();
        }
        return previousSongs;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public PlayerStatus getPlaybackStatus() {
        return playbackStatus;
    }

    public int getSeconds() {
        return seconds;
    }

    public ObjectId getUserId() {
        return userId;
    }
}
