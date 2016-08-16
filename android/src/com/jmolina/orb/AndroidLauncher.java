package com.jmolina.orb;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.jmolina.orb.interfaces.ActionResolver;

public class AndroidLauncher extends AndroidApplication {

	ActionResolver playGames = new PlayGames();

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = false;
		config.useAccelerometer = false;
		config.useCompass = false;
		config.hideStatusBar = true;
		initialize(new OrbApp(playGames), config);
	}
}
