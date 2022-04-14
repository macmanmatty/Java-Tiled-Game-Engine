package com.jessematty.black.tower.Editor.EditMode.Windows.EntitySelect;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class EntityDraggableImage extends Source {
    Payload payload= new Payload();
    Image invalidImage;
    Entity entity;
    public EntityDraggableImage(Entity entity, TextureRegion region) {
        super(new Image(new TextureRegionDrawable(region)));
        invalidImage = new Image(new TextureRegionDrawable());
        invalidImage.setColor(1,1,1,.5f);
        this.entity=entity;
        
        
    }
    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        payload.setDragActor(getActor());
        payload.setInvalidDragActor(invalidImage);
        payload.setObject(entity);
        return  payload;
    }
}
