package com.example.victor.licenta.backend;

import com.example.victor.licenta.modelClass.INformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 3/3/2018.
 */

public class InformerManager extends  Manager{

    private static InformerManager instance;

    private List<INformer> informers;

    private InformerManager(){
        super();
    }

     public static InformerManager getInstance() {
        if(instance==null)
        {
            instance = new InformerManager();
        }
        return instance;
    }
    public  void addInformer(INformer i) {
      informers.add(i);

    }

    public boolean containsInformer(INformer i){
        for(INformer j: informers){
            if(j.getName().equals(i.getName())){
                return true;
            }
        }
        return false;
    }

    public void removeInformer(INformer i) {
        informers.remove(i);
    }

    public void clear(){
        informers.clear();
    }

    public void inform(String message){
        for(INformer i:informers){
            i.inform(message);
        }
    }

    @Override
    protected void attachImplementationAndInitialize() {
        informers = new ArrayList<INformer>();
        impl = new InformerManagerImpl();
    }

    private class InformerManagerImpl implements  ManagerImplementation{

        @Override
        public void work() {
            for(INformer i:informers){
                i.setInformer();
            }
        }

        @Override
        public void stopWorking() {
            for(INformer i:informers){
                i.deactivate();
            }
        }
    }
}
