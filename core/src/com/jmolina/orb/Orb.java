package com.jmolina.orb;

import com.badlogic.gdx.Game;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.MainScreen;

public class Orb extends Game {
	BaseScreen baseScreen;
	MainScreen mainScreen;
	
	@Override
	public void create () {
		baseScreen = new BaseScreen();
		mainScreen = new MainScreen();
		setScreen(mainScreen);
	}

}
