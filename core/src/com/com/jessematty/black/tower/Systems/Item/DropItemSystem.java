package com.jessematty.black.tower.Systems.Item;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponent;
import com.jessematty.black.tower.Components.AttachEntity.Attachable;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.AttachEntity.OwnedComponent;
import com.jessematty.black.tower.Components.AttachEntity.OwnerComponent;
import com.jessematty.black.tower.Components.Item.ItemAction.DropItemComponent;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.RandomNumbers;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;
import com.jessematty.black.tower.Systems.GameEntitySystem;

public class DropItemSystem extends GameEntitySystem {

    RandomNumbers value = new RandomNumbers();
    public DropItemSystem(MapDraw draw) {
        super(draw);
    }
    ComponentMapper<Attachable> attachableComponentMapper;
    ComponentMapper<OwnerComponent> ownerComponentComponentMapper;
    ComponentMapper<OwnedComponent> ownedComponentComponentMapper;
    ComponentMapper<Holder> holderComponentMapper;
    ComponentMapper<PositionComponent> positionComponentMapper;
    ComponentMapper<ItemComponent> itemComponentMapper;
    ComponentMapper<ActionComponent> actionComponentMapper;


    @Override
    public void addedToEngine(Engine engine) {
        attachableComponentMapper = GameComponentMapper.getAttachableComponentMapper();
        ownerComponentComponentMapper=GameComponentMapper.getOwnerComponentComponentMapper();
        positionComponentMapper=GameComponentMapper.getPositionComponentMapper();
        itemComponentMapper=GameComponentMapper.getItemComponentMapper();
        actionComponentMapper=GameComponentMapper.getActionComponentMapper();
        holderComponentMapper=GameComponentMapper.getHolderComponentMapper();
        ownedComponentComponentMapper=GameComponentMapper.getOwnedComponentComponentMapper();


    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(ItemComponent.class, ActionComponent.class,   PositionComponent.class, DropItemComponent.class).get());
        for (Entity itemToDrop: entities) {
                itemToDrop.remove(DropItemComponent.class);
                ItemComponent item=itemComponentMapper.get(itemToDrop);
                ActionComponent actionComponent =actionComponentMapper.get(itemToDrop);
                PositionComponent positionComponent=positionComponentMapper.get(itemToDrop);
                actionComponent.setActing(false);
                actionComponent.setStat("rest");
                item.setInContainer(false);
                item.setOnGround(true);
                item.setHeld(false);
                positionComponent.reInstateBounds();
            OwnedComponent ownedComponent=ownedComponentComponentMapper.get(itemToDrop);
            if(ownedComponent!=null) {
                String ownerId = ownedComponent.getOwnerEntityID();
                Entity entity = getWorld().getEntity(ownerId);
                EntityUtilities.detachEntity(itemToDrop, entity);
            }
            GameMap map= getWorld().getMap(positionComponent.getMapId());
           positionComponent.setTiles( map.getAllTilesAndAddEntity(positionComponent.getBoundsBoundingRectangle(), itemToDrop));



        }

    }




    public void randomDrop (Entity item, PositionComponent position, int maxDrop) { // randomly drops an item  near  the  tile location of of an item on to another tile   by specifed distance
        int randomx = value.getRandomNumber(0, maxDrop+1);
        int randomy = value.getRandomNumber(0, maxDrop+1);
      Array<LandSquareTile> location = position.getTiles();
        int locationx = location.get(0).getLocationX();
        int locationy = location.get(0).getLocationY();
        LandSquareTile tile;
        tile = getWorld().getMap(position.getMapId()).getTile(locationx + randomx, locationy+randomy);
       tile.addEntity(item);
    }


}
