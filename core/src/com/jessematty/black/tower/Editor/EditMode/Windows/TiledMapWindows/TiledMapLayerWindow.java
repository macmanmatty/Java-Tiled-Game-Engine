package com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ScrollableItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;

public class TiledMapLayerWindow extends MapEditWindow {

    private TiledMapTileLayerList mapLayers;
    private TextButton addLayerButton;
    private NamedField enterName;
    private ScrollPane tiledMapLayersPane;
    private float listHeight=240;
    private Label layerLabel;



    public TiledMapLayerWindow(final MapEditScreen mapEditScreen, Skin skin) {
        this(mapEditScreen, "Map Layers", skin, "default");


    }


    public TiledMapLayerWindow(final MapEditScreen mapEditScreen, String title, Skin skin, String style) {
        super(mapEditScreen, title, skin, style);
        mapLayers= new TiledMapTileLayerList( mapEditScreen,skin, "Map Layers", "DisplayName", String.class);
        mapLayers.setEditable(true);
        mapLayers.setSortable(false);
        layerLabel = new Label("Current Layer Layer 0", skin);


        addLayerButton = new TextButton("Add New Layer", skin);
        addLayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String name=enterName.getField().getText();
                if(name!=null  && !(name.isEmpty())){

                    getMapEditScreen().getTiledMapEdit().addLayer(name);
                }
                else {
                    getMapEditScreen().getTiledMapEdit().addLayer();
                }



            }
        });
        tiledMapLayersPane= new ScrollPane(this.mapLayers);

        add(layerLabel).height(layerLabel.getPrefHeight());
        row();
        ScrollPane scrollPane= new ScrollableItemList<>(mapLayers, skin).getScrollPane();


        add(scrollPane).size(300, listHeight);
        tiledMapLayersPane.setTransform(true);
        tiledMapLayersPane.setScrollbarsVisible(true);

        row();
        this.addLayerButton.getLabel().setFontScale(getFontScale());

        enterName= new NamedField("Enter Name", skin, new TextField("", skin));
        enterName.getLabel().setFontScale(getFontScale());
        enterName.getField().setLayoutEnabled(true);

        HorizontalGroup addLayer= new HorizontalGroup();

        addLayer.addActor(enterName);
        addLayer.addActor(this.addLayerButton);
        addLayer.space(5);
        mapLayers.setDebug(true);




        bottom().add(addLayer).size(320, addLayer.getPrefHeight());

        setMovable(false);
        setResizable(false);







    }











    public void makeWindow(){


    }



}
