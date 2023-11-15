package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MapLoadingException;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGenerator;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGeneratorDTO;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class TiledMapGameLoader {
    private LPCObjectGenerator lpcObjectGenerator;
    private GameAssets assets;
    private World world;
    private Array<EntityBag> entityBagArray = new Array<>();
    public TiledMapGameLoader(GameAssets gameAssets, World world) {
        lpcObjectGenerator = new LPCObjectGenerator(gameAssets);
        this.assets = gameAssets;
        this.world = world;

    }

    public TiledMapGameLoader(GameAssets gameAssets) {
        lpcObjectGenerator = new LPCObjectGenerator(gameAssets);
        this.assets = gameAssets;
        this.world = new World();
    }

    /**
     * loads an array of tiled maps
     * into the game
     * @param tiledMaps
     */
    public void createGameFromTmxMaps(Array<TiledMap> tiledMaps) throws MapLoadingException { // loads tiles TMXTileMap from a given file path
                for(TiledMap map: tiledMaps){
               GameMap gameMap= createMapFromTmxMap(map);
                }
    }
    public GameMap  createMapFromTmxMap(TiledMap tiledMap) throws MapLoadingException { // loads tiles TMXTileMap from a given file path
        MapProperties mapProperties = tiledMap.getProperties();
        String objectGeneratorDTOPath = mapProperties.get("objectDTOPath", String.class);
        String name = mapProperties.get("mapName", String.class);
        ObjectMap<String, LPCObjectGeneratorDTO> generatorDTOObjectMap = lpcObjectGenerator.generateObjectDTOMap(objectGeneratorDTOPath);
        Array<EntityBag> entityBags = new Array<>();
        int width = mapProperties.get("width", java.lang.Integer.class);
        int height = mapProperties.get("height", Integer.class);
        int tileSizeX = mapProperties.get("tilewidth", Integer.class);
        int tileSizeY = mapProperties.get("tileheight", Integer.class);
        Float gravity = mapProperties.get("gravity", java.lang.Float.class);
        if (gravity == null) {
            gravity=9.8f;
        }
       GameMap gameMap= createLandMap(width, height, tileSizeX, tileSizeY , gravity, name);
        world.addMap(gameMap);
        MapLayers mapLayers = tiledMap.getLayers();
        if(mapLayers.size()==0){
            throw new MapLoadingException("Map Has no Layers");
        }
        for (MapLayer mapLayer : mapLayers) {
        loadMapObjects(entityBags, generatorDTOObjectMap, mapLayer.getObjects());
        }
        return gameMap;
    }

   public LandMap createLandMap(int mapWidth, int mapHeight, int tileSizeX, int tileSizeY,  float gravity, String name ) {
       LandMap map = new LandMap();
       LandSquareTile[][] tiles = new LandSquareTile[mapWidth][mapHeight];
       for (int countx = 0; countx < mapWidth; countx++) {
           for (int county = 0; county < mapHeight; county++) {
               tiles[countx][county] = new LandSquareTile(countx, county, mapHeight);
           }
       }
       map.setGravity(gravity);
       map.setTileSize(tileSizeX, tileSizeY);
       map.setMapName(name);
       return  map;
   }

    private Array<EntityBag> loadMapObjects (Array<EntityBag> entityBags, ObjectMap<String, LPCObjectGeneratorDTO> lpcObjectGeneratorObjectMap, MapObjects mapObjects) {
        for (MapObject mapObject : mapObjects) {
            MapProperties properties = mapObject.getProperties();
            float x=properties.get("X" , float.class);
            float y=properties.get("Y" , float.class);
            String tmxObjectID = properties.get("objectId", String.class);
            EntityBag entityBag = lpcObjectGenerator.generateEntity(lpcObjectGeneratorObjectMap.get(tmxObjectID));
            Entity entity=   entityBag.getOwner();
            PositionComponent positionComponent= GameComponentMapper.getPositionComponentMapper().get(entity);
            positionComponent.setPosition(x,y);
            entityBags.add(entityBag);
            world.addEntityToWorld(entityBag);
        }
        return entityBags;
    }

    public void createEntity(  Array<EntityBag> entityBags,  ObjectMap<String, LPCObjectGeneratorDTO> lpcObjectGeneratorObjectMap,  MapProperties properties){
        float x=properties.get("X" , float.class);
        float y=properties.get("Y" , float.class);
        String tmxObjectID = properties.get("objectId", String.class);
        EntityBag entityBag = lpcObjectGenerator.generateEntity(lpcObjectGeneratorObjectMap.get(tmxObjectID));
        Entity entity=   entityBag.getOwner();
        PositionComponent positionComponent= GameComponentMapper.getPositionComponentMapper().get(entity);
        positionComponent.setPosition(x,y);
        entityBags.add(entityBag);
    }

    public void createTile(  GameMap map,  Array<EntityBag> entityBags,  ObjectMap<String, LPCObjectGeneratorDTO> lpcObjectGeneratorObjectMap,  MapProperties properties){
        float x=properties.get("X" , float.class);
        float y=properties.get("Y" , float.class);
        String tmxObjectID = properties.get("objectId", String.class);
        LandSquareTile tiler = lpcObjectGenerator.generateTile(lpcObjectGeneratorObjectMap.get(tmxObjectID));
        LandSquareTile tile=map.getTileFromWorldUnitCoordinates(x, y);
        map.getMap()[tile.getLocationX()][tile.getLocationY()]= tile;
    }
}