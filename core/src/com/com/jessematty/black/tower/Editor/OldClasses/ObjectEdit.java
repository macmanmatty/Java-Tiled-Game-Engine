package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;

public abstract class ObjectEdit {

    protected Table window;
    protected Skin skin;

    protected GameAssets assetts;
    protected boolean mapWindow;

    public ObjectEdit() {
    }

    public ObjectEdit(Skin skin, GameAssets assetts) {
        this.skin = skin;
        this.assetts = assetts;
        window = new Table(skin);
        window.left();

    }

    public <T>  void setTextFieldsToThing(T thing){



    }


    public <T> T makeThing(T thing){



        return thing;


    }

    public ScrollPane getWindow() {
        window.left();
         Array<Cell> cells=window.getCells();
         int size=cells.size;
         for(int count=0; count<size; count++){

             cells.get(count).align(Align.left);
         }


        return new ScrollPane(window);
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public GameAssets getAssetts() {
        return assetts;
    }

    public void setAssetts(GameAssets assetts) {
        this.assetts = assetts;
    }


    public boolean isMapWindow() {
        return mapWindow;
    }

    public void setMapWindow(boolean mapWindow) {
        this.mapWindow = mapWindow;
    }
}
