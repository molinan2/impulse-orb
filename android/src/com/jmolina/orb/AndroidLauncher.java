/**
 * IMPULSE ORB
 * Copyright (C) 2016, Juan M. Molina
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

package com.jmolina.orb;

import android.content.Intent;
import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.example.games.basegameutils.GameHelper;
import com.jmolina.orb.managers.PrefsManager;

/**
 * Activity principal de la aplicacion de Android
 */
public class AndroidLauncher extends AndroidApplication {

	/** GameHelper de Play Games */
	private GameHelper gameHelper;

	/** Manager de Play Services */
	private PlayServicesManager playServicesManager;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(false);
		gameHelper.setup(new PrefsGameHelperListener());
		playServicesManager = new PlayServicesManager(this, gameHelper);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = false;
		config.useAccelerometer = false;
		config.useCompass = false;
		config.hideStatusBar = true;

		initialize(new ImpulseOrb(playServicesManager), config);
	}

	@Override
	protected void onStart() {
		super.onStart();
		PrefsManager prefsManager = new PrefsManager();

		if (prefsManager.getOptionOnline())
			gameHelper.onStart(this);
		else
			gameHelper.onStartWithoutSignIn(this);

		prefsManager = null;
	}

	@Override
	protected void onStop() {
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

}
