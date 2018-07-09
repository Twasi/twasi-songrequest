package net.twasiplugin.songrequest.tsss.communication.client;

import net.twasiplugin.songrequest.tsss.communication.BasePacket;

public class SignIn extends BasePacket {
    private String jwt;

    public String getJwt() {
        return jwt;
    }
}
