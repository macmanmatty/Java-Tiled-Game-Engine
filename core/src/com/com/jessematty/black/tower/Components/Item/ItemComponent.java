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
     * the   price of the item
     * not what a shop keeper will pay
     * but is used in calculations
     */
    private float price=1;
    /**
     * the minimum  price of the item
     * not what a shop keeper will pay
     * but is used in calculations
     */
    private float minPrice=1;
    /**
     * the maximum  price of the item
     * not what a shop keeper will pay
     * but is used in calculations
     */
    private float maxPrice=100;
    /**
     * the items condition  usually calculated by external factors
     */
    private float condition;
    /**
     * whether or not the item has the ability
     * to drop when owner dies
     */
    private boolean dropOnoDie;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isDropOnoDie() {
        return dropOnoDie;
    }

    public void setDropOnoDie(boolean dropOnoDie) {
        this.dropOnoDie = dropOnoDie;
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

    public float getCondition() {
        return condition;
    }

    public void setCondition(float condition) {
        this.condition = condition;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }
}
