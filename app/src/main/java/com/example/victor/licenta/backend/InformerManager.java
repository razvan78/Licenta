package com.example.victor.licenta.backend;

import com.example.victor.licenta.errorsAndExceptions.UnableToAddInformerException;
import com.example.victor.licenta.errorsAndExceptions.UnableToRemoveInformerException;
import com.example.victor.licenta.modelClass.EmailInformer;
import com.example.victor.licenta.modelClass.INformer;
import com.example.victor.licenta.modelClass.TextMessageInformer;

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
    public  void addInformer(INformer i) throws UnableToAddInformerException {
       if(! informers.add(i)){
           throw new UnableToAddInformerException(i.getName());
       }
    }

    public boolean containsInformer(INformer i){
        for(INformer j: informers){
            if(j.getName().equals(i.getName())){
                return true;
            }
        }
        return false;
    }

    public void removeInformer(INformer i) throws UnableToRemoveInformerException{
        if(!informers.remove(i)){
            throw new UnableToRemoveInformerException(i.getName());
        }
    }

    public void inform(){
        for(INformer i:informers){
            i.inform();
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
