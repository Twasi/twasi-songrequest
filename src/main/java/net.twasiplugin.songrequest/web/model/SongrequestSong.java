package net.twasiplugin.songrequest.web.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SongrequestSong {

    public String getName() {
        Calendar calendar = GregorianCalendar.getInstance();

        if (calendar.get(Calendar.MINUTE) % 2 == 0) {
            return "K/DA - POP/STARS (ft Madison Beer, (G)I-DLE, Jaira Burns) | Official Music Video - League of Legends";
        } else {
            return "Bee Gees - Stayin' Alive [Version 1] (Video)";
        }
    }

}
