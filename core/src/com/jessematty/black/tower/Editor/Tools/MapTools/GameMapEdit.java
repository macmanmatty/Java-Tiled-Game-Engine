package com.jessematty.black.tower.Editor.Tools.MapTools;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Rectangle;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
public class GameMapEdit {
   private GameMap currentMap;
   private int xSize;
   private int ySize;
   private int tileSizeX;
   private  int tileSizeY;
   private ClipBoard clipBoard;
   private GameAssets gameAssets;
    public GameMapEdit( GameAssets gameAssets, ClipBoard clipBoard) {
        this.clipBoard = clipBoard;
        this.gameAssets = gameAssets;
    }
    public void fill(int locationX, int locationY, LandSquareTile fillTile) {
    LandSquareTile landSquareTile = currentMap.getMapSquare(locationX, locationY);
    String  type = landSquareTile.getComponent(StringStats.class).getStringStat("type").getStat();
    fillCell(locationX, locationY, fillTile, type);
}
    public void fillCell(int locationX, int locationY, LandSquareTile fillTile, String type) {
        LandSquareTile landSquareTile = currentMap.getMapSquare(locationX, locationY);
        String  currentTileType = landSquareTile.getComponent(StringStats.class).getStringStat("type").getStat();
        if (currentTileType.equalsIgnoreCase(type)) {
            currentMap.getMap()[locationX][locationY]=fillTile;
            if (locationY + 1 < ySize) {
                fillCell(locationX, locationY + 1, fillTile, type);
            }
            if (locationY - 1 >= 0) {
                fillCell(locationX, locationY - 1, fillTile, type);
            }
            if (locationX - 1 >= 0) {
                fillCell(locationX - 1, locationY, fillTile, type);
            }
            if (locationX + 1 < xSize) {
                fillCell(locationX + 1, locationY, fillTile, type);
            }
        }
    }
    public void selectTiles(Rectangle selectedArea) {
        int x= (int) (selectedArea.x/tileSizeX);
        int y= (int) (selectedArea.y/tileSizeY);
        int xMax= (int) (selectedArea.width/tileSizeX)+1;
        int yMax= (int) (selectedArea.height/tileSizeY)+1;
        if(xMax>xSize){
            xMax=xSize;
        }
        if(yMax>ySize){
            yMax=ySize;
        }
        if(x<0){
            x=0;
        }
        if(y<0){
            y=0;
        }
       LandSquareTile [] []  tilesToPlace= (LandSquareTile[][]) clipBoard.getCurrentObject();
        for (int countx=x; countx<xMax; countx++) {
            for (int county = y; county < yMax; county++) {
                LandSquareTile tile=currentMap.getMapSquareOrNull(countx, county);
                if(tile!=null){
                    tilesToPlace[countx-x][county-y]=tile;
                }
            }
        }
    }
    public void placeTiles(int x, int y,LandSquareTile [] [] tiles){
        int xMax=tiles.length;
        int yMax=tiles[0].length;
        for (int countx=x; countx<xMax; countx++) {
            for (int county = y; county < yMax; county++) {
                LandSquareTile tile=currentMap.getMapSquareOrNull(countx, county);
                if(tile!=null){
                    tile=tiles[countx][county];
                }
            }
        }
    }




    public void changeMapSize(int xSize, int ySize){
        MapTools.changeMapSize(currentMap, xSize, ySize);
    }
    public GameMap getCurrentMap() {
        return currentMap;
    }
    public void setCurrentMap(GameMap currentMap) {
        this.currentMap = currentMap;
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
}
