package com.jessematty.black.tower.Systems.Attack;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Thrust;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Thrustable;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.Print;
import com.jessematty.black.tower.Systems.GameEntitySystem;


public class ThrustSystem extends GameEntitySystem {
    private ComponentMapper<Thrustable> thrustableComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ComponentMapper<MovableComponent> movableComponentMapper;
    private ComponentMapper<ActionComponent> actionComponentMapper;
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    private ComponentMapper<AnimatableComponent> animatableComponentComponentMapper;

    ImmutableArray<Entity> entities;


    public ThrustSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
         actionComponentMapper= GameComponentMapper.getActionComponentMapper();
         thrustableComponentMapper=GameComponentMapper.getThrustComponentMapper();
         positionComponentMapper=GameComponentMapper.getPositionComponentMapper();
         movableComponentMapper=GameComponentMapper.getMovableComponentMapper();
         animatableComponentComponentMapper=GameComponentMapper.getAnimatableComponentMapper();
         numericStatsComponentMapper=GameComponentMapper.getNumericStatsComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        entities = getEngine().getEntitiesFor(Family.all( Thrust.class, ActionComponent.class,   PositionComponent.class, MovableComponent.class, Thrustable.class).get());

        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            PositionComponent position = positionComponentMapper.get(entity);
            MovableComponent movableComponent = movableComponentMapper.get(entity);
            Thrustable thrustable = thrustableComponentMapper.get(entity);
            ActionComponent actionComponent = entity.getComponent(ActionComponent.class);
            Polygon weaponBounds = position.getBounds();
            if (!thrustable.isThrusting()) {

                float distanceY=weaponBounds.getBoundingRectangle().height/2f;
                float distanceX=weaponBounds.getBoundingRectangle().width/2f;
                float maxDistance=Math.max(distanceY, distanceX);
                thrustable.setMaxDistance(maxDistance);
                actionComponent.setActing(true);
                String thrust="thrust";
                actionComponent.setStat(thrust);
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


                movableComponent.setCurrentSpeed(thrustSpeedNumber);
                // calculate  rotation amount
                thrustable.setLengthToThrustPerTurn(thrustDistancePerTurn);


            }

                    movableComponent.setMoved(true);



                    float currentDistance = thrustable.getDistanceMoved();
                    currentDistance = thrustable.getLengthToThrustPerTurn()+ currentDistance;
                    thrustable.setDistanceMoved(currentDistance);

                    Direction direction = position.getDirection();

                    setMoveDistance(weaponBounds, movableComponent, direction, currentDistance);


                    if (currentDistance >= thrustable.getMaxDistance()) {
                       thrustable.setThrusting(false);
                       actionComponent.setActing(false);
                        EntityUtilities.setActionToAllConnectedEntities(entity, getWorld(), "rest");
                        entity.remove(Thrust.class);
                        actionComponent.setActing(false);
                        movableComponent.setCurrentSpeed(0);
                        thrustable.setDistanceMoved(0);
                        movableComponent.setDistanceMoved(0, 0, 0);
                        Vector2 oldPosition=thrustable.getStartPosition();
                        weaponBounds.setPosition(oldPosition.x, oldPosition.y);

                    }




                }



    }


        public void setMoveDistance(Polygon polygon, MovableComponent movableComponent, Direction direction, float distance ){
             float x=polygon.getX();
             float y=polygon.getY();

          float angle= Direction.getAngelInRadians(direction);


          float xDistance= (float) (Math.sin(angle)*distance);
           float yDistance= (float) (Math.cos(angle)*distance);
           movableComponent.setDistanceMoved(xDistance, yDistance, 0);
           polygon.setPosition(x+xDistance, y+yDistance);
           System.out.println("distance "+distance);
            Print.printXY("distance Moved ",xDistance, yDistance);


            return;


        }



}


