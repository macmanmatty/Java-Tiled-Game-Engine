package com.jessematty.black.tower.Editor.EditMode.Windows.OptionPaneWindows;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.GameBaseClasses.Loaders.TiledMap.MapLoadingExeception;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons.FileSelectPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.OptionPane;
import com.jessematty.black.tower.Maps.LandMap;
public class ImpotTMXMapOptionPane extends EditWindow {
    private CheckBox clipToFit;
    private CheckBox expandToFit;
    private FileSelectPane fileSelectPane;
    private TextField name;
    private Button importMap;
    private LandMap map;
    private MapEditScreen mapEditScreen;
    public ImpotTMXMapOptionPane(MapEditScreen mapEditScreen, Skin skin) {
        super( mapEditScreen, "Import TMX (Tiled) Map File ",  skin, "default");
        this.mapEditScreen=mapEditScreen;
    }
        public void importMap(){
            try {
                mapEditScreen.getMapTools().loadTmxMap( mapEditScreen.getWorld().getWorldTextureAtlas(), mapEditScreen.getCurrentMap(), mapEditScreen.getGameAssets(), fileSelectPane.getFile().getAbsolutePath(),expandToFit.isChecked(), clipToFit.isChecked());
                        setVisible(false);
            } catch (MapLoadingExeception mapLoadingExeception) {
                mapEditScreen.getUiStage().addActor(new OptionPane(skin, "Error Loading Map", mapLoadingExeception.getMessage(), "OK"));
                setVisible(false);
            }
        }
    public void makeWindow() {
        Label label= new Label("Select TMX File To Import", skin);
        fileSelectPane= new FileSelectPane(skin, "Import", 0);
        fileSelectPane.setFileTypes("TMX Map", "tmx");
        fileSelectPane.setDisplayFilePath(true);
        clipToFit= new CheckBox("Clip Map To Fit Current Map", skin);
        expandToFit= new CheckBox("Expand Map To Fit", skin);
        importMap = new TextButton("Create Map",skin);
        importMap.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                importMap();
            }
        });
        HorizontalGroup horizontalGroup= new HorizontalGroup();
        horizontalGroup.addActor(label);
        horizontalGroup.addActor(fileSelectPane);
        add(horizontalGroup);
        row();
        add(clipToFit);
        row();
        add(expandToFit);
        row();
        add(importMap);
        row();
        row();
        validate();
        setSize(getPrefWidth(), getPrefHeight());
        setMovable(true);
    }
}
