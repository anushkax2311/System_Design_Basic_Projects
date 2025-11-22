package Music_Player_Project.strategies;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import Music_Player_Project.models.PlayList;
import Music_Player_Project.models.Song;

public class CustomQueueStrategy implements PlayStrategy{
    private PlayList currentPlayList;
    private int currentIndex;
    private Queue<Song> nextQueue;
    private Stack<Song> prevStack;

    private Song nextSequential(){
        if (currentPlayList.getSize()==0) {
            throw new RuntimeException("Playlist is empty.");
        }
        currentIndex = currentIndex + 1;
        return currentPlayList.getSongs().get(currentIndex);
    }

    private Song previousSequential(){
        if (currentPlayList.getSize()==0) {
            throw new RuntimeException("Playlist is empty.");
        }
        currentIndex = currentIndex - 1;
        return currentPlayList.getSongs().get(currentIndex);
    }
    public CustomQueueStrategy(){
        currentPlayList = null;
        currentIndex = -1;
        nextQueue = new LinkedList<>();
        prevStack = new Stack<>();
    }
    
    @Override
    public void setPlaylist(PlayList playList){
        currentPlayList = playList;
        currentIndex = -1;
        nextQueue.clear();
        prevStack.clear();
    }
    
    @Override
    public boolean hasNext(){
        return ((currentIndex + 1)<currentPlayList.getSize());
    }

    @Override
    public Song next(){
if (currentPlayList==null || currentPlayList.getSize()==0) {
    throw new RuntimeException("No Playlist loaded or playlist is empty");
}
if (!nextQueue.isEmpty()) {
    Song s = nextQueue.poll();
    prevStack.push(s);

    for(int i = 0; i<currentPlayList.getSongs().size(); ++i){
        if (currentPlayList.getSongs().get(i)==s) {
             currentIndex = i;
             break;
        }
    }
    return s;
}
return nextSequential();
    }

    @Override
    public boolean hasPrevious(){
        return (currentIndex - 1 < 0);
    }

    @Override
    public Song previous() {
       if (currentPlayList == null || currentPlayList.getSize() == 0) {
        throw new RuntimeException("No Playlist loaded or playlist is empty.");
       }
       if (!prevStack.isEmpty()) {
            Song s = prevStack.pop();

            for(int i=0;i<currentPlayList.getSongs().size();i++){
                if (currentPlayList.getSongs().get(i)==s) {
                     currentIndex = i;
                     break;
                }
            }
            return s;
       }
       return previousSequential();
    }

    @Override
    public void addToNext(Song song){
        if (song==null) {
            throw new RuntimeException("Cannot enqueue null song.");
        }
        nextQueue.add(song);
    }
}

