package com.example.victor.licenta.backend;

/**
 *
 * Created by Victor on 3/4/2018.
 */

public abstract class Manager {

    private boolean hasStarted;
    protected ManagerImplementation impl;

        public Manager(){
             attachImplementationAndInitialize();
        }
    public final void start(){
        hasStarted = true;
        impl.work();
    }


    public final void stop(){
        hasStarted = false;
        impl.stopWorking();
    }


    public final boolean isWorking(){
        return hasStarted;
    }

    /**
     * In this method impl must be set
     */
    protected abstract void attachImplementationAndInitialize();
}
