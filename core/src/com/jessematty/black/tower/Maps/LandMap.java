package com.jessematty.black.tower.Maps;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.Print;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.Maps.Settings.GameMapSettings;
import com.jessematty.black.tower.Maps.Settings.LandMapSettings;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
public class LandMap extends GameMap implements Serializable {
	private transient ObjectMap<String, Building> buildings = new ObjectMap<>();
	public  LandMap() {
		gameMapSettings.setLightChanges(true);
	}

	public LandMap(int xSize, int ySize) {
		super(xSize, ySize);
		gameMapSettings.setLightChanges(true);
	}

	public void mapTurnActions(float deltaTime, GameTime gameTime){ // method for updating the map and the tiles on it each game loop call
		super.mapTurnActions(deltaTime, gameTime);
		if(gameMapSettings.isLightChanges()) {
			setDayLightAmount(gameTime.getTotalGameTimeLaspedInSeconds());

		}

	}


	public void addBuilding(Building building){

		this.buildings.put(building.getBuildingID(), building);

	}


		public Building getBuilding(String id){

		return buildings.get(id);
		}




	public ObjectMap<String, Building> getBuildings() {
		return buildings;
	}
}
