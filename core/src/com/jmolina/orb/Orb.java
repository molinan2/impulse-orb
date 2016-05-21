package com.jmolina.orb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Logger;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.CreditsScreen;
import com.jmolina.orb.screens.LevelLaunchScreen;
import com.jmolina.orb.screens.LevelSelectScreen;
import com.jmolina.orb.screens.LoadScreen;
import com.jmolina.orb.screens.MainScreen;
import com.jmolina.orb.screens.OptionsScreen;
import com.jmolina.orb.screens.StatsScreen;
import com.jmolina.orb.screens.TestScreen;

import java.util.ArrayList;

public class Orb extends Game {

	private LoadScreen loadScreen;
	private MainScreen mainScreen;
	private OptionsScreen optionsScreen;
	private StatsScreen statsScreen;
	private CreditsScreen creditsScreen;
	private LevelSelectScreen levelSelectScreen;
	private LevelLaunchScreen levelLaunchScreen;
	private TestScreen testScreen;

	private Logger logger;
	private ArrayList<BaseScreen> screens;
	int i = 0;

	@Override
	public void create () {
		logger = new Logger("Game", Logger.INFO);

		loadScreen = new LoadScreen();
		mainScreen = new MainScreen();
		optionsScreen = new OptionsScreen();
		statsScreen = new StatsScreen();
		creditsScreen = new CreditsScreen();
		levelSelectScreen = new LevelSelectScreen();
		levelLaunchScreen = new LevelLaunchScreen();
		testScreen = new TestScreen();

		screens = new ArrayList<BaseScreen>();

		screens.add(loadScreen);
		screens.add(mainScreen);
		screens.add(optionsScreen);
		screens.add(statsScreen);
		screens.add(creditsScreen);
		screens.add(levelSelectScreen);
		screens.add(levelLaunchScreen);
		screens.add(testScreen);

		setScreen(loadScreen);
	}

	@Override
	public void render () {
		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && Gdx.input.justTouched()) {
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

		mainScreen.dispose();
		loadScreen.dispose();
		optionsScreen.dispose();
		statsScreen.dispose();
		creditsScreen.dispose();
		levelSelectScreen.dispose();
		levelLaunchScreen.dispose();
		testScreen.dispose();
	}

}
