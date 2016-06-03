package com.jmolina.orb.interfaces;

import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.PreferenceManager;
import com.jmolina.orb.managers.ScreenManager;

public interface SuperManager {

    public AssetManager getAssetManager();

    public PreferenceManager getPreferenceManager();

    public ScreenManager getScreenManager();

}
