package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGround;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.MoveToSingleTile;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class MoveToSingleTileSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Movable> moveables= ComponentMapper.getFor(Movable.class);
    private ComponentMapper<Position> positions=ComponentMapper.getFor(Position.class);
    private ComponentMapper<Action> actions =ComponentMapper.getFor(Action.class);


    public MoveToSingleTileSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void  update(float deltaTime) { // moves the owner to new tile area


        entities=getEngine().getEntitiesFor(Family.all(Movable.class, Position.class, MoveToSingleTile.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entity=entities.get(count);

            Movable movable=moveables.get(entity);
            Position position=positions.get(entity);
            Action action =actions.get(entity);
            LandSquareTile targetTile=movable.getLocationToMoveTo();


            LandSquareTile location = null;
            int locationx = location.getLocationX();
            int locationy = location.getLocationY();

            int newLocationx = targetTile.getLocationX();
            int newLocationy = targetTile.getLocationY();
            int locationXDifference = locationx - newLocationx;
            int locationyDifference = locationy - newLocationy;
            if (locationyDifference > 0 && locationXDifference == 0) {
                position.setDirection(Direction.UP);
                movable.moveUp();
                entity.add(new MovingOnGround());


            } else if (locationyDifference < 0 && locationXDifference == 0) {
                 position.setDirection(Direction.DOWN);
                movable.moveDown();
                entity.add(new MovingOnGround());

            } else if (locationXDifference < 0 && locationyDifference == 0) {
                movable.moveRight();
                entity.add(new MovingOnGround());
                position.setDirection(Direction.RIGHT);


            } else if (locationXDifference > 0 && locationyDifference == 0) {
                movable.moveLeft();
                entity.add(new MovingOnGround());
                position.setDirection(Direction.LEFT);


            } else if (locationyDifference < 0 && locationXDifference > 0) {
                movable.moveLeftDown();
                entity.add(new MovingOnGround());
                position.setDirection(Direction.LEFTDOWN);


            } else if (locationyDifference > 0 && locationXDifference > 0) {
                movable.moveLeftUp();
                entity.add(new MovingOnGround());
                position.setDirection(Direction.LEFTUP);


            } else if (locationyDifference < 0 && locationXDifference < 0) {
                movable.moveRightDown();
                entity.add(new MovingOnGround());
                position.setDirection(Direction.RIGHTDOWN);


            } else if (locationyDifference > 0 && locationXDifference < 0) {
                movable.moveRightUp();
                entity.add(new MovingOnGround());
                position.setDirection(Direction.RIGHTUP);


            }

            else{

                entity.remove(MovingOnGround.class);

            }


        }


            return;
        }



}
