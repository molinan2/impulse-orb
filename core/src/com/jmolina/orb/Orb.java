package com.jmolina.orb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.ArrayMap;
import com.jmolina.orb.managers.OrbAssetManager;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.Credits;
import com.jmolina.orb.screens.LevelLaunch;
import com.jmolina.orb.screens.LevelSelect;
import com.jmolina.orb.screens.Loading;
import com.jmolina.orb.screens.Main;
import com.jmolina.orb.screens.Options;
import com.jmolina.orb.screens.Stats;
import com.jmolina.orb.var.Var;


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
	private ArrayMap<Name, BaseScreen> screens;
	private Preferences prefs;
	private OrbAssetManager assetManager;

	@Override
	public void create () {
		Gdx.input.setCatchBackKey(true); // Android
		prefs = Gdx.app.getPreferences(Orb.class.getName());
		setFirstRunPrefs();
		screens = new ArrayMap<Name, BaseScreen>();
		assetManager = new OrbAssetManager();
		createLoadScreen();
		setScreenByKey(Name.LOAD, BaseScreen.Hierarchy.LOWER);
	}

	@Override
	public void render () {
		if (Gdx.input.isKeyPressed(Input.Keys.BACK))
			((BaseScreen) screen).back();

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

		getAssetManager().clear();
		getAssetManager().dispose();
	}

	public void setScreenByKey(Name key, BaseScreen.Hierarchy hierarchy) {
		if (this.screen != null) this.screen.hide();

		this.screen = screens.get(key);
		((BaseScreen) this.screen).setAsInputProcessor();
		((BaseScreen) this.screen).setHierarchy(hierarchy);

		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	/**
	 * Necesario sólo en la primera ejecución
	 */
	public void setFirstRunPrefs() {
		if (!prefs.contains(Var.OPTION_MUSIC))
			prefs.putBoolean(Var.OPTION_MUSIC, true);

		if (!prefs.contains(Var.OPTION_SOUND))
			prefs.putBoolean(Var.OPTION_SOUND, true);

		if (!prefs.contains(Var.OPTION_VIBRATION))
			prefs.putBoolean(Var.OPTION_VIBRATION, true);

		if (!prefs.contains(Var.OPTION_ONLINE))
			prefs.putBoolean(Var.OPTION_ONLINE, true);

		if (!prefs.contains(Var.OPTION_ZOOM))
			prefs.putInteger(Var.OPTION_ZOOM, 2);
	}

	public AssetManager getAssetManager() {
		return this.assetManager;
	}

	public void createMenuScreens() {
		main = new Main(assetManager);
		options = new Options(assetManager);
		stats = new Stats(assetManager);
		credits = new Credits(assetManager);
		levelSelect = new LevelSelect(assetManager);
		levelLaunch1 = new LevelLaunch(assetManager, "BASICS");
		levelLaunch2 = new LevelLaunch(assetManager, "ADVANCED");
		levelLaunch3 = new LevelLaunch(assetManager, "EXPERT");
		levelLaunch4 = new LevelLaunch(assetManager, "HERO");

		main.setScreenManager(this);
		options.setScreenManager(this);
		options.setPrefs(prefs);
		stats.setScreenManager(this);
		credits.setScreenManager(this);
		levelSelect.setScreenManager(this);
		levelLaunch1.setScreenManager(this);
		levelLaunch2.setScreenManager(this);
		levelLaunch3.setScreenManager(this);
		levelLaunch4.setScreenManager(this);

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

	public void createLoadScreen() {
		assetManager.loadLoadingScreenAssets();
		loading = new Loading(assetManager);
		loading.setScreenManager(this);
		screens.put(Name.LOAD, loading);
	}

}
