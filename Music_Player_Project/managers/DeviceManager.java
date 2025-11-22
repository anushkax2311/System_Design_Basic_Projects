package Music_Player_Project.managers;

import Music_Player_Project.device.IAudioOutputDevice;
import Music_Player_Project.enums.DeviceType;
import Music_Player_Project.factories.DeviceFactory;

public class DeviceManager {
    

    public static DeviceManager instance = null;
    private IAudioOutputDevice currentOutputDevice;

    private DeviceManager(){
        currentOutputDevice= null;
    }
     
    public static synchronized DeviceManager getInstance(){
        if (instance==null) {
            instance = new DeviceManager();
        }
        return instance;
    }

    public void connect(DeviceType deviceType){
        if (currentOutputDevice!=null) {
            
        }
        currentOutputDevice = DeviceFactory.creatDevice(deviceType);


        switch (deviceType) {
            case BLUETOOTH:
                System.out.println("Bluetooth device connected");
            case WIRED:
                System.out.println("Wired device connected");
            case HEADPHONES:
                System.out.println("Headphones connected");
                break;
        

        }
    }

    public IAudioOutputDevice getOutputDevice(){
        if (currentOutputDevice==null) {
            throw new RuntimeException("No Output Device is connected");
        }
        return currentOutputDevice;
    }

    public boolean hasOutputDevice(){
        return currentOutputDevice!=null;
    }

}
