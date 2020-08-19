package com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.TopBars;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.UIWindow;
import com.jessematty.black.tower.Maps.GameMap;

public class DefaultRPGTopWindow extends UIWindow {




    public DefaultRPGTopWindow(String title, Skin skin, MapDraw mapDraw) {
        super(title, skin, mapDraw);
    }

    public DefaultRPGTopWindow(String title, Skin skin, String styleName, MapDraw mapDraw) {
        super(title, skin, styleName, mapDraw);
    }
}
