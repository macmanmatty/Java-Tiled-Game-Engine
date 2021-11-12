package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindow;

public class InfoWindow extends GameWindow {

   private  MapDraw mapDraw;
    private Entity entity;

    public InfoWindow(String title, MapDraw mapDraw, Entity entity) {
        this(title, mapDraw.getCurrentMap().getSkin(), "default", mapDraw, entity);
        this.mapDraw = mapDraw;
        this.entity = entity;
    }

    public InfoWindow(String title, Skin skin,  String styleName, MapDraw mapDraw, Entity entity) {
        super(title, skin, styleName, mapDraw.getAssetts());
        this.mapDraw = mapDraw;
        this.entity = entity;
        setRemoveOnClose(true);

    }
}
