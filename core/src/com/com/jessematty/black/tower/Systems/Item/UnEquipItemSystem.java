package com.jessematty.black.tower.Systems.Item;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.DetachItemMode;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.AttachEntity.UnEquipItem;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.Components.BodyParts.BodyComponent;
import com.jessematty.black.tower.Components.Other.ErrorComponent;
import com.jessematty.black.tower.Components.Other.RemoveFromEngine;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;
import com.jessematty.black.tower.Systems.Stats.ChangeStats;

public class UnEquipItemSystem extends GameEntitySystem {
    private ComponentMapper<Attachable> attachableComponentMapper;
    private ComponentMapper<BodyComponent> bodyComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<EntityId> idComponentMapper;
    private ComponentMapper<UnEquipItem> unEquipItemComponentMapperquipItemComponentMapper;
    private ComponentMapper<NameComponent> nameComponentMapper;



    public UnEquipItemSystem(MapDraw draw) {
        super(draw);

    }

    @Override
    public void addedToEngine(Engine engine) {
        attachableComponentMapper = GameComponentMapper.getAttachableComponentMapper();
        ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
        ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        idComponentMapper=GameComponentMapper.getIdComponentMapper();
        unEquipItemComponentMapperquipItemComponentMapper=GameComponentMapper.getUnEquipItemComponentMapperr();
        nameComponentMapper=GameComponentMapper.getNameComponentMapper();

    }




    @Override
    public void update(float deltaTime) {
      ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(Attachable.class,  UnEquipItem.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
           Entity itemToUnEquip= entities.get(count);

            Attachable itemToEquipAttachable = attachableComponentMapper.get(itemToUnEquip);

            ErrorComponent errorComponent=unEquipItem(itemToUnEquip);

            if(errorComponent!=null){

                itemToUnEquip.add(errorComponent);

            }


        }

    }


    // item unequipping method returns  false if item cannot be unequipped
    private ErrorComponent unEquipItem(Entity itemToUnEquip){

        Attachable itemToEquipAttachable = attachableComponentMapper.get(itemToUnEquip);
        String equiperId=ownedComponentComponentMapper.get(itemToUnEquip).getOwnerEntityID();
        Entity unEquiper=getWorld().getEntity(equiperId);


        if (!itemToEquipAttachable.isRemovable()) {
            return  null;

        }


            UnEquipItem unEquipItem=unEquipItemComponentMapperquipItemComponentMapper.get(itemToUnEquip);

        // change stats back
        ChangeStats.changeStats(unEquiper, itemToUnEquip, "unEquip", true, false, false);

        // get owner component should not be null as one is created in equip item if it is null
            OwnerComponent ownerComponent=ownerComponentComponentMapper.get(unEquiper);
        // get attached component should not be null as one is created in equip item if it is null


                String itemToUnEquipID = idComponentMapper.get(itemToUnEquip).getId();
                ownerComponent.getOwnedEntityIDs().removeValue(itemToUnEquipID, false);
            itemToUnEquip.remove(OwnerComponent.class);
            itemToUnEquip.remove(UnEquipItem.class);
            itemToUnEquip.add(unEquipItem.getDetachItemAction());




            return  null;



    }

    private void removeItem(DetachItemMode detachItemMode, Entity unEquipItem) {

        switch (detachItemMode){
            case DROP:
                break;
            case PACK:
                break;
            case DISAPPEAR:
                unEquipItem.add(new RemoveFromEngine());
                break;
        }
    }

}
