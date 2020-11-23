package com.jessematty.black.tower.GameBaseClasses.BitMask.Tiles;

import com.badlogic.gdx.graphics.Color;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;

public class NumberedTile {
    private int bitNumber;
    private String regionName;
    private String atlasName;
    private Color color=Color.WHITE;
    private AtlasNamedAtlasRegion atlasRegion;

    public NumberedTile() {
    }

    public NumberedTile(int bitNumber, String regionName, String atlasName) {
        this.bitNumber = bitNumber;
        this.regionName = regionName;
        this.atlasName = atlasName;
    }

    public NumberedTile(int bitNumber, String regionName, String atlasName, Color color) {
        this.bitNumber = bitNumber;
        this.regionName = regionName;
        this.atlasName = atlasName;
        this.color = color;
    }

    public int getBitNumber() {
        return bitNumber;
    }

    public void setBitNumber(int bitNumber) {
        this.bitNumber = bitNumber;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }

    public AtlasNamedAtlasRegion getAtlasRegion() {
        return atlasRegion;
    }

    public void setAtlasRegion(AtlasNamedAtlasRegion atlasRegion) {
        this.atlasRegion = atlasRegion;
    }
}
