package com.jessematty.black.tower.Components.Position;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class EntityBounds {

   private Rectangle boundingRectangle;
    private Vector2 boundsRectangle= new Vector2();
    private Polygon bounds= new Polygon( new float[] {0,0,0,0,0,0,0,0}); // the entity bounds
    private float [] boundsVerticies; // the points of the  shape that makes up bounds in x, y pairs
   private Vector2 boundsOffset= new Vector2();

    private boolean isBoundsRectangle=true;
    private boolean drawBounds;


    public Rectangle getBoundingRectangle() {
        return this.bounds.getBoundingRectangle();
    }



    public Polygon getBounds() {
        return bounds;
    }

    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
    }

    public float[] getBoundsVerticies() {
        return boundsVerticies;
    }

    public void setBoundsVerticies(float[] boundsVerticies) {
        this.boundsVerticies = boundsVerticies;
    }

    public boolean isBoundsRectangle() {
        return isBoundsRectangle;
    }

    public void setBoundsRectangle(boolean boundsRectangle) {
        isBoundsRectangle = boundsRectangle;
    }

    public Vector2 getBoundsRectangle() {
        return boundsRectangle;
    }

    public void setBoundsRectangle(int x , int y) {
        this.boundsRectangle = new Vector2(x, y);
    }

    public Vector2 getBoundsOffset() {
        return boundsOffset;
    }

    public void setBoundsOffset(int x, int y) {
        this.boundsOffset = new Vector2(x, y);

    }

    public boolean isDrawBounds() {
        return drawBounds;
    }

    public void setDrawBounds(boolean drawBounds) {
        this.drawBounds = drawBounds;
    }
}
