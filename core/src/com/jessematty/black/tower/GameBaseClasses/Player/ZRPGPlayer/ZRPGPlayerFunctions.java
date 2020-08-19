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
import com.jessematty.black.tower.Components.EquipItem;
import com.jessematty.black.tower.Components.Ingest;
import com.jessematty.black.tower.Components.Holder;
import com.jessematty.black.tower.Components.ID;
import com.jessematty.black.tower.Components.Item;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.ZRPGPlayer;
import com.jessematty.black.tower.Components.Position;
import com.jessematty.black.tower.GameBaseClasses.Entity.EntityUtilities;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ItemActionWindow;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.MapUtilities;
import com.jessematty.black.tower.Maps.World;

public class ZRPGPlayerFunctions {


    private ZRPGPlayer player;
    private MapDraw mapDraw;
    private ComponentMapper<ID> idComponentMapper;



    public ZRPGPlayerFunctions(ZRPGPlayer player, MapDraw mapDraw) {
        this.player = player;
        this.mapDraw = mapDraw;
        idComponentMapper=mapDraw.getGameComponentMapper().getIdComponentMapper();
    }


        public void moveRight() {
        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");
        player.getMovable().moveRight();



    }
    public void moveLeft() {

        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");


        player.getMovable().moveLeft();
        System.out.println("moved left!!");





    }
    public void moveUp() {
        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");


        player.getMovable().moveUp();
        System.out.println("moved up!!");





    }
    public void moveDown() {
        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");
        System.out.println("moved down!!");



        player.getMovable().moveDown();




    }
    public void moveRightUp() {

        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");

        player.getMovable().moveRightUp();
        System.out.println("moved right up!!");





    }
    public void moveLeftUp() {
        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");
        System.out.println("moved left up !!");



        player.getMovable().moveLeftUp();



    }
    public void moveLeftDown() {

        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");


        player.getMovable().moveLeftDown();
        System.out.println("moved left down!!");




    }
    public void moveRightDown() {

        player.getPlayerEntity().add(new MovingOnGround());
        player.getAction().setStat("move");


        player.getMovable().moveRightDown();
        System.out.println("moved Right down !!");





    }

    public void pickupItem() {
        Holder []  holder=player.getHandHolders();

        if (holder[0].getItemToHoldId()== null || holder[1].getItemToHoldId() == null) {

            Position position = player.getPosition();
            GameMap map = mapDraw.getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationX());

            Array<Entity> entities = MapUtilities.getClosestEntities(map, position, map.getTileSizeX(), Item.class);
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
                    mapDraw.addWindow(new ItemActionWindow(text, "Select An Item", entities, pickUp, mapDraw.getWorld()), ScreenPosition.CENTER);
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
            ingest.setIngestorid(player.getId());
            player.getHand(hand).add(ingest);
    }

    public void slashItem(int hand) {
            Slash slash = new Slash();
            player.getHand(hand).add(slash);

    }

    public void thrustItem(int hand) {
        Thrust thrust = new Thrust();
        player.getHand(hand).add(thrust);

    }


    public void readItem(int hand) {
        Read read = new Read();
        player.getHand(hand).add(read);

    }


    public void shootItem(int hand) {
       Shoot shoot = new Shoot();
        player.getHand(hand).add(shoot);
    }


    public void throwItem(int hand) {
        Throw throwV= new Throw();
        player.getHand(hand).add(throwV);
    }



    public void equipItem(int hand) {
        EquipItem equipItem= new EquipItem();
        player.getHand(hand).add(equipItem);
    }

    public void addItemToPack(int hand){
        Holder []  holder=player.getHandHolders();

        if (holder[0].getItemToHoldId()== null || holder[1].getItemToHoldId() == null) {

            Position position = player.getPosition();
            GameMap map = mapDraw.getWorld().getMap(position.getMapWorldLocationX(), position.getMapWorldLocationX());

            Array<Entity> packs = EntityUtilities.getOwnedEntitiesWithComponents(mapDraw.getWorld(), player.getPlayerEntity(), Pack.class);
            AddItemToPackComponent addItemToPackComponent= new AddItemToPackComponent();

            addItemToPackComponent.setItemToAddId(idComponentMapper.get(player.getHand(hand)).getId());


            if (packs.size > 0) {

                String text="Select A Pack To Add To";
                    mapDraw.addWindow(new ItemActionWindow(text, "Select An Item", packs, addItemToPackComponent, mapDraw.getWorld()), ScreenPosition.CENTER);



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


}
