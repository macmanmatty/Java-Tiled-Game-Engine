package com.jessematty.black.tower.Generators.Entity.LPCGenerator;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.DrawableComponent;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.BodyParts.BodyComponent;
import com.jessematty.black.tower.Components.BodyParts.PartComponent;
import com.jessematty.black.tower.Components.Containers.PackComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.MovableComponent;
import com.jessematty.black.tower.Components.Position.PhysicalObjectComponent;
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
import com.jessematty.black.tower.GameBaseClasses.Utilities.FileUtilities;
import com.jessematty.black.tower.Generators.Entity.IEntityGenerator;
import com.jessematty.black.tower.Generators.Entity.LPCGenerator.Animations.LPCSpriteGenerator;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

import java.util.UUID;

/**
 * class that generates various entities based of the LPC assets
 */
public class LPCObjectGenerator implements IEntityGenerator<LPCObjectGeneratorDTO> {
   private LPCSpriteGenerator lpcSpriteGenerator;
     private    GameAssets assets;
     private ObjectMap<String,LPCObjectGeneratorDTO> lpcObjectGeneratorDTOMap= new ObjectMap<>();
    public LPCObjectGenerator(GameAssets assets) {
        this.assets = assets;
        lpcSpriteGenerator= new LPCSpriteGenerator(assets);
    }
    public  Entity generatePhysicalObject(LPCObjectGeneratorDTO lpcObjectGeneratorDTO, String id) {
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
        position.setBoundsXOffset(lpcObjectGeneratorDTO.getBoundsXOffset());
        position.setBoundsYOffset(lpcObjectGeneratorDTO.getBoundsYOffset());
        position.setHeight(lpcObjectGeneratorDTO.getHeight());
        position.setHeightFromGround(position.getHeightFromGround());
        entity.add(position);
        entity.add(physicalObject);
        EntityId entityId;
        entityId =new EntityId(id);
        entity.add(entityId);
        return  entity;
    }
    /**
     * links attached entities to the owner  entity
     * where the attached entities are generated an array of LPC Generation DTO's
     * @param lpcActorBag
     * @param lpcActorGeneratorDTO
     * @param lpcObjectGenerators
     * @return
     */
    public EntityBag linkAttachedEntitiesViaDTO(EntityBag lpcActorBag, LPCObjectGeneratorDTO lpcActorGeneratorDTO, Array<LPCObjectGeneratorDTO> lpcObjectGenerators) {
        Entity lpcActor=lpcActorBag.getOwner();
        for(LPCObjectGeneratorDTO lpcObjectGeneratorDTOAttached:lpcObjectGenerators) {
            EntityBag attachedEntityBag= generateEntity(lpcObjectGeneratorDTOAttached);
            Entity attached=attachedEntityBag.getOwner();
            if(lpcObjectGeneratorDTOAttached.isPart()){
                PartComponent partComponent= new PartComponent();
                partComponent.setPartClass(lpcObjectGeneratorDTOAttached.partClass);
                attached.add(partComponent );
                EntityUtilities.attachPart(lpcActor, attached);
            }
            else {
                EntityUtilities.attachEntity(lpcActor, attached);
            }            lpcActorBag.getEntities().add(attached);
            lpcActorBag.getEntities().addAll(attachedEntityBag.getEntities());
        }
        return lpcActorBag;
    }
    /**
     * links attached entities to the owner  entity
     * where the attached entities are generated an array of LPC Generation DTO ids
     * @param lpcActorBag
     * @param lpcActorGeneratorDTO
     * @param lpcObjectGeneratorsIds
     * @return
     */
    public EntityBag linkAttachedEntitiesViaDTOId(EntityBag lpcActorBag, LPCObjectGeneratorDTO lpcActorGeneratorDTO, Array<String> lpcObjectGeneratorsIds) {
        Entity lpcActor=lpcActorBag.getOwner();
        for(String lpcEntityDtoId: lpcObjectGeneratorsIds) {
            LPCObjectGeneratorDTO lpcObjectGeneratorDTOAttached=lpcObjectGeneratorDTOMap.get(lpcEntityDtoId);
            if(lpcObjectGeneratorDTOAttached==null){
                Gdx.app.getApplicationLogger().debug("Generation Exception", " No LPCObjectGeneratorDTO found for given Id "+lpcEntityDtoId);
                continue;
            }
            EntityBag attachedEntityBag= generateEntity(lpcObjectGeneratorDTOAttached);
            Entity attached=attachedEntityBag.getOwner();
            if(lpcObjectGeneratorDTOAttached.isPart()){
                EntityUtilities.attachPart(lpcActor, attached);
            }
            else {
                EntityUtilities.attachEntity(lpcActor, attached);
            }
            lpcActorBag.getEntities().add(attached);
            lpcActorBag.getEntities().addAll(attachedEntityBag.getEntities());
        }
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

    /**
     * generates a new entity  from a lpcObjectGeneratorDTO
     * with random id generated from a UUID
     * @param lpcObjectGeneratorDTO
     * @return The Entity Bag containing the owner entity and all sub entities
     */
    public  EntityBag generateEntity(LPCObjectGeneratorDTO lpcObjectGeneratorDTO) {
    return  generateEntity(lpcObjectGeneratorDTO, UUID.randomUUID().toString());
        }
    public  EntityBag generateEntity(LPCObjectGeneratorDTO lpcObjectGeneratorDTO, String id)  {
        EntityBag entityBag=new EntityBag();
        Entity entity = generatePhysicalObject(lpcObjectGeneratorDTO, id);
        entityBag.setOwner(entity);
        NameComponent nameComponent = new NameComponent();
        nameComponent.setStat(lpcObjectGeneratorDTO.getName());
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
        if(lpcObjectGeneratorDTO.isBody()){
            BodyComponent bodyComponent= new BodyComponent();
            bodyComponent.setPartsAddable(lpcObjectGeneratorDTO.getAttachableParts());
            entity.add(bodyComponent);
        }
        numericStats.addStat(new NumericStat(true, "health", 100, 0, 100));
        numericStats.addStat(new NumericStat(true, "speed", 10, 0, 40));
        boolean lpcActorAnimated=lpcObjectGeneratorDTO.isLpcActorAnimated();
        boolean drawable=lpcObjectGeneratorDTO.isDrawable();
        boolean animated=lpcObjectGeneratorDTO.isAnimated();
        AnimatableComponent animatableComponent=null;
            if(lpcActorAnimated) {
            lpcSpriteGenerator.setHasWalkFrames(lpcObjectGeneratorDTO.isHasWalkFrames());
            lpcSpriteGenerator.setHasDieFrames(lpcObjectGeneratorDTO.isHasDieFrames());
            lpcSpriteGenerator.setHasEatFrames(lpcObjectGeneratorDTO.isHasEatFrames());
            lpcSpriteGenerator.setHasShootFrames(lpcObjectGeneratorDTO.isHasShootFrames());
            lpcSpriteGenerator.setHasThrustFrames(lpcObjectGeneratorDTO.isHasThrustFrames());
            lpcSpriteGenerator.setHasSlashFrames(lpcObjectGeneratorDTO.isHashSlashFrames());
            lpcSpriteGenerator.setHasSpellCastFrames(lpcObjectGeneratorDTO.isHasSpellCastFrames());
            lpcSpriteGenerator.setHasPickUpFrames(lpcObjectGeneratorDTO.isHasPickupFrames());
            lpcSpriteGenerator.setHasThrowFrames(lpcObjectGeneratorDTO.isHasThrowFrames());
            lpcSpriteGenerator.upLayerNumberOffset=lpcObjectGeneratorDTO.getUpLayerOffset();
            lpcSpriteGenerator.downLayerNumberOffset=lpcObjectGeneratorDTO.getDownLayerOffset();
            lpcSpriteGenerator.rightLayerNumberOffset=lpcObjectGeneratorDTO.getRightLayerOffset();
            lpcSpriteGenerator.leftLayerNumberOffset=lpcObjectGeneratorDTO.getLeftLayerOffset();
            animatableComponent = lpcSpriteGenerator.makeBody(lpcObjectGeneratorDTO.getSex(), lpcObjectGeneratorDTO.getAnimatableBodyName(), lpcObjectGeneratorDTO.getAtlasName());
            animatableComponent.setCurrentAction("rest");
            animatableComponent.nextFrame();
            entity.add(animatableComponent);
        }
            if(drawable || lpcActorAnimated) {
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
                drawableComponent.setSubLayerNumber(lpcObjectGeneratorDTO.getDrawSubLayer());
                drawableComponent.setSetLayerToYPosition(lpcObjectGeneratorDTO.isSetDrawLayerToYPosition());
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
        if(lpcObjectGeneratorDTO.isPart()){
            PartComponent partComponent= new PartComponent();
            partComponent.setPartClass(lpcObjectGeneratorDTO.getPartClass());
            entity.add(partComponent);
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
        if(lpcObjectGeneratorDTO.getAttachedEntityDTOs().size>0) {
          linkAttachedEntitiesViaDTO(entityBag,lpcObjectGeneratorDTO,  lpcObjectGeneratorDTO.attachedEntityDTOs);
        }
        if (lpcObjectGeneratorDTO.getAttachedEntitiesTmxObjectIDs().size>0){
            linkAttachedEntitiesViaDTOId(entityBag, lpcObjectGeneratorDTO, lpcObjectGeneratorDTO.attachedEntitiesTmxObjectIDs);
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
        PackComponent packComponent = new PackComponent();
        packComponent.setMaxHoldWeight(lpcObjectGeneratorDTO.getMaxAtachedWeight());
        packComponent.setMaxVolume(lpcObjectGeneratorDTO.getInternalVolume());
       entityBag.getOwner().add(packComponent);
        return entityBag;
    }

    private Entity  createAnimatedLpcActor(Entity entity, LPCObjectGeneratorDTO lpcObjectGeneratorDTO){
        AnimatableComponent animatableComponent;
        lpcSpriteGenerator.setHasWalkFrames(lpcObjectGeneratorDTO.isHasWalkFrames());
        lpcSpriteGenerator.setHasDieFrames(lpcObjectGeneratorDTO.isHasDieFrames());
        lpcSpriteGenerator.setHasEatFrames(lpcObjectGeneratorDTO.isHasEatFrames());
        lpcSpriteGenerator.setHasShootFrames(lpcObjectGeneratorDTO.isHasShootFrames());
        lpcSpriteGenerator.setHasThrustFrames(lpcObjectGeneratorDTO.isHasThrustFrames());
        lpcSpriteGenerator.setHasSlashFrames(lpcObjectGeneratorDTO.isHashSlashFrames());
        lpcSpriteGenerator.setHasSpellCastFrames(lpcObjectGeneratorDTO.isHasSpellCastFrames());
        lpcSpriteGenerator.setHasPickUpFrames(lpcObjectGeneratorDTO.isHasPickupFrames());
        lpcSpriteGenerator.setHasThrowFrames(lpcObjectGeneratorDTO.isHasThrowFrames());
        lpcSpriteGenerator.upLayerNumberOffset=lpcObjectGeneratorDTO.getUpLayerOffset();
        lpcSpriteGenerator.downLayerNumberOffset=lpcObjectGeneratorDTO.getDownLayerOffset();
        lpcSpriteGenerator.rightLayerNumberOffset=lpcObjectGeneratorDTO.getRightLayerOffset();
        lpcSpriteGenerator.leftLayerNumberOffset=lpcObjectGeneratorDTO.getLeftLayerOffset();
        animatableComponent = lpcSpriteGenerator.makeBody(lpcObjectGeneratorDTO.getSex(), lpcObjectGeneratorDTO.getAnimatableBodyName(), lpcObjectGeneratorDTO.getAtlasName());
        animatableComponent.setCurrentAction("rest");
        animatableComponent.nextFrame();
        entity.add(animatableComponent);

        return entity;

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
        private   Entity makeBodyPart(EntityBag entityBag, LPCObjectGeneratorDTO lpcObjectGeneratorDTO){
        BodyComponent bodyComponent =GameComponentMapper.getBodyComponentComponentMapper().get(entityBag.getOwner());
        EntityBag bodyPartEntityBag= generateEntity(lpcObjectGeneratorDTO);
        Entity bodyPart=bodyPartEntityBag.getOwner();
        Entity ownerBody=entityBag.getOwner();
        EntityUtilities.attachEntity(ownerBody, bodyPart);
        return bodyPart;
    }
    /**
     * loads an map  of  LPCObjectDTO's from  an LPCGeneratorDTO json file
     * and returns an the object map
     * @param path
     * @return an map  of entities with the entity as the value and the and either the entities id or  entities name  as  the key
     * if an entity(s) have duplicate  names a number will be appended to the reference name;
     */
    public ObjectMap<String, LPCObjectGeneratorDTO> generateObjectDTOMap(String path, FileUtilities.FileHandleType fileHandleType){
      lpcObjectGeneratorDTOMap= new ObjectMap<>();
        JsonLoader jsonLoader=assets.getJsonLoader();
        Array<LPCObjectGeneratorDTO> lpcObjectGeneratorDTOS= jsonLoader.loadArrayFromFile(LPCObjectGeneratorDTO.class, path, fileHandleType);
        for(LPCObjectGeneratorDTO lpcObjectGeneratorDTO: lpcObjectGeneratorDTOS){
            lpcObjectGeneratorDTOMap.put(lpcObjectGeneratorDTO.getTmxObjectId(), lpcObjectGeneratorDTO);
        }
        return  lpcObjectGeneratorDTOMap;
    }
    private void makeHand(Entity entity){
        entity.add(new Holder());
    }
    /**
     * loads an array of  LPC entities from  an LPCGeneratorDTO json file
     * and returns an object map of the entities
     * @param path
     * @param useIdAsKey  whether or not use to the entities is as the primary key in the returned map of entities
     * @return an map  of entities with the entity as the value and the and either the entities id or  entities name  as  the key
     * if an entity(s) have duplicate  names a number will be appended to the reference name;
     */
    public ObjectMap<String, EntityBag> loadEntities(String path, boolean useIdAsKey, FileUtilities.FileHandleType fileHandleType){
        lpcObjectGeneratorDTOMap.clear();
        ObjectMap<String, EntityBag> entities= new ObjectMap<>();
        JsonLoader jsonLoader=assets.getJsonLoader();
        FileHandle fileHandle=FileUtilities.getFileHandle(path, fileHandleType);
        Array<LPCObjectGeneratorDTO>  lpcObjectGeneratorDTOS=  jsonLoader.loadArrayFromFile(LPCObjectGeneratorDTO.class, path, fileHandleType);
       for(LPCObjectGeneratorDTO lpcObjectGeneratorDTO: lpcObjectGeneratorDTOS){
           lpcObjectGeneratorDTOMap.put(lpcObjectGeneratorDTO.getTmxObjectId(), lpcObjectGeneratorDTO);
       }
       for (LPCObjectGeneratorDTO lpcObjectGeneratorDTO: lpcObjectGeneratorDTOS) {
           if(!lpcObjectGeneratorDTO.isLoad()){
               continue;
           }
           EntityBag entity = generateEntity(lpcObjectGeneratorDTO);
           String name=lpcObjectGeneratorDTO.getTmxObjectId();
           if(!useIdAsKey) {
              name= getName(entities, lpcObjectGeneratorDTO.getName());
           }
           entities.put(name, entity);
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
    public LandSquareTile generateTile(LPCObjectGeneratorDTO lpcObjectGeneratorDTO) {
            LandSquareTile landSquareTile= new LandSquareTile();
        return  landSquareTile;
    }
}
