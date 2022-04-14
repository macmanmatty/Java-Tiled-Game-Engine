package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemContextMenu;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemTable;

public class ItemContextMenu<T> extends ItemList<T> {
    public ItemContextMenu(Skin skin, String titleName, Class itemDataClass) {
        super(skin, titleName, itemDataClass, false);
    }

    public ItemContextMenu(Skin skin, String titleName, String methodName, Class itemDataClass, boolean displayTitle) {
        super(skin, titleName, methodName, itemDataClass, displayTitle);
    }

    public ItemContextMenu(Skin skin, String styleName, String titleName, String methodName, Class itemDataClass, ItemTable itemTable, int tableColumnIndex, boolean displayTitle) {
        super(skin, styleName, titleName, methodName, itemDataClass, itemTable, tableColumnIndex, displayTitle);
    }
}
