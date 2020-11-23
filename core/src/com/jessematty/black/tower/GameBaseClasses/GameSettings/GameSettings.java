package com.jessematty.black.tower.GameBaseClasses.GameSettings;

public class GameSettings {

    private   String mainWindowImageName;
    private  String mainWindowText;
    private  String atlasName;
    private  int startMapLocationX;
    private   int startMapLocationY;
    private String gameName;
    private float  screenWidth;
    private float screenHeight;
    private int totalNumberOfMaps;
    private String savePath;
    private String textureAtlasPath;

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






    public int getStartMapLocationX() {
        return startMapLocationX;
    }

    public void setStartMapLocationX(int startMapLocationX) {
        this.startMapLocationX = startMapLocationX;
    }

    public int getStartMapLocationY() {
        return startMapLocationY;
    }

    public void setStartMapLocationY(int startMapLocationY) {
        this.startMapLocationY = startMapLocationY;
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

    public String getTextureAtlasPath() {
        return textureAtlasPath;
    }

    public void setTextureAtlasPath(String textureAtlasPath) {
        this.textureAtlasPath = textureAtlasPath;
    }
}
