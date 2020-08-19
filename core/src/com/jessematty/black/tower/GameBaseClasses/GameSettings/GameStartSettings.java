package com.jessematty.black.tower.GameBaseClasses.GameSettings;

public class GameStartSettings {

    private   String mainWindowImageName;
    private  String mainWindowText;
    private  String atlasName;
    private  int startTileLocationX;
    private   int startTileLocationY;
    private String gameName;
    private float  screenWidth;
    private float screenHeight;
    private int totalNumberOfMaps;
    private int xMaps;
    private int yMaps;


    public float getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(float screenWidth) {
        this.screenWidth = screenWidth;
    }

    public float getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(float screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getMainWindowImageName() {
        return mainWindowImageName;
    }

    public void setMainWindowImageName(String mainWindowImageName) {
        this.mainWindowImageName = mainWindowImageName;
    }

    public String getMainWindowText() {
        return mainWindowText;
    }

    public void setMainWindowText(String mainWindowText) {
        this.mainWindowText = mainWindowText;
    }

    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }






    public int getStartTileLocationX() {
        return startTileLocationX;
    }

    public void setStartTileLocationX(int startTileLocationX) {
        this.startTileLocationX = startTileLocationX;
    }

    public int getStartTileLocationY() {
        return startTileLocationY;
    }

    public void setStartTileLocationY(int startTileLocationY) {
        this.startTileLocationY = startTileLocationY;
    }

    public int getTotalNumberOfMaps() {
        return totalNumberOfMaps;
    }

    public void setTotalNumberOfMaps(int totalNumberOfMaps) {
        this.totalNumberOfMaps = totalNumberOfMaps;
    }

    public int getXMaps() {
        return xMaps;
    }

    public void setXMaps(int xMaps) {
        this.xMaps = xMaps;
    }

    public int getYMaps() {
        return yMaps;
    }

    public void setYMaps(int yMaps) {
        this.yMaps = yMaps;
    }
}
