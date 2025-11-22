package Music_Player_Project.strategies;

import Music_Player_Project.models.PlayList;
import Music_Player_Project.models.Song;

public interface PlayStrategy {
    void setPlaylist(PlayList playList);
    Song next();
    boolean hasNext();
    Song previous();
    boolean hasPrevious();
    default void addToNext(Song song){}
}
