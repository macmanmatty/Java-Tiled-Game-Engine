package com.jessematty.black.tower.Editor.TiledMapStage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jessematty.black.tower.Maps.GameMap;


public class TiledMapStage extends Stage {

        private TiledMap tiledMap;
        private TiledMapTileLayer currentLayer;
        private DragAndDrop dragAndDrop;
        private GameMap map;
        private int tileSizeX;
        private int tileSizeY;





        public TiledMapStage(Batch batch, Viewport viewport, GameMap map, DragAndDrop dragAndDrop) {
            super(viewport, batch);
            this.tiledMap = map.getTiledMap();
            this.map=map;
            this.dragAndDrop=dragAndDrop;
            tileSizeX= (int) map.getTileWidth();
            tileSizeY = map.getTileHeight();
            int count=0;
            for (MapLayer layer : tiledMap.getLayers()) {
                TiledMapTileLayer tiledLayer = (TiledMapTileLayer)layer;
                createActorsForLayer( count, tiledLayer);
                count++;
            }
        }

        private void createActorsForLayer( int layerNumber , TiledMapTileLayer tiledLayer) {

            int width=tiledLayer.getWidth();
            int height=tiledLayer.getHeight();
            for (int countX = 0; countX < width; countX++) {
                for (int countY = 0; countY < height; countY++) {
                    TiledMapTileLayer.Cell cell = tiledLayer.getCell(countX, countY);
                    System.out.println("Cell "+cell);
                    com.jessematty.black.tower.Editor.TiledMapStage.TiledMapActor actor = new TiledMapActor(tiledMap, tiledLayer,  cell, countX, countY, width, height,  tileSizeX, tileSizeY,  layerNumber);
                    actor.setBounds(countX * width, (height-countY-1)*countY , width,height);
                    this.addActor(actor);
                    EventListener eventListener = new TiledMapClickListener(actor);
                    actor.addListener(eventListener);
                }
            }
        }



        public void addLayer( int layerNumber, TiledMapTileLayer layer){
            createActorsForLayer( layerNumber, layer);

        }

        public void setCurrentLayer(String name){
            currentLayer= (TiledMapTileLayer) tiledMap.getLayers().get(name);


        }




    public GameMap getMap() {
        return map;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void setMap(GameMap map) {
        this.map = map;
        this.tiledMap=map.getTiledMap();
        int count=0;
        for (MapLayer layer : tiledMap.getLayers()) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer)layer;
            createActorsForLayer( count, tiledLayer);
            count++;
        }

    }
}






