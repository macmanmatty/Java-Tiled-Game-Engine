package com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.BottomBars;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponents;
import com.jessematty.black.tower.Components.Attacks.AttackMode;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.Containers.Pack;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.Other.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.NumericStatGroup;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.StringStatGroup;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Table.UITable;
import com.jessematty.black.tower.Maps.GameMap;

public class DefaultZRPGBottomWindow extends UITable {
    private HandGroup leftHandGroup;
    private HandGroup rightHandGroup;
   private Entity leftHandItem;
   private Entity rightHandItem;
   private ComponentMapper<ActionComponents> actionComponentsComponentMapper;
   private AttackMode attackMode;
   private ZRPGCharacter zrpgCharacter;
   private GameComponentMapper gameComponentMapper;
   private MapDraw mapDraw;



    public DefaultZRPGBottomWindow( Skin skin, String backgroundName,  MapDraw mapDraw) {
        super( skin, backgroundName);
        this.mapDraw=mapDraw;
        leftHandGroup= new HandGroup(mapDraw);
        rightHandGroup=new HandGroup(mapDraw);

    }



    public void makeUI() {
           add(leftHandGroup, rightHandGroup);



    }
    public void changeMap(GameMap map){
        makeUI();
    }
    public void updateHands(){

        Holder rightHolder= zrpgCharacter.getHandHolders()[1];
        Holder leftHolder= zrpgCharacter.getHandHolders()[0];
        if(rightHolder!=null) {
            String rightHandHeldEntityID = rightHolder.getItemToHoldId();
            Entity rightHeldItem=mapDraw.getWorld().getEntity(rightHandHeldEntityID);
            rightHandGroup.update(getSkin(), rightHeldItem);

        }
        if(leftHolder!=null) {
            String leftHandHeldEntityID = leftHolder.getItemToHoldId();
            Entity leftHeldItem = mapDraw.getWorld().getEntity(leftHandHeldEntityID);
            leftHandGroup.update(getSkin(), leftHeldItem);
        }

    }
    
    public void updatePackButtons(Array<Pack> packs){
    }

    @Override
    public void act(float delta){
        super.act(delta);
        attackMode= zrpgCharacter.getAttackMode();
        updateHands();



    }


    public ZRPGCharacter getZrpgCharacter() {
        return zrpgCharacter;
    }

    public void setZrpgCharacter(ZRPGCharacter zrpgCharacter) {
        this.zrpgCharacter = zrpgCharacter;
        StringStat name= zrpgCharacter.getNameComponent();
        StringStatGroup stringStatGroup= new StringStatGroup(getSkin(), name);
        StringStat attackMode= zrpgCharacter.getAttackMode();

        NumericStats numericStats= zrpgCharacter.getNumericStats();
        NumericStatGroup numericStatGroup= new NumericStatGroup(getSkin(), numericStats.getNumericStat("health"), false);
        add(stringStatGroup).pad(7.5f);
        row();
        add(numericStatGroup);
        row();
        numericStatGroup.getStatLabel().setFontScale(.6f);
        stringStatGroup.getStatLabel().setFontScale(.6f);


        makeUI();




    }
}
