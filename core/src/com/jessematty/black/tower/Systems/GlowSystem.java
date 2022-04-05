package com.jessematty.black.tower.Systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Transient;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Glow;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
@Transient
public  class GlowSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<DrawableComponent> drawableComponentMapper;
    private ComponentMapper<Glow> glowComponentMapper;
    private ComponentMapper<PositionComponent> positions;
    private RenderSystem renderSystem;
    public GlowSystem(MapDraw draw, RenderSystem system, int priority) {
        super(priority, draw );
        this.renderSystem=system;
    }
    @Override
    public void addedToEngine(Engine engine) {
        drawableComponentMapper = GameComponentMapper.getDrawableComponentMapper();
        glowComponentMapper=GameComponentMapper.getGlowComponentMapper();
        positions=GameComponentMapper.getPositionComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(AnimatableComponent.class, DrawableComponent.class, PositionComponent.class, Action.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            PositionComponent position=positions.get(entity);
            DrawableComponent drawableComponent = drawableComponentMapper.get(entity);
            if(getWorld().getMap(position.getMapId())!=getDraw().getCurrentMap()) {
                drawableComponent.setDraw(false);
                continue;
            }
            drawableComponent.setDraw(true);
            drawableComponent.setCalculateColor(true);
            Glow glow= glowComponentMapper.get(entity);
            float brightness= drawableComponent.getBrightness();
            if(!glow.isDecreaseBrightness()) {
                brightness = brightness + glow.getIncrease();
                double  maxValue=glow.getMaxValue();
                if (brightness > maxValue) {
                    glow.setDecreaseBrightness(true);
                    brightness= (float) maxValue;
                }
                drawableComponent.setBrightness(brightness);
                glow.setValue(brightness);
            }
            
            else{
                brightness = brightness - glow.getIncrease();
                double  minValue=glow.getMinValue();
                if (brightness < minValue) {
                    glow.setDecreaseBrightness(true);
                    brightness= (float) minValue;
                }
                drawableComponent.setBrightness(brightness);
                glow.setValue(brightness);
                
            }

           
        }
    }
}