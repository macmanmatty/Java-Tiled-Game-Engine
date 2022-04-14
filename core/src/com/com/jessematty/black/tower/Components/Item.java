package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.ArrayList;
import java.util.List;

public class Item implements Component {

    private boolean inPack;
    private boolean removeable;
    private boolean cursed;
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





    public boolean isRemoveable() {
        return removeable;
    }

    public void setRemoveable(boolean removeable) {
        this.removeable = removeable;
    }

    public boolean isCursed() {
        return cursed;
    }

    public void setCursed(boolean cursed) {
        this.cursed = cursed;
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
