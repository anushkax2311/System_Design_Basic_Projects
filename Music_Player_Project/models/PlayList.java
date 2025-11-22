package Music_Player_Project.models;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
    private String playlistName;
    private List<Song> songList;

    public PlayList(String name){
        this.playlistName = name;
        this.songList = new ArrayList<>();

    }

    public String getPlaylistName(){
        return playlistName;
    }

    public List<Song> getSongs(){
        return songList;
    }

    public int getSize(){
        return songList.size();
    }
    public void addSongToPlaylist(Song song){
        if (song==null) {
            throw new RuntimeException("Cannot add null song to playlist");
        }
        songList.add(song);
    }
}
