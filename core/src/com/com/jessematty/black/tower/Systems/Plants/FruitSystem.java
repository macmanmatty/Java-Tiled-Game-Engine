package com.jessematty.black.tower.Systems.Plants;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Other.Fruit;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class FruitSystem extends GameEntitySystem {

    private ComponentMapper<MovableComponent> moveables= ComponentMapper.getFor(MovableComponent.class);
    private ComponentMapper<ActionComponent> actions=ComponentMapper.getFor(ActionComponent.class);
    private ImmutableArray<Entity> entities;


    public FruitSystem(MapDraw draw) {
        super(draw);


    }

    @Override
    public void update(float deltaTime) {

        entities= getEngine().getEntitiesFor(Family.all(Fruit.class, ActionComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            MovableComponent movableComponent =moveables.get(entity);
            ActionComponent actionComponent = actions.get(entity);
            movableComponent.setCurrentSpeed(0);




        }



        super.update(deltaTime);
    }


}



