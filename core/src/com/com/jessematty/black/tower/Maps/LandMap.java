package com.jessematty.black.tower.Maps;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.Maps.Buildings.Building;
import java.io.Serializable;

/**
 * class the represents and over world map made of land
 * containing both  the tile entities and the TiledMap
 * with additional buildings on it
 */
public class LandMap extends GameMap implements Serializable {
	public  LandMap() {
		super();
		gameMapSettings.getSettings().put("lightChanges", true);
		this.lightChanges=true;
	}
	public LandMap(int xSize, int ySize) {
		super(xSize, ySize);
		gameMapSettings.getSettings().put("lightChanges", true);
		this.lightChanges=true;
	}
	public void mapTurnActions(float deltaTime, GameTime gameTime){ // method for updating the map and the tiles on it each game loop call
		super.mapTurnActions(deltaTime, gameTime);
		if(lightChanges) {
			setDayLightAmount(gameTime.getTotalGameTimeLapsedInSeconds());
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
}
