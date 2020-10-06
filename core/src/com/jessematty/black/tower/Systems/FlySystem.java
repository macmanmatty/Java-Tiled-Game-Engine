package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Fly;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGround;
import com.jessematty.black.tower.Components.Markers.Moved;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class FlySystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Movable> moveables;
    private ComponentMapper<PositionComponent> positions;
    private ComponentMapper<Action> actions;

    protected  boolean eightDirections=true;
    int counter=0;

    public FlySystem(MapDraw draw) {
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
        entities=getEngine().getEntitiesFor(Family.all(Movable.class, PositionComponent.class, Action.class, Fly.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
           Entity entity=entities.get(count);
            Action action= actions.get(entity);
            action.setStat("move");
                PositionComponent position = positions.get(entity);
                Movable movable = moveables.get(entity);
                GameMap  map=getDraw().getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY());

                if (movable.getCurrentSpeed() > 0) {
                }
                else{
                    entity.remove(MovingOnGround.class);
                }


        }
    }
    public boolean move(){
       return true;
    }



    public void  move(Movable movable, Entity entity, PositionComponent position, float deltaTime) { // moves an entity at x speed with x move angle
         entity.add(new Moved());
        float moveAngle = movable.getMoveAngle();
         float currentSpeed=movable.getCurrentSpeed();

        float speedX = (float) Math.sin(moveAngle) * currentSpeed;
        float speedY = (float) Math.cos(moveAngle) * currentSpeed;
        position.setDirection(Direction.getDirection(moveAngle));

        float distanceX = deltaTime * speedX;
            float distanceY = deltaTime * speedY;

            movable.setDistanceMoved(distanceX, distanceY, 0);
            float screenLocationX = position.getLocationX() + distanceX;
            float screenLocationY = position.getLocationY() + distanceY;
            GameMap  map=getDraw().getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY());
            if (screenLocationX >= map.getMaxXScreen() - 32 || screenLocationY >= map.getMaxYScreen() - 32 || screenLocationY < 10 || screenLocationX < 10) {
                return; //  trying to  move out of map bounds bounds exit move function
            }



            movable.setMoved(true);
            position.setLocationX(screenLocationX);
            position.setLocationY(screenLocationY);

                map.removeEntity(position.getTiles(), entity);
                Array<LandSquareTile> newTiles=map.getAllTilesAndAddEntity(position.getBoundsBoundingRectangle(), entity);
                position.setTiles(newTiles);
                movable.setVelocity(speedX, speedY, 0);




        }



}