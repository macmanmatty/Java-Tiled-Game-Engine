package com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.BottomBars;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponents;
import com.jessematty.black.tower.Components.Pack;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.UIWindow;
import com.jessematty.black.tower.Maps.GameMap;
public class DefaultRPGBottomWindow extends UIWindow {
    private Window bottomBar;
    private HandGroup leftHandGroup;
    private HandGroup rightHandGroup;
   private Entity leftHandItem;
   private Entity rightHandItem;
   private ComponentMapper<ActionComponents> actionComponentsComponentMapper;
    private Skin skin;

    private GameComponentMapper gameComponentMapper;


    public DefaultRPGBottomWindow(String title, Skin skin, MapDraw mapDraw) {
        super(title, skin, mapDraw);
    }

    public DefaultRPGBottomWindow(String title, Skin skin, String styleName, MapDraw mapDraw) {
        super(title, skin, styleName, mapDraw);
    }

    public void makeUI() {
            bottomBar = new Window("Bottom Bar", skin);
            bottomBar.setSize(960, 100);
            bottomBar.setColor(Color.GOLD);
            this.skin = skin;
            bottomBar.add(leftHandGroup.getHorizontalGroup(), rightHandGroup.getHorizontalGroup());

        }
    public void changeMap(GameMap map){
        makeUI();
    }
    public void updateLeftHand(  Skin skin, Entity heldItem){
        leftHandGroup.update(skin, heldItem);
      
    }
    public void updateRightHand(  Skin skin, Entity heldItem){
        rightHandGroup.update(skin, heldItem);
    }
    public void updatePackButtons(Array<Pack> packs){
    }
    public Actor getBar(){
                return bottomBar;
    }

    @Override
    public void update() {

    }
}
