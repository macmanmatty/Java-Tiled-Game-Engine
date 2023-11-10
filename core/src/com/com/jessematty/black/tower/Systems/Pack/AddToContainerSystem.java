package com.jessematty.black.tower.Systems.Pack;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.EventComponents.AddItemToContainer;
import com.jessematty.black.tower.Components.Position.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Other.RemoveFromEngine;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Systems.GameEntitySystem;

/**
 * system for adding an item to a container
 */

public class AddToContainerSystem extends GameEntitySystem {
    private ComponentMapper<ContainerComponent> containerComponentComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentComponentMapper;
    private ComponentMapper<EntityId> idComponentMapper;
    private ComponentMapper<PhysicalObjectComponent> physicalObjectComponentComponentMapper;
    private ComponentMapper<AddItemToContainer> addItemToContainerComponentMapper;
    private ComponentMapper<GroupsComponent> groupsComponentMapper;
    public AddToContainerSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        containerComponentComponentMapper =GameComponentMapper.getContainerComponentMapper();
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
        for(Entity entity: entities) {
            AddItemToContainer addItemToContainer = addItemToContainerComponentMapper.get(entity);
            Entity container =addItemToContainer.getContainer();
            ContainerComponent containerComponent = containerComponentComponentMapper.get(container);
            PhysicalObjectComponent physicalObjectComponent = physicalObjectComponentComponentMapper.get(entity);
            PositionComponent containerPosition = positionComponentComponentMapper.get(container);
            PositionComponent itemToAddPosition = positionComponentComponentMapper.get(entity);
            GroupsComponent groupsComponent = groupsComponentMapper.get(entity);

            if (checkAddable(groupsComponent, containerComponent, physicalObjectComponent, itemToAddPosition)) {
                String itemToAddId = idComponentMapper.get(entity).getId();
                containerComponent.getEntitiesInContainerIds().add(itemToAddId);
                if (addItemToContainer.isRemoveItemBoundsOnAdd()) {
                    itemToAddPosition.removeBounds();
                }
                if (addItemToContainer.isSetContainerAsOwner()) {
                    EntityUtilities.attachEntity(container, entity);
                }
            }
        }
    }

    /**
     * checks to see if an item can be added to a container based on whether or not it's groups match with the container components groups
     *
     * @param containerComponent the container component  with
     * @param groupsComponent the groups of  the entity to add to the container
     * @return boolean true if  the entity can be added to the container based on it's groups false if it can't
     */
    private boolean checkInGroups(ContainerComponent containerComponent, GroupsComponent  groupsComponent){
        Array<String> groupsAddable=containerComponent.getGroupsAddable();
        if (groupsComponent !=null && groupsAddable.size>0 && !InList.isInList(groupsComponent.getGroups(), containerComponent.getGroupsAddable())) {
            return false;
        }
        return true;
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
    private boolean checkAddable( GroupsComponent groupsComponent,  ContainerComponent containerComponent, PhysicalObjectComponent physicalObjectComponent, PositionComponent positionComponent){
        boolean addable=checkInGroups(containerComponent, groupsComponent);
        if(!addable){
            return false;

        }
        // check by number of items
        int maxNumberOfItems=containerComponent.getMaxNumberOfItemsAllowed();
        if((containerComponent.isAddByNumberOfItems() && maxNumberOfItems>containerComponent.getEntitiesInContainerIds().size) ||maxNumberOfItems<0){
            return true;
        }

        // check by weight and volume
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
        if(physicalObjectComponent==null){
            return true;
        }
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



