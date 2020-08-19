package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.jessematty.black.tower.Components.Actions.Action;

import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Slash;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.PhysicalObject;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Slashable;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class SlashSystem extends GameEntitySystem {
    private ComponentMapper<Position> positionComponentMapper;
    private ComponentMapper<Movable> movableComponentMapper;
    private ComponentMapper<Action> actionComponentMapper;
    private ComponentMapper<Slashable> slashableComponentMapper;

    ImmutableArray<Entity> entities;


    Movable movable;
    PhysicalObject object;

    public SlashSystem(MapDraw draw) {
        super(draw);
    }


    @Override
    public void addedToEngine(Engine engine) {

        positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();
        movableComponentMapper=getGameComponentMapper().getMovableComponentMapper();
        actionComponentMapper=getGameComponentMapper().getActionComponentMapper();
        slashableComponentMapper=getGameComponentMapper().getSlashableComponentMapper();



    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {


        entities = getEngine().getEntitiesFor(Family.all(Slash.class, Slashable.class, Position.class, Movable.class, Action.class).get());

        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            Position position = positionComponentMapper.get(entity);
            Movable movable = movableComponentMapper.get(entity);
            Slashable slashable=slashableComponentMapper.get(entity);
            Polygon weaponBounds1 = position.getBounds();

            Action action = actionComponentMapper.get(entity);

                if(!slashable.isSlashing()) {
                    action.setStat("slash");
                    action.setActing(true);
                    slashable.setSlashing(true);
                    int frames = action.getAnimationFrames();
                    int frameRate=action.getFrameRate();
                    float slashSpeed = slashable.getSlashSpeed();
                    movable.setCurrentSpeed(slashSpeed);
                    // calculate  rotation amount
                    weaponBounds1.setRotation(slashable.getMinRotationDegrees()+ weaponBounds1.getRotation());
                    float rotationAmount = slashable.getMaxRotationDegrees() / (frames*frameRate);
                    slashable.setDegreesToRotatePerTurn(rotationAmount);

                }



                // calculate new degrees
                float degrees=slashable.getCurrentRotation();
                degrees=degrees+slashable.getDegreesToRotatePerTurn();
                slashable.setCurrentRotation(degrees);

                // check if rotation is done
            if (degrees >= slashable.getMaxRotationDegrees()) {
                entity.remove(Slash.class);
                action.setStat("rest");
                action.setActing(false);
                slashable.setSlashing(false);
                movable.setCurrentSpeed(0);
                movable.setDistanceMoved(0, 0, 0);
                continue;

            }

                // rotate weapon
                weaponBounds1.rotate(degrees);
                position.getBounds().setRotation(degrees);
                float degreesRad = (float) Math.toRadians(degrees);
                float distance = position.getBoundsX();
                float xDistance = (float) (Math.sin(degreesRad) * distance);
                float yDistance = (float) (Math.cos(degreesRad) * distance);
                movable.setDistanceMoved(xDistance, yDistance, 0);


            }
        }





}

