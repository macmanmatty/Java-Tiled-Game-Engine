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

    public static void dropItem(ZRPGCharacter player) {
        DropItemComponent dropItemClass = new DropItemComponent();
        player.getHand(1).add(dropItemClass);
    }

    public static void eatItem(ZRPGCharacter player) {
       player.getActionComponent().setAction("eat");
    }
    public static void slashItem(ZRPGCharacter player) {

            player.getActionComponent().setAction("slash");

    }
    public static void switchHand(ZRPGCharacter player) {

       int hand= player.getCurrentHand();
       if(hand==0){
           player.setCurrentHand(1);
       }
       else{
           player.setCurrentHand(0);
       }

    }



    public static void thrustItem(ZRPGCharacter player) {
            player.getActionComponent().setAction("thrust");
    }
    public static void readItem( ZRPGCharacter player, int hand) {
        player.getActionComponent().setAction("read");
    }
    public static void shootItem( ZRPGCharacter player) {
        player.getActionComponent().setAction("shoot");
    }
    public static void throwItem(ZRPGCharacter player, int handNumber) {
        player.getActionComponent().setAction("throw");

    }
    private void spellCast(ZRPGCharacter player, int handNumber) {
        player.getActionComponent().setAction("spellCast");

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

    private void changeHoldPosition( ZRPGCharacter player, int hand) {
        Holder holder = player.getHandHolders()[hand];
        HoldPosition[]  holdPosition=HoldPosition.values();
        int holdPositions=holdPosition.length;

    }
}
