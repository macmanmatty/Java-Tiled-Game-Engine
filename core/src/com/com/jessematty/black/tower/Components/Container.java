package com.jessematty.black.tower.Components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;

/**
 * component for  an entity  that holds other entities
 */
public class Container implements Component {
    /**
     * maximum volume  pack can hold <0= no volume limit
     */
    private double innerVolume;
    /**
     * current total  volume of all the item in the pack.
     */
   private double filledInnerVolume; 
    /**
     * maximum weight the container can hold  <0 = no weight limit;
     */
   private  double maxHoldWeight;
    /**
     *  the current total  weight of all the items the container
     */
    private double currentWeight;
    /**
     * the ids of he entities in the container
     */
   private Array<String > entitiesInContainerIds = new Array<String>();
    /**
     *  the allowable groups to add  to the container
     */
   private  Array<String> groupsAddable= new Array<String>();
    /**
     *  whether or not to display entities  info used for UI
     */
   private boolean listEntities; 
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
