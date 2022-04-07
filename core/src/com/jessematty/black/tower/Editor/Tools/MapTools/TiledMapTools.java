package com.jessematty.black.tower.Editor.Tools.MapTools;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MapLoadingException;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import java.rmi.server.UID;
public final  class TiledMapTools {
    private static  ObjectMap<TextureRegion, String> regionNames = new ObjectMap<>();

        private TiledMapTools() {

    }

    /**
     * converts .tmx tiled tiled map to a texture atlas based  tiled map
     *
     * @param oldTiledMap   the libGDX .tmx  tiled map  to generate an atlas from
     * @param mapName   the name of the map which will also be the name of the atlas
     * @return NamedTextureAtlas the texture atlas generated from the tiled map
     * @ throws MapLoadingException
     */
    public  static TiledMap convertToAtlasBasedTiledMap(TiledMap oldTiledMap, String mapName, TextureAtlas worldAtlas, String atlasName) throws MapLoadingException {
        TiledMap newTiledMap= new TiledMap();
        MapProperties oldMapProperties = oldTiledMap.getProperties();
        MapProperties newMapProperties=newTiledMap.getProperties();
        if(oldMapProperties!=null) {
        newMapProperties.put("width", oldMapProperties.get("width", Integer.class));
            newMapProperties.put("height", oldMapProperties.get("height", Integer.class));
            newMapProperties.put("tilewidth", oldMapProperties.get("tilewidth", Integer.class));
            newMapProperties.put("tileheight", oldMapProperties.get("tileheight", Integer.class));
           }
        newMapProperties.put("atlasName", atlasName);
        MapLayers oldMapLayers = oldTiledMap.getLayers();
        if(oldMapLayers==null){
            throw new MapLoadingException("Tiled Map Has No Layers");
        }
        MapLayers newMapMapLayers = newTiledMap.getLayers();
        int layers = oldMapLayers.size();
        if (layers == 0) {
            return null;
        }
        TiledMapTileLayer oldTiledMapTileLayer = (TiledMapTileLayer) oldMapLayers.get(0);
        int width = oldTiledMapTileLayer.getWidth();
        int height = oldTiledMapTileLayer.getHeight();
        for (int count = 0; count < layers; count++) {
            oldTiledMapTileLayer = (TiledMapTileLayer) oldMapLayers.get(count);
            TiledMapTileLayer newMapTiledMapTileLayer = new TiledMapTileLayer(oldTiledMapTileLayer.getWidth(), oldTiledMapTileLayer.getHeight(), (int) oldTiledMapTileLayer.getTileWidth(), (int) oldTiledMapTileLayer.getTileHeight());
            newMapMapLayers.add(newMapTiledMapTileLayer);
            for (int countx = 0; countx < width; countx++) {
                for (int county = 0; county < height; county++) {
                    Cell oldCell = oldTiledMapTileLayer.getCell(countx, county);
                    if(oldCell==null){
                       continue;
                    }
                    TiledMapTile oldTile = oldCell.getTile();
                    if(oldTile==null){
                        continue;
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
                    newMapTiledMapTileLayer.setCell(countx, county, newCell);
                }
            }
        }
        return newTiledMap;
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
