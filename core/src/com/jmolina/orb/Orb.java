package com.jmolina.orb;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.PreferenceManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.OrbScreen;


public class Orb implements ApplicationListener, SuperManager {

	private PreferenceManager preferenceManager;
	private AssetManager assetManager;
	private ScreenManager screenManager;


	/**
	 * Constructor
	 */
	public Orb() {
	}


	@Override
	public void create () {
		Gdx.input.setCatchBackKey(true); // Android

		preferenceManager = new PreferenceManager(Gdx.app.getPreferences(Orb.class.getName()));
		preferenceManager.checkPrefs();

		screenManager = new ScreenManager(this);

		assetManager = new AssetManager();
		assetManager.loadLoadScreenAssets();

		screenManager.createLoadScreen();
		screenManager.switchToScreen(ScreenManager.Key.LOAD, OrbScreen.Hierarchy.LOWER);
	}

	@Override
	public void render () {
		if (Gdx.input.isKeyPressed(Input.Keys.BACK))
			screenManager.back();

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
		screenManager.disposeAll();
		assetManager.clear();
		assetManager.dispose();
	}

	@Override
	public PreferenceManager getPreferenceManager() {
		return preferenceManager;
	}

	@Override
	public AssetManager getAssetManager() {
		return this.assetManager;
	}

	@Override
	public ScreenManager getScreenManager() {
		return this.screenManager;
	}

}
