package net.twasiplugin.songrequest.tsss.communication.master;

import net.twasiplugin.songrequest.tsss.communication.BasePacket;

public class Seek extends BasePacket {
    private int seconds;

    public int getSeconds() {
        return seconds;
    }
}
