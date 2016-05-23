package com.jmolina.orb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.CreditsScreen;
import com.jmolina.orb.screens.LevelLaunchScreen;
import com.jmolina.orb.screens.LevelSelectScreen;
import com.jmolina.orb.screens.LoadScreen;
import com.jmolina.orb.screens.MainScreen;
import com.jmolina.orb.screens.OptionsScreen;
import com.jmolina.orb.screens.StatsScreen;

import java.util.ArrayList;

public class Orb extends Game {

	private LoadScreen gameLoadScreen;
	private MainScreen mainScreen;
	private OptionsScreen optionsScreen;
	private StatsScreen statsScreen;
	private CreditsScreen creditsScreen;
	private LevelSelectScreen levelSelectScreen;
	private LevelLaunchScreen levelLaunchScreen;

	private Logger logger;
	private ArrayList<BaseScreen> screens;
	private int i = 0;

	private Texture splashTexture;

	@Override
	public void create () {
		logger = new Logger("Game", Logger.INFO);
		Gdx.input.setCatchBackKey(true); // Android

		splashTexture = new Texture(Gdx.files.internal("splash.png"));

		gameLoadScreen = new LoadScreen(splashTexture);
		mainScreen = new MainScreen();
		optionsScreen = new OptionsScreen();
		statsScreen = new StatsScreen();
		creditsScreen = new CreditsScreen();
		levelSelectScreen = new LevelSelectScreen();
		levelLaunchScreen = new LevelLaunchScreen();

		screens = new ArrayList<BaseScreen>();

		screens.add(gameLoadScreen);
		screens.add(mainScreen);
		screens.add(optionsScreen);
		screens.add(statsScreen);
		screens.add(creditsScreen);
		screens.add(levelSelectScreen);
		screens.add(levelLaunchScreen);

		setScreen(gameLoadScreen);
	}

	@Override
	public void render () {
		if ((Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && Gdx.input.justTouched()) || Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			i++;
			int index = i % screens.size();
			setScreen(screens.get(index));
			// Gdx.input.setInputProcessor(screens.get(index).getStage());
			screens.get(index).setAsInputProcessor();
			logger.info("Screen change: " + screens.get(i % screens.size()).getClass());
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

}
