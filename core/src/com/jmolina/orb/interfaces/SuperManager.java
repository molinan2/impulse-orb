package com.jmolina.orb.interfaces;

import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;

/**
 * Define la interfaz del SuperManager, que controla el resto de managers de nivel de aplicación
 * y permite acceder a ellos.
 */
public interface SuperManager {

    /**
     * Obtiene el {@link AssetManager}
     */
    public AssetManager getAssetManager();

    /**
     * Obtiene el {@link PrefsManager}
     */
    public PrefsManager getPrefsManager();

    /**
     * Obtiene el {@link AssetManager}
     */
    public ScreenManager getScreenManager();

    /**
     * Obtiene el {@link GameManager}
     */
    public GameManager getGameManager();

    /**
     * Devuelve el ServiceManager
     */
    public PlayServices getServiceManager();

    /**
     * Crea el {@link GameManager}.
     */
    public void createGameManager();

}
