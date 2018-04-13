package com.example.victor.licenta.events;

/**
 * Created by Victor on 3/4/2018.
 */

public abstract class GreenRotEvent {
    private String message;
    public GreenRotEvent(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
