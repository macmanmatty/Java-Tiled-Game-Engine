package com.jessematty.black.tower.Maps.Settings;

public   class GameMapSettings {
    int xSize;
    int ySize;
    String tiledMapPath;
    String mapName;
    String skinName;
    double gravity;
    boolean hasEnemies;
    boolean currentMap;
    boolean hasWeather;
    int numberOfBuildings;
    float lightChangeAmount;
    int worldX;
    int worldY;


    public GameMapSettings() {
    }

    public GameMapSettings(int xSize, int ySize, String tiledMapPath, String mapName, String skinName, double gravity, boolean hasEnemies,  boolean currentMap, boolean hasWeather, int numberOfBuildings) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.tiledMapPath = tiledMapPath;
        this.mapName = mapName;
        this.skinName = skinName;
        this.gravity = gravity;
        this.hasEnemies = hasEnemies;
        this.currentMap = currentMap;
        this.hasWeather = hasWeather;
        this.numberOfBuildings = numberOfBuildings;
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
    public boolean isHasEnemies() {
        return hasEnemies;
    }
    public void setHasEnemies(boolean hasEnemies) {
        this.hasEnemies = hasEnemies;
    }


   
    public boolean isCurrentMap() {
        return currentMap;
    }
    public void setCurrentMap(boolean currentMap) {
        this.currentMap = currentMap;
    }
    public boolean isHasWeather() {
        return hasWeather;
    }
    public void setHasWeather(boolean hasWeather) {
        this.hasWeather = hasWeather;
    }
    public int getNumberOfBuildings() {
        return numberOfBuildings;
    }
    public void setNumberOfBuildings(int numberOfBuildings) {
        this.numberOfBuildings = numberOfBuildings;
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
}
