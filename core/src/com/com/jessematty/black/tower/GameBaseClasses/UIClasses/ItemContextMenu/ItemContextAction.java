package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemContextMenu;

import com.badlogic.gdx.utils.Array;

public interface ItemContextAction<T> {


    public void  act(Array<T> items, T item);
}
