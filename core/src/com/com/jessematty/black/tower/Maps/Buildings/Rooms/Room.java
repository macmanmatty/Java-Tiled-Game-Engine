package com.jessematty.black.tower.Maps.Buildings.Rooms;

import com.jessematty.black.tower.Maps.Buildings.Building;
import com.jessematty.black.tower.SquareTiles.EnterenceSquareTile;

import java.util.ArrayList;

public   abstract  class  Room extends Building {
	protected  ArrayList<EnterenceSquareTile> enterenceTiles;


	public Room() {
	}




	public ArrayList<EnterenceSquareTile> getEnterenceTiles() {
		return enterenceTiles;
	}

	public void setEnterenceTiles(ArrayList<EnterenceSquareTile> enterenceTiles) {
		this.enterenceTiles = enterenceTiles;
	}
}
