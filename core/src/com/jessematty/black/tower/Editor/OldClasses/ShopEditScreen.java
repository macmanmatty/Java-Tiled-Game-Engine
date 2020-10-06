package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.jessematty.black.tower.Editor.EditMode.Brushes.ClipBoard;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.GameBaseClasses.Input.KeyListener;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.World;
import com.jessematty.black.tower.Editor.EditMode.TopMenuBar.TopMenu;
import com.jessematty.black.tower.Editor.World.WorldObjects;

public class ShopEditScreen extends MapEditScreen {
    public ShopEditScreen(GameAssets assets, ClipBoard clipBoard, TopMenu topMenu, DragAndDrop dragAndDrop, KeyListener keyListener, Skin skin, World world, WorldObjects worldObjects) {
        super(assets, clipBoard, topMenu, dragAndDrop, keyListener, skin, world, worldObjects);
    }
}
