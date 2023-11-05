package com.jessematty.black.tower.Generators.Entity.LPCGenerator;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.BodyParts.BodyComponent;
import com.jessematty.black.tower.Components.BodyParts.BodyPart;
import com.jessematty.black.tower.Components.Containers.PackComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityBag;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.AtlasNamedAtlasRegion;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.NamedColor.NamedColor;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator;
import com.jessematty.black.tower.Maps.World;

import java.util.List;
/**
 * class that generates various entities based of the LPC assets
 */
public class LPCObjectGenerator {
   private LPCSpriteGenerator lpcSpriteGenerator;
     private    GameAssets assets;
     private   World  world;
     private ComponentMapper<EntityId> idComponentMapper;
    public LPCObjectGenerator(GameAssets assets, World world ) {
        this.assets = assets;
        this.world=world;
        this.idComponentMapper=GameComponentMapper.getIdComponentMapper();
        lpcSpriteGenerator= new LPCSpriteGenerator(assets);

    }
    private Entity generatePhysicalObject(LPCObjectGeneratorDTO lpcObjectGeneratorDTO) {
        Entity entity= new Entity();
        PhysicalObjectComponent physicalObject= new PhysicalObjectComponent();
        physicalObject.setMass(lpcObjectGeneratorDTO.getMass());
        physicalObject.setVolume(lpcObjectGeneratorDTO.getVolume());
        entity.add(physicalObject);
        Polygon bounds=lpcObjectGeneratorDTO.getBounds();
        PositionComponent position= new PositionComponent();
        if(bounds!=null){
            position.setBounds(bounds);
        }
        else {
            position.setBounds(lpcObjectGeneratorDTO.getBoundsX(), lpcObjectGeneratorDTO.getBoundsY());
        }
        entity.add(position);
        entity.add(physicalObject);
        EntityId entityId = new EntityId();
        entity.add(entityId);
        return  entity;
    }
    public EntityBag generateLPCCharacter(EntityBag lpcActorBag, LPCObjectGeneratorDTO lpcActorGeneratorDTO) {
        Entity lpcActor=lpcActorBag.getOwner();
        lpcActor.add(new BodyComponent());
        Entity leftHand= makeBodyPart(lpcActor, "leftHand",  lpcActorGeneratorDTO.getGloveSize());
        makeHand(leftHand);
        Entity rightHand= makeBodyPart(lpcActor, "rightHand", lpcActorGeneratorDTO.getGloveSize());
        makeHand(rightHand);
        Entity leftFoot= makeBodyPart(lpcActor, "leftFoot", lpcActorGeneratorDTO.getShoeSize());
        Entity rightFoot= makeBodyPart(lpcActor, "rightFoot",lpcActorGeneratorDTO.getShoeSize());
        lpcActorBag.getEntities().add(rightHand);
        lpcActorBag.getEntities().add(leftFoot);
        lpcActorBag.getEntities().add(rightFoot);
        lpcActorBag.getEntities().add(leftHand);
        return lpcActorBag;
    }
    public Entity generateStaticLPCActor(Entity entity, AtlasNamedAtlasRegion namedAtlasRegion, NamedColor color, float brightness) {
        DrawableComponent drawableComponent = new DrawableComponent();
        drawableComponent.setCurrentRegion(namedAtlasRegion);
        drawableComponent.setDraw(true);
        drawableComponent.setLayerNumber(1);
        drawableComponent.setColor(color);
        drawableComponent.setBrightness(brightness);

        return  entity;
    }
    
    private Entity generateAnimatedLPCActor( Entity entity, LPCObjectGeneratorDTO lpcObjectGeneratorDTO){
        lpcSpriteGenerator.setHasWalkFrames(true);
        lpcSpriteGenerator.setHasDieFrames(true);
        lpcSpriteGenerator.setHasEatFrames(true);
        lpcSpriteGenerator.setHasShootFrames(true);
        lpcSpriteGenerator.setHasThrustFrames(true);
        lpcSpriteGenerator.setHasSlashFrames(true);
        lpcSpriteGenerator.setHasSpellCastFrames(true);
        lpcSpriteGenerator.setHasPickUpFrames(true);
        lpcSpriteGenerator.setHasThrowFrames(true);
     AnimatableComponent animatableComponent  = lpcSpriteGenerator.makeBody(lpcObjectGeneratorDTO.getSex(), lpcObjectGeneratorDTO.getBody(), lpcObjectGeneratorDTO.getAtlasName());
        animatableComponent.setCurrentAction("rest");
        animatableComponent.nextFrame();
        entity.add(animatableComponent);
            DrawableComponent drawableComponent = new DrawableComponent();
            drawableComponent.setCurrentRegion(animatableComponent.getCurrentTexture());
            drawableComponent.setDraw(true);
            drawableComponent.setLayerNumber(1);
            drawableComponent.setColor(new NamedColor());
            drawableComponent.setBrightness(lpcObjectGeneratorDTO.getBrightness());
            entity.add(drawableComponent);
            return entity;
    }
    public  EntityBag generateLPCEntity(LPCObjectGeneratorDTO lpcObjectGeneratorDTO) {
        EntityBag entityBag=new EntityBag();
        Entity entity = generatePhysicalObject(lpcObjectGeneratorDTO);
        entityBag.setOwner(entity);

        NameComponent nameComponent = new NameComponent();
        entity.add(nameComponent);
        ActionComponent actionComponent = new ActionComponent();
        entity.add(actionComponent);
        NumericStats numericStats = new NumericStats();
        entity.add(numericStats);
        BooleanStats booleanStats = new BooleanStats();
        entity.add(booleanStats);
        StringStats stringStats = new StringStats();
        entity.add(stringStats);
        NumericStatsChangeable numericStatsChangeable = new NumericStatsChangeable();
        entity.add(numericStatsChangeable);
        BooleanStatsChangeable booleanStatsChangeable = new BooleanStatsChangeable();
        entity.add(booleanStatsChangeable);
        StringStatsChangeable stringStatsChangeable = new StringStatsChangeable();
        entity.add(stringStatsChangeable);
        NumericStatsSelfChangable numericStatsSelfChangable = new NumericStatsSelfChangable();
        entity.add(numericStatsSelfChangable);
        GroupsComponent groupsComponent = new GroupsComponent();
        groupsComponent.getGroups().add("entity");
        groupsComponent.getGroups().add(lpcObjectGeneratorDTO.getName());
        groupsComponent.getGroups().addAll(lpcObjectGeneratorDTO.getGroups());
        entity.add(groupsComponent);
        OwnerComponent ownerComponent= new OwnerComponent();
        entity.add(ownerComponent);
        numericStats.addStat(new NumericStat(true, "health", 100, 0, 100));
        numericStats.addStat(new NumericStat(true, "speed", 20, 0, 40));
        boolean animated=lpcObjectGeneratorDTO.isAnimated();
        boolean drawable=lpcObjectGeneratorDTO.isDrawable();
        AnimatableComponent animatableComponent=null;
        if(animated) {
            lpcSpriteGenerator.setHasWalkFrames(lpcObjectGeneratorDTO.isHasWalkFrames());
            lpcSpriteGenerator.setHasDieFrames(lpcObjectGeneratorDTO.isHasDieFrames());
            lpcSpriteGenerator.setHasEatFrames(lpcObjectGeneratorDTO.isHasEatFrames());
            lpcSpriteGenerator.setHasShootFrames(lpcObjectGeneratorDTO.isHasShootFrames());
            lpcSpriteGenerator.setHasThrustFrames(lpcObjectGeneratorDTO.isHasThrustFrames());
            lpcSpriteGenerator.setHasSlashFrames(lpcObjectGeneratorDTO.isHashSlashFrames());
            lpcSpriteGenerator.setHasSpellCastFrames(lpcObjectGeneratorDTO.isHasSpellCastFrames());
            lpcSpriteGenerator.setHasPickUpFrames(lpcObjectGeneratorDTO.isHasPickupFrames());
            lpcSpriteGenerator.setHasThrowFrames(lpcObjectGeneratorDTO.isHasThrowFrames());
            animatableComponent = lpcSpriteGenerator.makeBody(lpcObjectGeneratorDTO.getSex(), lpcObjectGeneratorDTO.getBody(), lpcObjectGeneratorDTO.getAtlasName());
            animatableComponent.setCurrentAction("rest");
            animatableComponent.nextFrame();
            entity.add(animatableComponent);
        }
            if(drawable || animated) {
                DrawableComponent drawableComponent = new DrawableComponent();
                if(animatableComponent!=null) {
                    drawableComponent.setCurrentRegion(animatableComponent.getCurrentTexture());
                }
                else{
                    AtlasNamedAtlasRegion image=null;
                    if(lpcObjectGeneratorDTO.isUseDownFrame1AsImage()){
                       image=animatableComponent.getCurrentTexture();
                    }
                    else {
                        image = assets.getAtlasRegionByName(lpcObjectGeneratorDTO.getImageName(), lpcObjectGeneratorDTO.getAtlasName());
                    }
                    if(lpcObjectGeneratorDTO.isHasImage()) {
                        ImageComponent imageComponent = new ImageComponent();
                        imageComponent.setImage(image);
                    }
                    drawableComponent.setCurrentRegion(image);
                }
                drawableComponent.setDraw(lpcObjectGeneratorDTO.isDrawOnStart());
                drawableComponent.setLayerNumber(lpcObjectGeneratorDTO.getDrawLayer());
                NamedColor color = new NamedColor(lpcObjectGeneratorDTO.getColorR(), lpcObjectGeneratorDTO.getColorG(), lpcObjectGeneratorDTO.getColorB(), lpcObjectGeneratorDTO.getColorA());
                drawableComponent.setColor(color);
                drawableComponent.setBrightness(lpcObjectGeneratorDTO.getBrightness());
                entity.add(drawableComponent);
            }
        if(lpcObjectGeneratorDTO.isMoveable()) {
            MovableComponent movableComponent = new MovableComponent();
            entity.add(movableComponent);
        }
        booleanStatsChangeable.getStatsToChange().addAll(lpcObjectGeneratorDTO.getBooleanStatChangeableList());
        numericStatsChangeable.getStatsToChange().addAll(lpcObjectGeneratorDTO.getNumericStatsChangeableList());
        stringStatsChangeable.getStatsToChange().addAll(lpcObjectGeneratorDTO.getStringStatChangeableList());
        addStats(lpcObjectGeneratorDTO.getStats(), numericStats, booleanStats, stringStats);
        entity.add(booleanStatsChangeable);
        entity.add(numericStatsChangeable);
        if(lpcObjectGeneratorDTO.isItem()){
            generateItem( entityBag, lpcObjectGeneratorDTO);
        }
        if(lpcObjectGeneratorDTO.isPack()){
            generatePack( entityBag, lpcObjectGeneratorDTO);
        }
        
        if(lpcObjectGeneratorDTO.isThrustable() || lpcObjectGeneratorDTO.isSlashable()){
            generateWeapon( entityBag , lpcObjectGeneratorDTO);
        }
        if(lpcObjectGeneratorDTO.isWearable()){
            generateArmor(entityBag, lpcObjectGeneratorDTO);
        }
        
        if(lpcObjectGeneratorDTO.isReadable()){
            generateReadable(entityBag, lpcObjectGeneratorDTO);
        }
        
        if(lpcObjectGeneratorDTO.isPlant()){
            generatePlant(entityBag, lpcObjectGeneratorDTO);
        }
        
        if(lpcObjectGeneratorDTO.isShootable()){
            generateBowedWeapon(entityBag, lpcObjectGeneratorDTO);
        }
        
        if(lpcObjectGeneratorDTO.isHolder()) {
            generateHolder(entityBag, lpcObjectGeneratorDTO);
        }

        if(lpcObjectGeneratorDTO.isHumanLikeCharacter()) {
          generateLPCCharacter(entityBag, lpcObjectGeneratorDTO);
        }

        return  entityBag;
        }

    private Array<Entity> createOwnedEntities( Entity owner, LPCObjectGeneratorDTO lpcObjectGeneratorDTO) {

        return new Array<>();
    }

    private void generateHolder(EntityBag entityBag, LPCObjectGeneratorDTO lpcObjectGeneratorDTO) {
    }
    private void generateBowedWeapon(EntityBag entityBag, LPCObjectGeneratorDTO lpcObjectGeneratorDTO) {
    }
    private void generateReadable(EntityBag entityBag, LPCObjectGeneratorDTO lpcObjectGeneratorDTO) {
    }
    private void generatePlant(EntityBag entityBag, LPCObjectGeneratorDTO lpcObjectGeneratorDTO) {
    }
    
    
    private EntityBag  generatePack(EntityBag entityBag, LPCObjectGeneratorDTO lpcObjectGeneratorDTO){
        EntityBag pack= generateLPCEntity(lpcObjectGeneratorDTO);
        PackComponent packComponent = new PackComponent();
        packComponent.setMaxHoldWeight(lpcObjectGeneratorDTO.getMaxAtachedWeight());
        packComponent.setMaxVolume(lpcObjectGeneratorDTO.getInternalVolume());
        pack.getOwner().add(packComponent);
        return pack;
    }
    private void addStats(Array<Stat> stats , NumericStats numericStats, BooleanStats booleanStats, StringStats stringStats){
        for(Stat stat: stats ){
            if(stat instanceof  NumericStat){
                numericStats.addStat((NumericStat) stat);
            }
            if(stat instanceof  BooleanStat){
                booleanStats.addStat((BooleanStat) stat);
            }
            if(stat instanceof  StringStat){
                stringStats.addStat((StringStat) stat);
            }
        }
    }
    private  void generateArmor(EntityBag entityBag, LPCObjectGeneratorDTO lpcObjectGeneratorDTO) {

    }
    private void generateItem(EntityBag entityBag, LPCObjectGeneratorDTO lpcObjectGenerator){
        ItemComponent itemComponent= new ItemComponent();
        itemComponent.setPrice(lpcObjectGenerator.getPrice());
        itemComponent.setMaxPrice(lpcObjectGenerator.getMaxPrice());
        itemComponent.setMinPrice(lpcObjectGenerator.getMinPrice());
        entityBag.getOwner().add(itemComponent);

    }
    private void generateWeapon(EntityBag entityBag, LPCObjectGeneratorDTO lpcObjectGeneratorDTO){

    }
        private   Entity makeBodyPart(Entity ownerBody, String name, int size){
        BodyComponent bodyComponent =ownerBody.getComponent(BodyComponent.class);
        BasicEntityContainer container= EntityUtilities.makeBasicEntity();
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
        ownedComponent.setPhysicallyAttached(true);
        ownedComponent.setOwnerEntityID(ownerBody.getComponent(EntityId.class).getId());
        ownedComponent.setSetEntityActionToOwner(true);
        ownedComponent.setSetEntityPositionToOwner(true);
        bodyPart.add(ownedComponent);
        bodyComponent.getBodyParts().put(name,id);
        container.getNumericStats().addStat(new NumericStat(true, "handSize", size, 0, 0));
        return bodyPart;
    }
    private void makeHand(Entity entity){
       entity.add(new Holder());
    }
    /**
     * loads an array of  LPC entities from  an LPCGeneratorDTO json file
     * and returns an object map of the entities
     * @param path
     * @param addToWorld whether or not add the entity to the world object
     * @param useIdAsKey  whether or not use to the entities is as the primary key in the returned map of entities
     * @return an map  of entities with the entity as the value and the and either the entities id or  entities name  as  the key
     * if an entity(s) have duplicate  names a number will be appended to the reference name;
     */
    public ObjectMap<String, EntityBag> loadEntities(String path, boolean useIdAsKey, boolean addToWorld){
        ObjectMap<String, EntityBag> entities= new ObjectMap<>();
        JsonLoader jsonLoader=assets.getJsonLoader();
       List<LPCObjectGeneratorDTO> lpcObjectGeneratorDTOS= jsonLoader.loadArrayFromFile(LPCObjectGeneratorDTO.class, path);
       for (LPCObjectGeneratorDTO lpcObjectGeneratorDTO: lpcObjectGeneratorDTOS) {
           if(!lpcObjectGeneratorDTO.isLoad()){
               continue;
           }
           EntityBag entity = generateLPCEntity(lpcObjectGeneratorDTO);
           String name=idComponentMapper.get(entity.getOwner()).getId();
           if(!useIdAsKey) {
              name= getName(entities, lpcObjectGeneratorDTO.getName());
           }
           entities.put(name, entity);
           if(addToWorld==true){
              world.addEntityToWorld(entity);
           }
       }
       return entities;
    }

    public String getName(ObjectMap<String, EntityBag> entityObjectMap, String name){
        int counter=2;
        String newName=name;
        while(entityObjectMap.get(newName)!=null){
             newName=name+counter;
            counter++;
        }
        return newName;
    }

}
