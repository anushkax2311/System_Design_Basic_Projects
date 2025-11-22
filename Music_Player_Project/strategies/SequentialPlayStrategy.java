package Music_Player_Project.strategies;

import Music_Player_Project.models.PlayList;
import Music_Player_Project.models.Song;

public class SequentialPlayStrategy implements PlayStrategy {
    private PlayList currentPlayList;
    private int currentIndex;

    public SequentialPlayStrategy(){
        currentPlayList=null;
        currentIndex = -1;
    }

    @Override
    public void setPlaylist(PlayList playList) {
      currentPlayList =playList;
      currentIndex = -1;
    }

    @Override
    public Song next() {
       if (currentPlayList == null || currentPlayList.getSize()==0) {
        throw new RuntimeException("No playlist loaded or playlist is empty");
       }
       currentIndex = currentIndex +1;
       return currentPlayList.getSongs().get(currentIndex);
    }

    @Override
    public boolean hasNext() {
       return ((currentIndex+1) < currentPlayList.getSize());
    }

    @Override
    public Song previous() {
       if (currentPlayList.getSize()==0 ||currentPlayList==null) {
        throw new RuntimeException("No playlist loaded or playlist is empty");
       }
       currentIndex = currentIndex -1;
       return currentPlayList.getSongs().get(currentIndex);
    }

    @Override
    public boolean hasPrevious() {
       return (currentIndex-1>0) ;
    }
}
