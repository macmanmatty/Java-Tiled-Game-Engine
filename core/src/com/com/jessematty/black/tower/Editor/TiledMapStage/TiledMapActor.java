package com.jessematty.black.tower.Editor.TiledMapStage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.ColoredTiledMapTile;

public class TiledMapActor extends Actor {

        private TiledMap tiledMap;

        private TiledMapTileLayer tiledLayer;

        private TiledMapTileLayer.Cell cell;
        private ColoredTiledMapTile coloredTiledMapTile;
        private int locationX;
        private int locationY;
        private int tileSizeX;
        private int tileSizeY;
        private float screenX;
        private float screenY;
        private int layerNumber;

        public TiledMapActor(TiledMap tiledMap, TiledMapTileLayer tiledLayer, TiledMapTileLayer.Cell cell, int locationX, int locationY,  int width, int height,  int tileSizeX, int tileSizeY, int layerNumber) {
            this.tiledMap = tiledMap;
            this.tiledLayer = tiledLayer;
            this.cell = cell;
            this.coloredTiledMapTile= (ColoredTiledMapTile) cell.getTile();
            this.locationX=locationX;
            this.locationY=locationY;
            this.tileSizeX=tileSizeX;
            this.tileSizeY=tileSizeY;
            this.layerNumber=layerNumber;
            this.screenX=locationX*tileSizeX;

             this.screenY = (height - locationY - 1) * tileSizeY;

        }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public TiledMapTileLayer getTiledLayer() {
        return tiledLayer;
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

    }

    public ColoredTiledMapTile getColoredTiledMapTile() {
        return coloredTiledMapTile;
    }
}
