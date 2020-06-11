package create_playlist;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AddTracks {
    private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
    private static final String playlistId = "3AGOiaoRXMSjswCLtuNqv5";
    private static final String[] uris = new String[]{"spotify:track:01iyCAUm8EvOFqVWYJ3dVX", "spotify:episode:4GI3dxEafwap1sFiTGPKd1"};

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi
            .addItemsToPlaylist(playlistId, uris)
//          .position(0)
            .build();

    public static void addItemsToPlaylist_Sync() {
        try {
            final SnapshotResult snapshotResult = addItemsToPlaylistRequest.execute();

            System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
