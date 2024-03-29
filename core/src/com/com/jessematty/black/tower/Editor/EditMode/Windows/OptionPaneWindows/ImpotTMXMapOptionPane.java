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
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Serialization.TiledMap.MapLoadingException;
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
    public ImpotTMXMapOptionPane(GameAssets gameAssets, Skin skin) {
        super( gameAssets, "Import TMX (Tiled) Map File ",  skin, "default");
        this.mapEditScreen=mapEditScreen;
    }
        public void importMap(){
            try {
                mapEditScreen.getMapTools().loadTmxMap( mapEditScreen.getWorld().getWorldTextureAtlas(), mapEditScreen.getCurrentMap(), mapEditScreen.getGameAssets(), fileSelectPane.getFile().getAbsolutePath(),expandToFit.isChecked(), clipToFit.isChecked());
                        setVisible(false);
            } catch (MapLoadingException mapLoadingException) {
                mapEditScreen.getUiStage().addActor(new OptionPane(getSkin(), "Error Loading Map", mapLoadingException.getMessage(), "OK", "default"));
                setVisible(false);
            }
        }
    public void makeWindow() {
        Label label= new Label("Select TMX File To Import", getSkin());
        fileSelectPane= new FileSelectPane(getSkin(), "Import", 0);
        fileSelectPane.setFileTypes("TMX Map", "tmx");
        fileSelectPane.setDisplayFilePath(true);
        clipToFit= new CheckBox("Clip Map To Fit Current Map", getSkin());
        expandToFit= new CheckBox("Expand Map To Fit", getSkin());
        importMap = new TextButton("Create Map", getSkin());
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
