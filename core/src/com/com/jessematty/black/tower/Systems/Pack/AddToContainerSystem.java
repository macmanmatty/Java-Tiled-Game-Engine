package com.jessematty.black.tower.Systems.Pack;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.EventComponents.AddItemToContainer;
import com.jessematty.black.tower.Components.Base.Groups;
import com.jessematty.black.tower.Components.Other.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Other.RemoveFromEngine;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Systems.GameEntitySystem;

/**
 * system for adding an item to player  pack
 */

public class AddToContainerSystem extends GameEntitySystem {
    private ComponentMapper<ContainerComponent> containerComponentComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentComponentMapper;
    private ComponentMapper<EntityId> idComponentMapper;
    private ComponentMapper<PhysicalObjectComponent> physicalObjectComponentComponentMapper;
    private ComponentMapper<AddItemToContainer> addItemToContainerComponentMapper;
    private ComponentMapper<Groups> groupsComponentMapper;
    public AddToContainerSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        containerComponentComponentMapper =GameComponentMapper.getContainerComponentMapper();
        ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
        idComponentMapper=GameComponentMapper.getIdComponentMapper();
        physicalObjectComponentComponentMapper=GameComponentMapper.getPhysicalObjectComponentMapper();
        addItemToContainerComponentMapper=GameComponentMapper.getAddItemToContainerComponentMapper();
        groupsComponentMapper=GameComponentMapper.getGroupsComponentMapper();
       positionComponentComponentMapper=GameComponentMapper.getPositionComponentMapper();
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }


    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities=getEngine().getEntitiesFor(Family.all(AddItemToContainer.class).get());
        int size=entities.size();
        for(Entity entity: entities) {
            AddItemToContainer addItemToContainer=addItemToContainerComponentMapper.get(entity);
            Entity container = getWorld().getEntity(addItemToContainer.getContainerId());
            Entity itemToAdd = getWorld().getEntity(addItemToContainer.getItemId());
            ContainerComponent containerComponent = containerComponentComponentMapper.get(container);
            PhysicalObjectComponent physicalObjectComponent = physicalObjectComponentComponentMapper.get(itemToAdd);
            Groups groups=groupsComponentMapper.get(itemToAdd);
            Array<String> groupsAddable=containerComponent.getGroupsAddable();
                if ( groupsAddable.size>0 && !InList.isInList(groups, containerComponent.getGroupsAddable())) {
                    entity.add( new RemoveFromEngine());
                    continue;

                }
                boolean addable=true;




        }



    }

    private void removeEntity( boolean destroyItem, Entity addItemToPackEvent, Entity entityToAdd){
        addItemToPackEvent.add(new RemoveFromEngine());
        if(destroyItem) {
            entityToAdd.add(new RemoveFromEngine());
        }

    }
    /**
     * checks to see if an item can fit into the container
     * the weight of the item is based on the items mass and the current GameMaps gravity
     * @param containerComponent the container component to add  the item too
     * @param physicalObjectComponent
     * @param positionComponent
     * @return  a boolean representing whether or not the item can be added to  the container
     */
    private boolean checkAddable(ContainerComponent containerComponent, PhysicalObjectComponent physicalObjectComponent, PositionComponent positionComponent, int itemsInConainer){
        int maxNumberOfItems=containerComponent.getMaxNumberOfItemsAllowed();
        if((containerComponent.isAddByNumberOfItems() && maxNumberOfItems>itemsInConainer) ||maxNumberOfItems<0){
            return true;
        }
        else{
            return checkWeightAndVolume(containerComponent, physicalObjectComponent, positionComponent);
        }

    }


    /**
     * checks to see if an item can fit into the container based on the items  weight and mass
     * the weight of the item is based on the items mass and the current GameMaps gravity
     * @param containerComponent the container component to add  the item too
     * @param physicalObjectComponent the items to be added physical object  component
     * @param positionComponent the items to be added position component
     * @return  a boolean representing whether or not the item can be added to  the container
     */
    private boolean checkWeightAndVolume(ContainerComponent containerComponent, PhysicalObjectComponent physicalObjectComponent, PositionComponent positionComponent){
      double currentVolume=containerComponent.getCurrentCarryVolume();
      double currentCarryWeight=containerComponent.getCurrentWeight();
      double itemMass=physicalObjectComponent.getMass();
      double itemVolume=physicalObjectComponent.getVolume();
      String mapId=positionComponent.getMapId();
        GameMap gameMap=getWorld().getMap(mapId);
        double itemWeight= MapUtilities.calculateWeight(gameMap, itemMass);
      if(currentCarryWeight+itemWeight>containerComponent.getMaxHoldWeight()){
          return false;
      }
      if(currentVolume+itemVolume>containerComponent.getMaxVolume()){
          return  false;
      }
        return true;

    }



}


