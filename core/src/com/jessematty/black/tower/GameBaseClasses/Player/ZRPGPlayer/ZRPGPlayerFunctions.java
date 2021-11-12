package com.jessematty.black.tower.GameBaseClasses.Player.ZRPGPlayer;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.AddItemToPackComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.Drop;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.MovingOnGround;
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
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
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
    }


        public void moveRight() {
        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");
        player.getMovableComponent().moveRight();

    }
    public void moveLeft() {

        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");


        player.getMovableComponent().moveLeft();
        System.out.println("moved left!!");


    }
    public void moveUp() {
        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");


        player.getMovableComponent().moveUp();
        System.out.println("moved up!!");





    }
    public void moveDown() {
        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");
        System.out.println("moved down!!");



        player.getMovableComponent().moveDown();




    }
    public void moveRightUp() {

        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");

        player.getMovableComponent().moveRightUp();
        System.out.println("moved right up!!");





    }
    public void moveLeftUp() {
        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");
        System.out.println("moved left up !!");



        player.getMovableComponent().moveLeftUp();



    }
    public void moveLeftDown() {

        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");


        player.getMovableComponent().moveLeftDown();
        System.out.println("moved left down!!");




    }
    public void moveRightDown() {

        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");


        player.getMovableComponent().moveRightDown();
        System.out.println("moved Right down !!");





    }



    public void pickupItem() {
        Holder []  holder=player.getHandHolders();

        if (holder[0].getItemToHoldId()== null || holder[1].getItemToHoldId() == null) {

            PositionComponent position = player.getPosition();
            GameMap map = mapDraw.getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationX());

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
                    mapDraw.addWindow(new ItemActionWindow(text, "Select An Item", entities, pickUp, mapDraw), ScreenPosition.CENTER);
                }


            }


        }

    }

    public void dropItem(int hand) {
        Drop drop = new Drop();
            player.getHand(hand).add(drop);


    }

    public void eatItem(int hand) {
            Ingest ingest = new Ingest();
            ingest.setIngestorID(player.getId());
            player.getHand(hand).add(ingest);
    }

    public void slashItem(int hand) {
            Slash slash = new Slash();
            Holder holder=player.getHandHolders()[ hand];
            String itemToHoledID=holder.getItemToHoldId();
            if(itemToHoledID!=null && !itemToHoledID.isEmpty()) {
                Entity heldItem = world.getEntity(holder.getItemToHoldId());
                    heldItem.add(slash);
                player.getAction().setStat("slash");
            }

    }

    public void thrustItem(int hand) {
        Thrust thrust = new Thrust();
        Holder holder=player.getHandHolders()[ hand];
        String itemToHoledID=holder.getItemToHoldId();
        if(itemToHoledID!=null && !itemToHoledID.isEmpty()) {
            Entity heldItem = world.getEntity(holder.getItemToHoldId());
            heldItem.add(thrust);
            player.getAction().setStat("thrust");
        }



    }


    public void readItem(int hand) {
        Read read = new Read();
        player.getHand(hand).add(read);

    }


    public void shootItem(int hand) {
       Shoot shoot = new Shoot();
        player.getHand(hand).add(shoot);
    }


    public void throwItem(int handNumber) {
        Throw throwV= new Throw();
       Entity hand=player.getHand(handNumber);
       if(hand!=null) {
           hand.add(throwV);
       }

    }



    public void equipItem(int hand) {
        EquipItem equipItem= new EquipItem();
        player.getHand(hand).add(equipItem);
    }

    public void addItemToPack(int hand){
        Holder []  holder=player.getHandHolders();

        if (holder[0].getItemToHoldId()== null || holder[1].getItemToHoldId() == null) {

            PositionComponent position = player.getPosition();
            GameMap map = mapDraw.getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationX());

            Array<Entity> packs = EntityUtilities.getOwnedEntitiesWithComponents(mapDraw.getWorld(), player.getPlayerEntity(), Pack.class);
            AddItemToPackComponent addItemToPackComponent= new AddItemToPackComponent();

            addItemToPackComponent.setItemToAddId(idComponentMapper.get(player.getHand(hand)).getId());


            if (packs.size > 0) {

                String text="Select A Pack To Add To";
                    mapDraw.addWindow(new ItemActionWindow(text, "Select An Item", packs, addItemToPackComponent, mapDraw), ScreenPosition.CENTER);

            }


        }

    }

    public void target(float screenX, float screenY){



    }


    public void changeHandNumber() {
        if(player.getHandToUse()==1){
            player.setHandToUse(0);
        }
        else{

            player.setHandToUse(1);
        }

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


    public void changeHoldPosition(int hand) {
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
