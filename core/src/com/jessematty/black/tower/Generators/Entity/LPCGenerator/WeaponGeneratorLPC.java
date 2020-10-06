package com.jessematty.black.tower.Generators.Entity.LPCGenerator;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.Info;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer;
import com.jessematty.black.tower.Maps.World;

public class WeaponGeneratorLPC extends LPCActorGeneratorLPC {



    public WeaponGeneratorLPC(GameAssets assets, World world) {
        super(assets, world);
    }


    public Entity generateMeeleWeapon(String bodyName, String atlasName, String name, String info, boolean hashSlashFrames, boolean hasThrustFrames, boolean hasWalkFrames, NamedColor color, float brightness, float condition, float volume, float mass, float length, float width, boolean upBehind, double damage, double speed, NumericStatsChangable numericStatsChangable, BooleanStatsChangable booleanStatsChangable) {
        BasicEntityContainer container= EntityUtilities.makeBasicEntity();        Entity weapon=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
        AnimatableComponent animatable= new AnimatableComponent(true);
       LPCSpriteGenerator lpcSpriteGenerator= new LPCSpriteGenerator(animatable, getAssets(), atlasName, bodyName);
        lpcSpriteGenerator.setHasWalkFrames(hasWalkFrames);
        lpcSpriteGenerator.setHasSlashFrames(hashSlashFrames);
        lpcSpriteGenerator.setHasThrustFrames(hasThrustFrames);
        if(upBehind==true){
            lpcSpriteGenerator.upLayerNumberOffset=3;
            lpcSpriteGenerator.upLayerNumberOffset=1;
            lpcSpriteGenerator.upLayerNumberOffset=1;


            lpcSpriteGenerator.downLayerNumberOffset=-1;
        }
        lpcSpriteGenerator.makeBody();
        animatable.setCurrentAction("rest");
        animatable.nextFrame();
        weapon.add(animatable);
        NumericStats numericStats=container.getNumericStats();
        NumericStat healthStat= new NumericStat( true, "health", 100, 0, 100);
        healthStat.setKillWhenZero(true);
        numericStats.addStat(healthStat);
        Drawable drawable= new Drawable();
        drawable.setCurrentRegion(animatable.getCurrentTexture());
        drawable.setDraw(true);
        drawable.setLayerNumber(layerNumber);
        drawable.setColor(color);
        drawable.setBrightness(brightness);
        Movable movable = new Movable();
        weapon.add(drawable);
        weapon.add(movable);
        PositionComponent position= new PositionComponent();
        position.setBounds(width, length);
        position.setBoundsXOffset(32);
        position.setBoundsYOffset(10);
        Action action= new Action();
        weapon.add(position);
        weapon.add(action);
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        weapon.add(physicalObject);
        weapon.add(new Name(true, name));
        weapon.add(new Info(true, "info", info));
        if(booleanStatsChangable !=null){
            weapon.add(booleanStatsChangable);
        }
        if(numericStatsChangable !=null){
            weapon.add(numericStatsChangable);
        }
        generateItem(weapon, 100);

        return weapon;
    }



}
