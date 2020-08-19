package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;

public class AddCurseEdit extends ItemActionEdit {


    public AddCurseEdit(GameAssets assetts, Skin skin) {
        super(assetts, skin);

        Label curseLabel= new Label("Select WoodWand Curse To Add", skin);
        window.add(curseLabel);

    }

}
