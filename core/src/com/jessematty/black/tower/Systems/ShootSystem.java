package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class ShootSystem extends GameEntitySystem {

   private  float targetScreenLocationX;
   private  float targetScreenLocationY;
   private Entity projectile;
   private Entity launcher;
   private ComponentMapper<PositionComponent> positionComponentMapper;
   private  ComponentMapper<NumericStats> numericStatsComponentMapper;

    public ShootSystem(MapDraw draw, float targetScreenLocationX, float targetScreenLocationY, Entity projectile, Entity launcher) {
        super(draw);
        this.targetScreenLocationX = targetScreenLocationX;
        this.targetScreenLocationY = targetScreenLocationY;
        this.projectile = projectile;
        this.launcher = launcher;
    }

    public ShootSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void update(float deltaTime) {





    }
}














