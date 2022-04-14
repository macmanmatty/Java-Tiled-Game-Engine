package com.jessematty.black.tower.Components.CreateEntity;


import com.jessematty.black.tower.Components.Position.PositionComponent;

public class CreateEntityOnTime {
    private  String entityToCreateID;
   private double interval;
    private PositionComponent position;
    public double getInterval() {
        return interval;
    }

    public void setInterval(double interval) {
        this.interval = interval;
    }

    public CreateEntityOnTime() {

    }

    public String getEntityToCreateID() {
        return entityToCreateID;
    }

    public void setEntityToCreateID(String entityToCreateID) {
        this.entityToCreateID = entityToCreateID;
    }

    public PositionComponent getPosition() {
        return position;
    }

    public void setPosition(PositionComponent position) {
        this.position = position;
    }
}
