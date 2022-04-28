package com.jessematty.black.tower.Systems.Owner;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AttachEntity.AddOwnerComponent;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.EntityId;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class AddOwnerSystem extends GameEntitySystem { // checks  the die when zero  stats  for all entities  if all stats are zero
    // marks the entities  as dying.

    private ComponentMapper<AddOwnerComponent> addOwnerComponentComponentMapper;
    private ImmutableArray<Entity> entities;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ComponentMapper<Attachable> attachableComponentMapper;
    private ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    private ComponentMapper<EntityId> idComponentMapper;

    public AddOwnerSystem(MapDraw draw) {
        super(draw);


    }

    @Override
    public void addedToEngine(Engine engine) {
        addOwnerComponentComponentMapper=GameComponentMapper.getAddOwnerComponentComponentMapper();
        positionComponentMapper= GameComponentMapper.getPositionComponentMapper();
        attachableComponentMapper=GameComponentMapper.getAttachableComponentMapper();
        ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
        idComponentMapper=GameComponentMapper.getIdComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        entities = getEngine().getEntitiesFor(Family.all(AddOwnerComponent.class).get());

        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            AddOwnerComponent addOwnerComponent = addOwnerComponentComponentMapper.get(entity);
            entity.remove(AddOwnerComponent.class);
            String ownerID = addOwnerComponent.getOwnerId();
            Entity entityToAdd = getWorld().getEntity(ownerID);
            EntityUtilities.attachEntity(getWorld(), entity, entityToAdd);

        }
    }


}



