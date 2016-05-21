package com.jmolina.orb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Logger;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.CreditsScreen;
import com.jmolina.orb.screens.ScrollScreen;
import com.jmolina.orb.screens.LevelLaunchScreen;
import com.jmolina.orb.screens.LevelSelectScreen;
import com.jmolina.orb.screens.LoadScreen;
import com.jmolina.orb.screens.MainScreen;
import com.jmolina.orb.screens.MenuScreen;
import com.jmolina.orb.screens.OptionsScreen;
import com.jmolina.orb.screens.StatsScreen;
import com.jmolina.orb.screens.TableTestScreen;
import com.jmolina.orb.screens.TestScreen;

import java.util.ArrayList;

public class Orb extends Game {

	private BaseScreen baseScreen;
	private LoadScreen loadScreen;
	private MainScreen mainScreen;
	private MenuScreen menuScreen;
	private OptionsScreen optionsScreen;
	private StatsScreen statsScreen;
	private CreditsScreen creditsScreen;
	private LevelSelectScreen levelSelectScreen;
	private LevelLaunchScreen levelLaunchScreen;
	private ScrollScreen scrollScreen1, scrollScreen2;
	private TableTestScreen tableScreen;
	private TestScreen testScreen;

	private Logger logger;
	private ArrayList<BaseScreen> screens;
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
		scrollScreen1 = new ScrollScreen();
		scrollScreen2 = new ScrollScreen();
		tableScreen = new TableTestScreen();
		testScreen = new TestScreen();

		screens = new ArrayList<BaseScreen>();

		screens.add(baseScreen);
		screens.add(loadScreen);
		screens.add(mainScreen);
		screens.add(menuScreen);
		screens.add(optionsScreen);
		screens.add(statsScreen);
		screens.add(creditsScreen);
		screens.add(levelSelectScreen);
		screens.add(levelLaunchScreen);
		screens.add(scrollScreen1);
		screens.add(scrollScreen2);
		screens.add(tableScreen);
		screens.add(testScreen);

		setScreen(testScreen);
	}

	@Override
	public void render () {
		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && Gdx.input.justTouched()) {
			i++;
			int index = i % screens.size();
			setScreen(screens.get(index));
			Gdx.input.setInputProcessor(screens.get(index).getStage());
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
		loadScreen.dispose();
		menuScreen.dispose();
		optionsScreen.dispose();
		statsScreen.dispose();
		creditsScreen.dispose();
		levelSelectScreen.dispose();
		levelLaunchScreen.dispose();
		scrollScreen1.dispose();
		scrollScreen2.dispose();
		tableScreen.dispose();
	}

}
