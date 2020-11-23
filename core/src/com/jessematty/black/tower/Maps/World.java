package com.jessematty.black.tower.Maps;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Markers.AddedToEngine;
import com.jessematty.black.tower.Components.Markers.NotAddedToEngine;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.NamedTextureAtlas;
import com.jessematty.black.tower.GameBaseClasses.Crafting.Craft;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameSettings.GameSettings;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Crafting.LookUpTables.CraftLookUpTable;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
public class World { // class that holds the array of maps  aka the world
        private LandMap [] [] worldMap;
        private GameSettings gameStartSettings;
        private  int xMaps;
        private int yMaps;
        private String loadPath; // load path for the world used to save the game during pauses.
        private String textureAtlasPath;
        private transient GameMap currentMap;
        private  int startMapX;
        private int startMapY;
        private OrderedMap<String, Entity> entitiesInWorld= new OrderedMap<String, Entity>(); // all of the entities currently in the world
        private   transient CraftLookUpTable craftLookUpTable;
        private  final  transient Engine engine=new PooledEngine(); //  ashley game engine
        private transient final GameComponentMapper gameComponentMapper= new GameComponentMapper(); // game compenent mapper for engine components
        private String name;
        private boolean gameInProgress;
        private  boolean newWorld =true;
        private int currentMapX;
        private int currentMapY;
        private Entity player;
        private String playerID;
        // the texture atlas the world uses
        private NamedTextureAtlas worldTextureAtlas;
        // the path to the above texture atlas
        private String worldTextureAtlasPath;

        // used for  deserlization
        public World() {
            craftLookUpTable= new CraftLookUpTable(getGameComponentMapper());

        }
        public World(int xMaps, int yMaps) {
           this(xMaps, yMaps, "world");


        }
    public World(int xMaps, int yMaps, String name) {
        createWorld(xMaps, yMaps);
        this.name = name;
        gameStartSettings= new GameSettings();
        craftLookUpTable= new CraftLookUpTable(getGameComponentMapper());

    }
    public Vector2 getTileWorldCoordinates(int tileX, int tileY, int mapX, int mapY){
            // returns world coordinates for a tile based on the word coordinates  in realtionship to all maps in the world.
            for(int count=0;  count<mapX; count++) {
                tileX = tileX + worldMap[count][mapY].getXSize();
            }
            for(int count=0;  count<mapY; count++) {
                tileY = tileY + worldMap[mapX][count].getYSize();
            }
            return new Vector2(tileX, tileY);
        }
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
        public LandMap getMap(int x, int y){ 
            // returns a valid game map with given coordinates  if map  if coordinates ar out bounds returns the closest in bound map
            //if space is empty map can be null
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
        public  GameMap  getGameMapOrNull(int x, int y){
            // returns a game map with given coordinates  if map  if coordinates are out of  bounds returns null
            if(x<0 || y<0 || x>xMaps-1 || y>yMaps-1){
                return null;
            }
            return worldMap[x][y];
        }
        public void placeMap(LandMap mapToPlace, int x, int y){
            // set a given array square to a landmap instance 
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
        private  void setMap( int x, int y, LandMap map){
            worldMap[x][y] = map;
            map.setWorldX(x);
            map.setWorldY(y);


            
        }
        
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
    public GameSettings getGameStartSettings() {
        return gameStartSettings;
    }
    public void deserialize(GameAssets assetts, GameSettings gameStartSettingsObject) {
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



    private void addEntitiesToEngine(Array<Entity> entities){
            int size=entities.size;
            for(int count=0; count<size; count++){
                addEntityToEngine(entities.get(count));

            }

    }
    private void removeEntitiesFromEngine(Array<Entity> entities){
        int size=entities.size;
        for(int count=0; count<size; count++){
            removeEntityFromEngine(entities.get(count));

        }

    }
    public void setCurrentMap(int x, int y) {
            if(currentMap!=null){
                removeTilesFromEngine(currentMap);
                removeEntitiesFromEngine(currentMap.getEntities());

            }
        this.currentMap = worldMap[x][y];
        this.currentMapX=currentMap.getWorldX();
        this.currentMapY=currentMap.getWorldY();
        addTilesToEngine(currentMap);
        addEntitiesToEngine(currentMap.getEntities());

    }
    
    public void setStartMap(int x, int y){
            //sets the start map
            this.startMapX=x;
            this.startMapY=y;
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
    public int getStartMapX() {
        return startMapX;
    }
    public int getStartMapY() {
        return startMapY;
    }
    public Entity getEntity(String id ){
            if(id==null || id.isEmpty()){
                return  null;
            }
            return entitiesInWorld.get(id);
    }
    private void addEntityToEngine(Entity entity){ // adds entity to ashley engine
       NotAddedToEngine notAddedToEngine= gameComponentMapper.getNotAddedToEngineComponentMapper().get(entity);


       // if entity doesn't have the not add to engine marker add it
       if(notAddedToEngine==null) {
           engine.addEntity(entity);
           entity.add(new AddedToEngine());
       }

    }
    public void addEntityToMap(Entity entity, PositionComponent position){ // adds an entity to a  gameMap
           GameMap map= getGameMapOrNull(position.getMapWorldLocationX(), position.getMapWorldLocationY());
           if(map==null){
               return;
           }
           map.addEntity( entity);
    }
       public void addEntityToWorld(Entity entity) { // adds entity to the world,  and the map stored in its position
        PositionComponent position=gameComponentMapper.getPositionComponentMapper().get(entity);
           ID id= gameComponentMapper.getIdComponentMapper().get(entity);
          // if entity has no id give it one
           if(id==null){
               id= new ID();
               entity.add(id);
           }
           // add entity to entity map
           entitiesInWorld.put(id.getId(), entity);


        if (position!=null) { // has  position add entity to map

            addEntityToMap(entity, position);
        }

    }

    public void addEntityToWorld(EntityBag entityBag) { // adds a bag of entities  to the world, engine, and the map stored in its position
        Array<Entity> entities=entityBag.getEntities();
        int size=entities.size;
        for(int count=0; count<size; count++) {
            Entity entity=entities.get(count);
            addEntityToWorld(entity);
        }

    }

    // removes and entity from the engine
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
    // removes entity from the world, engine, and map.
    public void removeEntityFromWorld(Entity entity){
        PositionComponent position=gameComponentMapper.getPositionComponentMapper().get(entity);
        removeEntityFromEngine(entity);
        removeEntityFromMap(entity, position);
        entitiesInWorld.remove(gameComponentMapper.getIdComponentMapper().get(entity).getId());



    }

    // adds the game map tiles to the engine
    private  void addTilesToEngine(GameMap gameMap){

            LandSquareTile[] [] tileMap=gameMap.getMap();
        for (int countx=0; countx<tileMap.length; countx++) {
            for (int county = 0; county < tileMap[0].length; county ++) {
                engine.addEntity(tileMap[countx][county]);

            }
        }
    }
    // removes the game map tiles from the engine

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

    public NamedTextureAtlas getWorldTextureAtlas() {
        return worldTextureAtlas;
    }

    public void setWorldTextureAtlas(NamedTextureAtlas worldTextureAtlas) {
        this.worldTextureAtlas = worldTextureAtlas;
        this.worldTextureAtlas=worldTextureAtlas;
    }

    public String getWorldTextureAtlasPath() {
        return worldTextureAtlasPath;
    }

    public void setWorldTextureAtlasPath(String worldTextureAtlasPath) {
        this.worldTextureAtlasPath = worldTextureAtlasPath;
    }
}
