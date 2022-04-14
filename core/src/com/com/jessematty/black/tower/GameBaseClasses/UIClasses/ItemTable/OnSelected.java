package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable;
/**
 * functional generic  interface method that called when an item is selected by item or index with an ItemList
 *
 *
 */
public interface OnSelected<T> {

    /**
     *  callback on selected method to perform an action when an Item is selected
     * @param item the selected item from the ItemList
     * @see com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemList
     * */
    public void  onSelected(T item);

}
