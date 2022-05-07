package com.jessematty.black.tower.Maps;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.FlagComponents.OnCurrentMap;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Kryo.KryoSerialized;
import com.jessematty.black.tower.GameBaseClasses.Settings.Settings;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.Maps.Settings.GameMapSettings;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Systems.GameEntitySystem;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
/**
 * class with map of 2d tiles  the game map that all other maps extend from
 */
public abstract  class GameMap  implements Map {
	/**
	 *                                 PLEASE READ!!!
	 * All fields in this class are serialized by a custom Kryo Serializer
	 * @linked {GameMapKryoSerializer}
	 * and then read upon deserialization
	 * any fields you want saved  in this class you  must add to the serializer and mark @KryoSerialized
	 *
	 * */
	/**
	 * 2d array of  LandSquareTiles representing the  map
	 */
	@KryoSerialized
	protected    LandSquareTile map[][]; // the  landSquareTileMap
	/**
	 * the settings object for this map
	 */
	@KryoSerialized
	protected GameMapSettings gameMapSettings= new GameMapSettings();
	/**
	 * the  libGDX TiledMap for the map
	 */
	@KryoSerialized
	protected  TiledMap tiledMap; //  tiled maps tiles Map
	/**
	 * game time object
	 */
	protected  transient GameTime gameTime; // game time object
	/**
	 * the libGDX Skin the map will use for it's UI
	 */
	protected transient Skin skin; // UI skin  for landSquareTileMap
	/**
	 * the Entities currently on the map NOT including the map of tiles
	 * note entities do not have to have  a x, y location to be present on the map
	 */
	protected  transient Array<Entity> entities = new Array<Entity>();
	/**
	 *  maps that are linked to this one
	 *  */
	private transient Array<GameMap> linkedMaps = new Array<>();
	/**
	 * position component mapper
	 * used for performance
	 */
	protected transient ComponentMapper<PositionComponent> positionComponentMapper=ComponentMapper.getFor(PositionComponent.class);
	/**
	 * array of game engine systems classes  unique to this map  they are added and removed from the engine  when the map changes
	 * 
	 */
	protected transient  Array<Class <? extends GameEntitySystem>> mapGameEntitySystemsClasses = new Array<>();
	/**
	 * basic map info stored in fields below for performance
	 */
	/**
	 *  the width size of the map in wold units
	 */
	protected float maxXWorld;  // max x size in world units
	/**
	 *  the height  size of the map in wold units
	 */
	protected  float maxYWorld; // max y size in world units
	/**
	 * the width of the map in tile units
	 */
	protected  int xTiles; // map  x Size in total tile units 1 tile unit = tileSizeX
	/**
	 * the height of the map in tile units
	 */
	protected int yTiles; // map  y Size in total tile units 1 tile unit = tileSizeY
	/**
	 *  the width and height of the tiled map tiles 
	 *  1  TiledMapTile= 1 LandSquareTile
	 *  1 TiledMapTile = tileWidth, tileHeight world units the default is 32
	 */
	protected  int tileWidth =32; 
	protected int tileHeight =32;
	
	protected  float dayLightAmount;
	protected  float lightChangeAmount;
	protected  boolean gettingBrighter;
	protected  boolean lightChanges;
	/**
	 * the image of the  the map  including all  tiled map tiles  and  drawable map entities
	 */
	protected transient Texture mapImage;
	/**
	 * the maps identifier
	 */
	protected   String id=new UID().toString();
	/**
	 * creates a unique id for the map on construction
	 */
	protected GameMap() {
		gameMapSettings.getSettings().put("id", id);
	}
	public GameMap(int xTiles, int yTiles) {
		this();
		this.xTiles = xTiles;
		this.yTiles = yTiles;
		this.gameMapSettings.setTiles(xTiles, yTiles);
	}
	/**
	 * // method calculates  daylight based on game time
	 * @param gameTime
	 */
	public abstract  void setDayLightAmount(double gameTime);
	/**
	 * @overidable method  for having map do stuff called the the mapdraw classes game loop
	 * @param deltaTime the game delta time @see libgdx docs
	 * @param gameTime // the game time lapsed in ticks
	 */
	public void mapTurnActions(float deltaTime, GameTime gameTime) { // method for updating the map and the tiles on it each game loop call
		this.gameTime = gameTime;
		gameMapSettings= new GameMapSettings();
		Boolean lightChanges=gameMapSettings.getSimpleSetting("lightChanges", Boolean.class);
		
		if (lightChanges!=null && lightChanges==true) {
			setDayLightAmount(gameTime.getTotalGameTimeLapsedInSeconds());
		}
	}
	/**
	 * // return a landSquareTileMap square checking first that the squre exists based on the given numbers and returns a map tile
	 * 		// if given tile is out of bounds returns the closest tile that is in bounds
	 * @param xLocation the tile x Location on the map
	 * @param yLocation the tile y  Location on the map
	 * @return LandSquare The given tile
	 */
	public LandSquareTile getTile(int xLocation, int yLocation) {
		// return a landSquareTileMap square checking first that the square exists based on the given numbers and returns a map tile
		// if given tile is out of bounds returns the closest tile that is in bounds
		if (xLocation < 0) {
			xLocation = 0;
		}
		if (yLocation < 0) {
			yLocation = 0;
		}
		if (yLocation > yTiles - 1) {
			yLocation = yTiles - 1;
		}
		if (xLocation > xTiles - 1) {
			xLocation = xTiles - 1;
		}
		return map[xLocation][yLocation];
	}
	/**
	 * gets a land square tile  at given x, y point on the Map if no tile exists returns null
	 * @param xLocation the tile x Loction on the map
	 * @param yLocation the tile y  Loction on the map
	 * @return LandSquare The given tile
	 */
	public LandSquareTile getMapSquareOrNull(int xLocation, int yLocation) { // returns a landSquareTileMap square
		// if the square does exist returns  null
		int ySize=map[0].length;
		int xSize=map.length;
		if (xLocation < 0) {
			return null;
		}
		if (yLocation < 0) {
			return null;
		}
		if (yLocation > ySize - 1) {
			return null;
		}
		if (xLocation > xSize - 1) {
			return null;
		}
		return map[xLocation][yLocation];
	}
	/**
	 *  add an entity to map and the maps tiles and  adds entity to the appropriate  landsquare tiles on the map based on its bounds
	 * @param entity the entity to add
	 */
	public void addEntity(  Entity entity) {
		PositionComponent position=positionComponentMapper.get(entity);
		if (position == null) {
			return;
		}
		if(position.getMapId()!=id) {
			entities.add(entity);
			position.setMapID(id);
		}
			Array<LandSquareTile> tiles=getAllTilesAndAddEntity(position.getBoundsBoundingRectangle(), entity);
			position.setTiles(tiles);
		}
	/**
	 *  removes an entity from  a list of tiles
	 * @param entity the entity to remove
	 * @param tiles the tilers to remove from
	 */
	public void removeEntity(Array<LandSquareTile>  tiles, Entity entity){
		int size=tiles.size;
		for(int count=0; count<size; count++){
			tiles.get(count).removeEntity(entity);
		}
	}
	/**
	 *  removes an entity from  the map and te tiles it's on
	 * @param entity the entity to remove
	 */
	public void removeEntity( Entity entity) {
		PositionComponent position=positionComponentMapper.get(entity);
		entities.removeValue(entity, true);
		if(position!=null){
			// remove entity from tiles
			removeEntity(position.getTiles(), entity);
		}
	}
	/**
	 *  gets the screen coordinates for a x, y tile location
	 * @param x
	 * @param y
	 * @return Vector2 the screen coordinates of the tile
	 */
	public Vector2 getScreenCoordinatesFromTileCoordinates(int x, int y){
        float screenLocationX = (x + 1) * 32;
        float screenLocationY = (yTiles - y - 1) * 32;
        return new Vector2(screenLocationX, screenLocationY);
    }
	/**
	 *  takes  in float world unit coordinates and returns the   the tile area at that point.
	 * @param mapLocationX the  x location  map in world units 
	 * @param mapLocationY the  y location  map in world units 
	 * @return the LandSquareTile at point location.
	 */
	public LandSquareTile getTileFromTileCoordinates(float mapLocationX, float mapLocationY) {
		return getMapSquareOrNull((int) Math.ceil(mapLocationX / tileWidth) - 1, yTiles - (int) Math.ceil(mapLocationY / tileHeight));
	}
	/**
	 *
	 * @param map the array of tiles to set as the map
	 */
	public void setMap(LandSquareTile[][] map) {
		this.map = map;
		this.xTiles = map.length;
		this.yTiles = map[0].length;
		maxXWorld = xTiles * tileWidth;
		maxYWorld = yTiles * tileHeight;
		gameMapSettings.setTiles(map.length, map[0].length);
		gameMapSettings.getSettings().put("newMap", true);
	}
	/**
	 returns the gravity for the map if no gravity is set returns -9.8
	 */
	public double getGravity() {
		Double gravity  =gameMapSettings.getSimpleSetting("gravity", Double.class);
		if(gravity!=null){
			return  gravity;
		}
		return -9.8;
	}
	public void setGravity(double gravity) {
		gameMapSettings.getSettings().put("gravity", gravity);
	}
	public float getMaxXWorld() {
		return maxXWorld;
	}
	public float getMaxYWorld() {
		return maxYWorld;
	}
	@Override
	public boolean isCurrentMap() {
		return false;
	}
	/**
	 *
	 *  method that returns all tiles surrounding a given tile x distance away including diagonals as a list
	 *  with the given tile in the list as well
	 * @param tile the tile to check for
	 * @param distance the distance away from the given tile
	 * @return
	 */
	public List<LandSquareTile> getSurroundingTiles(LandSquareTile tile, int distance) {
		ArrayList<LandSquareTile> tiles = new ArrayList<LandSquareTile>();
		int locationx = tile.getLocationX();
		int locationy = tile.getLocationY();
		for (int countx = -distance; countx < distance; countx++) {
			for (int county = -distance; county < distance; county++) {
				LandSquareTile tile2 = getTile(locationx + distance, locationy + distance);
				tiles.add(tile2);
			}
		}
		return tiles;
	}
	/**
	 *
	 * @param rectangle
	 * @param entity
	 * @return
	 */
	public Array<LandSquareTile> getAllTilesAndAddEntity(Rectangle rectangle, Entity entity) {
		return getAllTilesAndAddEntity(rectangle.x, rectangle.y,rectangle.width+rectangle.x, rectangle.height+rectangle.y, entity);
	}
	/**
	 * finds all tiles for a given  rectangle bounds  and adds them to a list and returns them
	 * in addition  it adds the passed in entity to the tiles
	 * @param xMin
	 * @param yMin
	 * @param xMax
	 * @param yMax
	 * @param entity
	 * @return
	 */
	public Array<LandSquareTile> getAllTilesAndAddEntity(float xMin, float yMin, float xMax, float yMax, Entity entity){
		Array<LandSquareTile> tiles= new Array<LandSquareTile>();
		xMin=xMin;
		yMin=yMin;
		yMax=yMax;
		xMax=xMax;
		for (float countx=xMin; countx<xMax; countx=countx+ tileWidth) {
			for (float county = yMin; county < yMax; county = county + tileHeight) {
				LandSquareTile tile= getTileFromTileCoordinates(countx, county);
				if(tile==null){
					continue;
				}
					tiles.add(tile);
					tile.addEntity(entity);
			}
		}
		return tiles;
	}
	public Array<LandSquareTile> getAllTiles(Rectangle rectangle) {
		return getAllTiles(rectangle.x, rectangle.y,rectangle.width+rectangle.x, rectangle.height+rectangle.y);
	}
	/**
	 * finds all tiles for a given  rectangle bounds with given side points and adds them to a list and returns them .
	 * @param xMin
	 * @param yMin
	 * @param xMax
	 * @param yMax
	 * @return
	 */
	public Array<LandSquareTile> getAllTiles(float xMin, float yMin, float xMax, float yMax){
		Array<LandSquareTile> tiles= new Array<LandSquareTile>();
		for (float countx=xMin-10; countx<xMax; countx=countx+ tileWidth) {
			for (float county = yMin-10; county < yMax; county = county + tileHeight) {
				LandSquareTile tile= getTileFromTileCoordinates(countx, county);
				if(tile==null){
					continue;
				}
					tiles.add(tile);

			}
		}
		return tiles;
	}
	
	/**
	 * sets the current map flag
	 * and add / or removes the on current map marker Component 
	 * to the all of the LandSquareTile Entities in this map.
	 * @param currentMap the current map flag true =current map false= not current map
	 */
	public void setCurrentMap(boolean currentMap) {
		this.gameMapSettings.getSettings().put("currentMap", currentMap);
		for (int countx = 0; countx< xTiles; countx++) {
			for (int county = 0; county < yTiles; county++) {
				if(currentMap==true) {
					map[countx][county].add(new OnCurrentMap());
				}
				else{
					map[countx][county].remove(OnCurrentMap.class);
				}
			}
		}
		}
	/**
	 * setters getter for the map name @Overidden from the  Nameable interface
	 *
	 */
	@Override
	public void setMapName(String mapName) {
		gameMapSettings.getSimpleSetting("mapName", String.class);
	}
	@Override
	public String getMapName() {
		return gameMapSettings.getSimpleSetting("name", String.class);
	}
	public float getDayLightAmount() {
		return dayLightAmount;
	}
	
	public TiledMap getTiledMap() {
		return tiledMap;
	}
	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}
	public Skin getSkin() {
		return skin;
	}
	public LandSquareTile[][] getMap() {
		return map;
	}
	public int getXTiles() {
		return xTiles;
	}
	public int getYTiles() {
		return yTiles;
	}
	public void setDayLight(float dayLight){
		this.dayLightAmount=dayLight;
	}
	@Override
	public String toString() { // overridden for scene 2d ui list to display thing name rather than class name.
		return  getMapName();
	}
    public int getTileWidth() {
        return tileWidth;
    }
	public int getTileHeight() {
		return tileHeight;
	}
	@Override
	public float getLightChangeAmount() {
		return dayLightAmount;
	}
	@Override
	public void setLightChangeAmount(float lightChangeAmount) {
		this.dayLightAmount=dayLightAmount;
		this.gameMapSettings.getSettings().put("dayLightAmount", lightChangeAmount);
	}
	/**
	 * sets the tile size for the map
	 * @param tileWidth
	 * @param tileHeight
	 */
	@Override
	public void setTileSize(int tileWidth, int tileHeight) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		gameMapSettings.getSettings().put("tileWidth", tileWidth);
		gameMapSettings.getSettings().put("tileHeight", tileHeight);
	}
	@Override
	public Settings getMapSettings() {
		return gameMapSettings;
	}
	/**
	 * sets a given map array element at position x, y  to a land square tile
	 * @param x the x position in the LandSquareTile array
	 * @param y the y position in the LandSquareTile array
	 * @param tile the LandSquareTile to place in the array
	 */
	public  void setMapSquare(int x,  int y, LandSquareTile tile){
		if(x<0 || y<0 || x> xTiles -1 || y> yTiles -1){
			return;
		}
		map[x][y]=tile;
	}
    public void setSkin(Skin skin) {
        this.skin = skin;
    }
	
	public Array<Entity> getEntities() {
		return entities;
	}
	public Array<Class<? extends GameEntitySystem>> getMapGameEntitySystemsClasses() {
		return mapGameEntitySystemsClasses;
	}
	public void setMapGameEntitySystemsClasses(Array<Class<? extends GameEntitySystem>> mapGameEntitySystemsClasses) {
		this.mapGameEntitySystemsClasses = mapGameEntitySystemsClasses;
	}
	public GameMapSettings getGameMapSettings() {
		return gameMapSettings;
	}
	public void setGameMapSettings(GameMapSettings gameMapSettings) {
		this.gameMapSettings = gameMapSettings;
	}
	public Texture getMapImage() {
		return mapImage;
	}
	public String getId() {
		return id;
	}
	public void setMapImage(Texture mapImage) {
		this.mapImage = mapImage;
	}
	/**
	 * sets the maps id
	 * used for deserialization 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
		gameMapSettings.getSettings().put("id", id);
	}
}
