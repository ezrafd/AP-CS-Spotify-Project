package create_playlist;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreatePlaylist {
    private final String name = String.format("New Music %s", LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yy")));


    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(PlaylistMain.accessToken)
            .build();
    private  final CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(PlaylistMain.userId, name)
//          .collaborative(false)
//          .public_(false)
//          .description("Amazing music.")
            .build();

    public String createPlaylist() {
        try {
            final Playlist playlist = createPlaylistRequest.execute();
            return playlist.getId();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
