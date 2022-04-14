package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.Rest;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class RestSystem extends GameEntitySystem {

    private ComponentMapper<MovableComponent> moveables= ComponentMapper.getFor(MovableComponent.class);
    private ComponentMapper<Action> actions=ComponentMapper.getFor(Action.class);
    private ImmutableArray<Entity> entities;


    public RestSystem(MapDraw draw) {
        super(draw);


    }

    @Override
    public void update(float deltaTime) {

        entities= getEngine().getEntitiesFor(Family.all(MovableComponent.class, Action.class, Rest.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            MovableComponent movableComponent =moveables.get(entity);
            Action action= actions.get(entity);
            action.setStat("rest");
            movableComponent.setCurrentSpeed(0);

        }



        super.update(deltaTime);
    }


}



