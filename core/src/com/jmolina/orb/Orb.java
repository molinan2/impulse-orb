package com.jmolina.orb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Logger;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.CreditsScreen;
import com.jmolina.orb.screens.Scroll2Screen;
import com.jmolina.orb.screens.ScrollScreen;
import com.jmolina.orb.screens.LevelLaunchScreen;
import com.jmolina.orb.screens.LevelSelectScreen;
import com.jmolina.orb.screens.LoadScreen;
import com.jmolina.orb.screens.MainScreen;
import com.jmolina.orb.screens.MenuScreen;
import com.jmolina.orb.screens.OptionsScreen;
import com.jmolina.orb.screens.StatsScreen;
import com.jmolina.orb.screens.TableTestScreen;

import java.util.ArrayList;

public class Orb extends Game {

	BaseScreen baseScreen;
	LoadScreen loadScreen;
	MainScreen mainScreen;
	MenuScreen menuScreen;
	OptionsScreen optionsScreen;
	StatsScreen statsScreen;
	CreditsScreen creditsScreen;
	LevelSelectScreen levelSelectScreen;
	LevelLaunchScreen levelLaunchScreen;
	ScrollScreen scrollScreen;
	TableTestScreen tableTestScreen;
	Scroll2Screen scroll2Screen;

	private Logger logger;
	private ArrayList<Screen> screens;
	int i = 0;

	@Override
	public void create () {
		logger = new Logger("Game", Logger.INFO);

		baseScreen = new BaseScreen();
		loadScreen = new LoadScreen();
		mainScreen = new MainScreen();
		menuScreen = new MenuScreen();
		optionsScreen = new OptionsScreen();
		statsScreen = new StatsScreen();
		creditsScreen = new CreditsScreen();
		levelSelectScreen = new LevelSelectScreen();
		levelLaunchScreen = new LevelLaunchScreen();
		scrollScreen = new ScrollScreen();
		tableTestScreen = new TableTestScreen();
		scroll2Screen = new Scroll2Screen();

		screens = new ArrayList<Screen>();

		screens.add(baseScreen);
		screens.add(loadScreen);
		screens.add(mainScreen);
		screens.add(menuScreen);
		screens.add(optionsScreen);
		screens.add(statsScreen);
		screens.add(creditsScreen);
		screens.add(levelSelectScreen);
		screens.add(levelLaunchScreen);
		screens.add(scrollScreen);
		screens.add(tableTestScreen);
		screens.add(scroll2Screen);

		setScreen(scroll2Screen);
	}

	@Override
	public void render () {
		if (Gdx.input.justTouched() && false) {
			i++;
			setScreen(screens.get(i % screens.size()));
			logger.info("Screen change: " + screens.get(i % screens.size()).getClass());
		}

		if (screen != null) {
			screen.render(Gdx.graphics.getDeltaTime());
		}
	}

	@Override
	public void dispose () {
		if (screen != null) screen.hide();

		baseScreen.dispose();
		mainScreen.dispose();
	}

}
