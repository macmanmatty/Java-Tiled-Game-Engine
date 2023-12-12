package com.jessematty.black.tower.Systems.Owner;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class SetEntityPositionAndActionToOwnerSystem extends GameEntitySystem {
    private ComponentMapper<OwnedComponent> ownedComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ComponentMapper<ActionComponent> actionComponentMapper;
    private ImmutableArray<Entity> entities;
    public SetEntityPositionAndActionToOwnerSystem(int priority, MapDraw draw) {
        super(priority, draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        ownedComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
        positionComponentMapper= GameComponentMapper.getPositionComponentMapper();
        actionComponentMapper=GameComponentMapper.getActionComponentMapper();
    }
    @Override
    public void update(float deltaTime) {
        entities= getEngine().getEntitiesFor(Family.all( OwnedComponent.class, ActionComponent.class, PositionComponent.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entity = entities.get(count);
            OwnedComponent ownedComponent = ownedComponentMapper.get(entity);
            Entity owner = getWorld().getEntity(ownedComponent.getOwnerEntityID());
            if (owner != null) {
                if (ownedComponent.isSetEntityPositionToOwner()) {
                    PositionComponent ownerPosition = positionComponentMapper.get(owner);
                    PositionComponent entityPosition = positionComponentMapper.get(entity);
                    entityPosition.setDirection(ownerPosition.getDirection());
                    entityPosition.setPosition(ownerPosition.getLocationX(), ownerPosition.getLocationY());
                    GameMap map = getDraw().getWorld().getMap(ownerPosition.getMapId());
                    map.removeEntity(entityPosition.getTiles(), entity);
                    // add entity to map  add the tiles
                    entityPosition.setTiles(map.getAllTilesAndAddEntity(entityPosition.getBoundsBoundingRectangle(), entity));
                    entityPosition.setMapID(ownerPosition.getMapId());
                }
                if (ownedComponent.isSetEntityActionToOwner()) {
                    ActionComponent ownerActionComponent = actionComponentMapper.get(owner);
                    ActionComponent entityActionComponent = actionComponentMapper.get(entity);
                    if (ownerActionComponent != null && entityActionComponent != null) {
                        entityActionComponent.setAction(ownerActionComponent.getAction());
                    }
                }
                if (ownedComponent.isSetOwnerPositionToEntity()) {
                    PositionComponent ownerPosition = positionComponentMapper.get(owner);
                    PositionComponent entityPosition = positionComponentMapper.get(entity);
                    ownerPosition.setDirection(entityPosition.getDirection());
                    ownerPosition.setPosition(entityPosition.getLocationX(), entityPosition.getLocationY());
                    GameMap map = getDraw().getWorld().getMap(entityPosition.getMapId());
                    map.removeEntity(ownerPosition.getTiles(), owner);
                    // add entity to map  add the tiles
                    ownerPosition.setTiles(map.getAllTilesAndAddEntity(ownerPosition.getBoundsBoundingRectangle(), owner));
                }
                if (ownedComponent.isSetOwnerActionToEntity()) {
                    ActionComponent ownerActionComponent = actionComponentMapper.get(owner);
                    ActionComponent entityActionComponent = actionComponentMapper.get(entity);
                    if (ownerActionComponent != null && entityActionComponent != null) {
                        ownerActionComponent.setAction(entityActionComponent.getAction());
                    }
                }
            }
            else{
                entity.remove(OwnerComponent.class);
            }
        }
    }
}
