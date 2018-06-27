package com.example.victor.licenta.modelClass;

import android.telephony.SmsManager;

import com.example.victor.licenta.MainActivity;

/**
 * Created by Victor on 3/3/2018.
 */

public class TextMessageInformer implements INformer {

    private String number;
    private boolean active = true;

    public TextMessageInformer(String number) {
        this.number = number;
    }

    @Override
    public void inform(String message) {
        if (active) {
            MainActivity.currentActivity.runOnUiThread(() -> {
                SmsManager sender = SmsManager.getDefault();
                sender.sendTextMessage(number, null, message, null, null);
            });
        }
    }

    @Override
    public String getName() {
        return number;
    }

    @Override
    public void setInformer() {
        active = true;
    }

    @Override
    public void deactivate() {
        active = false;
    }
}
