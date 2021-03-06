package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Slash;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Thrust;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Thrustable;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.Print;


public class ThrustSystem extends GameEntitySystem {
    private ComponentMapper<Thrustable> thrustableComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ComponentMapper<Movable> movableComponentMapper;
    private ComponentMapper<Action> actionComponentMapper;
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    private ComponentMapper<AnimatableComponent> animatableComponentComponentMapper;

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
         animatableComponentComponentMapper=getGameComponentMapper().getAnimatableComponentMapper();
         numericStatsComponentMapper=getGameComponentMapper().getNumericStatsComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        entities = getEngine().getEntitiesFor(Family.all( Thrust.class, Action.class,   PositionComponent.class, Movable.class, Thrustable.class).get());

        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            PositionComponent position = positionComponentMapper.get(entity);
            Movable movable = movableComponentMapper.get(entity);
            Thrustable thrustable = thrustableComponentMapper.get(entity);
            Action action = entity.getComponent(Action.class);
            Polygon weaponBounds = position.getBounds();
            if (!thrustable.isThrusting()) {

                float distanceY=weaponBounds.getBoundingRectangle().height/2f;
                float distanceX=weaponBounds.getBoundingRectangle().width/2f;
                float maxDistance=Math.max(distanceY, distanceX);
                thrustable.setMaxDistance(maxDistance);
                action.setActing(true);
                String thrust="thrust";
                action.setStat(thrust);
                thrustable.setStartPosition(new Vector2(weaponBounds.getX(), weaponBounds.getY()));
                thrustable.setThrusting(true);
                float thrustSpeedNumber=thrustable.getThrustSpeed();
                NumericStats numericStats=numericStatsComponentMapper.get(entity);
                if(numericStats!=null){
                    NumericStat thrustSpeed=numericStats.getNumericStat("thrustSpeed");
                    if(thrustSpeed!=null){
                        thrustSpeedNumber=thrustSpeed.getFloatValue();
                    }

                }


                float thrustDistancePerTurn = maxDistance / (thrustSpeedNumber);


                AnimatableComponent animatableComponent=animatableComponentComponentMapper.get(entity);
                if(animatableComponent!=null){
                    int frames = animatableComponent.getFrames(thrust, position.getDirection());
                    int frameRate = animatableComponent.getFrameRate(position.getDirection(), thrust);
                    thrustDistancePerTurn = maxDistance/(frames*frameRate);

                }


                movable.setCurrentSpeed(thrustSpeedNumber);
                // calculate  rotation amount
                thrustable.setLengthToThrustPerTurn(thrustDistancePerTurn);


            }

                    movable.setMoved(true);



                    float currentDistance = thrustable.getDistanceMoved();
                    currentDistance = thrustable.getLengthToThrustPerTurn()+ currentDistance;
                    thrustable.setDistanceMoved(currentDistance);

                    Direction direction = position.getDirection();

                    setMoveDistance(weaponBounds, movable, direction, currentDistance);


                    if (currentDistance >= thrustable.getMaxDistance()) {
                       thrustable.setThrusting(false);
                       action.setActing(false);
                        EntityUtilities.setActionToAllConnectedEntities(entity, getWorld(), "rest");
                        entity.remove(Thrust.class);
                        action.setActing(false);
                        movable.setCurrentSpeed(0);
                        thrustable.setDistanceMoved(0);
                        movable.setDistanceMoved(0, 0, 0);
                        Vector2 oldPosition=thrustable.getStartPosition();
                        weaponBounds.setPosition(oldPosition.x, oldPosition.y);

                    }




                }



    }


        public void setMoveDistance(Polygon polygon, Movable movable, Direction direction, float distance ){
             float x=polygon.getX();
             float y=polygon.getY();

          float angle= Direction.getAngelInRadians(direction);


          float xDistance= (float) (Math.sin(angle)*distance);
           float yDistance= (float) (Math.cos(angle)*distance);
           movable.setDistanceMoved(xDistance, yDistance, 0);
           polygon.setPosition(x+xDistance, y+yDistance);
           System.out.println("distance "+distance);
            Print.printXY("distance Moved ",xDistance, yDistance);


            return;


        }



}


