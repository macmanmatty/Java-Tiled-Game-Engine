package com.jessematty.black.tower.Systems.Ai;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Other.AIComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;
public class AiSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<AIComponent> aiComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private BitMask bitMask= new BitMask();
    @Override
    public void addedToEngine(Engine engine) {
        aiComponentMapper= GameComponentMapper.getAiComponentMapper();
    }
    public AiSystem(int order, MapDraw draw) {
        super(order, draw);
    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(AIComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {
         AIComponent aiComponent =  aiComponentMapper.get(entities.get(count));
         aiComponent.getZRPGBrainComponen().aiAct(deltaTime);
        }
    }
}
