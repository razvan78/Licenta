package com.example.victor.licenta.backend;

/**
 * Created by Victor on 3/3/2018.
 */

public class BackendManager extends Manager {
    private SecurityManager securityManager;

    private static BackendManager instance;

    private BackendManager() {
        super();
    }

    @Override
    protected void attachImplementationAndInitialize() {
        securityManager = new SecurityManager();
        impl = new BackendManagerImplementation();
    }

    public static BackendManager getInstance() {
        if (instance == null) {
            instance = new BackendManager();
        }
        return instance;
    }

    private class BackendManagerImplementation implements ManagerImplementation{

        @Override
        public void work() {
            securityManager.start();
        }

        @Override
        public void stopWorking() {
            securityManager.stop();
        }
    }

}


