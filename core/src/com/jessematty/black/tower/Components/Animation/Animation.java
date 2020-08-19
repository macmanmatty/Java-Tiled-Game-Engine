package com.jessematty.black.tower.Components.Animation;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.ColorSettable;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
public class Animation implements ColorSettable {
  private  transient   AtlasNamedAtlasRegion [] frames= new AtlasNamedAtlasRegion[0];
  private String [] atlasRegionNames = new String [0];
  
   private  String action="";
    private int layerNumber;
    private Direction direction;
    private NamedColor color= NamedColor.WHITE;
    private float brightness;
    private int frameRate;
    private Vector2 offsets= new Vector2();
    public AtlasNamedAtlasRegion[] getFrames() {
        return frames;
    }
    public void setFrames(AtlasNamedAtlasRegion[] frames) {
        this.frames = frames;
        int size=frames.length;
        atlasRegionNames = new String[size];
        for(int count=0; count<size; count++){
            atlasRegionNames[count]=frames[count].name;
            
        }
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public int getLayerNumber() {
        return layerNumber;
    }
    public void setLayerNumber(int layerNumber) {
        this.layerNumber = layerNumber;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
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
    public int getFrameRate() {
        return frameRate;
    }
    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }
    public Vector2 getOffsets() {
        return offsets;
    }
    public void setOffsets(Vector2 offsets) {
        this.offsets = offsets;
    }
    public void setXOffset(float x) {
        offsets.x=x;
    }
    public void setYOffset(float y) {
        offsets.y=y;
    }
    public String[] getAtlasRegionNames() {
        return atlasRegionNames;
    }
    public void deSerialize(GameAssets assets){
        
        int size= atlasRegionNames.length;
                AtlasNamedAtlasRegion[] regions = new AtlasNamedAtlasRegion[size];
                for (int count = 0; count < size; count++) {
                    String name = atlasRegionNames[count];
                    regions[count] = assets.getAtlasRegionByName(name, "assetts.atlas");
                }
            }
        
    
}
