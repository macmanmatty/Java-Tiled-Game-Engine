package com.jessematty.black.tower.GameBaseClasses.UIClasses;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Copyable;

public class ObjectBox<T extends Copyable> extends SelectBox {
    public ObjectBox(Skin skin) {
        super(skin);
    }

    public ObjectBox(Skin skin, String styleName) {
        super(skin, styleName);
    }

    public ObjectBox(SelectBoxStyle style) {
        super(style);
    } // list that returns WoodWand  new cloner of the selected item rather than the selected item

}
