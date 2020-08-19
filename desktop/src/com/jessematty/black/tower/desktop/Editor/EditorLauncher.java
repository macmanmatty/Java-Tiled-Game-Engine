package com.jessematty.black.tower.desktop.Editor;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jessematty.black.tower.Editor.GameEditor;

public class EditorLauncher {

	public EditorLauncher() {
	}

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Game Editor";
		config.height = 1120;
		config.width =1600;
		config.forceExit=false;

		new LwjglApplication(new GameEditor(), config);
	}

	public void start(){



	}


}
