package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.FlagComponents.OnCurrentMap;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;

import java.util.Comparator;

/**
 * system for rendering  entities with  drawable and position components
 * and the on current map marker component
 */
public  class RenderSystem extends SortedRenderingSystem {
    private  ComponentMapper<DrawableComponent> drawableComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ComponentMapper<OnCurrentMap> onCurrentMapComponentMapper;
    /**
     *  the libgdx Sprite batch to use for rendering of texture regions
     */
    private Batch batch;
    /**
     * the entity frame buffer used for drawing entities
     */
    private FrameBuffer buffer;


    public RenderSystem(  Family family , Comparator<Entity>  comparator, Batch batch, FrameBuffer buffer, int priority) {
        super(  family, comparator, batch ,buffer ,  priority);
        this.batch = batch;
        this.buffer=buffer;
    }
    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        positionComponentMapper= GameComponentMapper.getPositionComponentMapper();
        drawableComponentMapper=GameComponentMapper.getDrawableComponentMapper();
       onCurrentMapComponentMapper= GameComponentMapper.getOnCurrentMapComponentMapper();

    }

    /**
     *  Renders an entities current texture region  based on it's texture region and color
     * @param entity the entity to process
     * @param delta delta time
     */
    @Override
    protected void processEntity(Entity entity, float delta) {

         DrawableComponent drawableComponent =drawableComponentMapper.get(entity);
        OnCurrentMap onCurrentMap= onCurrentMapComponentMapper.get(entity);
        if (drawableComponent != null && onCurrentMap!=null) {
            if (drawableComponent.isDraw()) {
                TextureRegion textureRegion = drawableComponent.getTextureRegion();
                if (textureRegion != null) {
                PositionComponent position = positionComponentMapper.get(entity);

                    Color color=calculateColor(drawableComponent.getColor(), drawableComponent.getBrightness());
                    if(color!=null) {
                        batch.setColor(color);
                    }
                float positionX = position.getLocationX() + drawableComponent.getDrawOffsets().x;
                float positionY = position.getLocationY() + drawableComponent.getDrawOffsets().y;
                    batch.draw(textureRegion, positionX, positionY);
                    System.out.println( "region width" + textureRegion.getRegionWidth());                    System.out.println("region x" +textureRegion.getRegionX());
                    System.out.println( "region y" + textureRegion.getRegionY());
                    System.out.println("region height" +textureRegion.getRegionHeight());
                    System.out.println( "region width" + textureRegion.getRegionWidth());
                    System.out.println("region u" +textureRegion.getU());
                    System.out.println( "region u2" + textureRegion.getU2());
                    System.out.println("region v1" +textureRegion.getV());
                    System.out.println( "region v2" + textureRegion.getV2());

                }
            }
        }

    }

    /**
     *  // calculates an entities  brightness based on color
     * @param entityColor the color of the entity
     * @param brightness the brightness  multiplier
     * @return COLOR  the new  color with calculated brightness
     */
    private Color calculateColor(Color entityColor, float brightness) {
        Color color= new Color(entityColor.r, entityColor.g, entityColor.b, entityColor.a);
        color.r=color.r*brightness;
        color.g=color.g*brightness;
        color.b=color.b*brightness;
        return  color;
    }
}