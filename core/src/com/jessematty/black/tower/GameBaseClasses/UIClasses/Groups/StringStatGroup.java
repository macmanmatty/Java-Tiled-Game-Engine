package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Stats.StringStat;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class StringStatGroup extends StatGroup {

          private StringStat stringStat;
            private  Label statLabel;
            private TextField textField;

    public StringStatGroup(Skin skin,StringStat stringStat) {
        super(stringStat, skin);

        this.stringStat = stringStat;
        statLabel= new Label(stringStat.getName() + ": " + stringStat.getStat(), skin);
        addActor(statLabel);


    }

@Override
    public void act(float delta){
        super.act(delta);
        statLabel.setText(stringStat.getName() + ": " + stringStat.getStat());

            }

    public Label getStatLabel() {
        return statLabel;
    }
}



