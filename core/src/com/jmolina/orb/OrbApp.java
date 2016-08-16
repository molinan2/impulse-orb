package com.jmolina.orb;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.jmolina.orb.interfaces.ActionResolver;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.BaseScreen;


public class OrbApp implements ApplicationListener, SuperManager {

	private PrefsManager prefsManager;
	private AssetManager assetManager;
	private ScreenManager screenManager;
	private GameManager gameManager;

	private ActionResolver actionResolver;


	/**
	 * Constructor
	 */
	public OrbApp(ActionResolver actionResolver) {
		this.actionResolver = actionResolver;
	}


	@Override
	public void create () {
		setCatchBackKey(true);
		prefsManager = new PrefsManager();
		screenManager = new ScreenManager(this);
		assetManager = new AssetManager();
		assetManager.loadLoadScreenAssets();
		screenManager.switchToScreen(ScreenManager.Key.LOAD, BaseScreen.Hierarchy.LOWER);
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
	public void createGameManager() {
		gameManager = new GameManager(this);
	}

	/**
	 * Android Back key
	 * @param back boolean
     */
	private void setCatchBackKey(boolean back) {
		Gdx.input.setCatchBackKey(back);
	}

}
