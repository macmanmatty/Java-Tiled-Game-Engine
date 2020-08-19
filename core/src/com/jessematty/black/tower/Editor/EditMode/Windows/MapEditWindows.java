package com.jessematty.black.tower.Editor.EditMode.Windows;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.CreateWindows.CreateStatWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.EntityEdit.EditEntityWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.EntitySelect.EntitySelectWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows.TextureDisplayWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.BitMaskedTileSetCreationWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows.AnimatedTileEditWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows.TiledMapLayerWindow;

public class MapEditWindows implements InputProcessor {

    private MapEditScreen mapEditScreen;
    private Skin skin;
    private TiledMapLayerWindow tiledMapLayerWindow;
   private  TextureDisplayWindow textureDisplayWindow;
   private  EditEntityWindow entityEditWindow;
    private AnimatedTileEditWindow animatedTileEditWindow;
    private CreateStatWindow createStatWindow;



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
         createStatWindow= new CreateStatWindow(mapEditScreen, skin);
        createStatWindow.makeWindow();







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

    public CreateStatWindow getCreateStatWindow() {
        return createStatWindow;
    }

    public BitMaskedTileSetCreationWindow getBitMaskedTileSetCreationWindow() {
        return bitMaskedTileSetCreationWindow;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
