package com.jessematty.black.tower.GameBaseClasses.Character;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.Base.EntityId;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.EventComponents.AddItemToContainer;
import com.jessematty.black.tower.Components.Item.ItemAction.DropItemComponent;
import com.jessematty.black.tower.Components.Other.HoldPosition;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ItemActionWindow;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;

public class CharacterItemFunctions {

    private  static ComponentMapper<EntityId> idComponentMapper=ComponentMapper.getFor(EntityId.class);
    private static  MapDraw mapDraw;
    private static  World world;
    public static  void pickUpItem(ZRPGCharacter zrpgCharacter) {
//        Holder[] holder = zrpgCharacter.getHandHolders();
//        if (holder[0].getItemToHoldId() == null || holder[1].getItemToHoldId() == null) {
//            PositionComponent position = zrpgCharacter.getPositionComponent();
//            GameMap map = mapDraw.getWorld().getMap(position.getMapId());
//            Array<Entity> entities = MapUtilities.getClosestEntities(map, position, map.getTileWidth(), ItemComponent.class);
//            PickUpItemComponent pickUpItemComponent = new PickUpItemComponent();
//            String handId = "";
//            DominateHand dominateHand = zrpgCharacter.getDominateHand();
//            if (dominateHand == DominateHand.RIGHT) {
//                if (holder[0].getItemToHoldId() == null) {
//                    handId = idComponentMapper.get(zrpgCharacter.getHand(0)).getId();
//                }
//            } else if (holder[1].getItemToHoldId() == null) {
//                handId = idComponentMapper.get(zrpgCharacter.getHand(1)).getId();
//            }
//            pickUpItemComponent.setEntityToPickUpId(handId);
//            if (entities.size > 0) {
//                if (zrpgCharacter.isAutoPickUpFirstItem()) {
//                    entities.get(0).add(pickUpItemComponent);
//                    return;
//                } else {
//                    String text = "Select an Item to Pick Up";
//                    mapDraw.getUiStage().addWindow(new ItemActionWindow(text, "Select An Item", entities, pickUpItemComponent, mapDraw), ScreenPosition.CENTER);
//                }
//            }
//        }
    }

    public static void dropItem(ZRPGCharacter player) {
        DropItemComponent dropItemClass = new DropItemComponent();
        player.getHand(1).add(dropItemClass);
    }

    public static void eatItem(ZRPGCharacter player) {
       player.getActionComponent().setStat("eat");
    }
    public static void slashItem(ZRPGCharacter player) {

            player.getActionComponent().setStat("slash");

    }
    public static void thrustItem(ZRPGCharacter player) {
            player.getActionComponent().setStat("thrust");
    }
    public static void readItem( ZRPGCharacter player, int hand) {
        player.getActionComponent().setStat("read");
    }
    public static void shootItem( ZRPGCharacter player) {
        player.getActionComponent().setStat("shoot");
    }
    public static void throwItem(ZRPGCharacter player, int handNumber) {
        player.getActionComponent().setStat("throw");

    }
    private void spellCast(ZRPGCharacter player, int handNumber) {
        player.getActionComponent().setStat("spellCast");

    }
    private void equipItem(ZRPGCharacter player) {

    }
    private void addItemToPack(ZRPGCharacter player, int hand){
        Holder []  holder=player.getHandHolders();
        if (holder[0].getItemToHoldId()== null || holder[1].getItemToHoldId() == null) {
            PositionComponent position = player.getPositionComponent();
            GameMap map = mapDraw.getWorld().getMap(position.getMapId());
            Array<Entity> packs = EntityUtilities.getAllOwnedEntitiesWithComponents(mapDraw.getWorld(), player.getPlayerEntity(), ContainerComponent.class);
            AddItemToContainer addItemToPackComponent= new AddItemToContainer();
            if (packs.size > 0) {
                String text="Select A Pack To Add To";
                mapDraw.getUiStage().addWindow(new ItemActionWindow(text, "Select An Item", packs, addItemToPackComponent, mapDraw), ScreenPosition.CENTER);
            }
        }
    }
    private void target(float screenX, float screenY){
    }
    public Entity getItem( ZRPGCharacter player, int hand) {
        Holder[] holder = player.getHandHolders();
        String handId;
        if (hand == 1) {
            handId =holder[1].getItemToHoldId();
        } else {
            handId =holder[0].getItemToHoldId();
        }
        return  mapDraw.getWorld().getEntity(handId);
    }
    public Entity getAnyItem( ZRPGCharacter player, int hand) {
        World world = mapDraw.getWorld();
        Holder[] holder = player.getHandHolders();
        String handId;
        if (hand == 1) {
            handId = holder[1].getItemToHoldId();
            Entity entity = world.getEntity(handId);
            if (entity != null) {
                return entity;
            } else {
                handId = holder[0].getItemToHoldId();
                entity = world.getEntity(handId);
                return entity;
            }
        } else {
            handId = holder[0].getItemToHoldId();
            Entity entity = world.getEntity(handId);
            if (entity != null) {
                return entity;
            } else {
                handId = holder[1].getItemToHoldId();
                entity = world.getEntity(handId);
                return entity;
            }
        }
    }
    private void changeHoldPosition( ZRPGCharacter player, int hand) {
        Holder holder = player.getHandHolders()[hand];
        HoldPosition[]  holdPosition=HoldPosition.values();
        int holdPositions=holdPosition.length;

    }
}
