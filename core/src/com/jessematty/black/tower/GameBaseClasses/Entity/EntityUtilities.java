package com.jessematty.black.tower.GameBaseClasses.Entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.Components.PhysicalObject;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.Components.Animation.Animation;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.ChangeStats.BooleanStatsChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.NumericStatsSelfChangable;
import com.jessematty.black.tower.Components.Stats.ChangeStats.StringStatsChangable;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Generators.Entity.EntityContainers.BasicEntityContainer;
import com.jessematty.black.tower.GameBaseClasses.Loaders.Copy.CopyObject;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.World;

public class EntityUtilities {


    
   private static Array<Entity> entities= new Array<>();
   private static Array<String> entityIds= new Array<>();

   private EntityUtilities() {
    }
    // get the an  array of all entities and sub owned entities  owned by a entity using recursion

    public static  Array<Entity> getAllOwnedEntities(Entity entity, World world){
       getAllOwnedEntitiesIDs(entity, world);
        return entities;

    }

    //gets all entities ids linked to an entity using recursion
    public static  Array<String> getAllConnectedEntitiesIds(Entity entity, World world, boolean attached){
        ComponentMapper<OwnerComponent> ownerComponentMapper=world.getGameComponentMapper().getOwnerComponentComponentMapper();
        ComponentMapper<OwnedComponent> ownedComponentComponentMapperr=world.getGameComponentMapper().getOwnedComponentComponentMapper();


        OwnerComponent entityOwnerComponent =ownerComponentMapper.get(entity);
        OwnedComponent ownedComponent=ownedComponentComponentMapperr.get(entity);
        if(entityOwnerComponent==null && ownedComponent==null){
            return  entityIds;
        }

        String ownerId=ownedComponent.getOwnerEntityID();
        boolean attachedToOwner=ownedComponent.isAttached();
        if(attached==false || attachedToOwner==true){
            if (ownerId != null && !ownerId.isEmpty() && attachedToOwner == true) {
                Entity owner = world.getEntity(ownerId);
                entities.add(owner);
                entityIds.add(ownerId);
                getAllConnectedEntitiesIds(owner, world, attached);
            }

        }



        Array<String> ownedEntityIds=entityOwnerComponent.getOwnedEntityIDs();
        int size=entities.size;
        for(int count=0; count<size; count++){
            String entityId=ownedEntityIds.get(count);
            Entity ownedEntity=world.getEntity(entityId);
            OwnedComponent ownedEntityOwnedComponent=ownedComponentComponentMapperr.get(entity);
            if(ownedComponent.isAttached()==false&&attached==true){
                continue;
            }
            entities.add(ownedEntity);
            entityIds.add(entityId);
            OwnerComponent ownedEntityOwnerComponent=ownerComponentMapper.get(entity);
            if(ownedEntityOwnerComponent==null || ownedEntityOwnerComponent.getOwnedEntityIDs().size<=0){

                continue;
            }

            getAllConnectedEntitiesIds(ownedEntity, world, attached);

        }

        return entityIds;

    }

    public static  Array<Entity> getAllConnectedEntities(Entity entity, World world, boolean attached ){
      getAllConnectedEntitiesIds(entity, world, attached);
      return  entities;

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
            Position position=gameComponentMapper.getPositionComponentMapper().get(entity);
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
        ComponentMapper<PhysicalObject> physicalObjectComponentMapper=gameComponentMapper.getPhysicalObjectComponentMapper();
        ComponentMapper<Position> positionComponentMapper=gameComponentMapper.getPositionComponentMapper();

        PhysicalObject physicalObject=physicalObjectComponentMapper.get(entity);
        Position position=positionComponentMapper.get(entity);
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


    private static   double [] getTotalWeightAndVolume( double []  massAndVolume,  ComponentMapper<PhysicalObject> physicalObjectComponentMapper, Entity entity){
       PhysicalObject physicalObject= physicalObjectComponentMapper.get(entity);
        if(physicalObject!=null){
            massAndVolume[0]=massAndVolume[0]+physicalObject.getMass();
            massAndVolume[1]=massAndVolume[1]+physicalObject.getVolume();
        }


        return  massAndVolume;
    }


    public static  double [] getMassVolumeWeight( World world, Entity entity) {

        ComponentMapper<PhysicalObject> physicalObjectComponentMapper = world.getGameComponentMapper().getPhysicalObjectComponentMapper();

        PhysicalObject physicalObject = physicalObjectComponentMapper.get(entity);
        ComponentMapper<Position> positionComponentMapper = world.getGameComponentMapper().getPositionComponentMapper();
        Position position = positionComponentMapper.get(entity);

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


        return new BasicEntityContainer(entity, id, name, groups,  numericStats, booleanStats, stringStats, stringStatsChangable, numericStatsSelfChangable, booleanStatsChangable, numericStatsChangable);

    }


}
