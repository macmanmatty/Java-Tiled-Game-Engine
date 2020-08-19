package com.jessematty.black.tower.Editor.EditMode.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jessematty.black.tower.Maps.LandMap;
import com.jessematty.black.tower.Editor.EditMode.Windows.CreateWindows.CreateMapWindow;

class MapButton extends com.badlogic.gdx.scenes.scene2d.ui.ImageButton{

     private LandMap map;
     private WorldEditScreen worldEditScreen;
     private Label nullMap;



    public MapButton(TextureRegion imageUp, final WorldEditScreen worldEditScreen, final LandMap map) {
        super(new TextureRegionDrawable(imageUp));
        this.map = map;
        this.worldEditScreen = worldEditScreen;
    nullMap= new Label("Map is Null", getSkin());
        addListener();
    }

    public MapButton(Skin skin, LandMap map, WorldEditScreen worldEditScreen) {
        super(skin);
        this.map = map;
        this.worldEditScreen = worldEditScreen;
        nullMap= new Label("Map is Null", getSkin());
        addListener();


    }

    private void addListener(){
        addListener( new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {


                worldEditScreen.getAssetts().setScreen(worldEditScreen.getMapEditScreen());
                if(map!=null) {
                    worldEditScreen.getMapEditScreen().changeMap(map);
                }
                else{
                    CreateMapWindow createMapWindow= new CreateMapWindow(worldEditScreen.getMapEditScreen(),getSkin() );
                    createMapWindow.makeWindow();

                    createMapWindow.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                    worldEditScreen.getMapEditScreen().getUiStage().addActor(createMapWindow);
                    map= (LandMap) worldEditScreen.getMapEditScreen().getCurrentMap();

                }

            }
        });


    }

    private void setImage(){
        if(map==null){


            add(nullMap);

        }

        else{
            removeActor(nullMap);


        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);



    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
