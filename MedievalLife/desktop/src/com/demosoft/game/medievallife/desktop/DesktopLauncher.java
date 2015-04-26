package com.demosoft.game.medievallife.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.demosoft.game.medievallife.MedievalLife;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
		config.fullscreen = true;
		/*config.width = 800;
		config.height = 600;*/
		// config.vSyncEnabled = false;// if set to true the FPS is locked to 60
		// config.foregroundFPS = 1000;// don't lock FPS
		new LwjglApplication(new MedievalLife(), config);
	}
}
