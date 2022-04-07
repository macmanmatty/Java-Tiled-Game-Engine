package com.jessematty.black.tower.Editor;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntitySettable;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.MapSettable;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Maps.WorldSettable;

public class Update {
    
    private Array<EntitySettable>  entitySettables= new SnapshotArray<>();
    private Array<WorldSettable> worldSettables= new SnapshotArray<>();
    private Array<MapSettable> mapSettables= new SnapshotArray<>();
    
    public Array<EntitySettable> getEntitySettables() {
        return entitySettables;
    }
    
    public Array<WorldSettable> getWorldSettables() {
        return worldSettables;
    }

    
    public Array<MapSettable> getMapSettables() {
        return mapSettables;
    }
    
    public void setMap(GameMap map){
        for(MapSettable mapSettable: mapSettables){
            mapSettable.setMap(map);
        }
    }

    public void setWorld(World world){
        for(WorldSettable worldSettable: worldSettables){
            worldSettable.setWorld(world);
        }
    }
    public void setEntity(Entity entity){
        for(EntitySettable entitySettable: entitySettables){
            entitySettable.setEntity(entity);
        }
    }
}
