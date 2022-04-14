package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;

import com.badlogic.gdx.utils.Array;

/**
 * callback method for preforming  an action when the order of a ItemLists Items has changed by dragging them.
 *
 * @param <T>
 */
public interface OnOrderChanged<T> {

public void onOrderChanged(T item,  Array<T>Items);

    void onOrderChanged(Object object);
}
