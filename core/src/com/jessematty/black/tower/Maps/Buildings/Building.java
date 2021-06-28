package com.jessematty.black.tower.Maps.Buildings;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jessematty.black.tower.GameBaseClasses.GameTimes.GameTime;
import com.jessematty.black.tower.Maps.Buildings.Rooms.Room;
import com.jessematty.black.tower.Maps.GameMap;

import java.util.ArrayList;
import java.util.UUID;

public  class Building extends GameMap  {
	protected int numberOfStories;
	protected int xStart;
	protected int yStart;
	protected ArrayList<Room> stories  = new ArrayList<Room>();
	protected ArrayList<Integer> buildingLengths=  new ArrayList<Integer>();
		protected float shadowRegionX;
		protected float shadowRegionY;
		protected int upperLeftCornerTileX;
		protected int upperLeftCornerTileY;
		protected int landMapX;
		protected int landMapY;
		String buildingID;


	public Building() {

		buildingID= UUID.randomUUID().toString();

	}

	public Building(int xSize, int ySize) {
		super(xSize, ySize);
		buildingID= UUID.randomUUID().toString();

	}

	protected void makeBuildingShadow(){ // makes the shadow texture using pixmaps for y part each part of the building.
		int size=buildingLengths.size();
		ArrayList<Pixmap> pixMaps=new ArrayList<Pixmap>();
		int maxBuildingLength=0;
		int minBuildingLength= Integer.MAX_VALUE;
		for (int count=0; count<size; count++){
			int buildinngLength=buildingLengths.get(count);
			if(buildinngLength>maxBuildingLength){
				maxBuildingLength=buildinngLength;
			}
			if(buildinngLength<minBuildingLength){
				minBuildingLength=buildinngLength;
			}
			Pixmap pixmap=new Pixmap(32, buildinngLength*32, Format.RGBA8888);
			pixmap.setColor(0,0,0,.3f);
			pixmap.fill();
			pixMaps.add(pixmap);
		}
		Pixmap pixmap=new Pixmap(size*32, maxBuildingLength*32, Format.RGBA8888);
		for (int count=0; count<size; count++){
		pixmap.drawPixmap(pixMaps.get(count), 0, count*32);
		}
		TextureRegion region=new TextureRegion(new Texture(pixmap));
		float shadowY=upperLeftCornerTileY;
	}

	@Override
	public void mapTurnActions(float deltaTime, GameTime gameTime) {
		super.mapTurnActions(deltaTime, gameTime);
		//castShadow(gameTime);
	}
	public int getXTiles() {
		return xTiles;
	}
	public int getYTiles() {
		return yTiles;
	}


	public int getxStart() {
		return xStart;
	}
	public void setxStart(int xStart) {
		this.xStart = xStart;
	}
	public int getyStart() {
		return yStart;
	}
	public void setyStart(int yStart) {
		this.yStart = yStart;
	}
	public int getNumberOfStories() {
		return numberOfStories;
	}
	public ArrayList<Room> getRooms() {
		return stories;
	}
	public void setRooms(ArrayList<Room> stories) {
		this.stories = stories;
	}
	public void addRoom(Room story){
		this.stories.add(story);
	}
	public void removeRoom(Room story){
		this.stories.remove(story);
	}

	public int getLandMapX() {
		return landMapX;
	}

	public void setLandMapX(int landMapX) {
		this.landMapX = landMapX;
	}

	public int getLandMapY() {
		return landMapY;
	}

	public void setLandMapY(int landMapY) {
		this.landMapY = landMapY;
	}

	public String getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}
}
