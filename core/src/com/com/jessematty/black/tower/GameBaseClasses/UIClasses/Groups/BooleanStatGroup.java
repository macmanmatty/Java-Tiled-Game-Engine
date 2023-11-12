package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.StringStat;

/**
 * group for displaying a numeric stat
 */
public class BooleanStatGroup extends HorizontalGroup {
    /**
     * the groups numeric stat;
     */
    BooleanStat booleanStat;
    /**
     * libgdx Skin;
     */
    Skin skin;
    public BooleanStatGroup(BooleanStat booleanStat, Skin skin) {
        this.booleanStat=booleanStat;
        this.skin = skin;
        make();
    }

    public void make(){
        Label statLabel = new Label(booleanStat.getName() + ": " +  String.valueOf(booleanStat.getFlag()) , skin);
        addActor(statLabel);
    }
}
