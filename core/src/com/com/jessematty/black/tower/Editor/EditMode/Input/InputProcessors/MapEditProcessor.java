package com.jessematty.black.tower.Editor.EditMode.Input.InputProcessors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.GameMapEdit.GameMapEdit;
import com.jessematty.black.tower.Editor.EditMode.TiledMapEdit.TiledMapEdit;
import com.jessematty.black.tower.GameBaseClasses.Input.LockableInputProcessor;
import com.jessematty.black.tower.Maps.GameMap;

/**
 *
 * class all input processors for editing the game extend
 *
 */
public  abstract class MapEditProcessor implements LockableInputProcessor {
    protected boolean mouseInputLocked=true;
    protected boolean keyInputLocked=true;
    protected boolean  touchUpMouseInputLocked;
    protected boolean touchDownMouseInputLocked;
    protected boolean scrolledMouseInputLocked;
    protected boolean mouseMovedMouseInputLocked;
    protected boolean touchDraggedMouseInputLocked;
    private boolean keyTypedKeyInputLocked=false;
    private boolean keyUpKeyInputLocked=false;
    private boolean keyDownKeyInputLocked=false;
    protected MapEditScreen mapEditScreen;
    protected OrthographicCamera camera;
    protected ClipBoard clipBoard;
    protected TiledMapEdit tiledMapEdit;
    protected GameMapEdit gameMapEdit;
    protected GameMap currentMap;
    protected Rectangle selectedArea;
    protected ShapeRenderer shapeRenderer;
    protected Stage uiStage;
    protected TextureRegion mousePointer;
    protected float screenX;
    protected float screenY;
    protected boolean drawMousePointer;
    protected boolean drawPointerOutOfBounds;
    public MapEditProcessor(MapEditScreen mapEditScreen) {
        this.mapEditScreen = mapEditScreen;
        camera=mapEditScreen.getCamera();
        clipBoard=mapEditScreen.getClipBoard();
        tiledMapEdit=mapEditScreen.getTiledMapEdit();
        gameMapEdit =mapEditScreen.getGameMapEdit();
        currentMap=mapEditScreen.getCurrentMap();
        shapeRenderer=mapEditScreen.getShapeRenderer();
        uiStage=mapEditScreen.getUiStage();
    }

    /**

    these will be overridden in the sub classes
     */
    @Override
    public boolean isMouseInputLocked() {
        return mouseInputLocked;
    }

    @Override
    public void setMouseInputLocked(boolean mouseInputLocked) {
        this.mouseInputLocked = mouseInputLocked;
    }

    @Override
    public boolean isKeyInputLocked() {
        return keyInputLocked;
    }

    @Override
    public void setKeyInputLocked(boolean keyInputLocked) {
        keyInputLocked = keyInputLocked;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        uiStage.setKeyboardFocus(null);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    // sets the screen to  a  x, y world position
    public void setCameraToMapCoordinates(float mapX, float mapY) {
        Vector3 screenCoordinates = camera.project(new Vector3(mapX, mapY, 0));
        camera.translate(new Vector2(screenCoordinates.x, screenCoordinates.y));
    }
    private Vector2 setCoordinatesToTile(float screenX, float screenY){
        int tilesX= (int) (screenX/ currentMap.getTileWidth());
        int tilesY= (int) (screenY/ currentMap.getTileHeight());
        float newScreenX=tilesX* currentMap.getTileWidth();
        float newScreenY=tilesY* currentMap.getTileHeight();
        return  new Vector2(tilesX, tilesY);
    }

    /**
     * function check whether the pointer is on the map stage
     * also sets the current screen locations to x and y variables
     * for drawing of the pointer on the screen
     * @param screenX the X screen location of the pointer or object
     * @param screenY the Y screen location of the pointer or object
     * @return boolean whether or not the object is with in the bounds of the screen
     */
   public boolean  checkBounds(int screenX, int screenY) {
        Viewport mapViewport = uiStage.getViewport();
        if (screenX > mapViewport.getWorldWidth() || screenY > mapViewport.getWorldWidth()) {
            this.screenX=screenX;
            this.screenY=screenY;
            return true;
        }
        else if(!drawPointerOutOfBounds){
            drawMousePointer=false;
        }
        return  false;
    }

    public TextureRegion getMousePointer() {
        return mousePointer;
    }

    public float getScreenX() {
        return screenX;
    }

    public float getScreenY() {
        return screenY;
    }


    public boolean isDrawMousePointer() {
        return drawMousePointer;
    }

    public void setDrawMousePointer(boolean drawMousePointer) {
        this.drawMousePointer = drawMousePointer;
    }

    public boolean isDrawPointerOutOfBounds() {
        return drawPointerOutOfBounds;
    }

    public void setDrawPointerOutOfBounds(boolean drawPointerOutOfBounds) {
        this.drawPointerOutOfBounds = drawPointerOutOfBounds;
    }

    @Override
    public boolean isTouchUpMouseInputLocked() {
        return touchUpMouseInputLocked;
    }

    @Override
    public void setTouchUpMouseInputLocked(boolean touchUpMouseInputLocked) {
        this.touchUpMouseInputLocked = touchUpMouseInputLocked;
    }

    @Override
    public boolean isTouchDownMouseInputLocked() {
        return touchDownMouseInputLocked;
    }

    @Override
    public void setTouchDownMouseInputLocked(boolean touchDownMouseInputLocked) {
        this.touchDownMouseInputLocked = touchDownMouseInputLocked;
    }

    @Override
    public boolean isScrolledMouseInputLocked() {
        return scrolledMouseInputLocked;
    }

    @Override
    public void setScrolledMouseInputLocked(boolean scrolledMouseInputLocked) {
        this.scrolledMouseInputLocked = scrolledMouseInputLocked;
    }

    @Override
    public boolean isMouseMovedMouseInputLocked() {
        return mouseMovedMouseInputLocked;
    }

    @Override
    public void setMouseMovedMouseInputLocked(boolean mouseMovedMouseInputLocked) {
        this.mouseMovedMouseInputLocked = mouseMovedMouseInputLocked;
    }

    @Override
    public boolean isTouchDraggedMouseInputLocked() {
        return touchDraggedMouseInputLocked;
    }

    @Override
    public void setTouchDraggedMouseInputLocked(boolean touchDraggedMouseInputLocked) {
        this.touchDraggedMouseInputLocked = touchDraggedMouseInputLocked;
    }

    @Override
    public boolean isKeyTypedKeyInputLocked() {
        return keyTypedKeyInputLocked;
    }

    @Override
    public void setKeyTypedKeyInputLocked(boolean keyTypedKeyInputLocked) {
        this.keyTypedKeyInputLocked = keyTypedKeyInputLocked;
    }

    @Override
    public boolean isKeyUpKeyInputLocked() {
        return keyUpKeyInputLocked;
    }

    @Override
    public void setKeyUpKeyInputLocked(boolean keyUpKeyInputLocked) {
        this.keyUpKeyInputLocked = keyUpKeyInputLocked;
    }

    @Override
    public boolean isKeyDownKeyInputLocked() {
        return keyDownKeyInputLocked;
    }

    @Override
    public void setKeyDownKeyInputLocked(boolean keyDownKeyInputLocked) {
        this.keyDownKeyInputLocked = keyDownKeyInputLocked;
    }
}
