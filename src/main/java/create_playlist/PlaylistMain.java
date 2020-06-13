package create_playlist;

import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;

import java.text.ParseException;
import java.util.ArrayList;

public class PlaylistMain {
    protected static final String accessToken = "BQB1yEZMQ5mtC16Sp9MvFmdylrTaWtgTTiGIdSPIE4v1oaxcnFMD9ofn4o23TGBKLLY8OEZsRFn_EIQ5h5DJSH8nm7bRvmuRgL-kydfwyWaIwSa2v6DjVnouDViBhvcP1U0aQM2ou_bB7LTMN5u6hxSbWZFqFi019u5ugQgjOhJfr6O9tGbMCHRoRcjOvqVIyN-OG7bzXWrwPluexD3o7YTu0UFLCSLpC_7crULUPMeZt7krAXCqEE7C2e_cDabEIJzXpJMPoOsdpZomutfqBnwxjm-A03PDgVZO";
    protected static final String userId = "qzxb7k3cbr15kbmaney4brt79";

    public static void main(String[] args) throws ParseException {
        GetArtistIds g = new GetArtistIds("Polo G");
        String id = g.searchArtists();

        GetAlbums a = new GetAlbums(id, "2020-03-01");
        ArrayList<AlbumSimplified> albums = a.getArtistsAlbums();

        ArrayList<String> allTracks = new ArrayList<>();

        for (AlbumSimplified album : albums) {
            GetAlbumTracks gat = new GetAlbumTracks(album.getId());
            TrackSimplified[] tracks = gat.getAlbumsTracks();

            for (TrackSimplified track : tracks) {
                if (!allTracks.contains(track.getUri())) {
                    for (ArtistSimplified artist : track.getArtists()) {
                        if (artist.getId().equals(id)) {
                            allTracks.add(track.getUri());
                        }
                    }
                }
            }
        }
//rona szn
        String[] uris = allTracks.toArray(new String[0]);

        CreatePlaylist p = new CreatePlaylist();
        String playlistId = p.createPlaylist();
        System.out.println(playlistId);
        System.out.println(uris);

        AddTracks add = new AddTracks(playlistId, uris);
        add.addItemsToPlaylist();


    }
}
