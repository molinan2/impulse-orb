package com.jmolina.orb.runnables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.stages.HUDStage;

public abstract class GameRunnable {

    static public Runnable pauseWidgets(final HUDStage hudStage) {
        return new Runnable() {
            @Override
            public void run() {
                //
            }
        };
    }

}
