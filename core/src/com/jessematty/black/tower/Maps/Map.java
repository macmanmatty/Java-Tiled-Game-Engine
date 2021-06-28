package com.jessematty.black.tower.Maps;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Settings.Settings;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.List;
public interface Map {
  
    public void mapTurnActions(float deltaTime, GameTime gameTime);
    public LandSquareTile[][] getMap();
    public int getXTiles();
    public int getYTiles() ;
    public LandSquareTile getMapSquare(int xlocation, int ylocation);
    public LandSquareTile getMapSquareOrNull(int xLocation, int yLocation);
    public void addEntity(  Entity entity);
    public TiledMap getTiledMap() ;
    public void setTiledMap(TiledMap tiledMap);
    public void removeEntity( Entity entity);
    public LandSquareTile screenToTile(float screenLocationX, float screenLocationY) ;
    public void setMap(LandSquareTile[][] map);
    public double getGravity();
    public void setGravity(double gravity) ;
    public float getMaxXScreen();
    public float getMaxYScreen();
    public boolean isCurrentMap();
    public void setCurrentMap(boolean currentMap);
    public void setMapName(String mapName);
    public String getMapName() ;
    public float getDayLightAmount();
    public void setDayLightAmount(double gameTime);
    public void setDayLight(float dayLight);
    public int getTileWidth();
    public int getTileHeight();
    public float getLightChangeAmount();
    public void setLightChangeAmount(float lightChangeAmount);
    public  void setMapSquare(int x,  int y, LandSquareTile tile);
    public void setSkin(Skin skin) ;
    public Skin getSkin();
    public Array<Entity> getEntities() ;
    public void setEntities(Array<Entity> entities);
    public int getWorldX() ;
    public int getWorldY() ;
    public void setWorldX(int worldX) ;
    public void setWorldY(int worldY) ;
    public void setTileSize(int sizeX, int sizeY) ;
    public Settings getMapSettings();


    
    
}
