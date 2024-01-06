package com.jessematty.black.tower.Systems.Item;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.Interfaces.Transient;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.HoldPosition;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Systems.GameEntitySystem;
@Transient
public class ChangeHeldItemBoundsSystem extends GameEntitySystem {
    private ComponentMapper<PositionComponent> positionComponentComponentMapper;
    private ComponentMapper<Holder> holderComponentMapper;
    private ComponentMapper<ItemComponent> itemComponentMapper;
    private ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    ImmutableArray<Entity> entities;
    public ChangeHeldItemBoundsSystem(MapDraw draw, int priority) {
        super(priority, draw);
    }
    @Override
    public void addedToEngine(Engine engine) {
        positionComponentComponentMapper = GameComponentMapper.getPositionComponentMapper();
         holderComponentMapper =GameComponentMapper.getHolderComponentMapper();
         itemComponentMapper=GameComponentMapper.getItemComponentMapper();
         ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();
    }
    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }
    @Override
    public void update(float deltaTime) {
        entities = getEngine().getEntitiesFor(Family.all(ItemComponent.class, OwnedComponent.class, PositionComponent.class, ActionComponent.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity itemEntity = entities.get(count);
            ItemComponent item =itemComponentMapper.get(itemEntity);
            PositionComponent itemPosition = positionComponentComponentMapper.get(itemEntity);
            OwnedComponent ownedComponent=ownedComponentComponentMapper.get(itemEntity);
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
                        itemPosition.reInstateBounds();
                        break;
                    case UP:
                        itemPosition.removeBounds();
                        break;
                }
            }
            if(item.isHeld() && ownerPosition.isDirectionChanged()) {
                rotate(itemPosition, ownerPosition);
            }
        }
    }
    private   void rotate(PositionComponent itemPosition, PositionComponent ownerPosition){
        System.out.println("rotated!");
        Polygon itemBounds=itemPosition.getBounds();
        Direction direction=itemPosition.getDirection();
        Rectangle ownerBounds=ownerPosition.getBoundsBoundingRectangle();

        switch(direction){
            case UP:
                float x= 20;
                float y= 64;
                itemPosition.setBoundsXOffset(x);
                itemPosition.setBoundsYOffset(y);
                itemBounds.setRotation(0f);

                break;
            case RIGHT:
                x= 55;
                y= 32;
                itemPosition.setBoundsXOffset(x);
                itemPosition.setBoundsYOffset(y);
                itemBounds.setRotation(270f);
                break;
            case DOWN:
                x= 45;
                y=0;
                itemPosition.setBoundsXOffset(x);
                itemPosition.setBoundsYOffset(0);
                itemBounds.setRotation(180f);
                break;
            case LEFT:
                y=0;
                x=15;
                itemPosition.setBoundsYOffset(y);
                itemPosition.setBoundsXOffset(x);
                itemBounds.setRotation(90f);
                break;
            case RIGHTUP:
                itemBounds.setRotation(315f);
                break;
            case RIGHTDOWN:
                itemBounds.setRotation(225f);
                break;
            case LEFTUP:
                itemBounds.setRotation(45f);
                break;
            case LEFTDOWN:
                itemBounds.setRotation(135);
                break;
            case SAME:
                break;
        }
    }
}
