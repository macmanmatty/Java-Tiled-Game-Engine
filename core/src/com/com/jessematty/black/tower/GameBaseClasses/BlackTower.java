package com.jessematty.black.tower.GameBaseClasses;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.JsonWriter;
import com.jessematty.black.tower.GameBaseClasses.Screens.MainScreen;
import com.jessematty.black.tower.GameBaseClasses.Serialization.JsonLoader;
import com.jessematty.black.tower.GameBaseClasses.ZRPGTest.TestEntities;
import com.jessematty.black.tower.GameBaseClasses.ZRPGTest.UITEST;

public class BlackTower extends Game {
private GameAssets gameAssets;
private String assetsPath="testAssets";
public void MainGame(){
}
	public void makeMainWindow(){
	}
	@Override
	public void create () {
		JsonLoader jsonLoader= new JsonLoader();
		jsonLoader.writeObjectToFile(TestEntities.getAll(), "/Users/jessematty/AndroidStudioProjects/Java-Tiled-Game-Engine2/android/assets/Entities/testEntities.json", false);
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
