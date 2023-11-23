package com.jessematty.black.tower.Editor.EditMode.Windows.OptionPaneWindows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.Editor.Tools.MapTools.MapTools;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveFloatField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Maps.World;

/**
 * option pane to create a new map and add it to a world
 */
public class CreateMapOptionPane extends EditWindow {
    private PositiveIntegerField xSize;
    private PositiveIntegerField ySize;
    private PositiveFloatField gravity;
    private PositiveIntegerField tileSizeX;
    private PositiveIntegerField tileSizeY;
    private TextField name;
    private Button createWorld;
    private LandMap map;
    private World world;
    public CreateMapOptionPane( GameAssets gameAssets, Skin skin,  World world) {
        super(gameAssets, "Create World",  skin, "default");
        this.world=world;
    }
    private   void createWorld(){
      LandMap map= MapTools.newLandMap(gravity.getDouble(), name.getText(), xSize.getInteger(), ySize.getInteger(), tileSizeX.getInteger(), tileSizeY.getInteger());
        world.addMap(map);
    }
    public void makeWindow() {
        xSize=new PositiveIntegerField("2", getSkin());
        ySize=new PositiveIntegerField("2", getSkin());
        gravity=new PositiveFloatField("9.8", getSkin());
        tileSizeX= new PositiveIntegerField("32", getSkin());
        tileSizeY= new PositiveIntegerField("32", getSkin());
        name= new TextField("Enter Map  Name" , getSkin());
        Label label=new Label("Enter Map Width", getSkin());
       HorizontalGroup xSizeBox= new HorizontalGroup();
       Label label1=new Label("Enter Map Height", getSkin());
       xSizeBox.addActor(label);
       xSizeBox.addActor(xSize);
        HorizontalGroup ySizeBox= new HorizontalGroup();
        ySizeBox.addActor(label1);
        ySizeBox.addActor(ySize);
        Label label2=new Label("Enter World Name", getSkin());
        HorizontalGroup nameBox= new HorizontalGroup();
        nameBox.addActor(label2);
        nameBox.addActor(name);
        Label label3=new Label("Enter Maps Gravity ", getSkin());
        HorizontalGroup gravityBox= new HorizontalGroup();
        gravityBox.addActor(label3);
        gravityBox.addActor(gravity);
        Label label4=new Label(" Enter Tile Width ", getSkin());
        HorizontalGroup tileWdithBox= new HorizontalGroup();
        tileWdithBox.addActor(label4);
        tileWdithBox.addActor(tileSizeX);
        Label label5=new Label(" Enter Tile Height ", getSkin());
        HorizontalGroup tileheightBox= new HorizontalGroup();
        tileheightBox.addActor(label5);
        tileheightBox.addActor(tileSizeY);
        createWorld= new TextButton("Create Map", getSkin());
        createWorld.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createWorld();
                setVisible(false);
            }
        });
        add(nameBox);
        row();
        add(xSizeBox);
        row();
        add(ySizeBox);
        row();
        add(tileWdithBox);
        row();
        add(tileheightBox);
        row();
        add(gravityBox);
        row();
        add(createWorld);
        row();
        validate();
        pack();
        setSize(getPrefWidth(), getPrefHeight());
        setMovable(true);
    }
}
