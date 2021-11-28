package com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

import java.util.Arrays;
import java.util.Objects;


/**
 * class for saving a  libGDX tiled map cell
 */
public class CellSaver {
    boolean flipHorizontal;
    boolean flipVertical;
    String [] regionNames; // the names of the texture regions if the cell isn't animated  there is only one region
    boolean animated;
    boolean colored;
    float interval;
    float brightness;
    NamedColor color;
    Class <? extends TiledMapTile> tileClass; // the class to desearlize into
    int id;
    public CellSaver() {
    }



    public boolean isFlipVertical() {
        return flipVertical;
    }

    public String[] getRegionNames() {
        return regionNames;
    }

    public Class<? extends TiledMapTile> getTileClass() {
        return tileClass;
    }

    public boolean isAnimated() {
        return animated;
    }

    public float getInterval() {
        return interval;
    }

    public void setFlipHorizontal(boolean flipHorizontal) {
        this.flipHorizontal = flipHorizontal;
    }

    public void setFlipVertical(boolean flipVertical) {
        this.flipVertical = flipVertical;
    }

    public void setRegionNames(String[] regionNames) {
        this.regionNames = regionNames;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }

    public void setTileClass(Class<? extends TiledMapTile> tileClass) {
        this.tileClass = tileClass;
    }

    public boolean isColored() {
        return colored;
    }


    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public NamedColor getColor() {
        return color;
    }

    public void setColor(NamedColor color) {
        if (color.r!=1 && color.g!=1 && color.b!=1 && color.a!=1) {
            this.colored = true;
            this.color = color;

        }
        else{

            this.color=NamedColor.WHITE;
        }

    }

    public boolean isFlipHorizontal() {
        return flipHorizontal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CellSaver)) return false;
        CellSaver cellSaver = (CellSaver) o;
        return flipHorizontal == cellSaver.flipHorizontal &&
                flipVertical == cellSaver.flipVertical &&
                animated == cellSaver.animated &&
                colored == cellSaver.colored &&
                Float.compare(cellSaver.interval, interval) == 0 &&
                Float.compare(cellSaver.brightness, brightness) == 0 &&
                Arrays.equals(regionNames, cellSaver.regionNames) &&color.equals( cellSaver.color) &&
                tileClass.equals(cellSaver.tileClass);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(flipHorizontal, flipVertical, animated, colored, interval, brightness, color, tileClass);
        result = 31 * result + Arrays.hashCode(regionNames);
        return result;
    }
}
