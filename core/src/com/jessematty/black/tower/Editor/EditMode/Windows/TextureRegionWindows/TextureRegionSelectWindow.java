package com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.TextureAtlasRegionNames;
public class TextureRegionSelectWindow extends MapEditWindow {
    protected com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows.TextureRegionSelect textureRegionSelect; // the region select window  that display the regions
    protected SelectBox<String> textureNames; // the selected texture names to choose from and display
    protected SelectBox<TextureAtlasRegionNames> atlasNames; // select box for texture atlases used
    protected ScrollPane textureRegionTable;
    protected int xSize;
    protected int ySize;
    public TextureRegionSelectWindow(final MapEditScreen mapEditScreen, String title, final Skin skin, String style, int xSize, int ySize) {
        super(mapEditScreen, title, skin, style);
        textureNames = new SelectBox<String>(skin);
        this.xSize = xSize;
        this.ySize = ySize;
        atlasNames.getItems().get(0).getTextureNames();
        textureNames.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                textureRegionSelect = new TextureRegionSelect(getMapEditScreen().getClipBoard(), getGameAssets(), skin);
                textureRegionSelect.makeTable(textureNames.getSelected(), atlasNames.getSelected().getAmounts(textureNames.getSelectedIndex()), atlasNames.getSelected().getAtlasName() + ".atlas", false);
                textureRegionTable = textureRegionSelect.getTable();
                makeTextureWindow();
            }
        });
        atlasNames.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });
    }
    public void makeTextureWindow() {
        setPosition(0, 0);
        setSize(xSize, ySize);
        clear();
        Label label = new Label(" Select an Asset Name To Display", getSkin());
        add(label).size(xSize, 50);
        row();
        textureNames.setItems(atlasNames.getItems().get(0).getTextureNames());
        add(textureNames).size(xSize, 50);
        row();
        add(atlasNames).size(xSize, 50);
        row();
        textureRegionTable.layout();
        add(textureRegionTable).size(xSize, ySize - 150);
        pack();
    }
    public void setSize(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        makeTextureWindow();
    }
    public void setAtlasNames(TextureAtlasRegionNames [] names){
        atlasNames.setItems(names);
    }
    @Override
    public void makeWindow() {
    }
}
