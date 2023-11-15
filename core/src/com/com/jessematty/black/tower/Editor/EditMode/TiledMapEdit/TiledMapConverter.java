package com.jessematty.black.tower.Editor.EditMode.TiledMapEdit;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MapLoadingException;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import java.rmi.server.UID;
public final  class TiledMapConverter {
    /**
     * the map of the texture region names
     */
    private static  ObjectMap<TextureRegion, String> regionNames = new ObjectMap<>();
    
        private TiledMapConverter() {
    }
        /**
         * converts .tmx tiled tiled map to a texture atlas based  tiled map
         * @param oldTiledMap   the libGDX .tmx  tiled map  to generate an atlas from
         * @param mapName   the name of the map which will also be the name of the atlas
         * @return NamedTextureAtlas the texture atlas generated from the tiled map
         * @ throws MapLoadingException
         */
    public  static TiledMap convertToAtlasBasedTiledMap(TiledMap oldTiledMap, String mapName, TextureAtlas worldAtlas, String atlasName) throws MapLoadingException {
        TiledMap newTiledMap= new TiledMap();
        MapProperties oldMapProperties = oldTiledMap.getProperties();
        MapProperties newMapProperties=newTiledMap.getProperties();
        if(oldMapProperties==null ) {
            throw new MapLoadingException("Tiled Map Is Missing Properties");
        }
            newMapProperties.putAll(oldMapProperties);
           
        newMapProperties.put("atlasName", atlasName);
        MapLayers oldMapLayers = oldTiledMap.getLayers();
        if(oldMapLayers==null){
            throw new MapLoadingException("Tiled Map Has No Layers");
        }
        MapLayers newMapMapLayers = newTiledMap.getLayers();
        int layers = oldMapLayers.size();
        if (layers == 0) {
            throw new MapLoadingException("Tiled Map Has No Layers");
        }
            for (MapLayer oldLayer: oldMapLayers) {
                if(oldLayer instanceof  TiledMapTileLayer) {
                addTiledMapTileLayer(oldLayer, newMapMapLayers, worldAtlas, mapName);
            }
        }

        return newTiledMap;
    }
    /**
     * creates a new map layer from an old map layer
     * @param oldLayer the old tiled map tile layer
     * @param newMapMapLayers the new tiled MapLayers object
     * @param worldAtlas the world's texture atlas
     * @param mapName the name of the new map
     */
    private static  void addTiledMapTileLayer(MapLayer oldLayer, MapLayers newMapMapLayers, TextureAtlas worldAtlas, String mapName){
         TiledMapTileLayer   oldTiledMapTileLayer = (TiledMapTileLayer) oldLayer;
            TiledMapTileLayer newMapTiledMapTileLayer = new TiledMapTileLayer(oldTiledMapTileLayer.getWidth(), oldTiledMapTileLayer.getHeight(), (int) oldTiledMapTileLayer.getTileWidth(), (int) oldTiledMapTileLayer.getTileHeight());
            newMapTiledMapTileLayer.getProperties().putAll(oldLayer.getProperties());
            newMapMapLayers.add(newMapTiledMapTileLayer);
            int width = oldTiledMapTileLayer.getWidth();
            int height = oldTiledMapTileLayer.getHeight();
            for (int countx = 0; countx < width; countx++) {
                for (int county = 0; county < height; county++) {
                    Cell oldCell = oldTiledMapTileLayer.getCell(countx, county);
                    Cell newCell=copyCell(worldAtlas, oldCell, mapName);
                    newMapTiledMapTileLayer.setCell(countx, county, newCell);
                }
            }
    }
    /**
     * copies an TMX Tiled Map  old map cell
     * to a new  map cell for easier game serialization;
     * @param worldAtlas the world's TextureAtlas
     * @param oldCell the old map cell
     * @param mapName the nme of the new map
     * @return
     */
    private  static Cell copyCell( TextureAtlas worldAtlas, Cell oldCell, String mapName){
        if (oldCell == null) {
            return null ;
        }
        TiledMapTile oldTile = oldCell.getTile();
        if (oldTile == null) {
            return null;
        }
        TiledMapTile newTile = null;
        if (oldTile instanceof StaticTiledMapTile) {
            newTile = addStaticTileTextureToAtlas(worldAtlas, oldCell, mapName);
        } else if (oldTile instanceof AnimatedTiledMapTile) {
            newTile = addAnimatedTileTextureToAtlas(worldAtlas, oldCell, mapName);
        }
        Cell newCell = new Cell();
        newCell.setTile(newTile);
        newCell.setFlipHorizontally(oldCell.getFlipHorizontally());
        newCell.setFlipVertically(oldCell.getFlipVertically());
        newCell.setRotation(oldCell.getRotation());
        return  newCell;
    }
    /**
     *  Creates a new Tiled Map Tiled with a  Static AtlasTiledMap tile From a Cell and add its to the texture atlas
     * @param oldCell the old tiled map cell
     * @param mapName the name of the map
     * @return TiledMapTile  the new  Tiled Map Tile
     */
    private static  TiledMapTile addStaticTileTextureToAtlas( TextureAtlas worldAtlas, Cell oldCell, String mapName) {
        TextureRegion region = oldCell.getTile().getTextureRegion();
        AtlasNamedAtlasRegion atlasNamedAtlasRegion = new AtlasNamedAtlasRegion(region);
        atlasNamedAtlasRegion.setPageName(mapName + "Tiles");
        String tileName = "";
        if (!InList.isInList(worldAtlas, region)) {
            // add the atlas and give it a name
            tileName = createTileName(mapName);
            worldAtlas.addRegion(tileName, region);
            regionNames.put(region, tileName);
        } else {
            tileName = regionNames.get(region, "empty");
        }
        TiledMapTile oldCellTile = oldCell.getTile();
        TiledMapTile newTile = null;
        newTile = new AtlasStaticTiledMapTile((StaticTiledMapTile) oldCellTile, tileName);
        newTile.setTextureRegion(oldCellTile.getTextureRegion());
        return newTile;
    }
    /**
     *
     * @return creates  random name to use for a the atlas region name of tiled map tile.
     */
    private static  String createTileName(String mapName) {
        String uuid = new UID().toString();
        return mapName+uuid;
    }
    /**
     *  Creates a new  AnimatedAtlasTiledMap tile From a Cell
     * @param oldCell the old tiled map cell
     * @param mapName the name of the map
     * @return TiledMapTile  the new  Tiled Map Tile
     */
    private  static TiledMapTile addAnimatedTileTextureToAtlas( TextureAtlas worldAtlas, Cell oldCell, String mapName) {
        AnimatedTiledMapTile animatedTiledMapTile = (AnimatedTiledMapTile) oldCell.getTile();
        StaticTiledMapTile[] tiles = animatedTiledMapTile.getFrameTiles();
        int size = tiles.length;
        AtlasStaticTiledMapTile[] newTiles= new AtlasStaticTiledMapTile[size];
        for (int count = 0; count < size; count++) {
            AtlasNamedAtlasRegion atlasNamedAtlasRegion = new AtlasNamedAtlasRegion(tiles[count].getTextureRegion());
            atlasNamedAtlasRegion.setPageName(mapName + "Tiles");
            TextureRegion region = oldCell.getTile().getTextureRegion();
            String tileName = "";
            String [] names= new String [size];
            if (!InList.isInList(worldAtlas, region)) {
                // add the atlas and give it a name
                tileName = createTileName(mapName);
                worldAtlas.addRegion(tileName, region);
                regionNames.put(region, tileName);
            }
            TiledMapTile tile = oldCell.getTile();
            AtlasStaticTiledMapTile tile2 = null;
            tile2 = new AtlasStaticTiledMapTile((StaticTiledMapTile) tile, tileName);
            tile2.setTextureRegion(tile.getTextureRegion());
            newTiles[count]=tile2;
        }
        AtlasAnimatedTiledMapTile newAnimatedTiledMapTile= new AtlasAnimatedTiledMapTile( animatedTiledMapTile.getAnimationIntervals(), newTiles);
        newAnimatedTiledMapTile.setOffsetX(animatedTiledMapTile.getOffsetX());
        newAnimatedTiledMapTile.setOffsetY(animatedTiledMapTile.getOffsetY());
        animatedTiledMapTile.setBlendMode(animatedTiledMapTile.getBlendMode());
        return  newAnimatedTiledMapTile;
    }
    public ObjectMap<TextureRegion, String> getRegionNames() {
        return regionNames;
    }
}
