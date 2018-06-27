package com.example.victor.licenta.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.victor.licenta.MainActivity;
import com.example.victor.licenta.modelClass.EmailInformer;

import java.util.HashMap;
import java.util.Map;

/**
 * this class contains all of the configuration values needed
 * Created by Victor on 3/6/2018.
 */

public class DataManager {

    private static String EMAIL_CODE = "EyeWatchEMAIL_KEY";
    private static String NUMBER_CODE = "EyeWatchNUMBER_KEY";
    private static String NOTIFI_ON_START_CODE = "EyeWatchSTATE_KEY";
    private static String AUDIO_THRESH = "EyeWatchAUDIO_THRESH";
    private static String USE_AUDIO = "EyeWatchAUDIO_USE";
    private static String USE_CAMERA = "EyeWatchCAMERA_USE";
    private static String FRONT_CAMERA = "EyeWatchFRONT_CAMERA";

    private static DataManager instance;
    private Configurations appConfig;
    private SharedPreferences sharedPreferences;

    private DataManager() {
        appConfig = new Configurations();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.currentActivity);
        loadFromDevice();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public synchronized void saveToDevice() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(NOTIFI_ON_START_CODE, appConfig.getNotifOnStart());
        editor.putString(EMAIL_CODE, appConfig.getEmail());
        editor.putString(NUMBER_CODE, appConfig.getPhoneNumber());
        editor.putInt(AUDIO_THRESH, appConfig.getAudioThresh());
        editor.putBoolean(USE_CAMERA, appConfig.isUseCamera());
        editor.putBoolean(USE_AUDIO, appConfig.isUseAudio());
        editor.putBoolean(FRONT_CAMERA, appConfig.isUseFrontCamera());

        editor.apply();
    }

    public synchronized void loadFromDevice() {

        appConfig.setNotifOnStart(sharedPreferences.getBoolean(NOTIFI_ON_START_CODE, true));
        appConfig.setEmail(sharedPreferences.getString(EMAIL_CODE, "no email selected"));
        appConfig.setPhoneNumber(sharedPreferences.getString(NUMBER_CODE, ""));
        appConfig.setAudioThresh(sharedPreferences.getInt(AUDIO_THRESH, 10));
        appConfig.setUseAudio(sharedPreferences.getBoolean(USE_AUDIO, false));
        appConfig.setUseCamera(sharedPreferences.getBoolean(USE_CAMERA, false));
        appConfig.setUseFrontCamera(sharedPreferences.getBoolean(FRONT_CAMERA, false));
    }

    public synchronized Configurations getAppConfig() {
        return appConfig;
    }

}
