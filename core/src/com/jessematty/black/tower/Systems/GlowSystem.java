package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.Transient;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.Glow;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
@Transient
public  class GlowSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<Drawable> drawableComponentMapper;
    private ComponentMapper<Glow> glowComponentMapper;
    private ComponentMapper<PositionComponent> positions;
    private RenderSystem renderSystem;
    public GlowSystem(MapDraw draw, RenderSystem system, int priority) {
        super(priority, draw );
        this.renderSystem=system;
    }
    @Override
    public void addedToEngine(Engine engine) {
        drawableComponentMapper =getGameComponentMapper().getDrawableComponentMapper();
        glowComponentMapper=getGameComponentMapper().getGlowComponentMapper();
        positions=getGameComponentMapper().getPositionComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(AnimatableComponent.class, Drawable.class, PositionComponent.class, Action.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            PositionComponent position=positions.get(entity);
            Drawable drawable = drawableComponentMapper.get(entity);
            if(getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY())!=getDraw().getCurrentMap()) {
                drawable.setDraw(false);
                continue;
            }
            drawable.setDraw(true);
            drawable.setCalculateColor(true);
            Glow glow= glowComponentMapper.get(entity);
            float brightness=drawable.getBrightness();
            if(!glow.isDecreaseBrightness()) {
                brightness = brightness + glow.getIncrease();
                double  maxValue=glow.getMaxValue();
                if (brightness > maxValue) {
                    glow.setDecreaseBrightness(true);
                    brightness= (float) maxValue;
                }
                drawable.setBrightness(brightness);
                glow.setValue(brightness);
            }
            
            else{
                brightness = brightness - glow.getIncrease();
                double  minValue=glow.getMinValue();
                if (brightness < minValue) {
                    glow.setDecreaseBrightness(true);
                    brightness= (float) minValue;
                }
                drawable.setBrightness(brightness);
                glow.setValue(brightness);
                
            }

           
        }
    }
}