package com.jessematty.black.tower.GameBaseClasses.Camera;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeys;
import com.jessematty.black.tower.GameBaseClasses.Input.OrthographicCameraControls;
import com.jessematty.black.tower.Maps.GameMap;


// extended orthographic camera for additional game functions
public class GameCamera  extends OrthographicCamera implements InputKeys {

    private float screenMaxX;
    private float screenMaxY;
    private float screenMinY;
    private float screenMinX;
    private  PositionComponent positionComponent;
    private MovableComponent movableComponent;
    private OrthographicCameraControls orthographicCameraControls;
    private boolean controllable;
    public GameCamera() {


    }

    public GameCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
    }

    public void calculateScreenMaxes(GameMap gameMap){
        this.screenMaxX=gameMap.getXTiles()*gameMap.getTileWidth();
        this.screenMaxY=gameMap.getYTiles()*gameMap.getTileHeight();


    }

    public void moveCameraWithFollowMovable(){ // translates the camera position  to  the player the camera   is following
        // distance is how far the movable  moved

      translate(movableComponent.getDistanceMoved());
        cameraBoundsCheck();
    }
    public void cameraBoundsCheck(){
        float cameraHalfWidth=viewportWidth*.5f;
        float cameraHalfHeight=viewportHeight*.5f;
        float cameraLeft = position.x - cameraHalfWidth;
        float cameraRight = position.x + cameraHalfWidth;
        float cameraBottom = position.y - cameraHalfHeight;
        float cameraTop = position.y + cameraHalfHeight;

        if(cameraLeft <= screenMinX)
        {
            position.x =screenMinX+cameraHalfWidth;
        }
        else if(cameraRight >= screenMaxX)
        {
            position.x = screenMaxX- cameraHalfWidth;
        }
        else{
            centerCameraToPositionWidth(positionComponent);
        }
// Vertical axis
        if(cameraTop >= screenMaxY)
        {
            position.y = screenMaxY - cameraHalfHeight;
        }
        else if(cameraBottom <=screenMinY)
        {
            position.y = screenMinY+cameraHalfHeight;
        }
        else{
            centerCameraToPositionHeight(positionComponent);
        }
    }
    private void centerCameraToPositionWidth(PositionComponent positionComponent){
        float positionX= positionComponent.getLocationX();
        float cameraHalfWidth=viewportWidth*.5f;
        position.x=positionX;
        if(positionX<cameraHalfWidth+screenMinX ){
            position.x = screenMinX+cameraHalfWidth;
        }
        else if(positionX>screenMaxX-cameraHalfWidth){
            position.x=screenMaxX-cameraHalfWidth;
        }
    }


    private void centerCameraToPositionHeight(PositionComponent positionComponent){ // same as below but only  for height
        float positionY= positionComponent.getLocationY();
        float cameraHalfHeight=viewportHeight*.5f;
        position.y=positionY;
        if(positionY<cameraHalfHeight+screenMinY ){
            position.y = screenMinY+cameraHalfHeight;
        }
        else if(positionY>screenMaxY-cameraHalfHeight){
            position.y=screenMaxY-cameraHalfHeight;
        }
    }

    public void setEntityToFollow(Entity entity){

       PositionComponent positionComponent= GameComponentMapper.getPositionComponentMapper().get(entity);
        MovableComponent movableComponent= GameComponentMapper.getMovableComponentMapper().get(entity);
        if(movableComponent==null || positionComponent==null){
            return;
        }

        this.positionComponent=positionComponent;
        this.movableComponent=movableComponent;

    }
    public void unFollowEntity(){
        this.positionComponent=null;
        this.movableComponent=null;


    }

    public float getScreenMaxX() {
        return screenMaxX;
    }

    public void setScreenMaxX(float screenMaxX) {
        this.screenMaxX = screenMaxX;
    }

    public float getScreenMaxY() {
        return screenMaxY;
    }

    public void setScreenMaxY(float screenMaxY) {
        this.screenMaxY = screenMaxY;
    }

    public float getScreenMinY() {
        return screenMinY;
    }

    public void setScreenMinY(float screenMinY) {
        this.screenMinY = screenMinY;
    }

    public float getScreenMinX() {
        return screenMinX;
    }

    public void setScreenMinX(float screenMinX) {
        this.screenMinX = screenMinX;
    }

    public void update() {
        super.update();
        if(movableComponent!=null && positionComponent!=null && movableComponent.isMoved()){
            moveCameraWithFollowMovable();
    }


    }

    public void centerCameraToPosition(PositionComponent positionComponent){
        centerCameraToPositionHeight(positionComponent);
        centerCameraToPositionWidth(positionComponent);

    }

    public void enableControlledMovement(){
        if(orthographicCameraControls==null){
            orthographicCameraControls= new OrthographicCameraControls(this);
            GameAssets.getGameInput().getKeyListener().addInputKeys(orthographicCameraControls.getCameraControlKeyCombos());
        }
        orthographicCameraControls.enableControlledMovement();
        controllable=true;
    }
    public void disableControlledMovement(){
        orthographicCameraControls.disableControlledMovement();
        controllable=false;
    }

    @Override
    public Array<InputKeyCombo> getKeys() {
         return  orthographicCameraControls.getCameraControlKeyCombos();
    }

    public boolean isControllable() {
        return controllable;
    }
}
