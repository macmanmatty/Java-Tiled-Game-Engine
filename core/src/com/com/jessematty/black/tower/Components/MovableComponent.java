package com.jessematty.black.tower.Components;
import com.badlogic.gdx.math.Vector3;
import com.jessematty.black.tower.Components.Actions.ActionableComponent;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

/**
 * component for objects that move
 */
public class MovableComponent implements ActionableComponent {
    /**
     * // the current speed in world units per second
     */
    private float currentSpeed=1;
    /**
     * / the angle of movement in radians
     */
    private float moveAngle;
    /**
     * the x, y, and z velocities of the object
     */
    private Vector3 velocity= new Vector3(); // the x and y  speeds
    /**
     * // whether or not the entity moved
     */
    private boolean moved;
    /**
     * the x, y, and z distances of the object moved
     */
    private  Vector3 distanceMoved= new Vector3();
    /**
     * the tile to move to for path finding
     */
    private  transient LandSquareTile locationToMoveTo;
    /**
     * whether or not movement in 8 directions is possible
     */
    private boolean eightDirections=true;

    /**
     *
     * whether or not the movable has already collided with  something
     * during  the current tick of the game movement
     * reset to false on next move.
     */
    boolean collided;
    public float getCurrentSpeed() {
        return currentSpeed;
    }
    public void setCurrentSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
    public float getMoveAngle() {
        return moveAngle;
    }
    public void setMoveAngle(float moveAngle) {
        this.moveAngle = moveAngle;
    }

    /**
     * functions to set the move angle in radians
     * to a direction
     */
    public void moveUp() { // moves owner up
        moveAngle = 0;
    }
    public void moveDown() { // moves owner down
        moveAngle = (float) Math.PI;
    }
    public void moveRight() { //
        moveAngle =  1.5708f;
    }
    public void moveLeft() {
        moveAngle =  4.71239f;
    }
    public void moveLeftUp() { //
        moveAngle =  5.5065138f;
    }
    public void moveLeftDown() { //
        moveAngle = 3.9357175f;
    }
    public void moveRightUp() { //
        moveAngle =  0.7941248f;
    }
    public void moveRightDown() {
        moveAngle = 2.3649211f;
    }
    public void stop(){
        currentSpeed =0;
        velocity.x=0;
        velocity.y=0;
        moved=false;
    }
    public Vector3 getVelocity() {
        return velocity;
    }
    public void setVelocity(float x, float y, float z) {
        this.velocity.x=x;
        this.velocity.y=y;
        this.velocity.z=z;
    }
    public boolean isMoved() {
        return moved;
    }
    public void setMoved(boolean moved) {
        this.moved = moved;
    }
    public Vector3 getDistanceMoved() {
        return distanceMoved;
    }
    public void setDistanceMoved(float x,  float y, float z) {
        this.distanceMoved.x=x;
        this.distanceMoved.y=y;
        this.distanceMoved.z=z;
    }
    public LandSquareTile getLocationToMoveTo() {
        return locationToMoveTo;
    }
    public void setLocationToMoveTo(LandSquareTile locationToMoveTo) {
        this.locationToMoveTo = locationToMoveTo;
    }
    public boolean isEightDirections() {
        return eightDirections;
    }
    public void setEightDirections(boolean eightDirections) {
        this.eightDirections = eightDirections;
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }
}
