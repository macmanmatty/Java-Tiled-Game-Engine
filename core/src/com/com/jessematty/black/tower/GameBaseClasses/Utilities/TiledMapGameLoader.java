package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.Entity.EntityLoadingException;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MapLoadingException;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasAnimatedTiledMapTile;
import com.jessematty.black.tower.GameBaseClasses.TiledMapTileChangable.AtlasStaticTiledMapTile;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGenerator;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.LPCObjectGeneratorDTO;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.rmi.server.UID;

public class TiledMapGameLoader {
    /**
     * the lpc object generator
     */
    private LPCObjectGenerator lpcObjectGenerator;
    /**
     * the game assets object
     */
    private GameAssets assets;
    /**
     * the game world object
     */
    private World world;
    /**
     * a map of loaded entities in the world
     * where  the key is the tmxObjectID+ the X and Y
     * position of the entity on the tmx Map.
     */
    private ObjectMap<String, EntityBag> entityBagArray = new ObjectMap<>();
    /**
     * array of loaded texture atlases from the Entity generator(s)
     */
    private Array<TextureAtlas> textureAtlases= new Array<TextureAtlas>();

    /**
     * creates a new TiledMaGameLoader  object from a existing world
     * @param gameAssets the game assets object
     * @param world the game world
     */
    public TiledMapGameLoader(GameAssets gameAssets, World world) {
        lpcObjectGenerator = new LPCObjectGenerator(gameAssets);
        this.assets = gameAssets;
        this.world = world;
        assets.setCurrentTextureAtlas(world.getWorldTextureAtlas());

    }
    /**
     * creates a new TiledMaGameLoader  object with  a new  world
     * @param gameAssets the game assets object
     */
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
    public  void createGameFromTmxMaps(Array<TiledMap> tiledMaps) throws MapLoadingException, EntityLoadingException { // loads tiles TMXTileMap from a given file path
                for(TiledMap map: tiledMaps){
               GameMap gameMap= createMapFromTmxMap(map);
                }
    }


    /**
     * the map of the texture region names
     */
    private static  ObjectMap<TextureRegion, String> regionNames = new ObjectMap<>();

    /**
     * converts .tmx tiled tiled map to a texture atlas based  tiled map
     * @param oldTiledMap   the libGDX .tmx  tiled map  to generate an atlas from
     * @param mapName   the name of the map which will also be the name of the atlas
     * @return NamedTextureAtlas the texture atlas generated from the tiled map
     * @ throws MapLoadingException
     */
    private    TiledMap convertToAtlasBasedTiledMap( TiledMap newTiledMap, TiledMap oldTiledMap, String mapName, TextureAtlas worldAtlas, String atlasName) throws MapLoadingException {
        MapProperties oldMapProperties = oldTiledMap.getProperties();
        MapProperties newMapProperties=newTiledMap.getProperties();
        newMapProperties.putAll(oldMapProperties);
        newMapProperties.put("atlasName", atlasName);
        MapLayers oldMapLayers = oldTiledMap.getLayers();
        MapLayers newMapMapLayers = newTiledMap.getLayers();
        int layers = oldMapLayers.size();
        if (layers == 0) {
            throw new MapLoadingException("Tiled Map Has No Layers");
        }
        for (MapLayer oldLayer: oldMapLayers) {
            if(oldLayer instanceof TiledMapTileLayer) {
                addTiledMapTileLayer(oldLayer, newMapMapLayers, worldAtlas, mapName);
            }
        }

        return newTiledMap;
    }
    /**
     * creates a new map layer from an old map layer
     * @param oldLayer the old tiled map tile layer
     * @param newMapMapLayers the new tiled MapLayers object
     * @param mapAtlas the world's texture atlas
     * @param mapName the name of the new map
     */
    private static  void addTiledMapTileLayer(MapLayer oldLayer, MapLayers newMapMapLayers, TextureAtlas mapAtlas, String mapName){
        TiledMapTileLayer   oldTiledMapTileLayer = (TiledMapTileLayer) oldLayer;
        TiledMapTileLayer newMapTiledMapTileLayer = new TiledMapTileLayer(oldTiledMapTileLayer.getWidth(), oldTiledMapTileLayer.getHeight(), (int) oldTiledMapTileLayer.getTileWidth(), (int) oldTiledMapTileLayer.getTileHeight());
        newMapTiledMapTileLayer.getProperties().putAll(oldLayer.getProperties());
        newMapMapLayers.add(newMapTiledMapTileLayer);
        int width = oldTiledMapTileLayer.getWidth();
        int height = oldTiledMapTileLayer.getHeight();
        for (int countx = 0; countx < width; countx++) {
            for (int county = 0; county < height; county++) {
                TiledMapTileLayer.Cell oldCell = oldTiledMapTileLayer.getCell(countx, county);
                TiledMapTileLayer.Cell newCell=copyCell(mapAtlas, oldCell, mapName);
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
    private  static TiledMapTileLayer.Cell copyCell(TextureAtlas worldAtlas, TiledMapTileLayer.Cell oldCell, String mapName){
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
        TiledMapTileLayer.Cell newCell = new TiledMapTileLayer.Cell();
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
    private static  TiledMapTile addStaticTileTextureToAtlas(TextureAtlas worldAtlas, TiledMapTileLayer.Cell oldCell, String mapName) {
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
    private  static TiledMapTile addAnimatedTileTextureToAtlas(TextureAtlas worldAtlas, TiledMapTileLayer.Cell oldCell, String mapName) {
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

    /**
     * creates a GameMap from a tiled Map converting the old Tiled Map to  a new  texture atlas based tiled map
     *  and converting the objects to entities and tiles. 
     * @param tiledMap the Tiled TMX map to create a GameMap From
     * @return The Created Game Map
     * @throws MapLoadingException
     */
    public GameMap  createMapFromTmxMap(TiledMap tiledMap) throws MapLoadingException, EntityLoadingException { // loads tiles TMXTileMap from a given file path
        MapProperties mapProperties = tiledMap.getProperties();
        String objectGeneratorDTOPath = mapProperties.get("objectDTOPath", String.class);
        String name = mapProperties.get("mapName", String.class);
        ObjectMap<String, LPCObjectGeneratorDTO> generatorDTOObjectMap = lpcObjectGenerator.generateObjectDTOMap(objectGeneratorDTOPath);
        Array<EntityBag> entityBags = new Array<>();
        Integer width = mapProperties.get("width", java.lang.Integer.class);
        Integer height = mapProperties.get("height", Integer.class);
        Integer tileSizeX = mapProperties.get("tilewidth", Integer.class);
        Integer tileSizeY = mapProperties.get("tileheight", Integer.class);
        Float gravity = mapProperties.get("gravity", java.lang.Float.class);
        checkWidthHeight(width, height, tileSizeX, tileSizeY);
        if (gravity == null) {
            gravity=9.8f;
        }
       GameMap gameMap= createLandMap(width, height, tileSizeX, tileSizeY , gravity, name);
        TiledMap newTiledMap=new TiledMap();
        MapProperties newMapProperties=newTiledMap.getProperties();
        newMapProperties.putAll(tiledMap.getProperties());
        newMapProperties.put("atlasName", world.getWorldSettings().getSimpleSetting("textureAtlasPath", String.class));

        MapLayers newMapLayers=newTiledMap.getLayers();
        gameMap.setTiledMap(newTiledMap);
        world.addMap(gameMap);
        MapLayers mapLayers = tiledMap.getLayers();
        if(mapLayers.size()==0){
            throw new MapLoadingException("Map Has no Layers");
        }
        for (MapLayer mapLayer : mapLayers) {
        loadMapObjects(gameMap.getId(), entityBags, generatorDTOObjectMap, mapLayer.getObjects());
        if(mapLayer instanceof  TiledMapTileLayer){
            addTiledMapTileLayer(mapLayer,newMapLayers, world.getWorldTextureAtlas(), gameMap.getMapName());
        }
        }
        return gameMap;
    }
    
    private void checkWidthHeight(Integer width, Integer height, Integer tileWidth, Integer tileHeight ) throws MapLoadingException {
        if(width==null){
            throw new MapLoadingException("Tiled Map Width is Missing");
        }
        if(width.equals(0)){
            throw new MapLoadingException(" Tiled Map Width Must Greater Than Zero");
        }
        if(height==null){
            throw new MapLoadingException("Tiled Map Height is Missing");
        }
        if(height.equals(0)){
            throw new MapLoadingException(" Tiled Map Height Must Greater Than Zero");
        }
        if(tileWidth==null){
            throw new MapLoadingException("Tiled Map  Tile Width is Missing");
        }
        if(tileWidth.equals(0)){
            throw new MapLoadingException(" Tiled Map Tile Width Must Greater Than Zero");
        }
        if(tileHeight==null){
            throw new MapLoadingException("Tiled Map Tile Height is Missing");
        }
        if(tileHeight.equals(0)){
            throw new MapLoadingException(" Tiled Map Tile Height Must Greater Than Zero");
        }
    }


   private LandMap createLandMap(int mapWidth, int mapHeight, int tileSizeX, int tileSizeY,  float gravity, String name ) {
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
       map.setMap(tiles);
       return  map;
   }

    private Array<EntityBag> loadMapObjects (String mapId, Array<EntityBag> entityBags, ObjectMap<String, LPCObjectGeneratorDTO> lpcObjectGeneratorObjectMap, MapObjects mapObjects) throws EntityLoadingException {
        for (MapObject mapObject : mapObjects) {
            MapProperties properties = mapObject.getProperties();
            Float  x=properties.get("x" , float.class);
            Float y=properties.get("y" , float.class);
            String entityId=properties.get("entityId", String.class);

            String tmxObjectID = properties.get("tmxObjectID", String.class);
            if(tmxObjectID==null) {
                continue;
            }

            LPCObjectGeneratorDTO lpcObjectGeneratorDTO = lpcObjectGeneratorObjectMap.get(tmxObjectID);
            if(lpcObjectGeneratorDTO==null){
                continue;
            }

            String textureAtlasPath=lpcObjectGeneratorDTO.getAtlasName();
            TextureAtlas textureAtlas= getTextureAtlas(textureAtlasPath);
            textureAtlases.add(textureAtlas);
          //  lpcObjectGeneratorDTO.setAtlasName(world.getWorldSettings().getSimpleSetting("textureAtlasPath", String.class));
            EntityBag entityBag=null;
            if(entityId!=null){
                entityBag = lpcObjectGenerator.generateEntity(lpcObjectGeneratorDTO, entityId);

            }
            else {
                entityBag = lpcObjectGenerator.generateEntity(lpcObjectGeneratorDTO);
            }
            Entity entity=entityBag.getOwner();
            PositionComponent positionComponent= GameComponentMapper.getPositionComponentMapper().get(entity);
            positionComponent.setPosition(x,y);
            positionComponent.setMapID(mapId);
            entityBags.add(entityBag);
            world.addEntityToWorld(entityBag);
            String id=GameComponentMapper.getIdComponentMapper().get(entity).getId();
            if(entityBagArray.get(id)!=null){
                throw new EntityLoadingException("Duplicate Entity Id: "+id+" detected  please check your entity ids");
            }
            entityBagArray.put(id,  entityBag);
        }
        return entityBags;
    }



    public TextureAtlas getTextureAtlas(String textureAtlasPath){
        TextureAtlas textureAtlas=null;
        if(!assets.getAssetManager().isLoaded(textureAtlasPath)) {
            textureAtlas = assets.loadInternalTextureAtlas(textureAtlasPath);
        }
        else{
            textureAtlas=assets.getAssetManager().get(textureAtlasPath);
        }
        textureAtlases.add(textureAtlas);
        if(!InList.isInList(textureAtlases, textureAtlas)) {
            TextureAtlas worldTextureAtlas = world.getWorldTextureAtlas();
            worldTextureAtlas.getRegions().addAll(textureAtlas.getRegions());
            worldTextureAtlas.getTextures().addAll(textureAtlas.getTextures());
        }



        return textureAtlas;
    }

    private  void createEntity(  Array<EntityBag> entityBags,  ObjectMap<String, LPCObjectGeneratorDTO> lpcObjectGeneratorObjectMap,  MapProperties properties){
        float x=properties.get("X" , float.class);
        float y=properties.get("Y" , float.class);
        String tmxObjectID = properties.get("objectId", String.class);
        EntityBag entityBag = lpcObjectGenerator.generateEntity(lpcObjectGeneratorObjectMap.get(tmxObjectID));
        Entity entity=   entityBag.getOwner();
        PositionComponent positionComponent= GameComponentMapper.getPositionComponentMapper().get(entity);
        positionComponent.setPosition(x,y);
        entityBags.add(entityBag);
    }

    private void createTile(  GameMap map,  Array<EntityBag> entityBags,  ObjectMap<String, LPCObjectGeneratorDTO> lpcObjectGeneratorObjectMap,  MapProperties properties){
        float x=properties.get("X" , float.class);
        float y=properties.get("Y" , float.class);
        String tmxObjectID = properties.get("objectId", String.class);
        LandSquareTile tiler = lpcObjectGenerator.generateTile(lpcObjectGeneratorObjectMap.get(tmxObjectID));
        LandSquareTile tile=map.getTileFromWorldUnitCoordinates(x, y);
        map.getMap()[tile.getLocationX()][tile.getLocationY()]= tile;
    }

    public ObjectMap<String, EntityBag> getEntityBagArray() {
        return entityBagArray;
    }

}