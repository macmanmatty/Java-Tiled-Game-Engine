package com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.BottomBars;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.AttachEntity.Holder;
import com.jessematty.black.tower.Components.Attacks.AttackMode;
import com.jessematty.black.tower.Components.Containers.ContainerComponent;
import com.jessematty.black.tower.Components.ZRPG.ZRPGCharacter;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Table.UITable;
import com.jessematty.black.tower.Maps.GameMap;
public class DefaultZRPGBottomWindow extends UITable {
    private HandGroup leftHandGroup;
    private HandGroup rightHandGroup;
   private AttackMode attackMode;
   private ZRPGCharacter zrpgCharacter;
   private MapDraw mapDraw;
    public DefaultZRPGBottomWindow( Skin skin, String backgroundName,  MapDraw mapDraw) {
        super( skin, backgroundName);
        this.mapDraw=mapDraw;
        leftHandGroup= new HandGroup(mapDraw);
        rightHandGroup=new HandGroup(mapDraw);
    }
    public void makeUI() {
        setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()*.2f);
    }
    public void changeMap(GameMap map){
        makeUI();
    }
    public void updateHands(){

    }
    
    public void updatePackButtons(Array<ContainerComponent> packs){
    }
    @Override
    public void act(float delta){
        super.act(delta);

    }
    public ZRPGCharacter getZrpgCharacter() {
        return zrpgCharacter;
    }
    public void setZrpgCharacter(ZRPGCharacter zrpgCharacter) {
        this.zrpgCharacter = zrpgCharacter;

    }
}
