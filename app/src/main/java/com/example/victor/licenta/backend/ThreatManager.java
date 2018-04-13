package com.example.victor.licenta.backend;

import com.example.victor.licenta.errorsAndExceptions.UnableToAddSensorException;
import com.example.victor.licenta.errorsAndExceptions.UnableToRemoveSensorException;
import com.example.victor.licenta.modelClass.AudioSensor;
import com.example.victor.licenta.modelClass.CameraSensor;
import com.example.victor.licenta.modelClass.ISensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 3/3/2018.
 */

public class ThreatManager extends Manager{

    private List<ISensor> sensors;
    public ThreatManager(){
        super();
    }

    public void addSensor(ISensor s) throws UnableToAddSensorException{
        if(!sensors.add(s)){
            throw new UnableToAddSensorException(s.getName());
        }
    }

    public void removeSensor(ISensor s) throws UnableToRemoveSensorException{
        if(!sensors.remove(s)){
            throw new UnableToRemoveSensorException(s.getName());
        }
    }

    @Override
    protected void attachImplementationAndInitialize() {
        sensors = new ArrayList<ISensor>() ;
        sensors.add(new CameraSensor());
        impl = new ThreatManagerImpl();
    }

    private class ThreatManagerImpl implements ManagerImplementation{

        @Override
        public void work() {
            for(ISensor s: sensors){
                s.startWorking();
            }
        }

        @Override
        public void stopWorking() {
            for(ISensor s: sensors){
                s.stopWorking();
            }
        }
    }
}
