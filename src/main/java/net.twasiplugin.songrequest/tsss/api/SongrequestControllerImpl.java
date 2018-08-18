package net.twasiplugin.songrequest.tsss.api;

import net.twasi.core.database.models.User;
import net.twasi.core.database.repositories.UserRepository;
import net.twasi.core.services.ServiceRegistry;
import net.twasi.core.services.providers.DataService;
import net.twasiplugin.songrequest.requestlist.RequestList;
import net.twasiplugin.songrequest.requestlist.RequestListService;

public class SongrequestControllerImpl implements SongrequestController {
    private String twitchId = null;
    private boolean isSignedIn = false;
    private RequestList list;

    public SongrequestControllerImpl(String twitchId) {
        this.twitchId = twitchId;

        User user = ServiceRegistry.get(DataService.class).get(UserRepository.class).getByTwitchId(twitchId);

        list = ServiceRegistry.get(RequestListService.class).getRequestListForUser(user.getId());
    }

    public RequestList getRequestList() {
        return list;
    }

    @Override
    public boolean signIn(String jwt) {
        return true;
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
        return null;
    }

    public boolean isSignedIn() {
        return isSignedIn;
    }
}
