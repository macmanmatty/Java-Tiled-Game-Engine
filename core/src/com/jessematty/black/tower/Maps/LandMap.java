package com.jessematty.black.tower.Maps;

import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.Maps.Buildings.Building;

import java.io.Serializable;

public class LandMap extends GameMap implements Serializable {
	private transient ObjectMap<String, Building> buildings = new ObjectMap<>();
	public  LandMap() {
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


	public void addBuilding(Building building){

		this.buildings.put(building.getBuildingID(), building);

	}


		public Building getBuilding(String id){

		return buildings.get(id);
		}


	@Override
	public void setMapName(String mapName) {
		gameMapSettings.getSimpleSetting("mapName", String.class);

	}

	@Override
	public String getMapName() {
		return gameMapSettings.getSimpleSetting("name", String.class);

	}


	public ObjectMap<String, Building> getBuildings() {
		return buildings;
	}
}
