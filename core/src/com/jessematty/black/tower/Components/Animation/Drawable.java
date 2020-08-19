package com.jessematty.black.tower.Components.Animation;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangeMode;
import com.jessematty.black.tower.Components.ColorSettable;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

public class Drawable implements Component , ColorSettable {

   private AtlasRegion currentRegion;
   private NamedColor color= NamedColor.WHITE;
   private float  brightness=1;
    private Vector2 drawOffsets= new Vector2();
    private boolean draw=true;
    private  float layerNumber;
    private float previousLayerNumber;
    private float subLayerNumber;
    private ColorChangeMode colorChangeMode=ColorChangeMode.NONE;
    private String currentRegionName;
    private String atlasName;
    private boolean layerChanged;
    private boolean calculateColor;
    private float previousSubLayerNumber;
    private boolean subLayerChanged;

    public Drawable(String atlasName) {
        this.atlasName = atlasName;
    }

    public Drawable() {
    }

    public AtlasRegion getTextureRegion() {
        return currentRegion;
    }

    public void setCurrentRegion(AtlasRegion currentRegion) {
        this.currentRegion = currentRegion;
        this.currentRegionName=currentRegion.name;
    }

    public NamedColor getColor() {
        return color;
    }

    public void setColor(NamedColor color) {
        this.color = color;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public Vector2 getDrawOffsets() {
        return drawOffsets;
    }

    public void setDrawOffsets(Vector2 drawOffsets) {
        this.drawOffsets = drawOffsets;
    }



    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public float getLayerNumber() {
        return layerNumber;
    }

    public void setLayerNumber(float layerNumber) {
        if(layerNumber!=previousLayerNumber){
            previousLayerNumber=this.layerNumber;
            layerChanged=true;
            this.layerNumber = layerNumber;


        }

        else{
            layerChanged=false;
        }
    }


    public float getSubLayerNumber() {
        return subLayerNumber;
    }

    public void setSubLayerNumber(float subLayerNumber) {
        if(subLayerNumber!=previousSubLayerNumber){
            previousSubLayerNumber=this.subLayerNumber;
            subLayerChanged =true;
            this.subLayerNumber = subLayerNumber;


        }

        else{
            subLayerChanged =false;
        }
    }

    public ColorChangeMode getColorChangeMode() {
        return colorChangeMode;
    }

    public void setColorChangeMode(ColorChangeMode colorChangeMode) {
        this.colorChangeMode = colorChangeMode;
    }

    public String getAtlasName() {
        return atlasName;
    }


    public String getCurrentRegionName() {
        return currentRegionName;
    }

    public boolean isLayerChanged() {
        return layerChanged;
    }


    public boolean isCalculateColor() {
        return calculateColor;
    }

    public void setCalculateColor(boolean calculateColor) {
        this.calculateColor = calculateColor;
    }

    public boolean isSubLayerChanged() {
        return subLayerChanged;
    }


}
