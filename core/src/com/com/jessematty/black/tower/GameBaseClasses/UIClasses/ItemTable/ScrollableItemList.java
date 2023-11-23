package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class ScrollableItemList<T>   {
        private ScrollPane scrollPane;
    public ScrollableItemList(ItemList<T> itemList, Skin skin ){
        Table table= new Table();
        table.setWidth(itemList.getWidth());
        table.setHeight(itemList.getHeight());
        table.add(itemList).align(Align.top);
        scrollPane= new ScrollPane(table, skin);
        scrollPane.setScrollbarsVisible(false);
        scrollPane.setForceScroll(false, true);
        scrollPane.setFlickScroll(true);
        scrollPane.setOverscroll(false, false);
        scrollPane.setScrollBarPositions(false, true);
        scrollPane.setSmoothScrolling(true);
        scrollPane.setLayoutEnabled(true);
        scrollPane.setClamp(true);
    }
    public ScrollPane getScrollPane() {
        return scrollPane;
    }
}
