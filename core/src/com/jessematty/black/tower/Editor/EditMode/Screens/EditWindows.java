package com.jessematty.black.tower.Editor.EditMode.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Editor.EditMode.Windows.EntityEdit.EditEntityWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.EntityEdit.EntityBoundsWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows.BitMaskWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.EntityEdit.FullStatEditWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.EntitySelect.EntitySelectWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows.TextureDisplayWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.BitMaskedTileSetCreationWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapWindows.TiledMapLayerWindow;

public class EditWindows {

    private TiledMapLayerWindow tiledMapLayerWindow;
    private TextureDisplayWindow textureDisplayWindow;
    private EntitySelectWindow entitySelectWindow;
    private EditEntityWindow entityEditWindow;
    private Skin skin;
    private MapEditScreen mapEditScreen;
    private WorldEditScreen worldEditScreen;
    private FullStatEditWindow fullStatEditWindow;
    private BitMaskedTileSetCreationWindow bitMaskedTileSetCreationWindow;
    private BitMaskWindow bitMaskWindow;
    private EntityBoundsWindow entityBoundsWindow;

    public EditWindows(Skin skin) {
        this.skin = skin;
        this.mapEditScreen = mapEditScreen;
        this.worldEditScreen = worldEditScreen;
    }

    public  void makeWindows() {

   textureDisplayWindow= new TextureDisplayWindow(mapEditScreen,  skin);
        textureDisplayWindow.setSize(320, 640);
        float width= Gdx.graphics.getWidth();
        float height=Gdx.graphics.getHeight();
        textureDisplayWindow.setPosition(width-320, 640);
        TiledMapLayerWindow tiledMapLayerWindow = new TiledMapLayerWindow(mapEditScreen, skin);
        tiledMapLayerWindow.setSize(320, 240);
        tiledMapLayerWindow.setPosition(width-320, 640-240);


    }

    public void setWindowsToMap(GameMap map){


    }

    public void setWindowsToWorld(World world){


    }


    public TiledMapLayerWindow getTiledMapLayerWindow() {
        return tiledMapLayerWindow;
    }

    public TextureDisplayWindow getTextureDisplayWindow() {
        return textureDisplayWindow;
    }

    public EntitySelectWindow getEntitySelectWindow() {
        return entitySelectWindow;
    }

    public EditEntityWindow getEntityEditWindow() {
        return entityEditWindow;
    }

    public FullStatEditWindow getFullStatEditWindow() {
        return fullStatEditWindow;
    }

    public BitMaskedTileSetCreationWindow getBitMaskedTileSetCreationWindow() {
        return bitMaskedTileSetCreationWindow;
    }

    public BitMaskWindow getBitMaskWindow() {
        return bitMaskWindow;
    }

    public EntityBoundsWindow getEntityBoundsWindow() {
        return entityBoundsWindow;
    }




}
