package create_playlist;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetArtistExample {
    private static final String accessToken = "BQA-WCuJ02nH0DY7zl1YUJn1onb4znBj33ERq1SbPS81cWNJziHTwyiMxHKHvidQXpDZiXnolJvMC_nOp0rzLbtNbcxNdv04gZLSOho8vyMMriHV5dREYeqNxBPKo3D8plV6-kpt7jCbtktESrjMc6R-vuPbwOP9Mm8Y99xUtN27xhvJ29VZx-92qBlFkpR-1fyn38Zer5PNv3kKMaayGeknKgwioZKjMHMfce2Dd200uzy-CrKZ0UhXXSdFfYlIrOLpzuwovdMdMmirH5kTvHZZwnYOfSoB3wuy";
    private static final String id = "0LcJLqbBmaGUft1e9Mm8HV";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetArtistRequest getArtistRequest = spotifyApi.getArtist(id)
            .build();

    public static void getArtist_Sync() {
        try {
            final Artist artist = getArtistRequest.execute();

            System.out.println("Name: " + artist.getName());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getArtist_Async() {
        try {
            final CompletableFuture<Artist> albumFuture = getArtistRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Artist artist = albumFuture.join();

            System.out.println("Name: " + artist.getName());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    public static void main(String[] args) {
        getArtist_Sync();
        getArtist_Async();
    }
}