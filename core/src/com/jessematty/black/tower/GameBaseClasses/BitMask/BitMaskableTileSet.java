package com.jessematty.black.tower.GameBaseClasses.BitMask;

import com.badlogic.gdx.utils.ObjectMap;

public class BitMaskableTileSet {
    private String name;
    private ObjectMap<Integer, String > tileNumbers = new ObjectMap<>();


    public BitMaskableTileSet(String name) {
        this.name = name;
    }


    public ObjectMap<Integer, String> getTileNumbers() {
        return tileNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
