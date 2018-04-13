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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private String phoneNumber;

}
