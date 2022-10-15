package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.jessematty.black.tower.Components.AIComponent;
import com.jessematty.black.tower.Components.Eyes;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;


public  class SightAISystem extends GameEntitySystem{
    private ComponentMapper<Eyes> eyesComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ComponentMapper<AIComponent> aiComponentComponentMapper;
    ImmutableArray<Entity> entities;


    Batch batch;

    public SightAISystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        eyesComponentMapper= GameComponentMapper.getEyesComponentMapper();
        positionComponentMapper=GameComponentMapper.getPositionComponentMapper();
        aiComponentComponentMapper=GameComponentMapper.getAiComponentMapper();






    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);


        entities=getEngine().getEntitiesFor(Family.all(AIComponent.class, Eyes.class, PositionComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){

            Entity entity=entities.get(count);
            AIComponent aiComponent=aiComponentComponentMapper.get(entity);
            Eyes eyes=eyesComponentMapper.get(entity);
            PositionComponent position=positionComponentMapper.get(entity);





            }


}



}