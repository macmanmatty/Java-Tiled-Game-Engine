package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.jessematty.black.tower.Components.Light;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

import java.util.Comparator;


public  class LightRenderSystem extends SortedIteratingSystem{
    private ComponentMapper<Light> lights;
    private ComponentMapper<PositionComponent> positions;
    private final  MapDraw mapDraw;



    private Batch batch;
    private FrameBuffer buffer;
    public LightRenderSystem(MapDraw draw, Family family , Comparator<Entity>  comparator, Batch batch, FrameBuffer buffer, int priority) {
        super(family, comparator, priority);
        this.mapDraw=draw;
        this.batch = batch;
        this.buffer=buffer;
    }

    @Override
    public void addedToEngine(Engine engine) {


        lights=mapDraw.getGameComponentMapper().getLightComponentMapper();
        positions=mapDraw.getGameComponentMapper().getPositionComponentMapper();






    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);


        batch.begin();
        ImmutableArray<Entity> entities =getEntities();
        int size=entities.size();
        for(int count=0; count<size; count++){

            Entity entity=entities.get(count);
            Light light = lights.get(entity);
                PositionComponent position = positions.get(entity);

                batch.setColor(light.getColor());
                batch.getShader().setUniformf("bright", light.getBrightness());
                float positionX = position.getLocationX() + light.getDrawOffsetX();
                float positionY = position.getLocationY() + light.getDrawOffsetY();






        }



        batch.end();



    }

    @Override
    protected void processEntity(Entity entity, float v) {

    }


}