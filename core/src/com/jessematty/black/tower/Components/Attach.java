package com.jessematty.black.tower.Components;

import com.badlogic.gdx.utils.Array;

public class Attach {
    private int maxAttachedEntities;
    private Array<String> attachedEntities= new Array<>();

    public int getMaxAttachedEntities() {
        return maxAttachedEntities;
    }

    public void setMaxAttachedEntities(int maxAttachedEntities) {
        this.maxAttachedEntities = maxAttachedEntities;
    }
    public Array<String> getAttachedEntities() {
        return attachedEntities;
    }
}
