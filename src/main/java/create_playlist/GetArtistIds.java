package create_playlist;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class GetArtistIds {
    private String artist;

    private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(PlaylistMain.accessToken)
            .build();
    private final SearchArtistsRequest searchArtistsRequest;

    public GetArtistIds(String artist) {
        this.artist = artist;
        searchArtistsRequest = spotifyApi.searchArtists(artist)
                .limit(1)
                .build();
    }

    public String searchArtists() {
        try {
            final Paging<Artist> artistPaging = searchArtistsRequest.execute();
            Artist[] result = artistPaging.getItems();
            String id = result[0].getId();
            return id;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}

