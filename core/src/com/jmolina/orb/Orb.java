package com.jmolina.orb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.compression.lzma.Base;
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
	 *
	 * TODO
	 * Rehacer el sistema de seleccion de pantallas para evitar tanta repeticion
	 */
	public enum Name {
		LOAD, MAIN, OPTIONS, STATS, CREDITS,
		LEVEL_SELECT, LEVEL_LAUNCH_1, LEVEL_LAUNCH_2, LEVEL_LAUNCH_3, LEVEL_LAUNCH_4
	}

	private Loading gameLoading;
	private Main main;
	private Options options;
	private Stats stats;
	private Credits credits;
	private LevelSelect levelSelect;
	private LevelLaunch levelLaunch1, levelLaunch2, levelLaunch3, levelLaunch4;

	private Logger logger;
	private ArrayMap<Name, BaseScreen> screens;

	private Texture splashTexture;

	private Preferences prefs;

	@Override
	public void create () {
		logger = new Logger("Game", Logger.INFO);
		Gdx.input.setCatchBackKey(true); // Android

		prefs = Gdx.app.getPreferences(Orb.class.getName());
		firstRun();
		
		splashTexture = new Texture(Gdx.files.internal("splash.png"));

		gameLoading = new Loading(); // Parametrica
		gameLoading.setSplash(splashTexture);
		main = new Main();
		options = new Options();
		stats = new Stats();
		credits = new Credits();
		levelSelect = new LevelSelect();
		levelLaunch1 = new LevelLaunch("BASICS");
		levelLaunch2 = new LevelLaunch("ADVANCED");
		levelLaunch3 = new LevelLaunch("EXPERT");
		levelLaunch4 = new LevelLaunch("HERO");

		gameLoading.setManager(this);
		main.setManager(this);
		options.setManager(this);
		options.setPrefs(prefs);
		stats.setManager(this);
		credits.setManager(this);
		levelSelect.setManager(this);
		levelLaunch1.setManager(this);
		levelLaunch2.setManager(this);
		levelLaunch3.setManager(this);
		levelLaunch4.setManager(this);

		screens = new ArrayMap<Name, BaseScreen>();
		screens.put(Name.LOAD, gameLoading);
		screens.put(Name.MAIN, main);
		screens.put(Name.OPTIONS, options);
		screens.put(Name.STATS, stats);
		screens.put(Name.CREDITS, credits);
		screens.put(Name.LEVEL_SELECT, levelSelect);
		screens.put(Name.LEVEL_LAUNCH_1, levelLaunch1);
		screens.put(Name.LEVEL_LAUNCH_2, levelLaunch2);
		screens.put(Name.LEVEL_LAUNCH_3, levelLaunch3);
		screens.put(Name.LEVEL_LAUNCH_4, levelLaunch4);

		setScreenByKey(Name.LOAD, BaseScreen.Hierarchy.LOWER);
	}

	@Override
	public void render () {
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			((BaseScreen) screen).back();
		}

		if (screen != null) {
			screen.render(Gdx.graphics.getDeltaTime());
		}
	}

	@Override
	public void dispose () {
		if (screen != null) screen.hide();

		splashTexture.dispose();

		main.dispose();
		gameLoading.dispose();
		options.dispose();
		stats.dispose();
		credits.dispose();
		levelSelect.dispose();
		levelLaunch1.dispose();
		levelLaunch2.dispose();
		levelLaunch3.dispose();
		levelLaunch4.dispose();
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
	public void firstRun () {
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

}
