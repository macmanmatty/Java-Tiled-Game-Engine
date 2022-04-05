package com.jessematty.black.tower.Editor.EditMode.Windows;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.MapSelector.LandMapSelectorWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TextureRegionWindows.TextureDisplayWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TileSetCreationWindow.BitMaskedTileSetCreationWindow;
import com.jessematty.black.tower.Editor.EditMode.Windows.TiledMapLayerWindow.TiledMapLayerWindow;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Input.InputKeyCombo;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyAction;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.MapSettable;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Maps.WorldSettable;

import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 *
 * class for holding all of the ui windows  to edit the 2d rpg
 * Layers Window
 * Texture Window
 * Bit Mask Creation Window
 *
 */
public class MapEditWindows implements MapSettable, WorldSettable, InputProcessor {
    private Skin skin;
    private final ObjectMap< String , EditWindow> editWindows = new ObjectMap<>();
    private GameMap gameMap;
    private  World world;
    private ObjectMap<InputKeyCombo, Window> gameWindows= new ObjectMap<>();
     private MapEditScreen mapEditScreen;
    public MapEditWindows( MapEditScreen mapEditScreen) {
        this.mapEditScreen=mapEditScreen;
        this.skin=mapEditScreen.getSkin();
        TiledMapLayerWindow tiledMapLayerWindow= new TiledMapLayerWindow(mapEditScreen.getTiledMapEdit(), mapEditScreen.getGameAssets(), skin);
        tiledMapLayerWindow.makeWindow();
        this.editWindows.put("Layers Window", tiledMapLayerWindow);
     TextureDisplayWindow textureDisplayWindow=new TextureDisplayWindow( mapEditScreen.getClipBoard(), mapEditScreen.getGameAssets(), skin);
       textureDisplayWindow.makeWindow();
        this.editWindows.put("Texture Window", textureDisplayWindow);
      BitMaskedTileSetCreationWindow  bitMaskedTileSetCreationWindow= new BitMaskedTileSetCreationWindow(mapEditScreen.getGameAssets(), skin);
        bitMaskedTileSetCreationWindow.makeWindow();
        this.editWindows.put("Bit Mask Creation Window", bitMaskedTileSetCreationWindow);
        LandMapSelectorWindow landMapSelectorWindow= new LandMapSelectorWindow(mapEditScreen.getGameAssets(), mapEditScreen.getSkin(), "default");
        landMapSelectorWindow.makeWindow();
        this.editWindows.put("Land Map Selector Window", landMapSelectorWindow);




    }
    public ObjectMap<String, EditWindow> getEditWindows() {
        return editWindows;
    }
    /**
     * sets the game game for all of the map editable windows
     * @param gameMap the map to set to the windows
     */
    @Override
    public void setMap(GameMap gameMap) {
        this.gameMap=gameMap;
        Values<EditWindow> mapEditWindows=this.editWindows.values();
        while(mapEditWindows.hasNext){
            EditWindow mapEditWindow=mapEditWindows.next();
            if(mapEditWindow instanceof  MapSettable) {
                ((MapSettable) mapEditWindow).setMap(gameMap);
            }

        }
    }

    public GameMap getMap() {
        return gameMap;
    }
    /**
     * sets the game game for all of the map editable windows
     * @param world the map to set to the windows
     */

    @Override
    public void setWorld(World world) {
        this.world=world;
        Values<EditWindow> mapEditWindows=this.editWindows.values();
        while(mapEditWindows.hasNext){
            EditWindow mapEditWindow=mapEditWindows.next();
            if(mapEditWindow instanceof  WorldSettable) {
                ((WorldSettable) mapEditWindow).setWorld(world);
            }

        }
    }


    @Override
    public World getWorld() {
        return world;
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
