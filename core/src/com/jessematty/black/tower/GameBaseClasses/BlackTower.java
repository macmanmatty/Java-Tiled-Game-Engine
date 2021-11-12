package com.jessematty.black.tower.GameBaseClasses;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.jessematty.black.tower.GameBaseClasses.Screens.MainScreen;
public class BlackTower extends Game {
private GameAssets assetts;
private boolean packAssets=true;
private String assetsPath="TestAssets";



public void BlackTower(){
}
	public void makeMainWindow(){
	}
	@Override
	public void create () {
	if(packAssets){
		TexturePacker.process(new Settings(), "TestAssets", "android/assets/textureAtlases", "testAssets");


	}

		assetts=new GameAssets("game", this);
		assetts.setup();

		setScreen(new MainScreen(assetts));
	}
	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose () {
	super.dispose();
	}

	public GameAssets getAssetts() {
		return assetts;
	}
}
