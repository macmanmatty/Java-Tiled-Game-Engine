package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Maps.World;



public class WorldUtilities {

    /**
     * converts and array of entity ids to an array entities
     *
     * @param world
     * @param entityIds
     * @return
     */
    public static Array<Entity> getEntitiesFromId(World world, Array<String> entityIds){
        Array<Entity> entities= new Array<>();
        for(String entityId: entityIds){
          Entity entity=  world.getEntity(entityId);
          if(entity!=null){
              entities.add(entity);
          }
        }
        return  entities;
    }





}
