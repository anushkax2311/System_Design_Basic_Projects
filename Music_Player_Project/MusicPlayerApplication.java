package Music_Player_Project;

import java.util.ArrayList;
import java.util.List;

import Music_Player_Project.models.Song;

public class MusicPlayerApplication {
    private static MusicPlayerApplication instance = null;   //instance → static reference to the single DeviceManager.
    private List<Song> songLibrary;

    private MusicPlayerApplication(){
        songLibrary = new ArrayList<>();
    }

    public static synchronized MusicPlayerApplication getInstance(){     //synchronized ensures thread safety (only one thread can create the instance at a time).
        if (instance==null) {
            instance = new MusicPlayerApplication();
        }
        return instance;
    }
    public void createSongInLibrary(String title , String artist , String path){
        Song newSong = new Song(title, artist, path);
        songLibrary.add(newSong);
    }

}
