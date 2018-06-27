package com.example.victor.licenta.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 3/11/2018.
 */

public class Configurations {
    /**
     * variable that tells if the application will send a message via a choosen informer when it starts
     */
    private boolean notifOnStart;
    public void setNotifOnStart(boolean value) {
        notifOnStart = value;
    }
    public boolean getNotifOnStart() {
        return notifOnStart;
    }

    private String email;
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    private String phoneNumber;
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    private int audioThresh;

    private boolean useAudio;

    private boolean useCamera;

    public int getAudioThresh() {
        return audioThresh;
    }

    public void setAudioThresh(int audioThresh) {
        this.audioThresh = audioThresh;
    }

    public boolean isUseAudio() {
        return useAudio;
    }

    public void setUseAudio(boolean useAudio) {
        this.useAudio = useAudio;
    }

    public boolean isUseCamera() {
        return useCamera;
    }

    public void setUseCamera(boolean useCamera) {
        this.useCamera = useCamera;
    }

    public boolean isUseFrontCamera() {
        return useFrontCamera;
    }

    public void setUseFrontCamera(boolean useFrontCamera) {
        this.useFrontCamera = useFrontCamera;
    }

    private boolean useFrontCamera;


}
