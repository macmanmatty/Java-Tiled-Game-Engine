package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveFloatField;
import com.jessematty.black.tower.SquareTiles.LandSquareTile;

public class RiverEdit extends BitMaskedThingEdit {

    PositiveFloatField moveSpeed;
    PositiveFloatField depth;
    
    LandSquareTile riverStart;
    LandSquareTile riverEnd;
    VerticalGroup riverGroup= new VerticalGroup();
    CheckBox showRiverGroup;




    public RiverEdit() {
        HorizontalGroup moveSpeedGroup= new HorizontalGroup();
        Label moveSpeedLabel= new Label("The Speed of The River", skin);

        showRiverGroup=new CheckBox("Show River Fields To Edit", skin);

        showRiverGroup.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CheckBox box= (CheckBox) event.getListenerActor();


                if (box.isChecked()==true){
                    riverGroup.setVisible(true);
                }

                else {
                    riverGroup.setVisible(false);

                }

            }
        });


        moveSpeed=new PositiveFloatField("",skin );

        moveSpeedGroup.addActor(moveSpeedLabel);
        moveSpeedGroup.addActor(moveSpeed);

        Label depthLabel= new Label("The Depth of The River", skin);

        HorizontalGroup depthGroup= new HorizontalGroup();

        depth=new PositiveFloatField("",skin );

        depthGroup.addActor(depthLabel);
        depthGroup.addActor(depth);

        Label liquidLabel= new Label("Select the Rivers Liquid", skin);
        riverGroup.addActor(liquidLabel);
        riverGroup.addActor(moveSpeedGroup);
        riverGroup.addActor(depthGroup);
        window.add(showRiverGroup);
        window.add(riverGroup);






    }



}
