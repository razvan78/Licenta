package com.example.victor.licenta.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Victor on 3/11/2018.
 */

public class ToastMaker {
    private Context context;
    public ToastMaker(Context context){
        this.context = context;
    }
    public void show(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
