package net.twasiplugin.songrequest.tsss.api;

public interface PlaybackStatus {

    // Playback status
    PlayerStatus getStatus();
    int getTime();

    // Song information
    String getTitle();
    String getAuthor();
    int getLength();

    // Meta
    String getRequester();
    ProviderType getProvider();

}
