package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.Markers.OnCurrentMap;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;

import java.util.Comparator;
public  class RenderSystem extends SortedRenderingSystem {
    private  ComponentMapper<Drawable> drawableComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper =ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<OnCurrentMap> onCurrentMapComponentMapper;
    private Batch batch;
    private FrameBuffer buffer;


    public RenderSystem( GameComponentMapper gameComponentMapper, Family family , Comparator<Entity>  comparator, Batch batch, FrameBuffer buffer, int priority) {
        super(  gameComponentMapper, family, comparator, batch ,buffer ,  priority);
        this.batch = batch;
        this.buffer=buffer;
    }
    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();
        drawableComponentMapper =getGameComponentMapper().getDrawableComponentMapper();
        onCurrentMapComponentMapper =getGameComponentMapper().getOnCurrentMapComponentMapper();

    }
    @Override
    protected void processEntity(Entity entity, float delta) {

         Drawable  drawable=drawableComponentMapper.get(entity);
        OnCurrentMap onCurrentMap= onCurrentMapComponentMapper.get(entity);
        if (drawable != null && onCurrentMap!=null) {
            if (drawable.isDraw()) {
                TextureRegion textureRegion = drawable.getTextureRegion();
                if (textureRegion != null) {
                PositionComponent position = positionComponentMapper.get(entity);

                    Color color=calculateColor(drawable.getColor(), drawable.getBrightness());
                batch.setColor(color);
                float positionX = position.getLocationX() + drawable.getDrawOffsets().x;
                float positionY = position.getLocationY() + drawable.getDrawOffsets().y;
                    batch.draw(textureRegion, positionX, positionY);
                }
            }
        }

    }
    private Color calculateColor(Color entityColor, float brightness) {
        Color color= new Color(entityColor.r, entityColor.g, entityColor.b, entityColor.a);
        color.r=color.r*brightness;
        color.g=color.g*brightness;
        color.b=color.b*brightness;


        return  color;
    }
}