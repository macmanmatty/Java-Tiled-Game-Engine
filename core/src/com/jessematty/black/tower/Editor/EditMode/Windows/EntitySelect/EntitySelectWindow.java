package com.jessematty.black.tower.Editor.EditMode.Windows.EntitySelect;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces.AtlasRegionSetable;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.AtlasRegions.TextureAtlasRegionNames;

public class EntitySelectWindow extends MapEditWindow {

    protected EntitySelect textureRegionSelect; // the region select window  that display the regions
    protected SelectBox<String> textureNames; // the selected texture names to choose from and display
    protected SelectBox<TextureAtlasRegionNames> atlasNames; // select box for texture atlases used
    protected GameAssets assetts;
    protected Skin skin;
    protected Window window;
    protected ScrollPane textureRegionTable;
    protected AtlasRegionSetable atlasRegionSetable;

    public EntitySelectWindow(final MapEditScreen edit , final Skin skin) {
        super(edit,"Entities", skin, "default");

        this.skin = skin;
        this.assetts = edit.getGameAssets();


    }


    public void makeTextureWindow() {
        window.setPosition(0, 0);


        window.clear();
        Label label = new Label(" Select an Asset Name To Display", skin);
        window.row();
        textureNames.setItems(atlasNames.getItems().get(0).getTextureNames());
        window.row();



        window.row();
        textureRegionTable.layout();

        window.pack();


    }


    @Override
    public void makeWindow() {

    }

    public Window getWindow() {
        return window;
    }


    public void setSize(int xSize, int ySize) {



        makeTextureWindow();

    }







}
