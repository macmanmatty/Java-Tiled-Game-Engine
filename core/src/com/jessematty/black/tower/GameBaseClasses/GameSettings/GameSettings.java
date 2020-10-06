package com.jessematty.black.tower.GameBaseClasses.GameSettings;

public class GameSettings {

    private   String mainWindowImageName;
    private  String mainWindowText;
    private  String atlasName;
    private  int startTileLocationX;
    private   int startTileLocationY;
    private String gameName;
    private float  screenWidth;
    private float screenHeight;
    private int totalNumberOfMaps;
    private String savePath;

    private GameInputKeys gameInputKeys= new GameInputKeys();


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



    public GameInputKeys getGameInputKeys() {
        return gameInputKeys;
    }

    public void setGameInputKeys(GameInputKeys gameInputKeys) {
        this.gameInputKeys = gameInputKeys;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
