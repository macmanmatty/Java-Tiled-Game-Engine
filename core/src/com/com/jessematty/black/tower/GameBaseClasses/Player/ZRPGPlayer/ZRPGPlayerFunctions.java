package com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Entity.Functions.CharacterItemFunctions;
import com.jessematty.black.tower.GameBaseClasses.Entity.Functions.CharacterMoveFunctions;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyAction;
import com.jessematty.black.tower.GameBaseClasses.Input.DualActionKeyInputCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyPressMode;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.OptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;

/**
 * class that holds all InputKeyCombos related to player actions
 * such as moving stopping picking up items shooting , slashing eating ETC.
 */
public class ZRPGPlayerFunctions {
    private ZRPGCharacter player;
    private final  Array<InputKeyCombo> playerControlFunctions= new Array<>();
    private final MapDraw mapDraw;
    public ZRPGPlayerFunctions(MapDraw draw , ZRPGCharacter player) {
        this.player = player;
        this.mapDraw=draw;
        DualActionKeyInputCombo moveRightCombo= new DualActionKeyInputCombo( stop, moveRight, "Move Player Right", Keys.RIGHT);
        playerControlFunctions.addAll(  moveRightCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveLeftCombo= new DualActionKeyInputCombo( stop, moveLeft, "Move Player Left", Keys.LEFT);
        playerControlFunctions.addAll(  moveLeftCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveUpCombo= new DualActionKeyInputCombo( stop, moveUp, "Move Player Up", Keys.UP);
        playerControlFunctions.addAll(  moveUpCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveDownCombo= new DualActionKeyInputCombo( stop, moveDown, "Move Player Down", Keys.DOWN);
        playerControlFunctions.addAll(  moveDownCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveRightDownCombo= new DualActionKeyInputCombo( stop, moveRightDown, "Move Player RightDown", Keys.L);
        playerControlFunctions.addAll(  moveRightDownCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveRightUpCombo= new DualActionKeyInputCombo( stop, moveRightUp, "Move Player RightUpDown", Keys.P);
        playerControlFunctions.addAll(  moveRightUpCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveLeftDownCombo= new DualActionKeyInputCombo( stop, moveLeftDown, "Move Player LeftDown", Keys.K);
        playerControlFunctions.addAll(  moveLeftDownCombo.getInputKeyCombos());
        DualActionKeyInputCombo moveLeftUpCombo= new DualActionKeyInputCombo( stop, moveLeftUp, "Move Player LeftUp", Keys.O);
        playerControlFunctions.addAll(  moveLeftUpCombo.getInputKeyCombos());
        KeyPressMode []  keyPressMode= new KeyPressMode []{KeyPressMode.KEY_DOWN, KeyPressMode.KEY_PRESSED};
        InputKeyCombo increaseSpeedCombo= new InputKeyCombo(increaseSpeed, keyPressMode, "Increase Player Speed", Keys.CONTROL_RIGHT);
        playerControlFunctions.add(increaseSpeedCombo);
        InputKeyCombo decreaseSpeedCombo= new InputKeyCombo(decreaseSpeed,keyPressMode, "Decrease Player Speed", Keys.ALT_RIGHT);
        playerControlFunctions.add(decreaseSpeedCombo);
        InputKeyCombo displayPackWindow= new InputKeyCombo(displayPack, KeyPressMode.KEY_DOWN, "Display Pack Window", Keys.T);
        playerControlFunctions.add(displayPackWindow);

    }
    private   KeyAction displayPack= new KeyAction() {
        @Override
        public void act() {
            GameAssets.getGameInput().getLockableInputMultiplexer().lockAllOtherProcessorMouseInput(mapDraw.getUiStage());
            mapDraw.getUiStage().addWindow(new OptionPane(mapDraw.getCurrentMap().getSkin(), "OK", "Are You Ok?", "Click Me!!!", "Brick"), ScreenPosition.CENTER);
        }
    };
  private   KeyAction stop= new KeyAction() {
        @Override
        public void act() {
            CharacterMoveFunctions.stop(player);
        }
    };
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
    private KeyAction decreaseSpeed =new KeyAction(){
        @Override
        public void act()  {
            CharacterMoveFunctions.decreaseSpeed(player);

        }};

    private KeyAction pickupItem =new KeyAction(){
        @Override
          public void act()  {
            CharacterItemFunctions.pickUpItem(player);

    }};

    public Array<InputKeyCombo> getPlayerControlFunctions() {
        return playerControlFunctions;
    }
}
