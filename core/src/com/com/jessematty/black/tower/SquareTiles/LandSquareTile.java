package com.jessematty.black.tower.SquareTiles;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Tiles.TileComponent;
import com.jessematty.black.tower.Components.Tiles.TileWeatherChangableNumericStatChangeable;
import com.jessematty.black.tower.Components.Tiles.TileWeatherNumericStatsChangable;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;

/**
 * class that represents a game tile
 * this extends libGDX Ashley Entity
 * so that the Ashley Engine can process them
 * for things collision detection  and other functions
 */

public class  LandSquareTile extends Entity  { // base class all other tile classes extend
	protected transient  boolean unchangeable=false;
	protected  transient boolean checked;
	protected  transient boolean inArea;
	protected  transient PositionComponent position;
	protected  transient TileComponent tileComponent;

	/**
	 * used for deserialization
	 */
	public  LandSquareTile() {
		tileComponent= new TileComponent();
		NumericStats numericStats= new NumericStats();
		BooleanStats booleanStats= new BooleanStats();
		StringStats stringStats=new StringStats();
		NumericStatsChangeable numericStatsChangeable = new NumericStatsChangeable();
		TileWeatherNumericStatsChangable tileWeatherNumericStatsChangable= new TileWeatherNumericStatsChangable();
		BooleanStatsChangeable booleanStatsChangeable = new BooleanStatsChangeable();
		StringStatsChangeable stringStatsChangeable = new StringStatsChangeable();
		GroupsComponent groupsComponent = new GroupsComponent();
		Array<String> stringGroups= groupsComponent.getGroups();
		stringGroups.add("entity");
		stringGroups.add("tile");
		add(numericStats);
		add(booleanStats);
		add(stringStats);
		add(booleanStatsChangeable);
		add(numericStatsChangeable);
		add(stringStatsChangeable);
		add(tileWeatherNumericStatsChangable);
		add(tileComponent);
		add(groupsComponent);
		tileWeatherNumericStatsChangable.addStatToChange(new TileWeatherChangableNumericStatChangeable(true,"temperature", 70,-275, 20000));
		numericStats.addStat(new NumericStat(false,"COF" ,1,0,10));
		numericStatsChangeable.addStatToChange(new NumericStatChangeable(false,"temperature",  0,0,3, 0));
		position= new PositionComponent();
		position.getTiles().add(this);
		position.removeBounds();
		add(position);
		add(new NameComponent(true, toString()));

	}
	public LandSquareTile(int locationX, int locationY, int yTiles){
		this();
		position.setTileLocationX(locationX);
		position.setTileLocationY(locationY);
		float worldUnitsX = (position.getTileLocationX() ) * 32;
		float worldUnitsY =  (yTiles - position.getTileLocationY()) * 32;
		position.setPosition(worldUnitsX, worldUnitsY);
	}

	public void addEntity(Entity occupant) { // adds  a new Object to the square
		if (occupant == null) {
			return;
		}
		tileComponent.getEntities().add(occupant);
		tileComponent.setEntered(true);
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
	public float getScreenLocationX() {
		return position.getLocationX();
	}
	public float getScreenLocationY() {
		return position.getLocationY();
	}
	public boolean isEnterable() {
		return tileComponent.isEnterable();
	}
	public void setEnterable(boolean enterable) {
		this.tileComponent.setEnterable(enterable);
	}
	public PositionComponent getPosition() {
		return position;
	}
	public TileComponent getTileComponent() {
		return tileComponent;
	}
	@Override
	public String toString() {
		return  "tile  x "+ position.getTileLocationX() +" y "+ position.getTileLocationY();
	}
    public Array<Entity> getEntities() {
        return tileComponent.getEntities();
    }
    public void setEntities(Array<Entity> entities) {
        tileComponent.setEntities(entities);
    }
	public Array<Entity> getEntities( Class <?extends Component>... components){
	    Array< Entity> entitiesToReturn= GameComponentMapper.getEntitiesWithComponents(tileComponent.getEntities(), components);
		return entitiesToReturn;
	}

	/**
	 * gets all entities with  that match all of the passed in stats and components
	 * @param numericStats the array of strings that map to  the numeric stat names an entity must have to be returned
	 * @param stringStats the array of strings that map to  the string stat names an entity must have to be returned
	 * @param booleanStats the array of strings that map to  the booleanstat names an entity must have to be returned
	 * @param components the components an entity mus have to be returned
	 * @return an array  of the matching Entities  who are  present on this tile
	 */
	public Array<Entity> getEntities(Array<String> numericStats, Array<String> stringStats, Array<String> booleanStats, Class<? extends Component>... components) {
		Array< Entity> entitiesToReturn=GameComponentMapper.getEntitiesContainingStats(tileComponent.getEntities(), numericStats, stringStats, booleanStats,  components);
		return  entitiesToReturn;
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

	public void setBounds(float x, float y){
		position.setBounds(x,y);
	}
	public void makeSolid(){
		position.setBounds(32,32);
	}
	public void setPosition(PositionComponent position) {
		this.position = position;
	}
	public void setTileComponent(TileComponent tileComponent) {
		this.tileComponent = tileComponent;
	}
	public void setMapId(String mapId){
		position.setMapID(mapId);
	}
	public String getMapId(){
		return position.getMapId();
	}


}
