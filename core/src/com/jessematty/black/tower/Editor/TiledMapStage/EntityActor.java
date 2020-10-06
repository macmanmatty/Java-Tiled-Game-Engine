package com.jessematty.black.tower.Editor.TiledMapStage;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.Position.PositionComponent;

public class EntityActor  extends Actor {
   private  Entity entity;
   private PositionComponent position;
   private Drawable drawable;




    public EntityActor(Entity entity, PositionComponent position, Drawable drawable) {
        this.entity = entity;
        this.position = entity.getComponent(PositionComponent.class);
        this.drawable = entity.getComponent(Drawable.class);
        if(position==null){
            throw new IllegalArgumentException("Entity has no Position Component");

        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.setColor(drawable.getColor());
        batch.draw(drawable.getTextureRegion(), position.getLocationX(), position.getLocationY());
    }
}
