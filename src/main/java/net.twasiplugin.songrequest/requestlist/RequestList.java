package net.twasiplugin.songrequest.requestlist;

import net.twasiplugin.songrequest.object.Song;
import net.twasiplugin.songrequest.tsss.api.PlayerStatus;
import org.bson.types.ObjectId;

import java.util.List;

public class RequestList {
    public List<Song> nextSongs;
    public List<Song> previousSongs;

    public Song currentSong = null;

    // State to current playback
    PlayerStatus status = PlayerStatus.WAITING;
    int seconds = 0;

    public void loadForUser(ObjectId userId) {

    }
}
