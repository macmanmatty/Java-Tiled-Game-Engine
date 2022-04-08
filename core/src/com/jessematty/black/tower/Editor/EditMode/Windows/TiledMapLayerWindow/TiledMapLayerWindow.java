package com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapLayerWindow;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.Editor.Tools.MapTools.TiledMapEdit;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.OnSelected;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ItemTable.ScrollableItemList;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.NamedField;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.MapSettable;

public class TiledMapLayerWindow extends MapEditWindow implements MapSettable {
    private TiledMapTileLayerList mapLayers;
    private TextButton addLayerButton;
    private NamedField enterName;
    private float listHeight=240;
    private Label layerLabel;
    private TiledMapEdit tiledMapEdit;
    private ScrollPane scrollPane;
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
        mapLayers= new TiledMapTileLayerList( tiledMapEdit,skin, "Map Layers", "DisplayName", String.class);
        mapLayers.setEditable(true);
        mapLayers.setSortable(false);
        this.tiledMapEdit=tiledMapEdit;
        layerLabel = new Label("Current Layer Layer 0", skin);
        addLayerButton = new TextButton("Add New Layer", skin);
        addLayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String name=enterName.getField().getText();
                if(name!=null   && !(name.isEmpty())){
                    tiledMapEdit.addLayer(name);
                }
                else {
                    tiledMapEdit.addLayer();
                }
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
        scrollPane= new ScrollableItemList<>(mapLayers, skin).getScrollPane();
        add(scrollPane).setActorY(getMaxHeight());
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
    @Override
    public void setMap(GameMap gameMap) {

    }
}
