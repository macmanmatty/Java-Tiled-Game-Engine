package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Shoot;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Target;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class LaunchProjectileSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<MovableComponent> movableComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ComponentMapper<Action> actionComponentMapper;
    private ComponentMapper<Target> targetComponentMapper;

    protected  boolean eightDirections=true;
    int counter=0;

    public LaunchProjectileSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        movableComponentMapper= GameComponentMapper.getMovableComponentMapper();
        positionComponentMapper=GameComponentMapper.getPositionComponentMapper();
        actionComponentMapper=GameComponentMapper.getActionComponentMapper();
        targetComponentMapper=GameComponentMapper.getTargetComponentMapper();
    }

    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all( Target.class, MovableComponent.class, PositionComponent.class, Action.class, Shoot.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++){
           Entity entity=entities.get(count);
            Action action= actionComponentMapper.get(entity);
                PositionComponent position = positionComponentMapper.get(entity);
                Target target=targetComponentMapper.get(entity);



            }


    }
    public boolean move(){
       return true;
    }





}