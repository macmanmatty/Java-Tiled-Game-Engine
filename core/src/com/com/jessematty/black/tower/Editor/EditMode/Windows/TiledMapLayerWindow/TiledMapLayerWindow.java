package com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapLayerWindow;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.Editor.EditMode.TiledMapEdit.TiledMapEdit;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ScrollableItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.DeleteOptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.MapSettable;

public class TiledMapLayerWindow extends MapEditWindow implements MapSettable {
    private TiledMapTileLayerList mapLayers;
    private TextButton addLayerButton;
    private float listHeight=240;
    private Label layerLabel;
    private TiledMapEdit tiledMapEdit;
    private ScrollPane scrollPane;
    private TextButton removeButton;

    public TiledMapLayerWindow(TiledMapEdit tiledMapEdit, GameAssets gameAssets, Skin skin) {
        this(gameAssets,tiledMapEdit, "Map Layers", skin, "default");
    }
    public TiledMapLayerWindow(final GameAssets assets, final TiledMapEdit tiledMapEdit, String title, Skin skin, String style) {
        super(assets,  title, skin, style);
        this.tiledMapEdit=tiledMapEdit;
       makeWindow();
        
    }
    
    public void makeWindow(){
        clearWindow();
        mapLayers= new TiledMapTileLayerList( tiledMapEdit,getSkin(), "Map Layers", "DisplayName", String.class);
        mapLayers.setEditable(true);
        mapLayers.setSortable(false);
        this.tiledMapEdit=tiledMapEdit;
        layerLabel = new Label("Current Layer Layer 0", getSkin());
        addLayerButton = new TextButton("Add New Layer", getSkin());
        addLayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                    tiledMapEdit.addLayer();
                scrollPane.setScrollY(scrollPane.getMaxY());
            }
        });
        removeButton = new TextButton("Remove Layer", getSkin());
        removeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DeleteOptionPane deleteOptionPane= new DeleteOptionPane(getSkin(), "Tiled  Map Tile Layer", mapLayers.getItems(), mapLayers.getSelectedItem());
                deleteOptionPane.setPosition(ScreenPosition.CENTER.getX(), ScreenPosition.CENTER.getY());
                getStage().addActor(deleteOptionPane);
                scrollPane.setScrollY(scrollPane.getMaxY());
            }
        });
        mapLayers.setOnSelected(new OnSelected<NamedTiledMapTileLayer>() {
            @Override
            public void onSelected(NamedTiledMapTileLayer item) {
                layerLabel.setText("Current Layer : "+mapLayers.getSelectedItem().getName());

            }
        });
        add(layerLabel).height(layerLabel.getPrefHeight());
        row();
        scrollPane= new ScrollableItemList<>(mapLayers, getSkin()).getScrollPane();
        add(scrollPane).setActorY(getMaxHeight());
        row();
        this.addLayerButton.getLabel().setFontScale(getFontScale());
        this.removeButton.getLabel().setFontScale(getFontScale());
        HorizontalGroup addLayer= new HorizontalGroup();
        addLayer.addActor(this.addLayerButton);
        addLayer.addActor(this.removeButton);
        addLayer.space(5);
        mapLayers.setDebug(true);
        bottom().add(addLayer);
        setMovable(false);
        setResizable(false);
    }
    @Override
    public void setMap(GameMap gameMap) {

    }
}
