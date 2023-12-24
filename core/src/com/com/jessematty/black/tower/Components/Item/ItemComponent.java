package com.jessematty.black.tower.Components.Item;

import com.badlogic.ashley.core.Component;

/**
 * component for an Item
 */
public class ItemComponent implements Component {

    /**
     * whether  or not the item  is in a container
     */
    private boolean inContainer;
    /**
     * whether  or not the item  is removable from
     * it's current container / location
     */
    private boolean removable;
    /**
     * whether or not the item  is on the ground
     */
    private boolean onGround;
    /**
     * whether or not the item  is on the ground
     */
    private boolean held;
    /**
     * the action of the item when it is held in holder
     */
    private String heldItemAction="rest";
    /**
     *  the pack slot the item is in if the item is a slotted pack
     */
    private int slot;



    public ItemComponent() {
    }

    public boolean isInContainer() {
        return inContainer;
    }
    public void setInContainer(boolean inContainer) {
        this.inContainer = inContainer;
    }





    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean isOnGround() {
        return onGround;
    }


    public boolean isHeld() {
        return held;
    }

    public void setHeld(boolean held) {
        this.held = held;
    }

    public String getHeldItemAction() {
        return heldItemAction;
    }

    public void setHeldItemAction(String heldItemAction) {
        this.heldItemAction = heldItemAction;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

}
