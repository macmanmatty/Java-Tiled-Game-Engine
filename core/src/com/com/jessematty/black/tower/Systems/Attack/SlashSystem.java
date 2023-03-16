package com.jessematty.black.tower.Systems.Attack;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.jessematty.black.tower.Components.Actions.ActionComponent;

import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Slash;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Attacks.Slashable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class SlashSystem extends GameEntitySystem {
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ComponentMapper<MovableComponent> movableComponentMapper;
    private ComponentMapper<ActionComponent> actionComponentMapper;
    private ComponentMapper<Slashable> slashableComponentMapper;
    private ComponentMapper<AnimatableComponent> animatableComponentComponentMapper;
    private ComponentMapper<NumericStats> numericStatsComponentMapper;


    ImmutableArray<Entity> entities;


    MovableComponent movableComponent;
    PhysicalObjectComponent object;

    public SlashSystem(MapDraw draw) {
        super(draw);
    }


    @Override
    public void addedToEngine(Engine engine) {

        positionComponentMapper= GameComponentMapper.getPositionComponentMapper();
        movableComponentMapper=GameComponentMapper.getMovableComponentMapper();
        actionComponentMapper=GameComponentMapper.getActionComponentMapper();
        slashableComponentMapper=GameComponentMapper.getSlashableComponentMapper();
        animatableComponentComponentMapper=GameComponentMapper.getAnimatableComponentMapper();
    numericStatsComponentMapper=GameComponentMapper.getNumericStatsComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {


        entities = getEngine().getEntitiesFor(Family.all( Slashable.class, Slash.class, PositionComponent.class,  ActionComponent.class).get());

        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            PositionComponent position = positionComponentMapper.get(entity);
            MovableComponent movableComponent = movableComponentMapper.get(entity);
            Slashable slashable=slashableComponentMapper.get(entity);
            Polygon weaponBounds1 = position.getBounds();

            ActionComponent actionComponent = actionComponentMapper.get(entity);

                if(!slashable.isSlashing()) {
                    slashable.setStartDegrees(weaponBounds1.getRotation());

                    String slash="slash";
                    actionComponent.setStat(slash);
                    actionComponent.setActing(true);
                    slashable.setSlashing(true);
                    AnimatableComponent animatableComponent=animatableComponentComponentMapper.get(entity);
                    float totalAmountToRotate=slashable.getMinRotationDegrees()+slashable.getMaxRotationDegrees();
                    NumericStats numericStats=numericStatsComponentMapper.get(entity);
                    NumericStat slashSpeed=numericStats.getNumericStat("slashSpeed");
                    float slashSpeedNumber=slashable.getSlashSpeed();

                    if(slashSpeed!=null){
                        slashSpeedNumber=slashSpeed.getFloatValue();

                    }

                    float rotationAmount=slashSpeedNumber/totalAmountToRotate;
                    if(animatableComponent!=null) {
                        int frames = animatableComponent.getFrames(slash, position.getDirection());
                        int frameRate = animatableComponent.getFrameRate(position.getDirection(), slash);
                      rotationAmount = ((frames*frameRate)/totalAmountToRotate*slashSpeedNumber);

                    }
                    slashable.setDegreesToRotatePerTurn(rotationAmount);

                    movableComponent.setCurrentSpeed(slashSpeedNumber*totalAmountToRotate);
                    // calculate  rotation amount
                    float currentRotation=weaponBounds1.getRotation()-slashable.getMinRotationDegrees();
                    slashable.setDegreesToSlashTo(weaponBounds1.getRotation()+slashable.getMaxRotationDegrees());
                    weaponBounds1.setRotation( currentRotation);
                    slashable.setCurrentRotation(currentRotation);


                }



                // calculate new degrees
                float degrees=slashable.getCurrentRotation();
                degrees=degrees+slashable.getDegreesToRotatePerTurn();
            System.out.println("Degrees: " + degrees);


            slashable.setCurrentRotation(degrees);

                // check if rotation is done
            if (degrees >= slashable.getDegreesToSlashTo()) {

                entity.remove(Slash.class);
                EntityUtilities.setActionToAllConnectedEntities(entity, getWorld(), "rest");
                actionComponent.setActing(false);
                slashable.setSlashing(false);
                movableComponent.setCurrentSpeed(0);
                movableComponent.setDistanceMoved(0, 0, 0);
                weaponBounds1.setRotation(slashable.getStartDegrees());

                continue;

            }

                // rotate weapon
                weaponBounds1.rotate(degrees);
                position.getBounds().setRotation(degrees);
                float degreesRad = (float) Math.toRadians(degrees);
                float distance = position.getBoundsX();
                float xDistance = (float) (Math.sin(degreesRad) * distance);
                float yDistance = (float) (Math.cos(degreesRad) * distance);
                movableComponent.setDistanceMoved(xDistance, yDistance, 0);


            }
        }





}

