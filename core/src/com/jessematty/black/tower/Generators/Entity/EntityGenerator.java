package com.jessematty.black.tower.Generators.Entity;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;
import com.jessematty.black.tower.GameBaseClasses.Loaders.Copy.CopyObject;
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
        ID id= new ID();
        entity.add(id);
        Name name= new Name();
        entity.add(name);
        PositionComponent position= new PositionComponent();
        entity.add(position);
        Action action= new Action();
        entity.add(action);
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
        return  new BasicEntityContainer(entity,  id , name, groups,   numericStats, booleanStats, stringStats, stringStatsChangeable, numericStatsSelfChangable, booleanStatsChangeable, numericStatsChangeable);
    }


    // creates a deep copy of a  a given entity
    public static Entity  copyEntity(Entity  entity){

       return  copyObject.copyObject(entity, Entity.class);

    }


}
