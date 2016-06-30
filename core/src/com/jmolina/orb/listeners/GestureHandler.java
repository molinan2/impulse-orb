package com.jmolina.orb.listeners;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.screens.LevelScreen;
import com.jmolina.orb.stages.GestureStage;
import com.jmolina.orb.stages.HUDStage;

public class GestureHandler implements GestureDetector.GestureListener {

    private final float IMPULSE_RATIO_X = LevelScreen.RATIO_METER_PIXEL;
    private final float IMPULSE_RATIO_Y = -LevelScreen.RATIO_METER_PIXEL;
    private final float MAX_VELOCITY = 40.0f;

    private Orb orb;
    private GestureStage gestureStage;
    private HUDStage hudStage;

    public GestureHandler(GestureStage gestureStage, HUDStage hudStage, Orb orb) {
        this.orb = orb;
        this.gestureStage = gestureStage;
        this.hudStage = hudStage;
    }

    public Orb getOrb() {
        return this.orb;
    }


    /**
     * GestureListener methods
     */

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        getOrb().lock();
        gestureStage.tap();
        hudStage.increase(0.2f);
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        getOrb().unlock();
        getOrb().getBody().applyLinearImpulse(
                velocityX * IMPULSE_RATIO_X,
                velocityY * IMPULSE_RATIO_Y,
                getOrb().getBody().getPosition().x,
                getOrb().getBody().getPosition().y,
                true
        );

        gestureStage.fling();
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
    }

}
