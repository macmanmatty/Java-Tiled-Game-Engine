package com.jessematty.black.tower.Components.Containers;

import com.badlogic.gdx.utils.Array;

public class PackSlot {
    /** the max number of items the slot can hold
     * <0 = infinite  items
     */
  private   int maxItems;
  private   int slotNumber;
  private Array<String> entityIds = new Array<>();

    /**
     * the groups the slot can hold
     */
    private Array<String> groups= new Array<>();

    public int getMaxItems() {
        return maxItems;
    }

    public Array<String> getGroups() {
        return groups;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Array<String> getEntityIds() {
        return entityIds;
    }

}
