package com.jessematty.black.tower.Editor.EditMode.Screens;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.Generators.Entity.EntityGenerator;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Editor.Tools.MapTools.PlaceMode;
public class MapDragTarget extends Target {
  private   int locationX;
   private  int locationY;
   private MapEditScreen mapEditScreen;
   private Rectangle selectedArea;
    public MapDragTarget(Actor actor, int locationX, int locationY, MapEditScreen mapEditScreen) {
        super(actor);
        this.locationX = locationX;
        this.locationY = locationY;
        this.mapEditScreen = mapEditScreen;
    }
    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return false;
    }
    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        Vector3 unprojectedScreenCoordinates= mapEditScreen.getCamera().unproject(new Vector3(x, y,0));
        PlaceMode placeMode = mapEditScreen.getPlaceMode();
        GameMap currentMap= mapEditScreen.getCurrentMap();
        if(placeMode == PlaceMode.PLACE){
            Entity entityToPlace = (Entity) mapEditScreen.getClipBoard().getCurrentObject();
            World world= mapEditScreen.getWorld();
            ComponentMapper<PositionComponent> positionComponentMapper= GameComponentMapper.getPositionComponentMapper();
            if(currentMap!=null) {
                EntityGenerator.copyEntity(entityToPlace);
                PositionComponent position = positionComponentMapper.get(entityToPlace);
                position.setMapWorldLocationX(currentMap.getWorldX());
                position.setMapWorldLocationY(currentMap.getWorldX());
                position.setLocationX(unprojectedScreenCoordinates.x);
                position.setLocationY(unprojectedScreenCoordinates.y);
                world.addEntityToWorld(entityToPlace);
            }
        }
        if(placeMode == PlaceMode.PLACE) {
            Object object = payload.getObject();
            if (object instanceof LandSquareTile[][]) {
                LandSquareTile[][] tilesToPlace= (LandSquareTile[][]) object;
                if (currentMap != null) {
                    LandSquareTile tile = currentMap.screenToTile(unprojectedScreenCoordinates.x, unprojectedScreenCoordinates.y);
                    if (tilesToPlace != null) {
                        placeTiles(tile.getLocationX(), tile.getLocationY(), tilesToPlace);
                    }
                }
            }
        }
        if(placeMode == PlaceMode.PLACE) {
            if (currentMap != null) {
                LandSquareTile tile = currentMap.screenToTile(unprojectedScreenCoordinates.x, unprojectedScreenCoordinates.y);
                Object object=payload.getObject();
                if (object instanceof Cell [] [] []) {
                    Cell[][][] cellsToPlace  = (Cell [] [] []) object;
                    if (cellsToPlace != null) {
                        placeCells(tile.getLocationX(), tile.getLocationY(), cellsToPlace);
                    }
                }
            }
        }
    }
    private void placeTiles(int x, int y,LandSquareTile [] [] tiles){
        GameMap currentMap= mapEditScreen.getCurrentMap();
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
    private void placeCells(int locationX, int locationY, Cell[][][] cellsToPlace) {
        int layers=cellsToPlace.length;
        int xMax = cellsToPlace[0].length;
        int yMax = cellsToPlace[0][0].length;
        int currentLayerNumber= mapEditScreen.getCurrentLayerNumber();
        TiledMap currentTiledMap= mapEditScreen.getTiledMapEdit().getCurrentTiledMap();
        int xSize= mapEditScreen.getXSize();
        int ySize= mapEditScreen.getYSize();
       int tileSizeX= mapEditScreen.getTileWidth();
       int tileSizeY=mapEditScreen.getTileHeight();
        for (int countLayers = currentLayerNumber; countLayers < layers; countLayers++) {
            if(countLayers>=currentTiledMap.getLayers().size()){
                TiledMapTileLayer tiledMapTileLayer= new TiledMapTileLayer(xSize, ySize, tileSizeX, tileSizeY);
                tiledMapTileLayer.setName("layer "+currentTiledMap.getLayers().size());
                currentTiledMap.getLayers().add(tiledMapTileLayer);
            }
            TiledMapTileLayer tiledMapTileLayer= (TiledMapTileLayer) currentTiledMap.getLayers().get(countLayers);
            for (int countx = locationX; countx < xMax; countx++) {
                for (int county = locationY; county < yMax; county++) {
                    tiledMapTileLayer.setCell(countx, county, cellsToPlace[countLayers][countx][county]);
                }
            }
        }
    }
}
