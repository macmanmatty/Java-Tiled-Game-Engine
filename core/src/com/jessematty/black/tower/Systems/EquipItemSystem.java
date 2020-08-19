package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Attachable;
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.ErrorComponent;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.EquipItem;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class EquipItemSystem extends GameEntitySystem{
    private ComponentMapper<Attachable> attachableComponentMapper;
    private ComponentMapper<Body> bodyComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<ID> idComponentMapper;
    private  ComponentMapper<EquipItem> equipItemComponentMapper;
    private ComponentMapper<Groups> groupsComponentMapper;



    public EquipItemSystem(MapDraw draw) {
        super(draw);

    }

    @Override
    public void addedToEngine(Engine engine) {
        attachableComponentMapper =getGameComponentMapper().getAttachableComponentMapper();
        ownerComponentComponentMapper=getGameComponentMapper().getOwnerComponentComponentMapper();
        ownedComponentComponentMapper=getGameComponentMapper().getOwnedComponentComponentMapper();
        idComponentMapper=getGameComponentMapper().getIdComponentMapper();
        equipItemComponentMapper=getGameComponentMapper().getEquipItemComponentMapper();
        groupsComponentMapper=getGameComponentMapper().getGroupsComponentMapper();

    }




    @Override
    public void update(float deltaTime) {
      ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(Attachable.class, OwnedComponent.class, EquipItem.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
           Entity itemToEquip= entities.get(count);
           EquipItem equipItem=equipItemComponentMapper.get(itemToEquip);

            Attachable itemToEquipAttachable = attachableComponentMapper.get(itemToEquip);
            String equiperId=ownedComponentComponentMapper.get(itemToEquip).getOwnerEntityID();
            Entity equiper=getWorld().getEntity(equiperId);


            Array<String> equiperGroups=groupsComponentMapper.get(equiper).getGroups();
            if (InList.isInList(itemToEquipAttachable.getAttachableGroups(), equiperGroups)) {
                ChangeStats.changeStats(equiper, itemToEquip, "unEquip", true, true, true);
                OwnerComponent ownerComponent=ownerComponentComponentMapper.get(equiper);
                String itemToEquipID=idComponentMapper.get(itemToEquip).getId();
                Array<String> ownedEntityIDs=ownerComponent.getOwnedEntityIDs();
                if(ownerComponent.getMaxOwnedEntities()<ownedEntityIDs.size) {
                    ownedEntityIDs.add(itemToEquipID);
                    itemToEquip.add(new OwnedComponent(equiperId, true, true));
                }
                else{

                    equiper.add(new ErrorComponent(ownerComponent.getMaxEntitiesErrorTitle(), ownerComponent.getMaxEntitiesError()));

                }



            }

            else{

                itemToEquip.add(new ErrorComponent(itemToEquipAttachable.getUnAttachableTile(), itemToEquipAttachable.getUnAttachableMessage()));

            }

            itemToEquip.remove(EquipItem.class);


        }

    }

}
