package com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.BottomBars;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponents;
import com.jessematty.black.tower.Components.AttackMode;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.Components.Stats.NumericStats;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.Components.ZRPGPlayer;
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
   private ZRPGPlayer zrpgPlayer;
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

        Holder rightHolder=zrpgPlayer.getHandHolders()[1];
        Holder leftHolder=zrpgPlayer.getHandHolders()[0];
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
        attackMode=zrpgPlayer.getAttackMode();
        updateHands();



    }


    public ZRPGPlayer getZrpgPlayer() {
        return zrpgPlayer;
    }

    public void setZrpgPlayer(ZRPGPlayer zrpgPlayer) {
        this.zrpgPlayer = zrpgPlayer;
        StringStat name=zrpgPlayer.getNameComponent();
        StringStatGroup stringStatGroup= new StringStatGroup(getSkin(), name);
        StringStat attackMode=zrpgPlayer.getAttackMode();

        NumericStats numericStats=zrpgPlayer.getNumericStats();
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
