package com.jessematty.black.tower.Components.Item;

import com.badlogic.ashley.core.Component;

public class ItemComponent implements Component {

    private boolean inPack;
    private boolean removable;
    private boolean onGround;
    private float price;
    private boolean dropOnoDie;
    private boolean held;
    private String heldItemAction="rest";

    public boolean isInPack() {
        return inPack;
    }
    public void setInPack(boolean inPack) {
        this.inPack = inPack;
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
}
