package net.twasiplugin.songrequest.object;

import net.twasiplugin.songrequest.tsss.api.ProviderType;

public interface Song {
    String getTitle();
    String getAuthor();

    int getLength();

    String getRequester();
    ProviderType getProvider();
}
