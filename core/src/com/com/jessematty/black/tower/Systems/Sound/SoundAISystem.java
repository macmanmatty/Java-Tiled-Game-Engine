package com.jessematty.black.tower.Systems.Sound;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.jessematty.black.tower.Components.Other.SoundComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public  class SoundAISystem extends GameEntitySystem {
    private ComponentMapper<SoundComponent> noisies = ComponentMapper.getFor(SoundComponent.class);
    private ComponentMapper<PositionComponent> positions=ComponentMapper.getFor(PositionComponent.class);
    ImmutableArray<Entity> entities;
    Batch batch;
    public SoundAISystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        entities=getEngine().getEntitiesFor(Family.all(SoundComponent.class, PositionComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            SoundComponent soundComponent = noisies.get(entity);
            if(soundComponent.isPlaySound()) {
                }
            }
}
}