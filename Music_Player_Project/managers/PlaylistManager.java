package Music_Player_Project.managers;

import java.util.HashMap;
import java.util.Map;

import Music_Player_Project.models.PlayList;
import Music_Player_Project.models.Song;

public class PlaylistManager {
    private static PlaylistManager instance=null;
    private Map<String,PlayList> playlists;

    private PlaylistManager(){
        playlists = new HashMap<>();
    }
    public static synchronized PlaylistManager getInstance(){
        if (instance==null) {
           instance = new PlaylistManager();
        }
        return instance;
    }
    public void createPlaylist(String name){
        if (playlists.containsKey(name)) {
            throw new RuntimeException("Playlist \""+name+"\"already exists.");
        }
        playlists.put(name, new PlayList(name));
    }

    public void addSongToPlaylist(String playlistName , Song song){
        if (!playlists.containsKey(playlistName)) {
            throw new RuntimeException("Playlist \""+playlistName+"\"not found");
        }
        playlists.get(playlistName).addSongToPlaylist(song);
    }

    public PlayList getPlayList(String name){
        if (!playlists.containsKey(name)) {
            throw new RuntimeException("Playlist \""+name+"\"not found.");
        }
        return playlists.get(name);
    }
}
