package com.jessematty.black.tower.Generators.Entity.EntityContainers;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;

// entity container class containing all of the basic components required by the engine.
// used for faster component access
public class BasicEntityContainer {
   private  Entity entity;
   private EntityId id;
   private NumericStats numericStats;
   private BooleanStats booleanStats;
   private StringStats stringStats;
   private NumericStatsSelfChangable numericStatsSelfChangable;
   private StringStatsChangeable stringStatsChangeable;
   private BooleanStatsChangeable booleanStatsChangeable;
   private NumericStatsChangeable numericStatsChangeable;
   private GroupsComponent groupsComponent;
   private NameComponent nameComponent;

    public BasicEntityContainer(Entity entity, EntityId entityId, NameComponent nameComponent, GroupsComponent groupsComponent, NumericStats numericStats, BooleanStats booleanStats, StringStats stringStats, StringStatsChangeable stringStatsChangeable, NumericStatsSelfChangable numericStatsSelfChangable, BooleanStatsChangeable booleanStatsChangeable, NumericStatsChangeable numericStatsChangeable) {
        this.entity = entity;
        this.nameComponent = nameComponent;
        this.id = entityId;
        this.numericStats = numericStats;
        this.booleanStats = booleanStats;
        this.stringStats = stringStats;
        this.stringStatsChangeable = stringStatsChangeable;
        this.booleanStatsChangeable = booleanStatsChangeable;
        this.numericStatsChangeable = numericStatsChangeable;
        this.numericStatsSelfChangable=numericStatsSelfChangable;
        this.groupsComponent = groupsComponent;
    }

    public Entity getEntity() {
        return entity;
    }




    public EntityId getId() {
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

    public GroupsComponent getGroupsComponent() {
        return groupsComponent;
    }

    public void setGroupsComponent(GroupsComponent groupsComponent) {
        this.groupsComponent = groupsComponent;
    }



}
