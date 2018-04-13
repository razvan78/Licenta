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

    public EmailInformer(String emailAdress) {
        this.emailAdress = emailAdress;

    }

    @Override
    public void inform() {

        //TODO: take all data from DataManager
        //TODO: refactor all to intent
        MainActivity.currentActivity.runOnUiThread(() -> {
            BackgroundMail.newBuilder(MainActivity.currentActivity)
                    .withUsername("licenta.sender@gmail.com")
                    .withPassword("parola1234")
                    .withMailto(emailAdress)
                    .withType(BackgroundMail.TYPE_PLAIN)
                    .withSubject("Subiect de test")
                    .withBody("Alerta!")
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

    @Override
    public String getName() {
        return emailAdress;
    }


    @Override
    public void setInformer() {
        //TODO: set configuration here
    }

    @Override
    public void deactivate() {
        //TODO: deactivate the informer here
    }
}
