package com.jessematty.black.tower.GameBaseClasses;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.jessematty.black.tower.GameBaseClasses.Screens.MainScreen;
public class BlackTower extends Game {
private GameAssets gameAssets;
private boolean packAssets=true;
private String assetsPath="testAssets";
public void MainGame(){
}
	public void makeMainWindow(){
	}
	@Override
	public void create () {
	if(packAssets){
		TexturePacker.process(new Settings(), "testAssets", "android/assets/textureAtlases", "testAssets");
	}
		gameAssets =new GameAssets("game", this);
		gameAssets.setup();
		setScreen(new MainScreen(gameAssets));
	}
	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose () {
	super.dispose();
	}
	public  GameAssets getGameAssets() {
		return gameAssets;
	}
}
