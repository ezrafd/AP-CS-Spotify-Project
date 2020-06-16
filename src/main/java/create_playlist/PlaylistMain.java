package create_playlist;

import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.*;

public class PlaylistMain {
    protected static final String accessToken = "BQA8MxkbFEe1GcoPk2OjixoxubRedF7OO_fMZDxrpHTeoJbMIip9iPioKR3ru_4UfQDzbPnDudCjGZme_aAzXIlmwgPxMycOhqNvaCbTflTtZE8WAyzQzWffMBtiw8-2guwT_pMFvcU1tLFqYSTHnLInYjlZ5QQS6bW4z-Px5s-5ky2OH8G-FMq15HuJ8QwLMb3fITjVLh3uUgEtWZo91BgI_l-bFawbvHdogBH7oba2jahL2adUaxZ4GH2QMRkaHgxHli9Pui1Ydw2AA4KBCSyOgxhG329Pqyao";
    protected static final String userId = "qzxb7k3cbr15kbmaney4brt79";

    public static void main(String[] args) throws ParseException {
        boolean status = true;
        ArrayList<String> artistNames = new ArrayList<>();

        while(status) {
              System.out.print("enter an artist: ");
              Scanner userInput = new Scanner(System.in);
              String artist = userInput.next();
              artistNames.add(artist);
              Scanner stopper = new Scanner(System.in);
              System.out.print("do you want to add another artist (yes or no): ");
              String value = stopper.next();
              if (value.equalsIgnoreCase("no")) {
                status = false;
              }
        }

        CreatePlaylist p = new CreatePlaylist();
        String playlistId = p.createPlaylist();

        System.out.print("Enter a cutoff date (yyyy-MM-dd): ");
        Scanner date = new Scanner(System.in);
        String cutoffDate = date.next();

        ArrayList<String> allTracks = new ArrayList<>();

        for (int i = 0; i < artistNames.size(); i++){
            for (int j = i+1; j < artistNames.size(); j++) {
                 if (artistNames.get(i).equals(artistNames.get(j))){
                        artistNames.remove(i);
                 }
            }

            GetArtistIds g = new GetArtistIds(artistNames.get(i));
            String id = g.searchArtists();

            GetAlbums a = new GetAlbums(id, cutoffDate);
            ArrayList<AlbumSimplified> albums = a.getArtistsAlbums();

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

            if (allTracks.size()==0) {
                break;
            }

            String[] uris = allTracks.toArray(new String[0]);

            AddTracks add = new AddTracks(playlistId, uris);
            add.addItemsToPlaylist();

            allTracks.clear();
        }
    }
}
