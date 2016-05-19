package com.jmolina.orb.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jmolina.orb.Orb;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// TODO Coordenadas reales: 1184x768

		config.fullscreen = false;
		config.height = 1110;
		config.width = 720;
		config.title = "Orb Desktop";
		config.addIcon("icon.png", Files.FileType.Internal);

		new LwjglApplication(new Orb(), config);
	}
}
