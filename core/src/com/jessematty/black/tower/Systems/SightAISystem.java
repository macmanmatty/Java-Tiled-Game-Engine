package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.jessematty.black.tower.Components.SoundComponent;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;


public  class SightAISystem extends GameEntitySystem{
    private ComponentMapper<SoundComponent> noisies = ComponentMapper.getFor(SoundComponent.class);
    private ComponentMapper<Position> positions=ComponentMapper.getFor(Position.class);
    ImmutableArray<Entity> entities;


    Batch batch;

    public SightAISystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {






    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);


        entities=getEngine().getEntitiesFor(Family.all(SoundComponent.class, Position.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){

            Entity entity=entities.get(count);
            SoundComponent soundComponent = noisies.get(entity);
            if(soundComponent.isPlaySound()) {



                }

            }


}



}