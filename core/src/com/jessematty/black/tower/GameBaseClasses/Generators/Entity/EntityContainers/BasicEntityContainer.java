package com.jessematty.black.tower.GameBaseClasses.Generators.Entity.EntityContainers;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.PhysicalObject;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangable;

// entity container class containing all of the basic components required by the engine.
// used for faster component access
public class BasicEntityContainer {
   private  Entity entity;
   private ID id;
   private NumericStats numericStats;
   private BooleanStats booleanStats;
   private StringStats stringStats;
   private NumericStatsSelfChangable numericStatsSelfChangable;
   private StringStatsChangable stringStatsChangable;
   private BooleanStatsChangable booleanStatsChangable;
   private NumericStatsChangable numericStatsChangable;
   private Groups groups;
   private Name name;

    public BasicEntityContainer(Entity entity,  ID id, Name name, Groups groups,  NumericStats numericStats, BooleanStats booleanStats, StringStats stringStats, StringStatsChangable stringStatsChangable, NumericStatsSelfChangable numericStatsSelfChangable, BooleanStatsChangable booleanStatsChangable, NumericStatsChangable numericStatsChangable) {
        this.entity = entity;
        this.name=name;
        this.id = id;
        this.numericStats = numericStats;
        this.booleanStats = booleanStats;
        this.stringStats = stringStats;
        this.stringStatsChangable = stringStatsChangable;
        this.booleanStatsChangable = booleanStatsChangable;
        this.numericStatsChangable = numericStatsChangable;
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


    public StringStatsChangable getStringStatsChangable() {
        return stringStatsChangable;
    }



    public BooleanStatsChangable getBooleanStatsChangable() {
        return booleanStatsChangable;
    }

    public Name getName() {
        return name;
    }

    public NumericStatsChangable getNumericStatsChangable() {
        return numericStatsChangable;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }



}
