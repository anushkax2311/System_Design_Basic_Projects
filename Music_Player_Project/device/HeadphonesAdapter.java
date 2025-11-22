package Music_Player_Project.device;

import Music_Player_Project.external.HeadPhonesAPI;
import Music_Player_Project.models.Song;

public class HeadphonesAdapter implements IAudioOutputDevice {
    private HeadPhonesAPI headPhonesAPI;
    
    public HeadphonesAdapter(HeadPhonesAPI api){
        this.headPhonesAPI=api;
    }
    @Override
    public void playAudio(Song song){
        String payload = song.getTitle()+ "by" +song.getArtist();
        headPhonesAPI.playSoundViaJack(payload);
    }


}
