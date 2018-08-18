package net.twasiplugin.songrequest.tsss.api;

import net.twasiplugin.songrequest.requestlist.RequestList;

public interface SongrequestController {
    boolean signIn(String jwt);

    // Master options (need sign in)
    boolean startPlayback();
    boolean stopPlayback();
    boolean togglePlayback();
    boolean seek(int seconds);
    boolean skip();
    boolean revert();

    // Client options (no need to sign in)
    PlaybackStatus requestStatus();

    RequestList getRequestList();
}
