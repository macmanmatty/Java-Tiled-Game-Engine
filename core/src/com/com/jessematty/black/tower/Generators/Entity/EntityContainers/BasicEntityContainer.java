package com.jessematty.black.tower.Generators.Entity.EntityContainers;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.NameComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;

// entity container class containing all of the basic components required by the engine.
// used for faster component access
public class BasicEntityContainer {
   private  Entity entity;
   private ID id;
   private NumericStats numericStats;
   private BooleanStats booleanStats;
   private StringStats stringStats;
   private NumericStatsSelfChangable numericStatsSelfChangable;
   private StringStatsChangeable stringStatsChangeable;
   private BooleanStatsChangeable booleanStatsChangeable;
   private NumericStatsChangeable numericStatsChangeable;
   private Groups groups;
   private NameComponent nameComponent;

    public BasicEntityContainer(Entity entity, ID id, NameComponent nameComponent, Groups groups, NumericStats numericStats, BooleanStats booleanStats, StringStats stringStats, StringStatsChangeable stringStatsChangeable, NumericStatsSelfChangable numericStatsSelfChangable, BooleanStatsChangeable booleanStatsChangeable, NumericStatsChangeable numericStatsChangeable) {
        this.entity = entity;
        this.nameComponent = nameComponent;
        this.id = id;
        this.numericStats = numericStats;
        this.booleanStats = booleanStats;
        this.stringStats = stringStats;
        this.stringStatsChangeable = stringStatsChangeable;
        this.booleanStatsChangeable = booleanStatsChangeable;
        this.numericStatsChangeable = numericStatsChangeable;
        this.numericStatsSelfChangable=numericStatsSelfChangable;
        this.groups=groups;
    }

    public Entity getEntity() {
        return entity;
    }




    public ID getId() {
        return id;
    }


    public NumericStats getNumericStats() {
        return numericStats;
    }


    public BooleanStats getBooleanStats() {
        return booleanStats;
    }


    public StringStats getStringStats() {
        return stringStats;
    }


    public StringStatsChangeable getStringStatsChangeable() {
        return stringStatsChangeable;
    }



    public BooleanStatsChangeable getBooleanStatsChangeable() {
        return booleanStatsChangeable;
    }

    public NameComponent getNameComponent() {
        return nameComponent;
    }

    public NumericStatsChangeable getNumericStatsChangeable() {
        return numericStatsChangeable;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }



}
