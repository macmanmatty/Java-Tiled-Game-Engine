package com.jessematty.black.tower.Systems.Drawing;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.FlagComponents.OnCurrentMap;
import com.jessematty.black.tower.Components.Other.GlowComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

/**
 * system to make entities glow
 */
public class GlowSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<DrawableComponent> drawableComponentMapper;
    private  ComponentMapper<GlowComponent> glowComponentMapper;
    public GlowSystem(MapDraw draw) {
        super(draw);
    }

    public GlowSystem(int priority, MapDraw draw) {
        super(priority, draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        drawableComponentMapper = GameComponentMapper.getDrawableComponentMapper();
        glowComponentMapper=GameComponentMapper.getGlowComponentMapper();
    }

    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(OnCurrentMap.class,  DrawableComponent.class,  GlowComponent.class).get());
        for(Entity entity : entities ) {
            GlowComponent glowComponent = glowComponentMapper.get(entity);
                DrawableComponent drawableComponent=drawableComponentMapper.get(entity);
                drawableComponent.setDraw(true);
                drawableComponent.setCalculateColor(true);
                float brightness = drawableComponent.getBrightness();
                if (!glowComponent.isDecreaseBrightness()) {
                    brightness = brightness + glowComponent.getIncrease();
                    double maxValue = glowComponent.getMaxValue();
                    if (brightness > maxValue) {
                        glowComponent.setDecreaseBrightness(true);
                        brightness = (float) maxValue;
                    }
                    drawableComponent.setBrightness(brightness);
                    glowComponent.setValue(brightness);
                } else {
                    brightness = brightness - glowComponent.getIncrease();
                    double minValue = glowComponent.getMinValue();
                    if (brightness < minValue) {
                        glowComponent.setDecreaseBrightness(true);
                        brightness = (float) minValue;
                    }
                    drawableComponent.setBrightness(brightness);
                    glowComponent.setValue(brightness);
                }


            }
        }

}
