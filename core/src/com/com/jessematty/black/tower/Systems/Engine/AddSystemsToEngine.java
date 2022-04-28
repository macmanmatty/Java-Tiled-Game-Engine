package com.jessematty.black.tower.Systems.Engine;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;
import com.jessematty.black.tower.Components.Systems.AddSystemsComponent;
import com.jessematty.black.tower.Components.AttachEntity.AddOwnerComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class AddSystemsToEngine extends GameEntitySystem { // checks  the die when zero  stats  for all entities  if all stats are zero
    // marks the entities  as dying.

    private ComponentMapper<AddSystemsComponent> systemComponentComponentMapper;
    private ImmutableArray<Entity> entities;


    public AddSystemsToEngine(MapDraw draw) {
        super(draw);


    }

    @Override
    public void addedToEngine(Engine engine) {
        systemComponentComponentMapper=GameComponentMapper.getAddSystemsComponentComponentMapper();


    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities = getEngine().getEntitiesFor(Family.all(AddOwnerComponent.class).get());

        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            AddSystemsComponent addSystemsComponent = systemComponentComponentMapper.get(entity);
            Array<Class<?extends GameEntitySystem>> systemsToAdd= addSystemsComponent.getSystemComponentArray();
            int sizes=systemsToAdd.size;
            Engine engine=getEngine();
           OrderedMap< Class<? extends EntitySystem>, EntitySystem> worldSystems=getWorld().getSystemsInWorld();
            for(int counts=0; counts<sizes; counts++){

                engine.addSystem(worldSystems.get(systemsToAdd.get(count)));

            }


        }
    }


}



