package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.RemoveOwnerComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;

public class RemoveOwnerSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Attachable> attachableComponentMapper;
    private ComponentMapper<RemoveOwnerComponent> removeOwnerComponentComponentMapper;


    public RemoveOwnerSystem(MapDraw draw) {
        super(draw);


    }

    @Override
    public void addedToEngine(Engine engine) {
        attachableComponentMapper=getGameComponentMapper().getAttachableComponentMapper();
        removeOwnerComponentComponentMapper=getGameComponentMapper().getRemoveOwnerComponentComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities= getEngine().getEntitiesFor(Family.all(RemoveOwnerComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            RemoveOwnerComponent removeOwnerComponent=removeOwnerComponentComponentMapper.get(entity);
            String entityToRemoveID=removeOwnerComponent.getEntityToRemoveID();
            Entity entityToRemove=getWorld().getEntity(entityToRemoveID);
            EntityUtilities.detachEntity(getWorld(), entity, entityToRemove, true);
            entity.remove(RemoveOwnerComponent.class);



        }



        super.update(deltaTime);
    }


}



