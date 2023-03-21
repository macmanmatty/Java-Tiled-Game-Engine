package com.jessematty.black.tower.Systems.Move;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
/**
 * function that moves Entities on the ground
 */
public class MoveOnGround {
    /**
     *  function that moves an Entity a given distance  in the x and y directions for a given speed
     *  using basic 2 dimensional physics
     * @param map the map to move on 
     * @param movableComponent the Entities Movable Component
     * @param entity the Entity to move
     * @param position the entities Position Component
     * @param deltaTime  the libGDX deltaTime
     */
        public static  void  move(GameMap map, MovableComponent movableComponent, Entity entity, PositionComponent position, float deltaTime) { // moves an entity at x speed with x move angle
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
            if (screenLocationX >= map.getMaxXWorld() - map.getTileWidth()-10 || screenLocationY >= map.getMaxYWorld() - map.getTileHeight()-10 || screenLocationY < 10 || screenLocationX < 10) {
                return; //  trying to  move out of map bounds bounds exit move function
            }
            movableComponent.setMoved(true);
            position.setLocationX(screenLocationX);
            position.setLocationY(screenLocationY);
            map.removeEntity(position.getTiles(), entity);
            Array<LandSquareTile> newTiles=map.getAllTilesAndAddEntity(position.getBoundsBoundingRectangle(), entity);
            position.setTiles(newTiles);
            position.setTileLocationX(newTiles.get(0).getLocationX());
            position.setTileLocationY(newTiles.get(0).getLocationY());

            movableComponent.setVelocity(speedX, speedY, 0);
        }
}
