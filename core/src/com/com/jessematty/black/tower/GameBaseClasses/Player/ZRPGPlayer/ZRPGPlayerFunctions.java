package com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.Containers.PackComponent;
import com.jessematty.black.tower.Components.EventComponents.AddItemToContainer;
import com.jessematty.black.tower.Components.Item.ItemComponent;
import com.jessematty.black.tower.Components.Other.Player;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Direction.Direction;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Entity.Functions.CharacterMoveFunctions;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyAction;
import com.jessematty.black.tower.GameBaseClasses.Input.DualActionKeyInputCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyPressMode;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.OptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.MultipleEntitySelect.EntitySelectWindow;
import com.jessematty.black.tower.GameBaseClasses.Utilities.PackUtilities;
import com.jessematty.black.tower.Maps.World;

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
     * adds default player functions
     * @param draw
     * @param player
     */
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
        InputKeyCombo increaseSpeedCombo= new InputKeyCombo(increaseSpeed, keyPressMode, "Increase Player Speed", Keys.CONTROL_LEFT);
        playerControlFunctions.add(increaseSpeedCombo);
        InputKeyCombo decreaseSpeedCombo= new InputKeyCombo(decreaseSpeed,keyPressMode, "Decrease Player Speed", Keys.ALT_RIGHT);
        playerControlFunctions.add(decreaseSpeedCombo);
        InputKeyCombo displayPackWindow= new InputKeyCombo(displayPack, KeyPressMode.KEY_DOWN, "Display Pack Window", Keys.T);
        playerControlFunctions.add(displayPackWindow);
        InputKeyCombo slashCombo= new InputKeyCombo(slash, KeyPressMode.KEY_DOWN, "Slash", Keys.S);
        playerControlFunctions.add(slashCombo);


    }
    private KeyAction slash =new KeyAction(){
        @Override
        public void act()  {
            player.getActionComponent().setStat("slash");
            player.getActionComponent().setTurnsToCompletion(80);

        }};
    /**
     * displays the players pack window
     */
    private   KeyAction displayPack= new KeyAction() {
        @Override
        public void act() {
            GameAssets.getGameInput().getLockableInputMultiplexer().lockAllOtherProcessorMouseInput(mapDraw.getUiStage());
            mapDraw.getUiStage().addWindow(new OptionPane(mapDraw.getCurrentMap().getSkin(),  "Are You Ok?", "Click Me!!!", "packWindow"), ScreenPosition.CENTER);
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
         World world=player.getWorld();
         Entity item=world.getEntity( player.getHolders()[hand].getItemToHoldId());
         Array<Entity> containerComponents= PackUtilities.getAvailableContainers(world,player.getPacks(), item);
         OnSelected<Entity> onSelected= new OnSelected<Entity>() {
             @Override
             public void onSelected(Entity pack) {
                 item.add(new AddItemToContainer(pack));

             }
         };
         EntitySelectWindow entitySelectWindow= new EntitySelectWindow(mapDraw.getGameAssets().getSkin(), containerComponents, mapDraw.getGameAssets(), onSelected);

     }

    private KeyAction pickupItem =new KeyAction(){
        @Override
          public void act()  {
            Array<Entity> entities=player.getPositionComponent().getTiles().get(0).getEntities(ItemComponent.class);
            if(entities.size>0){
            }

            PackUtilities.getAvailableContainers(mapDraw.getWorld(), player.getPacks(), null);
    }};

    public Array<InputKeyCombo> getPlayerControlFunctions() {
        return playerControlFunctions;
    }
}
