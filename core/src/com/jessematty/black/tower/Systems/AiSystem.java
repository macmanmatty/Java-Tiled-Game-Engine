package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AIComponent;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;

public abstract class AiSystem extends GameEntitySystem {

    private ImmutableArray<Entity> entities;
    private ComponentMapper<AIComponent> aiComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
    private BitMask bitMask= new BitMask();
    @Override
    public void addedToEngine(Engine engine) {

        aiComponentMapper=getGameComponentMapper().getAiComponentMapper();

    }

    public AiSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void update(float deltaTime) {

        entities=getEngine().getEntitiesFor(Family.all(AIComponent.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++) {
         AIComponent aiComponent =  aiComponentMapper.get(entities.get(count));
         aiComponent.getBrain().act(getDraw().getCurrentMap());

        }


    }
}
