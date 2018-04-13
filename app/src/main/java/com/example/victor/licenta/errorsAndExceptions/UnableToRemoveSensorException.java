package com.example.victor.licenta.errorsAndExceptions;

/**
 * Created by Victor on 3/4/2018.
 */

public class UnableToRemoveSensorException extends UnableException {
    public UnableToRemoveSensorException(String sourceName) {
        super(sourceName);
        myMessage = "Unable to remove sensor";
    }
}
