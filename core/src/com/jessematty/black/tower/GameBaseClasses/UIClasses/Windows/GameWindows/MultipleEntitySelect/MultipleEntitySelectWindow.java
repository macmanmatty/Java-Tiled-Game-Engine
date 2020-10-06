package com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.MultipleEntitySelect;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.jessematty.black.tower.GameBaseClasses.Engine.GameComponentMapper;
import com.jessematty.black.tower.GameBaseClasses.MapDraw;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.ClosableWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.Windows.GameWindows.GameWindow;

import javax.swing.text.html.parser.Entity;

public class MultipleEntitySelectWindow implements GameWindow {


   private ClosableWindow window;
   private Array<Entity> entities;
   private GameComponentMapper gameComponentMapper;
   private MapDraw mapDraw;
   private Skin skin;

    public MultipleEntitySelectWindow(Array<Entity> entities, MapDraw mapDraw) {
        this.entities = entities;
        this.mapDraw = mapDraw;
        this.gameComponentMapper=mapDraw.getGameComponentMapper();
        this.skin=mapDraw.getCurrentMap().getSkin();
        this.window= new ClosableWindow("Entities", skin);


    }
    public void createWindow(){
        Label label =new Label("Select An Entity", skin);




    }

    @Override
    public ClosableWindow getWindow() {
        return window;
    }


}
