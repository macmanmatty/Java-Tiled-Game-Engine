package com.jessematty.black.tower.Maps;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.Settings.GameMapSettings;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.List;
public interface Map {
  
    public void deSerialize(GameAssets assets, com.jessematty.black.tower.Maps.Settings.GameMapSettings settings); // method for landSquareTileMap initlization landSquareTileMap deseralization
    public void mapTurnActions(float deltaTime, GameTime gameTime);
    public LandSquareTile[][] getMap();
    public int getXSize();
    public int getYSize() ;
    public LandSquareTile getMapSquare(int xlocation, int ylocation);
    public LandSquareTile getMapSquareOrNull(int xLocation, int yLocation);
    public void addEntity(  Entity entity);
    public TiledMap getTiledMap() ;
    public void setTiledMap(TiledMap tiledMap);
    public void removeEntity( Entity entity);
    public Vector2 getScreenCoordinatesFromTileCoordinates(int x, int y);
    public void setTime(int time);
    public LandSquareTile screenToTile(float screenLocationX, float screenLocationY) ;
    public void setMap(LandSquareTile[][] map);
    public double getGravity();
    public void setGravity(double gravity) ;
    public float getMaxXScreen();
    public float getMaxYScreen();
    public String getTiledMapPath() ;
    public boolean isCurrentMap();
    public void setCurrentMap(boolean currentMap);
    public String getSkinName();
    public void setSkinName(String skinName);
    public String getSkinAtlas();
    public void setSkinAtlas(String skinAtlas) ;
    public void setMapName(String mapName);
    public String getMapName() ;
    public GameMapSettings getSettings();
    public float getDayLightAmount();
    public void setDayLightAmount(double gameTime);
    public void setDayLight(float dayLight);
    public int getTileSizeX();
    public int getTileSizeY();
    public boolean isLightChanges();
    public void setLightChanges(boolean lightChanges) ;
    public float getLightChangeAmount();
    public void setLightChangeAmount(float lightChangeAmount);
    public  void setMapSquare(int x,  int y, LandSquareTile tile);
    public void setSkin(Skin skin) ;
    public Skin getSkin();
    public String getTiledMapAtlasName();
    public void setTiledMapAtlasName(String tiledMapAtlasName) ;
    public List<Entity> getEntities() ;
    public void setEntities(List<Entity> entities);
    public int getWorldX() ;
    public int getWorldY() ;
    public void setWorldX(int worldX) ;
    public void setWorldY(int worldY) ;
    public void setTileSize(int sizeX, int sizeY) ;
    public void setTileSize(int size) ;
    public boolean isNewMap() ;
    
    
    
}
