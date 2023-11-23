package com.jessematty.black.tower.Editor.EditMode.Screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jessematty.black.tower.Editor.EditMode.Screens.MapEdit.MapEditScreen;
import com.jessematty.black.tower.Maps.LandMap;
class MapButton extends com.badlogic.gdx.scenes.scene2d.ui.ImageButton{
     private LandMap map;
     private MapEditScreen worldEditScreen;
     private Label nullMap;
    public MapButton(TextureRegion imageUp, final MapEditScreen worldEditScreen, final LandMap map) {
        super(new TextureRegionDrawable(imageUp));
        this.map = map;
        this.worldEditScreen = worldEditScreen;
    nullMap= new Label("Map is Null", getSkin());
        addListener();
    }
    public MapButton(Skin skin, LandMap map, MapEditScreen worldEditScreen) {
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

                worldEditScreen.editMap(map);

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
