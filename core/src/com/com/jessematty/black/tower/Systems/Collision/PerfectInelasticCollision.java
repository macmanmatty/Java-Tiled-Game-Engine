package com.jessematty.black.tower.Systems.Collision;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Systems.Engine.EventSystem;

public class PerfectInelasticCollision extends EventSystem {
    private MovableComponent movableComponent1;
    private PhysicalObjectComponent physicalObject1;
    private PositionComponent position1;
    private MovableComponent movableComponent2;
    private PhysicalObjectComponent physicalObject2;
    private PositionComponent position2;


    private Engine engine;
    private Entity entity1;
    private Entity entity2;
    private GameMap map;
    private ComponentMapper<MovableComponent> movableComponentMapper;
    private boolean combine;




    public PerfectInelasticCollision(MapDraw draw, GameMap map, Entity entity1, MovableComponent movableComponent1, PhysicalObjectComponent physicalObject1, PositionComponent position1, Entity entity2, MovableComponent movableComponent2, PhysicalObjectComponent physicalObject2, PositionComponent position2, boolean combine) {
        super(draw);
        this.movableComponent1 = movableComponent1;
        this.physicalObject1 = physicalObject1;
        this.position1 = position1;
        this.movableComponent2 = movableComponent2;
        this.physicalObject2 = physicalObject2;
        this.position2 = position2;
        this.entity1=entity1;
        this.entity2=entity2;
        this.map=map;
        this.combine=combine;
        movableComponentMapper= GameComponentMapper.getMovableComponentMapper();

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





        float speed1= movableComponent1.getCurrentSpeed();
        float speed2= movableComponent2.getCurrentSpeed();
        float mass1=physicalObject1.getMass();
        float mass2=physicalObject2.getMass();
        Entity entity= EntityUtilities.combineEntities( getWorld(), entity1, entity2);
        MovableComponent movableComponent =movableComponentMapper.get(entity);
        if(movableComponent !=null){

            float newSpeed=((mass1*speed1)+(mass2*speed2))/(mass1+mass2);


            movableComponent.setCurrentSpeed(newSpeed);
        }



















    }


}
