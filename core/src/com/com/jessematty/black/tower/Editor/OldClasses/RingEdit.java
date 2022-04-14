package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveFloatField;

public class RingEdit extends ItemEdit {
    private PositiveFloatField power;




    public RingEdit() {

        power= new PositiveFloatField("", skin);
        Label ringPower= new Label("The Rings Power", skin);
        HorizontalGroup ringGroup= new HorizontalGroup();
        ringGroup.addActor(ringPower);
       ringGroup.addActor(power);
       window.addActor(ringGroup);



    }



}
