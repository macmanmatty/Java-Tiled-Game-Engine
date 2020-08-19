package com.jessematty.black.tower.Editor.OldClasses;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jessematty.black.tower.GameBaseClasses.BlackTower;
import com.jessematty.black.tower.GameBaseClasses.UIClasses.TextFields.PositiveIntegerField;
import com.jessematty.black.tower.Maps.LandMap;


public class EditGame implements Screen {
   private  SelectBox<LandMap> maps;
    private BlackTower game;
    private TextButton newMap;
    HorizontalGroup xTilesGroup;
    HorizontalGroup yTilesGroup;
    PositiveIntegerField newMapX;
    PositiveIntegerField newMapY;
    Stage  stage;
        Skin skin;


    public EditGame(final BlackTower game, final Skin skin) {
        this.game = game;
        this.skin=skin;
        yTilesGroup= new HorizontalGroup();
        xTilesGroup= new HorizontalGroup();
        newMapX= new PositiveIntegerField("", skin);
        newMapY= new PositiveIntegerField("", skin);


        Label selectMap= new Label("Select A Map", skin);
        Label newMapXTiles= new Label("New Map X Tiles", skin);
        Label newMapYTiles= new Label("new Map Y Tiles", skin);
        xTilesGroup.addActor(newMapXTiles);
        xTilesGroup.addActor(newMapX);
        yTilesGroup.addActor(newMapYTiles);
        yTilesGroup.addActor(newMapY);

        newMap= new TextButton("New Map", skin);


        newMap.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {





            }
        });

        maps=new SelectBox<LandMap>(skin);
        stage= new Stage();




    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
