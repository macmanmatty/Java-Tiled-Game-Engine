package com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.AddItemToPackComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Drop;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Moved;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGroundComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.PickUp;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Read;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Shoot;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Slash;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Throw;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Thrust;
import com.jessematty.black.tower.Components.DominateHand;
import com.jessematty.black.tower.Components.AttachEntity.EquipItem;
import com.jessematty.black.tower.Components.HoldPosition;
import com.jessematty.black.tower.Components.Ingest;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.Position.PositionComponent;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyAction;
import com.jessematty.black.tower.GameBaseClasses.Input.DualActionKeyInputCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyPressMode;
import com.jessematty.black.tower.GameBaseClasses.Utilities.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ItemActionWindow;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.GameBaseClasses.Utilities.MapUtilities;
import com.jessematty.black.tower.Maps.World;
public class ZRPGPlayerFunctions {
    private ZRPGPlayer player;
    private MapDraw mapDraw;
    private ComponentMapper<ID> idComponentMapper;
    private int holdPositionNumber;
    private World world;
    private final  Array<InputKeyCombo> playerControlFunctions= new Array<>();
    public ZRPGPlayerFunctions(ZRPGPlayer player, MapDraw mapDraw) {
        this.player = player;
        this.mapDraw = mapDraw;
        idComponentMapper= GameComponentMapper.getIdComponentMapper();
        this.world=mapDraw.getWorld();
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

    }
  private   KeyAction stop= new KeyAction() {
        @Override
        public void act() {
            if (player.getMovableComponent().getCurrentSpeed()>0 ) {
                player.getPlayerEntity().remove(MovingOnGroundComponent.class);
                player.getPlayerEntity().remove(Moved.class);
                player.getActionComponent().setStat("rest");
                player.getMovableComponent().setMoved(false);
                player.getMovableComponent().setCurrentSpeed(0);
            }
        }
    };

    private  KeyAction moveRight =new KeyAction(){
          @Override
          public void act()  {
                  player.getPlayerEntity().add(new MovingOnGroundComponent());
                  player.getActionComponent().setStat("move");
                  player.getMovableComponent().moveRight();
              }
          
      };
    
    private KeyAction moveLeft =new KeyAction(){
        @Override
          public void act()  {
        player.getPlayerEntity().add(new MovingOnGroundComponent());
        player.getActionComponent().setStat("move");
        player.getMovableComponent().moveLeft();
    }};
    private KeyAction moveUp =new KeyAction(){
        @Override
          public void act()  {
        player.getPlayerEntity().add(new MovingOnGroundComponent());
        player.getActionComponent().setStat("move");
        player.getMovableComponent().moveUp();
    }};
    private KeyAction moveDown =new KeyAction(){
        @Override
          public void act()  {
        player.getPlayerEntity().add(new MovingOnGroundComponent());
        player.getActionComponent().setStat("move");
        player.getMovableComponent().moveDown();
    }};
    private KeyAction moveRightUp =new KeyAction(){
        @Override
          public void act()  {
        player.getPlayerEntity().add(new MovingOnGroundComponent());
        player.getActionComponent().setStat("move");
        player.getMovableComponent().moveRightUp();
    }};
    private KeyAction moveLeftUp =new KeyAction(){
        @Override
          public void act()  {
        player.getPlayerEntity().add(new MovingOnGroundComponent());
        player.getActionComponent().setStat("move");
        player.getMovableComponent().moveLeftUp();
    }};
    private KeyAction moveLeftDown =new KeyAction(){
        @Override
          public void act()  {
        player.getPlayerEntity().add(new MovingOnGroundComponent());
        player.getActionComponent().setStat("move");
        player.getMovableComponent().moveLeftDown();
    }};
    private KeyAction moveRightDown =new KeyAction(){
        @Override
          public void act()  {
        player.getPlayerEntity().add(new MovingOnGroundComponent());
        player.getActionComponent().setStat("move");
        player.getMovableComponent().moveRightDown();
    }};
    private KeyAction increaseSpeed =new KeyAction(){
        @Override
        public void act()  {
          NumericStat speed= player.getNumericStats().getNumericStat("speed");
           speed.addValue(1);
        }};
    private KeyAction decreaseSpeed =new KeyAction(){
        @Override
        public void act()  {
            NumericStat speed= player.getNumericStats().getNumericStat("speed");
            speed.subtractValue(1);
        }};

    private KeyAction pickupItem =new KeyAction(){
        @Override
          public void act()  {
        Holder []  holder=player.getHandHolders();
        if (holder[0].getItemToHoldId()== null || holder[1].getItemToHoldId() == null) {
            PositionComponent position = player.getPosition();
            GameMap map = mapDraw.getWorld().getMap(position.getMapId());
            Array<Entity> entities = MapUtilities.getClosestEntities(map, position, map.getTileWidth(), Item.class);
            PickUp  pickUp= new PickUp();
            String handId="";
            DominateHand dominateHand=player.getDominateHand();
            if(dominateHand==DominateHand.RIGHT) {
                if (holder[0].getItemToHoldId()==null){
                   handId=idComponentMapper.get( player.getHand(0)).getId();
                }
            }
            else if (holder[1].getItemToHoldId()==null){
                handId=idComponentMapper.get( player.getHand(1)).getId();
            }
            pickUp.setEntityToPickUpId(handId);
            if (entities.size > 0) {
                if(player.isAutoPickUpFirstItem()){
                    entities.get(0).add(pickUp);
                    return;
                }
                else {
                    String text = "Select an Item to Pick Up";
                    mapDraw.getUiStage().addWindow(new ItemActionWindow(text, "Select An Item", entities, pickUp, mapDraw), ScreenPosition.CENTER);
                }
            }
        }
    }};
    
    private void dropItem() {
        Drop drop = new Drop();
            player.getHand(1).add(drop);
    }

    private void eatItem() {
            Ingest ingest = new Ingest();
            ingest.setIngestorID(player.getId());
            player.getHand(1).add(ingest);
    }
    private void slashItem() {
            Slash slash = new Slash();
            Holder holder=player.getHandHolders()[1];
            String itemToHoledID=holder.getItemToHoldId();
            if(itemToHoledID!=null && !itemToHoledID.isEmpty()) {
                Entity heldItem = world.getEntity(holder.getItemToHoldId());
                    heldItem.add(slash);
                player.getActionComponent().setStat("slash");
            }
    }
    private void thrustItem() {
        Thrust thrust = new Thrust();
        Holder holder=player.getHandHolders()[1];
        String itemToHoledID=holder.getItemToHoldId();
        if(itemToHoledID!=null && !itemToHoledID.isEmpty()) {
            Entity heldItem = world.getEntity(holder.getItemToHoldId());
            heldItem.add(thrust);
            player.getActionComponent().setStat("thrust");
        }
    }
    private void readItem(int hand) {
        Read read = new Read();
        player.getHand(hand).add(read);
    }
    private void shootItem(int hand) {
       Shoot shoot = new Shoot();
        player.getHand(hand).add(shoot);
    }
    private void throwItem(int handNumber) {
        Throw throwV= new Throw();
       Entity hand=player.getHand(handNumber);
       if(hand!=null) {
           hand.add(throwV);
       }
    }
    private void equipItem() {
        EquipItem equipItem= new EquipItem();
        player.getHand(1).add(equipItem);
    }
    private void addItemToPack(int hand){
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
    public Entity getItem(int hand) {
        Holder[] holder = player.getHandHolders();
        String handId;
        if (hand == 1) {
            handId =holder[1].getItemToHoldId();
        } else {
            handId =holder[0].getItemToHoldId();
        }
        return  mapDraw.getWorld().getEntity(handId);
    }
    public Entity getAnyItem(int hand) {
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
    private void changeHoldPosition(int hand) {
        Holder holder = player.getHandHolders()[hand];
        HoldPosition []  holdPosition=HoldPosition.values();
        int holdPositions=holdPosition.length;
        holdPositionNumber++;
        if(holdPositionNumber>=holdPositions){
            holdPositionNumber=0;
        }
        holder.setHoldPosition(holdPosition[holdPositionNumber]);
    }

    public Array<InputKeyCombo> getPlayerControlFunctions() {
        return playerControlFunctions;
    }
}
