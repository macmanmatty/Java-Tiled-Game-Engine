package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
public class ElasticCollision extends EventSystem {
    private Movable movable1;
   private PhysicalObjectComponent physicalObject1;
   private PositionComponent position1;
   private Movable movable2;
    private PhysicalObjectComponent physicalObject2;
   private PositionComponent position2;
    private Engine engine;
    private Entity entity1;
    private Entity entity2;
    private GameMap map;
    public ElasticCollision(MapDraw draw, GameMap map, Entity entity1, Movable movable1, PhysicalObjectComponent physicalObject1, PositionComponent position1, Entity entity2, Movable movable2, PhysicalObjectComponent physicalObject2, PositionComponent position2) {
        super(draw);
        this.movable1 = movable1;
        this.physicalObject1 = physicalObject1;
        this.position1 = position1;
        this.movable2 = movable2;
        this.physicalObject2 = physicalObject2;
        this.position2 = position2;
        this.entity1=entity1;
        this.entity2=entity2;
        this.map=map;
    }
    @Override
    public void act(float deltaTime) {
        float currentSpeed=movable1.getCurrentSpeed();
            if(physicalObject2!=null){
                double mass1=physicalObject1.getMass();
                double mass2=physicalObject2.getMass();

                double finalVelocity1=((mass2-mass1)*currentSpeed)/(mass1+mass2);
                double finalVelocity2=(2*mass1*currentSpeed)/(mass1+mass2);
                movable1.setMoveAngle((float) (movable1.getMoveAngle()+Math.PI));
                movable1.setCurrentSpeed((float) finalVelocity1);
                MoveOnGround.move( map, movable1, entity1, position1, deltaTime);

                if(movable2!=null) {
                    movable2.setMoveAngle((float) (movable2.getMoveAngle()+Math.PI));
                    movable2.setCurrentSpeed((float) finalVelocity2);
                    MoveOnGround.move(map, movable2, entity2, position2, deltaTime);
                }
            }
    }
}
