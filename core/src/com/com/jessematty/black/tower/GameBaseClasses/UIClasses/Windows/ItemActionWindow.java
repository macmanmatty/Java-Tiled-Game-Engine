package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Components.Actions.ActionComponentMarkers.ItemActionComponent;
import com.jessematty.black.tower.Components.Animation.ImageComponent;
import com.jessematty.black.tower.Components.NameComponent;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.Maps.World;

public class ItemActionWindow extends GameWindow {

    private Array<Entity> entities= new Array<>();
    private ItemActionComponent itemActionComponent;
    private World world;
    private String text;



    public ItemActionWindow(String text, String title, Array<Entity> entities, ItemActionComponent itemActionComponent, MapDraw draw) {
        super(title, draw.getCurrentMap().getSkin(), draw.getGameAssets() );
        this.entities = entities;
        this.itemActionComponent = itemActionComponent;
        this.world = world;
        makeWindow();

    }

    public void makeWindow(){
        Skin skin=getSkin();
        Label label= new Label(text, skin);
        add(label);
        ComponentMapper<ImageComponent> imageComponentComponentMapper=world.getGameComponentMapper().getImageComponentMapper();
        ComponentMapper<NameComponent> nameComponentMapper=world.getGameComponentMapper().getNameComponentMapper();
        int size=entities.size;
        for(int count=0; count<size; count++) {
            final Entity entity=entities.get(count);
            HorizontalGroup horizontalGroup = new HorizontalGroup();
            NameComponent nameComponent =nameComponentMapper.get(entity);
            ImageComponent imageComponent=imageComponentComponentMapper.get(entity);
            String itemName= nameComponent.getStat();
            Label nameLabel= new Label(itemName, skin);
            horizontalGroup.addActor(nameLabel);
            Image image= imageComponent.getImage();
            horizontalGroup.addActor(image);
            TextButton button= new TextButton("select Item", skin);
            button.addListener(new EventListener() {
                @Override
                public boolean handle(Event event) {
                    entity.add(itemActionComponent);

                    return true;
                }
            });

            horizontalGroup.addActor(button);
            add(horizontalGroup);


        }


    }
}
