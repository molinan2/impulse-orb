package com.jmolina.orb.runnables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.jmolina.orb.Orb;
import com.jmolina.orb.screens.Base;

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

    static public Runnable setScreen(final Orb manager, final Orb.Name name, final Base.Hierarchy hierarchy) {
        return new Runnable() {
            @Override
            public void run() {
                manager.setScreenByKey(name, hierarchy);
            }
        };
    }

}
