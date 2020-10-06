package com.jessematty.black.tower.Maps.Settings;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.Generators.MapGenerators.NumberMapGen;

import java.util.ArrayList;
import java.util.List;

public   class GameMapSettings {
    protected int xSize;
    protected int ySize; // x and y number  of tiles  for the landSquareTileMap
    private boolean newMap=true;
    protected String skinName = "";
    protected String skinAtlas = "";
    protected String mapName = "Map";
    protected float dayLightAmount = 0;
    protected boolean gettingBrighter = true;
    protected double gravity=9.8;
    protected int tileSizeX=32;// map tileSizes
    protected int tileSizeY=32;
    protected boolean lightChanges;
    protected float lightChangeAmount = .01f;
    protected String tiledMapAtlasName;
    protected boolean currentMap; // is the current landSquareTileMap in the Map drawItemAtLocation class?
    protected String tiledMapPath; // path to the tiled landSquareTileMap file
    protected float maxXScreen;
    protected float maxYScreen;   // libgdx screen units
    protected int worldX;
    protected int worldY;
    protected String textureAtlasPath;




    public GameMapSettings() {
    }


    public int getxSize() {
        return xSize;
    }
    public void setxSize(int xSize) {
        this.xSize = xSize;
    }
    public int getySize() {
        return ySize;
    }
    public void setySize(int ySize) {
        this.ySize = ySize;
    }
    public String getTiledMapPath() {
        return tiledMapPath;
    }
    public void setTiledMapPath(String tiledMapPath) {
        this.tiledMapPath = tiledMapPath;
    }
    public String getMapName() {
        return mapName;
    }
    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
    public double getGravity() {
        return gravity;
    }
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }


   
    public boolean isCurrentMap() {
        return currentMap;
    }
    public void setCurrentMap(boolean currentMap) {
        this.currentMap = currentMap;
    }
   
    public String getSkinName() {
        return skinName;
    }
    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public float getLightChangeAmount() {
        return lightChangeAmount;
    }

    public void setLightChangeAmount(float lightChangeAmount) {
        this.lightChangeAmount = lightChangeAmount;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }



    public String getSkinAtlas() {
        return skinAtlas;
    }

    public void setSkinAtlas(String skinAtlas) {
        this.skinAtlas = skinAtlas;
    }



    public float getDayLightAmount() {
        return dayLightAmount;
    }

    public void setDayLightAmount(float dayLightAmount) {
        this.dayLightAmount = dayLightAmount;
    }

    public boolean isGettingBrighter() {
        return gettingBrighter;
    }

    public void setGettingBrighter(boolean gettingBrighter) {
        this.gettingBrighter = gettingBrighter;
    }

    public int getTileSizeX() {
        return tileSizeX;
    }

    public void setTileSizeX(int tileSizeX) {
        this.tileSizeX = tileSizeX;
    }

    public int getTileSizeY() {
        return tileSizeY;
    }

    public void setTileSizeY(int tileSizeY) {
        this.tileSizeY = tileSizeY;
    }

    public boolean isLightChanges() {
        return lightChanges;
    }

    public void setLightChanges(boolean lightChanges) {
        this.lightChanges = lightChanges;
    }

    public String getTiledMapAtlasName() {
        return tiledMapAtlasName;
    }

    public void setTiledMapAtlasName(String tiledMapAtlasName) {
        this.tiledMapAtlasName = tiledMapAtlasName;
    }

    public float getMaxXScreen() {
        return maxXScreen;
    }

    public void setMaxXScreen(float maxXScreen) {
        this.maxXScreen = maxXScreen;
    }

    public float getMaxYScreen() {
        return maxYScreen;
    }

    public void setMaxYScreen(float maxYScreen) {
        this.maxYScreen = maxYScreen;
    }

    public String getTextureAtlasPath() {
        return textureAtlasPath;
    }

    public void setTextureAtlasPath(String textureAtlasPath) {
        this.textureAtlasPath = textureAtlasPath;
    }

    public boolean isNewMap() {
        return newMap;
    }

    public void setNewMap(boolean newMap) {
        this.newMap = newMap;
    }






}
