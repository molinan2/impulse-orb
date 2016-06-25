package com.jmolina.orb.stages;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GestureStage extends Stage implements GestureDetector.GestureListener {

    public GestureStage() {
        super();
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        System.out.println("Touch up");

        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        System.out.println("Touch down");

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        System.out.println("Tap");

        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        System.out.println("Long press");

        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
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
