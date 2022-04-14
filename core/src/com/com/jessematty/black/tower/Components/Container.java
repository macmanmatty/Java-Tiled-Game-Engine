package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

public class Container implements Component {
    private double innerVolume; // maximum volumer pack can hold
   private double filledInnerVolume; // current volume of all the item in the pack.
   private  double maxHoldWeight; // maximum weight the pack can carry determined by the fighetr who is wearing it.
    private double currentWeight; // the current weight of all the item sthe pack.
   private Array<String > entitiesInContainerIds = new Array<String>(); // the items in the pack.
   private  Array<String> groupsAddable= new Array<String>();
   private boolean listEntities; // whether or not to display entities  info



    public Container() {

        innerVolume =200;
        maxHoldWeight =200;
    }


    public double getCurrentCarryVolume(){
        return filledInnerVolume;
    }
    public double getCurrentWeight() {
        return currentWeight;
    }
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }
    public double getMaxHoldWeight() {
        return maxHoldWeight;
    }
    public void setMaxHoldWeight(double maxHoldWeight) {
        this.maxHoldWeight = maxHoldWeight;
    }
    public double getInnerVolume() {
        return innerVolume;
    }
    public String getItem(int  number){
        return entitiesInContainerIds.get(number);
    }
    public void setInnerVolume(double points){
        innerVolume =points;
    }

    public Array<String> getEntitiesInContainerIds() {
        return entitiesInContainerIds;
    }

    public Array<String> getGroupsAddable() {
        return groupsAddable;
    }
    public int getNumberOfItems(){
        return entitiesInContainerIds.size;
    }
    public void emptyPack(){
        entitiesInContainerIds.removeAll(entitiesInContainerIds,false);
    }
    public double getFilledInnerVolume() {
        return filledInnerVolume;
    }
    public void setFilledInnerVolume(double filledInnerVolume) {
        this.filledInnerVolume = filledInnerVolume;
    }

    public boolean isListEntities() {
        return listEntities;
    }

    public void setListEntities(boolean listEntities) {
        this.listEntities = listEntities;
    }
}

