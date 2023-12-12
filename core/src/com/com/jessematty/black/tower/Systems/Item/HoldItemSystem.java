package com.jessematty.black.tower.Systems.Item;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.EquipItem;
import com.jessematty.black.tower.Components.AttachEntity.HoldItem;
import com.jessematty.black.tower.Components.AttachEntity.Holdable;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.BodyParts.BodyComponent;
import com.jessematty.black.tower.Components.Other.ErrorComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;
import com.jessematty.black.tower.Systems.GameEntitySystem;
import com.jessematty.black.tower.Systems.Stats.ChangeStats;

public class HoldItemSystem extends GameEntitySystem {
    private ComponentMapper<HoldItem> holdItemComponentMapper;
        private ComponentMapper<Holder> holderComponentMapper;
    private ComponentMapper<BodyComponent> bodyComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<EntityId> idComponentMapper;
    private  ComponentMapper<com.jessematty.black.tower.Components.AttachEntity.Holdable> holdableComponentMapper;
    private ComponentMapper<GroupsComponent> groupsComponentMapper;



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
      ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all( HoldItem.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
           Entity itemToHold= entities.get(count);
            HoldItem holdable=holdItemComponentMapper.get(itemToHold);
            OwnedComponent ownedComponent=ownedComponentComponentMapper.get(itemToHold);
            if(ownedComponent!=null){
                Entity currentOwner=getWorld().getEntity(ownedComponent.getOwnerEntityID());
                EntityUtilities.detachEntity(currentOwner, itemToHold);
            }
            itemToHold.remove(EquipItem.class);
            Entity holderEntity=getWorld().getEntity(holdable.getHolderId());
            Holder holder=holderComponentMapper.get(holderEntity);
            EntityId entityId=idComponentMapper.get(itemToHold);
            holder.setItemToHoldId(entityId.getId());
            EntityUtilities.attachEntity(holderEntity,itemToHold );
            itemToHold.remove(HoldItem.class);
        }

    }



}
