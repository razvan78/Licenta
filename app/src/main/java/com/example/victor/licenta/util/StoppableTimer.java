package com.example.victor.licenta.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Victor on 3/11/2018.
 */

public class StoppableTimer extends Timer {

    private boolean hasStartedTimer;
    public StoppableTimer (){super();}

    @Override
    public void schedule(TimerTask timerTask,long duration){
        hasStartedTimer = true;
        super.schedule(timerTask,duration);
    }

    public boolean hasStartedTimer(){
        return hasStartedTimer;
    }
}
