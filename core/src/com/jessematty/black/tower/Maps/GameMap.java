package com.jessematty.black.tower.Maps;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Markers.OnCurrentMap;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.Maps.Settings.GameMapSettings;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import java.util.ArrayList;
import java.util.List;
public abstract class GameMap  implements Map { // baisc landSquareTileMap class all maps extend
	protected    LandSquareTile map[][]; // the  landSquareTileMap
	protected GameMapSettings gameMapSettings= new GameMapSettings();
	protected  TiledMap tiledMap; //  tiled maps tiles Map
	protected  transient GameTime gameTime;
	protected transient Skin skin; // UI skin  for landSquareTileMap
	protected  transient Array<Entity> entities = new Array<Entity>();
	protected transient ComponentMapper<PositionComponent> positionComponentMapper=ComponentMapper.getFor(PositionComponent.class);
	protected transient  Array<EntitySystem> mapGameEntitySystems= new Array<>();
	protected Array<String> mapGameEntitySystemsPaths= new Array(); // map game entity Systems and paths  ie systems unique to this map.
	protected float maxXScreen;
	protected  float maxYScreen;
	protected  int  xSize;
	protected int ySize;
	protected  int tileSizeX=32;
	protected int tileSizeY=32;
	protected  float dayLightAmount;
	protected  float lightChangeAmount;
	protected  boolean gettingBrighter;



	protected GameMap() {
	}

	public GameMap(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.gameMapSettings.setxSize(xSize);
		this.gameMapSettings.setySize(ySize);
	}

	public void mapTurnActions(float deltaTime, GameTime gameTime) { // method for updating the map and the tiles on it each game loop call
		this.gameTime = gameTime;
		gameMapSettings= new GameMapSettings();
		
		if (gameMapSettings.isLightChanges() == true) {
			setDayLightAmount(gameTime.getTotalGameTimeLaspedInSeconds());
		}
	}
	public LandSquareTile[][] getMap() {
		return map;
	}
	public int getXSize() {
		return gameMapSettings.getxSize();
	}
	public int getYSize() {
		return gameMapSettings.getySize();
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
	public void addEntity(  Entity entity) { // adds entity to the approiate landsquare tiles on the map based on its bounds
		PositionComponent position=positionComponentMapper.get(entity);
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
	public TiledMap getTiledMap() {
		return tiledMap;
	}
	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}
	public Skin getSkin() {
		return skin;
	}
	public void removeEntity( Entity entity) {
		PositionComponent position=positionComponentMapper.get(entity);
		entities.removeValue(entity, true);
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
	public LandSquareTile screenToTile(float screenLocationX, float screenLocationY) { // 0,0 is top left for landsquare tile landSquareTileMap but 0,0 is bottom left for screen  stage  to y is y- yscreenloaction
		return getMapSquareOrNull((int) Math.ceil(screenLocationX / tileSizeX) - 1, ySize - (int) Math.ceil(screenLocationY / tileSizeY));
	}
	public void setMap(LandSquareTile[][] map) {
		this.map = map;
		this.xSize = map.length;
		this.ySize = map[0].length;
		maxXScreen = xSize * tileSizeX;
		maxYScreen = ySize * tileSizeY;
		gameMapSettings.setMaxXScreen(maxXScreen);
		gameMapSettings.setMaxYScreen(maxYScreen);
		gameMapSettings.setxSize(xSize);
		gameMapSettings.setySize(ySize);
		gameMapSettings.setNewMap(true);
	}
	
	public double getGravity() {
		return gameMapSettings.getGravity();
	}
	public void setGravity(double gravity) {
		gameMapSettings.setGravity(gravity);
	}
	public float getMaxXScreen() {
		return maxXScreen;
	}
	public float getMaxYScreen() {
		return maxYScreen;
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
	public Array<LandSquareTile> getAllTilesAndAddEntity(Rectangle rectangle, Entity entity) {
		return getAllTilesAndAddEntity(rectangle.x, rectangle.y,rectangle.width+rectangle.x, rectangle.height+rectangle.y, entity);
	}
	public Array<LandSquareTile> getAllTilesAndAddEntity(float xMin, float yMin, float xMax, float yMax, Entity entity){ // finds all tiles for a given  rectangle bounds  and adds them to a list and returns them .
		Array<LandSquareTile> tiles= new Array<LandSquareTile>();
		xMin=xMin-10;
		yMin=yMin-10;
		yMax=yMax+10;
		xMax=xMax+10;
		for (float countx=xMin; countx<xMax; countx=countx+tileSizeX) {
			for (float county = yMin; county < yMax; county = county + tileSizeY) {
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
	public boolean isCurrentMap() {
		return gameMapSettings.isCurrentMap();
	}
	public void setCurrentMap(boolean currentMap) {
		this.gameMapSettings.setCurrentMap(true);
		for (int  countx=0; countx<xSize; countx++) {
			for (int county = 0; county <ySize ; county++) {
				if(currentMap==true) {
					map[countx][county].add(new OnCurrentMap());
				}
				else{
					map[countx][county].remove(OnCurrentMap.class);
				}
			}
		}
		}
	
	public void setMapName(String mapName) {
		gameMapSettings.setMapName(mapName);
	}
	public String getMapName() {
		return gameMapSettings.getMapName();
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
		return gameMapSettings.getMapName();
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
		this.gameMapSettings.setTileSizeX(tileSizeX);
		this.gameMapSettings.setTileSizeY(tileSizeY);
	}
	public boolean isLightChanges() {
		return gameMapSettings.isLightChanges();
	}
	public void setLightChanges(boolean lightChanges) {
		this.gameMapSettings.setLightChanges(lightChanges);
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


	
	public Array<Entity> getEntities() {
		return entities;
	}
	public void setEntities(Array<Entity> entities) {
		this.entities = entities;
	}
	public int getWorldX() {
		return gameMapSettings.getWorldX();
	}
	public int getWorldY() {
		return gameMapSettings.getWorldY();
	}
	public void setWorldX(int worldX) {
		gameMapSettings.setWorldX(worldX);
	}
	public void setWorldY(int worldY) {
		gameMapSettings.setWorldY(worldY);
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
	public GameMapSettings getGameMapSettings() {
		return gameMapSettings;
	}
	public void setGameMapSettings(GameMapSettings gameMapSettings) {
		this.gameMapSettings = gameMapSettings;
	}


}
