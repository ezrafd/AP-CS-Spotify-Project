package create_playlist;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.albums.GetAlbumsTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class GetAlbumTracks {
    private String id;

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(PlaylistMain.accessToken)
            .build();
    private final GetAlbumsTracksRequest getAlbumsTracksRequest;

    public GetAlbumTracks(String id) {
        this.id = id;
        getAlbumsTracksRequest = spotifyApi.getAlbumsTracks(id)
                .build();
    }

    public TrackSimplified[] getAlbumsTracks() {
        try {
            final Paging<TrackSimplified> trackSimplifiedPaging = getAlbumsTracksRequest.execute();

            TrackSimplified[] tracks = trackSimplifiedPaging.getItems();
            return tracks;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
