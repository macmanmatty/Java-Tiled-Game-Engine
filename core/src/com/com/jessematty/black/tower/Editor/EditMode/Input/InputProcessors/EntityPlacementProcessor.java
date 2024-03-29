package com.jessematty.black.tower.Editor.EditMode.Input.InputProcessors;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.Tools.EntityTools.EntityTools;

/**
 * input processor  that places a  a texture region in  static tile on a tiled map
 */
public class EntityPlacementProcessor extends MapEditProcessor {

    public EntityPlacementProcessor(MapEditScreen mapEditScreen) {
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


}
