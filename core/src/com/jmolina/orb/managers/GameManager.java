package com.jmolina.orb.managers;

import com.jmolina.orb.screens.LevelScreen;

public class GameManager {

    // interface: pause, resume, restart, destroy, increase gauge, decrease gauge?
    // data: gauge,

    private LevelScreen currentLevel;

    public GameManager () {

    }

    public void setCurrentLevel (LevelScreen screen) {
        currentLevel = screen;
    }

}
