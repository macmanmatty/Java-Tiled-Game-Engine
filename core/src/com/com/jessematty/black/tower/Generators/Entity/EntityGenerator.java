package com.jessematty.black.tower.Generators.Entity;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Base.Groups;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Copy.CopyObject;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer;
import com.jessematty.black.tower.Maps.World;
// create a basic  with all  of the  required components.
public class EntityGenerator {


    protected  static CopyObject copyObject;
    protected  World world;
    protected GameAssets gameAssets;

    public EntityGenerator(World world, GameAssets gameAssets) {
        this.world = world;
        this.gameAssets = gameAssets;
        copyObject = new CopyObject(gameAssets);
    }
   //  creates  a basic entity  with all the components requires by the engine  and returns its entity container for easier access of it components
    public static com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer makeBasicEntity( ){
        Entity entity=new Entity();
        EntityId entityId = new EntityId();
        entity.add(entityId);
        NameComponent nameComponent = new NameComponent();
        entity.add(nameComponent);
        PositionComponent position= new PositionComponent();
        entity.add(position);
        ActionComponent actionComponent = new ActionComponent();
        entity.add(actionComponent);
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
        physicalObject.setMass(1);
        physicalObject.setVolume(1);
        entity.add(physicalObject);
        NumericStats numericStats = new NumericStats();
        numericStats.addStat(new NumericStat(true, "speed", 0, 0, Integer.MAX_VALUE));
        entity.add(numericStats);
        BooleanStats booleanStats= new BooleanStats();
        entity.add(booleanStats);
        StringStats stringStats=  new StringStats();
        entity.add(stringStats);
        NumericStatsChangeable numericStatsChangeable = new NumericStatsChangeable();
        entity.add(numericStatsChangeable);
        BooleanStatsChangeable booleanStatsChangeable = new BooleanStatsChangeable();
        entity.add(booleanStatsChangeable);
        StringStatsChangeable stringStatsChangeable =  new StringStatsChangeable();
        entity.add(stringStatsChangeable);
        Groups groups= new Groups();
        groups.getGroups().add("entity");
        entity.add(groups);
        NumericStatsSelfChangable numericStatsSelfChangable= new NumericStatsSelfChangable();
        entity.add(numericStatsSelfChangable);
        return  new BasicEntityContainer(entity, entityId, nameComponent, groups,   numericStats, booleanStats, stringStats, stringStatsChangeable, numericStatsSelfChangable, booleanStatsChangeable, numericStatsChangeable);
    }


    // creates a deep copy of a  a given entity
    public static Entity  copyEntity(Entity  entity){

       return  copyObject.copyObject(entity, Entity.class);

    }


}
