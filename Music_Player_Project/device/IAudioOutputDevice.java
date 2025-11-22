package Music_Player_Project.device;

import Music_Player_Project.models.Song;

public interface IAudioOutputDevice {
    void playAudio(Song song);
}
