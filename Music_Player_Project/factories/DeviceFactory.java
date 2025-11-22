package Music_Player_Project.factories;

import Music_Player_Project.device.BluetoothSpeakerAdapter;
import Music_Player_Project.device.HeadphonesAdapter;
import Music_Player_Project.device.IAudioOutputDevice;
import Music_Player_Project.device.WiredSpeakerAdapter;
import Music_Player_Project.enums.DeviceType;
import Music_Player_Project.external.BluetoothSpeakerAPI;
import Music_Player_Project.external.HeadPhonesAPI;
import Music_Player_Project.external.WiredSpeakerAPI;

public class DeviceFactory {
    public static IAudioOutputDevice creatDevice(DeviceType deviceType){
        switch (deviceType) {
            case BLUETOOTH:
                return new BluetoothSpeakerAdapter(new BluetoothSpeakerAPI());
            case WIRED:
                return new WiredSpeakerAdapter(new WiredSpeakerAPI());
            
            case HEADPHONES:
            default:
                return new HeadphonesAdapter(new HeadPhonesAPI());
        }
    }
}
