package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Stats.BooleanStat;

public class BooleanStatGroup extends UpdatableHorizontalGroup {

          private BooleanStat booleanStat;
            private  Label statLabel;

    public BooleanStatGroup(Skin skin, BooleanStat booleanStat) {
        super(skin);

        this.booleanStat = booleanStat;
        statLabel= new Label(booleanStat.getName() + ": " + booleanStat.getFlag(), skin);
        addActor(statLabel);


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





