package com.jmolina.orb.managers;

import com.jmolina.orb.screens.Level;

public class GameManager {

    // interface: pause, resume, restart, destroy, increaseGauge gauge, decrease gauge?
    // data: gauge,

    private Level currentLevel;

    public GameManager () {

    }

    public void setCurrentLevel (Level screen) {
        currentLevel = screen;
    }

}
