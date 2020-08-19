package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Attachable;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.GameMap;

import java.security.acl.Owner;

public class SetEntityPositionAndActionToOwnerSystem extends GameEntitySystem {

    private ComponentMapper<OwnedComponent> ownedComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
    private ComponentMapper<Action> actionComponentMapper;
    private ImmutableArray<Entity> entities;



    public SetEntityPositionAndActionToOwnerSystem(int priority, MapDraw draw) {
        super(priority, draw);
    }

    @Override
    public void addedToEngine(Engine engine) {

        ownedComponentMapper=getGameComponentMapper().getOwnedComponentComponentMapper();

        positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();
        actionComponentMapper=getGameComponentMapper().getActionComponentMapper();
    }

    @Override
    public void update(float deltaTime) {

        entities= getEngine().getEntitiesFor(Family.all( OwnedComponent.class, Action.class, Position.class).get());
        int size=entities.size();
        for(int count=0; count<size; count++) {
            Entity entity = entities.get(count);
            OwnedComponent ownedComponent = ownedComponentMapper.get(entity);
            System.out.println("setting Position");
            Entity owner = getWorld().getEntity(ownedComponent.getOwnerEntityID());
            if (owner != null) {

                if (ownedComponent.isSetEntityPositionToOwner()) {

                    Position ownerPosition = positionComponentMapper.get(owner);
                    Position entityPosition = positionComponentMapper.get(entity);


                    entityPosition.setDirection(ownerPosition.getDirection());
                    entityPosition.setSetScreenLocations(ownerPosition.getScreenLocationX(), ownerPosition.getScreenLocationY());
                    GameMap map = getDraw().getWorld().getMap(ownerPosition.getMapWorldLocationX(), ownerPosition.getMapWorldLocationY());
                    map.removeEntity(entityPosition.getTiles(), entity);
                    entityPosition.setTiles(map.getAllTilesAndAddEntity(entityPosition.getBoundsBoundingRectangle(), entity));

                }


                if (ownedComponent.isSetEntityActionToOwner()) {

                    Action ownerAction = actionComponentMapper.get(owner);
                    Action entityAction = actionComponentMapper.get(entity);
                    if (ownerAction != null && entityAction != null) {
                        entityAction.setStat(ownerAction.getText());
                    }


                }
            }
            else{

                entity.remove(OwnerComponent.class);

            }

        }



    }
}



