package com.jessematty.black.tower.Components.Animation;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Interfaces.ColorSettable;
import com.jessematty.black.tower.Components.Interfaces.SerializableComponet;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangeMode;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
public class DrawableComponent implements Component , ColorSettable , SerializableComponet{
    /**
     * the current Atlas Region to be drawn
     */
   private transient AtlasRegion currentRegion;
    /**
     *  the nme of the current region as it appears in the
     *  TextureAtlas
     */
   private String currentRegionName;
    /**
     * the name of the current libGDX TextureAtlas
     * teh Atlas region is in.
     */
   private String currentRegionAtlasName;
    /**
     * the color of the current AtlasRegion
     */
   private NamedColor color= NamedColor.WHITE;
    /**
     * the brightness of the current AtlasRegion
     */
   private float  brightness=.33f;
    /**
     * the draw offsets for the current AtlasRegion.
     */
    private Vector2 drawOffsets= new Vector2();
    /**
     * whether  or not to the draw the AtlasRegion
     */
    private boolean draw=true;
    /**
     * the  Z-layer number of the current AtlasRegion
     */
    private  float layerNumber=1;
    /**
     * the  previous layer number of the current AtlasRegion
     */
    private float previousLayerNumber;

    /**
     * the current sub  Z-layer number of the current AtlasRegion
     */
    private float subLayerNumber=1;
    private ColorChangeMode colorChangeMode=ColorChangeMode.NONE;
    private String atlasName;
    /**
     * whether or not the layer or the sub layer of
     * the drawable AtlasRegion has changed
     */
    private boolean layerChanged;
    /**
     * is the color calculated using the calculate color method in the
     * draw component
     */
    private boolean calculateColor;
    /**
     * the old sub layer number
     */
    private float previousSubLayerNumber;
    /**
     * has the sub layer number changed
     */
    private boolean subLayerChanged;
    private boolean calculateBrightness=true;
    /**
     * if true the layer number will set to
     * the y axis position of the entity
     *
     */
    private boolean setLayerToYPosition;
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

    public boolean isSetLayerToYPosition() {
        return setLayerToYPosition;
    }

    public void setSetLayerToYPosition(boolean setLayerToYPosition) {
        this.setLayerToYPosition = setLayerToYPosition;
    }


}
