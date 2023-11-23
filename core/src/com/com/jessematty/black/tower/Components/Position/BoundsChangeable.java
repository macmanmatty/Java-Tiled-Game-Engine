package com.jessematty.black.tower.Components.Position;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;

public class BoundsChangeable  implements Component {

    private ObjectMap<String, ObjectMap<String, EntityBounds>> bounds = new ObjectMap<>();
    private boolean positionChanged; // whether or not the position changed

    public boolean isPositionChanged() {
        return positionChanged;
    }

    public void setPositionChanged(boolean positionChanged) {
        this.positionChanged = positionChanged;
    }

    public ObjectMap<String, ObjectMap<String, EntityBounds>> getBounds() {
        return bounds;
    }

    public Polygon getBounds(Direction direction, String action){
        ObjectMap<String , EntityBounds> boundsObjectMap=this.bounds.get(direction.toString());
        if(bounds!=null){
            EntityBounds entityBounds=boundsObjectMap.get(action);
            if(entityBounds!=null) {

                return  entityBounds.getBounds();

        }

        }


        return null;

    }

    public Vector2 getBoundsOffset(Direction direction, String action){
        ObjectMap<String , EntityBounds> boundsObjectMap=this.bounds.get(direction.toString());
        if(bounds!=null){
            EntityBounds entityBounds=boundsObjectMap.get(action);
            if(entityBounds!=null) {

                return  entityBounds.getBoundsOffset();

            }

        }


        return null;

    }



}
