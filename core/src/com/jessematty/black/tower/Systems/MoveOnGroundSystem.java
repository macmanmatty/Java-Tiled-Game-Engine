package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Moved;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGround;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Maps.GameMap;

public class MoveOnGroundSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Movable> moveables;
    private ComponentMapper<PositionComponent> positions;
    private ComponentMapper<Action> actions;

    protected  boolean eightDirections=true;
    int counter=0;

    public MoveOnGroundSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        moveables=getGameComponentMapper().getMovableComponentMapper();
        positions=getGameComponentMapper().getPositionComponentMapper();
        actions=getGameComponentMapper().getActionComponentMapper();

    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(Movable.class, PositionComponent.class, Action.class, MovingOnGround.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
           Entity entity=entities.get(count);
            Action action= actions.get(entity);
            action.setStat("move");
                PositionComponent position = positions.get(entity);
                Movable movable = moveables.get(entity);
                GameMap  map=getDraw().getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY());

                if (movable.getCurrentSpeed() > 0) {
                    MoveOnGround.move(map,  movable, entity, position, deltaTime);
                    entity.add(new Moved());
                }
                else{
                    entity.remove(MovingOnGround.class);
                    entity.remove(Moved.class);
                }


        }
    }
    public boolean move(){
       return true;
    }







}