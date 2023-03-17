package com.jessematty.black.tower.Systems.Owner;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.DetachEntityEvent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class RemoveOwnerSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Attachable> attachableComponentMapper;
    private ComponentMapper<DetachEntityEvent> removeOwnerComponentComponentMapper;


    public RemoveOwnerSystem(MapDraw draw) {
        super(draw);


    }

    @Override
    public void addedToEngine(Engine engine) {
        attachableComponentMapper=GameComponentMapper.getAttachableComponentMapper();
        removeOwnerComponentComponentMapper= GameComponentMapper.getRemoveOwnerComponentComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities= getEngine().getEntitiesFor(Family.all(DetachEntityEvent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            DetachEntityEvent detachEntityEvent =removeOwnerComponentComponentMapper.get(entity);
            String entityToRemoveID= detachEntityEvent.getEntityToRemoveID();
            Entity entityToRemove=getWorld().getEntity(entityToRemoveID);
            EntityUtilities.detachEntity(getWorld(), entity, entityToRemove);
            entity.remove(DetachEntityEvent.class);

        }



        super.update(deltaTime);
    }


}



