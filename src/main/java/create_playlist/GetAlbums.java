package create_playlist;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GetAlbums {
    private String id;
    private Date cutoff;

    private SimpleDateFormat simp = new SimpleDateFormat("yyyy-mm-dd");

    private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(PlaylistMain.accessToken)
            .build();

    private final GetArtistsAlbumsRequest getArtistsAlbumsRequest;

    public GetAlbums(String id, String cutoff) throws java.text.ParseException {
        this.id = id;
        this.cutoff = simp.parse(cutoff);
        System.out.println(id);
        getArtistsAlbumsRequest = spotifyApi.getArtistsAlbums(id)
                .build();
    }

    public ArrayList<AlbumSimplified> getArtistsAlbums() {
        try {
            final Paging<AlbumSimplified> albumSimplifiedPaging = getArtistsAlbumsRequest.execute();
            AlbumSimplified[] result = albumSimplifiedPaging.getItems();

            ArrayList<AlbumSimplified> albums = new ArrayList<>();
            ArrayList<String> albumNames = new ArrayList<>();
            for (AlbumSimplified album :result) {
                String rd = album.getReleaseDate();
                Date date = simp.parse(rd);

                if (date.after(cutoff)) {
                    if (!albumNames.contains(album.getName())) {
                        albumNames.add(album.getName());
                        albums.add(album);
                    }
                }
            }
            System.out.println(albumNames);
            return albums;
        } catch (IOException | SpotifyWebApiException | ParseException | java.text.ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
