package net.twasiplugin.songrequest.web.model;

import net.twasi.core.database.models.User;

import java.util.List;

public class SongrequestDTO {

    public SongrequestDTO(User user) {

    }

    public List<SongrequestRequest> getRequests() {
        return null;
    }

    public SongrequestStatus getCurrent() {
        return new SongrequestStatus();
    }

}
