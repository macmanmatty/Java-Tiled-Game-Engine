package com.jessematty.black.tower.Components.CreateEntity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.ObjectMap;

public class CreateEntityOnBooleanStatChange {
   private  String entityToCreateID;
   private ObjectMap<String, Boolean> flagsToCreateOn= new ObjectMap<>();

    public CreateEntityOnBooleanStatChange() {

    }

    public String getEntityToCreateID() {
        return entityToCreateID;
    }

    public void setEntityToCreateID(String entityToCreateID) {
        this.entityToCreateID = entityToCreateID;
    }

    public ObjectMap<String, Boolean> getFlagsToCreateOn() {
        return flagsToCreateOn;
    }


}
