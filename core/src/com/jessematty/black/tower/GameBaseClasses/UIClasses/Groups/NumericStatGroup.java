package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.StatBar;
import com.jessematty.black.tower.GameBaseClasses.Utilities.InList;

public class NumericStatGroup extends UpdatableHorizontalGroup {

          private   NumericStat numericStat;
            private  Label statLabel;
            private StatBar statBar;

    public NumericStatGroup(Skin skin,  NumericStat numericStat) {
        super(skin);

        this.numericStat = numericStat;
        statLabel= new Label(numericStat.getName() + ": " + numericStat.getDoubleValue() + "/" + numericStat.getMaxValue(), skin);
        addActor(statLabel);
         statBar = numericStat.getStatBar();
        if (statBar != null) {
            addActor(statBar);

        }

    }


    @Override
    public void act(float delta){
        super.act(delta);
        statLabel.setText(numericStat.getName() + ": " + numericStat.getDoubleValue() + "/" + numericStat.getMaxValue());
        if(statBar!=null) {
            statBar.update();
        }


            }


    public Label getStatLabel() {
        return statLabel;
    }

    public StatBar getStatBar() {
        return statBar;
    }
}



