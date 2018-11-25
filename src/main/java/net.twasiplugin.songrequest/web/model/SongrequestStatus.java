package net.twasiplugin.songrequest.web.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SongrequestStatus {

    public SongrequestStatusType getStatus() {
        return SongrequestStatusType.PLAYING;
    }

    public int getSecondsIn() {
        Calendar calendar = GregorianCalendar.getInstance();

        return calendar.get(Calendar.SECOND);
    }

    public SongrequestRequest getRequest() {
        return new SongrequestRequest();
    }

}
