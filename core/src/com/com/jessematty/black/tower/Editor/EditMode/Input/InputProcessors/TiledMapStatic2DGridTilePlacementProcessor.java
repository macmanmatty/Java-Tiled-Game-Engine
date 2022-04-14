package com.jessematty.black.tower.Editor.EditMode.Input.InputProcessors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

/**
 * input processor  that places a  a texture region in  static tile on a tiled map
 */
public class TiledMapStatic2DGridTilePlacementProcessor extends MapEditProcessor {

    public TiledMapStatic2DGridTilePlacementProcessor(MapEditScreen mapEditScreen) {
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
        LandSquareTile tile = currentMap.screenToTile(x, y);
        tiledMapEdit.placeCell(tile.getLocationX(), tile.getLocationY(), (Cell[][]) object);
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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

    // sets the screen to  a  x, y world position
    public void setCameraToMapCoordinates(float mapX, float mapY) {
        Vector3 screenCoordinates = camera.project(new Vector3(mapX, mapY, 0));
        camera.translate(new Vector2(screenCoordinates.x, screenCoordinates.y));
    }


}
