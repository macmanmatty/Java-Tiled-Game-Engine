package com.jessematty.black.tower.GameBaseClasses.Input;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector3;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class CameraInput implements InputProcessor {

    private OrthographicCamera camera; // the camera for rendering the currentGameMap and actors
    private TiledMap tiledMap;
    TapMode tapMode;
    GameMap currentGameMap;
    MapDraw draw;

    public CameraInput(MapDraw draw) {
        this.draw = draw;
        camera=draw.getCamera();

    }

    @Override
    public boolean keyDown(int keycode) {
        if(true==false) {
            if (keycode == Keys.LEFT)
                camera.translate(-32, 0);
            if (keycode == Keys.RIGHT)
                camera.translate(32, 0);
            if (keycode == Keys.UP)
                camera.translate(0, -32);
            if (keycode == Keys.DOWN)
                camera.translate(0, 32);
            if (keycode == Keys.EQUALS)
                camera.zoom = camera.zoom + .01f;
            if (keycode == Keys.MINUS)
                camera.zoom = camera.zoom - .01f;
            if (keycode == Keys.S) {
            }
            if (keycode == Keys.NUM_1)
                tiledMap.getLayers().get(0).setVisible(!draw.getCurrentTiledMap().getLayers().get(0).isVisible());
            if (keycode == Keys.NUM_2)
                tiledMap.getLayers().get(1).setVisible(!draw.getCurrentTiledMap().getLayers().get(1).isVisible());
            return false;
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        LandSquareTile tile = draw.getCurrentMap().screenToTile(worldCoordinates.x, worldCoordinates.y);
        if (button== Buttons.RIGHT) {
            if(tapMode==TapMode.MOVE) {
            }
            else if(tapMode==TapMode.TARGET) {
                draw.getPlayer().getMovable().setLocationToMoveTo(tile);
            }
            else if(tapMode==TapMode.INFO){
            }
        }
        if (button== Buttons.LEFT) {
        }
        else{
        }
        return true;
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
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        LandSquareTile tile= currentGameMap.screenToTile(worldCoordinates.x, worldCoordinates.y);
        /*
        System.out.println("tile locations: "+tile.getLocationX()+" , "+tile.getLocationY());
            System.out.println("tile screen  locations: "+tile.getScreenLocationx()+" , "+tile.getScreenLocationy());
            System.out.println("Fighter locations: "+fighterInteractingWith.getTileLocationX()+" , "+fighterInteractingWith.getTileLocationY());
            System.out.println("Fighter screen  locations: "+fighterInteractingWith.getScreenLocationX()+" , "+fighterInteractingWith.getScreenLocationY());
            System.out.println("tile name  "+tile);
            System.out.println("fighter tile name: "+fighterInteractingWith.getLocationToTransportTo());
*/
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
