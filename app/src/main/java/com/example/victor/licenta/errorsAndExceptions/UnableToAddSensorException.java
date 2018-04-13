package com.example.victor.licenta.errorsAndExceptions;

/**
 * Created by Victor on 3/4/2018.
 */

public class UnableToAddSensorException extends UnableException {
    public UnableToAddSensorException(String sourceName) {
        super(sourceName);
        myMessage = "Unable to add sensor";
    }
}
