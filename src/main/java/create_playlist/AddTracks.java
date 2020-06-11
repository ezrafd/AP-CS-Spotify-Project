package create_playlist;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class AddTracks {
    private String playlistId;
    private String[] uris;

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(PlaylistMain.accessToken)
            .build();
    private AddItemsToPlaylistRequest addItemsToPlaylistRequest;

    protected AddTracks(String playlistId, String[] uris) {
        this.uris = uris;
        this.playlistId = playlistId;
        addItemsToPlaylistRequest = spotifyApi
                .addItemsToPlaylist(playlistId, uris)
//          .position(0)
                .build();
    }

    public void addItemsToPlaylist() {
        try {
            final SnapshotResult snapshotResult = addItemsToPlaylistRequest.execute();

            System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
