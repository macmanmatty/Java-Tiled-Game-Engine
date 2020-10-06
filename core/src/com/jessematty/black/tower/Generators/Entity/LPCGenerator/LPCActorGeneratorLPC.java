package com.jessematty.black.tower.Generators.Entity.LPCGenerator;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Drop;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Hold;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Throw;
import com.jessematty.black.tower.Components.Actions.ActionComponents;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.BodyParts.BodyPart;
import com.jessematty.black.tower.Components.BodyParts.BodyPartSize;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Slashable;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Info;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Thrower;
import com.jessematty.black.tower.Components.Thrustable;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator;
import com.jessematty.black.tower.Maps.World;

public class LPCActorGeneratorLPC extends LPCObjectGenerator {
      com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator lpcSpriteGenerator;
     private    GameAssets assetts;
     private   World  world;
     private    GameComponentMapper gameComponentMapper;
    protected  int layerNumber=1;
     private int objectlayerNumber=2;
     private ComponentMapper<ID> idComponentMapper;
    public LPCActorGeneratorLPC(GameAssets assetts, World world ) {
        super(assetts, world);

        this.assetts=assetts;
        this.world=world;
        this.gameComponentMapper=world.getGameComponentMapper();
        this.idComponentMapper=gameComponentMapper.getIdComponentMapper();
    }




    public EntityBag generateLPCCharacter(String atlasName, String bodyName, String name, String info, float brightness, NamedColor color, float mass, float volume, float boundsX, float boundsY, float health, float maxHealth, float strength, float hearingDistance, float seeingDistance, float iq, float experience, float maxSpeed, boolean flies, boolean swims ) {
       com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer container= EntityUtilities.makeBasicEntity();
       NumericStats numericStats=container.getNumericStats();
       NumericStat healthStat= new NumericStat( assetts, true, "health", health, 0, maxHealth);
       healthStat.setKillWhenZero(true);
       numericStats.addStat(healthStat);
       BooleanStat invisible= new BooleanStat("invisible");
       BooleanStats booleanStats=container.getBooleanStats();
       booleanStats.addStat(invisible);
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
        Entity lpcActor=container.getEntity();
        Body body= new Body();
        lpcActor.add(body);
        generatePhysicalObject(lpcActor, mass, volume, boundsX, boundsY);
         lpcActor=   generateAnimatedLPCActor(lpcActor, atlasName, bodyName, color, brightness);
        Movable movable = new Movable();
        lpcActor.add(movable);
        Name name1=new Name(true,  name);
        lpcActor.add(name1);
       lpcActor.add(new Info(true, "info", info));
       StringStats stringStats=container.getStringStats();
       stringStats.addStat(name1);

        Thrower thrower=  new Thrower();
        lpcActor.add(thrower);
        Entity leftHand= makeBodyPart(lpcActor, "leftHand",  5);
        makeHand(leftHand);
        Entity rightHand= makeBodyPart(lpcActor, "rightHand", 5);
        makeHand(rightHand);
        Entity leftFoot= makeBodyPart(lpcActor, "leftFoot", 5);
        Entity rightFoot= makeBodyPart(lpcActor, "rightFoot",5);
        EntityBag entityBag= new EntityBag();
        entityBag.getEntities().add(lpcActor);
        entityBag.getEntities().add(rightHand);
        entityBag.getEntities().add(leftFoot);
        entityBag.getEntities().add(rightFoot);
        entityBag.getEntities().add(leftHand);


        return entityBag;
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
        lpcSpriteGenerator= new com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator(animatable, assetts, atlasName, bodyName);
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
        com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer container= EntityUtilities.makeBasicEntity();
        Entity armor=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
       AnimatableComponent animatable= new AnimatableComponent(true);
        lpcSpriteGenerator= new com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator(animatable, assetts, atlasName, bodyName);
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
        PositionComponent position= new PositionComponent();
        position.setBounds(width, length);
        Action action= new Action();
        armor.add(position);
        armor.add(action);
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
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
    public Entity generateItem(Entity entity, int price,  ActionComponent... components){
        Item item= new Item();
        item.setPrice(price);
        entity.add(item);
        ActionComponents actionComponents= new ActionComponents();
        actionComponents.getActionComponents().add(new Throw());
        actionComponents.getActionComponents().add(new Drop());
        actionComponents.getActionComponents().add(new Hold());
        actionComponents.getActionComponents().addAll(components);
        entity.add(actionComponents);
        return  entity;

    }




    public  Entity generateMeeleWeapon(String bodyName, String atlasName, String name, String info, boolean hashSlashFrames, boolean overSizeSlash,  boolean hasThrustFrames, boolean hasWalkFrames, NamedColor color, float brightness, float condition, float volume, float mass, float length, float width, boolean upBehind,  double damage, double speed, NumericStatsChangable numericStatsChangable, BooleanStatsChangable booleanStatsChangable) {
        com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer container= EntityUtilities.makeBasicEntity();        Entity weapon=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
       AnimatableComponent animatable= new AnimatableComponent(true);
        lpcSpriteGenerator= new com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator(animatable, assetts, atlasName, bodyName);
        lpcSpriteGenerator.setHasWalkFrames(hasWalkFrames);
        lpcSpriteGenerator.setHasSlashFrames(hashSlashFrames);
        lpcSpriteGenerator.setHasThrustFrames(hasThrustFrames);



        if(upBehind==true){
            lpcSpriteGenerator.upLayerNumberOffset=3;
            lpcSpriteGenerator.upLayerNumberOffset=1;
            lpcSpriteGenerator.upLayerNumberOffset=1;


            lpcSpriteGenerator.downLayerNumberOffset=-1;
        }
        if(overSizeSlash==true){
           lpcSpriteGenerator.slashDownOffsets=new Vector2(-64, -64);
            lpcSpriteGenerator.slashUpOffsets=new Vector2(-64, -64);
            lpcSpriteGenerator.slashLeftOffsets=new Vector2(-64, -64);
            lpcSpriteGenerator.slashRightOffsets=new Vector2(-64, -64);


        }


        lpcSpriteGenerator.makeBody();

        if(hashSlashFrames==true){
            Slashable slashable=new Slashable();
            weapon.add(slashable);

        }
        if(hasThrustFrames==true){
            Thrustable thrustable=new Thrustable();
            weapon.add(thrustable);

        }


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
        position.setBoundsXOffset(28);
        position.setBoundsYOffset(28);
        Rectangle boundingRectangle=position.getBoundsBoundingRectangle();
        position.getBounds().setOrigin(boundingRectangle.width/2f,boundingRectangle.height/50f );
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



    public  Entity generatePack(String atlasName, String name, String info,  NamedColor color, float brightness, float volume, float mass, float length, float width,   NumericStatsChangable numericStatsChangable, BooleanStatsChangable booleanStatsChangable) {
        com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer container= EntityUtilities.makeBasicEntity();        Entity pack=container.getEntity();
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
        PositionComponent position= new PositionComponent();
        position.setBounds(width, length);
        Action action= new Action();
        pack.add(position);
        pack.add(action);
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
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
    public  Entity generateObject (String atlasName, String bodyName, String name) {
        com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer container= EntityUtilities.makeBasicEntity();
        Entity lpcActor=container.getEntity();
        container.getName().setStat(name);
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
        Drawable drawable= new Drawable();
        drawable.setCurrentRegion(assetts.getAtlasRegionByName(bodyName, atlasName));
        drawable.setDraw(true);
        drawable.setColor(NamedColor.WHITE);
        drawable.setBrightness(1f);
        drawable.setLayerNumber(objectlayerNumber);
        lpcActor.add(drawable);
        PositionComponent position= new PositionComponent();
        Action action= new Action();
        lpcActor.add(position);
        lpcActor.add(action);
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
        physicalObject.setMass(1000);
        physicalObject.setVolume(1500);
        lpcActor.add(physicalObject);
        return lpcActor;
    }
        public   Entity makeBodyPart(Entity ownerBody, String name, int size){
        Body body=ownerBody.getComponent(Body.class);
        com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer container= EntityUtilities.makeBasicEntity();
        Entity bodyPart=container.getEntity();
            String id=bodyPart.getComponent(ID.class).getId();
            OwnerComponent ownerComponent=ownerBody.getComponent(OwnerComponent.class);
            ownerComponent.addEntity(id);
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
        bodyPart.add(new PositionComponent());
        Attachable attachable= new Attachable(bodyPart, ownerBody);
         attachable.setDrawAttachedItem(false);
            bodyPart.add(new Attachable( bodyPart, ownerBody));
        bodyPart.add(new BodyPart());
        bodyPart.add (new Name(true, name));
        OwnedComponent ownedComponent= new OwnedComponent();
        ownedComponent.setAttached(true);
        ownedComponent.setOwnerEntityID(ownerBody.getComponent(ID.class).getId());
        ownedComponent.setSetEntityActionToOwner(true);
        ownedComponent.setSetEntityPositionToOwner(true);
        bodyPart.add(ownedComponent);
        body.getBodyParts().put(name,id);
        container.getNumericStats().addStat(new NumericStat(true, "handSize", size, 0, 0));
        return bodyPart;
    }

    public void makeHand(Entity entity){
       entity.add(new Holder());

    }



    public   Entity makeWings(  String atlasName, String spriteName, Entity owner, String name, Body parts, NamedColor color, float brightness, int size, float mass, float volume){
        com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer container= EntityUtilities.makeBasicEntity();        Entity wings=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        wings.add(new PositionComponent());
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
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        lpcSpriteGenerator= new com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator(animatable, assetts, atlasName, spriteName);
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
        horns.add(new PositionComponent());
        horns.add(new Attachable( horns, owner));
        horns.add(new BodyPart());
        horns.add (new Name(true, "horns of "+name));
        horns.add(new Action());
        parts.getBodyParts().put(name+"Hand",horns.getComponent(ID.class).getId());
        horns.add(new BodyPartSize(true,  "wingSize", size));
        Drawable drawable= new Drawable();
        drawable.setLayerNumber(layerNumber);
       AnimatableComponent animatable= new AnimatableComponent(true);
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
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
