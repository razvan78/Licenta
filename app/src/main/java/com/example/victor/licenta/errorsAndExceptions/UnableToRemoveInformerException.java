package com.example.victor.licenta.errorsAndExceptions;

/**
 * Created by Victor on 3/3/2018.
 */

public class UnableToRemoveInformerException extends UnableException {

    public UnableToRemoveInformerException(String sourceName) {
        super(sourceName);
        myMessage = "Unable to remove informer";
    }
}
