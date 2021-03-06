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

public class HandGroup extends HorizontalGroup {

    private Skin skin;
    private GameComponentMapper gameComponentMapper;
    private MapDraw draw;
    private float fontScale =.6f;


    public HandGroup(MapDraw draw) {
        this.draw=draw;
        this.gameComponentMapper=draw.getGameComponentMapper();
        this.skin=draw.getCurrentMap().getSkin();

    }

    public   void update(Skin skin, Entity heldItem){
      clear();
       this.skin = skin;
       if(heldItem!=null) {
           ComponentMapper<Name> nameComponentMapper = gameComponentMapper.getNameComponentMapper();
           ComponentMapper<ActionComponents> actionComponentsComponentMapper = gameComponentMapper.getActionComponentsComponentMapper();
           ComponentMapper<ImageComponent> imageComponentComponentMapper = gameComponentMapper.getImageComponentMapper();
           String itemName = nameComponentMapper.get(heldItem).getStat();
           Label nameLabel = new Label(" Hand: " + itemName, skin);
           nameLabel.setFontScale(fontScale);
           addActor(nameLabel);
           Image image = imageComponentComponentMapper.get(heldItem).getImage();
           if (image != null){
               addActor(image);
       }

           Array<ActionComponent> actionComponents=actionComponentsComponentMapper.get(heldItem).getActionComponents();
           int size=actionComponents.size;
           for(int count=0; count<size; count++){
               ActionComponent actionComponent=actionComponents.get(count);
               //ActionButton actionButton= new ActionButton(actionComponent, heldItem);
              // addActor(actionButton);

           }

       }

       else{

           Label nameLabel = new Label("Left Hand: " , skin);
          addActor(nameLabel);
           nameLabel.setFontScale(fontScale);


       }

   }

    public float getFontScale() {
        return fontScale;
    }

    public void setFontScale(float fontScale) {
        this.fontScale = fontScale;
    }
}
