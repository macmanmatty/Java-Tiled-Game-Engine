package com.jessematty.black.tower.Editor.TiledMapStage;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;

public class EntityActor  extends Actor {
   private  Entity entity;
   private PositionComponent position;
   private DrawableComponent drawableComponent;




    public EntityActor(Entity entity, PositionComponent position, DrawableComponent drawableComponent) {
        this.entity = entity;
        this.position = entity.getComponent(PositionComponent.class);
        this.drawableComponent = entity.getComponent(DrawableComponent.class);
        if(position==null){
            throw new IllegalArgumentException("Entity has no Position Component");

        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.setColor(drawableComponent.getColor());
        batch.draw(drawableComponent.getTextureRegion(), position.getLocationX(), position.getLocationY());
    }
}
