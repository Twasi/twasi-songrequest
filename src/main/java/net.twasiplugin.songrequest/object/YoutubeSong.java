package net.twasiplugin.songrequest.object;

import net.twasiplugin.songrequest.tsss.api.ProviderType;

public class YoutubeSong implements Song {
    private String title;
    private String author;
    private int length;
    private String requester;

    private String videoId;

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
        return ProviderType.YouTube;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
