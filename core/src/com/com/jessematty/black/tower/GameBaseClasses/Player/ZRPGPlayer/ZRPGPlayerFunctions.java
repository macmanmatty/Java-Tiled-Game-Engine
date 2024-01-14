package com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Containers.PackComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Character.CharacterItemFunctions;
import com.jessematty.black.tower.GameBaseClasses.Character.CharacterMoveFunctions;
import com.jessematty.black.tower.GameBaseClasses.Character.PlayerItemFunctions;
import com.jessematty.black.tower.GameBaseClasses.Input.DualActionKeyInputCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyAction;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyPressMode;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.MultipleEntitySelect.EntitySelectWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.MultipleEntitySelect.OnEntitySelectFunctions;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;

/**
 * class that holds all InputKeyCombos related to player actions
 * such as moving stopping picking up items shooting , slashing eating ETC.
 */
public class ZRPGPlayerFunctions {
    /**
     * the player object
     */
    private ZRPGCharacter player;
    /**
     *  the key input combos used to control the player
     */
    private final  Array<InputKeyCombo> playerControlFunctions= new Array<>();
    /**
     * the games map draw class
     */
    private final MapDraw mapDraw;
    /**
     * the key mappings  the for the player actions
     */

    private PlayerKeys playerKeys;

    /**
     * adds default player functions
     * @param draw
     * @param player
     */

    public ZRPGPlayerFunctions( PlayerKeys playerKeys, MapDraw draw , ZRPGCharacter player) {
        this.player = player;
        this.mapDraw=draw;
        this.playerKeys=playerKeys;
        DualActionKeyInputCombo moveRightCombo= new DualActionKeyInputCombo( stop, moveRight, "Move Player Right", playerKeys.getMoveRight());
        playerControlFunctions.addAll(  moveRightCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveLeftCombo= new DualActionKeyInputCombo( stop, moveLeft, "Move Player Left", playerKeys.getMoveLeft());
        playerControlFunctions.addAll(  moveLeftCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveUpCombo= new DualActionKeyInputCombo( stop, moveUp, "Move Player Up", playerKeys.getMoveUp());
        playerControlFunctions.addAll(  moveUpCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveDownCombo= new DualActionKeyInputCombo( stop, moveDown, "Move Player Down", playerKeys.getMoveDown());
        playerControlFunctions.addAll(  moveDownCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveRightDownCombo= new DualActionKeyInputCombo( stop, moveRightDown, "Move Player RightDown", playerKeys.getMoveDownRight());
        playerControlFunctions.addAll(  moveRightDownCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveRightUpCombo= new DualActionKeyInputCombo( stop, moveRightUp, "Move Player RightUp", playerKeys.getMoveUpRight());
        playerControlFunctions.addAll(  moveRightUpCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveLeftDownCombo= new DualActionKeyInputCombo( stop, moveLeftDown, "Move Player LeftDown", playerKeys.getMoveDownLeft());
        playerControlFunctions.addAll(  moveLeftDownCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveLeftUpCombo= new DualActionKeyInputCombo( stop, moveLeftUp, "Move Player LeftUp", playerKeys.getMoveUpLeft());
        playerControlFunctions.addAll(  moveLeftUpCombo.getInputKeyCombos());
        KeyPressMode []  keyPressMode= new KeyPressMode []{KeyPressMode.KEY_DOWN, KeyPressMode.KEY_PRESSED};
        InputKeyCombo increaseSpeedCombo= new InputKeyCombo(increaseSpeed, keyPressMode, "Increase Player Speed", playerKeys.getIncreaseSpeed());
        playerControlFunctions.add(increaseSpeedCombo);
        InputKeyCombo decreaseSpeedCombo= new InputKeyCombo(decreaseSpeed,keyPressMode, "Decrease Player Speed", playerKeys.getDecreaseSpeed());
        playerControlFunctions.add(decreaseSpeedCombo);
        InputKeyCombo slashCombo= new InputKeyCombo(slash, KeyPressMode.KEY_DOWN, "Slash", playerKeys.getSlash());
        playerControlFunctions.add(slashCombo);
        InputKeyCombo thrustCombo= new InputKeyCombo(thrust, KeyPressMode.KEY_DOWN, "Thrust", playerKeys.getThrust());
        playerControlFunctions.add(thrustCombo);
        InputKeyCombo packDisplay= new InputKeyCombo(viewPacks, KeyPressMode.KEY_DOWN, "Display Pack Window", playerKeys.getDisplayPack());
        playerControlFunctions.add(packDisplay);
        InputKeyCombo pickUpItem= new InputKeyCombo(pickupItem, KeyPressMode.KEY_DOWN, "Pick Up Item", playerKeys.getPickupItem());
        playerControlFunctions.add(pickUpItem);
        InputKeyCombo addItemToPackCombo= new InputKeyCombo(addItemToPack,  KeyPressMode.KEY_DOWN, "Add Item To Pack", playerKeys.getAddItemToPack());
        playerControlFunctions.add(addItemToPackCombo);
        InputKeyCombo dropItemCombo= new InputKeyCombo(dropItem,  KeyPressMode.KEY_DOWN, "Drop Item", playerKeys.getDropItem());
        playerControlFunctions.add(dropItemCombo);
        InputKeyCombo switchHand= new InputKeyCombo(this.switchHand,  KeyPressMode.KEY_DOWN, "Switch Hand", playerKeys.getSwitchHand());
        playerControlFunctions.add(switchHand);

    }
    private KeyAction switchHand =new KeyAction(){
        @Override
        public void act()  {
            CharacterItemFunctions.switchHand(player);
        }};

    private KeyAction addItemToPack =new KeyAction(){
        @Override
        public void act()  {
           PlayerItemFunctions.addItemToPackFromHand(player,mapDraw );
        }};



    private KeyAction dropItem =new KeyAction(){
        @Override
        public void act()  {
        }};

    private KeyAction slash =new KeyAction(){
        @Override
        public void act()  {
            CharacterItemFunctions.slashItem(player);
        }};


    private KeyAction thrust=new KeyAction(){
        @Override
        public void act()  {
            CharacterItemFunctions.thrustItem(player);
        }};
    private KeyAction shoot=new KeyAction(){
        @Override
        public void act()  {
            CharacterItemFunctions.shootItem(player);

        }};
    private KeyAction eat=new KeyAction(){
        @Override
        public void act()  {
            CharacterItemFunctions.eatItem(player);

        }};
    /**
     * displays the players pack window
     */
    private   KeyAction displayPack= new KeyAction() {
        @Override
        public void act() {
            PlayerItemFunctions.displayPack(mapDraw, player);

        }
    };
    /**
     * stops player movement
     */
  private   KeyAction stop= new KeyAction() {
        @Override
        public void act() {
            CharacterMoveFunctions.stop(player);
        }
    };
    /**
     * player movement functions up down left right ect.
     */
    private  KeyAction moveRight =new KeyAction(){
          @Override
          public void act()  {
                 CharacterMoveFunctions.moveRight(player);
              }
          
      };
    private KeyAction moveLeft =new KeyAction(){
        @Override
          public void act()  {
            CharacterMoveFunctions.moveLeft(player);

        }};
    private KeyAction moveUp =new KeyAction(){
        @Override
          public void act()  {
            CharacterMoveFunctions.moveUp(player);

        }};
    private KeyAction moveDown =new KeyAction(){
        @Override
          public void act()  {
            CharacterMoveFunctions.moveDown(player);

        }};
    private KeyAction moveRightUp =new KeyAction(){
        @Override
          public void act()  {
            CharacterMoveFunctions.moveRightUp(player);

        }};
    private KeyAction moveLeftUp =new KeyAction(){
        @Override
          public void act()  {
            CharacterMoveFunctions.moveLeftUp(player);

        }};
    private KeyAction moveLeftDown =new KeyAction(){
        @Override
          public void act()  {
            CharacterMoveFunctions.moveLeftDown(player);

        }};
    private KeyAction moveRightDown =new KeyAction(){
        @Override
          public void act()  {
            CharacterMoveFunctions.moveRightDown(player);

        }};
    private KeyAction increaseSpeed =new KeyAction(){
        @Override
        public void act()  {
            CharacterMoveFunctions.increaseSpeed(player);

        }};



    /**
     * increases a players speed
     */
    private KeyAction decreaseSpeed =new KeyAction(){
        @Override
        public void act()  {
            CharacterMoveFunctions.decreaseSpeed(player);

        }};



    private KeyAction addItemToPackLeft =new KeyAction(){
        @Override
        public void act()  {

        }};

     private void  addItemToPack(int hand){

     }

    private KeyAction pickupItem =new KeyAction(){
        @Override
          public void act()  {
            PlayerItemFunctions.pickUpItem(player, mapDraw);

    }};
    private KeyAction viewPacks =new KeyAction(){
        @Override
        public void act()  {
            Array<Entity> containers=mapDraw.getWorld().getEntitiesFromEntityIdsArray(player.getPacks().getPackEntityIds());
         EntitySelectWindow window=  new EntitySelectWindow("Select A Pack To View Contents", mapDraw.getGameAssets().getCurrentSkin(), containers, mapDraw.getGameAssets(), "View Contents", new OnEntitySelectFunctions(mapDraw).packDisplay);
            mapDraw.getUiStage().addWindow(window, ScreenPosition.CENTER);
        }};

    public Array<InputKeyCombo> getPlayerControlFunctions() {
        return playerControlFunctions;
    }
}
