package com.jessematty.black.tower.Components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.HashMap;

public class Position implements Component { //  a  position  and bounds component for entities. This is REQUIRED to bed added to entities
    // the engine will throw an illegal operation exeception  if an entity is added  with  out this component attached.

    private  float screenLocationX = 0f; // the location the screen of the current map
    private float screenLocationY = 0f;
    private int tileLocationX; // the current tile locations
    private int tileLocationY;
    private Direction direction=Direction.DOWN; // the current dierction of the entity
    private transient Array<LandSquareTile> tiles= new Array<LandSquareTile>(); // the tiles the entity occupies

    private boolean positionChanged; // whether or not the position changed
    private Polygon bounds= new Polygon( new float[] {0,0,0,0,0,0,0,0}); // the entity bounds
    private Polygon oldBounds= new Polygon( new float[] {0,0,0,0,0,0,0,0}); // the entities old bounds
    private HashMap<Direction, Polygon> directionalBounds= new HashMap<Direction, Polygon>();
    private float boundsX; // width of the bounds as if they were a square
    private float boundsY; // height of the bounds  as if they were a square
    private float [] boundsVerticies; // the points of the  shape that makes up bounds in x, y pairs
    private boolean boundsIsRectangle;
    private float boundsXOffset; // the offset of the bounds from the screen locations where  the entities texture region (if it has one) will be drawn
    private float boundsYOffset;
    private boolean hasBounds; // does the entity currently have bounds
    private int mapWorldLocationX;
    private int mapWorldLocationY;
    private  String buildingID; // empty  = not in building  else number corrospondes to the  to the building in the given land map array slot.
    private float height;
    private float heightFromGround;

    public Position(NumericStats stats) {
    }

    public Position() {
    }

    public Polygon getBounds() {
        return bounds;
    }
    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
        bounds.setPosition(screenLocationX, screenLocationY);
        boundsIsRectangle=false;
    }
    public Rectangle getBoundsBoundingRectangle(){
        return bounds.getBoundingRectangle();
    }
    public HashMap<Direction, Polygon> getDirectionalBounds() {
        return directionalBounds;
    }
    public void setDirectionalBounds(HashMap<Direction, Polygon> directionalBounds) {
        this.directionalBounds = directionalBounds;
    }
    public void setSetScreenLocations(float x, float y){
        bounds.setPosition(x+boundsXOffset, y+boundsYOffset);
        screenLocationX=x;
        screenLocationY=y;
    }
    public void setScreenLocationX(float screenLocationX) {
        this.screenLocationX = screenLocationX;
        bounds.setPosition(this.screenLocationX+boundsXOffset, bounds.getY());
    }
    public void setScreenLocationY(float screenLocationY) {
        this.screenLocationY = screenLocationY;
        bounds.setPosition(bounds.getX(), this.screenLocationY+boundsYOffset);
    }
    public void setBounds(float x, float y){// set the bounds of the object to a new rectangle of the given x and y length
        this.boundsX=x;
        this.boundsY=y;
        boundsVerticies = new float[]{  0 , 0 ,  0 ,  0  + boundsY,  0   + boundsX,  0   + boundsY,  0   + boundsX,  0 };
        bounds = new Polygon(boundsVerticies);
        bounds.setPosition(screenLocationX, screenLocationY);
        boundsIsRectangle=true;
    }
    public float getBoundsX() { // returns the x size of the bounds rectangle if the bounds is a rectangle else  returns the width of the bounding rectangle
        if (boundsIsRectangle=true){
            return  boundsX;
        }
        else {
            return bounds.getBoundingRectangle().getWidth();
        }
    }
    public void setBoundsX(float boundsX) {
        this.boundsX = boundsX;
    }
        public float getBoundsY() { // returns the y size of the bounds rectangle if the bounds is a rectangle else  returns the height of the bounding rectangle
        if (boundsIsRectangle=true){
            return  boundsY;
        }
        else {
            return bounds.getBoundingRectangle().getHeight();
        }
    }
    public void setBoundsY(float boundsY) {
        this.boundsY = boundsY;
    }
    public float getBoundsXOffset() {
        return boundsXOffset;
    }
    public void setBoundsXOffset(float boundsXOffset) {
        this.boundsXOffset = boundsXOffset;
        bounds.setPosition(screenLocationX+boundsXOffset, bounds.getY());
    }
    public float getBoundsYOffset() {
        return boundsYOffset;
    }
    public void setBoundsYOffset(float boundsYOffset) {
        this.boundsYOffset = boundsYOffset;
        bounds.setPosition(bounds.getX(), screenLocationY+boundsYOffset);
    }
    public float getScreenLocationX() {
        return screenLocationX;
    }
    public float getScreenLocationY() {
        return screenLocationY;
    }
    public int getTileLocationX() {
        return tileLocationX;
    }
    public void setTileLocationX(int tileLocationX) {
        this.tileLocationX = tileLocationX;
    }
    public int getTileLocationY() {
        return tileLocationY;
    }
    public void setTileLocationY(int tileLocationY) {
        this.tileLocationY = tileLocationY;
    }
    public Array<LandSquareTile> getTiles() {
        return tiles;
    }
    public void setTiles(Array<LandSquareTile> tiles) {
        this.tiles = tiles;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public boolean isPositionChanged() {
        return positionChanged;
    }
    public void setPositionChanged(boolean positionChanged) {
        this.positionChanged = positionChanged;
    }

    public void removeBounds(){ // set the bounds to a new sqaure of all zeros effectivly removing the entities bounds
        oldBounds=bounds;
       bounds= new Polygon( new float[] {0,0,0,0,0,0,0,0});
        hasBounds=false;
    }
    private void reInstateBounds(){ // reinstates the entities old bounds after removing them
        bounds=oldBounds;
        hasBounds=true;
    }
    public boolean isBoundsIsRectangle() {
        return boundsIsRectangle;
    }
    public boolean isHasBounds() {
        return hasBounds;
    }
    public int getMapWorldLocationX() {
        return mapWorldLocationX;
    }
    public void setMapWorldLocationX(int mapWorldLocationX) {
        this.mapWorldLocationX = mapWorldLocationX;
    }
    public int getMapWorldLocationY() {
        return mapWorldLocationY;
    }
    public void setMapWorldLocationY(int mapWorldLocationY) {
        this.mapWorldLocationY = mapWorldLocationY;
    }

    public float getHeightFromGround() {
        return heightFromGround;
    }

    public void setHeightFromGround(float heightFromGround) {
        this.heightFromGround = heightFromGround;
    }

    public String getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(String buildingID) {
        this.buildingID = buildingID;
    }

}
