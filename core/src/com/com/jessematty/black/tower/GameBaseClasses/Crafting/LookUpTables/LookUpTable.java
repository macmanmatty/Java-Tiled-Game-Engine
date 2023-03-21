package com.jessematty.black.tower.GameBaseClasses.Crafting.LookUpTables;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;

public  abstract class LookUpTable {

   Array<EntityToCreate> entitiesToCreate = new Array<>();

    protected ComponentMapper<PhysicalObjectComponent> physicalObjectComponentMapper;
    protected  ComponentMapper<NumericStats> numericStatsComponentMapper;
    protected ComponentMapper<BooleanStats> booleanStatsComponentMapper;
    protected  ComponentMapper<StringStats> stringStatsComponentMapper;
   protected  GameComponentMapper gameComponentMapper;

    public LookUpTable(GameComponentMapper gameComponentMapper) {
        this.gameComponentMapper = gameComponentMapper;
        physicalObjectComponentMapper=gameComponentMapper.getPhysicalObjectComponentMapper();
        numericStatsComponentMapper=gameComponentMapper.getNumericStatsComponentMapper();
        booleanStatsComponentMapper=gameComponentMapper.getBooleanStatsComponentMapper();
        stringStatsComponentMapper=gameComponentMapper.getStringStatsComponentMapper();
    }



}
