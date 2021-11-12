package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveFloatField;

public class ItemActionEdit extends ObjectEdit {



    PositiveFloatField time;
    PositiveFloatField amount;
    CheckBox distanceAction;
    CheckBox timedAction;


    SelectBox actions;

    VerticalGroup itemActionGroup;
    HorizontalGroup amountGroup;
    Label statLabel;


    public ItemActionEdit() {
    }

    public ItemActionEdit(GameAssets assetts, Skin skin) {
        super(skin, assetts);
        itemActionGroup= new VerticalGroup();
        time= new PositiveFloatField("", skin);
        amount= new PositiveFloatField("", skin);
        statLabel= new Label("Select WoodWand Stat to Change", skin);

        amountGroup= new HorizontalGroup();
        Label amountLabel = new Label("Amount to Change Stat By ", skin);
        amountGroup.addActor(amountLabel);
        amountGroup.addActor(amount);







        distanceAction= new CheckBox("Distance Action?", skin);

       distanceAction.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();




            }
        });
        timedAction= new CheckBox("Timed Action?", skin);

       timedAction.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();




            }
        });
































    }



}
