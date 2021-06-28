package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Stats.BooleanStat;

public class BooleanStatGroup extends StatGroup {

          private BooleanStat booleanStat;
            private  Label statLabel;
            private boolean isEditable;
            private CheckBox value;
            private CheckBox defaultValue;

    public BooleanStatGroup(Skin skin, BooleanStat booleanStat) {
        super(booleanStat, skin);

        this.booleanStat = booleanStat;
        statLabel= new Label(booleanStat.getName() + ": " + booleanStat.getFlag(), skin);
        addActor(statLabel);


    }

    public void makeGroup(){


    }

    @Override
    public void act(float delta){
        super.act(delta);
        statLabel.setText(booleanStat.getName() + ": " + booleanStat.getFlag());

            }


    public Label getStatLabel() {
        return statLabel;
    }
}





