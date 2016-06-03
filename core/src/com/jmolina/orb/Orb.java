package com.jmolina.orb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.ArrayMap;
import com.jmolina.orb.managers.OrbAssetManager;
import com.jmolina.orb.managers.PreferenceManager;
import com.jmolina.orb.screens.Base;
import com.jmolina.orb.screens.Credits;
import com.jmolina.orb.screens.LevelLaunch;
import com.jmolina.orb.screens.LevelSelect;
import com.jmolina.orb.screens.Loading;
import com.jmolina.orb.screens.Main;
import com.jmolina.orb.screens.Options;
import com.jmolina.orb.screens.Stats;


public class Orb extends Game {

	public Orb() {
	}

	/**
	 * Todas las pantallas de menu
	 */
	public enum Name {
		LOAD, MAIN, OPTIONS, STATS, CREDITS, LEVEL_SELECT,
		LEVEL_LAUNCH_1, LEVEL_LAUNCH_2, LEVEL_LAUNCH_3, LEVEL_LAUNCH_4
	}

	private Loading loading;
	private Main main;
	private Options options;
	private Stats stats;
	private Credits credits;
	private LevelSelect levelSelect;
	private LevelLaunch levelLaunch1, levelLaunch2, levelLaunch3, levelLaunch4;
	private ArrayMap<Name, Base> screens;
	private PreferenceManager preferenceManager;
	private OrbAssetManager assetManager;

	@Override
	public void create () {
		Gdx.input.setCatchBackKey(true); // Android

		// prefs = Gdx.app.getPreferences(Orb.class.getName());
		preferenceManager = new PreferenceManager(Gdx.app.getPreferences(Orb.class.getName()));
		preferenceManager.checkPrefs();

		screens = new ArrayMap<Name, Base>();

		assetManager = new OrbAssetManager();
		assetManager.loadLoadingScreenAssets();
		createLoadScreen();

		setScreenByKey(Name.LOAD, Base.Hierarchy.LOWER);
	}

	@Override
	public void render () {
		if (Gdx.input.isKeyPressed(Input.Keys.BACK))
			((Base) screen).back();

		if (screen != null)
			screen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose () {
		if (screen != null) screen.hide();

		main.dispose();
		loading.dispose();
		options.dispose();
		stats.dispose();
		credits.dispose();
		levelSelect.dispose();
		levelLaunch1.dispose();
		levelLaunch2.dispose();
		levelLaunch3.dispose();
		levelLaunch4.dispose();

		assetManager.clear();
		assetManager.dispose();
	}

	public void setScreenByKey(Name key, Base.Hierarchy hierarchy) {
		if (this.screen != null) this.screen.hide();

		this.screen = screens.get(key);
		((Base) this.screen).setAsInputProcessor();
		((Base) this.screen).setHierarchy(hierarchy);

		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	public void createLoadScreen() {
		loading = new Loading(this);
		screens.put(Name.LOAD, loading);
	}

	public void createMenuScreens() {
		main = new Main(this);
		options = new Options(this);
		stats = new Stats(this);
		credits = new Credits(this);
		levelSelect = new LevelSelect(this);
		levelLaunch1 = new LevelLaunch(this, "BASICS");
		levelLaunch2 = new LevelLaunch(this, "ADVANCED");
		levelLaunch3 = new LevelLaunch(this, "EXPERT");
		levelLaunch4 = new LevelLaunch(this, "HERO");

		screens.put(Name.MAIN, main);
		screens.put(Name.OPTIONS, options);
		screens.put(Name.STATS, stats);
		screens.put(Name.CREDITS, credits);
		screens.put(Name.LEVEL_SELECT, levelSelect);
		screens.put(Name.LEVEL_LAUNCH_1, levelLaunch1);
		screens.put(Name.LEVEL_LAUNCH_2, levelLaunch2);
		screens.put(Name.LEVEL_LAUNCH_3, levelLaunch3);
		screens.put(Name.LEVEL_LAUNCH_4, levelLaunch4);
	}

	public PreferenceManager getPreferenceManager() {
		return preferenceManager;
	}

	public OrbAssetManager getAssetManager() {
		return this.assetManager;
	}

}
