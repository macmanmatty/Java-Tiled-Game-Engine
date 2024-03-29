package com.jessematty.black.tower.Components.Animation;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.Components.Interfaces.SerializableComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.Utilities.AnimationUtilities;

/**
 * component for animations
 */
public class AnimatableComponent implements SerializableComponent {
    /**
     *     ObjectMap of actions  that links to ObjectMap of directions that links
     *     to the animations;
     *     map 1
     *     key = action as string
     *     value= object map of direction and animations
     *     map 2
     *     key= the toString() value of a Direction enum
     *     value= the Animation object @see Animation.class
     */
  protected  ObjectMap<String, ObjectMap<String, Animation >> animations = new ObjectMap<String, ObjectMap<String, Animation>>();
    /**
     *  the number of the current animation the frame
     */
    protected int currentFrameNumber; // the current animation frame number
    /**
     * String representing the current action of the entity
     */
    protected String currentAction="rest"; // the current animation action
    /**
     * the current direction of the animation
     */
    protected Direction currentDirection=Direction.DOWN; // the current animation direction;
    protected boolean singleImage;
    /**
     * internal counter for the frame rate
     */
    protected  int frameRateCounter=0;
    /**
     * the frame rate of the animation for the given
     * direction and action
     */
    protected  int currentFrameRate =1;
    /**
     *  the current state of the animation
     */
    protected boolean finishedAnimating;
    /**
     * the  old  sub-layer number of the animation
     */
    protected  int previousSublayerNumber;
    /**
     * the  old  layer number of the animation
     */
    protected  int previousLayerNumber;

    /**
     * the  current  sub-layer number of the animation
     */
    protected  int currentSublayerNumber;

    /**
     * the  current  layer number of the animation
     */
    protected  int currentLayerNumber;

    protected Animation currentAnimation;



    /**
     * whether  or not the layer number has changed
     * if true this will cause a resort of the render  system
     */
    protected boolean layerChanged;
    private AtlasNamedAtlasRegion staticTexture;
    public AnimatableComponent() {
    }
    /**
     * get an Animation object based on a direction and action
     * if only four directions exist will use the
     * base direction animation for the animation frames
     * if only a single animation exists for given action
     * and direction mapping that single animation will be used
     * @param direction the direction of the animation
     * @param action the action of the animation
     * @return the Animation Object
     */
    public Animation getAnimation(Direction direction, String action) {
        ObjectMap<String , Animation> animationObjectMap=animations.get(action);
        if(animationObjectMap==null){
            currentAnimation=null;
            return null;
        }
        if(animationObjectMap.size==1){
           return animationObjectMap.values().next();
        }
        else if(animationObjectMap.size>4){
            direction=Direction.getBaseDirection(direction);
        }
         Animation animation=animationObjectMap.get(direction.toString());
        currentAnimation=animation;
        return currentAnimation;
    }
    /**
     * returns the number of frames a given animation has  for given
     * action and direction
     * @param action
     * @param direction
     * @return
     */
    public int getFrames(  String action, Direction direction) {
        Animation animation=getAnimation(direction, action);
        if(animation!=null) {
            return animation.getFrames().length;
        }
        else{
            return 1;
        }
    }
    /**
     * returns the frame rate a given animation has  for given
     * action and direction
     * @param action
     * @param direction
     * @return
     */
    public int getFrameRate(Direction direction, String action) {
        Animation animation=getAnimation(direction, action);
        if(animation!=null) {
            return animation.getFrameRate();
        }
        else{
            return 1;
        }   }

    /**
     add an animation to the current animation map
     or overwrites it  with the new data if it exits
     */
    public void addAnimation(Animation animation){
        Direction direction=animation.getDirection();
        String action=animation.getAction();
       ObjectMap<String, Animation>  animationObjectMap;
       animationObjectMap=animations.get(action);
       if(animationObjectMap==null) {
           animationObjectMap= new ObjectMap<>();
           animations.put(action, animationObjectMap);
       }
        animations.get(action).put(direction.toString(),animation);
    }
    public void addAnimation(AtlasNamedAtlasRegion [] regions, Direction direction, String action, int frameRate, Vector2 offsets, int subLayerNumber, int  layerNumber){
      Animation animation=  AnimationUtilities.createAnimation(regions, direction, action, frameRate, offsets, subLayerNumber, layerNumber);
      addAnimation(animation);
    }
    public int getCurrentFrameNumber() {
        return currentFrameNumber;
    }
    public NamedColor getCurrentColor(){
        Animation animation=animations.get(currentAction).get(currentDirection.toString());
        if(animation!=null) {
            return animation.getColor();
        }
        else{
            return NamedColor.WHITE;
        }    }
    public String getCurrentAction() {
        return currentAction;
    }
    public void changeAction(String currentAction) {
        this.currentAction = currentAction;
        currentFrameNumber=0;
    }
    public Direction getCurrentDirection() {
        return currentDirection;
    }
    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
   public  AtlasNamedAtlasRegion getCurrentTexture(){
        if(singleImage) {
            return staticTexture;
        }
       Animation animation=getAnimation(currentDirection, currentAction);
        if(animation!=null) {
            return animation.getFrames()[currentFrameNumber];
        }
        else{
            return null;
        }
    }

    /**
     * sets the next frame of the animation
     * based on the frame rate and rate counter
     */
    public void nextFrame(){
        finishedAnimating =false;
        currentFrameRate = getFrameRate(currentDirection, currentAction);
        if(frameRateCounter% currentFrameRate ==0 && singleImage==false) {
            currentFrameNumber++;
            if (currentFrameNumber >= getCurrentNumberOfFrames()) {
                currentFrameNumber = 0;
                finishedAnimating =true;
            }
        }
        frameRateCounter++;
    }

    /**
     * returns the current number of frames for the current animation
     * @return
     */
    public   int getCurrentNumberOfFrames(){
        Animation animation=getAnimation(currentDirection, currentAction);
        if(animation!=null) {
            return animation.getFrames().length;
        }
        else{
            return 1;
        }    }
    /**
     * returns the current number of frame rate
     * for the current animation
     * @return
     */
    public   int getCurrentFrameRate(){
        Animation animation=getAnimation(currentDirection, currentAction);
        if(animation!=null) {
            return animation.getFrameRate();
        }
        else{
            return 1;
        }    }
    /**
     * returns the current sub layer number
     * for the current animation
     * @return
     */
    public   int getCurrentSublayerNumber(){
    Animation animation=getAnimation(currentDirection, currentAction);
     if(animation!=null) {
     currentSublayerNumber =animation.getSubLayerNumber();
     }
        if(previousSublayerNumber != currentSublayerNumber){
            layerChanged=true;
        }
        else{
            layerChanged=false;
        }
        return currentSublayerNumber;
    }
    public   int getCurrentLayerNumber(){
        Animation animation=getAnimation(currentDirection, currentAction);
        if(animation!=null) {
            currentLayerNumber =animation.getLayerNumber();
        }
        if(previousLayerNumber != currentLayerNumber){
            layerChanged=true;
        }
        else{
            layerChanged=false;
        }
        return currentLayerNumber;
    }
    public Vector2 getCurrentDrawOffsets(){
        Animation animation=getAnimation(currentDirection, currentAction);
        if(animation!=null) {
            return animation.getOffsets();
        }
        else{
            return new Vector2();
        }
    }
    public AtlasNamedAtlasRegion getStaticTexture() {
        return staticTexture;
    }
    public void setStaticTexture(AtlasNamedAtlasRegion staticTexture) {
        this.staticTexture = staticTexture;
    }
    public boolean isSingleImage() {
        return singleImage;
    }
    public void setSingleImage(boolean singleImage) {
        this.singleImage = singleImage;
    }
    public Float getCurrentBrightness() {
            return animations.get(currentAction).get(currentDirection.toString()).getBrightness();
    }
  
    public boolean isLayerChanged() {
        return layerChanged;
    }

    /**
     * method for deserializing  a Animatable component using
     * the GameAssets Class to retrieve the texture regions for the animation frames
     * @param assets
     */
    @Override
    public void deSerialize(Entity unused, GameAssets assets) {
        Keys<String> keys=animations.keys();
        while(keys.hasNext==true ) {
            String key=keys.next();
            ObjectMap<String,  Animation> names=animations.get(key);
            Keys<String> keys2=names.keys();
            while( keys2.hasNext==true ) {
                String key2=keys2.next();
                Animation animation=names.get(key2);
                animation.deSerialize(assets);
                
            }
            }
    }
    public ObjectMap<String, ObjectMap<String, Animation>> getAnimations() {
        return animations;
    }
    @Override
    public void serialize(Entity unused) {
    }
    public void setAnimations(ObjectMap<String, ObjectMap<String, Animation>> animations) {
        this.animations = animations;
    }

    public void setCurrentFrameNumber(int currentFrameNumber) {
        this.currentFrameNumber = currentFrameNumber;
    }
    public void setFrameRateCounter(int frameRateCounter) {
        this.frameRateCounter = frameRateCounter;
    }
    public void setCurrentFrameRate(int currentFrameRate) {
        this.currentFrameRate = currentFrameRate;
    }
    public void setCurrentsubLayerNumber(int currentsubLayerNumber) {
        this.currentSublayerNumber = currentsubLayerNumber;
    }
    public boolean isFinishedAnimating() {
        return finishedAnimating;
    }




}
