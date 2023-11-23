package com.jessematty.black.tower.Systems.Entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangeMode;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ColorChangingStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class ColorChangeSystem extends GameEntitySystem {
    private ComponentMapper<NumericStats> numericStatsComponentMapper;
    private ComponentMapper<DrawableComponent> drawableComponentMapper;
    private ImmutableArray<Entity> entities;
    public ColorChangeSystem(MapDraw draw) {
        super(draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        numericStatsComponentMapper=GameComponentMapper.getNumericStatsComponentMapper();
        drawableComponentMapper= GameComponentMapper.getDrawableComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities= getEngine().getEntitiesFor(Family.all(NumericStats.class, DrawableComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++){
            Entity entity=entities.get(count);
            NumericStats stats=numericStatsComponentMapper.get(entity);
            DrawableComponent drawableComponent =drawableComponentMapper.get(entity);
            if(drawableComponent.getColorChangeMode()!= ColorChangeMode.NumericStat){
                continue;
            }
            float brightness=1;
            NamedColor color=new NamedColor(0,0,0,0);// create an empty color
            Array<ColorChangingStat> colorChangingStatList=stats.getColorChangingStats();
            int size2=colorChangingStatList.size;
            for(int count2=0; count2<size2; count2++){ // loop through color changing stats
                ColorChangingStat stat=colorChangingStatList.get(count2);
                if(stat.isAffectsBlue()){
                    color.b=color.b+stat.getBlueValue(); // calculate blue red alpha green and brightness values
                }
                if(stat.isAffectsRed()){
                    color.r=color.r+stat.getRedValue();
                }
                if(stat.isAffectsGreen()){
                    color.g=color.g+stat.getGreenValue();
                }
                if(stat.isAffectsAlpha()){
                    color.a=color.a+stat.getAlphaValue();
                }

                drawableComponent.setColor(color);
                drawableComponent.setBrightness(brightness);
            }
        }
        super.update(deltaTime);
    }
}
