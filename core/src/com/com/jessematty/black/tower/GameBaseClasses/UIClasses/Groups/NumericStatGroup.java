package com.jessematty.black.tower.GameBaseClasses.UIClasses.Groups;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.StatBar;

/**
 * group for displaying a numeric stat
 */
public class NumericStatGroup extends HorizontalGroup {
    /**
     * the groups numeric stat;
     */
    NumericStat numericStat;
    /**
     * libgdx Skin;
     */
    Skin skin;
    GameAssets gameAssets;
    public NumericStatGroup(GameAssets assets, NumericStat numericStat, Skin skin) {
        this.numericStat = numericStat;
        this.skin = skin;
        this.gameAssets=assets;
        make();
    }

    public void make(){
            Label statLabel = new Label(numericStat.getName() + ": " + numericStat.getDoubleValue() + "/" + numericStat.getMaxValue(), skin);
            addActor(statLabel);
            if(numericStat.isHasStatBar()) {
                StatBar statBar = numericStat.createStatBar(gameAssets);
                statBar.setSize(110,100);
                addActor(statBar);
                space(10);
            }
        }
}
