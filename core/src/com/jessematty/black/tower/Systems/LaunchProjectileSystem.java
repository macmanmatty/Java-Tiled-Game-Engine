package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Shoot;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Target;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class LaunchProjectileSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Movable> movableComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
    private ComponentMapper<Action> actionComponentMapper;
    private ComponentMapper<Target> targetComponentMapper;

    protected  boolean eightDirections=true;
    int counter=0;

    public LaunchProjectileSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        movableComponentMapper=getGameComponentMapper().getMovableComponentMapper();
        positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();
        actionComponentMapper=getGameComponentMapper().getActionComponentMapper();
        targetComponentMapper=getGameComponentMapper().getTargetComponentMapper();
    }

    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all( Target.class, Movable.class, Position.class, Action.class, Shoot.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++){
           Entity entity=entities.get(count);
            Action action= actionComponentMapper.get(entity);
                Position position = positionComponentMapper.get(entity);
                Target target=targetComponentMapper.get(entity);



            }


    }
    public boolean move(){
       return true;
    }





}