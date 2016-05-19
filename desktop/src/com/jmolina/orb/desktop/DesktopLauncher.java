package com.jmolina.orb.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jmolina.orb.Orb;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.fullscreen = false;
		config.height = 1184;
		config.width = 768;
		config.title = "Orb Desktop";

		new LwjglApplication(new Orb(), config);
	}
}
