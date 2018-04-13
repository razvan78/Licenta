package com.example.victor.licenta.modelClass;

import android.telephony.SmsManager;

import com.example.victor.licenta.MainActivity;

/**
 * Created by Victor on 3/3/2018.
 */

public class TextMessageInformer implements INformer {

    private String number;

    public TextMessageInformer(String number){
        this.number = number;
    }

    @Override
    public void inform() {
        //TODO: take data from DataManager
     MainActivity.currentActivity.runOnUiThread(()->{
            SmsManager sender = SmsManager.getDefault();
            sender.sendTextMessage(number,null,"text de test",null,null);
        });


        //TODO: more research here
    }

    @Override
    public String getName() {
        return number;
    }

    @Override
    public void setInformer() {
        //TODO: set the configuration here
    }

    @Override
    public void deactivate() {
    //TODO: deactivate here the configuration
    }
}
