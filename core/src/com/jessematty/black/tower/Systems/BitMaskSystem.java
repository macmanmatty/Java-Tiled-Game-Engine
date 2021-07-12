package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.BitMaskable;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.BitMask.BitMask;

public  class BitMaskSystem extends GameEntitySystem {
    private ImmutableArray<Entity> entities;
    private ComponentMapper<DrawableComponent> drawables;
    private ComponentMapper<BitMaskable> bitmaskables;
    private ComponentMapper<PositionComponent> positions;
    private BitMask bitMask= new BitMask();
    public BitMaskSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {

        drawables= GameComponentMapper.getDrawableComponentMapper();
        bitmaskables=GameComponentMapper.getBitMaskableComponentMapper();
    positions=GameComponentMapper.getPositionComponentMapper();

    }
    @Override
    public void update(float deltaTime) {
        entities=getEngine().getEntitiesFor(Family.all(BitMaskable.class, DrawableComponent.class, PositionComponent.class).get());

        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            PositionComponent position=positions.get(entity);
            DrawableComponent drawableComponent =drawables.get(entity);
            BitMaskable bitMaskable=bitmaskables.get(entity);
            int bitNumber=bitMask.eightSideBitMapCalculator(position.getTiles().get(0),entity,  getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY()));
            if(bitNumber!=bitMaskable.getBitNumber()){
                
                bitMaskable.setBitNumber(bitNumber);
                drawableComponent.setCurrentRegion(bitMaskable.getTextureRegion().get(bitNumber));
                
            }
            
            
            if(getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY())!=getDraw().getCurrentMap()) {
                drawableComponent.setDraw(false);
                continue;
            }
            drawableComponent.setDraw(true);
          
          
        }
    }


}