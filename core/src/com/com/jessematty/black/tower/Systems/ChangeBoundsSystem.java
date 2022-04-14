package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Position.BoundsChangeable;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Transient;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

@Transient

public class ChangeBoundsSystem extends GameEntitySystem {
    private ComponentMapper<PositionComponent> positions;
    private ComponentMapper<Action> actionComponentMapper;
    private ComponentMapper<BoundsChangeable> boundsChangeableComponentMapper;

    ImmutableArray<Entity> entities;


    MovableComponent movableComponent;
    PhysicalObjectComponent object;

    public ChangeBoundsSystem(MapDraw draw, int priorty) {
        super(priorty, draw);
    }


    @Override
    public void addedToEngine(Engine engine) {
        positions = GameComponentMapper.getPositionComponentMapper();
         actionComponentMapper = GameComponentMapper.getActionComponentMapper();
         boundsChangeableComponentMapper=GameComponentMapper.getBoundsChangeableComponentMapper();
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {

        entities = getEngine().getEntitiesFor(Family.all(BoundsChangeable.class, PositionComponent.class, Action.class).get());


        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            BoundsChangeable boundsChangeable =boundsChangeableComponentMapper.get(entity);
            PositionComponent position = positions.get(entity);
            Action action=actionComponentMapper.get(entity);
            Direction direction=position.getDirection();
            String actionValue=action.getStat();
            Polygon bounds =boundsChangeable.getBounds(direction, actionValue);
            if(bounds!=null){
                position.setBounds(bounds);

            }
            Vector2 boundsOffset=boundsChangeable.getBoundsOffset(direction, actionValue);
            if(boundsOffset!=null){

                position.setBoundsXOffset(boundsOffset.x);
                position.setBoundsYOffset(boundsOffset.y);
            }





        }



    }



}

