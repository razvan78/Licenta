package com.example.victor.licenta.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.victor.licenta.MainActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * this class contains all of the configuration values needed
 * Created by Victor on 3/6/2018.
 */

public class DataManager {

    private static String EMAIL_CODE="EyeWatchEMAIL_KEY";
    private static String NUMBER_CODE="EyeWatchNUMBER_KEY";
    private static String NOTIFI_ON_START_CODE="EyeWatchSTATE_KEY";
    private static DataManager instance;
    private Configurations appConfig;
    private SharedPreferences sharedPreferences;
    private DataManager() {
        appConfig = new Configurations();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.currentActivity);
    }

     public static DataManager getInstance() {
         if(instance==null){
             instance = new DataManager();
         }
        return instance;
    }

    public synchronized void saveToDevice(){
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putBoolean(NOTIFI_ON_START_CODE,appConfig.getNotifOnStart());
    editor.putString(EMAIL_CODE,appConfig.getEmail());
    editor.putString(NUMBER_CODE,appConfig.getPhoneNumber());


    editor.apply();
        //TODO: save to device da   ta and files
    }

    public synchronized void loadFromDevice(){
        //TODO: make an event in which way InformerMAnager gets the new Values
        appConfig.setNotifOnStart(sharedPreferences.getBoolean(NOTIFI_ON_START_CODE,true));
        appConfig.setEmail(sharedPreferences.getString(EMAIL_CODE,"no email selected"));
        appConfig.setPhoneNumber(sharedPreferences.getString(NUMBER_CODE,"0000000000"));
        //todo: load from device all the stored data
    }

    public synchronized Configurations getAppConfig(){
        return appConfig;
    }

}
