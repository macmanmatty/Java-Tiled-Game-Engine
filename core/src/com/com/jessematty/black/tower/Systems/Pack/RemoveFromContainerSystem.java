package com.jessematty.black.tower.Systems.Pack;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.EventComponents.RemoveItemFromContainer;
import com.jessematty.black.tower.Components.Other.RemoveFromEngine;
import com.jessematty.black.tower.Components.Position.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Systems.GameEntitySystem;

/**
 * system for adding an item to player  pack
 */

public class RemoveFromContainerSystem extends GameEntitySystem {
    private ComponentMapper<ContainerComponent> containerComponentComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentComponentMapper;
    private ComponentMapper<EntityId> idComponentMapper;
    private ComponentMapper<PhysicalObjectComponent> physicalObjectComponentComponentMapper;
    private ComponentMapper<RemoveItemFromContainer> addItemToContainerComponentMapper;
    private ComponentMapper<GroupsComponent> groupsComponentMapper;
    public RemoveFromContainerSystem(MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        containerComponentComponentMapper =GameComponentMapper.getContainerComponentMapper();
        ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
        idComponentMapper=GameComponentMapper.getIdComponentMapper();
        physicalObjectComponentComponentMapper=GameComponentMapper.getPhysicalObjectComponentMapper();
        addItemToContainerComponentMapper=GameComponentMapper.getRemoveItemFromContainerComponentComponentMapper();
        groupsComponentMapper=GameComponentMapper.getGroupsComponentMapper();
        positionComponentComponentMapper=GameComponentMapper.getPositionComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }


    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities=getEngine().getEntitiesFor(Family.all(RemoveItemFromContainer.class).get());
        for(Entity entity: entities) {
            RemoveItemFromContainer removeItemFromContainer=addItemToContainerComponentMapper.get(entity);
            Entity container = getWorld().getEntity(removeItemFromContainer.getContainerId());
            Entity itemToAdd = getWorld().getEntity(removeItemFromContainer.getItemId());
            ContainerComponent containerComponent = containerComponentComponentMapper.get(container);
            GroupsComponent groupsComponent =groupsComponentMapper.get(itemToAdd);

            boolean removable=checkRemoveable(containerComponent, groupsComponent);
                if(removable){
                    String itemToAddId=idComponentMapper.get(itemToAdd).getId();
                    containerComponent.getEntitiesInContainerIds().removeValue(itemToAddId, false);
                    containerComponent.getEntitiesInContainer().removeValue(itemToAdd, false);

                    if(removeItemFromContainer.isDetachOnRemove()){
                        EntityUtilities.detachEntity( container, itemToAdd);
                    }
                }
        }

    }


    /**
     * checks to see if an item can be removed from  a container based om something
     *
     * @param containerComponent the container component  with
     * @param groupsComponent the groups of  the entity to add to the container
     * @return boolean true if  the entity can be removed  from  the container based on something
     */
    private boolean checkRemoveable(ContainerComponent containerComponent, GroupsComponent  groupsComponent){
        return true;
    }

    private void removeEntity( boolean destroyItem, Entity addItemToPackEvent, Entity entityToAdd){
        addItemToPackEvent.add(new RemoveFromEngine());
        if(destroyItem) {
            entityToAdd.add(new RemoveFromEngine());
        }

    }

}



