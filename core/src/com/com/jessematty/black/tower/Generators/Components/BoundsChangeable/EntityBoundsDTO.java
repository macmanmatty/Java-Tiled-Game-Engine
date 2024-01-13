package com.jessematty.black.tower.Generators.Components.BoundsChangeable;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
/**
 * DTO class for an Entity Bounds Object Object @see Bounds.class
 */
public class EntityBoundsDTO {
    /**
     * the action corresponding to the animation
     */
    private String action="default";
    /**
     * the direction of the entity
     */
    private  String direction;
   
    /**
     * whether or not  the bounds is a rectangle
     * 
     * */
    
   private  boolean isRectangle;
    /**
     * the x and y offsets for the bounds from the  entities
     * x and y position  on the map
     */
    private float xOffset;
    private float yOffset;
    /**
     * the  bounds of the entity represented as libGDX Polygon
     *
     */
    private Polygon bounds= new Polygon();
    /**
     * when in debug mode whether or not  to draw the bounds
     */
    boolean drawBounds=true;

    private float boundsX;
    private float boundsY;

    private boolean hasBounds=true;
    
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction.toString();
    }
    public boolean isRectangle() {
        return isRectangle;
    }
    public void setRectangle(boolean rectangle) {
        this.isRectangle = rectangle;
    }
    public float getxOffset() {
        return xOffset;
    }
    public void setOffsets(float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset=yOffset;
    }
    public float getyOffset() {
        return yOffset;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }
    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    public Polygon getBounds() {
        return bounds;
    }
    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
    }
    public boolean isDrawBounds() {
        return drawBounds;
    }
    public void setDrawBounds(boolean drawBounds) {
        this.drawBounds = drawBounds;
    }

    public float getBoundsX() {
        return boundsX;
    }
    public float getBoundsY() {
        return boundsY;
    }

    public void setBounds( float boundsX, float boundsY) {
        this.boundsY = boundsY;
        this.boundsX = boundsX;
        isRectangle=true;

    }

    public boolean isHasBounds() {
        return hasBounds;
    }

    public void setHasBounds(boolean hasBounds) {
        this.hasBounds = hasBounds;
    }
}
