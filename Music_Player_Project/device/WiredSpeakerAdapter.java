package Music_Player_Project.device;

import Music_Player_Project.external.WiredSpeakerAPI;
import Music_Player_Project.models.Song;

public class WiredSpeakerAdapter implements IAudioOutputDevice{
    private WiredSpeakerAPI wiredApi;

    public WiredSpeakerAdapter(WiredSpeakerAPI api){
        this.wiredApi = api;
    }

    @Override
    public void playAudio(Song song){
        String payload= song.getTitle()+"by"+song.getArtist();
        wiredApi.playSoundViaCable(payload);
    }
}
