package com.jessematty.black.tower.Systems.Owner;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.AttachEntity.AttachEntityEvent;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Systems.GameEntitySystem;

/**
 * system to attach one entity to another
 * when the
 */
public class AddOwnerSystem extends GameEntitySystem {

    private ComponentMapper<AttachEntityEvent> addOwnerComponentComponentMapper;
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
        entities = getEngine().getEntitiesFor(Family.all(AttachEntityEvent.class).get());

        for (Entity entity: entities) {
            AttachEntityEvent attachEntityEvent = addOwnerComponentComponentMapper.get(entity);
            entity.remove(AttachEntityEvent.class);
            String ownerID = attachEntityEvent.getOwnerId();
            Entity entityToAdd = getWorld().getEntity(ownerID);
            EntityUtilities.attachEntity( entity, entityToAdd);
        }
    }


}



