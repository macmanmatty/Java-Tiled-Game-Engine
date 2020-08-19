package com.jessematty.black.tower.GameBaseClasses;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.GameBaseClasses.Screens.MainScreen;
public class BlackTower extends Game {
GameAssets assetts;
 Settings gameSettings;
Skin skin;
Label errorLabel;
public void BlackTower(){
}
	public void makeMainWindow(){
	}
	@Override
	public void create () {
code();
	}
	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose () {
	super.dispose();
	}
	public void code(){
	assetts=new GameAssets(this);
	assetts.setup();
		setScreen(new MainScreen(assetts));
	}
	public GameAssets getAssetts() {
		return assetts;
	}
}
