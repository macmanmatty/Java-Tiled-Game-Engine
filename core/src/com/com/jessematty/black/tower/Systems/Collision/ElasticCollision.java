package com.jessematty.black.tower.Systems.Collision;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Systems.EventSystem;
import com.jessematty.black.tower.Systems.Move.MoveOnGround;

/**
 * event system for an elastic collision
 */
public class ElasticCollision extends EventSystem {
    private final MovableComponent movableComponent1;
    private final PhysicalObjectComponent physicalObject1;
    private final PositionComponent position1;
    private final MovableComponent movableComponent2;
    private final PhysicalObjectComponent physicalObject2;
    private final PositionComponent position2;
    private Engine engine;
    private final Entity entity1;
    private final Entity entity2;
    private final GameMap map;

    public ElasticCollision(MapDraw draw, GameMap map, Entity entity1, MovableComponent movableComponent1, PhysicalObjectComponent physicalObject1, PositionComponent position1, Entity entity2, MovableComponent movableComponent2, PhysicalObjectComponent physicalObject2, PositionComponent position2) {
        super(draw);
        this.movableComponent1 = movableComponent1;
        this.physicalObject1 = physicalObject1;
        this.position1 = position1;
        this.movableComponent2 = movableComponent2;
        this.physicalObject2 = physicalObject2;
        this.position2 = position2;
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.map = map;
    }

    @Override
    public void act(float deltaTime) {
        float currentSpeed = movableComponent1.getCurrentSpeed();
        if (physicalObject2 != null) {
            double mass1 = physicalObject1.getMass();
            double mass2 = physicalObject2.getMass();
            double finalVelocity1 = ((mass2 - mass1) * currentSpeed) / (mass1 + mass2);
            double finalVelocity2 = (2 * mass1 * currentSpeed) / (mass1 + mass2);
            //set move angle   opposite direction
            movableComponent1.setMoveAngle((float) (movableComponent1.getMoveAngle() + Math.PI));
            movableComponent1.setCurrentSpeed((float) finalVelocity1);
            // move back opposite direction
            MoveOnGround.move(map, movableComponent1, entity1, position1, deltaTime * 2);
            if (movableComponent2 != null) {
                movableComponent2.setMoveAngle((float) (movableComponent2.getMoveAngle() + Math.PI));
                movableComponent2.setCurrentSpeed((float) finalVelocity2);
                MoveOnGround.move(map, movableComponent2, entity2, position2, deltaTime * 2);
            }
        }
    }
}
