package com.jessematty.black.tower.Editor.EditMode.UIElements.PopUpMenus.MenuItems;

import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;

public class MapEditScreenMenuItem extends MenuItem {
    private MapEditScreen mapEditScreen;

    public MapEditScreenMenuItem(String text, MapEditScreen mapEditScreen) {
        super(text);
        this.mapEditScreen = mapEditScreen;
    }

    public MapEditScreen getMapEditScreen() {
        return mapEditScreen;
    }
}
