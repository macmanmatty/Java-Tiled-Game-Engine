package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Other.RemoveFromEngine;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatChangeable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangeable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.Copy.CopyObject;
import com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer;
import com.jessematty.black.tower.Maps.World;

public class EntityUtilities {
  
   private static Array<Entity> entities= new Array<>();
   private static Array<String> entityIds= new Array<>();
   private static  boolean connected;
   private EntityUtilities() {
    }

    /**
     *     get an  array of all entities and sub owned entities  owned by a entity using recursion
     *     and returns the attached entities
     */
   public static  Array<Entity> getAllOwnedEntities(Entity entity, World world){
     entities.clear();
       ComponentMapper<OwnerComponent> ownerComponentMapper=world.getGameComponentMapper().getOwnerComponentComponentMapper();
       OwnerComponent entityOwnerComponent =ownerComponentMapper.get(entity);
       if(entityOwnerComponent==null || entityOwnerComponent.getOwnedEntityIDs().isEmpty()){
           return  entities;
       }
       entityIds.clear();
       getAllOwnedEntitiesIDsInternal(entity, world);
        return entities;
   }
    /**
     *     get an  array of all entities and sub owned entities  owned by a entity using recursion
     *     and returns the attached entities ids
     */
    public static  Array<String> getAllOwnedEntitiesIDs(Entity entity, World world){
        entityIds.clear();
        ComponentMapper<OwnerComponent> ownerComponentMapper=world.getGameComponentMapper().getOwnerComponentComponentMapper();
        OwnerComponent entityOwnerComponent =ownerComponentMapper.get(entity);
        if(entityOwnerComponent==null || entityOwnerComponent.getOwnedEntityIDs().isEmpty()){
            return  entityIds;
        }
        entities.clear();
        getAllOwnedEntitiesIDsInternal(entity, world);
        return entityIds;
    }
  public static  Array<String> getAllConnectedEntitiesIds(Entity entity, World world, boolean attached) {
        entities.clear();
        entityIds.clear();
       ComponentMapper<EntityId> idComponentMapper=world.getGameComponentMapper().getIdComponentMapper();
        entities.add(entity);
        entityIds.add(idComponentMapper.get(entity).getId());
      return getAllConnectedEntitiesIdsInternal(entity, world, attached);
   }

    /**
     * internal method for getting all attached entities and sun entities  ids
     * @param entity the entity to get the entities for
     * @param world the game world object
     * @param attached whether for not search for physically attach entities
     * @return
     */
    private static  Array<String> getAllConnectedEntitiesIdsInternal(Entity entity, World world, boolean attached){
         entities.clear();
         entityIds.clear();
        ComponentMapper<OwnerComponent> ownerComponentMapper=world.getGameComponentMapper().getOwnerComponentComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentComponentMapperr=world.getGameComponentMapper().getOwnedComponentComponentMapper();
       OwnerComponent entityOwnerComponent =ownerComponentMapper.get(entity);
        OwnedComponent ownedComponent=ownedComponentComponentMapperr.get(entity);
        if(entityOwnerComponent==null && ownedComponent==null ){
            return  entityIds;
        }
      // get owner entity if  entity has one
        if(ownedComponent!=null) {
            String ownerId = ownedComponent.getOwnerEntityID();
            boolean attachedToOwner = ownedComponent.isPhysicallyAttached();
            if (attached == false || attachedToOwner == true) {
                if (ownerId != null && !ownerId.isEmpty() && attachedToOwner == true) {
                    Entity owner = world.getEntity(ownerId);
                   if(!InList.isInList(entities, owner)) {
                        entities.add(owner);
                        entityIds.add(ownerId);
                        getAllConnectedEntitiesIdsInternal(owner, world, attached);
                    }
               }
           }
       }
           // get  all owned entities
        if(entityOwnerComponent!=null) {
            Array<String> ownedEntityIds = entityOwnerComponent.getOwnedEntityIDs();
            int size = ownedEntityIds.size;
           for (int count = 0; count < size; count++) {
                String entityId = ownedEntityIds.get(count);
                Entity ownedEntity = world.getEntity(entityId);
                OwnedComponent ownedEntityOwnedComponent = ownedComponentComponentMapperr.get(ownedEntity);
                //entity is not attached and needs to be don't process
               if ( ownedEntityOwnedComponent.isPhysicallyAttached() == false && attached == true) {
                    continue;
                }
                entities.add(ownedEntity);
                entityIds.add(entityId);
                OwnerComponent ownedEntityOwnerComponent = ownerComponentMapper.get(entity);
                // no entities or  owned entities to process
                if (ownedEntityOwnerComponent == null || ownedEntityOwnerComponent.getOwnedEntityIDs().size <= 0) {
                   continue;
                }
               getAllConnectedEntitiesIdsInternal(ownedEntity, world, attached);
           }
       }
         return entityIds;
    }

    /** checks to see if two entities are connected using recursion
     *
     * @param entity
     * @param entityToCheck
     * @param world
     * @return
     */
  public static  boolean isEntityConnected(Entity entity, Entity entityToCheck,  World world, boolean attached){
        ComponentMapper<OwnerComponent> ownerComponentMapper=world.getGameComponentMapper().getOwnerComponentComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentComponentMapperr=world.getGameComponentMapper().getOwnedComponentComponentMapper();
      OwnerComponent entityOwnerComponent =ownerComponentMapper.get(entity);
        OwnedComponent ownedComponent=ownedComponentComponentMapperr.get(entity);
        if(entity==entityToCheck) {
        return true;
        }
         if(ownedComponent==null ){
                return false;
           }
         String ownerId=ownedComponent.getOwnerEntityID();
         if(ownerId==null || ownerId.isEmpty()){
             return false;
         }
         if(attached && !ownedComponent.isPhysicallyAttached()){
             return  false;
         }

       // get  all owned entities
        if(entityOwnerComponent!=null) {

            return InList.isInList(   entities=getAllOwnedEntities(world.getEntity(ownerId), world), entity);


       }
        return false;
   }
public static  Array<Entity> getAllConnectedEntities(Entity entity, World world, boolean attached ){
        entities.clear();
        entityIds.clear();
        ComponentMapper<EntityId> idComponentMapper=world.getGameComponentMapper().getIdComponentMapper();
       entities.add(entity);
        entityIds.add(idComponentMapper.get(entity).getId());
       getAllConnectedEntitiesIdsInternal(entity, world, attached);
      return  entities;
   }
  public static  void  setActionToAllConnectedEntities(Entity entity, World world, String action  ){
        entities.clear();
        entityIds.clear();
       ComponentMapper<EntityId> idComponentMapper=world.getGameComponentMapper().getIdComponentMapper();
       entities.add(entity);
        entityIds.add(idComponentMapper.get(entity).getId());
        ActionComponent actionComponent=world.getGameComponentMapper().getActionComponentMapper().get(entity);
        actionComponent.setStat(action);
       setActionToAllConnectedEntitiesInternal(entity, world, action);
        return;
   }
   private static  void setActionToAllConnectedEntitiesInternal(Entity entity, World world, String action){
        ComponentMapper<OwnerComponent> ownerComponentMapper=world.getGameComponentMapper().getOwnerComponentComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentComponentMapperr=world.getGameComponentMapper().getOwnedComponentComponentMapper();
      OwnerComponent entityOwnerComponent =ownerComponentMapper.get(entity);
        OwnedComponent ownedComponent=ownedComponentComponentMapperr.get(entity);
        ActionComponent actionComponent=world.getGameComponentMapper().getActionComponentMapper().get(entity);
        actionComponent.setStat(action);
        if(entityOwnerComponent==null && ownedComponent==null ){
            return;
        }
      // get owner entity if  entity has one
        if(ownedComponent!=null) {
            String ownerId = ownedComponent.getOwnerEntityID();
            boolean attachedToOwner = ownedComponent.isPhysicallyAttached();
            if ( attachedToOwner == true) {
                if (ownerId != null && !ownerId.isEmpty() && attachedToOwner == true) {
                    Entity owner = world.getEntity(ownerId);
                   if(!InList.isInList(entities, owner)) {
                        entities.add(owner);
                        entityIds.add(ownerId);
                        setActionToAllConnectedEntitiesInternal( owner, world, action);
                    }
               }
           }
       }
       // get  all owned entities
        if(entityOwnerComponent!=null) {
            Array<String> ownedEntityIds = entityOwnerComponent.getOwnedEntityIDs();
            int size = ownedEntityIds.size;
           for (int count = 0; count < size; count++) {
                String entityId = ownedEntityIds.get(count);
                Entity ownedEntity = world.getEntity(entityId);
                OwnedComponent ownedEntityOwnedComponent = ownedComponentComponentMapperr.get(ownedEntity);
                //entity is not attached and needs to be don't process
               if ( ownedEntityOwnedComponent.isPhysicallyAttached() == false) {
                    continue;
                }
                entities.add(ownedEntity);
                entityIds.add(entityId);
                OwnerComponent ownedEntityOwnerComponent = ownerComponentMapper.get(entity);
                // no entities or  owned entities to process
                if (ownedEntityOwnerComponent == null || ownedEntityOwnerComponent.getOwnedEntityIDs().size <= 0) {
                   continue;
                }
               setActionToAllConnectedEntitiesInternal(ownedEntity, world, action);
           }
       }
        return;
   }
    public static  void  addComponentsAndStatsToAllConnectedEntities(Entity entity, World world, Array<NumericStat> numericStats, Array<BooleanStat> booleanStats, Array<StringStat> stringStats, Array<NumericStatChangeable> changableNumericStats, Array<BooleanStatChangeable> changableBooleanStats, Array<StringStatChangeable> changableStringStats, Component... components){
        entities.clear();
        entityIds.clear();
        ComponentMapper<EntityId> idComponentMapper=world.getGameComponentMapper().getIdComponentMapper();
    
    entities.add(entity);
        entityIds.add(idComponentMapper.get(entity).getId());
        addComponentsToEntity(entity, components);
        addStatsToEntity(entity, world, numericStats, booleanStats, stringStats, changableNumericStats, changableBooleanStats, changableStringStats);
     addComponentsAndStatsToAllConnectedEntitiesInternal(entity, world,numericStats, booleanStats,  stringStats, changableNumericStats, changableBooleanStats, changableStringStats,  components);
        return;
   }
   private static  void addComponentsAndStatsToAllConnectedEntitiesInternal(Entity entity, World world, Array<NumericStat> numericStats, Array<BooleanStat> booleanStats, Array<StringStat> stringStats, Array<NumericStatChangeable> changableNumericStats, Array<BooleanStatChangeable> changableBooleanStats, Array<StringStatChangeable> changableStringStats, Component... components){
        ComponentMapper<OwnerComponent> ownerComponentMapper=world.getGameComponentMapper().getOwnerComponentComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentComponentMapperr=world.getGameComponentMapper().getOwnedComponentComponentMapper();
      OwnerComponent entityOwnerComponent =ownerComponentMapper.get(entity);
        OwnedComponent ownedComponent=ownedComponentComponentMapperr.get(entity);
        addComponentsToEntity(entity, components);
        addStatsToEntity(entity, world, numericStats, booleanStats, stringStats, changableNumericStats, changableBooleanStats, changableStringStats);
       if(entityOwnerComponent==null && ownedComponent==null ){
            return;
        }
      // get owner entity if  entity has one
        if(ownedComponent!=null) {
            String ownerId = ownedComponent.getOwnerEntityID();
            boolean attachedToOwner = ownedComponent.isPhysicallyAttached();
            if ( attachedToOwner == true) {
                if (ownerId != null && !ownerId.isEmpty() && attachedToOwner == true) {
                    Entity owner = world.getEntity(ownerId);
                   if(!InList.isInList(entities, owner)) {
                        entities.add(owner);
                        entityIds.add(ownerId);
                        addComponentsAndStatsToAllConnectedEntities( owner, world, numericStats, booleanStats, stringStats, changableNumericStats, changableBooleanStats, changableStringStats, components);
                    }
               }
           }
       }
       // get  all owned entities
        if(entityOwnerComponent!=null) {
            Array<String> ownedEntityIds = entityOwnerComponent.getOwnedEntityIDs();
            int size = ownedEntityIds.size;
           for (int count = 0; count < size; count++) {
                String entityId = ownedEntityIds.get(count);
                Entity ownedEntity = world.getEntity(entityId);
                OwnedComponent ownedEntityOwnedComponent = ownedComponentComponentMapperr.get(ownedEntity);
                //entity is not attached and needs to be don't process
               if ( ownedEntityOwnedComponent.isPhysicallyAttached() == false) {
                    continue;
                }
                entities.add(ownedEntity);
                entityIds.add(entityId);
                OwnerComponent ownedEntityOwnerComponent = ownerComponentMapper.get(entity);
                // no entities or  owned entities to process
                if (ownedEntityOwnerComponent == null || ownedEntityOwnerComponent.getOwnedEntityIDs().size <= 0) {
                   continue;
                }
               addComponentsAndStatsToAllConnectedEntities( ownedEntity, world, numericStats, booleanStats, stringStats, changableNumericStats, changableBooleanStats, changableStringStats, components);
           }
       }
        return;
   }


    /**
     *    a  function that recursively  gets the ids of all entities and sub owned entities  owned by a entity
      */

    private static Array<String> getAllOwnedEntitiesIDsInternal(Entity entity, World world){
        ComponentMapper<OwnerComponent> ownerComponentMapper=world.getGameComponentMapper().getOwnerComponentComponentMapper();
        OwnerComponent entityOwnerComponent =ownerComponentMapper.get(entity);
        if(entityOwnerComponent==null || entityOwnerComponent.getOwnedEntityIDs().isEmpty()){
           return  entityIds;
        }
        Array<String> ownedEntityIds=entityOwnerComponent.getOwnedEntityIDs();
        int size=ownedEntityIds.size;
        for(int count=0; count<size; count++){
            String entityId=ownedEntityIds.get(count);
            Entity ownedEntity=world.getEntity(entityId);
            entityIds.add(entityId);
            entities.add(world.getEntity(entityId));
            OwnerComponent ownedEntityOwnerComponent=ownerComponentMapper.get(entity);
            if(ownedEntityOwnerComponent==null || ownedEntityOwnerComponent.getOwnedEntityIDs().isEmpty()){
               continue;
            }
           getAllOwnedEntitiesIDsInternal(ownedEntity, world);
       }
       return entityIds;
   }
 public static  Array<Entity> getOwnedEntitiesWithComponents(World world, Entity entity , Class<? extends Component>... components ){
        GameComponentMapper gameComponentMapper=world.getGameComponentMapper();
        Array<Entity>  entities=getAllOwnedEntities(entity, world);
        return  gameComponentMapper.getEntitiesWithComponents(entities, components);
   }
   public static  Array<Entity> getLinkedEntitiesWithComponents(World world, Entity entity , boolean attached,  Class<? extends Component>... components ){
        GameComponentMapper gameComponentMapper=world.getGameComponentMapper();
        Array<Entity>  entities=getAllConnectedEntities(entity, world, attached);
        return  gameComponentMapper.getEntitiesWithComponents(entities, components);
   }
   public static  Array<Entity> getConnectedEntitiesWithStatsAndComponents(World world, Entity entity , boolean attached,  Array<String> numericStats, Array<String>  booleanStats, Array<String> stringStats,  Class<? extends Component>... components ){
        GameComponentMapper gameComponentMapper=world.getGameComponentMapper();
        Array<Entity>  entities=getAllConnectedEntities(entity, world, attached);
        return  gameComponentMapper.getEntitiesContainingStats(entities, numericStats, booleanStats, stringStats,  components);
   }
 public static   <T extends Component>  Array<T> getOwnedEntitiesComponent(World world, Entity entity , Class< T> components){
        GameComponentMapper gameComponentMapper=world.getGameComponentMapper();
        Array<Entity>  entities=getAllOwnedEntities(entity, world);
        return  gameComponentMapper.getComponentsFromEntitiesWithComponents(entities, components);
   }
   public static    int getEntityActionTime(World world, Entity entity , String action){
        GameComponentMapper gameComponentMapper=world.getGameComponentMapper();
        AnimatableComponent animatable=gameComponentMapper.getAnimatableComponentMapper().get(entity);
        int time=1;
        if(animatable!=null){
            PositionComponent position=gameComponentMapper.getPositionComponentMapper().get(entity);
            Direction direction=position.getDirection();
           Animation animation=animatable.getAnamation(direction, action);
            if(animation!=null){
                time=animation.getFrameRate()*animation.getFrames().length;
           }
       }
       return  time;
   }
 public static  Array<Entity> getOwnedEntitiesWithStatsAndComponents(World world, Entity entity , Array<String> numericStats, Array<String>  booleanStats, Array<String> stringStats,  Class<? extends Component>... components ){
        GameComponentMapper gameComponentMapper=world.getGameComponentMapper();
        Array<Entity>  entities=getAllOwnedEntities(entity, world);
        return  gameComponentMapper.getEntitiesContainingStats(entities, numericStats, booleanStats, stringStats,  components);
   }

    /**
     *     returns a vector 2 object of the total combined mass,   and volume  of the entity plus all attached entities 0=mass  1=volume
     */
    public static double [] getEntityMassAndVolume(World world, Entity entity){
        OwnerComponent  ownerComponent=GameComponentMapper.getOwnerComponentComponentMapper().get(entity);
        ComponentMapper<PhysicalObjectComponent> physicalObjectComponentMapper=GameComponentMapper.getPhysicalObjectComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
       PhysicalObjectComponent physicalObject=physicalObjectComponentMapper.get(entity);
        if(physicalObject==null ) {
        // no physical  object or position  = no mass or volume return empty array
        return  new double[2];
        }
            float mass = physicalObject.getMass();
            float volume = physicalObject.getVolume();
           double[]  massAndVolume = new double[] {mass,  volume};

            if (ownerComponent != null) {
               Array<Entity> allOwnedEntities=getAllOwnedEntities(entity, world);
               for(Entity attachedEntity:allOwnedEntities){

                   PhysicalObjectComponent physicalObjectOfAttachedEntity=physicalObjectComponentMapper.get(attachedEntity);
                   if(physicalObjectOfAttachedEntity==null){
                       continue;
                   }
                   OwnedComponent ownedComponent=ownedComponentMapper.get(attachedEntity);
                   if(ownedComponent!=null && ownedComponent.isPhysicallyAttached()) {
                       massAndVolume[0] = massAndVolume[0] + physicalObjectOfAttachedEntity.getMass();
                       massAndVolume[1] = massAndVolume[1] + physicalObjectOfAttachedEntity.getVolume();
                   }


               }
           }
            return new double[]{massAndVolume[0],  massAndVolume[1]};

   }
  private static   double [] getTotalMassAndVolumeInternal(double []  massAndVolume, ComponentMapper<PhysicalObjectComponent> physicalObjectComponentMapper, Entity entity){
       PhysicalObjectComponent physicalObject= physicalObjectComponentMapper.get(entity);
        if(physicalObject!=null){
            massAndVolume[0]=massAndVolume[0]+physicalObject.getMass();
            massAndVolume[1]=massAndVolume[1]+physicalObject.getVolume();
        }
      return  massAndVolume;
    }
  public static  double [] getMassVolumeWeight( World world, Entity entity) {
       ComponentMapper<PhysicalObjectComponent> physicalObjectComponentMapper = world.getGameComponentMapper().getPhysicalObjectComponentMapper();
       PhysicalObjectComponent physicalObject = physicalObjectComponentMapper.get(entity);
        ComponentMapper<PositionComponent> positionComponentMapper = world.getGameComponentMapper().getPositionComponentMapper();
        PositionComponent position = positionComponentMapper.get(entity);
       double massNumber = physicalObject.getMass();
        double gravity = world.getMap(position.getMapId()).getGravity();
        double weightNumber = massNumber * gravity;
        double volumeNumber = physicalObject.getVolume();
       return new double[]{massNumber, weightNumber, volumeNumber};
  }

    /**
     * deep clones an entity and all of its components
     * @param assets
     * @param entity the Entity to Copy
     * @return the deep Copied Entity
     */
   public static  Entity copyEntity(GameAssets assets, Entity entity){
       return new CopyObject(assets).copyObject(entity, Entity.class);
   }

    /**
     * factory method that creates a new entity with  all  the
     * base components and stats
     * @return a Basic Entity
     */
   public static BasicEntityContainer makeBasicEntity() {
        Entity entity = new Entity();
       EntityId entityId = new EntityId();
        entity.add(entityId);
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
        entity.add(groupsComponent);
        OwnerComponent ownerComponent= new OwnerComponent();
        entity.add(ownerComponent);
        ImageComponent imageComponent= new ImageComponent();
        entity.add(imageComponent);
     return new BasicEntityContainer(entity, entityId, nameComponent, groupsComponent,  numericStats, booleanStats, stringStats, stringStatsChangeable, numericStatsSelfChangable, booleanStatsChangeable, numericStatsChangeable);
   }
  public static Entity combineEntities(World world, Entity entity1, Entity entity2) {
       Entity entity=new Entity();
       entity1.add(new RemoveFromEngine());
        entity2.add(new RemoveFromEngine());
      return entity;
    }

    public  static  boolean attachEntity(Entity entityToAttachTo,   Entity entityToAttach) {

     return  attachEntity(  entityToAttachTo,  entityToAttach, true);

        }


        /**
         * links one entity to  another
         * @param entityToAttachTo
         * @param entityToAttach
         * @return
         */
   public  static  boolean attachEntity( Entity entityToAttachTo,   Entity entityToAttach, boolean  physicallyAttached){
       ComponentMapper<EntityId> idComponentMapper=GameComponentMapper.getIdComponentMapper();
       ComponentMapper<OwnerComponent> ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
       ComponentMapper<OwnedComponent> ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
       OwnerComponent ownerComponent=ownerComponentComponentMapper.get(entityToAttachTo);
       if(ownerComponent==null){
           ownerComponent=new OwnerComponent();
        entityToAttachTo.add(ownerComponent);
       }
      EntityId entityToAttachID=idComponentMapper.get(entityToAttach);
        EntityId entityToAttachToID=idComponentMapper.get(entityToAttachTo);
       if(entityToAttachID==null){
            return false;
        }
      boolean  attached=ownerComponent.addEntity(entityToAttachID.getId());
        OwnedComponent ownedComponent=ownedComponentComponentMapper.get(entityToAttach);
        if(ownedComponent==null){
            ownedComponent= new OwnedComponent();
            entityToAttach.add(ownedComponent);
        }
        ownedComponentComponentMapper.get(entityToAttach);
        ownedComponent.setPhysicallyAttached(physicallyAttached);
        ownedComponent.setOwnerEntityID(entityToAttachToID.getId());
      return  attached;
   }
  public  static  boolean holdItem(World world, Entity holder,   Entity itemToHold){
        GameComponentMapper gameComponentMapper=world.getGameComponentMapper();
        ComponentMapper<EntityId> idComponentMapper=gameComponentMapper.getIdComponentMapper();
        ComponentMapper<OwnerComponent> ownerComponentComponentMapper=gameComponentMapper.getOwnerComponentComponentMapper();
        ComponentMapper<Holder> holderComponentMapper=gameComponentMapper.getHolderComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentComponentMapper=gameComponentMapper.getOwnedComponentComponentMapper();
        ComponentMapper<ItemComponent> itemComponentMapper=gameComponentMapper.getItemComponentMapper();
       Holder holderComponent =holderComponentMapper.get(holder);
        OwnerComponent ownerComponent=ownerComponentComponentMapper.get(holder);
        if(holderComponent ==null){
            return false;
        }
       EntityId itemToHoldID=idComponentMapper.get(itemToHold);
        EntityId holderID=idComponentMapper.get(holder);
        ownerComponent.addEntity(itemToHoldID.getId());
        if(itemToHoldID==null){
            return false;
        }
        String heldItemID=holderComponent.getItemToHoldId();
        // currently holding somthieng cant hold  somthing new
        if(heldItemID!=null){
           return false;
       }
        else{
            holderComponent.setItemToHoldId(itemToHoldID.getId());
        }
        OwnedComponent ownedComponent=ownedComponentComponentMapper.get(itemToHold);
        if(ownedComponent==null){
            ownedComponent= new OwnedComponent();
            itemToHold.add(ownedComponent);
        }
        ownedComponent.setPhysicallyAttached(true);
        ownedComponent.setOwnerEntityID(holderID.getId());
        ownedComponent.setSetEntityActionToOwner(true);
        ItemComponent item=itemComponentMapper.get(itemToHold);
        item.setHeld(true);
      return  true;
   }


public  static  void detachEntity( Entity entityToDetachFrom,   Entity entityToDetach){
        ComponentMapper<EntityId> idComponentMapper=GameComponentMapper.getIdComponentMapper();
        ComponentMapper<OwnerComponent> ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        OwnerComponent ownerComponent=ownerComponentComponentMapper.get(entityToDetachFrom);
      EntityId entityTodetachID=idComponentMapper.get(entityToDetach);
            ownerComponent.getOwnedEntityIDs().removeValue(entityTodetachID.getId(), false);
            ownedComponentComponentMapper.get(entityToDetach);
                entityToDetach.remove(OwnedComponent.class);

                      }

    public  static  PositionComponent  getEntityPosition(World world,  Entity entity){
        ComponentMapper<EntityId> idComponentMapper=GameComponentMapper.getIdComponentMapper();
        ComponentMapper<PositionComponent> positionComponentComponentMapper=GameComponentMapper.getPositionComponentMapper();
        PositionComponent positionComponent=positionComponentComponentMapper.get(entity);
        ComponentMapper<OwnedComponent> ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        OwnedComponent ownedComponent=ownedComponentComponentMapper.get(entity);
        if(ownedComponent==null  || !ownedComponent.isSetEntityPositionToOwner()){
            return positionComponent;
        }
        Entity ownerEntity=world.getEntity(ownedComponent.getOwnerEntityID())   ;
        return positionComponentComponentMapper.get(ownerEntity);
    }
   public static Array<Entity> getEntities() {
        return entities;
    }
   public static Array<String> getEntityIds() {
        return entityIds;
    }
   public static boolean isConnected() {
        return connected;
    }
  public static  void addComponentsToEntity(Entity entity, Component... components){
      int size=components.length;
       for(int count=0; count<size; count++){
          entity.add(components[count]);
       }
  }
    public static  void addStatsToEntity(Entity entity, World world, Array<NumericStat> numericStats, Array<BooleanStat> booleanStats, Array<StringStat> stringStats, Array<NumericStatChangeable> numericStatsChangables, Array<BooleanStatChangeable> booleanStatsChangables, Array<StringStatChangeable> stringStatsChangables){
       ComponentMapper<NumericStats> numericStatsComponentMapper=world.getGameComponentMapper().getNumericStatsComponentMapper();
        ComponentMapper<BooleanStats> booleanStatsComponentMapper=world.getGameComponentMapper().getBooleanStatsComponentMapper();
        ComponentMapper<StringStats> stringStatsComponentMapper=world.getGameComponentMapper().getStringStatsComponentMapper();
        NumericStats numericStatsComponent=numericStatsComponentMapper.get(entity);
        BooleanStats booleanStatsComponent=booleanStatsComponentMapper.get(entity);
        StringStats stringStatsComponent=stringStatsComponentMapper.get(entity);
        ComponentMapper<NumericStatsChangeable> numericStatsChangableComponentMapper=world.getGameComponentMapper().getNumericStatsChangableComponentMapper();
        ComponentMapper<BooleanStatsChangeable> booleanStatsChangableComponentMapper=world.getGameComponentMapper().getBooleanStatsChangableComponentMapper();
        ComponentMapper<StringStatsChangeable> stringStatsChangableComponentMapper=world.getGameComponentMapper().getStringStatsChangableComponentMapper();
        NumericStatsChangeable numericStatsChangeableComponent =numericStatsChangableComponentMapper.get(entity);
        BooleanStatsChangeable booleanStatsChangeableComponent =booleanStatsChangableComponentMapper.get(entity);
        StringStatsChangeable stringStatsChangeableComponent =stringStatsChangableComponentMapper.get(entity);
        
        if(numericStats!=null && !numericStats.isEmpty()){
            
            int size=numericStats.size;
            for ( int count=0; count<size; count++){
                
                numericStatsComponent.addOrCombineStat(numericStats.get(count));
            }
        }
       if(booleanStats!=null && !booleanStats.isEmpty()){
           int size=booleanStats.size;
            for ( int count=0; count<size; count++){
               booleanStatsComponent.addOrCombineStat(booleanStats.get(count));
            }
        }
       if(stringStats!=null && !stringStats.isEmpty()){
           int size=stringStats.size;
            for ( int count=0; count<size; count++){
               stringStatsComponent.addOrCombineStat(stringStats.get(count));
            }
        }
       if(numericStatsChangables!=null && !numericStatsChangables.isEmpty()){
           int size=numericStatsChangables.size;
            for ( int count=0; count<size; count++){
               numericStatsChangeableComponent.addOrCombineStat(numericStatsChangables.get(count));
            }
        }
        if(booleanStatsChangables!=null && !booleanStatsChangables.isEmpty()){
           int size=booleanStatsChangables.size;
            for ( int count=0; count<size; count++){
               booleanStatsChangeableComponent.addOrCombineStat(booleanStatsChangables.get(count));
            }
        }
        if(stringStatsChangables!=null && !stringStatsChangables.isEmpty()) {
            int size = stringStatsChangables.size;
            for (int count = 0; count < size; count++) {
                stringStatsChangeableComponent.addOrCombineStat(stringStatsChangables.get(count));
            }
        }
}

public static Entity  createEntity(boolean addStats, boolean addChangeableStats){
    Entity gameEntity= new Entity();
    gameEntity.add(new EntityId());

    if(addStats){
        gameEntity.add(new NumericStats());
        gameEntity.add(new BooleanStats());
        gameEntity.add(new StringStats());
    }
    if(addChangeableStats){
        gameEntity.add(new BooleanStatsChangeable());
        gameEntity.add(new NumericStatsChangeable());
        gameEntity.add(new StringStatsChangeable());
    }
    return gameEntity;

}


}
