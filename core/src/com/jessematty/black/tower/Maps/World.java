package com.jessematty.black.tower.Maps;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.FlagComponents.AddedToEngine;
import com.jessematty.black.tower.Components.FlagComponents.NotAddedToEngine;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MapLoadingException;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.NamedTextureAtlas;
import com.jessematty.black.tower.GameBaseClasses.Crafting.Craft;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Settings.GameSettings.GamePrefecences;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Crafting.LookUpTables.CraftLookUpTable;
import com.jessematty.black.tower.Maps.Settings.WorldSettings;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Systems.GameEntitySystem;

import org.omg.CORBA.Object;

import java.rmi.server.UID;
import java.util.UUID;

/**
 * The Game World Class the has a 2d  array of game  maps that make up the world as well as the world's  texture atlas  and  ashley ecs entities
 */
public class World implements Disposable {

    /**
     *                                 PLEASE READ!!!
     * All fields in this class are serialized by a custom Kryo Serializer
     * @linked {WorldKryoSerializer} any fields NOT marked as Transient will be serialized by kryo
     * and then read upon deserialization  THE ORDER OF this classes  fields must the same as the order in
     * the WorldKryoSerializer classes  or the game will not save properly!!!
     */

    /**
     * the object map of land maps aka the world the key LandMaps id the value is the land map
     */
    private ObjectMap <String, LandMap> worldMap = new ObjectMap<>();
    /**
     * class that holds all the setting for the world
     */
    private WorldSettings worldSettings= new WorldSettings();
    /**
     * the current map displayed on the screen usually the one the player is in
     */
    private transient GameMap currentMap;
    /**
     * the map entities in the world  teh key is the entity's id
     */
    private ObjectMap<String, Entity> entitiesInWorld= new ObjectMap<String, Entity>(); // all of the entities currently in the world
    private   transient CraftLookUpTable craftLookUpTable;
    /**
     * the games engine
     */
    private  final  transient Engine engine=new PooledEngine(); //  ashley game engine
    /**
     *  the game component mapper class
     */
    private transient final GameComponentMapper gameComponentMapper= new GameComponentMapper();
    /**
     * the name of the world
     */
    private String name;
    /**
     * the game currently running
     */
        private boolean gameInProgress;
        private  boolean newWorld =true;
    /**
     * the games player
     */
    private transient Entity player;
    /**
     * object for calculating who much time has  passed in the game
     */
        private GameTime gameTime= new GameTime();
    /**
     *     the texture atlas the world uses
      */
    private transient TextureAtlas worldTextureAtlas= new NamedTextureAtlas();

    /**  custom added the ashley systems in the world these do NOT include the game  base systems  such as render,
     die, or animation  as tthose should not be removed  or modified once the game has started
     */
    private OrderedMap< Class<? extends EntitySystem>, EntitySystem> systemsInWorld = new OrderedMap<>();
        // used for  deserialization
        public World() {
            craftLookUpTable= new CraftLookUpTable(getGameComponentMapper());
        }
    /**
     *
     * @param name the name of the world
     */
    public World( String name) {
        createWorld();
        this.name = name;
        craftLookUpTable= new CraftLookUpTable(getGameComponentMapper());
    }

    /**
     *  // returns a valid game map with given coordinates  if map  if coordinates ar out bounds returns the closest in bound map
     *             //if space is empty map can be null
     * @param mapId the ID of the  map
     e
     * @return
     */
        public LandMap getMap(String mapId){
            return worldMap.get(mapId);
        }
    /**
     *  adds a land map to map of land maps  and gives it a unique id
     * @param map the land  map to place
     */
    public void addMap(LandMap map){
            worldMap.put(map.getId(), map);
            
        }
    /**
     *  removes a land map from the world array of maps
     * @param id the id of the map to remove
     *
     */

    protected void removeMap(String id){
        worldMap.remove(id);

        }
        public ObjectMap<String, LandMap> getWorldMaps() {
            return worldMap;
        }
    public void deserialize(GameAssets assetts, GamePrefecences gameStartSettingsObject) {
    }

    public GameMap getCurrentMap() {
        return currentMap;
    }
    /**
     * adds a libGDX Array Of Entities to the map
     * @param entities
     */
    private void addEntitiesToEngine(Array<Entity> entities){
            int size=entities.size;
            for(int count=0; count<size; count++){
                addEntityToEngine(entities.get(count));
            }
    }
    /**
     * removes a libGDX Array Of Entities from the map
     * @param entities
     */
    private void removeEntitiesFromEngine(Array<Entity> entities){
        int size=entities.size;
        for(int count=0; count<size; count++){
            removeEntityFromEngine(entities.get(count));
        }
    }
    /**
     * sets a  given map  as the current map and add its entities  to the engine
     */
    public void setCurrentMap(String mapId) {
            if(currentMap!=null){
                removeTilesFromEngine(currentMap);
                removeEntitiesFromEngine(currentMap.getEntities());
                removeSystemsFromEngine(currentMap.getMapGameEntitySystemsClasses());
            }
        this.currentMap = worldMap.get(mapId);
        addTilesToEngine(currentMap);
        addEntitiesToEngine(currentMap.getEntities());
        addSystemsToEngine(currentMap.getMapGameEntitySystemsClasses());
    }
    /**
     * adds the maps  ashley entity systems to the game  engine's entity   systems
     * @param mapGameEntitySystemsClasses // array holding the classes of the systems to add
     */
    private void addSystemsToEngine(Array<Class<? extends GameEntitySystem>> mapGameEntitySystemsClasses) {
        int size=mapGameEntitySystemsClasses.size;
        for(int count=0; count<size; count++){
            engine.addSystem(systemsInWorld.get(mapGameEntitySystemsClasses.get(count)));
        }
    }
    /**
     * removes the maps  ashley entity systems from the game  engine's entity   systems
     * @param mapGameEntitySystemsClasses // array holding the classes of the systems to remove
     */
    private void removeSystemsFromEngine(Array<Class<? extends GameEntitySystem>> mapGameEntitySystemsClasses) {
        int size=mapGameEntitySystemsClasses.size;
        for(int count=0; count<size; count++){
            engine.removeSystem(systemsInWorld.get(mapGameEntitySystemsClasses.get(count)));
        }
    }
    /**
     * sets a  given map at  as the start map for the player
     * @param mapId

     */
    
    public void setStartMap(String mapId){
            //sets the start map
            worldSettings.getSettings().put("startMapId", mapId);
            if(currentMap==null) {
                setCurrentMap(mapId);
            }
            
    }
    public GameMap getStartMap(){
            //gets the start map
            if(currentMap!=null){
                return  currentMap;
            }
            if(worldMap.size!=0) {
                currentMap = worldMap.values().next();
            }
            return currentMap;
    }
    public Entity getEntity(String id ){
            if(id==null || id.isEmpty()){
                return  null;
            }
            return entitiesInWorld.get(id);
    }
    /**
     * // adds entity to ashley engine
     * @param entity entity to add
     */
    private void addEntityToEngine(Entity entity){
       NotAddedToEngine notAddedToEngine= gameComponentMapper.getNotAddedToEngineComponentMapper().get(entity);
        AddedToEngine addedToEngine= gameComponentMapper.getAddedToEngineComponentMapper().get(entity);
       // if entity has the not add to engine marker  so don't add it the engine return instead
       if(notAddedToEngine!=null) {
           return;
       }
           // entity has been added to engine return no need to add
           if(addedToEngine!=null) {
               return;
           }
               engine.addEntity(entity);
               entity.add(new AddedToEngine());
    }
    /**
     * // adds an entity to a game map
     * @param entity
     * @param position
     */
    public void addEntityToMap(Entity entity, PositionComponent position){
           GameMap map= getMap(position.getMapId());
           if(map==null){
               return;
           }
           map.addEntity( entity);
    }
    /**
     * // adds entity to the world, and if applicable the engine and   and the map  and tiles stored in its position
     * @param entity the entity to add
     */
       public void addEntityToWorld(Entity entity) {
        PositionComponent position=gameComponentMapper.getPositionComponentMapper().get(entity);
           ID id= gameComponentMapper.getIdComponentMapper().get(entity);
          // if entity has no id give it one
           if(id==null){
               id= new ID();
               entity.add(id);
           }
           // add entity to entity map
           entitiesInWorld.put(id.getId(), entity);
           addEntityToEngine(entity);
        if (position!=null) { // has  position add entity to map
            addEntityToMap(entity, position);
        }
    }
    /**
     *   adds a bag of entities  to the world, engine, and the map stored in its position
     * @param entityBag the list on entities to add to the world
     */
    public void addEntityToWorld(EntityBag entityBag) {
        Array<Entity> entities=entityBag.getEntities();
        int size=entities.size;
        for(int count=0; count<size; count++) {
            Entity entity=entities.get(count);
            addEntityToWorld(entity);
        }
    }
    /**
     *      // removes an entity from the engine
     * @param entity the entity to remove
     */
    private void removeEntityFromEngine(Entity entity){
        engine.removeEntity(entity);
    }
    public void removeEntityFromMap(Entity entity, PositionComponent position){
        GameMap map= getMap(position.getMapId());
        if(map==null){
            return;
        }
        map.removeEntity(  entity);
    }
    /**
     *   adds an antity   to the world, engine, and the map stored in its position
     * @param entity the entity to add to the world
     */    public void removeEntityFromWorld(Entity entity){
        PositionComponent position=gameComponentMapper.getPositionComponentMapper().get(entity);
        removeEntityFromEngine(entity);
        removeEntityFromMap(entity, position);
        entitiesInWorld.remove(gameComponentMapper.getIdComponentMapper().get(entity).getId());
    }
    /**
     *    adds the game map tiles to the engine
     * @param gameMap the game map with tiles to add
     */
    private  void addTilesToEngine(GameMap gameMap){
            LandSquareTile[] [] tileMap=gameMap.getMap();
        for (int countx=0; countx<tileMap.length; countx++) {
            for (int county = 0; county < tileMap[0].length; county ++) {
                engine.addEntity(tileMap[countx][county]);
            }
        }
    }
    /**
     *    removes  the game map tiles from  the engine
     * @param gameMap the game map with tiles to remove
     */
    private  void removeTilesFromEngine( GameMap gameMap){
        LandSquareTile[] [] tileMap=gameMap.getMap();
        for (int countx=0; countx<tileMap.length; countx++) {
            for (int county = 0; county < tileMap[0].length; county ++) {
                engine.removeEntity(tileMap[countx][county]);
            }
        }
    }
    public Engine getEngine() {
        return engine;
    }
    public ObjectMap<String, Entity> getEntitiesInWorld() {
        return entitiesInWorld;
    }
    public GameComponentMapper getGameComponentMapper() {
        return gameComponentMapper;
    }
    /**
     *  creates a new world

     */
    public void createWorld() {
            newWorld =false;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isGameInProgress() {
        return gameInProgress;
    }
    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }
    public boolean isNewWorld() {
        return newWorld;
    }
    @Override
    public String toString() {
        return name;
    }
    public Array<Entity> getEntityFromCraft(Craft craft){
            return craftLookUpTable.lookUpCraft(craft);
    }
    public void setWorldMap(ObjectMap<String, LandMap> worldMap) {
        this.worldMap = worldMap;
    }
    public Entity getPlayer() {
        return player;
    }
    public void setPlayer(Entity player) {
        this.player = player;
    }
    public TextureAtlas getWorldTextureAtlas() {
        return worldTextureAtlas;
    }
    public void setWorldTextureAtlas(TextureAtlas worldTextureAtlas, String path) {
        this.worldTextureAtlas = worldTextureAtlas;
        worldSettings.getSettings().put("assetsPath", path);
    }
    public WorldSettings getWorldSettings() {
        return worldSettings;
    }
    /**
     *  adds a game entity  system  to the engine
     * @param system
     */
    public void addSystem(EntitySystem system, boolean addToEngine){
        systemsInWorld.put( system.getClass(), system);
        if(addToEngine){
            engine.addSystem(system);
        }
    }
    public void removeSystem(GameEntitySystem system) {
        systemsInWorld.remove(system.getClass());
            engine.removeSystem(system);
    }
    public OrderedMap<Class<? extends EntitySystem>, EntitySystem> getSystemsInWorld() {
        return systemsInWorld;
    }
    public void setWorldSettings(WorldSettings worldSettings) {
        this.worldSettings = worldSettings;
    }
    public GameTime getGameTime() {
        return gameTime;
    }
    @Override
    public void dispose() {
        worldTextureAtlas.dispose();
    }
}
