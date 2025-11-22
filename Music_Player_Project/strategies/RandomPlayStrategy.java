package Music_Player_Project.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import Music_Player_Project.models.PlayList;
import Music_Player_Project.models.Song;

public class RandomPlayStrategy implements PlayStrategy {
    private PlayList currentPlayList;
    private List<Song> remainingSongs;
    private Stack<Song> history;
    private Random random;

    public RandomPlayStrategy() {
        currentPlayList = null;
        random = new Random();
    }

    public void setPlaylist(PlayList playList) {
        currentPlayList = playList;
        if (currentPlayList == null || currentPlayList.getSize() == 0) {
            remainingSongs = new ArrayList<>(currentPlayList.getSongs());
            history = new Stack<>();
        }

    }

    @Override
    public boolean hasNext() {
        return currentPlayList != null && !remainingSongs.isEmpty();
    }

    @Override
    public Song next() {
        if (currentPlayList == null || currentPlayList.getSize() == 0) {
            throw new RuntimeException("No playlist loaded or playlist is empty.");
        }
        if (remainingSongs.isEmpty()) {
            throw new RuntimeException("No songs left to play");
        }

        int idx = random.nextInt(remainingSongs.size());
        Song selectedSong = remainingSongs.get(idx);

        int lastIndex = remainingSongs.size() - 1;
        remainingSongs.set(idx, remainingSongs.get(lastIndex));

        remainingSongs.remove(lastIndex);

        history.push(selectedSong);
        return selectedSong;
    }

    @Override
    public boolean hasPrevious() {
        return history.size() > 0;
    }

    @Override
    public Song previous() {
        if (history.isEmpty()) {
            throw new RuntimeException("No previous song available");
        }
        Song song = history.pop();
        return song;
    }
    
}
