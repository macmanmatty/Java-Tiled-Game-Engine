package com.jessematty.black.tower.GameBaseClasses;
import com.badlogic.gdx.Game;
import com.jessematty.black.tower.GameBaseClasses.Screens.MainScreen;
public class BlackTower extends Game {
private GameAssets assetts;



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

	assetts=new GameAssets("game", this);
	assetts.setup();

		setScreen(new MainScreen(assetts));
	}
	public GameAssets getAssetts() {
		return assetts;
	}
}
