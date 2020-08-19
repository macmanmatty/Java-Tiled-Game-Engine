package com.jessematty.black.tower.SquareTiles;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangable;
import com.jessematty.black.tower.Components.Tile;
import com.jessematty.black.tower.Components.TileWeatherChangableNumericStat;
import com.jessematty.black.tower.Components.TileWeatherNumericStatsChangable;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.Area;
import com.jessematty.black.tower.Maps.GameMap;

import java.io.Serializable;

public class  LandSquareTile extends Entity implements Serializable { // base class all other tile classes extend
	private transient  Area area;
	private  boolean enterable=true;
	protected boolean unchangeable=false;
	protected boolean checked;
	protected boolean inArea;
	protected  transient GameMap map;
	protected  transient Position position;
	protected transient Tile tileComponent;
	protected String atlasName;
	protected  transient GameAssets assetts;
	protected transient TiledMapTile mapTile;
	public LandSquareTile(int locationX, int locationY){
		Tile tile= new Tile();
		this.tileComponent=tile;
		tile.setAtlasName("assetts.atlas");
		 NumericStats numericStats= new NumericStats();
		BooleanStats booleanStats= new BooleanStats();
		StringStats stringStats=new StringStats();
		NumericStatsChangable numericStatsChangable = new NumericStatsChangable();
		TileWeatherNumericStatsChangable tileWeatherNumericStatsChangable= new TileWeatherNumericStatsChangable();
		BooleanStatsChangable booleanStatsChangable = new BooleanStatsChangable();
		StringStatsChangable stringStatsChangable = new StringStatsChangable();
		Groups groups= new Groups();
		 Array<String> stringGroups=groups.getGroups();
		 stringGroups.add("entity");
		 stringGroups.add("tile");

		add(numericStats);
		add(booleanStats);
		add(stringStats);
		add(booleanStatsChangable);
		add(numericStatsChangable);
		add(stringStatsChangable);
		add(tileWeatherNumericStatsChangable);
		add(tile);
		add(groups);
		tileWeatherNumericStatsChangable.addStatToChange(new TileWeatherChangableNumericStat(true,"temperature", 70,-275, 20000));
		numericStats.addStat(new NumericStat(false,"COF" ,1,0,10));
		numericStatsChangable.addStatToChange(new ChangableNumericStat(false,"temperature",  0,0,3, 0));
		Position  position= new Position();
		position.getTiles().add(this);
		position.removeBounds();
		position.setTileLocationX(locationX);
		position.setTileLocationY(locationY);
		add(position);
		this.position=position;
		add(new Name(true, toString()));

	}
	public  LandSquareTile() {

	}




	public void setTextureRegion( TextureRegion region){
		mapTile.setTextureRegion(region);
	}
	public void addEntity(Entity occupant) { // adds  a new Object to the square
		if (occupant == null) {
			return;
		}
		tileComponent.getEntities().add(occupant);

	}
	

	public void removeEntity(Entity entity){
		tileComponent.getEntities().removeValue(entity, true);
	}
	
	public void setLocationX(int x){
		position.setTileLocationX(x);
	}
	public void setLocationY(int y){
		position.setTileLocationY(y);
	}
	
	public int getLocationX() {
		return position.getTileLocationX();
	}
	public int getLocationY() {
		return position.getTileLocationY();
	}
	public float getScreenLocationx() {
		return position.getScreenLocationX();
	}
	public float getScreenLocationy() {
		return position.getScreenLocationY();
	}
	public boolean isEnterable() {
		return enterable;
	}
	
	public void setEnterable(boolean enterable) {
		this.enterable = enterable;
	}
	public void setMap(GameMap map) {
		this.map = map;
		float screenLocationX = (position.getTileLocationX() + 1) * 32;
		float screenLocationY = (map.getYSize() - position.getTileLocationY() - 1) * 32;
		position.setSetScreenLocations(screenLocationX, screenLocationY);
		assetts = map.getGameAssets();

	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Tile getTileComponent() {
		return tileComponent;
	}

	public void setTileComponent(Tile tileComponent) {
		this.tileComponent = tileComponent;

	}

	public GameAssets getAssetts() {
		return assetts;
	}
	public void setAssetts(GameAssets assetts) {
		this.assetts = assetts;
	}
	@Override
	public String toString() { // overridden for scene 2d ui list to display thing name rather than class name.
		return  "tile  x "+ position.getTileLocationX() +" y "+ position.getTileLocationY();
	}
	
	
	public TiledMapTile getMapTile() {
		return mapTile;
	}
    public Array<Entity> getEntities() {
        return tileComponent.getEntities();
    }
    public void setEntities(Array<Entity> entities) {
        tileComponent.setEntities(entities);
    }
	public Array<Entity> getEntities( Class <?extends Component>... components){

	    Array< Entity> entitiesToReturn=assetts.getMapDraw().getGameComponentMapper().getEntitiesWithComponents(tileComponent.getEntities(), components);


		return entitiesToReturn;
	}
	public boolean isInArea() {
		return inArea;
	}
	public void setInArea(boolean inArea) {
		this.inArea = inArea;
	}
	public void setUnchangeable(boolean unchangable) {
		this.unchangeable=unchangable;
	}
	public boolean isUnchangeble() {
		return unchangeable;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public boolean hasEntity(Entity entity){
		Array<Entity> entities=tileComponent.getEntities();
		int size=entities.size;
		for(int count=0; count<size; count++) {
			if (entities.get(count).equals(entity)){
				return true;
			}
		}
		return false;
	}




}
