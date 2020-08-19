package com.jessematty.black.tower.Editor;

import com.badlogic.gdx.Game;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.kotcrab.vis.ui.VisUI;

public class GameEditor extends Game {
 private GameAssets gameAssets;


	public GameEditor() {
		gameAssets = new GameAssets(this);


	}

	@Override
	public void create () {
		gameAssets.setScreen(new EditorMainScreen(gameAssets));
		if(VisUI.isLoaded()==false) {
			VisUI.load();
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

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}


}
