package com.jmolina.orb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Logger;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.CreditsScreen;
import com.jmolina.orb.screens.LevelLaunchScreen;
import com.jmolina.orb.screens.LevelSelectScreen;
import com.jmolina.orb.screens.LoadScreen;
import com.jmolina.orb.screens.MainScreen;
import com.jmolina.orb.screens.OptionsScreen;
import com.jmolina.orb.screens.StatsScreen;
import com.jmolina.orb.var.Var;


public class Orb extends Game {

	public Orb() {
	}

	/**
	 * Todas las pantallas de menu
	 */
	public enum Name {
		LOAD, MAIN, OPTIONS, STATS, CREDITS, LEVEL_SELECT, LEVEL_LAUNCH
	}

	/**
	 * Pantalla actual
	 */
	protected BaseScreen screen;

	private LoadScreen gameLoadScreen;
	private MainScreen mainScreen;
	private OptionsScreen optionsScreen;
	private StatsScreen statsScreen;
	private CreditsScreen creditsScreen;
	private LevelSelectScreen levelSelectScreen;
	private LevelLaunchScreen levelLaunchScreen;

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

		gameLoadScreen = new LoadScreen(); // Parametrica
		gameLoadScreen.setSplash(splashTexture);
		mainScreen = new MainScreen();
		optionsScreen = new OptionsScreen();
		statsScreen = new StatsScreen();
		creditsScreen = new CreditsScreen();
		levelSelectScreen = new LevelSelectScreen();
		levelLaunchScreen = new LevelLaunchScreen(); // Parametrica

		gameLoadScreen.setManager(this);
		mainScreen.setManager(this);
		optionsScreen.setManager(this);
		optionsScreen.setPrefs(prefs);
		statsScreen.setManager(this);
		creditsScreen.setManager(this);
		levelSelectScreen.setManager(this);
		levelLaunchScreen.setManager(this);

		screens = new ArrayMap<Name, BaseScreen>();
		screens.put(Name.LOAD, gameLoadScreen);
		screens.put(Name.MAIN, mainScreen);
		screens.put(Name.OPTIONS, optionsScreen);
		screens.put(Name.STATS, statsScreen);
		screens.put(Name.CREDITS, creditsScreen);
		screens.put(Name.LEVEL_SELECT, levelSelectScreen);
		screens.put(Name.LEVEL_LAUNCH, levelLaunchScreen);

		setScreenByKey(Name.LOAD, BaseScreen.Hierarchy.LOWER);
	}

	@Override
	public void render () {
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			screen.back();
		}

		if (screen != null) {
			screen.render(Gdx.graphics.getDeltaTime());
		}
	}

	@Override
	public void dispose () {
		if (screen != null) screen.hide();

		splashTexture.dispose();

		mainScreen.dispose();
		gameLoadScreen.dispose();
		optionsScreen.dispose();
		statsScreen.dispose();
		creditsScreen.dispose();
		levelSelectScreen.dispose();
		levelLaunchScreen.dispose();
	}

	public void setScreenByKey(Name key, BaseScreen.Hierarchy hierarchy) {
		if (this.screen != null) this.screen.hide();

		this.screen = screens.get(key);
		this.screen.setAsInputProcessor();
		this.screen.setHierarchy(hierarchy);

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
