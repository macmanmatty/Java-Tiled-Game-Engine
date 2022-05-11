package com.jessematty.black.tower.GameBaseClasses.Utilities.PathFind.Validation;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.SolidObject;
import com.jessematty.black.tower.Maps.GameMap;

public class IsValidForEntity implements IsValid{
   private  GameMap map;
   private Entity entity;

    public IsValidForEntity(GameMap map, Entity entity) {
        this.map = map;
        this.entity=entity;
    }

    @Override
    public boolean isValid(int x, int y) {
        if(map.getTile(x,y).isEnterable()) {
            Array<Entity> entityList = map.getTile(x, y).getEntities(SolidObject.class);
            entityList.removeValue(entity, true);
            if (entityList.size> 0) {
                return false;
            }
            return true;
        }
        else{
            return  false;
        }
    }
    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
