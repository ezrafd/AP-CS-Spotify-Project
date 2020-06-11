package create_playlist;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import org.apache.hc.core5.http.ParseException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetArtistExample {
    private static final String accessToken = "BQBQFKVVRcd3f-D3CUNMSCmYIc_smwGJOIUTba1qUWUoHBR9xe6KY3DozzpfRwk6rAxIE6a55dvSpDfddgWsVyHGaz95K1QMHe1WkwxBGfot1v-hzq6D60Q3G-efCh1f0GAZfHSsPqWJmYghHJfxkv8_YRJ2XoX4tStSAc5xaN53rdtg8pjO5g4R1WvIS_Ve5BB2bzegbcpmlV29k1oJxnMrJB1CYXzBDvXXloqKGNZ0YtRN5ZCpqy99JmIdA4mbXaeH6bTCsOwz4CWti3dTEWNx1K8RPiVyCZv9";
    private static final String id = "0LcJLqbBmaGUft1e9Mm8HV";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetArtistRequest getArtistRequest = spotifyApi.getArtist(id)
            .build();

    public static void getArtist_Sync(ArrayList<String> artists) {
        ArrayList<String> artist_ids = new ArrayList<>();
        for (String name : artists) {
            try {
                final Artist artist = getArtistRequest.execute();

                artist_ids.add(artist.getId());
            } catch (IOException | SpotifyWebApiException | ParseException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println(artist_ids);
    }

    public static void main(String[] args) {
        ArrayList<String> artists = new ArrayList<>(Arrays.asList("Playboi Carti, Meek Mill"));
        getArtist_Sync(artists);
    }
}