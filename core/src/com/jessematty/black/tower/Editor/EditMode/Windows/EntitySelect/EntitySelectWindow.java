package com.jessematty.black.tower.Editor.EditMode.Windows.EntitySelect;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces.AtlasRegionSetable;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapEditWindow;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Textures.AtlasRegions.TextureAtlasRegionNames;
public class EntitySelectWindow extends EditWindow {
    protected EntitySelect textureRegionSelect; // the region select window  that display the regions
    protected SelectBox<String> textureNames; // the selected texture names to choose from and display
    protected SelectBox<TextureAtlasRegionNames> atlasNames; // select box for texture atlases used
    protected Skin skin;
    protected Window window;
    protected ScrollPane textureRegionTable;
    protected AtlasRegionSetable atlasRegionSetable;
    public EntitySelectWindow(final GameAssets  gameAssets , final Skin skin) {
        super(gameAssets,"Entities", skin, "default");
        this.skin = skin;
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
