package com.jessematty.black.tower.GameBaseClasses.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;

public class About implements Screen {

    Skin skin;
    GameAssets assetts;
    Stage stage;

    public About(Skin skin, GameAssets assetts) {
        this.skin = skin;
        this.assetts = assetts;
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
