package com.example.victor.licenta.errorsAndExceptions;

/**
 * Created by Victor on 3/4/2018.
 */

public abstract class UnableException extends Exception {

    private String sourceName;
    protected String myMessage;

    public UnableException(String sourceName){
       this.sourceName=sourceName;
    }

     public String getMessage(){
        return myMessage + " " + sourceName;
     }
}
