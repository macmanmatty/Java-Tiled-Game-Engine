package com.jessematty.black.tower.Editor.EditMode.Input.InputProcessors;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;

/**
 *  processor that when clicked bucket fills a layer on TiledMap
 *  similar to bucket fill in ms paint
 */
public class FillBucketProcessor extends  MapEditProcessor {

    public FillBucketProcessor(MapEditScreen mapEditScreen) {
       super(mapEditScreen);

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // out of screen exit the listener
        if(!checkBounds(screenX, screenY)){
            return  false;
        }
        Vector3 unprojectedScreenCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        float x = unprojectedScreenCoordinates.x;
        float y = unprojectedScreenCoordinates.y;

            if (currentMap != null) {
                int tiledMapX = (int) (x / currentMap.getTileWidth());
                int tiledMapY = (int) (((y / currentMap.getTileHeight())));
                tiledMapEdit.bucketFill(tiledMapX, tiledMapY, (AtlasNamedAtlasRegion) clipBoard.getCurrentObject());
            }

        return false;
    }



    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // out of screen exit the listener
        if(!checkBounds(screenX, screenY)){
            return  false;
        }
        Object object = clipBoard.getCurrentObject();
        // no object exit
        if (object == null) {
            return false;
        }
        // object out of screen exit and true mouse in not in  application bounds
        Viewport mapViewport = uiStage.getViewport();
        if (screenX > mapViewport.getWorldWidth() || screenY > mapViewport.getWorldWidth()) {
            return true;
        }
        //  actual  get game location coordinates
        Vector3 unprotectedScreenCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        clipBoard.setScreenLocations(screenX, mapViewport.getTopGutterY() - screenY);
        float x = unprotectedScreenCoordinates.x;
        float y = unprotectedScreenCoordinates.y;

            if (currentMap != null) {
                int tiledMapX = (int) (x / currentMap.getTileWidth());
                int tiledMapY = (int) (((y / currentMap.getTileHeight())));
                tiledMapEdit.clearMouseOver();
                tiledMapEdit.fillMouseOver(tiledMapX, tiledMapY, (AtlasNamedAtlasRegion) clipBoard.getCurrentObject()); }

        return false;
    }

}
