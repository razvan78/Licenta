package com.example.victor.licenta.util;

import android.content.Context;
import android.widget.Toast;

import com.example.victor.licenta.MainActivity;

/**
 * Created by Victor on 3/11/2018.
 */

public class ToastMaker {
    private Context context;
    public ToastMaker(Context context){
        this.context = context;
    }
    public static void show(String message){
        Toast.makeText(MainActivity.currentActivity,message,Toast.LENGTH_SHORT).show();
    }
}
