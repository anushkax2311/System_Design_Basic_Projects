package Music_Player_Project.device;

import Music_Player_Project.external.BluetoothSpeakerAPI;
import Music_Player_Project.models.Song;

public class BluetoothSpeakerAdapter implements IAudioOutputDevice {
    private BluetoothSpeakerAPI bluetoothAPI;

    public BluetoothSpeakerAdapter(BluetoothSpeakerAPI api){
        this.bluetoothAPI = api;
    }
    @Override
    public void playAudio(Song song){
String payload = song.getTitle() + "by" +song.getArtist();
bluetoothAPI.playSoundViaBluetooth(payload);
    }
}
