package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.Transient;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Holdable;
import com.jessematty.black.tower.Components.HoldPosition;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.Attachable;
import com.jessematty.black.tower.Components.PhysicalObject;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
@Transient

public class ChangeHeldItemBoundsSystem extends GameEntitySystem {
    private ComponentMapper<Holdable> holdables;
    private ComponentMapper<Position> positions;
    private ComponentMapper<Movable> moveable;
    private ComponentMapper<Attachable> owners;
    private ComponentMapper<Action> actions;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;

    ImmutableArray<Entity> entities;


    Movable movable;
    PhysicalObject object;

    public ChangeHeldItemBoundsSystem(MapDraw draw) {
        super(draw);
    }


    @Override
    public void addedToEngine(Engine engine) {
       holdables = getGameComponentMapper().getHoldableComponentMapper();
        positions = getGameComponentMapper().getPositionComponentMapper();
         moveable = getGameComponentMapper().getMovableComponentMapper();
       owners = getGameComponentMapper().getAttachableComponentMapper();
         actions= getGameComponentMapper().getActionComponentMapper();
         ownedComponentComponentMapper=getGameComponentMapper().getOwnedComponentComponentMapper();
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {

        entities = getEngine().getEntitiesFor(Family.all(Holdable.class, OwnedComponent.class, Position.class, Attachable.class, Action.class).get());


        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            Holdable holdable =holdables.get(entity);
            Position position = positions.get(entity);
            HoldPosition holdPosition= holdable.getHoldPosition();
            if(holdPosition== HoldPosition.OUT) {
                Attachable attachable = owners.get(entity);
                OwnedComponent ownedComponent=ownedComponentComponentMapper.get(entity);
                Entity ownerEntity=getWorld().getEntity(ownedComponent.getOwnerEntityID());
                Action action = actions.get(entity);
                action.setActing(false);
                action.setStat("holdOut");
                Position ownerPosition = positions.get(ownerEntity);
                float screenLocationX = ownerPosition.getScreenLocationX();
                float screenLocationY = ownerPosition.getScreenLocationY();
                float yOffset = 16;
                float xOffset = 16;
                Polygon bounds = position.getBounds();
                float weaponWidth = position.getBoundsX();
                float weaponLength = position.getBoundsY();
                Direction direction = ownerPosition.getDirection();
                bounds.setOrigin(screenLocationX + xOffset + (weaponWidth / 2), screenLocationY + yOffset);
                bounds.setRotation(Direction.getAngel(direction));

            }

            else if (holdPosition==HoldPosition.UP){
                Action action = actions.get(entity);
                action.setActing(false);
                action.setStat("holdIn");
                position.removeBounds();

            }

            else{
                position.removeBounds();


            }


        }



    }



}

