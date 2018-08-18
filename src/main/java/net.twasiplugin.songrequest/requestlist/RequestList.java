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

import java.util.List;

public class RequestList extends ApiDTO {
    private SongrequestData data;

    // State to current playback
    private PlayerStatus playbackStatus = PlayerStatus.WAITING;

    @JsonIgnore
    private ObjectId userId;

    public RequestList() {
        super(true);
    }

    public void loadForUser(ObjectId userId) {
        SongrequestRepo repo = ServiceRegistry.get(DataService.class).get(SongrequestRepo.class);
        data = repo.getByUser(userId);

        this.userId = userId;
    }

    public void request(Song song) {
        data.getNextSongs().add(song);

        save();
    }

    public void save() {
        ServiceRegistry.get(DataService.class).get(SongrequestRepo.class).commit(data);

        // Reload data
        loadForUser(userId);
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
}
