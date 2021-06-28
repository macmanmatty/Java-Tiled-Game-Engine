package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.Transient;
import com.jessematty.black.tower.Components.Breather;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
@Transient

public class BreathSystem extends GameEntitySystem {

    private ComponentMapper<PositionComponent> positions;
    private ComponentMapper<Breather> breathers;

    ImmutableArray<Entity> entities;

    public BreathSystem(MapDraw draw) {
        super(draw);
    }


    @Override
    public void addedToEngine(Engine engine) {

        breathers=GameComponentMapper.getBreatherComponentMapper();
        positions= GameComponentMapper.getPositionComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all( PositionComponent.class, Breather.class).get());

        int size=entities.size();


                for(int count=0; count<size; count++){

                    Entity entity=entities.get(count);
                    PositionComponent position=positions.get(entity);



                }


    }


}
