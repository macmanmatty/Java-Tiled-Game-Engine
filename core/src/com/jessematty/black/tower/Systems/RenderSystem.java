package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

import java.util.Comparator;
public  class RenderSystem extends SortedRenderingSystem {
    private  ComponentMapper<Drawable> drawableComponentMapper;
    private ComponentMapper<Position> positionComponentMapper =ComponentMapper.getFor(Position.class);
    private Batch batch;
    private FrameBuffer buffer;

    private GameComponentMapper gameComponentMapper;

    public RenderSystem(GameComponentMapper gameComponentMapper , Family family , Comparator<Entity>  comparator, Batch batch, FrameBuffer buffer, int priority) {
        super( family, comparator, batch ,buffer ,  priority);
        this.batch = batch;
        this.buffer=buffer;
        this.gameComponentMapper=gameComponentMapper;
    }
    @Override
    public void addedToEngine(Engine engine) {

        positionComponentMapper=gameComponentMapper.getPositionComponentMapper();
        drawableComponentMapper =gameComponentMapper.getDrawableComponentMapper();

    }
    @Override
    protected void processEntity(Entity entity, float v) {
        Drawable drawableArrayComponent= drawableComponentMapper.get(entity);

         Drawable  drawable=drawableComponentMapper.get(entity);
        if (drawable != null) {
            if (drawable.isDraw()) {
                TextureRegion textureRegion = drawable.getTextureRegion();
                if (textureRegion != null) {
                Position position = positionComponentMapper.get(entity);
                Color color=calculateColor(drawable.getColor(), drawable.getBrightness());
                batch.setColor(color);
                float positionX = position.getScreenLocationX() + drawable.getDrawOffsets().x;
                float positionY = position.getScreenLocationY() + drawable.getDrawOffsets().y;
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