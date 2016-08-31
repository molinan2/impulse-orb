package com.jmolina.orb.listeners;

import com.badlogic.gdx.input.GestureDetector;
import com.jmolina.orb.interfaces.LevelManager;
import com.jmolina.orb.screens.Level;

/**
 * Listener de la entrada gestual en los niveles de juego
 */
public class GestureHandler extends GestureDetector.GestureAdapter {

    private LevelManager levelManager;

    /**
     * Constructor
     *
     * @param levelManager LevelManager
     */
    public GestureHandler(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        levelManager.freeze();
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        levelManager.impulse(velocityX, velocityY);
        return false;
    }

}
