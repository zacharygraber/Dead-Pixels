package com.zegraber.deadpixels.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zegraber.deadpixels.DeadPixels;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Dead Pixels";
		config.width = 1920;
		config.height = 1080;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		//config.fullscreen = true;
		config.vSyncEnabled = true;
		new LwjglApplication(new DeadPixels(), config);
	}
}