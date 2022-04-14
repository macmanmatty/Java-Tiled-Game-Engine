package com.jessematty.black.tower.GameBaseClasses.UIClasses.DropDownMenus;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TitleMenu<T> extends List<T> {

    public TitleMenu(Skin skin) {
        super(skin);
    }

    public TitleMenu(Skin skin, String styleName) {
        super(skin, styleName);
    }

    public TitleMenu(ListStyle style) {
        super(style);
    }
}
