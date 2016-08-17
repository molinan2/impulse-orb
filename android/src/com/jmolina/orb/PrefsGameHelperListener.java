package com.jmolina.orb;

import com.google.example.games.basegameutils.GameHelper;
import com.jmolina.orb.managers.PrefsManager;

public class PrefsGameHelperListener implements GameHelper.GameHelperListener {

    @Override
    public void onSignInFailed() {
        PrefsManager prefsManager = new PrefsManager();
        prefsManager.putOptionOnline(false);
        prefsManager.save();
    }

    @Override
    public void onSignInSucceeded() {
        PrefsManager prefsManager = new PrefsManager();
        prefsManager.putOptionOnline(true);
        prefsManager.save();
    }

}
