package com.jessematty.black.tower.GameBaseClasses.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.Components.Stats.BooleanStat;
import com.jessematty.black.tower.Components.Stats.NumericStat;
import com.jessematty.black.tower.Components.Stats.Stat;
import com.jessematty.black.tower.Components.Stats.StringStat;
import com.jessematty.black.tower.GameBaseClasses.Settings.GameSettings.GamePrefecences;
import com.jessematty.black.tower.GameBaseClasses.Settings.GameSettings.KeyStat;
public class SettingsScreen implements NamedScreen {
    private Stage stage= new Stage();
    private Color color = Color.FOREST;
    private Skin skin;
    GamePrefecences gamePrefecences;
    public SettingsScreen( String gameName, Skin skin) {
        this.gamePrefecences=new  GamePrefecences(gameName);
    }
    private  void makeScreen(){
       int size=gamePrefecences.getNumberOfPreferences();
        for(int count=0; count<size; count++){
        }
    }
    private void  makePreference(Stat stat){
        if(stat instanceof NumericStat){
        }
        if(stat instanceof StringStat){
        }
        if(stat instanceof BooleanStat){
        }
        if(stat instanceof KeyStat){
        }
    }
    @Override
    public void show() {
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a); // clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().setScreenSize(width, height);
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
    @Override
    public String getName() {
        return null;
    }
}
