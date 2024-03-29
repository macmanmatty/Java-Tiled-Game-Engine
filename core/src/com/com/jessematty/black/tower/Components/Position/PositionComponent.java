package com.jessematty.black.tower.Components.Position;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

/**
 * //  a  position  and bounds component for entities.
 *
 */

public class PositionComponent implements Component {
    /**
     * entity position in world units
     */
    private  float locationX = 0f;
    private float locationY = 0f;
    private int tileLocationX; // the current location of the entity in tiles units  where is x and y are the lower left tile aka tiles.get(0);
    private int tileLocationY;

    /**
     * // the current direction of the entity
     */
    private Direction direction=Direction.DOWN;
    private transient Array<LandSquareTile> tiles= new Array<LandSquareTile>(); // the tiles the entity occupies
    private boolean positionChanged; // whether or not the position changed
    private Polygon bounds= new Polygon( new float[] {0,0,0,0,0,0,0,0}); // the entity bounds
    private Polygon oldBounds=null; // the entities old bounds
    private float boundsX; // width of the bounds as if they were a rectangle
    private float boundsY; // height of the bounds  as if they were a rectangle
    private float [] boundsVertices; // the points of the  shape that makes up bounds in x, y pairs
    private boolean boundsIsRectangle;
    private float boundsXOffset; // the offset of the bounds from the screen locations where  the entities texture region (if it has one) will be drawn
    private float boundsYOffset;
    private boolean hasBounds=true; // does the entity currently have bounds
    private float height; // entities physical  height or z coordinate of the entity
    private float heightFromGround; // height from the ground
    private boolean directionChanged; // flag for direction changing
    private boolean drawBounds;
    private boolean onGround;
    /**
     * if this is not null this is id of the container the entity is located in
     */
    private String containerEntityId="";
    private String mapId="";
    public PositionComponent() {
    }
    public Polygon getBounds() {
        return bounds;
    }
    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
        bounds.setPosition(locationX+boundsXOffset, locationY+boundsYOffset);
        boundsIsRectangle=false;
        hasBounds=true;
    }
    public Rectangle getBoundsBoundingRectangle(){
        return bounds.getBoundingRectangle();
    }
    public void setPosition(float x, float y){
        bounds.setPosition(x+boundsXOffset, y+boundsYOffset);
        locationX =x;
        locationY =y;
        positionChanged=true;
    }
    public void setLocationX(float locationX) {
        this.locationX = locationX;
        bounds.setPosition(this.locationX +boundsXOffset, bounds.getY());
    }
    public void setLocationY(float locationY) {
        this.locationY = locationY;
        bounds.setPosition(bounds.getX(), this.locationY +boundsYOffset);
    }
    /**
     * creates a rectangular shaped bounds based on an x and y length
     * and then sets teh position of the bounds
     * @param x the x length of the bounds
     * @param y the y length of the bounds
     */
    public void setBounds(float x, float y){// set the bounds of the object to a new rectangle of the given x and y length
        this.boundsX=x;
        this.boundsY=y;
        boundsVertices = new float[]{  0 , 0 ,  0 ,  0  + boundsY,  0   + boundsX,  0   + boundsY,  0   + boundsX,  0 };
        bounds = new Polygon(boundsVertices);
        bounds.setPosition(locationX+boundsXOffset, locationY+boundsYOffset);
        boundsIsRectangle=true;
        hasBounds=true;
    }

    /**
     * // returns the x size of the bounds rectangle if the bounds is a rectangle else  returns the width of the bounding rectangle
     * @return
     */
    public float getBoundsX() {
        if (boundsIsRectangle=true){
            return  boundsX;
        }
        else {
            return bounds.getBoundingRectangle().getWidth();
        }
    }
    /**
     * // returns the y size of the bounds rectangle if the bounds is a rectangle else  returns the width of the bounding rectangle
     * @return
     */
        public float getBoundsY() { // returns the y size of the bounds rectangle if the bounds is a rectangle else  returns the height of the bounding rectangle
        if (boundsIsRectangle=true){
            return  boundsY;
        }
        else {
            return bounds.getBoundingRectangle().getHeight();
        }
    }
    public float getBoundsXOffset() {
        return boundsXOffset;
    }
    public void setBoundsXOffset(float boundsXOffset) {
        this.boundsXOffset = boundsXOffset;
        bounds.setPosition(bounds.getX() +boundsXOffset, bounds.getY());
    }
    public float getBoundsYOffset() {
        return boundsYOffset;
    }
    public void setBoundsYOffset(float boundsYOffset) {
        this.boundsYOffset = boundsYOffset;
        bounds.setPosition(bounds.getX(), bounds.getY() +boundsYOffset);
    }
    public float getLocationX() {
        return locationX;
    }
    public float getLocationY() {
        return locationY;
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
        if(direction!=this.direction) {
            this.direction = direction;
            directionChanged=true;
        }
        else {
            directionChanged = false;
        }
    }
    public boolean isPositionChanged() {
        return positionChanged;
    }
    public void setPositionChanged(boolean positionChanged) {
        this.positionChanged = positionChanged;
    }

    /**
     * // sets the bounds to a new square of all zeros effectively removing the entities bounds
     */
    public void removeBounds(){
        oldBounds=bounds;
       bounds= new Polygon( new float[] {0,0,0,0,0,0,0,0});
        hasBounds=false;
    }
    /**
     * / // reinstates the entities old bounds after removing them
     */
    public void reInstateBounds(){
        if(oldBounds!=null && !hasBounds) {
            bounds = oldBounds;
            hasBounds=true;
        }

    }
    public boolean isBoundsIsRectangle() {
        return boundsIsRectangle;
    }
    public boolean isHasBounds() {
        return hasBounds;
    }
    public float getHeightFromGround() {
        return heightFromGround;
    }
    public void setHeightFromGround(float heightFromGround) {
        this.heightFromGround = heightFromGround;
        positionChanged=true;
    }
    public boolean isDirectionChanged() {
        return directionChanged;
    }
    // set an entities position
    public void setPosition(float x, float y, float z){
        setHeightFromGround(z);
        setLocationX(x);
        setLocationY(y);
        bounds.setPosition(x+boundsXOffset, y+boundsYOffset);
        positionChanged=true;
    }
    // moves an entities position  by a given amount
    public  void movePosition(float x, float y, float z){
        this.height=this.height+z;
        this.locationY=this.locationY+y;
        this.locationX=this.locationX+x;
        bounds.setPosition(x+bounds.getX(), y+bounds.getY());
        positionChanged=true;
    }
    public boolean isDrawBounds() {
        return drawBounds;
    }
    public void setDrawBounds(boolean drawBounds) {
        this.drawBounds = drawBounds;
    }
    public void setMapID(String id) {
        this.mapId=id;
    }

    public String getMapId() {
        return mapId;
    }

    public String getContainerEntityId() {
        return containerEntityId;
    }

    public void setContainerEntityId(String containerEntityId) {
        this.containerEntityId = containerEntityId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
