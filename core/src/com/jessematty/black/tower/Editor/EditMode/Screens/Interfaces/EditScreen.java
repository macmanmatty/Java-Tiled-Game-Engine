package com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jessematty.black.tower.Editor.EditMode.World.WorldObjects;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.MapSettable;
import com.jessematty.black.tower.Maps.World;

public interface EditScreen {
    Stage getUiStage();
    Camera getCamera();
    String getName();
    World getWorld();
    GameMap getMap();
    WorldObjects getWorldObjects();
     GameAssets getGameAssets();
}
