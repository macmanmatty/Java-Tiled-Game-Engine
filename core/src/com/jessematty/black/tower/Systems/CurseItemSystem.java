package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Attachable;
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.CurseItem;
import com.jessematty.black.tower.Components.EquipItem;
import com.jessematty.black.tower.Components.Groups;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class CurseItemSystem extends GameEntitySystem{
    private ComponentMapper<Attachable> attachableComponentMapper;
    private ComponentMapper<Body> bodyComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<ID> idComponentMapper;
    private  ComponentMapper<EquipItem> equipItemComponentMapper;
    private ComponentMapper<Groups> groupsComponentMapper;



    public CurseItemSystem(MapDraw draw) {
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
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(Attachable.class, Item.class, CurseItem.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity item = entities.get(count);
            Attachable itemAttachable = attachableComponentMapper.get(item);
            itemAttachable.setRemovable(false);
            item.remove(CurseItem.class);


        }
    }


}
