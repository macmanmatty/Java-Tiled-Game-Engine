package com.jessematty.black.tower.Generators.Entity.LPCGenerator;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
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
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.EntityId;
import com.jessematty.black.tower.Components.Info;
import com.jessematty.black.tower.Components.MovableComponent;
import com.jessematty.black.tower.Components.NameComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.Components.Thrower;
import com.jessematty.black.tower.Components.Thrustable;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator;
import com.jessematty.black.tower.Maps.World;
public class LPCActorGeneratorLPC extends LPCObjectGenerator {
   LPCSpriteGenerator lpcSpriteGenerator;
     private    GameAssets assets;
     private   World  world;
     private    GameComponentMapper gameComponentMapper;
    protected  int layerNumber=1;
     private int objectlayernumber =2;
     private ComponentMapper<EntityId> idComponentMapper;
    public LPCActorGeneratorLPC(GameAssets assets, World world ) {
        super(assets, world);
        this.assets = assets;
        this.world=world;
        this.gameComponentMapper=world.getGameComponentMapper();
        this.idComponentMapper=gameComponentMapper.getIdComponentMapper();
    }
    public EntityBag generateLPCCharacter(String atlasName, String bodyName, String name, String info, float brightness, NamedColor color, float mass, float volume, float boundsX, float boundsY, float health, float maxHealth, float strength, float hearingDistance, float seeingDistance, float iq, float experience, float maxSpeed, boolean flies, boolean swims ) {
        BasicEntityContainer container= EntityUtilities.makeBasicEntity();
       NumericStats numericStats=container.getNumericStats();
       NumericStat healthStat= new NumericStat(  true, "health", health, 0, maxHealth);
       healthStat.setKillWhenZero(true);
       numericStats.addStat(healthStat);
       BooleanStat invisible= new BooleanStat("invisible");
       BooleanStats booleanStats=container.getBooleanStats();
       booleanStats.addStat(invisible);
        container.getNumericStats().addStat(new NumericStat(true, "speed", 1, 0, 40));
        Entity lpcActor=container.getEntity();
        Body body= new Body();
        lpcActor.add(body);
        generatePhysicalObject(lpcActor, mass, volume, boundsX, boundsY);
         lpcActor=   generateAnimatedLPCActor(lpcActor, atlasName, bodyName, color, brightness);
        MovableComponent movableComponent = new MovableComponent();
        lpcActor.add(movableComponent);
        NameComponent nameComponent1 =new NameComponent(true,  name);
        lpcActor.add(nameComponent1);
       lpcActor.add(new Info(true, "info", info));
       StringStats stringStats=container.getStringStats();
       stringStats.addStat(nameComponent1);
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
        DrawableComponent drawableComponent = new DrawableComponent();
        drawableComponent.setCurrentRegion(namedAtlasRegion);
        drawableComponent.setDraw(true);
        drawableComponent.setLayerNumber(layerNumber);
        drawableComponent.setColor(color);
        drawableComponent.setBrightness(brightness);
        
        return  entity;
    }
    
    
    public Entity generateAnimatedLPCActor(Entity entity,  String atlasName, String bodyName, NamedColor color,  float brightness){
      
        AnimatableComponent animatable= new AnimatableComponent(true);
        lpcSpriteGenerator= new LPCSpriteGenerator(animatable, assets, atlasName, bodyName);
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
            DrawableComponent drawableComponent = new DrawableComponent();
            drawableComponent.setCurrentRegion(animatable.getCurrentTexture());
            drawableComponent.setDraw(true);
            drawableComponent.setLayerNumber(layerNumber);
            drawableComponent.setColor(color);
            drawableComponent.setBrightness(brightness);
            entity.add(drawableComponent);
            return entity;
    }
    public  Entity generateArmor(String bodyName, String atlasName, String name, String info, boolean hashSlashFrames, boolean hasThrustFrames, boolean hasWalkFrames, NamedColor color, float brightness, float condition, float volume, float mass, float length, float width, double damage, double speed, NumericStatsChangeable numericStatsChangeable, BooleanStatsChangeable booleanStatsChangeable) {
        com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer container= EntityUtilities.makeBasicEntity();
        Entity armor=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
       AnimatableComponent animatable= new AnimatableComponent(true);
        lpcSpriteGenerator= new com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator(animatable, assets, atlasName, bodyName);
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
        DrawableComponent drawableComponent = new DrawableComponent();
        drawableComponent.setCurrentRegion(animatable.getCurrentTexture());
        drawableComponent.setDraw(true);
        drawableComponent.setLayerNumber(layerNumber);
        drawableComponent.setColor(color);
        drawableComponent.setBrightness(brightness);
        MovableComponent movableComponent = new MovableComponent();
        armor.add(drawableComponent);
        armor.add(movableComponent);
        PositionComponent position= new PositionComponent();
        position.setBounds(width, length);
        ActionComponent actionComponent = new ActionComponent();
        armor.add(position);
        armor.add(actionComponent);
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        armor.add(physicalObject);
        armor.add(new NameComponent(true,  name));
        armor.add(new Info(true, "info", info));
        if(booleanStatsChangeable !=null){
            armor.add(booleanStatsChangeable);
        }
        if(numericStatsChangeable !=null){
            armor.add(numericStatsChangeable);
        }
        return armor;
    }
    public Entity generateItem(Entity entity, int price,  com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ActionComponent... components){
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
    public  Entity generateMeeleWeapon(String bodyName, String atlasName, String name, String info, boolean hashSlashFrames, boolean overSizeSlash, boolean hasThrustFrames, boolean hasWalkFrames, NamedColor color, float brightness, float condition, float volume, float mass, float length, float width, boolean upBehind, double damage, double speed, NumericStatsChangeable numericStatsChangeable, BooleanStatsChangeable booleanStatsChangeable) {
        com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer container= EntityUtilities.makeBasicEntity();        Entity weapon=container.getEntity();
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
       AnimatableComponent animatable= new AnimatableComponent(true);
        lpcSpriteGenerator= new com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator(animatable, assets, atlasName, bodyName);
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
        position.setBoundsXOffset(28);
        position.setBoundsYOffset(28);
        Rectangle boundingRectangle=position.getBoundsBoundingRectangle();
        position.getBounds().setOrigin(boundingRectangle.width/2f,boundingRectangle.height/50f );
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
    public  Entity generatePack(String atlasName, String name, String info, NamedColor color, float brightness, float volume, float mass, float length, float width, NumericStatsChangeable numericStatsChangeable, BooleanStatsChangeable booleanStatsChangeable) {
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
        DrawableComponent drawableComponent = new DrawableComponent();
        drawableComponent.setCurrentRegion(animatable.getCurrentTexture());
        drawableComponent.setDraw(true);
        drawableComponent.setLayerNumber(layerNumber);
        drawableComponent.setColor(color);
        drawableComponent.setBrightness(brightness);
        MovableComponent movableComponent = new MovableComponent();
        pack.add(drawableComponent);
        pack.add(movableComponent);
        PositionComponent position= new PositionComponent();
        position.setBounds(width, length);
        ActionComponent actionComponent = new ActionComponent();
        pack.add(position);
        pack.add(actionComponent);
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        pack.add(physicalObject);
        pack.add(new Pack());
        pack.add(new NameComponent(true, name));
        pack.add(new Info(true, "info", info));
        if(booleanStatsChangeable !=null){
            pack.add(booleanStatsChangeable);
        }
        if(numericStatsChangeable !=null){
            pack.add(numericStatsChangeable);
        }
        return pack;
    }
    public  Entity generateObject (String atlasName, String bodyName, String name) {
        com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer container= EntityUtilities.makeBasicEntity();
        Entity lpcActor=container.getEntity();
        container.getNameComponent().setStat(name);
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
        DrawableComponent drawableComponent = new DrawableComponent();
        drawableComponent.setCurrentRegion(assets.getAtlasRegionByName(bodyName, atlasName));
        drawableComponent.setDraw(true);
        drawableComponent.setColor(NamedColor.WHITE);
        drawableComponent.setBrightness(1f);
        drawableComponent.setLayerNumber(objectlayernumber);
        lpcActor.add(drawableComponent);
        PositionComponent position= new PositionComponent();
        ActionComponent actionComponent = new ActionComponent();
        lpcActor.add(position);
        lpcActor.add(actionComponent);
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
            String id=bodyPart.getComponent(EntityId.class).getId();
            OwnerComponent ownerComponent=ownerBody.getComponent(OwnerComponent.class);
            ownerComponent.addEntity(id);
        container.getNumericStats().addStat(new NumericStat(true, "health", 100, 0, 100));
        container.getNumericStats().addStat(new NumericStat(true, "speed", 20, 0, 40));
        bodyPart.add(new PositionComponent());
        Attachable attachable= new Attachable(bodyPart, ownerBody);
         attachable.setDrawAttachedItem(false);
            bodyPart.add(new Attachable( bodyPart, ownerBody));
        bodyPart.add(new BodyPart());
        bodyPart.add (new NameComponent(true, name));
        OwnedComponent ownedComponent= new OwnedComponent();
        ownedComponent.setAttached(true);
        ownedComponent.setOwnerEntityID(ownerBody.getComponent(EntityId.class).getId());
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
        wings.add (new NameComponent(true, "wings of "+name));
        wings.add(new ActionComponent());
        NumericStats numericStats=container.getNumericStats();
        NumericStat healthStat= new NumericStat( true, "health", 100, 0, 100);
        healthStat.setKillWhenZero(true);
        numericStats.addStat(healthStat);
        OwnedComponent ownedComponent= new OwnedComponent();
        ownedComponent.setOwnerEntityID(idComponentMapper.get(owner).getId());
        wings.add(ownedComponent);
        parts.getBodyParts().put(name,wings.getComponent(EntityId.class).getId());
        wings.add(new BodyPartSize(true,  "wingSize", size));
        DrawableComponent drawableComponent = new DrawableComponent();
        drawableComponent.setLayerNumber(layerNumber);
       AnimatableComponent animatable= new AnimatableComponent(true);
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        lpcSpriteGenerator= new com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator(animatable, assets, atlasName, spriteName);
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
        wings.add(drawableComponent);
        wings.add(animatable);
        drawableComponent.setCurrentRegion(animatable.getCurrentTexture());
        drawableComponent.setDraw(true);
        drawableComponent.setColor(color);
        drawableComponent.setBrightness(brightness);
        animatable.nextFrame();
        MovableComponent movableComponent = new MovableComponent();
        wings.add(movableComponent);
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
        horns.add (new NameComponent(true, "horns of "+name));
        horns.add(new ActionComponent());
        parts.getBodyParts().put(name+"Hand",horns.getComponent(EntityId.class).getId());
        horns.add(new BodyPartSize(true,  "wingSize", size));
        DrawableComponent drawableComponent = new DrawableComponent();
        drawableComponent.setLayerNumber(layerNumber);
       AnimatableComponent animatable= new AnimatableComponent(true);
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
        physicalObject.setMass(mass);
        physicalObject.setVolume(volume);
        OwnedComponent ownedComponent= new OwnedComponent();
        ownedComponent.setOwnerEntityID(idComponentMapper.get(owner).getId());
        horns.add(ownedComponent);
        lpcSpriteGenerator= new LPCSpriteGenerator(animatable, assets, atlasName, spriteName);
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
        horns.add(drawableComponent);
        horns.add(animatable);
        drawableComponent.setCurrentRegion(animatable.getCurrentTexture());
        drawableComponent.setDraw(true);
        drawableComponent.setColor(color);
        drawableComponent.setBrightness(brightness);
        animatable.nextFrame();
        MovableComponent movableComponent = new MovableComponent();
        horns.add(movableComponent);
        return horns;
    }
}
