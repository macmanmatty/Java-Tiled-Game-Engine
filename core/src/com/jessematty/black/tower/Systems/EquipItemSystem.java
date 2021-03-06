package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.AttachedComponent;
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.ErrorComponent;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.AttachEntity.EquipItem;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Name;
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
    private ComponentMapper<AttachedComponent> attachedComponentComponentMapper;
    private ComponentMapper<Name> nameComponentMapper;



    public EquipItemSystem(MapDraw draw) {
        super(draw);

    }

    @Override
    public void addedToEngine(Engine engine) {
        attachableComponentMapper =getGameComponentMapper().getAttachableComponentMapper();
        ownerComponentComponentMapper=getGameComponentMapper().getOwnerComponentComponentMapper();
        ownedComponentComponentMapper=getGameComponentMapper().getOwnedComponentComponentMapper();
        idComponentMapper=getGameComponentMapper().getIdComponentMapper();
        groupsComponentMapper=getGameComponentMapper().getGroupsComponentMapper();
        attachedComponentComponentMapper=getGameComponentMapper().getAttachedComponentComponentMapper();
        nameComponentMapper=getGameComponentMapper().getNameComponentMapper();
        equipItemComponentMapper=getGameComponentMapper().getEquipItemComponentMapper();

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

        AttachedComponent attachedComponent=attachedComponentComponentMapper.get(equiper);
        if(attachedComponent==null){
            attachedComponent= new AttachedComponent();
            attachedComponent.setMaxAttachedEntities(-1);
        }
        int maxEntitesAttached=attachedComponent.getMaxAttachedEntities();

        // too many entites attached can't equip item return false
        if(maxEntitesAttached>0 && attachedComponent.getAttachedEntities().size>=maxEntitesAttached){
            return  null;

        }


                ownedEntityIDs.add(itemToEquipID);
                itemToEquip.add(new OwnedComponent(equipperId, true, true));
        Name name=nameComponentMapper.get(itemToEquip);
                attachedComponent.getAttachedEntities().put(name.getStat(), equipperId);

        return  null;

    }


}
