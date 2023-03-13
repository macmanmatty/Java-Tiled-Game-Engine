package com.jessematty.black.tower.Systems.Pack;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.EntityId;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Containers.Pack;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.Engine.EventSystem;

/**
 * system for adding an item to player  pack
 */

public class AddToPackSystem extends EventSystem {
    private ComponentMapper<Pack> packComponentMapper;
    private ImmutableArray<Entity> entities;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ComponentMapper<Attachable> attachableComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<EntityId> idComponentMapper;
    private ComponentMapper<ItemComponent> itemComponentComponentMapper;

    public AddToPackSystem(String packId, String itemId,   MapDraw draw) {
        super(draw);
    }

    @Override
    public void addedToEngine(Engine engine) {
        packComponentMapper =GameComponentMapper.getPackComponentMapper();
        positionComponentMapper= GameComponentMapper.getPositionComponentMapper();
        attachableComponentMapper=GameComponentMapper.getAttachableComponentMapper();
        ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
        idComponentMapper=GameComponentMapper.getIdComponentMapper();
        itemComponentComponentMapper=GameComponentMapper.getItemComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }


    @Override
    public void act(float deltaTime) {

    }
}



