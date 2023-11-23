package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.LIstWindow;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ScrollableItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindow;
/**
 * class for basic list of the items that has basic functionality like edit name add item and remove item
 * @param <T> the item Class type
 */
public class ListWindow<T> extends GameWindow {
    protected ItemList<T> itemList;
    /**
     * button to add items
     */
    protected TextButton addButton;

    /**
     * button to remove items
     */
    protected TextButton removeButton;
    /**
     * field to enter item name
     */
    protected NamedField enterName;
    /**
     * the scroll pane that holds the list of items
     */
    protected ScrollPane itemsPane;
    /**
     * the  maximum height of the list to be displayed at a given time
     */
    protected float listHeight=240;
    /**
     * the  current item
     */
    protected Label layerLabel;
    /**
     * the  title for the list of items
     */
    protected String listTitle;
    /**
     * the  array of items
     */
    private  Array<T> items= new Array<>();
    /**
     * the  name of the method to get the displayedName minus the word get  used format is "get"+methodName
     */
    protected String methodName="Name";
    /**
     * whether or not show the add button at the bottom of the list
     */
    protected boolean showAddAndRemoveButtons;
    /**
     * whether or not the displayed names in the list are editable
     */
    protected boolean listNameEditable;
    /**
     * whether or not the displayed names in the list are sortable
     */
    protected boolean listNameSortable;
    /**
     *  The String name of the kind of  objects in the list
     */
    protected String itemName="Object";
    /**
     * The text that is displayed  if there are no items in the list of items;
     */
    protected String notItemsText;

    /**
     * Button to click that performs an action with the selected item when clicked
     */
    protected Button selectButton;

    /**
     *  the action to be performed when the select button is clicked
     */
    protected OnSelected<T> onSelected;

    /**
     * whether or not to show the select button
     */
    protected boolean showSelectButton=true;

    /**
     * whether or not to close the window on select;
     */
    protected boolean closeOnSelect;

    /**
     *
     * @param assets
     * @param items
     * @param itemName
     * @param title
     * @param listTitle
     * @param skin
     * @param style
     */
  
    public ListWindow(final GameAssets assets, Array<T> items, String itemName, String title, String listTitle, Skin skin, String style ) {
        this(assets, items, itemName,"Name",   title, listTitle, skin, style);
    }
    public ListWindow(final GameAssets assets, Array<T> items, String itemName, String methodName, String title, String listTitle, Skin skin, String style ) {
        super( title, skin, style, assets);
        this.listTitle=listTitle;
        this.methodName=methodName;
        this.itemName=itemName;
        addButton = new TextButton("Add New " + itemName, skin);
        removeButton = new TextButton("Remove " + itemName, skin);
        selectButton= new TextButton("select "+itemName, skin);
        selectButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onSelected.onSelected(itemList.getSelectedItem());
                if(closeOnSelect){
                    close();
                }
                return  true;
            }
        });

        makeWindow();
        
    }
    public void makeWindow(){
        if(items.size>0){
            itemList = new ItemList<T>(getSkin(), listTitle, methodName, String.class, true);
            itemList.setItems(items, true);
            itemList.setEditable(listNameEditable);
            itemList.setSortable(false);
            layerLabel = new Label("Current " + itemName + "  :", getSkin());

            itemsPane = new ScrollPane(this.itemList);
            add(layerLabel).height(layerLabel.getPrefHeight());
            row();
            ScrollPane scrollPane = new ScrollableItemList<>(itemList, getSkin()).getScrollPane();
            add(scrollPane).size(300, listHeight);
            itemsPane.setTransform(true);
            itemsPane.setScrollbarsVisible(true);
            row();
            this.addButton.getLabel().setFontScale(1);
            enterName = new NamedField("Enter Name", getSkin(), new TextField("", getSkin()));
            enterName.getLabel().setFontScale(1);
            enterName.getField().setLayoutEnabled(true);
            if (showAddAndRemoveButtons == true) {
                HorizontalGroup addLayer = new HorizontalGroup();
                addLayer.addActor(enterName);
                addLayer.addActor(this.addButton);
                addLayer.addActor(this.removeButton);
                addLayer.space(5);
                itemList.setDebug(true);
                bottom().add(addLayer).size(320, addLayer.getPrefHeight());
            }
            if (showSelectButton == true) {
                HorizontalGroup addLayer = new HorizontalGroup();
                addLayer.addActor(enterName);
                addLayer.addActor(this.addButton);
                addLayer.addActor(this.removeButton);
                addLayer.space(5);
                itemList.setDebug(true);
                bottom().add(addLayer).size(320, addLayer.getPrefHeight());
            }
        }
        else{
            Label label= new Label(notItemsText, getSkin());
            add(label);
        }
        
            setMovable(false);
            setResizable(false);
    }
    @Override
    public void act(float delta){
        super.act(delta);
    }
    
    public ItemList<T> getItemList() {
        return itemList;
    }
    public void setItemList(ItemList<T> itemList) {
        this.itemList = itemList;
    }
    public boolean isShowAddButton() {
        return showAddAndRemoveButtons;
    }
    public void setShowAddButton(boolean showAddButton) {
        this.showAddAndRemoveButtons = showAddButton;
        makeWindow();
    }
    public boolean isListNameEditable() {
        return listNameEditable;
    }
    public void setListNameEditable(boolean listNameEditable) {
        this.listNameEditable = listNameEditable;
        makeWindow();
    }
    public boolean isListNameSortable() {
        return listNameSortable;
    }
    public void setListNameSortable(boolean listNameSortable) {
        this.listNameSortable = listNameSortable;
        makeWindow();
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
        makeWindow();
    }
    public float getListHeight() {
        return listHeight;
    }
    public void setListHeight(float listHeight) {
        this.listHeight = listHeight;
        makeWindow();
    }
    public String getListTitle() {
        return listTitle;
    }
    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
        makeWindow();
    }
    public Array<T> getItems() {
        return items;
    }
    public void setItems(Array<T> items) {
        this.items = items;
        makeWindow();
    }
    public String getNotItemsText() {
        return notItemsText;
    }
    public void setNotItemsText(String notItemsText) {
        this.notItemsText = notItemsText;
    }
    public  T getSelectedItem(){
        return  itemList.getSelectedItem();
    }
    public  int getSelectedItemIndex(){
        return  itemList.getSelectedIndex();
    }

    public boolean isShowSelectButton() {
        return showSelectButton;
    }

    public void setShowSelectButton(boolean showSelectButton) {
        this.showSelectButton = showSelectButton;
    }

    public boolean isCloseOnSelect() {
        return closeOnSelect;
    }

    public void setCloseOnSelect(boolean closeOnSelect) {
        this.closeOnSelect = closeOnSelect;
    }

    public boolean isShowAddAndRemoveButtons() {
        return showAddAndRemoveButtons;
    }

    public void setShowAddAndRemoveButtons(boolean showAddAndRemoveButtons) {
        this.showAddAndRemoveButtons = showAddAndRemoveButtons;
    }

    public OnSelected<T> getOnSelected() {
        return onSelected;
    }

    public void setOnSelected(OnSelected<T> onSelected) {
        this.onSelected = onSelected;
    }


}
