package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;

public class SetEntityPositionAndActionToOwnerSystem extends GameEntitySystem {

    private ComponentMapper<OwnedComponent> ownedComponentMapper;
    private ComponentMapper<PositionComponent> positionComponentMapper;
    private ComponentMapper<Action> actionComponentMapper;
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

        entities= getEngine().getEntitiesFor(Family.all( OwnedComponent.class, Action.class, PositionComponent.class).get());
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


                }


                if (ownedComponent.isSetEntityActionToOwner()) {

                    Action ownerAction = actionComponentMapper.get(owner);
                    Action entityAction = actionComponentMapper.get(entity);
                    if (ownerAction != null && entityAction != null) {
                        entityAction.setStat(ownerAction.getStat());
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

                    Action ownerAction = actionComponentMapper.get(owner);
                    Action entityAction = actionComponentMapper.get(entity);
                    if (ownerAction != null && entityAction != null) {
                        ownerAction.setStat(entityAction.getStat());
                    }


                }



            }
            else{

                entity.remove(OwnerComponent.class);

            }

        }



    }
}



