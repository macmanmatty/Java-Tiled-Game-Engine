package com.jessematty.black.tower.Components.Animation;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.Components.Interfaces.SerializableComponet;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;

/**
 * component for animations
 */
public class AnimatableComponent implements SerializableComponet{
    /**
     *     ObjectMap of directions  that links to ObjectMap of actions that links
     *     to the animations;
     */
    protected    ObjectMap<String, ObjectMap<String, Animation >> animations = new ObjectMap<String, ObjectMap<String, Animation>>();
    /**
     * does this animation have eight directions
     * if true the map of animations will initialized with
     * sub maps to actions for all eight directions;
     */
    protected boolean eightDirections=true;
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
    protected Direction currentDirection=Direction.UP; // the current animation direction;
    protected boolean singleImage;
    /**
     * internal counter for the frame rate
     */
    protected  int frameRateCounter=0;
    /**
     * the frame rate of the animation for the given
     * direction and action
     */
    protected  int currrentFrameRate=1;
    /**
     *  the current state of the animation
     */
    protected boolean finishedAnimating;
    /**
     * the  old  sub-layer number of the animation
     */
    protected  int previousLayerNumber;

    /**
     * the  current  sub-layer number of the animation
     */
    protected  int currentLayerNumber;

    /**
     * whether  or not the layer number has changed
     * if true this will cause a resort of the render  system
     */
    protected boolean layerChanged;
    protected boolean newlyCreated =true;
    /**
     * the default action for getting default frame rates
     */
    protected String defaultAction="rest";
    private AtlasNamedAtlasRegion staticTexture;
    public AnimatableComponent() {
        this(true);
    }
    public AnimatableComponent(boolean eightDirections) {
        this.eightDirections=eightDirections;
        animations.put(Direction.DOWN.toString(), new ObjectMap<String, Animation>());
        animations.put(Direction.UP.toString(), new ObjectMap<String, Animation>());
        animations.put(Direction.LEFT.toString(), new ObjectMap<String, Animation>());
        animations.put(Direction.RIGHT.toString(), new ObjectMap<String, Animation>());
        if(eightDirections){
            animations.put(Direction.RIGHTUP.toString(), new ObjectMap<String, Animation>());
            animations.put(Direction.LEFTUP.toString(), new ObjectMap<String, Animation>());
            animations.put(Direction.LEFTDOWN.toString(), new ObjectMap<String, Animation>());
            animations.put(Direction.RIGHTDOWN.toString(), new ObjectMap<String, Animation>());
        }
    }

    /**
     * get an Animation object based on a direction and action
     * @param direction the direction of the animation
     * @param action the action of the animation
     * @return
     */
    public Animation getAnimation(Direction direction, String action) {
        if(eightDirections=false){
            direction=Direction.getBaseDirection(direction);
        }
        return animations.get(direction.toString()).get(action);
    }

    /**
     * returns the number of frames a given animation has  for given
     * action and direction
     * @param action
     * @param direction
     * @return
     */
    public int getFrames(  String action, Direction direction) {
        if(eightDirections=false){
            direction=Direction.getBaseDirection(direction);
        }
        Animation animation=animations.get(direction.toString()).get(action);
        if(animation!=null) {
            return animation.getFrames().length;
        }
        else{
            return animations.get(currentDirection.toString()).get(defaultAction).getFrames().length;
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
        if(eightDirections=false){
            direction=Direction.getBaseDirection(direction);
        }
        Animation animation=animations.get(direction.toString()).get(action);
        if(animation!=null) {
            return animation.getFrameRate();
        }
        else{
            return animations.get(currentDirection.toString()).get(defaultAction).getFrameRate();
        }   }

    /**
     add an animation to the current animation map
     or overwrites it  with the new data if it exits
     */
    public void addAnimation(Animation animation){
        Direction direction=animation.getDirection();
        String action=animation.getAction();
        animations.get(direction.toString()).put(action,animation);
    }
    public void addAnimation(AtlasNamedAtlasRegion [] regions, Direction direction, String action, int frameRate, Vector2 offsets, int layerNumber){
            Animation animation = new Animation();
            animation.setFrames(regions);
            animation.setDirection(direction);
            animation.setAction(action);
            animation.setFrameRate(frameRate);
            animation.setOffsets(offsets);
            animation.setLayerNumber(layerNumber);
            animations.get(direction.toString()).put(action, animation);
    }
    public boolean isEightDirections() {
        return eightDirections;
    }
    public void  addFrames(String action, Direction direction, AtlasNamedAtlasRegion[] frames) {
        animations.get(direction.toString()).get(action).setFrames(frames);
    }
    public int getCurrentFrameNumber() {
        return currentFrameNumber;
    }
    public NamedColor getCurrentColor(){
        Animation animation=animations.get(currentDirection.toString()).get(currentAction);
        if(animation!=null) {
            return animation.getColor();
        }
        else{
            return animations.get(currentDirection.toString()).get(defaultAction).getColor();
        }    }
    public String getCurrentAction() {
        return currentAction;
    }
    public void setCurrentAction(String currentAction) {
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
        if(singleImage==true) {
            return staticTexture;
        }
        Animation animation=animations.get(currentDirection.toString()).get(currentAction);
        if(animation!=null) {
            return animation.getFrames()[currentFrameNumber];
        }
        else{
            return animations.get(currentDirection.toString()).get(defaultAction).getFrames()[0];
        }
    }

    /**
     * sets the next frame of the animation
     * based on the frame rate and rate counter
     */
    public void nextFrame(){
        finishedAnimating =false;
        currrentFrameRate= getFrameRate(currentDirection, currentAction);
        if(frameRateCounter%currrentFrameRate==0 && singleImage==false) {
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
        Animation animation=animations.get(currentDirection.toString()).get(currentAction);
        if(animation!=null) {
            return animation.getFrames().length;
        }
        else{
            return animations.get(currentDirection.toString()).get(defaultAction).getFrames().length;
        }    }
    /**
     * returns the current number of frame rate
     * for the current animation
     * @return
     */
    public   int getCurrentFrameRate(){
        Animation animation=animations.get(currentDirection.toString()).get(currentAction);
        if(animation!=null) {
            return animation.getFrameRate();
        }
        else{
            return animations.get(currentDirection.toString()).get(defaultAction).getFrameRate();
        }    }
    /**
     * returns the current sub layer number
     * for the current animation
     * @return
     */
    public   int getCurrentLayerNumber(){
     Animation animation=animations.get(currentDirection.toString()).get(currentAction);
     if(!currentAction.equals("move") && !currentAction.equals("rest")){
         System.out.println(currentAction);
     }
     if(animation!=null) {
     currentLayerNumber=animation.getLayerNumber();
     }
        if(previousLayerNumber!=currentLayerNumber){
            layerChanged=true;
        }
        else{
            layerChanged=false;
        }
        return currentLayerNumber;
    }
    public Vector2 getCurrentDrawOffsets(){
        Animation animation=animations.get(currentDirection.toString()).get(currentAction);
        if(animation!=null) {
            return animation.getOffsets();
        }
        else{
            return animations.get(currentDirection.toString()).get(defaultAction).getOffsets();
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
            return animations.get(currentDirection.toString()).get(currentAction).getBrightness();
    }
  
    public boolean isLayerChanged() {
        return layerChanged;
    }
    public boolean isNewlyCreated() {
        return newlyCreated;
    }
    public void onCreate(){
        newlyCreated =false;
        nextFrame();
    }
    @Override
    public void deSerialize(GameAssets assets) {
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
    public void serialize() {
    }
    public String getDefaultAction() {
        return defaultAction;
    }
    public void setDefaultAction(String defaultAction) {
        this.defaultAction = defaultAction;
    }
    public void setAnimations(ObjectMap<String, ObjectMap<String, Animation>> animations) {
        this.animations = animations;
    }
    public void setEightDirections(boolean eightDirections) {
        this.eightDirections = eightDirections;
    }
    public void setCurrentFrameNumber(int currentFrameNumber) {
        this.currentFrameNumber = currentFrameNumber;
    }
    public void setFrameRateCounter(int frameRateCounter) {
        this.frameRateCounter = frameRateCounter;
    }
    public void setCurrrentFrameRate(int currrentFrameRate) {
        this.currrentFrameRate = currrentFrameRate;
    }
    public void setPreviousLayerNumber(int previousLayerNumber) {
        this.previousLayerNumber = previousLayerNumber;
    }
    public void setCurrentLayerNumber(int currentLayerNumber) {
        this.currentLayerNumber = currentLayerNumber;
    }
    public void setLayerChanged(boolean layerChanged) {
        this.layerChanged = layerChanged;
    }
    public void setNewlyCreated(boolean newlyCreated) {
        this.newlyCreated = newlyCreated;
    }

    public boolean isFinishedAnimating() {
        return finishedAnimating;
    }


}
