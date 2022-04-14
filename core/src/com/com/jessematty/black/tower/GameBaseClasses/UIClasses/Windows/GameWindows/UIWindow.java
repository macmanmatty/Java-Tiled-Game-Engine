package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;

public abstract class  UIWindow extends Window{


   private boolean addedToStage;
   private final  MapDraw mapDraw;


    public UIWindow(String title, Skin skin, MapDraw mapDraw) {
        super(title, skin, "lpc");
        this.mapDraw = mapDraw;
    }

    public UIWindow(String title, Skin skin, String styleName, MapDraw mapDraw) {
        super(title, skin, styleName);
        this.mapDraw = mapDraw;
    }


    public void  update(){}

    public boolean isAddedToStage() {
        return addedToStage;
    }

    public void setAddedToStage(boolean addedToStage) {
        this.addedToStage = addedToStage;
    }

    public MapDraw getMapDraw() {
        return mapDraw;
    }
}
