package com.jessematty.black.tower.Components.Position;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * class for entity bounds
 */
public class EntityBounds {

    /**
     * the bounds of the entity expressed  as a libGDX Rectangle object
     */
   private Rectangle boundingRectangle= new Rectangle();
       /**
     * the bounds of the entity expressed  as a libGDX Polygon object
     */
    private Polygon bounds= new Polygon( new float[] {0,0,0,0,0,0,0,0}); // the entity bounds
    /**
     * the x, y  offset of the bounds from he entities x,y position in the world
     */
    private Vector2 boundsOffset= new Vector2();
    /**
     * is the bounds simply represented by a libGDX rectangle object
      */
    private boolean isBoundsRectangle=true;
    /**
     * if bounds drawing is enabled whether or not to draw this entities bounds
     */
    private boolean drawBounds;
    /**
     * whether or not to recalculate the bounding rectangle for the bounds
     */
    private boolean recalculateBoundingRectangle;


    public Rectangle getBoundingRectangle() {
        if(!isBoundsRectangle && (boundingRectangle==null || recalculateBoundingRectangle) ) {
             boundingRectangle=this.bounds.getBoundingRectangle();
             recalculateBoundingRectangle =false;
             return  boundingRectangle;
        }
        return  boundingRectangle;
    }

    public Polygon getBounds() {
        return bounds;
    }

    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
        recalculateBoundingRectangle =true;

    }

    public boolean isBoundsRectangle() {
        return isBoundsRectangle;
    }


    public Vector2 getBoundsOffset() {
        return boundsOffset;
    }

    public void setBoundsOffset(float x, float  y) {
        this.boundsOffset = new Vector2(x, y);

    }

    public boolean isDrawBounds() {
        return drawBounds;
    }

    public void setDrawBounds(boolean drawBounds) {
        this.drawBounds = drawBounds;
    }

    public void setBoundingRectangle(Rectangle boundingRectangle) {
        this.boundingRectangle = boundingRectangle;
    }

    public void setBoundsOffset(Vector2 boundsOffset) {
        this.boundsOffset = boundsOffset;
    }

    public void setBoundsRectangle(boolean boundsRectangle) {
        isBoundsRectangle = boundsRectangle;
    }
}
