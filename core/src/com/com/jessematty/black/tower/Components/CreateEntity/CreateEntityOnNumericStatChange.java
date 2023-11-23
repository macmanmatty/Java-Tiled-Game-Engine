package com.jessematty.black.tower.Components.CreateEntity;

import com.badlogic.gdx.utils.ObjectMap;

public class CreateEntityOnNumericStatChange  {
    private  String entityToCreateID;
   private ObjectMap<String, Double> greaterThanStats= new ObjectMap<>();
    private ObjectMap<String, Double> lessThanStats= new ObjectMap<>();
    private ObjectMap<String, Double> equalToStats= new ObjectMap<>();
    public CreateEntityOnNumericStatChange() {

    }

    public String getEntityToCreateID() {
        return entityToCreateID;
    }

    public void setEntityToCreateID(String entityToCreateID) {
        this.entityToCreateID = entityToCreateID;
    }

    public ObjectMap<String, Double> getGreaterThanStats() {
        return greaterThanStats;
    }

    public ObjectMap<String, Double> getLessThanStats() {
        return lessThanStats;
    }

    public ObjectMap<String, Double> getEqualToStats() {
        return equalToStats;
    }
}
