package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.AddItemToContainerComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.AddItemToPackComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Drop;
import com.jessematty.black.tower.Components.Attachable;
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.DetachItemMode;
import com.jessematty.black.tower.Components.ErrorComponent;
import com.jessematty.black.tower.Components.ID;


import com.jessematty.black.tower.Components.RemoveFromEngine;
import com.jessematty.black.tower.Components.UnEquipItem;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class UnEquipItemSystem extends GameEntitySystem{
    private ComponentMapper<Attachable> attachableComponentMapper;
    private ComponentMapper<Body> bodyComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<ID> idComponentMapper;
    private ComponentMapper<UnEquipItem> unEquipItemComponentMapperquipItemComponentMapper;



    public UnEquipItemSystem(MapDraw draw) {
        super(draw);

    }

    @Override
    public void addedToEngine(Engine engine) {
        attachableComponentMapper =getGameComponentMapper().getAttachableComponentMapper();
        ownerComponentComponentMapper=getGameComponentMapper().getOwnerComponentComponentMapper();
        ownedComponentComponentMapper=getGameComponentMapper().getOwnedComponentComponentMapper();
        idComponentMapper=getGameComponentMapper().getIdComponentMapper();
        unEquipItemComponentMapperquipItemComponentMapper=getGameComponentMapper().getUnEquipItemComponentMapperr();

    }




    @Override
    public void update(float deltaTime) {
      ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(Attachable.class, OwnedComponent.class, UnEquipItem.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
           Entity itemToUnEquip= entities.get(count);
           itemToUnEquip.remove(UnEquipItem.class);

            Attachable itemToEquipAttachable = attachableComponentMapper.get(itemToUnEquip);
            String equiperId=ownedComponentComponentMapper.get(itemToUnEquip).getOwnerEntityID();
            Entity unEquiper=getWorld().getEntity(equiperId);


            if (itemToEquipAttachable.isRemovable()) {
                UnEquipItem unEquipItem=unEquipItemComponentMapperquipItemComponentMapper.get(itemToUnEquip);

                ChangeStats.changeStats(unEquiper, itemToUnEquip, "unEquip", true, false, false);
                OwnerComponent ownerComponent=ownerComponentComponentMapper.get(unEquiper);
                String itemToUnEquipID=idComponentMapper.get(itemToUnEquip).getId();
                ownerComponent.getOwnedEntityIDs().removeValue(itemToUnEquipID, false);
                itemToUnEquip.remove(OwnerComponent.class);
                removeItem(unEquipItem.getDetachItemMode(), itemToUnEquip);

            }

            else{

                itemToUnEquip.add(new ErrorComponent(itemToEquipAttachable.getUnDetachableTile(), itemToEquipAttachable.getUnDetachableMessage()));

            }


        }

    }

    private void removeItem(DetachItemMode detachItemMode, Entity unEquipItem) {

        switch (detachItemMode){
            case DROP:
                unEquipItem.add(new Drop());
                break;
            case PACK:
                unEquipItem.add(new AddItemToPackComponent());
                break;
            case DISAPPEAR:
                unEquipItem.add(new RemoveFromEngine());
                break;
        }
    }

}
