package com.jessematty.black.tower.GameBaseClasses.Utilities;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.AttachEntity.AttachedComponent;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.ImageComponent;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.RemoveFromEngine;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableBooleanStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableNumericStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.ChangableStringStat;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangable;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.Generators.Entity.EntityContainers.BasicEntityContainer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.Copy.CopyObject;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.World;

public class EntityUtilities {
  
   private static Array<Entity> entities= new Array<>();
   private static Array<String> entityIds= new Array<>();
   private static  boolean connected;
 private EntityUtilities() {
    }
    // get the an  array of all entities and sub owned entities  owned by a entity using recursion
   public static  Array<Entity> getAllOwnedEntities(Entity entity, World world){
       getAllOwnedEntitiesIDs(entity, world);
        return entities;
   }
  public static  Array<String> getAllConnectedEntitiesIds(Entity entity, World world, boolean attached) {
        entities.clear();
        entityIds.clear();
       ComponentMapper<ID> idComponentMapper=world.getGameComponentMapper().getIdComponentMapper();
        entities.add(entity);
        entityIds.add(idComponentMapper.get(entity).getId());
      return getAllConnectedEntitiesIdsInternal(entity, world, attached);
   }
    
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
            boolean attachedToOwner = ownedComponent.isAttached();
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
               if ( ownedEntityOwnedComponent.isAttached() == false && attached == true) {
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
   public static  boolean isEntityConnected(Entity entity, Entity entityToCheck,  World world) {
      connected=false;
        entities.clear();
        entityIds.clear();
           // entity equals entity to check return true
       if(entity==entityToCheck){
           return  true;
      }
      return  isEntityConnectedInternal(entity, entityToCheck, world);
  }
// checks to see if two entites are connected using recursion
   private static  boolean isEntityConnectedInternal(Entity entity, Entity entityToCheck,  World world){
        ComponentMapper<OwnerComponent> ownerComponentMapper=world.getGameComponentMapper().getOwnerComponentComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentComponentMapperr=world.getGameComponentMapper().getOwnedComponentComponentMapper();
      OwnerComponent entityOwnerComponent =ownerComponentMapper.get(entity);
        OwnedComponent ownedComponent=ownedComponentComponentMapperr.get(entity);
        if(entity==entityToCheck){
         if(ownedComponent==null || ownedComponent.isAttached()==false){
                return connected;
           }
            connected=true;
             return connected;
    }
       if(entityOwnerComponent==null && ownedComponent==null ){
            return  connected;
        }
      // get owner entity if  entity has one
        if(ownedComponent!=null) {
            String ownerId = ownedComponent.getOwnerEntityID();
            boolean attachedToOwner = ownedComponent.isAttached();
            if ( attachedToOwner == true) {
                if (ownerId != null && !ownerId.isEmpty() && attachedToOwner == true) {
                    Entity owner = world.getEntity(ownerId);
                   if(!InList.isInList(entities, owner)) {
                        entities.add(owner);
                        entityIds.add(ownerId);
                        isEntityConnectedInternal(owner,  entityToCheck, world);
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
               if ( ownedEntityOwnedComponent.isAttached() == false ) {
                    continue;
                }
                entities.add(ownedEntity);
                entityIds.add(entityId);
                OwnerComponent ownedEntityOwnerComponent = ownerComponentMapper.get(entity);
                // no entities or  owned entities to process
                if (ownedEntityOwnerComponent == null || ownedEntityOwnerComponent.getOwnedEntityIDs().size <= 0) {
                   continue;
                }
               isEntityConnectedInternal(ownedEntity,  entityToCheck, world);
           }
       }
        return connected;
   }
public static  Array<Entity> getAllConnectedEntities(Entity entity, World world, boolean attached ){
        entities.clear();
        entityIds.clear();
        ComponentMapper<ID> idComponentMapper=world.getGameComponentMapper().getIdComponentMapper();
       entities.add(entity);
        entityIds.add(idComponentMapper.get(entity).getId());
       getAllConnectedEntitiesIdsInternal(entity, world, attached);
      return  entities;
   }
  public static  void  setActionToAllConnectedEntities(Entity entity, World world, String action  ){
        entities.clear();
        entityIds.clear();
       ComponentMapper<ID> idComponentMapper=world.getGameComponentMapper().getIdComponentMapper();
       entities.add(entity);
        entityIds.add(idComponentMapper.get(entity).getId());
        Action actionComponent=world.getGameComponentMapper().getActionComponentMapper().get(entity);
        actionComponent.setStat(action);
       setActionToAllConnectedEntitiesInternal(entity, world, action);
        return;
   }
   private static  void setActionToAllConnectedEntitiesInternal(Entity entity, World world, String action){
        ComponentMapper<OwnerComponent> ownerComponentMapper=world.getGameComponentMapper().getOwnerComponentComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentComponentMapperr=world.getGameComponentMapper().getOwnedComponentComponentMapper();
      OwnerComponent entityOwnerComponent =ownerComponentMapper.get(entity);
        OwnedComponent ownedComponent=ownedComponentComponentMapperr.get(entity);
        Action actionComponent=world.getGameComponentMapper().getActionComponentMapper().get(entity);
        actionComponent.setStat(action);
        if(entityOwnerComponent==null && ownedComponent==null ){
            return;
        }
      // get owner entity if  entity has one
        if(ownedComponent!=null) {
            String ownerId = ownedComponent.getOwnerEntityID();
            boolean attachedToOwner = ownedComponent.isAttached();
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
               if ( ownedEntityOwnedComponent.isAttached() == false) {
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
    public static  void  addComponentsAndStatsToAllConnectedEntities(Entity entity, World world, Array<NumericStat> numericStats, Array<BooleanStat> booleanStats, Array<StringStat> stringStats, Array<ChangableNumericStat> changableNumericStats, Array<ChangableBooleanStat> changableBooleanStats, Array<ChangableStringStat> changableStringStats,  Component... components){
        entities.clear();
        entityIds.clear();
        ComponentMapper<ID> idComponentMapper=world.getGameComponentMapper().getIdComponentMapper();
    
    entities.add(entity);
        entityIds.add(idComponentMapper.get(entity).getId());
        addComponentsToEntity(entity, components);
        addStatsToEntity(entity, world, numericStats, booleanStats, stringStats, changableNumericStats, changableBooleanStats, changableStringStats);
     addComponentsAndStatsToAllConnectedEntitiesInternal(entity, world,numericStats, booleanStats,  stringStats, changableNumericStats, changableBooleanStats, changableStringStats,  components);
        return;
   }
   private static  void addComponentsAndStatsToAllConnectedEntitiesInternal(Entity entity, World world, Array<NumericStat> numericStats, Array<BooleanStat> booleanStats, Array<StringStat> stringStats, Array<ChangableNumericStat> changableNumericStats, Array<ChangableBooleanStat> changableBooleanStats, Array<ChangableStringStat> changableStringStats,  Component... components){
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
            boolean attachedToOwner = ownedComponent.isAttached();
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
               if ( ownedEntityOwnedComponent.isAttached() == false) {
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





// get the ids of all entities and sub owned entities  owned by a entity
    public static Array<String> getAllOwnedEntitiesIDs(Entity entity, World world){
        ComponentMapper<OwnerComponent> ownerComponentMapper=world.getGameComponentMapper().getOwnerComponentComponentMapper();
        OwnerComponent entityOwnerComponent =ownerComponentMapper.get(entity);
        if(entityOwnerComponent==null){
           return  entityIds;
        }
        Array<String> ownedEntityIds=entityOwnerComponent.getOwnedEntityIDs();
        int size=entities.size;
        for(int count=0; count<size; count++){
            String entityId=ownedEntityIds.get(count);
            Entity ownedEntity=world.getEntity(entityId);
            entityIds.add(entityId);
            OwnerComponent ownedEntityOwnerComponent=ownerComponentMapper.get(entity);
            if(ownedEntityOwnerComponent==null || ownedEntityOwnerComponent.getOwnedEntityIDs().size<=0){
               continue;
            }
           getAllOwnedEntities(ownedEntity, world);
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
   //returns a vector 2 object of the total combined mass, weight  and volume  of the entity plus all attached entities 0=mass 1=weight 2=volume
    public static double [] getTotalMassWeightVolume(World world, Entity entity){
        GameComponentMapper gameComponentMapper=world.getGameComponentMapper();
        OwnerComponent  ownerComponent=gameComponentMapper.getOwnerComponentComponentMapper().get(entity);
        ComponentMapper<PhysicalObjectComponent> physicalObjectComponentMapper=gameComponentMapper.getPhysicalObjectComponentMapper();
        ComponentMapper<PositionComponent> positionComponentMapper=gameComponentMapper.getPositionComponentMapper();
       PhysicalObjectComponent physicalObject=physicalObjectComponentMapper.get(entity);
        PositionComponent position=positionComponentMapper.get(entity);
        if(physicalObject!=null && position!=null) {
            float mass = physicalObject.getMass();
            float volume = physicalObject.getVolume();
           double[]  massAndVolume = new double[] {mass,  volume};
            if (ownerComponent != null) {
               Array<Entity> ownedEntitiesIds=getAllOwnedEntities(entity, world);
                massAndVolume=getTotalWeightAndVolume(massAndVolume, physicalObjectComponentMapper, entity);
           }
            float weight= (float) (world.getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY()).getGravity()*massAndVolume[0]);
            return new double[]{massAndVolume[0], weight, massAndVolume[1]};
     }
       // no physical  object or position  = no mass or volume return empty vector 2
        return  new double[3];
   }
  private static   double [] getTotalWeightAndVolume(double []  massAndVolume, ComponentMapper<PhysicalObjectComponent> physicalObjectComponentMapper, Entity entity){
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
        double gravity = world.getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY()).getGravity();
        double weightNumber = massNumber * gravity;
        double volumeNumber = physicalObject.getVolume();
       return new double[]{massNumber, weightNumber, volumeNumber};
  }
   public static  Entity copyEntity(GameAssets assets, Entity entity){
       return new CopyObject(assets).copyObject(entity, Entity.class);
   }
   public static BasicEntityContainer makeBasicEntity() {
        Entity entity = new Entity();
       ID id = new ID();
        entity.add(id);
        Name name = new Name();
        entity.add(name);
        Action action = new Action();
        entity.add(action);
        NumericStats numericStats = new NumericStats();
        entity.add(numericStats);
        BooleanStats booleanStats = new BooleanStats();
        entity.add(booleanStats);
        StringStats stringStats = new StringStats();
        entity.add(stringStats);
        NumericStatsChangable numericStatsChangable = new NumericStatsChangable();
        entity.add(numericStatsChangable);
        BooleanStatsChangable booleanStatsChangable = new BooleanStatsChangable();
        entity.add(booleanStatsChangable);
        StringStatsChangable stringStatsChangable = new StringStatsChangable();
        entity.add(stringStatsChangable);
        NumericStatsSelfChangable numericStatsSelfChangable = new NumericStatsSelfChangable();
        entity.add(numericStatsSelfChangable);
        Groups groups = new Groups();
        groups.getGroups().add("entity");
        entity.add(groups);
        OwnerComponent ownerComponent= new OwnerComponent();
        entity.add(ownerComponent);
        ImageComponent imageComponent= new ImageComponent();
        entity.add(imageComponent);
     return new BasicEntityContainer(entity, id, name, groups,  numericStats, booleanStats, stringStats, stringStatsChangable, numericStatsSelfChangable, booleanStatsChangable, numericStatsChangable);
   }
  public static Entity combineEntities(World world, Entity entity1, Entity entity2) {
       Entity entity=new Entity();
       entity1.add(new RemoveFromEngine());
        entity2.add(new RemoveFromEngine());
      return entity;
    }
   public  static  boolean attachEntity(World world, Entity entityToAttachTo,   Entity entityToAttach){
       GameComponentMapper gameComponentMapper=world.getGameComponentMapper();
       ComponentMapper<ID> idComponentMapper=gameComponentMapper.getIdComponentMapper();
       ComponentMapper<OwnerComponent> ownerComponentComponentMapper=gameComponentMapper.getOwnerComponentComponentMapper();
       ComponentMapper<AttachedComponent> attachedComponentComponentMapper=gameComponentMapper.getAttachedComponentComponentMapper();
       ComponentMapper<OwnedComponent> ownedComponentComponentMapper=gameComponentMapper.getOwnedComponentComponentMapper();
       AttachedComponent attachedComponent=attachedComponentComponentMapper.get(entityToAttachTo);
       OwnerComponent ownerComponent=ownerComponentComponentMapper.get(entityToAttachTo);
       if(attachedComponent==null){
           return false;
       }
      ID entityToAttachID=idComponentMapper.get(entityToAttach);
        ID entityToAttachToID=idComponentMapper.get(entityToAttachTo);
       if(entityToAttachID==null){
            return false;
        }

    Name name=entityToAttach.getComponent(Name.class);
        ObjectMap<String , String> attachedEntityIds=attachedComponent.getAttachedEntities();
        String entityKey="entity"+attachedEntityIds.size;
      if(name!=null){
            entityKey=name.getStat();
       }
       boolean attached=attachedComponent.addEntity(entityKey, entityToAttachID.getId());
       attached=ownerComponent.addEntity(entityToAttachID.getId());
        OwnedComponent ownedComponent=ownedComponentComponentMapper.get(entityToAttachTo);
        ownedComponentComponentMapper.get(entityToAttach);
        ownedComponent.setAttached(true);
        ownedComponent.setOwnerEntityID(entityToAttachToID.getId());
      return  attached;
   }
  public  static  boolean holdItem(World world, Entity holder,   Entity itemToHold){
        GameComponentMapper gameComponentMapper=world.getGameComponentMapper();
        ComponentMapper<ID> idComponentMapper=gameComponentMapper.getIdComponentMapper();
        ComponentMapper<OwnerComponent> ownerComponentComponentMapper=gameComponentMapper.getOwnerComponentComponentMapper();
        ComponentMapper<Holder> holderComponentMapper=gameComponentMapper.getHolderComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentComponentMapper=gameComponentMapper.getOwnedComponentComponentMapper();
        ComponentMapper<Item> itemComponentMapper=gameComponentMapper.getItemComponentMapper();
       Holder holderComponent =holderComponentMapper.get(holder);
        OwnerComponent ownerComponent=ownerComponentComponentMapper.get(holder);
        if(holderComponent ==null){
            return false;
        }
       ID itemToHoldID=idComponentMapper.get(itemToHold);
        ID holderID=idComponentMapper.get(holder);
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
        ownedComponent.setAttached(true);
        ownedComponent.setOwnerEntityID(holderID.getId());
        ownedComponent.setSetEntityActionToOwner(true);
        Item item=itemComponentMapper.get(itemToHold);
        item.setHeld(true);
      return  true;
   }


public  static  void detachEntity(World world, Entity entityToDetachFrom,   Entity entityToDetach, boolean removeOwnership){
        GameComponentMapper gameComponentMapper=world.getGameComponentMapper();
        ComponentMapper<ID> idComponentMapper=gameComponentMapper.getIdComponentMapper();
        ComponentMapper<OwnerComponent> ownerComponentComponentMapper=gameComponentMapper.getOwnerComponentComponentMapper();
        ComponentMapper<AttachedComponent> attachedComponentComponentMapper=gameComponentMapper.getAttachedComponentComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentComponentMapper=gameComponentMapper.getOwnedComponentComponentMapper();
        AttachedComponent attachedComponent=attachedComponentComponentMapper.get(entityToDetachFrom);
        OwnerComponent ownerComponent=ownerComponentComponentMapper.get(entityToDetachFrom);
        OwnedComponent ownedComponent = ownedComponentComponentMapper.get(entityToDetachFrom);
       if(attachedComponent==null){
            return;
        }
      ID entityTodetachID=idComponentMapper.get(entityToDetach);
      Name name=entityToDetach.getComponent(Name.class);
        String entityName=name.getStat();
        ObjectMap<String , String> attachedEntityIds=attachedComponent.getAttachedEntities();
       attachedEntityIds.remove(entityName);
        if(removeOwnership==true) {
            ownerComponent.getOwnedEntityIDs().removeValue(entityTodetachID.getId(), false);
            ownedComponentComponentMapper.get(entityToDetach);
            ownedComponent.setAttached(false);
            ownedComponent.setOwnerEntityID(null);
        }
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
    public static  void addStatsToEntity(Entity entity, World world, Array<NumericStat> numericStats, Array<BooleanStat> booleanStats, Array<StringStat> stringStats, Array<ChangableNumericStat> numericStatsChangables, Array<ChangableBooleanStat> booleanStatsChangables, Array<ChangableStringStat> stringStatsChangables){
       ComponentMapper<NumericStats> numericStatsComponentMapper=world.getGameComponentMapper().getNumericStatsComponentMapper();
        ComponentMapper<BooleanStats> booleanStatsComponentMapper=world.getGameComponentMapper().getBooleanStatsComponentMapper();
        ComponentMapper<StringStats> stringStatsComponentMapper=world.getGameComponentMapper().getStringStatsComponentMapper();
        NumericStats numericStatsComponent=numericStatsComponentMapper.get(entity);
        BooleanStats booleanStatsComponent=booleanStatsComponentMapper.get(entity);
        StringStats stringStatsComponent=stringStatsComponentMapper.get(entity);
        ComponentMapper<NumericStatsChangable> numericStatsChangableComponentMapper=world.getGameComponentMapper().getNumericStatsChangableComponentMapper();
        ComponentMapper<BooleanStatsChangable> booleanStatsChangableComponentMapper=world.getGameComponentMapper().getBooleanStatsChangableComponentMapper();
        ComponentMapper<StringStatsChangable> stringStatsChangableComponentMapper=world.getGameComponentMapper().getStringStatsChangableComponentMapper();
        NumericStatsChangable numericStatsChangableComponent=numericStatsChangableComponentMapper.get(entity);
        BooleanStatsChangable booleanStatsChangableComponent=booleanStatsChangableComponentMapper.get(entity);
        StringStatsChangable stringStatsChangableComponent=stringStatsChangableComponentMapper.get(entity);
        
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
               numericStatsChangableComponent.addOrCombineStat(numericStatsChangables.get(count));
            }
        }
        if(booleanStatsChangables!=null && !booleanStatsChangables.isEmpty()){
           int size=booleanStatsChangables.size;
            for ( int count=0; count<size; count++){
               booleanStatsChangableComponent.addOrCombineStat(booleanStatsChangables.get(count));
            }
        }
        if(stringStatsChangables!=null && !stringStatsChangables.isEmpty()){
           int size=stringStatsChangables.size;
            for ( int count=0; count<size; count++){
               stringStatsChangableComponent.addOrCombineStat(stringStatsChangables.get(count));
            }
        }
}
}
