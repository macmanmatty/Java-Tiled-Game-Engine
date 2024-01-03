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
    private void target(float screenX, float screenY){
    }

    private void changeHoldPosition( ZRPGCharacter player, int hand) {
        Holder holder = player.getHandHolders()[hand];
        HoldPosition[]  holdPosition=HoldPosition.values();
        int holdPositions=holdPosition.length;

    }
}
