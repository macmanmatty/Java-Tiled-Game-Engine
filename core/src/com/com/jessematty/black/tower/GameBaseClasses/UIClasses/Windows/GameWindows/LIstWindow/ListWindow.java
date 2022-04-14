package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.LIstWindow;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ScrollableItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.ContextMenu.ContextMenu;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.Maps.GameMap;

/**
 * class for basic list of the items that has basic functionality like edit name add item and remove item
 * @param <T> the item Class type
 */
public class ListWindow<T> extends MapEditWindow {


    protected ItemList<T> itemList;
    /**
     * button to ad items
     */
    protected TextButton addButton;
    /**
     * field to enter item name
     */
    protected NamedField enterName;
    /**
     * the scroll pane that holds the list of items
     */
    protected ScrollPane tiledMapLayersPane;
    /**
     * the  maximum height of the list to be diplayed at a given time
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
     * the  context menu that appears when clicking on an item
     */
    protected ContextMenu<T> itemContextMenu;
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
    protected boolean showAddButton;

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


  


    public ListWindow(final GameAssets assets, Array<T> items, String itemName, String title, String listTitle, Skin skin, String style ) {
        this(assets, items, itemName,"Name",   title, listTitle, skin, style);

    }
    public ListWindow(final GameAssets assets, Array<T> items, String itemName, String methodName, String title, String listTitle, Skin skin, String style ) {
        super(assets,  title, skin, style);
        this.listTitle=listTitle;
        this.methodName=methodName;
        this.itemName=itemName;
        makeWindow();
        
    }
    public void makeWindow(){
        clearWindow();
        itemList = new ItemList<T>(skin, listTitle, methodName, String.class, true);
        itemList.setItems(items, true);
        itemList.setEditable(listNameEditable);
        itemList.setSortable(false);
        layerLabel = new Label("Current "+itemName+"  :", skin);
        addButton = new TextButton("Add New "+itemName, skin);
        tiledMapLayersPane= new ScrollPane(this.itemList);
        add(layerLabel).height(layerLabel.getPrefHeight());
        row();
        ScrollPane scrollPane= new ScrollableItemList<>(itemList, skin).getScrollPane();
        add(scrollPane).size(300, listHeight);
        tiledMapLayersPane.setTransform(true);
        tiledMapLayersPane.setScrollbarsVisible(true);
        row();
        this.addButton.getLabel().setFontScale(getFontScale());
        enterName= new NamedField("Enter Name", skin, new TextField("", skin));
        enterName.getLabel().setFontScale(getFontScale());
        enterName.getField().setLayoutEnabled(true);
        if(showAddButton==true) {
            HorizontalGroup addLayer = new HorizontalGroup();
            addLayer.addActor(enterName);
            addLayer.addActor(this.addButton);
            addLayer.space(5);
            itemList.setDebug(true);
            bottom().add(addLayer).size(320, addLayer.getPrefHeight());
        }
            setMovable(false);
            setResizable(false);
    }

    @Override
    public void act(float delta){
        super.act(delta);

    }
    

    @Override
    public void setMap(GameMap gameMap) {
        
    }

    public ContextMenu<T> getItemContextMenu() {
        return itemContextMenu;
    }

    public void setItemContextMenu(ContextMenu<T> itemContextMenu) {
        this.itemContextMenu = itemContextMenu;
    }

    public ItemList<T> getItemList() {
        return itemList;
    }

    public void setItemList(ItemList<T> itemList) {
        this.itemList = itemList;

    }

    public boolean isShowAddButton() {
        return showAddButton;
    }

    public void setShowAddButton(boolean showAddButton) {
        this.showAddButton = showAddButton;
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

    public OnSelected<T> getOnSelected() {
        return itemList.getOnSelected();
    }

    public void setOnSelected(OnSelected<T> onSelected) {
        this.itemList.setOnSelected(onSelected);
    }





}
