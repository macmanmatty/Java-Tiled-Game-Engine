package com.jessematty.black.tower.Editor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.jessematty.black.tower.GameBaseClasses.GameAssets;
import com.kotcrab.vis.ui.VisUI;

public class GameEditor extends Game {
 private GameAssets gameAssets;
	public GameEditor() {


	}

	@Override
	public void create () {
		gameAssets = new GameAssets("editor", this);
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
