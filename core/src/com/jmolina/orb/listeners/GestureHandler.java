package com.jmolina.orb.listeners;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.screens.LevelScreen;
import com.jmolina.orb.stages.GestureStage;
import com.jmolina.orb.stages.HUDStage;

public class GestureHandler extends GestureDetector.GestureAdapter {

    private LevelScreen level;

    public GestureHandler(LevelScreen level) {
        this.level = level;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        level.tap();
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        level.fling(velocityX, velocityY);
        return false;
    }

}
