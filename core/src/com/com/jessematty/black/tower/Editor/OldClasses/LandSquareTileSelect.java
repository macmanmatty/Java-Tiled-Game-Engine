package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class LandSquareTileSelect  implements Screen , InputProcessor{

    GameMap map;
    LandSquareTile tileSelected;

    LandSquareTile  [] [] landSquareTiles; // the game landSquareTileMap of tiles
    TiledMap tiledMap; // the tiled  landSquareTileMap
    TiledMapRenderer tiledMapRenderer;
    int mapMaxX;
    int mapMaxY;


    protected OrthogonalTiledMapRenderer renderer; // landSquareTileMap render
    protected OrthographicCamera camera; // landSquareTileMap camera


    public LandSquareTileSelect(GameMap map) {
        this.map = map;
        landSquareTiles=map.getMap();
        tiledMap=map.getTiledMap();
        mapMaxX=map.getXTiles();
        mapMaxY=map.getYTiles();
        Gdx.input.setInputProcessor(this);

    }



    @Override
    public void show() {

        tiledMapRenderer= new OrthogonalTiledMapRenderer(tiledMap);
        VerticalGroup lists= new VerticalGroup();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)
            camera.translate(-32, 0);



        else  if(keycode == Input.Keys.RIGHT)
            camera.translate(32,0);
        else  if(keycode == Input.Keys.UP)
            camera.translate(0,-32);
        else  if(keycode == Input.Keys.DOWN)
            camera.translate(0,32);


        else  if(keycode == Input.Keys.EQUALS)
            camera.zoom=camera.zoom+.01f;


        else  if(keycode == Input.Keys.MINUS)
            camera.zoom=camera.zoom-.01f;



        return true;
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
        tileSelected=map.getTileFromWorldUnitCoordinates(screenX, screenY);
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
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }


    public boolean scrolled(int amount) {
        return false;
    }

    public LandSquareTile getTileSelected() {
        return tileSelected;
    }
}
