package com.jessematty.black.tower.Generators.Entity;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Copy.CopyObject;
import com.jessematty.black.tower.Maps.World;
public class EntityGenerator {
    protected  static CopyObject copyObject;
    protected  World world;
    protected GameAssets gameAssets;
    public EntityGenerator(World world, GameAssets gameAssets) {
        this.world = world;
        this.gameAssets = gameAssets;
        copyObject = new CopyObject(gameAssets);
    }
   //  creates  a basic entity  with all the components requires by the engine  and returns its entity container for easier access of it components



    // creates a deep copy of a  a given entity
    public static Entity  copyEntity(Entity  entity){

       return  copyObject.copyObject(entity, Entity.class);

    }


}
