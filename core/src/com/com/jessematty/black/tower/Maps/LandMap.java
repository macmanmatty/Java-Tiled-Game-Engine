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

	public void setDayLightAmount(double gameTime) {
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

	@Override
	public void setMapName(String mapName) {
		gameMapSettings.getSimpleSetting("mapName", String.class);
	}
	@Override
	public String getMapName() {
		return gameMapSettings.getSimpleSetting("name", String.class);
	}
}
