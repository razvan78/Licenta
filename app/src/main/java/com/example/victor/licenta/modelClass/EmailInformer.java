package com.example.victor.licenta.modelClass;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.example.victor.licenta.MainActivity;

/**
 * Created by Victor on 3/3/2018.
 */

public class EmailInformer implements INformer {

    private String emailAdress;

    private boolean active = true;

    public EmailInformer(String emailAdress) {
        this.emailAdress = emailAdress;

    }

    @Override
    public void inform(String message) {


        if (active) {
            MainActivity.currentActivity.runOnUiThread(() -> {
                        BackgroundMail.newBuilder(MainActivity.currentActivity)
                                .withUsername("licenta.sender@gmail.com")
                                .withPassword("parola1234")
                                .withMailto(emailAdress)
                                .withType(BackgroundMail.TYPE_PLAIN)
                                .withSubject("Home SecurityApp")
                                .withBody(message)
                                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                                    @Override
                                    public void onSuccess() {
                                        Log.d("EMAIL", "succes");
                                    }
                                })
                                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                                    @Override
                                    public void onFail() {
                                        Log.d("EMAIL", "fail");
                                    }
                                })
                                .send();
                    }

            );
        }
    }

    @Override
    public String getName() {
        return emailAdress;
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
