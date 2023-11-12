package com.jessematty.black.tower.GameBaseClasses.UIClasses.Buttons;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.ScreenPosition;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.EntityInfoWindow;

public class InfoButton extends TextButton {
   private  Entity entity;
   private MapDraw mapDraw;

    public InfoButton(String text, Skin skin, Entity entity, MapDraw mapDraw) {
        super(text, skin);
        this.entity = entity;
        this.mapDraw = mapDraw;
    }

    public InfoButton(String text, Skin skin, String styleName, Entity entity, MapDraw mapDraw) {
        super(text, skin, styleName);
        this.entity = entity;
        this.mapDraw = mapDraw;
    }

    public InfoButton(String text, TextButtonStyle style, Entity entity, MapDraw mapDraw) {
        super(text, style);
        this.entity = entity;
        this.mapDraw = mapDraw;
    }


    private void addListener(){


    }
}
