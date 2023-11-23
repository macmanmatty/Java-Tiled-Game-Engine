package com.jessematty.black.tower.GameBaseClasses;

import com.badlogic.gdx.Game;
import com.jessematty.black.tower.GameBaseClasses.Screens.MainScreen;
import com.jessematty.black.tower.ZRPGTest.UITEST;

public class BlackTower extends Game {
private GameAssets gameAssets;

boolean uiTest=false;

public void MainGame(){
}
	public void makeMainWindow(){


	}
	@Override
	public void create () {
		gameAssets =new GameAssets("game", this);
		gameAssets.setup();
		if(uiTest) {
			setScreen(new UITEST(gameAssets));
		}
		else {
			setScreen(new MainScreen(gameAssets));
		}
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
