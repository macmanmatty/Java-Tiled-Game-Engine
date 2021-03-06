package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.HoldPosition;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Movable;
import com.jessematty.black.tower.Components.PhysicalObjectComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Json.Entity.Transient;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

@Transient

public class ChangeHeldItemBoundsSystem extends GameEntitySystem {
    private ComponentMapper<PositionComponent> positionComponentComponentMapper;
    private ComponentMapper<Holder> holderComponentMapper;
    private ComponentMapper<Item> itemComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;

    ImmutableArray<Entity> entities;


    Movable movable;
    PhysicalObjectComponent object;

    public ChangeHeldItemBoundsSystem(MapDraw draw, int priorty) {
        super(priorty, draw);
    }


    @Override
    public void addedToEngine(Engine engine) {
        positionComponentComponentMapper = getGameComponentMapper().getPositionComponentMapper();
         holderComponentMapper =getGameComponentMapper().getHolderComponentMapper();
         itemComponentMapper=getGameComponentMapper().getItemComponentMapper();
         ownedComponentComponentMapper=getGameComponentMapper().getOwnedComponentComponentMapper();

    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {

        entities = getEngine().getEntitiesFor(Family.all(Item.class, OwnedComponent.class, PositionComponent.class, Action.class).get());


        int size = entities.size();

        for (int count = 0; count < size; count++) {
            Entity entity = entities.get(count);
            Item item =itemComponentMapper.get(entity);
            PositionComponent position = positionComponentComponentMapper.get(entity);


            OwnedComponent ownedComponent=ownedComponentComponentMapper.get(entity);
            String ownerId=ownedComponent.getOwnerEntityID();
            // get owner
            if(ownerId==null){
                continue;
            }

            Entity owner=getWorld().getEntity(ownerId);
            if(owner==null){
                continue;
            }

            PositionComponent ownerPosition= positionComponentComponentMapper.get(owner);
            Holder holder= holderComponentMapper.get(owner);
            if(ownerPosition==null){
                continue;
            }
            if(holder==null){
                continue;
            }

            if(holder.isHoldPositionChanged()) {
                HoldPosition holdPosition=holder.getHoldPosition();
                holder.setHoldPositionChanged(false);
                switch (holdPosition){
                    case OUT:
                        position.reInstateBounds();
                        break;
                    case UP:
                        position.removeBounds();
                        break;
                }


            }


            if(item.isHeld()==true  && ownerPosition.isDirectionChanged()) {

                rotate(position);


            }




        }



    }

    private   void rotate(PositionComponent positionComponent){

        System.out.println("rotated!");
        Polygon bounds=positionComponent.getBounds();
        Direction direction=positionComponent.getDirection();

        Rectangle boundingRectangle=positionComponent.getBoundsBoundingRectangle();

        switch(direction){

            case UP:

                bounds.setRotation(0f);
                break;
            case RIGHT:

                bounds.setRotation(270f);
                break;
            case DOWN:

                bounds.setRotation(180f);
                break;
            case LEFT:

                bounds.setRotation(90f);
                break;
            case RIGHTUP:

                bounds.setRotation(315f);
                break;
            case RIGHTDOWN:

                bounds.setRotation(225f);
                break;
            case LEFTUP:

                bounds.setRotation(45f);
                break;
            case LEFTDOWN:

                bounds.setRotation(135);
                break;
            case SAME:
                break;
        }
        System.out.println("Degrees: " + bounds.getRotation());


    }



}

