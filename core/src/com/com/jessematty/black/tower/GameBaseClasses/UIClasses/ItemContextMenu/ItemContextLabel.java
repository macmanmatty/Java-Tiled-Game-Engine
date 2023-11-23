package com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemContextMenu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemListAdapter;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemListLabelStyle;

/**
 *  class for  label that runs into a TextField on double clicking to allow you to edit the contents the 
 *  label returns after hotting enter in the text field.
 *  uses reflection to call a method to get the and set the data to a linked item.
 * @param <T>
 */
public class ItemContextLabel<T >  extends Label {
    /**
     * the linked item
     */
    private T item;

    /**
     * is this the selected  label in the liked item list
     */
    private boolean selected;
    /**
     *  the adapter for the linked ItemList
     * @see ItemList
     */
    private ItemListAdapter<T> itemList;
    /**
     * the position of  the item in the linked ItemLists Array
     */
    private  int index;
    /**
     * the counter in milliseconds  to reset the click counter after 2 seconds the click counter will reset to zero
     */
    private int clickResetCounter;
    /**
     * is the label editable aka will the text appear on double clicking
     */
    private boolean editable;
    /**
     * the method name to call to get the data
     */
    private String methodName="";
    /**
     * the  java Class of the data type supported types are
     * String.class
     * Long.class
     * Integer.class
     * Double.class
     * Float.class
     * method is the format of get+MethodName and set+MethodName
     * you need ony the part of the  method  name after the get and set portions
     */
    private Class itemDataClass=String.class;
    /**
     *  the custom libGDX Scene 2d style for this  UI class
     */
    private ItemListLabelStyle itemListLabelStyle;



    /**
     *
     * @param itemList
     * @param index
     * @param itemListLabelStyle
     * @param item
     * @param editable
     * @param methodName
     * @param itemDataClass
     */
    public ItemContextLabel(final ItemListAdapter <T> itemList, final int index, ItemListLabelStyle itemListLabelStyle, final T item, final boolean editable, String methodName, Class itemDataClass) {
        super("", itemListLabelStyle.labelStyle);
        this.itemList = itemList;
        this.index = index;
        this.editable = editable;
        this.methodName = methodName;
        this.itemDataClass = itemDataClass;
        this.itemListLabelStyle=itemListLabelStyle;
        setLayoutEnabled(true);
        this.item=item;
        if(item!=null){
        }
        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer,  int button ) {
               return  true;
            }
        });
        setBounds(getX(),getY() ,getPrefWidth() ,getPrefHeight() );
    }

    /**
     * 
     * @return the linked item
     */
    public T getItem() {
        return item;
    }
    /**
     * 
     * @param item sets the linked item
     * @throws IllegalArgumentException if the item is null
     */
    public void setItem(T item) throws IllegalArgumentException {
        if(item==null){
            throw new IllegalArgumentException("Item Cannot Be Null");
        }
        this.item = item;
    }
    /**
     * overridden act method  used to swap TextField and Label actors  and count clicks
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);
    }
    /**
     * overidden methods to size the TextField the same as the label
     *
     */
    

    public boolean isSelected() {
        return selected;
    }
    /**
     * sets whether or not this Label is the selected one  in the list
     * and if so sets it style to the style to the selected style.
     * @param selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        if(selected==true){
            setStyle(itemListLabelStyle.selectedLabelStyle);
        }
        else{
            setStyle(itemListLabelStyle.labelStyle);
        }
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
   private ItemContextLabel<T> getLabel(){
        return this;
    }
    public boolean isEditable() {
        return editable;
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
    }


}
