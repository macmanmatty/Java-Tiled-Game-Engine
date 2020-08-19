package com.jessematty.black.tower.GameBaseClasses.BitMask;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.Set;

public class BitMaskableTileSet {
    private String name;

    public BitMaskableTileSet(String name) {
        this.name = name;
    }

    private ObjectMap<Integer, String >  tiles= new ObjectMap<>();

    public ObjectMap<Integer, String> getTiles() {
        return tiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
