package com.jessematty.black.tower.Editor.EditMode.Screens.Interfaces;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jessematty.black.tower.Editor.EditMode.World.WorldObjects;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.Maps.GameMap;
import com.jessematty.black.tower.Maps.World;

public interface EditScreen {
    Stage getUiStage();
    Camera getCamera();
    String getName();
    World getWorld();
    void changeWorld(World World);
    GameMap getMap();
    void changeMap(GameMap map);
    WorldObjects getWorldObjects();
     void setWorldObjects(WorldObjects worldObjects);
     GameAssets getGameAssets();






}
