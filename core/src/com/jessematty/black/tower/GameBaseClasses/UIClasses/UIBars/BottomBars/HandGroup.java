package com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.BottomBars;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ActionComponent;
import com.jessematty.black.tower.Components.Actions.ActionComponents;
import com.jessematty.black.tower.Components.ImageComponent;
import com.jessematty.black.tower.Components.Name;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.ActionButton;

public class HandGroup {

    private HorizontalGroup horizontalGroup = new HorizontalGroup();
    private Skin skin;
    private GameComponentMapper gameComponentMapper;
    private MapDraw draw;


    public HandGroup(MapDraw draw) {
        this.draw=draw;
        this.gameComponentMapper=draw.getGameComponentMapper();
        this.skin=draw.getCurrentMap().getSkin();

    }

    public   void update(Skin skin, Entity heldItem){
       horizontalGroup.clear();
       this.skin = skin;
       if(heldItem!=null) {
           ComponentMapper<Name> nameComponentMapper = gameComponentMapper.getNameComponentMapper();
           ComponentMapper<ActionComponents> actionComponentsComponentMapper = gameComponentMapper.getActionComponentsComponentMapper();
           ComponentMapper<ImageComponent> imageComponentComponentMapper = gameComponentMapper.getImageComponentMapper();
           String itemName = nameComponentMapper.get(heldItem).getText();
           Label nameLabel = new Label("Left Hand: " + itemName, skin);
           horizontalGroup.addActor(nameLabel);
           Image image=imageComponentComponentMapper.get(heldItem).getImage();
           horizontalGroup.addActor(image);
           Array<ActionComponent> actionComponents=actionComponentsComponentMapper.get(heldItem).getActionComponents();
           int size=actionComponents.size;
           for(int count=0; count<size; count++){
               ActionComponent actionComponent=actionComponents.get(count);
               ActionButton actionButton= new ActionButton(actionComponent, heldItem);
               horizontalGroup.addActor(actionButton);

           }

       }

       else{

           Label nameLabel = new Label("Left Hand: " , skin);
           horizontalGroup.addActor(nameLabel);

       }

   }

    public HorizontalGroup getHorizontalGroup() {
        return horizontalGroup;
    }
}
