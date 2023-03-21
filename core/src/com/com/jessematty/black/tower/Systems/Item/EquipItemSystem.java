package com.jessematty.black.tower.Systems.Item;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.Other.ErrorComponent;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.AttachEntity.EquipItem;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;
import com.jessematty.black.tower.Systems.Stats.ChangeStats;

public class EquipItemSystem extends GameEntitySystem {
    private ComponentMapper<Attachable> attachableComponentMapper;
    private ComponentMapper<Body> bodyComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<EntityId> idComponentMapper;
    private  ComponentMapper<EquipItem> equipItemComponentMapper;
    private ComponentMapper<GroupsComponent> groupsComponentMapper;
    private ComponentMapper<NameComponent> nameComponentMapper;



    public EquipItemSystem(MapDraw draw) {
        super(draw);

    }

    @Override
    public void addedToEngine(Engine engine) {
        attachableComponentMapper = GameComponentMapper.getAttachableComponentMapper();
        ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
        ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        idComponentMapper=GameComponentMapper.getIdComponentMapper();
        groupsComponentMapper=GameComponentMapper.getGroupsComponentMapper();
        nameComponentMapper=GameComponentMapper.getNameComponentMapper();
        equipItemComponentMapper=GameComponentMapper.getEquipItemComponentMapper();

    }




    @Override
    public void update(float deltaTime) {
      ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(Attachable.class, EquipItem.class,  EquipItem.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
           Entity itemToEquip= entities.get(count);
            Attachable itemToEquipAttachable = attachableComponentMapper.get(itemToEquip);


            ErrorComponent errorComponent=equipItem(itemToEquip);

            //item was not equipped add an error message
            if(errorComponent!=null){

                itemToEquip.add(errorComponent);

            }

            itemToEquip.remove(EquipItem.class);


        }

    }



    public ErrorComponent  equipItem( Entity itemToEquip) {

        // get attachable component
        Attachable itemToEquipAttachable = attachableComponentMapper.get(itemToEquip);
        EquipItem equipItem=equipItemComponentMapper.get(itemToEquip);
        String equipperId = equipItem.getEquiperID();

        // get  the entity equipping the item
        Entity equiper = getWorld().getEntity(equipperId);
        Array<String> equiperGroups = groupsComponentMapper.get(equiper).getGroups();
        // check if groups match if not return false as item can't be equipped
        if (!InList.isInList(itemToEquipAttachable.getAttachableGroups(), equiperGroups)) {

            return  null;
        }


        // change  the stats for the equiper
            ChangeStats.changeStats(equiper, itemToEquip, "unEquip", true, true, true);
            // get owner component  of the equipping entity
        OwnerComponent ownerComponent = ownerComponentComponentMapper.get(equiper);
        // if equipping entity has no owner component  add it
            if (ownerComponent == null) {
                ownerComponent = new OwnerComponent();
                equiper.add(ownerComponent);

            }
            // get item to equip id
            String itemToEquipID = idComponentMapper.get(itemToEquip).getId();
            Array<String> ownedEntityIDs = ownerComponent.getOwnedEntityIDs();
            // check max number of owned  entites is reach if so  return false item can't be equiped



        // too many entites attached can't equip item return false



                ownedEntityIDs.add(itemToEquipID);
                itemToEquip.add(new OwnedComponent(equipperId, true, true));
        return  null;

    }


}
