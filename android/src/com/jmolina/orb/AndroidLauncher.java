package com.jmolina.orb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.jmolina.orb.interfaces.PlayServices;
import com.jmolina.orb.managers.PrefsManager;

public class AndroidLauncher extends AndroidApplication {

	private GameHelper gameHelper;
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
