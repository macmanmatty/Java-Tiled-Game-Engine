package com.jessematty.black.tower.Components.Position;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.Generators.Components.Animation.AnimationDirections;

import java.util.Map;

/**
 * component for  an Entity whose bounds change on direction  and / or action
 */
public class BoundsChangeableComponent implements Component {
    private boolean eightDirections;

    /**
     * the map of map of entity bounds
     * key 1= action
     * key 2= direction.toString();
     * value = the Entity Bounds Object
     */
    private ObjectMap<String, ObjectMap<String, EntityBounds>> bounds = new ObjectMap<>();
    private boolean positionChanged; // whether or not the position changed
    /**
     * the default bounds in case no bounds are set for a given action and direction
     */
    private EntityBounds defaultBounds= new EntityBounds();
    public boolean isPositionChanged() {
        return positionChanged;
    }
    public void setPositionChanged(boolean positionChanged) {
        this.positionChanged = positionChanged;
    }
    public ObjectMap<String, ObjectMap<String, EntityBounds>> getBounds() {
        return bounds;
    }
    /**
     * returns the EntityBounds object for a given direction and action
     * if no object for a given action actions exists attempts to retrieve bounds for an action named default
     * if no bounds exist for a given action or direction
     * will return the default bounds
     * @param direction
     * @param action
     * @return
     */
    public EntityBounds getBounds(Direction direction, String action){
        ObjectMap<String , EntityBounds> boundsObjectMap=this.bounds.get(action);
        if (boundsObjectMap == null) {
            boundsObjectMap = this.bounds.get("default");
        }
        if(boundsObjectMap!=null) {
            EntityBounds entityBounds = null;

            if (eightDirections) {
                entityBounds = boundsObjectMap.get(direction.toString());
            } else {
                entityBounds = boundsObjectMap.get(Direction.getBaseDirection(direction).toString());

            }
            if (entityBounds != null) {
                return entityBounds;
            }
        }
            return defaultBounds;
    }

    public void addBounds(Direction direction, String action, EntityBounds entityBounds){
        ObjectMap<String, EntityBounds> boundsObjectMap=bounds.get(action);
        if ((boundsObjectMap==null)){
            boundsObjectMap=new ObjectMap<>();
            bounds.put(action, boundsObjectMap);
        }
        boundsObjectMap.put(direction.toString(), entityBounds);
    }


    public boolean isEightDirections() {
        return eightDirections;
    }

    public void setEightDirections(boolean eightDirections) {
        this.eightDirections = eightDirections;
    }
}
