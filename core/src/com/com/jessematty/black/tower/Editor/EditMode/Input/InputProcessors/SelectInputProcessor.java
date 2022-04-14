package com.jessematty.black.tower.Editor.EditMode.Input.InputProcessors;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.Tools.EntityTools.EntityTools;
import com.jessematty.black.tower.Editor.Tools.MapTools.SelectMode;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

/**
 * input processor  that places a  a texture region in  static tile on a tiled map
 */
public class SelectInputProcessor extends MapEditProcessor {

    public SelectInputProcessor(MapEditScreen mapEditScreen) {
        super(mapEditScreen);

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // out of screen exit the listener
        if(!checkBounds(screenX, screenY)){
            return  false;
        }
        Object object = clipBoard.getCurrentObject();
        if ( !(object instanceof TextureRegion)) {
            return false;
        }
        Vector3 unprojectedScreenCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        float x = unprojectedScreenCoordinates.x;
        float y = unprojectedScreenCoordinates.y;
        EntityTools.placeEntity(currentMap, mapEditScreen.getWorld(), (Entity) object, x, y);

        return false;
    }



    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (GameAssets.getGameInput().getKeyListener().anyKeysPressed(Keys.SHIFT_LEFT, Keys.SHIFT_RIGHT)) {
            Viewport viewport = uiStage.getViewport();
            if (screenX > viewport.getWorldWidth() || screenY > viewport.getWorldWidth()) {
                return true;
            }
            Vector3 unprojectedScreenCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
                if (currentMap != null) {
                    selectedArea.height = unprojectedScreenCoordinates.x - selectedArea.x;
                    selectedArea.width = unprojectedScreenCoordinates.y - selectedArea.y;
                }
                gameMapEdit.selectTiles(selectedArea);

                if (currentMap != null) {
                    selectedArea.height = unprojectedScreenCoordinates.x - selectedArea.x;
                    selectedArea.width = unprojectedScreenCoordinates.y - selectedArea.y;

                tiledMapEdit.selectCells(selectedArea);
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        uiStage.setKeyboardFocus(null);

        Viewport mapViewport = uiStage.getViewport();
        if (screenX > mapViewport.getWorldWidth() || screenY > mapViewport.getWorldWidth()) {
            return true;
        }
        Vector3 unprojectedScreenCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        if (pointer == Buttons.LEFT)
                if (currentMap != null) {
                    selectedArea.height = unprojectedScreenCoordinates.x - selectedArea.x;
                    selectedArea.width = unprojectedScreenCoordinates.y - selectedArea.y;
                }
                shapeRenderer.begin(ShapeType.Line);
                shapeRenderer.setColor(1, 0, 0, 1);
                shapeRenderer.rect(selectedArea.x, selectedArea.y, selectedArea.width, selectedArea.height);
                shapeRenderer.end();

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // out of screen exit the listener
        if(!checkBounds(screenX, screenY)){
            return  false;
        }
         Object object=clipBoard.getCurrentObject();
        //  actual  get game location coordinates
        Vector3 unprotectedScreenCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        clipBoard.setScreenLocations(screenX, uiStage.getViewport().getTopGutterY() - screenY);
        float x = unprotectedScreenCoordinates.x;
        float y = unprotectedScreenCoordinates.y;
        placeObjectPreview(object, x, y);

        return false;
    }

    private void placeObjectPreview(Object object, float x, float y) {


    }







}
