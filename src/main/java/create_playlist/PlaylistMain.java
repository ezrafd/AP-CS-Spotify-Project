package create_playlist;

import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;

import java.text.ParseException;
import java.util.ArrayList;

public class PlaylistMain {
    protected static final String accessToken = "BQCHLn-P7JZcAxSCZnW2HGRZR0lbTuc-OJOpcaOTxc8l3UTR1Bk2KnREAOMjpE8Ehf_dtAT5Jvx5srgNNtTMCYnb6S535W9bNnUisBinFoGE79R8qBJXmBI0YRqQtKV6zIGlGgTwoR5pIioVaKxQSskiDBpPNcNRVp8y6Mj2-oT9TLCKl1vgAz8gnas91uAMgidhgck-GSWMmLqbqS29oJAD6bQhw_F1zsTiAE3fSEFam1Skvhrya58GbBeVNAR4fKaS8r8YyBDtlKDh2mvT7SRhgQy5BTX4mMxB";
    protected static final String userId = "qzxb7k3cbr15kbmaney4brt79";

    public static void main(String[] args) throws ParseException {
        GetArtistIds g = new GetArtistIds("Polo G");
        String id = g.searchArtists();

        GetAlbums a = new GetAlbums(id, "2020-03-01");
        ArrayList<AlbumSimplified> albums = a.getArtistsAlbums();

        ArrayList<String> allTracks = new ArrayList<>();
        ArrayList<String> trackNames = new ArrayList<>();

        for (AlbumSimplified album : albums) {
            GetAlbumTracks gat = new GetAlbumTracks(album.getId());
            TrackSimplified[] tracks = gat.getAlbumsTracks();

            for (TrackSimplified track : tracks) {
                if (!allTracks.contains(track.getUri())) {
                    trackNames.add(track.getName());
                    allTracks.add(track.getUri());
                }
            }
        }

        CreatePlaylist p = new CreatePlaylist();
        p.createPlaylist();

        for (String uri : allTracks) {

        }

    }
}
