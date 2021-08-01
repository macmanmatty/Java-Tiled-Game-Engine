package com.jessematty.black.tower.Maps;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.FlagComponents.AddedToEngine;
import com.jessematty.black.tower.Components.FlagComponents.NotAddedToEngine;
import com.jessematty.black.tower.Components.Position.PositionComponent;
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

/**
 * The Game World Class the has a 2d  array of game  maps that make up the world as well as the world's  texture atlas  and  ashley ecs entities
 */

public class World { // class that holds the array of maps  aka the world
        private LandMap [] [] worldMap;
        private WorldSettings worldSettings= new WorldSettings();
        private  int xMaps;
        private int yMaps;
        private String loadPath; // load path for the world used to save the game during pauses.
        private String textureAtlasPath;
        private transient GameMap currentMap;
        private OrderedMap<String, Entity> entitiesInWorld= new OrderedMap<String, Entity>(); // all of the entities currently in the world
        private   transient CraftLookUpTable craftLookUpTable;
        private  final  transient Engine engine=new PooledEngine(); //  ashley game engine
        private transient final GameComponentMapper gameComponentMapper= new GameComponentMapper(); // game compenent mapper for engine components
        private String name;
        private boolean gameInProgress;
        private  boolean newWorld =true;
        private int currentMapX;
        private int currentMapY;
        private transient Entity player;
        private String playerID;
        // the texture atlas the world uses
        private TextureAtlas worldTextureAtlas= new NamedTextureAtlas();
        // the path to the above texture atlas
        private String worldTextureAtlasPath;
    //  game  added the ashley systems in the world
    //these do NOT include the game  base systems  such as render,  die, or animation  as they should not be removed  or modified once the game has started
    private OrderedMap< Class<? extends EntitySystem>, EntitySystem> systemsInWorld = new OrderedMap<>();



        // used for  deserlization
        public World() {
            craftLookUpTable= new CraftLookUpTable(getGameComponentMapper());

        }
    /**
     *
     * @param xMaps number of maps that connect horizontally
     * @param yMaps number of maps that connect vertically
     */
        public World(int xMaps, int yMaps) {
           this(xMaps, yMaps, "world");


        }

    /**
     *
     * @param xMaps number of maps that connect horizontally
     * @param yMaps number of maps that connect vertically
     * @param name the name of the map
     */
    public World(int xMaps, int yMaps, String name) {
        createWorld(xMaps, yMaps);
        this.name = name;
        craftLookUpTable= new CraftLookUpTable(getGameComponentMapper());

    }

    /**
     *  // returns world coordinates for a tile based on the word coordinates  in relationship to all maps in the world.
     * @param tileX  the position of the tile
     * @param tileY the y position of the tile
     * @param mapX the x position of the map  in the world
     * @param mapY the y position of the map  in the world
     * @return vector2 containing the coordinates of the the tile
     */
    public Vector2 getTileWorldCoordinates(int tileX, int tileY, int mapX, int mapY){
            // returns world coordinates for a tile based on the word coordinates  in realtionship to all maps in the world.
            for(int count=0;  count<mapX; count++) {
                tileX = tileX + worldMap[count][mapY].getXTiles();
            }
            for(int count=0;  count<mapY; count++) {
                tileY = tileY + worldMap[mapX][count].getYTiles();
            }
            return new Vector2(tileX, tileY);
        }
    /**
     *  // returns world coordinates for a tile based on the word coordinates  in realtionship to all maps in the world.
     * @param screenX  the x  position of the tile
     * @param screenY the y position of the tile
     * @param mapX the x position of the map  in the world
     * @param mapY the y position of the map  in the world
     * @return vector2 containing the coordinates of the the tile
     */
        public Vector2 getScreenWorldCoordinates(float screenX,  float screenY, int mapX,  int mapY){
            // returns  world coordinates for a screen based on the word coordinates  in realtionship to all maps in the world.
            for(int count=0;  count<mapX; count++) {
                screenX = screenX + worldMap[count][mapY].getMaxXScreen();
            }
            for(int count=0;  count<mapY; count++) {
                screenY = screenY + worldMap[mapX][count].getMaxYScreen();
            }
            return new Vector2(screenX, screenY);
        }

    /**
     *  // returns a valid game map with given coordinates  if map  if coordinates ar out bounds returns the closest in bound map
     *             //if space is empty map can be null
     * @param x the x map coordinate
     * @param y the y map coordinate
     * @return
     */
        public LandMap getMap(int x, int y){ 

            if(x<0){
                x=0;
            }
            if(y<0){
                y=0;
            }
            if(y>yMaps-1){
                y=yMaps-1;
            }
            if(x>xMaps-1){
                x=xMaps-1;
            }
            return worldMap[x][y];
        }

    /**
     *             // returns a game map with given coordinates  if map
     *             if coordinates are out of  bounds returns null
     * @param x
     * @param y
     * @return GameMap
     */
    public  GameMap  getGameMapOrNull(int x, int y){
            if(x<0 || y<0 || x>xMaps-1 || y>yMaps-1){
                return null;
            }
            return worldMap[x][y];
        }

    /**
     *   // set a given array square to a LandMap instance
     * @param mapToPlace
     * @param x
     * @param y
     */
    public void placeMap(LandMap mapToPlace, int x, int y){
            GameMap currentMapAtLocation=getGameMapOrNull(x, y);
            if(currentMapAtLocation==null) { // no map exists place the map
                if (mapToPlace != null) {
                   setMap(x, y, mapToPlace); 
                }
                
                return; 
            }
            else{ // remove current map and place new one
               removeMap(x, y); 
               setMap(x, y, mapToPlace);
                
            }
            
            
        }

    /**
     *  adds a land map to the world position x, y  if a map exists a`t given location replaces it
     * @param x the location of the  map
     * @param y the y location of the map
     * @param map the land  map to place
     */
        private  void setMap( int x, int y, LandMap map){
            worldMap[x][y] = map;
            map.setWorldLocation(x, y);



            
        }
    /**
     *  removes a land map to the world position x, y  by setting it to null
     * @param x the location of the  map
     * @param y the y location of the map
     *
     */
        
        private void removeMap(int x, int y){
             LandMap map =worldMap[x][y];

            worldMap[x][y]=null;
        }
        public LandMap[][] getWorldMap() {
            return worldMap;
        }
        public int getXMaps() {
            return xMaps;
        }
        public int getYMaps() {
            return yMaps;
        }
    public void deserialize(GameAssets assetts, GamePrefecences gameStartSettingsObject) {
    }
    public String getLoadPath() {
        return loadPath;
    }
    public void setLoadPath(String loadPath) {
        this.loadPath = loadPath;
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
     * sets a  given map at location x, y as the current map and add its entities  to the engine
     * @param x
     * @param y
     */
    public void setCurrentMap(int x, int y) {
            if(currentMap!=null){
                removeTilesFromEngine(currentMap);
                removeEntitiesFromEngine(currentMap.getEntities());
                removeSystemsFromEngine(currentMap.getMapGameEntitySystemsClasses());

            }
        this.currentMap = worldMap[x][y];
        this.currentMapX=currentMap.getWorldX();
        this.currentMapY=currentMap.getWorldY();
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
     * sets a  given map at location x, y as the start map for the player
     * @param x
     * @param y
     */
    
    public void setStartMap(int x, int y){
            //sets the start map
            worldSettings.getSettings().put("startMapX", x);
        worldSettings.getSettings().put("startMapY", y);
            if(currentMap==null) {
                setCurrentMap(x, y);
            }
            
    }
    public GameMap getStartMap(){
            //gets the start map
            if(currentMap!=null){
                return  currentMap;
            }
            
            currentMap=worldMap[0][0];
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
           GameMap map= getGameMapOrNull(position.getMapWorldLocationX(), position.getMapWorldLocationY());
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
        GameMap map= getGameMapOrNull(position.getMapWorldLocationX(), position.getMapWorldLocationY());
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
    public OrderedMap<String, Entity> getEntitiesInWorld() {
        return entitiesInWorld;
    }
    public GameComponentMapper getGameComponentMapper() {
        return gameComponentMapper;
    }

    /**
     *  creates a new world 2d array ox x maps by y maps
     * @param xMaps
     * @param yMaps
     */
    public void createWorld(int xMaps, int yMaps) {
            this.xMaps=xMaps;
            this.yMaps=yMaps;
            worldMap= new LandMap[xMaps][yMaps];
            newWorld =false;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTextureAtlasPath() {
        return textureAtlasPath;
    }
    public void setTextureAtlasPath(String textureAtlasPath) {
        this.textureAtlasPath = textureAtlasPath;
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

    public void setWorldMap(LandMap[][] worldMap) {
        this.worldMap = worldMap;
        this.xMaps=worldMap.length;
        this.yMaps=worldMap[0].length;

    }

    public int getCurrentMapX() {
        return currentMapX;
    }

    public int getCurrentMapY() {
        return currentMapY;
    }

    public int getxMaps() {
        return xMaps;
    }

    public int getyMaps() {
        return yMaps;
    }

    public Entity getPlayer() {
        return player;
    }

    public void setPlayer(Entity player) {
        this.player = player;
        this.playerID=getGameComponentMapper().getIdComponentMapper().get(player).getId();

    }

    public TextureAtlas getWorldTextureAtlas() {
        return worldTextureAtlas;
    }

    public void setWorldTextureAtlas(TextureAtlas worldTextureAtlas, String path) {
        this.worldTextureAtlas = worldTextureAtlas;
        worldSettings.getSettings().put("assetsPath", path);

    }

    public String getWorldTextureAtlasPath() {
        return worldTextureAtlasPath;
    }



    public WorldSettings getWorldSettings() {
        return worldSettings;
    }

    /**
     *  adds a game entity  system  to the engine
     * @param system
     * @param addToEngine
     */

    public void addSystem(EntitySystem system, boolean addToEngine){
        systemsInWorld.put( system.getClass(), system);
        if(addToEngine){
            engine.addSystem(system);
        }



    }

    public void removeSystem(GameEntitySystem system, boolean addToEngine) {
        systemsInWorld.remove(system.getClass());
            engine.removeSystem(system);


    }

    public OrderedMap<Class<? extends EntitySystem>, EntitySystem> getSystemsInWorld() {
        return systemsInWorld;
    }

    public void setWorldSettings(WorldSettings worldSettings) {
        this.worldSettings = worldSettings;
    }
}
