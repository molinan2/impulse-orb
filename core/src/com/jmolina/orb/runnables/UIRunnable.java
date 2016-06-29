package com.jmolina.orb.runnables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.BaseScreen;

public abstract class UIRunnable {

    static public Runnable enableTouchable(final Actor actor) {
        return new Runnable() {
            @Override
            public void run() {
                actor.setTouchable(Touchable.enabled);
            }
        };
    }

    static public Runnable disableTouchable(final Actor actor) {
        return new Runnable() {
            @Override
            public void run() {
                actor.setTouchable(Touchable.disabled);
            }
        };
    }

    static public Runnable setInputProcessor(final InputProcessor processor) {
        return new Runnable() {
            @Override
            public void run() {
                Gdx.input.setInputProcessor(processor);
            }
        };
    }

    static public Runnable exit() {
        return new Runnable() {
            @Override
            public void run() {
                Gdx.app.exit();
            }
        };
    }

    static public Runnable setScreen(final ScreenManager screenManager, final ScreenManager.Key key, final BaseScreen.Hierarchy hierarchy) {
        return new Runnable() {
            @Override
            public void run() {
                screenManager.switchToScreen(key, hierarchy);
            }
        };
    }

    static public Runnable backOperation(final BaseScreen screen) {
        return new Runnable() {
            @Override
            public void run() {
                screen.back();
            }
        };
    }

}
