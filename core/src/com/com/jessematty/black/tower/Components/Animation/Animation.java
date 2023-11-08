package com.jessematty.black.tower.Components.Animation;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Interfaces.ColorSettable;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

/**
 * the animation class
 */
public class Animation implements ColorSettable {
    /**
     * the atlas regions that make up the the animation
     */
  private  transient   AtlasNamedAtlasRegion [] frames= new AtlasNamedAtlasRegion[0];
    /**
     *  the names of the animations in the texture atlas
     *  used for serialization and  deserialization
     *
     */
  private String [] atlasRegionNames = new String [0];

    /**
     * the name of the atlas that holds the texture regions
     */
  private String atlasName;

    /**
     * the action the animation corresponds to
     */
  
   private  String action="";
    /**
     * the sub layer number of the animation
     */
    private int layerNumber;

    /**
     * the direction of the animation
     */
    private Direction direction;
    /**
     * the color of the animation
     */
    private NamedColor color= NamedColor.WHITE;

    /**
     * the openGL brightness of the animation
     */
    private float brightness;

    /**
     * the frame rate of the animation
     */
    private int frameRate;

    /**
     * the offsets of the animation
     */
    private Vector2 offsets= new Vector2();


    public AtlasNamedAtlasRegion[] getFrames() {
        return frames;
    }

    /**
     * sets the frames of the animation
     * and the names of the atlas regions
     * used to create the animation.
     *
     * @param frames the AtlasNamedAtlasRegions that make up the animation
     */
    public void setFrames(AtlasNamedAtlasRegion[] frames) {
        this.frames = frames;
        int size=frames.length;
        atlasRegionNames = new String[size];
        atlasName=frames[0].getAtlasName();
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
                frames = new AtlasNamedAtlasRegion[size];
                for (int count = 0; count < size; count++) {
                    String name = atlasRegionNames[count];
                    frames [count] = assets.getAtlasRegionByName(name, atlasName);
                }
            }
        
    
}
