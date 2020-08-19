package com.jessematty.black.tower.Maps;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;

import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Generators.MapGenerators.NumberMapGen;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.Maps.Settings.GameMapSettings;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public abstract class GameMap  implements Map, Serializable { // baisc landSquareTileMap class all maps extend
	protected LandSquareTile map[][]; // the  landSquareTileMap
	protected int xSize;
	protected int ySize; // x and y number  of tiles  for the landSquareTileMap
	protected int mapNumber;
	private boolean newMap=true;
	protected double gravity = 9.8; // the gravitional pul of the world;
	protected transient GameAssets gameAssets;
	protected int numberOfEnemies; // how many enemies are on the landSquareTileMap
	protected boolean hasEnemies; // does the landSquareTileMap have enemies
	protected boolean enemiesRegenerate;
	protected String skinName = "";
	protected String skinAtlas = "";
	protected String mapName = "Map";
	protected com.jessematty.black.tower.Maps.Settings.GameMapSettings settings;
	protected float dayLightAmount = 0;
	protected boolean gettingBrighter = true;
	protected int tileSizeX=32;// map tileSizes
	protected int tileSizeY=32;
	protected boolean lightChanges;
	protected float lightChangeAmount = .01f;
	protected String tiledMapAtlasName;
	protected boolean currentMap; // is the current landSquareTileMap in the Map drawItemAtLocation class?
	protected transient TiledMap tiledMap; // maps tiles landSquareTileMap
	protected String tiledMapPath; // path to the tiled landSquareTileMap file
	protected float time;
	protected RandomNumbers value = new RandomNumbers(); // radom numbers class
	protected  transient GameTime gameTime;
	protected transient Skin skin; // UI skin  for landSquareTileMap
	protected NumberMapGen numberMapGen = new NumberMapGen();
	protected  transient List<Entity> entities = new ArrayList<Entity>();
	protected float maxXScreen;
	protected float maxYScreen;   // libgdx screen units
	protected int worldX;
	protected int worldY;
	protected String textureAtlasPath;
	protected transient ComponentMapper<Position> positionComponentMapper=ComponentMapper.getFor(Position.class);
	protected transient  Array<EntitySystem> mapGameEntitySystems= new Array<>();
	protected Array<String> mapGameEntitySystemsPaths= new Array(); // map game entity Systems and paths  ie systems unique to this map.
	public GameMap() {
	}
	public GameMap(GameAssets assetManager, Skin skin) {
		this.gameAssets = assetManager;
		this.skin = skin;
	}
	public GameMap(int xSize, int ySize, GameAssets gameAssets) {
		this.xSize = xSize;
		this.ySize = ySize;
		maxXScreen = xSize * tileSizeX;
		maxYScreen = ySize * tileSizeY;
		this.gameAssets = gameAssets;
	}
	public GameMap( int xSize, int ySize, GameAssets gameAssets, Skin skin) {
		this.xSize = xSize;
		this.ySize = ySize;
		maxXScreen = xSize * tileSizeX;
		maxYScreen = ySize * tileSizeY;
		this.gameAssets = gameAssets;
		this.skin = skin;
	}
	public GameMap(GameAssets assetManager) {
		this.gameAssets = assetManager;
	}
	public GameMap( LandSquareTile[][] map, int xSize, int ySize, GameAssets gameAssets, LandSquareTile enterGate, LandSquareTile exitGate, Skin skin) {
		this.map = map;
		this.xSize = xSize;
		this.ySize = ySize;
		this.gameAssets = gameAssets;
		this.skin = skin;
		maxXScreen = xSize * tileSizeX;
		maxYScreen = ySize * tileSizeY;
		numberMapGen.setMapSize(map.length, map[0].length);
	}
	public void deSerialize(GameAssets assets, com.jessematty.black.tower.Maps.Settings.GameMapSettings settings) { // method for landSquareTileMap initlization landSquareTileMap deseralization
		this.gameAssets = assets;
		this.tiledMapPath = settings.getTiledMapPath();
		this.skinName = settings.getSkinName();

		tiledMap = gameAssets.loadExternalTMXMap(tiledMapPath);
		skin = assets.loadInternalSkin(skinName, skinAtlas);
		this.gravity = settings.getGravity();
		this.mapName = settings.getMapName();
		this.lightChangeAmount=settings.getLightChangeAmount();
		this.worldX=settings.getWorldX();
		this.worldY=settings.getWorldY();
	}
	public void mapTurnActions(float deltaTime, GameTime gameTime) { // method for updating the map and the tiles on it each game loop call
		this.gameTime = gameTime;
		if (lightChanges == true) {
			setDayLightAmount(gameTime.getTotalGameTimeLaspedInSeconds());
		}
	}
	public LandSquareTile[][] getMap() {
		return map;
	}
	public int getXSize() {
		return xSize;
	}
	public int getYSize() {
		return ySize;
	}
	public LandSquareTile getMapSquare(int xlocation, int ylocation) {
		// return a landSquareTileMap square checking first that the squre exists based on the given numbers and returns a map tile
		// if gievn tile is out of bounds returns the closest tile that is in bounds
		if (xlocation < 0) {
			xlocation = 0;
		}
		if (ylocation < 0) {
			ylocation = 0;
		}
		if (ylocation > ySize - 1) {
			ylocation = ySize - 1;
		}
		if (xlocation > xSize - 1) {
			xlocation = xSize - 1;
		}
		return map[xlocation][ylocation];
	}
	public LandSquareTile getMapSquareOrNull(int xLocation, int yLocation) { // returns a landSquareTileMap square
		// if the square does exist returns  null
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
	public Array<LandSquareTile> getFourDirectionsAdjoiningTiles(LandSquareTile tile) {
		int x = tile.getLocationX();
		int y = tile.getLocationY();
		Array<LandSquareTile> tiles = new Array<LandSquareTile>();
		LandSquareTile tile2 = getMapSquareOrNull(x - 1, y);
		if (tile2 != null) {
			tiles.add(tile2);
		}
		tile2 = getMapSquareOrNull(x + 1, y);
		if (tile2 != null) {
			tiles.add(tile2);
		}
		tile2 = getMapSquareOrNull(x, y - 1);
		if (tile2 != null) {
			tiles.add(tile2);
		}
		tile2 = getMapSquareOrNull(x, y + 1);
		if (tile2 != null) {
			tiles.add(tile2);
		}
		return tiles;
	}
	public LandSquareTile getClosestEnterableTile(LandSquareTile tile) { // returns the closest isEnterable tile to  WoodWand given tile
		int locationx = tile.getLocationX();
		int locationy = tile.getLocationY();
		LandSquareTile newTile = null;
		int counter = 1;
		if (locationx == xSize) {
			locationx = xSize - 1;
		}
		if (locationy == ySize) {
			locationy = ySize - 1;
		}
		if (locationy < 0) {
			locationy = 0;
		}
		if (locationx < 0) {
			locationx = 0;
		}
		return newTile;
	}
	public int getMapNumber() {
		// TODO Auto-generated method stub
		return mapNumber;
	}
	public void addEntity(  Entity entity) { // adds entity to the approiate landsquare tiles on the map based on its bounds
		Position position=positionComponentMapper.get(entity);
		if (position == null) {
			return;
		}
			entities.add(entity);
			Array<LandSquareTile> tiles=getAllTilesAndAddEntity(position.getBoundsBoundingRectangle(), entity);
			position.setTiles(tiles);
		}
	public void removeEntity(Array<LandSquareTile>  tiles, Entity entity){
		int size=tiles.size;
		for(int count=0; count<size; count++){
			tiles.get(count).removeEntity(entity);
		}
	}
	public int getEnemies() {
		// TODO Auto-generated method stub
		return numberOfEnemies;
	}
	public boolean getHasEnemies() {
		return hasEnemies;
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
	public Entity getClosestEntity(LandSquareTile location, Direction direction, int distance, Class<? extends Component>... components) {
		Array<LandSquareTile> tiles = getAdjacentTiles(location, direction, distance);
		int size = tiles.size;
		for (int count = 0; count < size; count++) {
			Array<Entity> entities = tiles.get(count).getEntities(components);
			if (entities.size > 0) {
				return entities.get(0);
			}
		}
		return null;
	}
	public Array<Entity> getClosestEntities(LandSquareTile location, Direction direction, int distance, Class<? extends Component>... components) {
		Array<Entity> occupants = new Array<Entity>();
		if (direction != null) {
			Array<LandSquareTile> tiles = getAdjacentTiles(location, direction, distance);
			int size = tiles.size;
			for (int count = 0; count < size; count++) {
				occupants.addAll(tiles.get(count).getEntities(components));
			}
		}
		return occupants;
	}
	public Array<Entity> getClosestEntities(Position position, int distance, Class<? extends Component>... components) {
		Array<LandSquareTile> tiles = getSurroundingTiles(position, distance);
		int size = tiles.size;
		Array<Entity> occupants = new Array<Entity>();
		for (int count = 0; count < size; count++) {
			occupants.addAll(tiles.get(count).getEntities(components));
		}
		return occupants;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public void removeEntity( Entity entity) {
		Position position=positionComponentMapper.get(entity);
		entities.remove(entity);
		if(position!=null){
			// remove entity from tiles
			removeEntity(position.getTiles(), entity);
		}
	}
	public Vector2 getScreenCoordinatesFromTileCoordinates(int x, int y){
        float screenLocationX = (x + 1) * 32;
        float screenLocationY = (ySize - y - 1) * 32;
        return new Vector2(screenLocationX, screenLocationY);
    }
	public void setTime(int time) {
		this.time = time;
	}
	public boolean isEnemiesRegenerate() {
		return enemiesRegenerate;
	}
	public void setEnemiesRegenerate(boolean enemiesRegenerate) {
		this.enemiesRegenerate = enemiesRegenerate;
	}
	public GameAssets getGameAssets() {
		return gameAssets;
	}
	public LandSquareTile screenToTile(float screenLocationX, float screenLocationY) { // 0,0 is top left for landsquare tile landSquareTileMap but 0,0 is bottom left for screen  stage  to y is y- yscreenloaction
		return getMapSquareOrNull((int) Math.ceil(screenLocationX / tileSizeX) - 1, ySize - (int) Math.ceil(screenLocationY / tileSizeY));
	}
	public LandSquareTile getRandomEnterableTile() {
		LandSquareTile tile = null;
		while (tile == null) {
			LandSquareTile tile2 = getMapSquare(value.getRandomNumber(3, xSize - 3), value.getRandomNumber(3, ySize - 3));
			if (tile2.isEnterable() == true) {
				tile = tile2;
			}
		}
		return tile;
	}
	public void setMap(LandSquareTile[][] map) {
		this.map = map;
		this.xSize = map.length;
		this.ySize = map[0].length;
		maxXScreen = xSize * tileSizeX;
		maxYScreen = ySize * tileSizeY;
		newMap=false;
	}
	
	public double getGravity() {
		return gravity;
	}
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	public int getNumberOfEnemies() {
		return numberOfEnemies;
	}
	public boolean isHasEnemies() {
		return hasEnemies;
	}
	public float getMaxXScreen() {
		return maxXScreen;
	}
	public float getMaxYScreen() {
		return maxYScreen;
	}
	public List<LandSquareTile> getSurroundingTiles(Position position) {  //returns the surronding tiles for a given object based on the texture width and height.
		float xMin = position.getScreenLocationX() - 2;
		float yMin = position.getScreenLocationY() - 2;
		float xMax = position.getScreenLocationX() + position.getBoundsX();
		float yMax = position.getScreenLocationY() + position.getBoundsY();
		ArrayList<LandSquareTile> tiles = new ArrayList<LandSquareTile>();
		int minTileX = (int) (xMin / tileSizeX);
		int minTileY = (int) (yMin / tileSizeY);
		int maxTileY = (int) (yMax / tileSizeY);
		int maxTileX = (int) (xMax / tileSizeX);
		int xTiles = maxTileX - minTileX;
		int yTiles = maxTileY - minTileY;
		for (int countx = 0; countx < xTiles; countx++) {
			for (int county = 0; county < yTiles; county++) {
				if (countx == minTileX || countx == maxTileX || county == maxTileY || county == minTileY) {
					LandSquareTile tile = getMapSquare(minTileX, minTileY);
					tiles.add(tile);
				}
			}
		}
		return tiles;
	}
	public Array<LandSquareTile> getSurroundingTiles(Position position, float distance) {  //returns the surronding tiles for a given object based on the texture width and height.
		float xMin = position.getScreenLocationX() - tileSizeX;
		float yMin = position.getScreenLocationY() - tileSizeY;
		float xMax = position.getScreenLocationX() + position.getBoundsX() + (distance) + tileSizeX;
		float yMax = position.getScreenLocationY() + position.getBoundsY() + (distance) + tileSizeY;
		Array<LandSquareTile> tiles = new Array<LandSquareTile>();
		int minTileX = (int) (xMin / tileSizeX);
		int minTileY = (int) (yMin / tileSizeY);
		int maxTileY = (int) (yMax / tileSizeY);
		int maxTileX = (int) (xMax / tileSizeX);
		int xTiles = maxTileX - minTileX;
		int yTiles = maxTileY - minTileY;
		for (int countx = 0; countx < xTiles; countx++) {
			for (int county = 0; county < yTiles; county++) {
				if (countx == minTileX || countx == maxTileX || county == maxTileY || county == minTileY) {
					LandSquareTile tile = getMapSquare(minTileX, minTileY);
					tiles.add(tile);
				}
			}
		}
		return tiles;
	}
	public List<LandSquareTile> getSurroundingTiles(LandSquareTile tile, int distance) { // returns all tiles surronding a given tile as an arraylist with the give tile in the list aswell
		ArrayList<LandSquareTile> tiles = new ArrayList<LandSquareTile>();
		int locationx = tile.getLocationX();
		int locationy = tile.getLocationY();
		for (int countx = -distance; countx < distance; countx++) {
			for (int county = -distance; county < distance; county++) {
				LandSquareTile tile2 = getMapSquare(locationx + distance, locationy + distance);
				tiles.add(tile2);
			}
		}
		return tiles;
	}
	public LandSquareTile getNextTile(LandSquareTile tile, Direction direction) { // returns  the next tile in the squence based on the direction you are headed
		int xLocation = tile.getLocationX();
		int yLocation = tile.getLocationY();
		switch (direction) {
			case UP:
				return getMapSquare(xLocation, yLocation - 1);
			case DOWN:
				return getMapSquare(xLocation, yLocation + 1);
			case LEFT:
				return getMapSquare(xLocation - 1, yLocation);
			case RIGHT:
				return getMapSquare(xLocation + 1, yLocation);
			case LEFTUP:
				return getMapSquare(xLocation - 1, yLocation - 1);
			case LEFTDOWN:
				return getMapSquare(xLocation - 1, yLocation + 1);
			case RIGHTUP:
				return getMapSquare(xLocation + 1, yLocation - 1);
			case RIGHTDOWN:
				return getMapSquare(xLocation + 1, yLocation + 1);
			case SAME:
				return tile;
		}
		return tile;
	}
	public LandSquareTile getPreviousTile(LandSquareTile tile, Direction direction, int distance) { // returns  the next tile in the squence based on the direction you are headed
		int xLocation = tile.getLocationX();
		int yLocation = tile.getLocationY();
		switch (direction) {
			case UP:
				return getMapSquare(xLocation, yLocation + distance);
			case DOWN:
				return getMapSquare(xLocation, yLocation - distance);
			case LEFT:
				return getMapSquare(xLocation + distance, yLocation);
			case RIGHT:
				return getMapSquare(xLocation - distance, yLocation);
			case LEFTUP:
				return getMapSquare(xLocation + distance, yLocation + distance);
			case LEFTDOWN:
				return getMapSquare(xLocation + distance, yLocation - distance);
			case RIGHTUP:
				return getMapSquare(xLocation - distance, yLocation + distance);
			case RIGHTDOWN:
				return getMapSquare(xLocation - distance, yLocation - distance);
			case SAME:
				return tile;
		}
		return tile;
	}
	public Array<LandSquareTile> getNextTile(LandSquareTile tile, Direction direction, int distance) { // returns  the next line of  tiles in the squence based on the direction you are headed
		int xLocation = tile.getLocationX();
		int yLocation = tile.getLocationY();
		Array<LandSquareTile> tiles = new Array<LandSquareTile>();
		switch (direction) {
			case UP:
				for (int count = 0; count < distance; count++) {
					tiles.add(getMapSquare(xLocation, yLocation - count));
				}
			case DOWN:
				for (int count = 0; count < distance; count++) {
					tiles.add(getMapSquare(xLocation, yLocation + count));
				}
			case LEFT:
				for (int count = 0; count < distance; count++) {
					tiles.add(getMapSquare(xLocation - count, yLocation));
				}
			case RIGHT:
				for (int count = 0; count < distance; count++) {
					tiles.add(getMapSquare(xLocation + count, yLocation));
				}
			case LEFTUP:
				for (int count = 0; count < distance; count++) {
					tiles.add(getMapSquare(xLocation - count, yLocation - count));
				}
			case LEFTDOWN:
				for (int count = 0; count < distance; count++) {
					tiles.add(getMapSquare(xLocation - count, yLocation + count));
				}
			case RIGHTUP:
				for (int count = 0; count < distance; count++) {
					tiles.add(getMapSquare(xLocation + count, yLocation - count));
				}
			case RIGHTDOWN:
				for (int count = 0; count < distance; count++) {
					tiles.add(getMapSquare(xLocation + count, yLocation + count));
				}
			case SAME:
				return tiles;
		}
		return tiles;
	}
	public Array<LandSquareTile> getAllTilesAndAddEntity(Rectangle rectangle, Entity entity) {
		return getAllTilesAndAddEntity(rectangle.x, rectangle.y,rectangle.width+rectangle.x, rectangle.height+rectangle.y, entity);
	}
	public Array<LandSquareTile> getAllTilesAndAddEntity(float xMin, float yMin, float xMax, float yMax, Entity entity){ // finds all tiles for a given  rectangle bounds  and adds them to a list and returns them .
		Array<LandSquareTile> tiles= new Array<LandSquareTile>();
		for (float countx=xMin-10; countx<xMax; countx=countx+tileSizeX) {
			for (float county = yMin-10; county < yMax; county = county + tileSizeY) {
				LandSquareTile tile=screenToTile(countx, county);
				boolean  canAdd=tileCheck(tile, tiles);
				if(canAdd==true){
					tiles.add(tile);
					tile.addEntity(entity);
				}
			}
		}
		return tiles;
	}
	public Array<LandSquareTile> getAllTiles(Rectangle rectangle) {
		return getAllTiles(rectangle.x, rectangle.y,rectangle.width+rectangle.x, rectangle.height+rectangle.y);
	}
	public Array<LandSquareTile> getAllTiles(float xMin, float yMin, float xMax, float yMax){ // finds all tiles for a given  rectangle bounds  and adds them to a list and returns them .
		Array<LandSquareTile> tiles= new Array<LandSquareTile>();
		for (float countx=xMin-10; countx<xMax; countx=countx+tileSizeX) {
			for (float county = yMin-10; county < yMax; county = county + tileSizeY) {
				LandSquareTile tile=screenToTile(countx, county);
				boolean  canAdd=tileCheck(tile, tiles);
				if(canAdd==true){
					tiles.add(tile);
				}
			}
		}
		return tiles;
	}
	public boolean tileCheck(LandSquareTile tile, Array<LandSquareTile> tiles){ // checks to make sure a tile is not being added twice
		if(tile==null){
			return false;
		}
		int size=tiles.size;
		for(int count=0; count<size; count++){
			if(tiles.get(count)==tile){
				return false;
			}
		}
		return true;
	}
	public LandSquareTile getAdjoiningRandomTile(LandSquareTile tile) { // returns  a random adjoining tile
		int xLocation = tile.getLocationX();
		int yLocation = tile.getLocationY();
		int random=value.getRandomNumber(1,13);
		switch (random) {
			case 1:
				return getMapSquare(xLocation, yLocation - 1);
			case 2:
				return getMapSquare(xLocation, yLocation + 1);
			case 3:
				return getMapSquare(xLocation - 1, yLocation);
			case 4:
				return getMapSquare(xLocation + 1, yLocation);
			case 5:
				return getMapSquare(xLocation - 1, yLocation - 1);
			case 6:
				return getMapSquare(xLocation - 1, yLocation + 1);
			case 7:
				return getMapSquare(xLocation + 1, yLocation - 1);
			case 8:
				return getMapSquare(xLocation + 1, yLocation + 1);
			case 9:
				return getMapSquare(xLocation + 1, yLocation + 1);
			case 10:
				return getMapSquare(xLocation, yLocation + 1);
			default:
				return getMapSquare(xLocation, yLocation - 1);
		}
	}
		public Array<LandSquareTile> getAdjacentTiles(LandSquareTile tile, Direction direction, int distance) { // returns  an array tiles  of  tiles for specified distance going plis one tile to left and right of you in the squence based on the direction you are headed
		int xLocation = tile.getLocationX();
		int yLocation = tile.getLocationY();
			Array<LandSquareTile> tiles= new Array<LandSquareTile>();
		switch (direction) {
			case UP:
				for (int count=0; count<distance; count++){
				tiles.add(getMapSquare(xLocation, yLocation - count)); // addEntity the tiles right infront you x distance ahead
				tiles.add(getMapSquare(xLocation + count, yLocation - count)); // addEntity the tiles to the left of you x distance ahead
				tiles.add(getMapSquare(xLocation - count, yLocation-count )); // addthe tiles to the righjt of you x distance ahead
			}
				break;
			case DOWN:
				for (int count=0; count<distance; count++) {
					tiles.add(getMapSquare(xLocation, yLocation + count));
					tiles.add(getMapSquare(xLocation + count, yLocation + count));
					tiles.add(getMapSquare(xLocation - count, yLocation + count));
				}
				break;
			case LEFT:
				for (int count=0; count<distance; count++) {
					tiles.add(getMapSquare(xLocation - count, yLocation));
					tiles.add(getMapSquare(xLocation - count, yLocation + count));
					tiles.add(getMapSquare(xLocation - count, yLocation - count));
				}
				break;
			case RIGHT:
				for (int count=0; count<distance; count++) {
					tiles.add(getMapSquare(xLocation + count, yLocation));
					tiles.add(getMapSquare(xLocation + count, yLocation - count));
					tiles.add(getMapSquare(xLocation + count, yLocation + count));
				}
				break;
			case LEFTUP:
				for (int count=0; count<distance; count++) {
					tiles.add(getMapSquare(xLocation - count, yLocation - count));
					tiles.add(getMapSquare(xLocation, yLocation - count));
					tiles.add(getMapSquare(xLocation - count, yLocation));
				}
				break;
			case LEFTDOWN:
				for (int count=0; count<distance; count++) {
					tiles.add(getMapSquare(xLocation - count, yLocation + count));
					tiles.add(getMapSquare(xLocation, yLocation + count));
					tiles.add(getMapSquare(xLocation - count, yLocation));
				}
				break;
			case RIGHTUP:
				for (int count=0; count<distance; count++) {
					tiles.add(getMapSquare(xLocation + count, yLocation - count));
					tiles.add(getMapSquare(xLocation, yLocation - count));
					tiles.add(getMapSquare(xLocation + count, yLocation - count));
				}
				break;
			case RIGHTDOWN:
				for (int count=0; count<distance; count++) {
					tiles.add(getMapSquare(xLocation + count, yLocation + count));
					tiles.add(getMapSquare(xLocation, yLocation + count));
					tiles.add(getMapSquare(xLocation + count, yLocation));
				}
				break;
			case SAME:
				return tiles;
		}
		return tiles;
	}
	public LandSquareTile [] getAdjacentTiles(LandSquareTile tile, Direction direction) { // returns  the next tile in the squence based on the direction you are headed
		int xLocation = tile.getLocationX();
		int yLocation = tile.getLocationY();
		LandSquareTile [] tiles = new LandSquareTile [3];
		switch (direction) {
			case UP:
				tiles [0]=getMapSquare(xLocation, yLocation - 1);
				tiles[1]=getMapSquare(xLocation+1, yLocation - 1);
				tiles[2]=getMapSquare(xLocation-1, yLocation - 1);
				break;
			case DOWN:
				tiles [0]=getMapSquare(xLocation, yLocation + 1);
				tiles[1]=getMapSquare(xLocation+1, yLocation + 1);
				tiles[2]=getMapSquare(xLocation-1, yLocation + 1);
				break;
			case LEFT:
				tiles [0]=getMapSquare(xLocation-1, yLocation );
				tiles[1]=getMapSquare(xLocation-1, yLocation+1  );
				tiles[2]=getMapSquare(xLocation-1, yLocation -1);
				break;
			case RIGHT:
				tiles [0]=getMapSquare(xLocation+1, yLocation  );
				tiles[1]=getMapSquare(xLocation+1, yLocation - 1);
				tiles[2]=getMapSquare(xLocation+1, yLocation + 1);
				break;
			case LEFTUP:
				tiles [0]=getMapSquare(xLocation-1, yLocation -1 );
				tiles[1]=getMapSquare(xLocation, yLocation - 1);
				tiles[2]=getMapSquare(xLocation-1, yLocation);
				break;
			case LEFTDOWN:
				tiles [0]=getMapSquare(xLocation-1, yLocation + 1);
				tiles[1]=getMapSquare(xLocation, yLocation + 1);
				tiles[2]=getMapSquare(xLocation-1, yLocation);
				break;
			case RIGHTUP:
				tiles [0]=getMapSquare(xLocation+1, yLocation - 1);
				tiles[1]=getMapSquare(xLocation, yLocation -1);
				tiles[2]=getMapSquare(xLocation+1, yLocation - 1);
				break;
			case RIGHTDOWN:
				tiles [0]=getMapSquare(xLocation+1, yLocation + 1);
				tiles[1]=getMapSquare(xLocation, yLocation + 1);
				tiles[2]=getMapSquare(xLocation+1, yLocation);
				break;
			case SAME:
				return tiles;
		}
		return tiles;
	}
	 public  double findDistance(LandSquareTile location1,  LandSquareTile location2) { // calculates the distance bewteen to tiles using the x and y coordinates
		int x2=location2.getLocationX();
		int y2=location2.getLocationY();
		int y1=location1.getLocationY();
		int x1=location1.getLocationX();
		return Math.sqrt(((x2-x1)^2+((y2-y1)^2)));
	}
	public Vector2 getDistance(Position thing1, Position thing2) {
		float xVector=thing1.getScreenLocationX()-thing2.getScreenLocationX();
		float yVector=thing1.getScreenLocationY()-thing2.getScreenLocationY();
		return new Vector2(xVector, yVector);
	}
	public String getTiledMapPath() {
		return tiledMapPath;
	}
	public boolean isCurrentMap() {
		return currentMap;
	}
	public void setCurrentMap(boolean currentMap) {
		this.currentMap = currentMap;
	}
	public void setHasEnemies(boolean hasEnemies) {
		this.hasEnemies = hasEnemies;
	}
	public String getSkinName() {
		return skinName;
	}
	public void setSkinName(String skinName) {
		this.skinName = skinName;
	}
	public String getSkinAtlas() {
		return skinAtlas;
	}
	public void setSkinAtlas(String skinAtlas) {
		this.skinAtlas = skinAtlas;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public String getMapName() {
		return mapName;
	}
	public GameMapSettings getSettings() {
		return settings;
	}
	public float getDayLightAmount() {
		return dayLightAmount;
	}
	public void setDayLightAmount(double gameTime) { // method thats sets the actionTurns of day for casting shadows.
		if(gettingBrighter ==true) {
			if (gameTime % 60 == 0) {
				dayLightAmount = dayLightAmount - lightChangeAmount;
				if(dayLightAmount<0){
					dayLightAmount=0;
				}
			}
		}
		else {
			if (gameTime % 60 == 0) {
				dayLightAmount = dayLightAmount + lightChangeAmount;
				if(dayLightAmount>1){
					dayLightAmount=1;
				}
			}
		}
		if(gameTime%86400==0){
			if(gettingBrighter ==true){
				gettingBrighter =false;
			}
			else{
				gettingBrighter =true;
			}
		}
	}
	public void setDayLight(float dayLight){
		this.dayLightAmount=dayLight;
	}
	@Override
	public String toString() { // overridden for scene 2d ui list to display thing name rather than class name.
		return mapName;
	}
    public int getTileSizeX() {
        return tileSizeX;
    }

	public int getTileSizeY() {
		return tileSizeY;
	}

	public void setTileSize(int sizeX, int sizeY) {
		this.tileSizeX=sizeX;
		this.tileSizeY=sizeY;

	}
	public void setTileSize(int size) {
		this.tileSizeX=size;
		this.tileSizeY=size;

	}



	public boolean isLightChanges() {
		return lightChanges;
	}
	public void setLightChanges(boolean lightChanges) {
		this.lightChanges = lightChanges;
	}
	public float getLightChangeAmount() {
		return lightChangeAmount;
	}
	public void setLightChangeAmount(float lightChangeAmount) {
		this.lightChangeAmount = lightChangeAmount;
	}
    public  void setMapSquare(int x,  int y, LandSquareTile tile){
		if(x<0 || y<0 || x>xSize-1 || y>ySize-1){
			return;
		}
		map[x][y]=tile;
	}
    public void setSkin(Skin skin) {
        this.skin = skin;
    }
	public String getTiledMapAtlasName() {
		return tiledMapAtlasName;
	}
	public void setTiledMapAtlasName(String tiledMapAtlasName) {
		this.tiledMapAtlasName = tiledMapAtlasName;
	}
	public List<Entity> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	public int getWorldX() {
		return worldX;
	}
	public int getWorldY() {
		return worldY;
	}
	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}
	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}
	public boolean isNewMap() {
		return newMap;
	}

	public String getTextureAtlasPath() {
		return textureAtlasPath;
	}

	public void setTextureAtlasPath(String textureAtlasPath) {
		this.textureAtlasPath = textureAtlasPath;
	}

	public Array<EntitySystem> getMapGameEntitySystems() {
		return mapGameEntitySystems;
	}

	public void setMapGameEntitySystems(Array<EntitySystem> mapGameEntitySystems) {
		this.mapGameEntitySystems = mapGameEntitySystems;
	}

	public Array<String> getMapGameEntitySystemsPaths() {
		return mapGameEntitySystemsPaths;
	}

	public void setMapGameEntitySystemsPaths(Array<String> mapGameEntitySystemsPaths) {
		this.mapGameEntitySystemsPaths = mapGameEntitySystemsPaths;
	}
}
