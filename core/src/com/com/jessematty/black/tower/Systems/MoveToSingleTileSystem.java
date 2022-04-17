package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.MoveToSingleTile;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class MoveToSingleTileSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<MovableComponent> moveables= ComponentMapper.getFor(MovableComponent.class);
    private ComponentMapper<PositionComponent> positions=ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<ActionComponent> actions =ComponentMapper.getFor(ActionComponent.class);


    public MoveToSingleTileSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void  update(float deltaTime) { // moves the owner to new tile area


        entities=getEngine().getEntitiesFor(Family.all(MovableComponent.class, PositionComponent.class, MoveToSingleTile.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entity=entities.get(count);

            MovableComponent movableComponent =moveables.get(entity);
            PositionComponent position=positions.get(entity);
            ActionComponent actionComponent =actions.get(entity);
            LandSquareTile targetTile= movableComponent.getLocationToMoveTo();


            LandSquareTile location = null;
            int locationx = location.getLocationX();
            int locationy = location.getLocationY();

            int newLocationx = targetTile.getLocationX();
            int newLocationy = targetTile.getLocationY();
            int locationXDifference = locationx - newLocationx;
            int locationyDifference = locationy - newLocationy;
            if (locationyDifference > 0 && locationXDifference == 0) {
                position.setDirection(Direction.UP);
                movableComponent.moveUp();
                entity.add(new MovingOnGroundComponent());


            } else if (locationyDifference < 0 && locationXDifference == 0) {
                 position.setDirection(Direction.DOWN);
                movableComponent.moveDown();
                entity.add(new MovingOnGroundComponent());

            } else if (locationXDifference < 0 && locationyDifference == 0) {
                movableComponent.moveRight();
                entity.add(new MovingOnGroundComponent());
                position.setDirection(Direction.RIGHT);


            } else if (locationXDifference > 0 && locationyDifference == 0) {
                movableComponent.moveLeft();
                entity.add(new MovingOnGroundComponent());
                position.setDirection(Direction.LEFT);


            } else if (locationyDifference < 0 && locationXDifference > 0) {
                movableComponent.moveLeftDown();
                entity.add(new MovingOnGroundComponent());
                position.setDirection(Direction.LEFTDOWN);


            } else if (locationyDifference > 0 && locationXDifference > 0) {
                movableComponent.moveLeftUp();
                entity.add(new MovingOnGroundComponent());
                position.setDirection(Direction.LEFTUP);


            } else if (locationyDifference < 0 && locationXDifference < 0) {
                movableComponent.moveRightDown();
                entity.add(new MovingOnGroundComponent());
                position.setDirection(Direction.RIGHTDOWN);


            } else if (locationyDifference > 0 && locationXDifference < 0) {
                movableComponent.moveRightUp();
                entity.add(new MovingOnGroundComponent());
                position.setDirection(Direction.RIGHTUP);


            }

            else{

                entity.remove(MovingOnGroundComponent.class);

            }


        }


            return;
        }



}
