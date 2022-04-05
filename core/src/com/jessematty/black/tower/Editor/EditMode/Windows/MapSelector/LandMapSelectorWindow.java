package com.jessematty.black.tower.Editor.EditMode.Windows.MapSelector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.EditorSettable;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.OptionPaneWindows.CreateMapOptionPane;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ScrollableItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.ContextMenu.ContextMenu;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.IntegerField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameAction;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Maps.WorldSettable;

/**
 * class for basic list of the items that has basic functionality like edit name add item and remove item
 */
public class LandMapSelectorWindow extends MapEditWindow implements WorldSettable{

    /**
     * the games world object
     */
    protected  World world;

    protected ItemList<LandMap> itemList;
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
    protected ContextMenu<LandMap> itemContextMenu;

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


    public LandMapSelectorWindow(final GameAssets assets,  Skin skin, String style ) {
        super(assets,  "World Maps", skin, style);
        this.listTitle="World Maps";
        this.methodName="MapName";
        this.itemName="Map";

        makeWindow();
        
    }
    public void makeWindow(){
        clearWindow();

        itemList = new ItemList<LandMap>(skin, listTitle, methodName, String.class, true);
        itemList.setEditable(listNameEditable);
        itemList.setSortable(false);
        layerLabel = new Label("Current "+itemName+"  :", skin);
        addButton = new TextButton("Add New "+itemName, skin);

        addButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                CreateMapOptionPane createMapOptionPane= new CreateMapOptionPane(getGameAssets(), getSkin(), world);
                createMapOptionPane.makeWindow();
                createMapOptionPane.setPosition(ScreenPosition.CENTER.getX(), ScreenPosition.CENTER.getY());
                getStage().addActor(createMapOptionPane);

            }
        });
        if(world!=null) {
            itemList.setItems(world.getWorldMap().values().toArray(), true);
        }
        tiledMapLayersPane= new ScrollPane(this.itemList);
        itemList.setOnSelected(new OnSelected<LandMap>() {
            @Override
            public void onSelected(LandMap landMap) {
                mapEditScreen.setMap(landMap);
            }
        });
        add(layerLabel).height(layerLabel.getPrefHeight());
        row();
        ScrollPane scrollPane= new ScrollableItemList<>(itemList, skin).getScrollPane();
        add(scrollPane).size(300, listHeight);
        tiledMapLayersPane.setTransform(true);
        tiledMapLayersPane.setScrollbarsVisible(true);
        row();
        this.addButton.getLabel().setFontScale(getFontScale());
        enterName= new NamedField("Enter Map Name", skin, new TextField("", skin));
        enterName.getLabel().setFontScale(getFontScale());
        enterName.getField().setLayoutEnabled(true);
            HorizontalGroup addLayer = new HorizontalGroup();
            addLayer.addActor(enterName);
            addLayer.addActor(this.addButton);
            addLayer.space(5);
            itemList.setDebug(true);
            bottom().add(addLayer).size(320, addLayer.getPrefHeight());

            setMovable(false);
            setResizable(false);
    }



    @Override
    public void act(float delta){
        Array<LandMap> maps=getItems();
        if( maps!=null && maps.size!=world.getWorldMap().size){
            setItems(world.getWorldMap().values().toArray());

        }
        super.act(delta);


    }
    

    @Override
    public void setMap(GameMap gameMap) {
        
    }

    public ContextMenu<LandMap> getItemContextMenu() {
        return itemContextMenu;
    }

    public void setItemContextMenu(ContextMenu<LandMap> itemContextMenu) {
        this.itemContextMenu = itemContextMenu;
    }

    public ItemList<LandMap> getItemList() {
        return itemList;
    }

    public void setItemList(ItemList<LandMap> itemList) {
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

    public Array<LandMap> getItems() {
        return this.itemList.getItems();
    }

    public void setItems(Array<LandMap> items) {
        this.itemList.setItems(items, true);
        makeWindow();

    }

    @Override
    public void setWorld(World world) {
        this.world=world;
        setItems(world.getWorldMap().values().toArray());

    }

    @Override
    public World getWorld() {
        return world;
    }



}
