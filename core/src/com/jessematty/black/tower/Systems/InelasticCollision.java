package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Maps.GameMap;

public class InelasticCollision extends EventSystem {
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
    private ComponentMapper<Movable> movableComponentMapper;



    public InelasticCollision(MapDraw draw, GameMap map, Entity entity1, Movable movable1, PhysicalObjectComponent physicalObject1, PositionComponent position1, Entity entity2, Movable movable2, PhysicalObjectComponent physicalObject2, PositionComponent position2) {
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
        movableComponentMapper=getGameComponentMapper().getMovableComponentMapper();

    }

    @Override
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void addedToEngine(Engine engine) {

        this.engine=engine;
    }

    @Override
    public void act(float deltaTime) {

        float speed1=movable1.getCurrentSpeed();
        float speed2=movable2.getCurrentSpeed();
        float mass1=physicalObject1.getMass();
        float mass2=physicalObject2.getMass();
        Movable movable=movableComponentMapper.get(entity1);
        if(movable!=null){
            float newSpeed=((mass1*speed1)+(mass2*speed2))/(mass1+mass2);
            float speedReduction=newSpeed*physicalObject2.getEntitySolidity();

            movable.setCurrentSpeed(newSpeed-speedReduction);
        }



















    }


}
