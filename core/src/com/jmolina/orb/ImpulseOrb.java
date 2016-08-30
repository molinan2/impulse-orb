package com.jmolina.orb;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.jmolina.orb.interfaces.PlayServices;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.var.Var;


/**
 * Punto de entrada de la aplicación. Implementa {@link ApplicationListener} para escuchar los
 * eventos de Android. Implementa {@link SuperManager} para gestionar el resto de managers de nivel
 * de aplicación.
 */
public class ImpulseOrb implements ApplicationListener, SuperManager {

	private PrefsManager prefsManager;
	private AssetManager assetManager;
	private ScreenManager screenManager;
	private GameManager gameManager;
	private PlayServices serviceManager;


	/**
	 * Constructor
	 */
	public ImpulseOrb() {
		this.serviceManager = null;
	}

	/**
	 * Constructor
	 */
	public ImpulseOrb(PlayServices serviceManager) {
		this.serviceManager = serviceManager;
	}


	@Override
	public void create () {
		Gdx.input.setCatchBackKey(true);
		prefsManager = new PrefsManager();
		assetManager = new AssetManager();
		assetManager.loadLoadScreenAssets();
		screenManager = new ScreenManager(this);
	}

	@Override
	public void render () {
		screenManager.render();
	}

	@Override
	public void resize (int width, int height) {
		screenManager.resize(width, height);
	}

	@Override
	public void resume () {
		screenManager.resume();
	}

	@Override
	public void pause () {
		screenManager.pause();
	}

	@Override
	public void dispose () {
		screenManager.dispose();
		if (gameManager != null) gameManager.dispose();
		assetManager.clear();
		assetManager.dispose();
	}

	@Override
	public void createGameManager() {
		gameManager = new GameManager(this);
	}

	@Override
	public PrefsManager getPrefsManager() {
		return prefsManager;
	}

	@Override
	public AssetManager getAssetManager() {
		return this.assetManager;
	}

	@Override
	public ScreenManager getScreenManager() {
		return this.screenManager;
	}

	@Override
	public GameManager getGameManager() {
		return this.gameManager;
	}

	@Override
	public PlayServices getServiceManager() {
		return serviceManager;
	}

}
