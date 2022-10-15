package com.jessematty.black.tower.Systems.Item;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.EquipItem;
import com.jessematty.black.tower.Components.AttachEntity.HoldItem;
import com.jessematty.black.tower.Components.AttachEntity.Holdable;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.ErrorComponent;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.EntityId;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.Systems.GameEntitySystem;
import com.jessematty.black.tower.Systems.Stats.ChangeStats;

public class HoldItemSystem extends GameEntitySystem {
    private ComponentMapper<HoldItem> holdItemComponentMapper;
        private ComponentMapper<Holder> holderComponentMapper;
    private ComponentMapper<Body> bodyComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<EntityId> idComponentMapper;
    private  ComponentMapper<com.jessematty.black.tower.Components.AttachEntity.Holdable> holdableComponentMapper;
    private ComponentMapper<Groups> groupsComponentMapper;



    public HoldItemSystem(MapDraw draw) {
        super(draw);

    }

    @Override
    public void addedToEngine(Engine engine) {
        holdItemComponentMapper = GameComponentMapper.getHoldItemComponentMapper();
        ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
        ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        idComponentMapper=GameComponentMapper.getIdComponentMapper();
        holdableComponentMapper =GameComponentMapper.getHoldableComponentMapper();
        groupsComponentMapper=GameComponentMapper.getGroupsComponentMapper();
        holderComponentMapper=GameComponentMapper.getHolderComponentMapper();

    }




    @Override
    public void update(float deltaTime) {
      ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(Attachable.class, Holdable.class,  OwnedComponent.class, EquipItem.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
           Entity itemToHold= entities.get(count);
            Holdable holdable=holdableComponentMapper.get(itemToHold);
            ErrorComponent error=equipItem(itemToHold);
            //item was not equipped add an error message
            if(error!=null){

                itemToHold.add(error);

            }

            itemToHold.remove(EquipItem.class);


        }

    }



    public ErrorComponent  equipItem( Entity itemToEquip) {

        // gte attachable component
        Holdable itemToEquipAttachable = holdableComponentMapper.get(itemToEquip);
        String equiperId = ownedComponentComponentMapper.get(itemToEquip).getOwnerEntityID();
        // get  the entity equipping the item
        Entity equiper = getWorld().getEntity(equiperId);
        Array<String> equiperGroups = groupsComponentMapper.get(equiper).getGroups();
        // check if groups match if not return false as item can't be equipped
        if (!InList.isInList(itemToEquipAttachable.getHoldGroups(), equiperGroups)) {

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

        Holder holder=holderComponentMapper.get(equiper);

        if(holder==null){
            return null;

        }

        // too many entites attached can't equip item return false
        if(holder.getItemToHoldId()!=null){
            return  null;

        }

                ownedEntityIDs.add(itemToEquipID);
                itemToEquip.add(new OwnedComponent(equiperId, true, true));
                holder.setItemToHoldId(itemToEquipID);

        return  null;
    }


}
