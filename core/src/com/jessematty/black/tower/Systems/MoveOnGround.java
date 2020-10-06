package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Utilities.Print;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class MoveOnGround {
        public static  void  move(GameMap map, Movable movable, Entity entity, PositionComponent position, float deltaTime) { // moves an entity at x speed with x move angle
            float moveAngle = movable.getMoveAngle();
            float currentSpeed=movable.getCurrentSpeed();
            System.out.println("Position " +position);
            float speedX = (float) Math.sin(moveAngle) * currentSpeed;
            float speedY = (float) Math.cos(moveAngle) * currentSpeed;
            position.setDirection(Direction.getDirection(moveAngle));
            float distanceX = deltaTime * speedX;
            float distanceY = deltaTime * speedY;

            movable.setDistanceMoved(distanceX, distanceY, 0);
            float screenLocationX = position.getLocationX() + distanceX;
            float screenLocationY = position.getLocationY() + distanceY;



            if (screenLocationX >= map.getMaxXScreen() - map.getTileSizeX()-10 || screenLocationY >= map.getMaxYScreen() - map.getTileSizeY()-10 || screenLocationY < 10 || screenLocationX < 10) {
                return; //  trying to  move out of map bounds bounds exit move function
            }
            LandSquareTile newTile = map.screenToTile(screenLocationX, screenLocationY);

            movable.setMoved(true);

            position.setLocationX(screenLocationX);
            position.setLocationY(screenLocationY);
            map.removeEntity(position.getTiles(), entity);
            Array<LandSquareTile> newTiles=map.getAllTilesAndAddEntity(position.getBoundsBoundingRectangle(), entity);
            position.setTiles(newTiles);
            movable.setVelocity(speedX, speedY, 0);
            movable.getTotalDistanceMoved().add(movable.getDistanceMoved());
        }
}
