package com.jessematty.black.tower.GameBaseClasses.UIClasses.UIBars.TopBars;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.UIWindow;

public class DefaultRPGTopWindow extends UIWindow {




    public DefaultRPGTopWindow(String title, Skin skin, MapDraw mapDraw) {
        super(title, skin, mapDraw);
    }

    public DefaultRPGTopWindow(String title, Skin skin, String styleName, MapDraw mapDraw) {
        super(title, skin, styleName, mapDraw);
    }
}
