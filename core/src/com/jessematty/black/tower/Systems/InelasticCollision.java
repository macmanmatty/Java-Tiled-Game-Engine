package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.PhysicalObject;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;

public class InelasticCollision extends EventSystem {
    private Movable movable1;
    private  PhysicalObject physicalObject1;
    private Position position1;
    private Movable movable2;
    private PhysicalObject physicalObject2;
    private  Position position2;


    private Engine engine;
    private Entity entity1;
    private Entity entity2;
    private GameMap map;


    public InelasticCollision(MapDraw draw, GameMap map, Entity entity1, Movable movable1, PhysicalObject physicalObject1, Position position1, Entity entity2, Movable movable2, PhysicalObject physicalObject2, Position position2) {
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
        float moveAngle=0;
        if(mass1>mass2) {
            movable1.getMoveAngle();
        }
        else{

            moveAngle=movable2.getMoveAngle();
        }

















    }


}
