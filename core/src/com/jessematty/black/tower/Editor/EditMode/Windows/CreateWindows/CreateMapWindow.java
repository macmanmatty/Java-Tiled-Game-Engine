package com.jessematty.black.tower.Editor.EditMode.Windows.CreateWindows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen;
import com.jessematty.black.tower.Editor.EditMode.Windows.EditWindow;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.OptionPanes.OptionPane;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveFloatField;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Editor.EditMode.MapTools.MapTools;

public class CreateMapWindow extends EditWindow {

    private PositiveIntegerField xSize;
    private PositiveIntegerField ySize;
    private PositiveFloatField gravity;
    private PositiveIntegerField tileSizeX;
    private PositiveIntegerField tileSizeY;


    private TextField name;
    private Button createWorld;
    private LandMap map;
    private com.jessematty.black.tower.Editor.EditMode.Screens.MapEditScreen mapEditScreen;



    public CreateMapWindow(MapEditScreen mapEditScreen, Skin skin) {
        super( mapEditScreen, "Create World",  skin, "default");


        this.mapEditScreen=mapEditScreen;
    }



    private   void createWorld(){

        boolean mapSet=setMap();


        if(mapSet==true) {

            setVisible(false);
        }


    }


    private boolean setMap(){

        int xMaps=xSize.getInteger();
        int yMaps=ySize.getInteger();
        int tileSizeNumberX=tileSizeX.getInteger();
        int tileSizeNumberY=tileSizeY.getInteger();


         boolean nameInUse= MapTools.mapNameCheck(name.getText(), mapEditScreen.getWorld());
         if(nameInUse==true){
             mapEditScreen.getUiStage().addActor(new OptionPane(skin, "Error!", "Map Name is Already Used In Your World Please Use A  Different Name ", "OK"));

             return false;

         }


            else {
             if (yMaps <= 10 || xMaps <= 10 || tileSizeNumberX < 8 || tileSizeNumberY < 8) {

                 mapEditScreen.getUiStage().addActor(new OptionPane(skin, "Error!", "Map X or Y Size Can't be  less than 10!!", "OK"));

                 return false;


             } else {


                 map = MapTools.newLandMap(gravity.getDouble(), name.getText(), xMaps, yMaps, tileSizeNumberX, tileSizeNumberY);


                 mapEditScreen.changeMap(map);

             }


         }

        return true;

    }


    public void makeWindow() {
        xSize=new PositiveIntegerField("2", skin);
        ySize=new PositiveIntegerField("2", skin);
        gravity=new PositiveFloatField("9.8", skin);
        tileSizeX= new PositiveIntegerField("32", skin);
        tileSizeY= new PositiveIntegerField("32", skin);

        name= new TextField("Enter Map  Name" , skin);

        Label label=new Label("Enter Map Width", skin);
       HorizontalGroup xSizeBox= new HorizontalGroup();
       Label label1=new Label("Enter Map Height", skin);
       xSizeBox.addActor(label);
       xSizeBox.addActor(xSize);
        HorizontalGroup ySizeBox= new HorizontalGroup();
        ySizeBox.addActor(label1);
        ySizeBox.addActor(ySize);
        Label label2=new Label("Enter World Name", skin);
        HorizontalGroup nameBox= new HorizontalGroup();
        nameBox.addActor(label2);
        nameBox.addActor(name);

        Label label3=new Label("Enter Maps Gravity ", skin);
        HorizontalGroup gravityBox= new HorizontalGroup();
        gravityBox.addActor(label3);
        gravityBox.addActor(gravity);
        Label label4=new Label(" Enter Tile Width ", skin);

        HorizontalGroup tileWdithBox= new HorizontalGroup();
        tileWdithBox.addActor(label4);
        tileWdithBox.addActor(tileSizeX);

        Label label5=new Label(" Enter Tile Height ", skin);

        HorizontalGroup tileheightBox= new HorizontalGroup();
        tileheightBox.addActor(label5);
        tileheightBox.addActor(tileSizeY);
        createWorld= new TextButton("Create Map",skin);
        createWorld.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createWorld();

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
        setSize(getPrefWidth(), getPrefHeight());
        setMovable(true);




    }
}
