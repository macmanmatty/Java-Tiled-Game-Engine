package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;

import com.badlogic.gdx.utils.Array;

public interface ItemListAdapter<T> {
    void  setSelected( T item, boolean change);
    void setSelected( int index, boolean change);
    T getSelectedItem();
    Array<T> getItems();
     void forceRemakeTable();
     int getSelectedIndex();



}
