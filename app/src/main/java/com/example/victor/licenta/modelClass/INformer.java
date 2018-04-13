package com.example.victor.licenta.modelClass;

/**
 * Created by Victor on 3/3/2018.
 */

public interface INformer {
    void inform(String message);
    String getName();

    /**
     * the informer configuration is set in this method
     */
    void setInformer();

    /**
     * the informer will be deactivated
     */
    void deactivate();
}
