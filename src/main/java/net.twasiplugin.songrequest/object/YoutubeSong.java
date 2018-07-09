package net.twasiplugin.songrequest.object;

import net.twasiplugin.songrequest.tsss.api.ProviderType;

public class YoutubeSong implements Song {
    private String title;
    private String author;
    private int length;
    private String requester;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public String getRequester() {
        return requester;
    }

    @Override
    public ProviderType getProvider() {
        return ProviderType.Youtube;
    }
}
