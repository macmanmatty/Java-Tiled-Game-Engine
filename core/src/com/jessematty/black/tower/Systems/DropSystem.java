package com.jessematty.black.tower.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Drop;
import com.jessematty.black.tower.Components.Attachable;
import com.jessematty.black.tower.Components.ErrorComponent;
import com.jessematty.black.tower.Components.Holder;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.OwnedComponent;
import com.jessematty.black.tower.Components.OwnerComponent;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class DropSystem extends GameEntitySystem {

    RandomNumbers value = new RandomNumbers();
    public DropSystem(MapDraw draw) {
        super(draw);
    }
    ComponentMapper<Attachable> attachableComponentMapper;
    ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    ComponentMapper<Holder> holderComponentMapper;
    ComponentMapper<Position> positionComponentMapper;
    ComponentMapper<Item> itemComponentMapper;
    ComponentMapper<Action> actionComponentMapper;


    @Override
    public void addedToEngine(Engine engine) {
        attachableComponentMapper =getGameComponentMapper().getAttachableComponentMapper();
        ownerComponentComponentMapper=getGameComponentMapper().getOwnerComponentComponentMapper();
        positionComponentMapper=getGameComponentMapper().getPositionComponentMapper();
        itemComponentMapper=getGameComponentMapper().getItemComponentMapper();
        actionComponentMapper=getGameComponentMapper().getActionComponentMapper();
        holderComponentMapper=getGameComponentMapper().getHolderComponentMapper();
        ownedComponentComponentMapper=getGameComponentMapper().getOwnedComponentComponentMapper();


    }




    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all( Action.class, Attachable.class, Position.class, OwnedComponent.class, Drop.class).get());
        int size = entities.size();
        for (int count = 0; count < size; count++) {
            Entity itemToDrop= entities.get(count);
            itemToDrop.remove(Drop.class);
            Attachable itemToEquipAttachable = attachableComponentMapper.get(itemToDrop);
            OwnedComponent ownedComponent=ownedComponentComponentMapper.get(itemToDrop);
            String ownerEntityId=ownedComponent.getOwnerEntityID();
            Entity ownerEntity=getWorld().getEntity(ownerEntityId);
            OwnerComponent ownerComponent=ownerComponentComponentMapper.get(ownerEntity);
            Holder holder=holderComponentMapper.get(ownerEntity);
            if(ownerComponent!=null){
               ownerComponent.getOwnedEntityIDs().removeValue(ownerEntityId, false);
            }
            if(holder!=null){
                holder.setItemToHoldId(null);
            }



            if (itemToEquipAttachable.isRemovable()) {
                Position position=positionComponentMapper.get(itemToDrop);
                Item item=itemComponentMapper.get(itemToDrop);
                Action action=actionComponentMapper.get(itemToDrop);
                action.setActing(false);
                action.setStat("rest");
                item.setInPack(false);
                item.setOnGround(true);






            }

            else{

                itemToDrop.add(new ErrorComponent(itemToEquipAttachable.getUnDetachableTile(), itemToEquipAttachable.getUnDetachableMessage()));

            }


        }

    }




    public void randomDrop (Entity item, Position position, int maxDrop) { // randomly drops an item  near  the  tile location of of an item on to another tile   by specifed distance
        int randomx = value.getRandomNumber(0, maxDrop+1);
        int randomy = value.getRandomNumber(0, maxDrop+1);
      Array<LandSquareTile> location = position.getTiles();
        int locationx = location.get(0).getLocationX();
        int locationy = location.get(0).getLocationY();
        LandSquareTile tile;
        tile = getDraw().getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationY()).getMapSquare(locationx + randomx, locationy+randomy);
       tile.addEntity(item);
    }


}
