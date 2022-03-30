package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.ListWindow;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ScrollableItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Labels.NameEditableLabel.Nameable;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Lists.ContextMenu.ContextMenu;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.Maps.GameMap;

/**
 * class for basic list of the items that has basic functionality like edit name add item and remove item
 * @param <T>
 */
public class ListWindow <T extends Nameable>extends MapEditWindow {


    private ItemList<T> itemList;
    /**
     * button to ad items
     */
    private TextButton addButton;
    /**
     * field to enter item name
     */
    private NamedField enterName;
    private ScrollPane tiledMapLayersPane;
    /**
     * the  maximum height of the list to be diplayed at a given time
     */
    private float listHeight=240;
    /**
     * the  list window actions interface
     */
    private Label layerLabel;
    /**
     * the  list window actions interface
     */
    private ListWindowActions listWindowActions;
    /**
     * the  title for the list of items
     */
    private String listTitle;
    private ContextMenu<T> itemContextMenu;
    /**
     * the  array of items
     */
    private Array<T> items= new Array<>();

    public ListWindow(final GameAssets assets, Array<T> items, String title, String listTitle, Skin skin, String style, ListWindowActions listWindowActions ) {
        super(assets,  title, skin, style);
        this.listTitle=listTitle;
        this.listWindowActions=listWindowActions;
       makeWindow();
        
    }
    public void makeWindow(){

        itemList = new ItemList<T>(skin, listTitle, "Name", String.class, true);
        itemList.setItems(items, true);
        itemList.setEditable(true);
        itemList.setSortable(false);
        layerLabel = new Label("Current Layer Layer 0", skin);
        addButton = new TextButton("Add New Layer", skin);
        addButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                listWindowActions.addAction();

            }
        });
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
        HorizontalGroup addLayer= new HorizontalGroup();
        addLayer.addActor(enterName);
        addLayer.addActor(this.addButton);
        addLayer.space(5);
        itemList.setDebug(true);
        bottom().add(addLayer).size(320, addLayer.getPrefHeight());
        setMovable(false);
        setResizable(false);
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
}
