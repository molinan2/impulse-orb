package com.jmolina.orb.listeners;

import com.badlogic.gdx.input.GestureDetector;
import com.jmolina.orb.screens.Level;

public class GestureHandler extends GestureDetector.GestureAdapter {

    private Level level;

    public GestureHandler(Level level) {
        this.level = level;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        level.tapEvent();
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        level.flingEvent(velocityX, velocityY);
        return false;
    }

}
