package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;

import java.util.ArrayList;
import java.util.List;

public class Item implements Component {

    private boolean inPack;
    private boolean removeable;
    private boolean cursed;
    private boolean onGround;
    private float price;
    private boolean dropOnoDie;

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
}
