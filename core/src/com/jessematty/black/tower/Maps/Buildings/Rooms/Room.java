package com.jessematty.black.tower.Maps.Buildings.Rooms;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.SquareTiles.EnterenceSquareTile;

import java.util.ArrayList;

public   abstract  class  Room extends Building {
	protected  ArrayList<EnterenceSquareTile> enterenceTiles;


	public Room() {
	}

	public Room(GameAssets assetManager,Skin skin) {
		super(assetManager, skin);
	}

	public Room( int xSize, int ySize, GameAssets gameAssets) {
		super(xSize, ySize, gameAssets);
	}

	public Room( int xSize, int ySize, GameAssets gameAssets, Skin skin) {
		super( xSize, ySize, gameAssets, skin);
	}





	public ArrayList<EnterenceSquareTile> getEnterenceTiles() {
		return enterenceTiles;
	}

	public void setEnterenceTiles(ArrayList<EnterenceSquareTile> enterenceTiles) {
		this.enterenceTiles = enterenceTiles;
	}
}
