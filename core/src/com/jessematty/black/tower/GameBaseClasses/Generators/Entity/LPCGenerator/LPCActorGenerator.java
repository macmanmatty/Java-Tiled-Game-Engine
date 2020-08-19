package com.jessematty.black.tower.GameBaseClasses.Generators.Entity.LPCGenerator;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.BodyParts.BodyPart;
import com.jessematty.black.tower.Components.BodyParts.BodyPartSize;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.Holder;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Info;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.Attachable;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.PhysicalObject;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangable;
import com.jessematty.black.tower.Components.Thrower;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Generators.Entity.EntityContainers.BasicEntityContainer;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.Maps.World;

public class LPCActorGenerator {
      LPCSpriteGenerator lpcSpriteGenerator;
     private    GameAssets assetts;
     private   World  world;
     private    GameComponentMapper gameComponentMapper;
     private int layerNumber=1;
     private int objectlayerNumber=2;
     private ComponentMapper<ID> idComponentMapper;
    public LPCActorGenerator(World world , GameAssets assetts) {
        this.assetts=assetts;
        this.world=world;
        this.gameComponentMapper=world.getGameComponentMapper();
        this.idComponentMapper=gameComponentMapper.getIdComponentMapper();
    }



    public Entity generatePhysicalObject(Entity entity, float  mass,  float volume, float  boundsX, float boundsY) {
        PhysicalObject physicalObject= new PhysicalObject();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        entity.add(physicalObject);
        Position position= new Position();
        position.setBounds(boundsX, boundsY);
        entity.add(position);
        entity.add(physicalObject);

        return  entity;

    }




    public  Entity generateLPCCharacter(String atlasName, String bodyName, String name, String info, float brightness, NamedColor color, float mass, float volume, float boundsX, float boundsY, float health, float maxHealth, float strength, float hearingDistance, float seeingDistance, float iq, float experience, float maxSpeed, boolean flies, boolean swims ) {
       BasicEntityContainer container= EntityUtilities.makeBasicEntity();
       NumericStats numericStats=container.getNumericStats();
       NumericStat healthStat= new NumericStat( true, "health", health, 0, maxHealth);
       healthStat.setKillWhenZero(true);
       numericStats.addStat(healthStat);
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
        Entity lpcActor=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "speed", 200, 0, 400));
        generatePhysicalObject(lpcActor, mass, volume, boundsX, boundsY);
         lpcActor=   generateAnimatedLPCActor(lpcActor, atlasName, bodyName, color, brightness);
        Movable movable = new Movable();
        lpcActor.add(movable);
        lpcActor.add(new Name(true,  name));
       lpcActor.add(new Info(true, "info", info));
        Thrower thrower=  new Thrower();
       // lpcActor.add(thrower);
        Body body= new Body();
        Entity leftHand= makeHand(lpcActor, "left", body);
        Entity rightHand= makeHand(lpcActor, "right", body);
        Entity leftFoot= makeFoot(lpcActor, "left", body);
        Entity rightFoot= makeHand(lpcActor, "right", body);
        lpcActor.add(body);

        return lpcActor;
    }

    public Entity generateStaticLPCActor(Entity entity, AtlasNamedAtlasRegion namedAtlasRegion, NamedColor color, float brightness) {
        Drawable drawable = new Drawable();
        drawable.setCurrentRegion(namedAtlasRegion);
        drawable.setDraw(true);
        drawable.setLayerNumber(layerNumber);
        drawable.setColor(color);
        drawable.setBrightness(brightness);
        
        return  entity;

    }
    
    
    public Entity generateAnimatedLPCActor(Entity entity,  String atlasName, String bodyName, NamedColor color,  float brightness){
      
        AnimatableComponent animatable= new AnimatableComponent(true);
        lpcSpriteGenerator= new com.jessematty.black.tower.GameBaseClasses.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator(animatable, assetts, atlasName, bodyName);
        lpcSpriteGenerator.setHasWalkFrames(true);
        lpcSpriteGenerator.setHasDieFrames(true);
        lpcSpriteGenerator.setHasEatFrames(true);
        lpcSpriteGenerator.setHasShootFrames(true);
        lpcSpriteGenerator.setHasThrustFrames(true);
        lpcSpriteGenerator.setHasSlashFrames(true);
        lpcSpriteGenerator.setHasSpellCastFrames(true);
        lpcSpriteGenerator.setHasPickUpFrames(true);
        lpcSpriteGenerator.setHasThrowFrames(true);
        lpcSpriteGenerator.makeBody();
        animatable.setCurrentAction("rest");
        animatable.nextFrame();
        entity.add(animatable);

            Drawable drawable= new Drawable();
            drawable.setCurrentRegion(animatable.getCurrentTexture());
            drawable.setDraw(true);
            drawable.setLayerNumber(layerNumber);
            drawable.setColor(color);
            drawable.setBrightness(brightness);
            entity.add(drawable);




            return entity;
    }
    public  Entity generateArmor(String bodyName, String atlasName, String name, String info, boolean hashSlashFrames, boolean hasThrustFrames, boolean hasWalkFrames, NamedColor color, float brightness, float condition, float volume, float mass, float length, float width, double damage, double speed, NumericStatsChangable numericStatsChangable, BooleanStatsChangable booleanStatsChangable) {
        BasicEntityContainer container= EntityUtilities.makeBasicEntity();
        Entity armor=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
       AnimatableComponent animatable= new AnimatableComponent(true);
        lpcSpriteGenerator= new com.jessematty.black.tower.GameBaseClasses.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator(animatable, assetts, atlasName, bodyName);
        lpcSpriteGenerator.setHasWalkFrames(true);
        lpcSpriteGenerator.setHasDieFrames(true);
        lpcSpriteGenerator.setHasEatFrames(true);
        lpcSpriteGenerator.setHasShootFrames(true);
        lpcSpriteGenerator.setHasThrustFrames(true);
        lpcSpriteGenerator.setHasSlashFrames(true);
        lpcSpriteGenerator.setHasSpellCastFrames(true);
        lpcSpriteGenerator.setHasPickUpFrames(true);
        lpcSpriteGenerator.setHasThrowFrames(true);
        lpcSpriteGenerator.makeBody();
        animatable.setCurrentAction("rest");
        animatable.nextFrame();
        armor.add(animatable);
        Drawable drawable= new Drawable();
        drawable.setCurrentRegion(animatable.getCurrentTexture());
        drawable.setDraw(true);
        drawable.setLayerNumber(layerNumber);
        drawable.setColor(color);
        drawable.setBrightness(brightness);
        Movable movable = new Movable();
        armor.add(drawable);
        armor.add(movable);
        Position position= new Position();
        position.setBounds(width, length);
        Action action= new Action();
        armor.add(position);
        armor.add(action);
        PhysicalObject physicalObject= new PhysicalObject();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        armor.add(physicalObject);
        armor.add(new Name(true,  name));
        armor.add(new Info(true, "info", info));
        if(booleanStatsChangable !=null){
            armor.add(booleanStatsChangable);
        }
        if(numericStatsChangable !=null){
            armor.add(numericStatsChangable);
        }
        return armor;
    }
    public  Entity generateMeeleWeapon(String bodyName, String atlasName, String name, String info, boolean hashSlashFrames, boolean hasThrustFrames, boolean hasWalkFrames, NamedColor color, float brightness, float condition, float volume, float mass, float length, float width, boolean upBehind,  double damage, double speed, NumericStatsChangable numericStatsChangable, BooleanStatsChangable booleanStatsChangable) {
        BasicEntityContainer container= EntityUtilities.makeBasicEntity();        Entity weapon=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
       AnimatableComponent animatable= new AnimatableComponent(true);
        lpcSpriteGenerator= new com.jessematty.black.tower.GameBaseClasses.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator(animatable, assetts, atlasName, bodyName);
        lpcSpriteGenerator.setHasWalkFrames(hasWalkFrames);
        lpcSpriteGenerator.setHasSlashFrames(hashSlashFrames);
        lpcSpriteGenerator.setHasThrustFrames(hasThrustFrames);
        if(upBehind==true){
            lpcSpriteGenerator.upLayerNumberOffset=-1;
            lpcSpriteGenerator.downLayerNumberOffset=+1;
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
        Position position= new Position();
        position.setBounds(width, length);
        Action action= new Action();
        weapon.add(position);
        weapon.add(action);
        PhysicalObject physicalObject= new PhysicalObject();
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
        return weapon;
    }



    public  Entity generatePack(String atlasName, String name, String info,  NamedColor color, float brightness, float volume, float mass, float length, float width,   NumericStatsChangable numericStatsChangable, BooleanStatsChangable booleanStatsChangable) {
        BasicEntityContainer container= EntityUtilities.makeBasicEntity();        Entity pack=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
       AnimatableComponent animatable= new AnimatableComponent(true);
        lpcSpriteGenerator.setHasWalkFrames(true);
        lpcSpriteGenerator.setHasDieFrames(true);
        lpcSpriteGenerator.setHasEatFrames(true);
        lpcSpriteGenerator.setHasShootFrames(true);
        lpcSpriteGenerator.setHasThrustFrames(true);
        lpcSpriteGenerator.setHasSlashFrames(true);
        lpcSpriteGenerator.setHasSpellCastFrames(true);
        lpcSpriteGenerator.setHasPickUpFrames(true);
        lpcSpriteGenerator.setHasThrowFrames(true);
        lpcSpriteGenerator.makeBody();
        animatable.setCurrentAction("rest");
        animatable.nextFrame();
        pack.add(animatable);
        Drawable drawable= new Drawable();
        drawable.setCurrentRegion(animatable.getCurrentTexture());
        drawable.setDraw(true);
        drawable.setLayerNumber(layerNumber);
        drawable.setColor(color);
        drawable.setBrightness(brightness);
        Movable movable = new Movable();
        pack.add(drawable);
        pack.add(movable);
        Position position= new Position();
        position.setBounds(width, length);
        Action action= new Action();
        pack.add(position);
        pack.add(action);
        PhysicalObject physicalObject= new PhysicalObject();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        pack.add(physicalObject);
        pack.add(new Pack());
        pack.add(new Name(true, name));
        pack.add(new Info(true, "info", info));
        if(booleanStatsChangable !=null){
            pack.add(booleanStatsChangable);
        }
        if(numericStatsChangable !=null){
            pack.add(numericStatsChangable);
        }
        return pack;
    }
    public  Entity generateObject (String atlasName, String bodyName) {
        BasicEntityContainer container= EntityUtilities.makeBasicEntity();        Entity lpcActor=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
        Drawable drawable= new Drawable();
        drawable.setCurrentRegion(assetts.getAtlasRegionByName(bodyName, atlasName));
        drawable.setDraw(true);
        drawable.setColor(NamedColor.WHITE);
        drawable.setBrightness(1f);
        drawable.setLayerNumber(objectlayerNumber);
        lpcActor.add(drawable);
        Position position= new Position();
        Action action= new Action();
        lpcActor.add(position);
        lpcActor.add(action);
        PhysicalObject physicalObject= new PhysicalObject();
        physicalObject.setMass(1000);
        physicalObject.setVolume(1500);
        lpcActor.add(physicalObject);
        return lpcActor;
    }
    public   Entity makeHand( Entity entity, String handOrientation, Body parts){
        BasicEntityContainer container= EntityUtilities.makeBasicEntity();        Entity hand=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
        hand.add(new Holder());
        hand.add(new Position());
        hand.add(new Attachable( hand, entity));
        hand.add(new BodyPart());
        hand.add (new Name(true, handOrientation+"hand"));
        parts.getBodyParts().put(handOrientation+"Hand",hand. getComponent(ID.class).getId());
        hand.add(new BodyPartSize(true,  "handSize", 4));
        return hand;
    }
    public    Entity makeFoot( Entity entity, String footOrientation, Body parts){
        BasicEntityContainer container= EntityUtilities.makeBasicEntity();        Entity foot=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
        foot.add(new Holder());
        foot.add(new Position());
        foot.add(new Attachable( foot, entity));
        foot.add(new BodyPart());
        foot.add (new Name(true, footOrientation+"hand"));
        parts.getBodyParts().put(footOrientation+"Hand",foot.getComponent(ID.class).getId() );
        foot.add(new BodyPartSize(true,  "footSize", 4));
        return foot;
    }
    public   Entity makeWings(  String atlasName, String spriteName, Entity owner, String name, Body parts, NamedColor color, float brightness, int size, float mass, float volume){
        BasicEntityContainer container= EntityUtilities.makeBasicEntity();        Entity wings=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        wings.add(new Position());
        wings.add(new Attachable( wings, owner));
        wings.add(new BodyPart());
        wings.add (new Name(true, "wings of "+name));
        wings.add(new Action());
        NumericStats numericStats=container.getNumericStats();
        NumericStat healthStat= new NumericStat( true, "health", 100, 0, 100);
        healthStat.setKillWhenZero(true);
        numericStats.addStat(healthStat);
        OwnedComponent ownedComponent= new OwnedComponent();
        ownedComponent.setOwnerEntityID(idComponentMapper.get(owner).getId());
        wings.add(ownedComponent);
        parts.getBodyParts().put(name,wings.getComponent(ID.class).getId());
        wings.add(new BodyPartSize(true,  "wingSize", size));
        Drawable drawable= new Drawable();
        drawable.setLayerNumber(layerNumber);
       AnimatableComponent animatable= new AnimatableComponent(true);
        PhysicalObject physicalObject= new PhysicalObject();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        lpcSpriteGenerator= new LPCSpriteGenerator(animatable, assetts, atlasName, spriteName);
        lpcSpriteGenerator.setHasWalkFrames(true);
        lpcSpriteGenerator.setHasDieFrames(true);
        lpcSpriteGenerator.setHasEatFrames(true);
        lpcSpriteGenerator.setHasShootFrames(true);
        lpcSpriteGenerator.setHasThrustFrames(true);
        lpcSpriteGenerator.setHasSlashFrames(true);
        lpcSpriteGenerator.setHasSpellCastFrames(true);
        lpcSpriteGenerator.setHasPickUpFrames(true);
        lpcSpriteGenerator.setHasThrowFrames(true);
        lpcSpriteGenerator.upLayerNumberOffset=1;
        lpcSpriteGenerator.downLayerNumberOffset=-1;
        lpcSpriteGenerator.leftLayerNumberOffset=-1;
        lpcSpriteGenerator.rightLayerNumberOffset=-1;
        lpcSpriteGenerator.makeBody();
        wings.add(drawable);
        wings.add(animatable);
        drawable.setCurrentRegion(animatable.getCurrentTexture());
        drawable.setDraw(true);
        drawable.setColor(color);
        drawable.setBrightness(brightness);
        animatable.nextFrame();
        Movable movable = new Movable();
        wings.add(movable);
        return wings;
    }







    public   Entity makeHorns(  String atlasName, String spriteName, Entity owner, String name, Body parts, NamedColor color, float brightness, int size, float mass, float volume){
        BasicEntityContainer container= EntityUtilities.makeBasicEntity();
        Entity horns=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
        horns.add(new Position());
        horns.add(new Attachable( horns, owner));
        horns.add(new BodyPart());
        horns.add (new Name(true, "horns of "+name));
        horns.add(new Action());
        parts.getBodyParts().put(name+"Hand",horns.getComponent(ID.class).getId());
        horns.add(new BodyPartSize(true,  "wingSize", size));
        Drawable drawable= new Drawable();
        drawable.setLayerNumber(layerNumber);
       AnimatableComponent animatable= new AnimatableComponent(true);
        PhysicalObject physicalObject= new PhysicalObject();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);

        OwnedComponent ownedComponent= new OwnedComponent();
        ownedComponent.setOwnerEntityID(idComponentMapper.get(owner).getId());

        horns.add(ownedComponent);
        lpcSpriteGenerator= new LPCSpriteGenerator(animatable, assetts, atlasName, spriteName);
        lpcSpriteGenerator.setHasWalkFrames(true);
        lpcSpriteGenerator.setHasDieFrames(true);
        lpcSpriteGenerator.setHasEatFrames(true);
        lpcSpriteGenerator.setHasShootFrames(true);
        lpcSpriteGenerator.setHasThrustFrames(true);
        lpcSpriteGenerator.setHasSlashFrames(true);
        lpcSpriteGenerator.setHasSpellCastFrames(true);
        lpcSpriteGenerator.setHasPickUpFrames(true);
        lpcSpriteGenerator.setHasThrowFrames(true);
        lpcSpriteGenerator.makeBody();
        horns.add(drawable);
        horns.add(animatable);
        drawable.setCurrentRegion(animatable.getCurrentTexture());
        drawable.setDraw(true);
        drawable.setColor(color);
        drawable.setBrightness(brightness);
        animatable.nextFrame();
        Movable movable = new Movable();
        horns.add(movable);
        return horns;
    }
}
