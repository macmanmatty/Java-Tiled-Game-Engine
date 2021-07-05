package com.jessematty.black.tower.Maps;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.FlagComponents.OnCurrentMap;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Settings.Settings;
import com.jessematty.black.tower.Maps.Settings.GameMapSettings;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import java.util.ArrayList;
import java.util.List;
public abstract  class GameMap  implements Map { // baisc landSquareTileMap class all maps extend
	protected    LandSquareTile map[][]; // the  landSquareTileMap
	protected GameMapSettings gameMapSettings= new GameMapSettings();
	protected  TiledMap tiledMap; //  tiled maps tiles Map
	protected  transient GameTime gameTime; // game time object
	protected transient Skin skin; // UI skin  for landSquareTileMap
	protected  transient Array<Entity> entities = new Array<Entity>();
	protected transient ComponentMapper<PositionComponent> positionComponentMapper=ComponentMapper.getFor(PositionComponent.class);
	protected transient  Array<EntitySystem> mapGameEntitySystems= new Array<>();  // array of gam,e engine systems unique to this map  they are added and removed from the engine  when the map changes
	protected Array<String> mapGameEntitySystemsPaths= new Array(); //  the paths to map game entity Systems and paths  ie systems unique to this map.
	protected float maxXScreen;  // max x size in world units
	protected  float maxYScreen; // max y size in world units
	protected  int xTiles; // map  x Size in total tile units 1 tile unit = tileSizeX
	protected int yTiles; // map  y Size in total tile units 1 tile unit = tileSizeY
	protected  int tileWidth =32; // tiled map tile sizes for x and y  landsquare tiles hold  the dimensions 1 tiled map tile equals one land square tile.
	protected int tileHeight =32;
	protected  int worldX;
	protected  int worldY;
	protected  float dayLightAmount;
	protected  float lightChangeAmount;
	protected  boolean gettingBrighter;
	protected  boolean lightChanges;
	protected transient Texture mapImage; // the image of the  the map  including all  tiled map tiles  and  drawable map entities


	protected GameMap() {
	}
	public GameMap(int xTiles, int yTiles) {
		this.xTiles = xTiles;
		this.yTiles = yTiles;
		this.gameMapSettings.setTiles(xTiles, yTiles);
	}

	/**
	 * @overidable method  for having map do stuff called the the mapdraw classes game loop
	 * @param deltaTime the game delta time @see libgdx docs
	 * @param gameTime // the game time lapsed in ticks
	 */
	public void mapTurnActions(float deltaTime, GameTime gameTime) { // method for updating the map and the tiles on it each game loop call
		this.gameTime = gameTime;
		gameMapSettings= new GameMapSettings();
		Boolean lightChanges=gameMapSettings.getSimpleSetting("lighChanges", Boolean.class);
		
		if (lightChanges!=null && lightChanges==true) {
			setDayLightAmount(gameTime.getTotalGameTimeLaspedInSeconds());
		}
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

	/**
	 * // return a landSquareTileMap square checking first that the squre exists based on the given numbers and returns a map tile
	 * 		// if given tile is out of bounds returns the closest tile that is in bounds
	 * @param xLocation the tile x Loction on the map
	 * @param yLocation the tile y  Loction on the map
	 * @return LandSquare The given tile
	 */
	public LandSquareTile getMapSquare(int xLocation, int yLocation) {
		// return a landSquareTileMap square checking first that the squre exists based on the given numbers and returns a map tile
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
	 *  add an entity to map and the maps tiles
	 * @param entity the entity to add
	 */
	public void addEntity(  Entity entity) { // adds entity to the approiate landsquare tiles on the map based on its bounds
		PositionComponent position=positionComponentMapper.get(entity);
		if (position == null) {
			return;
		}
			entities.add(entity);
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
	public TiledMap getTiledMap() {
		return tiledMap;
	}
	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}
	public Skin getSkin() {
		return skin;
	}


	public Vector2 getScreenCoordinatesFromTileCoordinates(int x, int y){
        float screenLocationX = (x + 1) * 32;
        float screenLocationY = (yTiles - y - 1) * 32;
        return new Vector2(screenLocationX, screenLocationY);
    }
	public LandSquareTile screenToTile(float screenLocationX, float screenLocationY) { // 0,0 is top left for landsquare tile landSquareTileMap but 0,0 is bottom left for screen  stage  to y is y- yscreenloaction
		return getMapSquareOrNull((int) Math.ceil(screenLocationX / tileWidth) - 1, yTiles - (int) Math.ceil(screenLocationY / tileHeight));
	}

	/**
	 *
	 * @param map the array of tiles to set as the map
	 */
	public void setMap(LandSquareTile[][] map) {
		this.map = map;
		this.xTiles = map.length;
		this.yTiles = map[0].length;
		maxXScreen = xTiles * tileWidth;
		maxYScreen = yTiles * tileHeight;
		gameMapSettings.setTiles(map.length, map[0].length);

		gameMapSettings.getSettings().put("newMap", true);

	}
	
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
	public float getMaxXScreen() {
		return maxXScreen;
	}
	public float getMaxYScreen() {
		return maxYScreen;
	}

	@Override
	public boolean isCurrentMap() {
		return false;
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

	/**
	 *
	 * @param rectangle
	 * @param entity
	 * @return
	 */
	public Array<LandSquareTile> getAllTilesAndAddEntity(Rectangle rectangle, Entity entity) {
		return getAllTilesAndAddEntity(rectangle.x, rectangle.y,rectangle.width+rectangle.x, rectangle.height+rectangle.y, entity);
	}
	public Array<LandSquareTile> getAllTilesAndAddEntity(float xMin, float yMin, float xMax, float yMax, Entity entity){ // finds all tiles for a given  rectangle bounds  and adds them to a list and returns them .
		Array<LandSquareTile> tiles= new Array<LandSquareTile>();
		xMin=xMin-10;
		yMin=yMin-10;
		yMax=yMax+10;
		xMax=xMax+10;
		for (float countx=xMin; countx<xMax; countx=countx+ tileWidth) {
			for (float county = yMin; county < yMax; county = county + tileHeight) {
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
		for (float countx=xMin-10; countx<xMax; countx=countx+ tileWidth) {
			for (float county = yMin-10; county < yMax; county = county + tileHeight) {
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

	public void setCurrentMap(boolean currentMap) {
		this.gameMapSettings.getSettings().put("newMap", true);
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
	public void setEntities(Array<Entity> entities) {
		this.entities = entities;
	}
	public int getWorldX() {
		return worldX;
	}
	public int getWorldY() {
		return worldY;
	}
	public void setWorldX(int worldX) {
		gameMapSettings.getSettings().put("worldX", worldX);
		this.worldX=worldX;
	}
	public void setWorldY(int worldY) {
		gameMapSettings.getSettings().put("worldY", worldY);
		this.worldY=worldY;
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

	public Texture getMapImage() {
		return mapImage;
	}



	public void setMapImage(Texture mapImage) {
		this.mapImage = mapImage;
	}
}
