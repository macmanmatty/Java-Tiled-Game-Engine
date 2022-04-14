package com.jessematty.black.tower.Components.Animation;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.SerializableComponet;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangeMode;
import com.jessematty.black.tower.Components.ColorSettable;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

public class DrawableComponent implements Component , ColorSettable , SerializableComponet{

   private transient AtlasRegion currentRegion;
   private String currentRegionName;
   private String currentRegionAtlasName;
   private NamedColor color= NamedColor.WHITE;
   private float  brightness=.33f;
    private Vector2 drawOffsets= new Vector2();
    private boolean draw=true;
    private  float layerNumber;
    private float previousLayerNumber;
    private float subLayerNumber;
    private ColorChangeMode colorChangeMode=ColorChangeMode.NONE;
    private String atlasName;
    private boolean layerChanged;
    private boolean calculateColor;
    private float previousSubLayerNumber;
    private boolean subLayerChanged;
    private boolean calculateBrightness=true;


    public DrawableComponent(String atlasName) {
        this.atlasName = atlasName;
    }

    public DrawableComponent() {
    }

    public AtlasRegion getTextureRegion() {
        return currentRegion;
    }

    public void setCurrentRegion(AtlasNamedAtlasRegion currentRegion) {
        this.currentRegion = currentRegion;
        this.currentRegionName=currentRegion.name;
        this.currentRegionAtlasName=currentRegion.getAtlasName();

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

    public boolean isCalculateBrightness() {
        return calculateBrightness;
    }

    public void setCalculateBrightness(boolean calculateBrightness) {
        this.calculateBrightness = calculateBrightness;
    }

    @Override
    public void deSerialize(GameAssets assets) {
        currentRegion=assets.getAtlasRegionByName(currentRegionName, currentRegionAtlasName);


    }

    @Override
    public void serialize() {

    }
}
