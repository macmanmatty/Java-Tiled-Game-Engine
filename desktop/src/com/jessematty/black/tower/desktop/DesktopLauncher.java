package com.jessematty.black.tower.desktop;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.jessematty.black.tower.GameBaseClasses.BlackTower;

public class DesktopLauncher {

	public static void main (String[] arg) {
		String userDirectoryPath = System.getProperty("user.dir");
		System.out.println("******");
		System.out.println("******");
		System.out.println("Working Directory: "+userDirectoryPath);
		System.out.println("******");
		System.out.println("******");
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		Graphics.DisplayMode dm = Lwjgl3ApplicationConfiguration.getDisplayMode();
		config.setWindowedMode((int) (dm.width*.75), (int) (dm.height*.75));
		new Lwjgl3Application(new BlackTower(), config);
	}
}
