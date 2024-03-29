package com.jessematty.black.tower.Editor.EditMode.Input.InputProcessors;

import com.badlogic.gdx.math.Vector3;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;

/**
 *  processor that when clicked bucket fills a layer on TiledMap
 *  similar to bucket fill in ms paint
 */
public class CopyInputProcessor extends  MapEditProcessor {


    public CopyInputProcessor(MapEditScreen mapEditScreen) {
       super(mapEditScreen);

    }
    
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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



}
