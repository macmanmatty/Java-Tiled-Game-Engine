package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;


public class AbilityEdit extends ObjectEdit {

    Skin skin;
    GameAssets assetts;

    public AbilityEdit(GameAssets assetts, Skin skin) {
        super(skin, assetts);
        this.skin = skin;
        this.assetts = assetts;
    }


}
