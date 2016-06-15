package com.jmolina.orb.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jmolina.orb.Orb;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.var.Var;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.fullscreen = false;
		config.height = 1184;
		config.width = 768;
		config.title = "Orb " + Var.VERSION;
		config.addIcon(Asset.APP_ICON_128, Files.FileType.Internal);
		config.addIcon(Asset.APP_ICON_64, Files.FileType.Internal);
		config.addIcon(Asset.APP_ICON_32, Files.FileType.Internal);
		config.addIcon(Asset.APP_ICON_16, Files.FileType.Internal);

		new LwjglApplication(new Orb(), config);
	}
}
