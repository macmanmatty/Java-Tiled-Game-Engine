package com.jessematty.black.tower.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.jessematty.black.tower.Components.Actions.Action;
import com.jessematty.black.tower.Components.Animation.AnimatableComponent;
import com.jessematty.black.tower.Components.Animation.Drawable;
import com.jessematty.black.tower.Components.BodyParts.Body;
import com.jessematty.black.tower.Components.Stats.BooleanStats;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Stats.StringStats;
import com.jessematty.black.tower.GameBaseClasses.Loaders.serialization.Entity.Transient;
import com.jessematty.black.tower.Maps.World;

@Transient

public class ZRPGPlayer implements Component {

    private transient Movable movable;
    private transient Position position;
    private transient NumericStats numericStats;
    private transient BooleanStats booleanStats;
    private transient StringStats stringStats;
    private transient  OwnerComponent ownerComponent;

    private transient  Ears ears;
    private transient  Nose nose;
    private transient Eyes eyes;
    private String id;


    private transient PhysicalObject physicalObject;
    private transient Drawable drawable;
    private transient AnimatableComponent animatable;
    private transient Thrower thrower;
    private transient Entity rightHand;
    private transient Holder []  handHolders= new Holder[2];
    private transient Entity leftHand;
    private transient  Entity leftFoot;
    private transient Entity rightFoot;
    private transient Pack pack;
    private transient Entity playerEntity;
    private transient com.jessematty.black.tower.Components.Actions.Action action;
    private transient AttackMode attackMode;
    private transient com.jessematty.black.tower.Components.BodyParts.Body body;
    private transient  Name name;
    private transient World world;
    private transient com.jessematty.black.tower.Components.Stats.NumericStat speed;
    private boolean showBottomBar;
    private boolean autoPickUpFirstItem;
    private DominateHand dominateHand;
    private boolean autoAddToFirstPack;
    private ZRPGPlayerButtonModes buttonMode;
    private int handToUse;




    public ZRPGPlayer(World world, Entity playerEntity) {
        this.playerEntity = playerEntity;
        this.movable = playerEntity.getComponent(Movable.class);
        this.position = playerEntity.getComponent(Position.class);
        this.numericStats = playerEntity.getComponent(com.jessematty.black.tower.Components.Stats.NumericStats.class);
        this.booleanStats= playerEntity.getComponent(com.jessematty.black.tower.Components.Stats.BooleanStats.class);
        this.stringStats= playerEntity.getComponent(com.jessematty.black.tower.Components.Stats.StringStats.class);
        this.physicalObject = playerEntity.getComponent(PhysicalObject.class);
        this.drawable = playerEntity.getComponent(com.jessematty.black.tower.Components.Animation.Drawable.class);
        this.animatable = playerEntity.getComponent(com.jessematty.black.tower.Components.Animation.AnimatableComponent.class);
        this.action= playerEntity.getComponent(com.jessematty.black.tower.Components.Actions.Action.class);
        this.body= playerEntity.getComponent(com.jessematty.black.tower.Components.BodyParts.Body.class);
        this.ears= playerEntity.getComponent(Ears.class);
        this.eyes= playerEntity.getComponent(Eyes.class);
        this.nose= playerEntity.getComponent(Nose.class);
        this.ownerComponent= playerEntity.getComponent(OwnerComponent.class);
        this.id=playerEntity.getComponent(ID.class).getId();



        //this.rightHand=world.getEntity(body.getBodyParts().get("rightHand"));
//        this.leftHand=world.getEntity(body.getBodyParts().get("lefttHand"));
       // handHolders[1]=rightHand.getComponent(Holder.class);
        //handHolders[0]=leftHand.getComponent(Holder.class);
       // this.rightFoot=world.getEntity(body.getBodyParts().get("rightFoot"));
       // this.leftFoot=world.getEntity(body.getBodyParts().get("leftFoot"));
        this.pack= playerEntity.getComponent(Pack.class);
        this.thrower= playerEntity.getComponent(Thrower.class);
        this.name= playerEntity.getComponent(Name.class);
        this.speed=numericStats.getNumericStat("speed");
        Playable playable= playerEntity.getComponent(Playable.class);
        if(playable==null){
            playable= new Playable();
            playerEntity.add(playable);
        }
        playable.setCurrentPlayer(true);






    }

    public Movable getMovable() {
        return movable;
    }


    public Position getPosition() {
        return position;
    }


    public NumericStats getNumericStats() {
        return numericStats;
    }


    public PhysicalObject getPhysicalObject() {
        return physicalObject;
    }

    public Drawable getDrawable() {
        return drawable;
    }


    public AnimatableComponent getAnimatable() {
        return animatable;
    }


    public Thrower getThrower() {
        return thrower;
    }



    public Pack getPack() {
        return pack;
    }




    public Entity getHand( int number ) {
        if(number==0) {
            return rightHand;

        }

        else{
            return  leftHand;
        }
    }





    public Holder [] getHolders() {
        return handHolders;



    }




    public Entity getPlayerEntity() {
        return playerEntity;
    }


    public Action getAction() {
        return action;
    }


    public AttackMode getAttackMode() {
        return attackMode;
    }


    public BooleanStats getBooleanStats() {
        return booleanStats;
    }

    public StringStats getStringStats() {
        return stringStats;
    }

    public Holder[] getHandHolders() {
        return handHolders;
    }

    public Entity getLeftFoot() {
        return leftFoot;
    }

    public Entity getRightFoot() {
        return rightFoot;
    }

    public Body getBody() {
        return body;
    }

    public StringStat getName() {

        return name;

    }

    public Ears getEars() {
        return ears;
    }

    public Nose getNose() {
        return nose;
    }

    public Eyes getEyes() {
        return eyes;
    }

    public NumericStat getSpeed() {
        return speed;
    }

    public OwnerComponent getOwnerComponent() {
        return ownerComponent;
    }

    public void setOwnerComponent(OwnerComponent ownerComponent) {
        this.ownerComponent = ownerComponent;
    }

    public boolean isShowBottomBar() {
        return showBottomBar;
    }

    public void setShowBottomBar(boolean showBottomBar) {
        this.showBottomBar = showBottomBar;
    }

    public boolean isAutoPickUpFirstItem() {
        return autoPickUpFirstItem;
    }

    public void setAutoPickUpFirstItem(boolean autoPickUpFirstItem) {
        this.autoPickUpFirstItem = autoPickUpFirstItem;
    }

    public DominateHand getDominateHand() {
        return dominateHand;
    }

    public void setDominateHand(DominateHand dominateHand) {
        this.dominateHand = dominateHand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAutoAddToFirstPack() {
        return autoAddToFirstPack;
    }

    public void setAutoAddToFirstPack(boolean autoAddToFirstPack) {
        this.autoAddToFirstPack = autoAddToFirstPack;
    }

    public ZRPGPlayerButtonModes getButtonMode() {
        return buttonMode;
    }

    public void setButtonMode(ZRPGPlayerButtonModes buttonMode) {
        this.buttonMode = buttonMode;
    }

    public int getHandToUse() {
        return handToUse;
    }

    public void setHandToUse(int handToUse) {
        this.handToUse = handToUse;
    }
}

