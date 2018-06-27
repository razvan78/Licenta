package com.example.victor.licenta.backend;

import com.example.victor.licenta.modelClass.CameraSensor;
import com.example.victor.licenta.modelClass.ISensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 3/3/2018.
 */

public class ThreatManager extends Manager {

    private static ThreatManager instance;
    private List<ISensor> sensors;

    private ThreatManager() {
        super();
    }

    public static ThreatManager getInstance() {
        if (instance == null) {
            instance = new ThreatManager();
        }
        return instance;
    }

    public void addSensor(ISensor s) {
        sensors.add(s);
    }

    public void removeSensor(ISensor s) {
        sensors.remove(s);
    }

    public void clear(){
        sensors.clear();
    }
    @Override
    protected void attachImplementationAndInitialize() {
        sensors = new ArrayList<ISensor>();
        impl = new ThreatManagerImpl();
    }

    private class ThreatManagerImpl implements ManagerImplementation {

        @Override
        public void work() {
            for (ISensor s : sensors) {
                s.startWorking();
            }
        }

        @Override
        public void stopWorking() {
            for (ISensor s : sensors) {
                s.stopWorking();
            }
        }
    }
}
