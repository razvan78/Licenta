package com.example.victor.licenta.backend;

import android.util.Log;

import com.example.victor.licenta.events.ApplicationStartedEvent;
import com.example.victor.licenta.events.ThreatFoundEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Victor on 3/3/2018.
 */

public class SecurityManager extends Manager{
    private InformerManager informerManager;
    private ThreatManager threatManager;

    public SecurityManager(){
        //TODO: maybe backend should have getSecurity wich has get Informer
        informerManager = InformerManager.getInstance();
        threatManager = new ThreatManager();
    }

    @Override
    protected void attachImplementationAndInitialize() {
        this.impl = new SecurityManagerImpl();
    }



  private class SecurityManagerImpl implements ManagerImplementation{

      @Override
      public void work() {
          threatManager.start();
          informerManager.start();
          EventBus.getDefault().register(this);
      }

      @Override
      public void stopWorking() {
          threatManager.stop();
          informerManager.stop();
          EventBus.getDefault().unregister(this);
      }

      @Subscribe
      public void onThreatReceived(ThreatFoundEvent threatEvent){
          Log.d("DANGER",threatEvent.getMessage());
          informerManager.inform(threatEvent.getMessage());
      }

      @Subscribe
      public void onApplicationStarted(ApplicationStartedEvent startedEvent){
          Log.d("START",startedEvent.getMessage());
          informerManager.inform(startedEvent.getMessage());
      }
  }
}
