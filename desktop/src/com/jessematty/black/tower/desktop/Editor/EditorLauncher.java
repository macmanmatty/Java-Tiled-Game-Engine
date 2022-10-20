package com.jessematty.black.tower.desktop.Editor;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.jessematty.black.tower.Editor.GameEditor;

public class EditorLauncher {

	public EditorLauncher() {
	}

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle( "Game Editor");
		config.setWindowSizeLimits(1200, 1200, 1200, 1200);
		Graphics.DisplayMode dm = Lwjgl3ApplicationConfiguration.getDisplayMode();
		config.setWindowedMode((int) (dm.width*.75), (int) (dm.height*.75));
		new Lwjgl3Application(new GameEditor(), config);
	}

	public void start(){

	}


}
