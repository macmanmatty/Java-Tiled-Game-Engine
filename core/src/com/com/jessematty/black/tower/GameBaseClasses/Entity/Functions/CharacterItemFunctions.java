package com.jessematty.black.tower.GameBaseClasses.Entity.Functions;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Item.AddItemToPackComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Drop;
import com.jessematty.black.tower.Components.Item.PickUpItem;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Read;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Shoot;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Slash;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Throw;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Thrust;
import com.jessematty.black.tower.Components.AttachEntity.EquipItem;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.DominateHand;
import com.jessematty.black.tower.Components.EntityId;
import com.jessematty.black.tower.Components.HoldPosition;
import com.jessematty.black.tower.Components.Ingest;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ItemActionWindow;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;

public class CharacterItemFunctions {

    private  static ComponentMapper<EntityId> idComponentMapper=ComponentMapper.getFor(EntityId.class);
    private static  MapDraw mapDraw;
    private static  World world;
    public static  void pickUpItem(ZRPGCharacter zrpgCharacter) {
        Holder[] holder = zrpgCharacter.getHandHolders();
        if (holder[0].getItemToHoldId() == null || holder[1].getItemToHoldId() == null) {
            PositionComponent position = zrpgCharacter.getPosition();
            GameMap map = mapDraw.getWorld().getMap(position.getMapId());
            Array<Entity> entities = MapUtilities.getClosestEntities(map, position, map.getTileWidth(), ItemComponent.class);
            PickUpItem pickUpItem = new PickUpItem();
            String handId = "";
            DominateHand dominateHand = zrpgCharacter.getDominateHand();
            if (dominateHand == DominateHand.RIGHT) {
                if (holder[0].getItemToHoldId() == null) {
                    handId = idComponentMapper.get(zrpgCharacter.getHand(0)).getId();
                }
            } else if (holder[1].getItemToHoldId() == null) {
                handId = idComponentMapper.get(zrpgCharacter.getHand(1)).getId();
            }
            pickUpItem.setEntityToPickUpId(handId);
            if (entities.size > 0) {
                if (zrpgCharacter.isAutoPickUpFirstItem()) {
                    entities.get(0).add(pickUpItem);
                    return;
                } else {
                    String text = "Select an Item to Pick Up";
                    mapDraw.getUiStage().addWindow(new ItemActionWindow(text, "Select An Item", entities, pickUpItem, mapDraw), ScreenPosition.CENTER);
                }
            }
        }
    }

    private void dropItem(ZRPGCharacter player) {
        Drop drop = new Drop();
        player.getHand(1).add(drop);
    }

    private void eatItem(ZRPGCharacter player) {
        Ingest ingest = new Ingest();
        ingest.setIngestorID(player.getId());
        player.getHand(1).add(ingest);
    }
    private void slashItem(ZRPGCharacter player) {
        Slash slash = new Slash();
        Holder holder=player.getHandHolders()[1];
        String itemToHoledID=holder.getItemToHoldId();
        if(itemToHoledID!=null && !itemToHoledID.isEmpty()) {
            Entity heldItem = world.getEntity(holder.getItemToHoldId());
            heldItem.add(slash);
            player.getActionComponent().setStat("slash");
        }
    }
    private void thrustItem(ZRPGCharacter player) {
        Thrust thrust = new Thrust();
        Holder holder=player.getHandHolders()[1];
        String itemToHoledID=holder.getItemToHoldId();
        if(itemToHoledID!=null && !itemToHoledID.isEmpty()) {
            Entity heldItem = world.getEntity(holder.getItemToHoldId());
            heldItem.add(thrust);
            player.getActionComponent().setStat("thrust");
        }
    }
    private void readItem( ZRPGCharacter player, int hand) {
        Read read = new Read();
        player.getHand(hand).add(read);
    }
    private void shootItem( ZRPGCharacter player, int hand) {
        Shoot shoot = new Shoot();
        player.getHand(hand).add(shoot);
    }
    private void throwItem(ZRPGCharacter player, int handNumber) {
        Throw throwV= new Throw();
        Entity hand=player.getHand(handNumber);
        if(hand!=null) {
            hand.add(throwV);
        }
    }
    private void equipItem(ZRPGCharacter player) {
        EquipItem equipItem= new EquipItem();
        player.getHand(1).add(equipItem);
    }
    private void addItemToPack(ZRPGCharacter player, int hand){
        Holder []  holder=player.getHandHolders();
        if (holder[0].getItemToHoldId()== null || holder[1].getItemToHoldId() == null) {
            PositionComponent position = player.getPosition();
            GameMap map = mapDraw.getWorld().getMap(position.getMapId());
            Array<Entity> packs = EntityUtilities.getOwnedEntitiesWithComponents(mapDraw.getWorld(), player.getPlayerEntity(), Pack.class);
            AddItemToPackComponent addItemToPackComponent= new AddItemToPackComponent();
            addItemToPackComponent.setItemToAddId(idComponentMapper.get(player.getHand(hand)).getId());
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
