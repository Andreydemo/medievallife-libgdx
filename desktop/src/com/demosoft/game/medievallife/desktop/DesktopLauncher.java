package com.demosoft.game.medievallife.desktop;

import javax.swing.JOptionPane;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.demosoft.game.medievallife.MedievalLife;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
		config.fullscreen = false;
		config.width = 1366;
		config.height = 768;
		//JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.");
		//config.vSyncEnabled = false;// if set to true the FPS is locked to 60
		//config.foregroundFPS = 30;// don't lock FPS
		new LwjglApplication(new MedievalLife(), config);
	}
}
