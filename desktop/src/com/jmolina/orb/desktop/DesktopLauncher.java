/*
 * IMPULSE ORB
 * Copyright (C) 2016 Juan M. Molina
 *
 * This file is part of the IMPULSE ORB source code.
 *
 * IMPULSE ORB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IMPULSE ORB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jmolina.orb.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jmolina.orb.ImpulseOrb;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.fullscreen = false;
		config.height = (int) Var.SCREEN_HEIGHT;
		config.width = (int) Var.SCREEN_WIDTH;
		config.title = Var.APP_NAME + " " + Var.APP_VERSION;
		config.addIcon(Asset.APP_ICON_256, Files.FileType.Internal);
		config.addIcon(Asset.APP_ICON_128, Files.FileType.Internal);
		config.addIcon(Asset.APP_ICON_64, Files.FileType.Internal);
		config.addIcon(Asset.APP_ICON_32, Files.FileType.Internal);
		config.addIcon(Asset.APP_ICON_16, Files.FileType.Internal);

		new LwjglApplication(new ImpulseOrb(), config);
	}
}
