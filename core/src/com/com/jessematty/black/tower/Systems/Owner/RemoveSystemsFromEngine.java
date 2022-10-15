package com.jessematty.black.tower.Systems.Owner;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.Components.Systems.RemoveSystemsComponent;
import com.jessematty.black.tower.Components.AttachEntity.RemoveOwnerComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class RemoveSystemsFromEngine extends GameEntitySystem { // checks  the die when zero  stats  for all entities  if all stats are zero
    // marks the entities  as dying.

    private ComponentMapper<RemoveSystemsComponent> systemComponentComponentMapper;
    private ImmutableArray<Entity> entities;


    public RemoveSystemsFromEngine(MapDraw draw) {
        super(draw);


    }

    @Override
    public void addedToEngine(Engine engine) {
        systemComponentComponentMapper=GameComponentMapper.getRemoveSystemsComponentComponentMapper();


    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities = getEngine().getEntitiesFor(Family.all(RemoveOwnerComponent.class).get());

        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            RemoveSystemsComponent RemoveSystemsComponent = systemComponentComponentMapper.get(entity);
            Array<Class<?extends GameEntitySystem>> systemsToRemove= RemoveSystemsComponent.getSystemComponentArray();
            int sizes=systemsToRemove.size;
            Engine engine=getEngine();
           OrderedMap< Class<? extends EntitySystem>, EntitySystem> worldSystems=getWorld().getSystemsInWorld();
            for(int counts=0; counts<sizes; counts++){

               engine.removeSystem(worldSystems.get(systemsToRemove.get(count)));

            }


        }
    }


}



