package net.twasiplugin.songrequest.database;

import net.twasi.core.database.models.BaseEntity;
import net.twasiplugin.songrequest.object.Song;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity(noClassnameStored = true)
public class SongrequestData extends BaseEntity {
    public SongrequestData() {
    }

    public SongrequestData(ObjectId userId) {
        this.userId = userId;
        nextSongs = new ArrayList<>();
        previousSongs = new ArrayList<>();
        currentSong = null;
        seconds = 0;
    }

    /**
     * Reference to the user this data belongs to
     */
    private ObjectId userId;

    private List<Song> nextSongs;
    private List<Song> previousSongs;
    private Song currentSong;
    private int seconds;

    public ObjectId getUserId() {
        return userId;
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

    public int getSeconds() {
        return seconds;
    }
}
