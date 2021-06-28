package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class ScrollableItemList<T>  {

        private ScrollPane scrollPane;
    public ScrollableItemList(ItemList<T> itemList, Skin skin ){
        Table table= new Table();
        table.setWidth(itemList.getWidth()+100);
        table.setHeight(itemList.getHeight());
        table.add(itemList).align(Align.center);
        scrollPane= new ScrollPane(table, skin);

        scrollPane.setScrollbarsVisible(false);
        scrollPane.setForceScroll(false, true);
        scrollPane.setFlickScroll(true);
        scrollPane.setOverscroll(false, true);
        scrollPane.setScrollBarPositions(false, true);

    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }
}
