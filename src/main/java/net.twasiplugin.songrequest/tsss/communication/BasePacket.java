package net.twasiplugin.songrequest.tsss.communication;

public class BasePacket {
    private PacketType type;

    public BasePacket() {

    }

    public BasePacket(PacketType type) {
        this.type = type;
    }

    public PacketType getType() {
        return type;
    }
}
