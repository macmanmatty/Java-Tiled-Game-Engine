package com.jessematty.black.tower.Generators.Entity.LPCGenerator;

import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Info;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.NameComponent;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer;
import com.jessematty.black.tower.Maps.World;

public class WeaponGeneratorLPC extends LPCActorGeneratorLPC {



    public WeaponGeneratorLPC(GameAssets assets, World world) {
        super(assets, world);
    }


    public Entity generateMeeleWeapon(String bodyName, String atlasName, String name, String info, boolean hashSlashFrames, boolean hasThrustFrames, boolean hasWalkFrames, NamedColor color, float brightness, float condition, float volume, float mass, float length, float width, boolean upBehind, double damage, double speed, NumericStatsChangeable numericStatsChangeable, BooleanStatsChangeable booleanStatsChangeable) {
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
        DrawableComponent drawableComponent = new DrawableComponent();
        drawableComponent.setCurrentRegion(animatable.getCurrentTexture());
        drawableComponent.setDraw(true);
        drawableComponent.setLayerNumber(layerNumber);
        drawableComponent.setColor(color);
        drawableComponent.setBrightness(brightness);
        MovableComponent movableComponent = new MovableComponent();
        weapon.add(drawableComponent);
        weapon.add(movableComponent);
        PositionComponent position= new PositionComponent();
        position.setBounds(width, length);
        position.setBoundsXOffset(32);
        position.setBoundsYOffset(10);
        ActionComponent actionComponent = new ActionComponent();
        weapon.add(position);
        weapon.add(actionComponent);
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        weapon.add(physicalObject);
        weapon.add(new NameComponent(true, name));
        weapon.add(new Info(true, "info", info));
        if(booleanStatsChangeable !=null){
            weapon.add(booleanStatsChangeable);
        }
        if(numericStatsChangeable !=null){
            weapon.add(numericStatsChangeable);
        }
        generateItem(weapon, 100);

        return weapon;
    }



}
