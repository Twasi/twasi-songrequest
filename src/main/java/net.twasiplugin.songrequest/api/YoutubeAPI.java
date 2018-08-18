package net.twasiplugin.songrequest.api;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.VideoListResponse;
import net.twasiplugin.songrequest.Songrequest;
import net.twasiplugin.songrequest.object.YoutubeSong;

import java.io.IOException;
import java.time.Duration;

public class YoutubeAPI {
    private static YouTube youtube;
    private static String key;

    public static void setUp() {
        youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {
            }
        }).setApplicationName("twasi-sr-query").build();

        key = Songrequest.config.getProperty("provider.youtube.secret");
    }

    public static YoutubeSong getByReference(String reference, String requester) {
        return null;
    }

    public static YoutubeSong getByTerm(String term, String requester) {
        try {
            YouTube.Search.List search = youtube.search().list("id,snippet");

            search.setQ(term);
            search.setType("video");
            search.setKey(key);
            search.setVideoCategoryId("10");

            SearchListResponse searchResponse = search.execute();

            if (searchResponse.getItems().size() == 0) {
                return null;
            }

            SearchResult result = searchResponse.getItems().get(0);

            // We need to query the video directly to get contentDetails
            YouTube.Videos.List singleVideoRequest = youtube.videos().list("contentDetails");
            singleVideoRequest.setId(result.getId().getVideoId());
            singleVideoRequest.setKey(key);

            VideoListResponse response = singleVideoRequest.execute();
            System.out.println(response.getItems().get(0).getContentDetails().getDuration());
            Duration duration = Duration.parse(response.getItems().get(0).getContentDetails().getDuration());

            YoutubeSong song = new YoutubeSong();
            song.setTitle(result.getSnippet().getTitle());
            song.setAuthor(result.getSnippet().getChannelTitle());
            song.setLength(Integer.valueOf(String.valueOf(duration.getSeconds())));
            song.setRequester(requester);
            song.setVideoId(result.getId().getVideoId());
            return song;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
