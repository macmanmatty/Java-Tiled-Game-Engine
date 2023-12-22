package com.jessematty.black.tower.Generators.Components.Animation;

import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

/**
 * DTO class for an Animation Object @see Animation.class
 */
public class AnimationDTO {
    /**
     * the name aka path of the atlas the  texture regions reside in
     */
   private  String atlasName;
    /**
     * the number of directions the animation has
     * either 1, 2, 4, or 8 @See Direction.class @See AnimationDirections.class
     */
    private int animationDirections;
    /**
     * the action corresponding to the animation
     */
    private String action;
    /**
     * the name of the animatable entity
     */
    private String bodyName;

    /**
     * the number of frames the animation has
     */
    private int frames;

    /**
     * the frame of the animation
     */
    private int frameRate;
    /**
     * the color of the animation
     */
    private NamedColor namedColor=NamedColor.WHITE;
    /**
     * whether or not to calculate the number of frames for the animation
     * based on the image sequence found in the textureAtlas
     */
   private  boolean calculateFrames;

    /**
     * the x and y offsets for the animation  from the  entities
     * x and y position  on the map
     */
    private float xOffset;

    private float yOffset;
    /**
     * the list of layer  numbers for the animation frames based on direction
     *            0 = Direction.DOWN;
     *            1 =Direction.UP;
                  2= Direction.LEFT;
                  3 =Direction.RIGHT;
                  4 = Direction.LEFTDOWN;
                  5= Direction.RIGHTDOWN;
                  6 = Direction.RIGHTUP;
                  7 = Direction.LEFTUP;
     */
    private int []  layerNumbers= new int [] {1};
    /**
     * the list of  subLayer  numbers for the animation frames based on direction
     * the directions are the same as layer numbers
     *
     */
    private int [] sublayerNumbers= new int []{1};

    public int getAnimationDirections() {
        return animationDirections;
    }

    public void setAnimationDirections(AnimationDirections animationDirections) {
        this.animationDirections = animationDirections.directions;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBodyName() {
        return bodyName;
    }

    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
    }

    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public NamedColor getNamedColor() {
        return namedColor;
    }

    public void setNamedColor(NamedColor namedColor) {
        this.namedColor = namedColor;
    }

    public boolean isCalculateFrames() {
        return calculateFrames;
    }

    public void setCalculateFrames(boolean calculateFrames) {
        this.calculateFrames = calculateFrames;
    }

    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setOffsets(float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset=yOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public int[] getLayerNumbers() {
        return layerNumbers;
    }

    public void setLayerNumbers(int[] layerNumbers) {
        this.layerNumbers = layerNumbers;
    }

    public int[] getSublayerNumbers() {
        return sublayerNumbers;
    }

    public void setSublayerNumbers(int[] sublayerNumbers) {
        this.sublayerNumbers = sublayerNumbers;
    }
}
