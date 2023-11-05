package com.jessematty.black.tower.Systems.Item;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.BodyParts.BodyComponent;
import com.jessematty.black.tower.Components.AttachEntity.EquipItem;
import com.jessematty.black.tower.Components.Base.GroupsComponent;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.AttachEntity.UnCurseItem;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class UnCurseItemSystem extends GameEntitySystem {
    private ComponentMapper<Attachable> attachableComponentMapper;
    private ComponentMapper<BodyComponent> bodyComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<EntityId> idComponentMapper;
    private  ComponentMapper<EquipItem> equipItemComponentMapper;
    private ComponentMapper<GroupsComponent> groupsComponentMapper;



    public UnCurseItemSystem(MapDraw draw) {
        super(draw);

    }

    @Override
    public void addedToEngine(Engine engine) {
        attachableComponentMapper = GameComponentMapper.getAttachableComponentMapper();
        ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
        ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        idComponentMapper=GameComponentMapper.getIdComponentMapper();
        equipItemComponentMapper=GameComponentMapper.getEquipItemComponentMapper();
        groupsComponentMapper=GameComponentMapper.getGroupsComponentMapper();

    }




    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(Attachable.class, ItemComponent.class, UnCurseItem.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity item = entities.get(count);
            Attachable itemAttachable = attachableComponentMapper.get(item);
            itemAttachable.setRemovable(true);
            item.remove(UnCurseItem.class);





        }
    }


}
