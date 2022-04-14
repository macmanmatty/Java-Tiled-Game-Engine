package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.RemoveFromEngine;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;

public class RemoveEntityFromEngineSystem extends GameEntitySystem {

    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;


    public RemoveEntityFromEngineSystem(MapDraw draw) {
        super( Integer.MAX_VALUE, draw);
    }


    @Override
    public void addedToEngine(Engine engine) {

        ownedComponentComponentMapper = GameComponentMapper.getOwnedComponentComponentMapper();
    }

    @Override
    public void update(float deltaTime) {

        ImmutableArray<Entity>   entities= getEngine().getEntitiesFor(Family.all(RemoveFromEngine.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            Array<Entity> ownedEntities= EntityUtilities.getAllConnectedEntities(entity, getWorld(), false);
           removeAttachedEntities(ownedEntities);
            getWorld().removeEntityFromWorld(entity);


        }






    }

    private  void removeAttachedEntities(Array<Entity> entities){

        int size=entities.size;
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            OwnedComponent ownedComponent=ownedComponentComponentMapper.get(entity);

            if(ownedComponent!=null && ownedComponent.isRemoveEntityFromEngineOnOwnerRemoval()){
                getWorld().removeEntityFromWorld(entity);
            }




        }

    }


}



