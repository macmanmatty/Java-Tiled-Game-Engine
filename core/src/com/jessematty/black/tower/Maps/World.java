package com.jessematty.black.tower.Maps;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.Crafting.Craft;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameSettings.GameStartSettings;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Crafting.LookUpTables.CraftLookUpTable;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
public class World { // class that holds the array of maps  aka the world
        private LandMap [] [] worldMap;
        private GameStartSettings gameStartSettings;
        private  int xMaps;
        private int yMaps;
        private String loadPath; // load path for the world used to save the game during pauses.
        private String textureAtlasPath;
        private transient GameMap currentMap;
        private  int startMapX;
        private int startMapY;
        private OrderedMap<String, Entity> entitiesInWorld= new OrderedMap<String, Entity>(); // all of the entities currently in the world

        private CraftLookUpTable craftLookUpTable;
        private  final  transient Engine engine=new PooledEngine(); // game engine
        private transient final GameComponentMapper gameComponentMapper= new GameComponentMapper();
        private String name;
        private boolean gameInProgress;
        private  boolean newWorld =true;
        // used for  deserlization
        public World() {
        }
        public World(int xMaps, int yMaps) {
           this(xMaps, yMaps, "world");


        }
    public World(int xMaps, int yMaps, String name) {
        createWorld(xMaps, yMaps);
        this.name = name;
        gameStartSettings= new GameStartSettings();
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
            addTilesToEngine(map);
            
        }
        
        private void removeMap(int x, int y){
             LandMap map =worldMap[x][y];
           
            removeTilesFromEngine(map);
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
    public GameStartSettings getGameStartSettings() {
        return gameStartSettings;
    }
    public void deserialize(GameAssets assetts, GameStartSettings gameStartSettingsObject) {
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
    public void setCurrentMap(GameMap currentMap) {
        this.currentMap = currentMap;
    }
    
    public void setStartMap(int x, int y){
            //sets the start map
            this.startMapX=x;
            this.startMapY=y;
            if(currentMap==null) {
                currentMap = worldMap[x][y];
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
    private void addEntityToEngine(Entity entity){ // adds entity to ashley engine and hashmap of entities currently  in the world;

            ID id= gameComponentMapper.getIdComponentMapper().get(entity);
            if(id==null){
                id= new ID();
                entity.add(id);
            }
        entitiesInWorld.put(id.getId(), entity);
        engine.addEntity(entity);
    }
    public void addEntityToMap(Entity entity, Position position){ // adds an entity to gameMap
           GameMap map= getGameMapOrNull(position.getMapWorldLocationX(), position.getMapWorldLocationY());
           if(map==null){
               return;
           }
           map.addEntity( entity);
    }
    public void addEntity(Entity entity) { // adds entity to the world, engine, and the map stored in its position
            Position position=gameComponentMapper.getPositionComponentMapper().get(entity);

            addEntityToEngine(entity);
        if (position!=null) { // has no position cannot add entity to map

            addEntityToMap(entity, position);
        }

    }
    private void removeEntityFromEngine(Entity entity){
        entitiesInWorld.remove(gameComponentMapper.getIdComponentMapper().get(entity).getId());
        engine.removeEntity(entity);
    }
    public void removeEntityFromMap(Entity entity, Position position){
        GameMap map= getGameMapOrNull(position.getMapWorldLocationX(), position.getMapWorldLocationY());
        if(map==null){
            return;
        }
        map.removeEntity(  entity);
    }
    public void removeEntity(Entity entity){
        Position position=gameComponentMapper.getPositionComponentMapper().get(entity);
        removeEntityFromEngine(entity);
        removeEntityFromMap(entity, position);
    }
    private  void addTilesToEngine(GameMap gameMap){
          
            LandSquareTile[] [] tileMap=gameMap.getMap();
        for (int countx=0; countx<tileMap.length; countx++) {
            for (int county = 0; county < tileMap[0].length; county ++) {
                engine.addEntity(tileMap[countx][county]);
            }
        }
    }
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




}
