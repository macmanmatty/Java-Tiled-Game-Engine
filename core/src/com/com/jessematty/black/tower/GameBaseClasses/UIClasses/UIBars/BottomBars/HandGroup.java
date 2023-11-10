package com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.BottomBars;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ItemActionImageComponent;
import com.jessematty.black.tower.Components.Item.ItemAction.ItemActionComponents;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.Base.NameComponent;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public class HandGroup extends HorizontalGroup {

    private Skin skin;
    private MapDraw draw;
    private float fontScale =.6f;


    public HandGroup(MapDraw draw) {
        this.draw=draw;
        this.skin=draw.getCurrentMap().getSkin();

    }

    public   void update(Skin skin, Entity heldItem){
      clear();
       this.skin = skin;
       if(heldItem!=null) {
           ComponentMapper<NameComponent> nameComponentMapper = GameComponentMapper.getNameComponentMapper();
           ComponentMapper<ItemActionComponents> actionComponentsComponentMapper = GameComponentMapper.getActionComponentsComponentMapper();
           ComponentMapper<ImageComponent> imageComponentComponentMapper = GameComponentMapper.getImageComponentMapper();
           String itemName = nameComponentMapper.get(heldItem).getStat();
           Label nameLabel = new Label(" Hand: " + itemName, skin);
           nameLabel.setFontScale(fontScale);
           addActor(nameLabel);
           Image image = imageComponentComponentMapper.get(heldItem).getImage();
           if (image != null){
               addActor(image);
       }

           Array<ItemActionImageComponent> actionComponents=actionComponentsComponentMapper.get(heldItem).getActionComponents();
           int size=actionComponents.size;
           for(int count=0; count<size; count++){
               ItemActionImageComponent itemActionImageComponent =actionComponents.get(count);
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
