package com.jessematty.black.tower.Components.Position;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * class for entity bounds
 */
public class EntityBounds {

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

    float boundsX;

    float boundsY;
    boolean hasBounds=true;


    public Polygon getBounds() {
        return bounds;
    }

    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
        hasBounds=true;

    }

    /**
     * creates a rectangular shaped bounds based on an x and y length;
     * @param x the x length of the bounds
     * @param y the y length of the bounds
     */
    public void setBounds(float x, float y){// set the bounds of the object to a new rectangle of the given x and y length
        if(x<0){
            x=0;
        }
        if(y<0){
            y=0;
        }
        this.boundsX=x;
        this.boundsY=y;
       float [] boundsVertices = new float[]{  0 , 0 ,  0 ,  0  + boundsY,  0   + boundsX,  0   + boundsY,  0   + boundsX,  0 };
        bounds = new Polygon(boundsVertices);
        isBoundsRectangle=true;
        hasBounds=true;
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

    public void setBoundsOffset(Vector2 boundsOffset) {
        this.boundsOffset = boundsOffset;
    }

    public void setBoundsRectangle(boolean boundsRectangle) {
        isBoundsRectangle = boundsRectangle;
    }

    public float getBoundsX() {
        return boundsX;
    }

    public float getBoundsY() {
        return boundsY;
    }

    public boolean isHasBounds() {
        return hasBounds;
    }

    public void setHasBounds(boolean hasBounds) {
        this.hasBounds = hasBounds;
    }
}
