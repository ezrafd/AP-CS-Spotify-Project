package create_playlist;

import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.*;

public class PlaylistMain {
    protected static final String accessToken = "BQB1yEZMQ5mtC16Sp9MvFmdylrTaWtgTTiGIdSPIE4v1oaxcnFMD9ofn4o23TGBKLLY8OEZsRFn_EIQ5h5DJSH8nm7bRvmuRgL-kydfwyWaIwSa2v6DjVnouDViBhvcP1U0aQM2ou_bB7LTMN5u6hxSbWZFqFi019u5ugQgjOhJfr6O9tGbMCHRoRcjOvqVIyN-OG7bzXWrwPluexD3o7YTu0UFLCSLpC_7crULUPMeZt7krAXCqEE7C2e_cDabEIJzXpJMPoOsdpZomutfqBnwxjm-A03PDgVZO";
    protected static final String userId = "qzxb7k3cbr15kbmaney4brt7";
    private ArrayList<String> artistNames;
    
    public static void main(String[] args) throws ParseException {
boolean status = true;
while(true) {
      System.out.print("enter an artist: ");
      Scanner userInput = new Scanner(System.in);
      String artist = userInput.next();
      artistNames.add(artist);
      Scanner stopper = new Scanner(System.in);
      System.out.print("do you want to add another artist (yes or no): ");
      String value = userInput.next();    
      if (value.equalsIgnoreCase("no")) {
      status = false;
}	
}
for (int i = 0; i < artistNames.size(); i++){
for (int j = i+1; j < artistNames.size(); j++) {
         if (artistName.get(i)== artistName.get(j)){
            artistName.remove(i);
         }
}
System.out.print("Enter a cutoff date (yyyy-MM-dd):");
     Scanner date = new Scanner(System.in);
      String cutoffDate = datet.next();

        GetArtistIds g = new GetArtistIds(i);
        String id = g.searchArtists();
	
        GetAlbums a = new GetAlbums(id, cutoffDate);
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

        String[] uris = allTracks.toArray(new String[0]);

        CreatePlaylist p = new CreatePlaylist();
        String playlistId = p.createPlaylist();
        System.out.println(playlistId);
        System.out.println(uris);

        AddTracks add = new AddTracks(playlistId, uris);
        add.addItemsToPlaylist();


    }
}	
}
