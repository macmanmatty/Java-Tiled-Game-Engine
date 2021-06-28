package com.jessematty.black.tower.Editor.EditMode.Windows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.OptionPaneWindows.CreateStatOptionPane;
import com.jessematty.black.tower.Editor.EditMode.Windows.EntityEdit.EditEntityWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.EntitySelect.EntitySelectWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows.TextureDisplayWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.BitMaskedTileSetCreationWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows.AnimatedTileEditWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows.TiledMapLayerWindow;

public class MapEditWindows{

    private MapEditScreen mapEditScreen;
    private Skin skin;
    private TiledMapLayerWindow tiledMapLayerWindow;
   private  TextureDisplayWindow textureDisplayWindow;
   private  EditEntityWindow entityEditWindow;
    private AnimatedTileEditWindow animatedTileEditWindow;
    private CreateStatOptionPane createStatOptionPane;



    private BitMaskedTileSetCreationWindow bitMaskedTileSetCreationWindow;
    public MapEditWindows(MapEditScreen editScreen) {
        this.skin=editScreen.getSkin();
        this.mapEditScreen=editScreen;
        tiledMapLayerWindow= new TiledMapLayerWindow(mapEditScreen, skin);
        tiledMapLayerWindow.makeWindow();
      textureDisplayWindow=new TextureDisplayWindow(mapEditScreen, skin);
       textureDisplayWindow.makeWindow();
        EntitySelectWindow entitySelectWindow= new EntitySelectWindow(mapEditScreen, skin);
        entitySelectWindow.makeWindow();
      entityEditWindow= new EditEntityWindow(mapEditScreen, skin);
        entityEditWindow.makeWindow();
         animatedTileEditWindow= new AnimatedTileEditWindow(mapEditScreen,  skin);
        animatedTileEditWindow.makeWindow();
        bitMaskedTileSetCreationWindow= new BitMaskedTileSetCreationWindow(editScreen, skin);
        bitMaskedTileSetCreationWindow.makeWindow();
         createStatOptionPane = new CreateStatOptionPane(mapEditScreen, skin);
        createStatOptionPane.makeWindow();







    }

    public TiledMapLayerWindow getTiledMapLayerWindow() {
        return tiledMapLayerWindow;
    }

    public TextureDisplayWindow getTextureDisplayWindow() {
        return textureDisplayWindow;
    }

    public EditEntityWindow getEntityEditWindow() {
        return entityEditWindow;
    }

    public AnimatedTileEditWindow getAnimatedTileEditWindow() {
        return animatedTileEditWindow;
    }

    public CreateStatOptionPane getCreateStatOptionPane() {
        return createStatOptionPane;
    }

    public BitMaskedTileSetCreationWindow getBitMaskedTileSetCreationWindow() {
        return bitMaskedTileSetCreationWindow;
    }



}
