package net.twasiplugin.songrequest.tsss.api;

public class SongrequestControllerImpl implements SongrequestController {
    private String channel = null;
    private boolean isSignedIn = false;

    @Override
    public boolean join(String twitchname) {
        return false;
    }

    @Override
    public boolean signIn(String jwt) {
        if (!isReady()) {
            return false;
        }
        return false;
    }

    @Override
    public boolean startPlayback() {
        if (!isSignedIn()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean stopPlayback() {
        if (!isSignedIn()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean togglePlayback() {
        if (!isSignedIn()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean seek(int seconds) {
        if (!isSignedIn()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean skip() {
        if (!isSignedIn()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean revert() {
        if (!isSignedIn()) {
            return false;
        }
        return true;
    }

    @Override
    public PlaybackStatus requestStatus() {
        if (!isReady()) {
            return null;
        }
        return null;
    }

    public boolean isReady() {
        return channel != null;
    }

    public boolean isSignedIn() {
        if (!isReady()) {
            return false;
        }
        return isSignedIn;
    }
}
