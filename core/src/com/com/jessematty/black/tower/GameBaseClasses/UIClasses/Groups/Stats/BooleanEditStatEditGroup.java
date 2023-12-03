package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups.Stats;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Stats.BooleanStat;

public class BooleanEditStatEditGroup extends EditStatGroup {

          private BooleanStat booleanStat;
            private  Label statLabel;
            private boolean isEditable;
            private CheckBox value;
            public BooleanEditStatEditGroup(Skin skin, BooleanStat booleanStat) {
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





