package com.jessematty.black.tower.Maps;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
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
	private ObjectMap<String, Building> buildings = new ObjectMap<>();
	int numberOfBuildings;
	public LandMap() {
		settings= new com.jessematty.black.tower.Maps.Settings.LandMapSettings();
		lightChanges=true;
	}



	public LandMap(GameAssets assetManager, Skin skin) {
		super(assetManager,  skin);
		hasEnemies = true;
		tiledMap= new TiledMap();
		settings= new com.jessematty.black.tower.Maps.Settings.LandMapSettings();
		lightChanges=true;
	}
	public LandMap(GameAssets assetManager) {
		super(assetManager);
		hasEnemies = true;
		tiledMap= new TiledMap();
		settings= new com.jessematty.black.tower.Maps.Settings.LandMapSettings();
		lightChanges=true;
	}
	public LandMap( int xSize, int ySize, GameAssets gameAssets) {
		super( xSize, ySize, gameAssets);
		settings= new com.jessematty.black.tower.Maps.Settings.LandMapSettings();
		settings.setxSize(xSize);
		settings.setySize(ySize);
		lightChanges=true;
	}
	public LandMap(MapDraw draw, int xSize, int ySize, GameAssets gameAssets, Skin skin) {
		super( xSize, ySize, gameAssets, skin);
		settings= new LandMapSettings();
		settings.setxSize(xSize);
		settings.setySize(ySize);
		lightChanges=true;
		lightChangeAmount=.1f;
	}
	public LandSquareTile getMapSquare(int xlocation, int ylocation) {
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
	public void deSerialize(GameAssets assets, GameMapSettings settings) {
		super.deSerialize(assets, settings);

		this.gameAssets = assets;
		tiledMap = gameAssets.loadExternalTMXMap(tiledMapPath);


	}
	public void mapTurnActions(float deltaTime, GameTime gameTime){ // method for updating the map and the tiles on it each game loop call
		this.gameTime=gameTime;
		if(lightChanges==true) {
			setDayLightAmount(gameTime.getTotalGameTimeLaspedInSeconds());

		}

	}
  public void addEnemies(){
  }
@Override
public LandSquareTile[][] getMap() {
	// TODO Auto-generated method stub
	return map;
}
	
	private ArrayList<Integer> findNumbers( int [] [] map ){
		ArrayList<Integer> numbers= new ArrayList<Integer>();
		numbers.add(map[0][0]);
		for (int countx=0; countx<xSize;  countx++){
			for (int county=0; county<ySize; county++){
				int size=numbers.size();
				boolean in=false;
				for (int count=0; count<size; count++) {
					if (numbers.get(count).equals(map[countx][county])) {
						in=true;
						break;
					}
				}
				if (in==false) {
					numbers.add(map[countx][county]);
				}
			}
		}
		Collections.sort(numbers);
		return numbers;
	}
public void saveMap(){
		try {
			File mapFolder = new File("/maps");
			if (!(mapFolder.exists())) {
				mapFolder.mkdir();
			}
			FileOutputStream fileOut = new FileOutputStream("/maps/"+mapName);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(this);
			objectOut.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
}
public void makeRain(){
}



	public void addBuilding(Building building){

		this.buildings.put(building.getBuildingID(), building);

	}


		public Building getBuilding(String id){

		return buildings.get(id);
		}


	public int getNumberOfBuildings() {
		return numberOfBuildings;
	}
	public void setNumberOfBuildings(int numberOfBuildings) {
		this.numberOfBuildings = numberOfBuildings;
	}

	public ObjectMap<String, Building> getBuildings() {
		return buildings;
	}
}
