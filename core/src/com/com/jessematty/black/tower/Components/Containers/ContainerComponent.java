package com.jessematty.black.tower.Components.Containers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

/**
 * component for  an entity  that holds other entities
 */
public class ContainerComponent implements Component {
    /**
     * maximum volume  pack can hold <0= no volume limit
     */
    private double maxVolume;
    /**
     * current total  volume of all the item in the pack.
     */
   private double currentFilledVolume;
    /**
     * maximum weight the container can hold  <0 = no weight limit;
     */
   private  double maxHoldWeight=-1;
    /**
     *  the current total  weight of all the items the container
     */
    private double currentWeight=-1;
    /**
     * the ids of the entities in the container
     */
   private Array<String > entitiesInContainerIds = new Array<String>();

    /**
     *  the allowable groups to add  to the container
     *  if this any array is empty any item that is small enough
     *  or light enough can be added to  this  container
     */
   private  Array<String> groupsAddable= new Array<String>();
    /**
     *  whether or not to display entities  info used for UI
     */
   private boolean listEntities;
    /**
     *  if true items that  will / cannot not be added to the container (entitiesInContainerIds array)  and
     *  instead permanently disappear  from the game!
     */
   private boolean eatAllItems;

    /**
     *  if true items with groups  that don't match the groups in the groups addableArray
     *  will not be added to the container (entitiesInContainerIds array)  and instead permanently disappear
     *  from the game!
     */
    private boolean eatItemsNotInGroups;

    /**
     * if this is set to true  weight and volume will be ignored
     *  and items can  be added into the container up to
     *  the maxNumberOfItemsAllowed variable;
     */
    private boolean  addByNumberOfItems;

    /**
     * the maximum number of Items allowed in the pack
     * only used  if addByNumberOfItems=true;
     */
    private int maxNumberOfItemsAllowed;


    public ContainerComponent() {
    }
    public double getCurrentCarryVolume(){
        return currentFilledVolume;
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
    public double getMaxVolume() {
        return maxVolume;
    }
    public String getItem(int  number){
        return entitiesInContainerIds.get(number);
    }
    public void setMaxVolume(double points){
        maxVolume =points;
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
    public double getCurrentFilledVolume() {
        return currentFilledVolume;
    }
    public void setCurrentFilledVolume(double currentFilledVolume) {
        this.currentFilledVolume = currentFilledVolume;
    }
    public boolean isListEntities() {
        return listEntities;
    }
    public void setListEntities(boolean listEntities) {
        this.listEntities = listEntities;
    }

    public boolean isEatAllItems() {
        return eatAllItems;
    }

    public void setEatAllItems(boolean eatAllItems) {
        this.eatAllItems = eatAllItems;
    }

    public boolean isEatItemsNotInGroups() {
        return eatItemsNotInGroups;
    }

    public void setEatItemsNotInGroups(boolean eatItemsNotInGroups) {
        this.eatItemsNotInGroups = eatItemsNotInGroups;
    }

    public boolean isAddByNumberOfItems() {
        return addByNumberOfItems;
    }

    public void setAddByNumberOfItems(boolean addByNumberOfItems) {
        this.addByNumberOfItems = addByNumberOfItems;
    }

    public int getMaxNumberOfItemsAllowed() {
        return maxNumberOfItemsAllowed;
    }

    public void setMaxNumberOfItemsAllowed(int maxNumberOfItemsAllowed) {
        this.maxNumberOfItemsAllowed = maxNumberOfItemsAllowed;
    }
}
