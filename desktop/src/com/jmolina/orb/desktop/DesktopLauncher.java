package com.jmolina.orb.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jmolina.orb.OrbGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// TODO Coordenadas reales: 1184x768
		// TODO Coordenadas reduccion 1: 1110x720

		config.fullscreen = false;
		config.height = 1184;
		config.width = 768;
		config.title = "OrbGame Desktop";
		config.addIcon("icon.png", Files.FileType.Internal);

		new LwjglApplication(new OrbGame(), config);
	}
}
