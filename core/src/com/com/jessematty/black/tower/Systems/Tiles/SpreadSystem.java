package com.jessematty.black.tower.Systems.Tiles;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Spread;
import com.jessematty.black.tower.Components.FlagComponents.Moved;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Other.Spreadable;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Systems.GameEntitySystem;
import com.jessematty.black.tower.Systems.Move.MoveOnGround;
public class SpreadSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<MovableComponent> moveables;
    private ComponentMapper<PositionComponent> positions;
    private ComponentMapper<ActionComponent> actions;
    protected  boolean eightDirections=true;
    int counter=0;
    public SpreadSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        moveables= GameComponentMapper.getMovableComponentMapper();
        positions=GameComponentMapper.getPositionComponentMapper();
        actions=GameComponentMapper.getActionComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(Spreadable.class, PositionComponent.class, ActionComponent.class, Spread.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            ActionComponent actionComponent = actions.get(entity);
                PositionComponent position = positions.get(entity);
                MovableComponent movableComponent = moveables.get(entity);
                GameMap  map=getDraw().getWorld().getMap(position.getMapId());
                if (movableComponent.getCurrentSpeed() > 0) {
                    MoveOnGround.move(map, movableComponent, entity, position, deltaTime);
                }
                else{
                    entity.remove(MovingOnGroundComponent.class);
                }
            }
    }
    public boolean move(){
        return true;
    }
    public void  move(MovableComponent movableComponent, Entity entity, PositionComponent position, float deltaTime) { // moves an entity at x speed with x move angle
        entity.add(new Moved());
        float moveAngle = movableComponent.getMoveAngle();
        float currentSpeed= movableComponent.getCurrentSpeed();
        float speedX = (float) Math.sin(moveAngle) * currentSpeed;
        float speedY = (float) Math.cos(moveAngle) * currentSpeed;
        position.setDirection(Direction.getDirection(moveAngle));
        float distanceX = deltaTime * speedX;
        float distanceY = deltaTime * speedY;
        movableComponent.setDistanceMoved(distanceX, distanceY, 0);
        float screenLocationX = position.getLocationX() + distanceX;
        float screenLocationY = position.getLocationY() + distanceY;
        GameMap map=getDraw().getWorld().getMap(position.getMapId());
        if (screenLocationX >= map.getMaxXWorld() - 32 || screenLocationY >= map.getMaxYWorld() - 32 || screenLocationY < 10 || screenLocationX < 10) {
            return; //  trying to  move out of map bounds bounds exit move function
        }
        LandSquareTile newTile = map.getTileFromWorldUnitCoordinates(screenLocationX, screenLocationY);
        movableComponent.setMoved(true);
        position.setLocationX(screenLocationX);
        position.setLocationY(screenLocationY);
        map.removeEntity(position.getTiles(), entity);
        Array<LandSquareTile> newTiles=map.getAllTilesAndAddEntity(position.getBoundsBoundingRectangle(), entity);
        position.setTiles(newTiles);
        movableComponent.setVelocity(speedX, speedY, 0);
    }
}
