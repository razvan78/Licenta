package com.example.victor.licenta.errorsAndExceptions;

/**
 * Created by Victor on 3/3/2018.
 */

public class UnableToAddInformerException extends UnableException {


    public UnableToAddInformerException(String sourceName){
        super(sourceName);
        myMessage = "Unable to add informer";
    }

}
