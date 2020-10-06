package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AddComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;

public  class AddComponentToEntity extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<AddComponent> addComponentComponentMapper;

    private BitMask bitMask= new BitMask();
    public AddComponentToEntity(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {

        addComponentComponentMapper =getGameComponentMapper().getAddComponentComponentMapper();

    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(AddComponent.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            AddComponent addComponent =addComponentComponentMapper.get(entity);
            entity.add(addComponent.getComponent());
            entity.remove(AddComponent.class);

        }
    }


}