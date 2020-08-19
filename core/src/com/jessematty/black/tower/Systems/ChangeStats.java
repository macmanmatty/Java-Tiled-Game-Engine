package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeComponent;

public class ChangeStats {

    public static void  changeStats(Entity entity, Entity entity2, String action, boolean changeBothEntities,  boolean addStat, boolean addStat2) {
        entity.add(new NumericStatsChangeComponent(entity2, action, addStat));
        entity.add(new BooleanStatsChangeComponent(entity2, action, addStat));
        entity.add(new StringStatsChangeComponent(entity2, action, addStat));
        if(changeBothEntities) {
            entity2.add(new BooleanStatsChangeComponent(entity, action, addStat2));

            entity2.add(new NumericStatsChangeComponent(entity, action, addStat2));

            entity2.add(new StringStatsChangeComponent(entity, action, addStat2));
        }


    }
    
}
