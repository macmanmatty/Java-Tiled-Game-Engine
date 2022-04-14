package com.jessematty.black.tower.Maps;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class LightMap {

    TiledMap tiledMap;





    public void makeMap(TextureRegion region, int xSize, int ySize){
        tiledMap= new TiledMap();
        TiledMapTileLayer layer= new TiledMapTileLayer(xSize,ySize, 32 ,32 );
        Cell cell=new Cell();
        cell.setTile(new StaticTiledMapTile(region));

        for (int countx=0; countx<xSize; countx++) {
            for (int county = 0; county < ySize; county++) {

                layer.setCell(countx, county, cell);

            }

        }


        tiledMap.getLayers().add(layer);
    }


    public TiledMap getTiledMap() {
        return tiledMap;
    }
}
