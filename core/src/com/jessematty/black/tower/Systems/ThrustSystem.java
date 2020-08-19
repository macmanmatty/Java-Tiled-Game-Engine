package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Thrust;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Thrustable;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;


public class ThrustSystem extends GameEntitySystem {
    private ComponentMapper<Thrustable> thrustableComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
    private ComponentMapper<Movable> movableComponentMapper;
    private ComponentMapper<Action> actionComponentMapper;

    ImmutableArray<Entity> entities;


    public ThrustSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
         actionComponentMapper=getGameComponentMapper().getActionComponentMapper();
         thrustableComponentMapper=getGameComponentMapper().getThrustComponentMapper();
         positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();
         movableComponentMapper=getGameComponentMapper().getMovableComponentMapper();
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        entities = getEngine().getEntitiesFor(Family.all( Thrust.class, Action.class,   Position.class, Movable.class, Thrustable.class).get());

        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            Position position = positionComponentMapper.get(entity);
            Movable movable = movableComponentMapper.get(entity);
            Thrustable thrustable = thrustableComponentMapper.get(entity);
            Action action = entity.getComponent(Action.class);
            Polygon weaponBounds1 = position.getBounds();

            float maxDistance=thrustable.getMaxThrustDistance();
            if (!thrustable.isThrusting()) {

                action.setActing(true);
                action.setStat("thrust");
                int frames = action.getAnimationFrames();
                int frameRate = action.getFrameRate();
                float thrustSpeed = thrustable.getThrustSpeed();
                movable.setCurrentSpeed(thrustSpeed);
                // calculate  rotation amount
                float thrustsPerTurn = maxDistance*2 / (frames * frameRate);
                thrustable.setLengthToThrustPerTurn(thrustsPerTurn);


            }

                    movable.setMoved(true);

                    Polygon weaponBounds = position.getBounds();


                    float currentDistance = thrustable.getDistanceMoved();
                    currentDistance = thrustable.getLengthToThrustPerTurn()+ currentDistance;
                    thrustable.setDistanceMoved(currentDistance);

                    Direction direction = position.getDirection();

                    getThrustVerticies(weaponBounds, movable, direction, currentDistance);


                    if (currentDistance == maxDistance) {
                        position.setDirection(Direction.getOppositeDirection(direction));
                    }


                }



    }


        public float[] getThrustVerticies (Polygon polygon, Movable movable,  Direction direction, float distance ){
             float x=polygon.getX();
             float y=polygon.getY();

          float angle= (float) Math.toRadians(Direction.getAngel(direction));


          float xDistance= (float) (Math.sin(angle)*distance);
           float yDistance= (float) (Math.cos(angle)*distance);
           movable.setDistanceMoved(xDistance, yDistance, 0);


           polygon.setPosition(x+xDistance, y+yDistance);


            return polygon.getTransformedVertices();


        }



}


