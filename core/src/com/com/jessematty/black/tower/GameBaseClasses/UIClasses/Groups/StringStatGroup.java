package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Stats.StringStat;

/**
 * group for displaying a numeric stat
 */
public class StringStatGroup extends HorizontalGroup {
    /**
     * the groups numeric stat;
     */
    StringStat stringStat;
    /**
     * libgdx Skin;
     */
    Skin skin;
    public StringStatGroup(StringStat stringStat, Skin skin) {
        this.stringStat=stringStat;
        this.skin = skin;
        make();
    }

    public void make(){
                Label statLabel = new Label(stringStat.getName() + ": " + stringStat.getStat(), skin);
                addActor(statLabel);
    }
}
