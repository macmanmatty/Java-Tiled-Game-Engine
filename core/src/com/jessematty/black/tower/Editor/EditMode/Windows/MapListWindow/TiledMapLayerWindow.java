package com.jessematty.black.tower.Editor.EditMode.Windows.MapListWindow;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.Editor.Tools.MapTools.TiledMapEdit;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ScrollableItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.Maps.GameMap;

public class TiledMapLayerWindow extends MapEditWindow {
    private TiledMapTileLayerList mapLayers;
    private TextButton addMapButton;
    private NamedField enterName;
    private ScrollPane tiledMapLayersPane;
    private float listHeight=240;
    private Label mapLabel;
    private TiledMapEdit tiledMapEdit;
    public TiledMapLayerWindow(TiledMapEdit tiledMapEdit, GameAssets gameAssets, Skin skin) {
        this(gameAssets,tiledMapEdit, "Map Layers", skin, "default");
    }
    public TiledMapLayerWindow(final GameAssets assets, final TiledMapEdit tiledMapEdit, String title, Skin skin, String style) {
        super(assets,  title, skin, style);
        mapLayers= new TiledMapTileLayerList( tiledMapEdit,skin, "Map Layers", "DisplayName", String.class);
        mapLayers.setEditable(true);
        mapLayers.setSortable(false);
        this.tiledMapEdit=tiledMapEdit;
        mapLabel = new Label("Current Map Map 0, 0", skin);
        addMapButton = new TextButton("Add  New  Map ", skin);
        addMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String name=enterName.getField().getText();
                if(name!=null  && !(name.isEmpty())){
                    tiledMapEdit.addLayer(name);
                }
                else {
                    tiledMapEdit.addLayer();
                }
            }
        });
        tiledMapLayersPane= new ScrollPane(this.mapLayers);
        add(mapLabel).height(mapLabel.getPrefHeight());
        row();
        ScrollPane scrollPane= new ScrollableItemList<>(mapLayers, skin).getScrollPane();
        add(scrollPane).size(300, listHeight);
        tiledMapLayersPane.setTransform(true);
        tiledMapLayersPane.setScrollbarsVisible(true);
        row();
        this.addMapButton.getLabel().setFontScale(getFontScale());
        enterName= new NamedField("Enter Name", skin, new TextField("", skin));
        enterName.getLabel().setFontScale(getFontScale());
        enterName.getField().setLayoutEnabled(true);
        HorizontalGroup addLayer= new HorizontalGroup();
        addLayer.addActor(enterName);
        addLayer.addActor(this.addMapButton);
        addLayer.space(5);
        mapLayers.setDebug(true);
        bottom().add(addLayer).size(320, addLayer.getPrefHeight());
        setMovable(false);
        setResizable(false);
        
    }
    
    public void makeWindow(){
    }
    @Override
    public void setMap(GameMap gameMap) {
        
    }
}
