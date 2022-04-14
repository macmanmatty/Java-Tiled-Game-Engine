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
import com.jessematty.black.tower.Maps.GameMap;

/** extended orthographic camera for additional game functions
 *
 */
public class GameCamera  extends OrthographicCamera implements InputKeys {
    /**
     * the maximum and minimum values  for the games  world
     */
    private float screenMaxX;
    private float screenMaxY;
    private float screenMinY;
    private float screenMinX;
    /**
     * the entity to follow's position component needed to have the camera follow the player
     */
    private  PositionComponent positionComponent;
    /**
     *  the entity to follow's movable component  needed to have the camera follow the player
     */
    private MovableComponent movableComponent;
    /**
     * the key controls for the camera
     */
    private OrthographicCameraControls orthographicCameraControls;
    /**
     * whether or not you can move the camera with key input
     */
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
    /**
     * translates the camera position  to  the player the camera   is following
     *   distance is how far the movable  moved
     */
    public void moveCameraWithFollowMovable(){ 
      translate(movableComponent.getDistanceMoved());
        cameraBoundsCheck();
    }

    /**
     * checks to make sure the camera doesn't  go out of bounds
     * when following the player so only the map
     * is visible and not empty screen space
     */
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

    /**
     * centers the cameras horizontal axis to the entity the
     * camera is following  position  component
     * @param positionComponent the players position component
     */
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
    /**
     * centers the cameras vertical axis to the entity  the
     * camera is following  position  component
     * @param positionComponent the players position component
     */
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

    /**
     * set a  the entity to follow and extract the needed
     * movable and position components from the entity
     * @param entity Entity the entity to follow
     */
    public void setEntityToFollow(Entity entity){
       PositionComponent positionComponent= GameComponentMapper.getPositionComponentMapper().get(entity);
        MovableComponent movableComponent= GameComponentMapper.getMovableComponentMapper().get(entity);
        if(movableComponent==null || positionComponent==null){
            return;
        }
        this.positionComponent=positionComponent;
        this.movableComponent=movableComponent;
    }

    /**
     * sets the position component and movable component to null
     * which stop the camera from following an entity
     */
    public void unFollowEntity(){
        this.positionComponent=null;
        this.movableComponent=null;
    }

    /**
     * screen min and max getters and setters
     *
     */
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

    /**
     * updates the camera and if following an entity
     * centers the camera to the  entities positions
     */
    public void update() {
        super.update();
        if(movableComponent!=null && positionComponent!=null && movableComponent.isMoved()){
            moveCameraWithFollowMovable();
    }
    }

    /**
     * centers  the camera to an entities position
     * @param positionComponent the position component to center to
     */
    public void centerCameraToPosition(PositionComponent positionComponent){
        centerCameraToPositionHeight(positionComponent);
        centerCameraToPositionWidth(positionComponent);
    }

    /**
     * enables controlling the camera by keyboard input
     * and  adds  all of  input keys functions to the key listener.
     */
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
