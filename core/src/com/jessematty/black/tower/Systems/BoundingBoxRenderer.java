package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.jessematty.black.tower.Components.FlagComponents.OnCurrentMap;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.Transient;

@Transient

public  class BoundingBoxRenderer extends EntitySystem {
    private ComponentMapper<PositionComponent> positionComponentMapper=ComponentMapper.getFor(PositionComponent.class);
    ImmutableArray<Entity> entities;
    ShapeRenderer shapes;
    public BoundingBoxRenderer( ShapeRenderer shapes) {
        this.shapes = shapes;
    }
    @Override
    public void addedToEngine(Engine engine) {
        
    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(OnCurrentMap.class, PositionComponent.class).get());
        int size=entities.size();
        shapes.begin(ShapeType.Line);
        shapes.setColor(Color.RED);
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            PositionComponent entityBounds= positionComponentMapper.get(entity);

                if(entityBounds.isHasBounds()) {
                    shapes.polygon(entityBounds.getBounds().getTransformedVertices());
                }

        }
        shapes.end();
    }
}