package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class MoveOnGround {
        public static  void  move(GameMap map, MovableComponent movableComponent, Entity entity, PositionComponent position, float deltaTime) { // moves an entity at x speed with x move angle
            float moveAngle = movableComponent.getMoveAngle();
            float currentSpeed= movableComponent.getCurrentSpeed();
            System.out.println("Position " +position);
            float speedX = (float) Math.sin(moveAngle) * currentSpeed;
            float speedY = (float) Math.cos(moveAngle) * currentSpeed;
            position.setDirection(Direction.getDirection(moveAngle));
            float distanceX = deltaTime * speedX;
            float distanceY = deltaTime * speedY;

            movableComponent.setDistanceMoved(distanceX, distanceY, 0);
            float screenLocationX = position.getLocationX() + distanceX;
            float screenLocationY = position.getLocationY() + distanceY;



            if (screenLocationX >= map.getMaxXScreen() - map.getTileWidth()-10 || screenLocationY >= map.getMaxYScreen() - map.getTileHeight()-10 || screenLocationY < 10 || screenLocationX < 10) {
                return; //  trying to  move out of map bounds bounds exit move function
            }
            LandSquareTile newTile = map.screenToTile(screenLocationX, screenLocationY);

            movableComponent.setMoved(true);

            position.setLocationX(screenLocationX);
            position.setLocationY(screenLocationY);
            map.removeEntity(position.getTiles(), entity);
            Array<LandSquareTile> newTiles=map.getAllTilesAndAddEntity(position.getBoundsBoundingRectangle(), entity);
            position.setTiles(newTiles);
            movableComponent.setVelocity(speedX, speedY, 0);
            movableComponent.getTotalDistanceMoved().add(movableComponent.getDistanceMoved());
        }
}
