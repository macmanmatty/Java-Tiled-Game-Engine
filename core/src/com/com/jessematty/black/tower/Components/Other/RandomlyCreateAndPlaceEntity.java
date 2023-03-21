package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Position.PositionComponent;


public class RandomlyCreateAndPlaceEntity  implements Component {

   private  float entitiesToCreate =-1;
  private   float createInterval;
    private boolean randomLocation=true;
    private boolean randomMap=false; // whether of not place on random map  if not a random map will be selected to place entity in
    private Array<PositionComponent> positionsToCreateAt = new Array<>(); // the positions for the entity to be placed at if used
    private Array<String> mapIds= new Array<>(); // the map locations for the entity to be placed at
    private int entitiesPlaced;
    // x and y are the map locations and  z is the building number of z<0 the entity is not placed in a building
    public float getEntitiesToCreate() {
        return entitiesToCreate;
    }

    public void setEntitiesToCreate(float entitiesToCreate) {
        this.entitiesToCreate = entitiesToCreate;
    }

    public float getCreateInterval() {
        return createInterval;
    }

    public void setCreateInterval(float createInterval) {
        this.createInterval = createInterval;
    }

    public boolean isRandomLocation() {
        return randomLocation;
    }

    public void setRandomLocation(boolean randomLocation) {
        this.randomLocation = randomLocation;
    }

    public Array<PositionComponent> getPositionsToCreateAt() {
        return positionsToCreateAt;
    }

    public Array<String> getMapIds() {
        return mapIds;
    }

    public boolean isRandomMap() {
        return randomMap;
    }

    public void setRandomMap(boolean randomMap) {
        this.randomMap = randomMap;
    }

    public void placeEntity(){

        entitiesPlaced++;

    }

    public int getEntitiesPlaced() {
        return entitiesPlaced;
    }


}
