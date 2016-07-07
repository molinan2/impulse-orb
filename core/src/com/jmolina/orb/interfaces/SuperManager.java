package com.jmolina.orb.interfaces;

import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;

public interface SuperManager {

    public AssetManager getAssetManager();

    public PrefsManager getPrefsManager();

    public ScreenManager getScreenManager();

}
