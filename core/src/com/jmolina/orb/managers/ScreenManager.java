package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.screens.Credits;
import com.jmolina.orb.screens.levels.Level1;
import com.jmolina.orb.screens.LevelLaunch;
import com.jmolina.orb.screens.LevelSelect;
import com.jmolina.orb.screens.LevelSuccess;
import com.jmolina.orb.screens.Load;
import com.jmolina.orb.screens.Main;
import com.jmolina.orb.screens.Options;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.Stats;
import com.jmolina.orb.screens.levels.Level2;
import com.jmolina.orb.screens.levels.Level3;
import com.jmolina.orb.screens.levels.Level4;
import com.jmolina.orb.screens.levels.Level5;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

public class ScreenManager {

    /** Todas las pantallas de menu */
    public enum Key {
        LOAD, MAIN,
        OPTIONS, STATS, CREDITS, LEVEL_SELECT,
        LEVEL_LAUNCH_1, LEVEL_LAUNCH_2, LEVEL_LAUNCH_3, LEVEL_LAUNCH_4, LEVEL_LAUNCH_5,
        LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5,
        LEVEL_SUCCESS_1, LEVEL_SUCCESS_2, LEVEL_SUCCESS_3, LEVEL_SUCCESS_4, LEVEL_SUCCESS_5
    }

    private SuperManager superManager;
    private BaseScreen screen;


    public ScreenManager(SuperManager superManager) {
        this.superManager = superManager;
    }

    private SuperManager getSuperManager() {
        return this.superManager;
    }

    private BaseScreen getCurrent() {
        return this.screen;
    }

    private void setCurrent(BaseScreen screen) {
        this.screen = screen;
    }

    private void setScreen(BaseScreen baseScreen) {
        this.screen = baseScreen;
    }

    public void hideCurrent() {
        if (getCurrent() != null) getCurrent().hide();
    }

    public void showCurrent() {
        if (getCurrent() != null) getCurrent().show();
    }

    public void render() {
        if (getCurrent() != null) getCurrent().render(Gdx.graphics.getDeltaTime());
    }

    public void resize(int width, int height) {
        if (getCurrent() != null) getCurrent().resize(width, height);
    }

    public void resume() {
        getCurrent().resume();
    }

    public void pause() {
        getCurrent().pause();
    }

    public void dispose() {
        if (getCurrent() != null) getCurrent().hide();
        getCurrent().dispose();
    }

    public void back() {
        getCurrent().back();
    }

    public void switchToScreen(Key key, BaseScreen.Hierarchy hierarchy) {
        if (getCurrent() != null) {
            hideCurrent();
            getCurrent().dispose();
        }

        setScreen(newScreen(key));
        getCurrent().setAsInputProcessor();
        getCurrent().setHierarchy(hierarchy);

        if (getCurrent() != null) {
            showCurrent();
            resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    public BaseScreen newScreen(Key key) {
        switch (key) {
            case LOAD: return new Load(getSuperManager(), LOAD);
            case MAIN: return new Main(getSuperManager(), MAIN);
            case OPTIONS: return new Options(getSuperManager(), OPTIONS);
            case STATS: return new Stats(getSuperManager(), STATS);
            case CREDITS: return new Credits(getSuperManager(), CREDITS);
            case LEVEL_SELECT: return new LevelSelect(getSuperManager(), LEVEL_SELECT);
            case LEVEL_LAUNCH_1: return new LevelLaunch(getSuperManager(), LEVEL_LAUNCH_1, LEVEL_1, "BASICS");
            case LEVEL_LAUNCH_2: return new LevelLaunch(getSuperManager(), LEVEL_LAUNCH_2, LEVEL_2, "ADVANCED");
            case LEVEL_LAUNCH_3: return new LevelLaunch(getSuperManager(), LEVEL_LAUNCH_3, LEVEL_3, "EXPERT");
            case LEVEL_LAUNCH_4: return new LevelLaunch(getSuperManager(), LEVEL_LAUNCH_4, LEVEL_4, "HERO");
            case LEVEL_LAUNCH_5: return new LevelLaunch(getSuperManager(), LEVEL_LAUNCH_5, LEVEL_5, "GOD");
            case LEVEL_1: return new Level1(getSuperManager(), LEVEL_1);
            case LEVEL_2: return new Level2(getSuperManager(), LEVEL_2);
            case LEVEL_3: return new Level3(getSuperManager(), LEVEL_3);
            case LEVEL_4: return new Level4(getSuperManager(), LEVEL_4);
            case LEVEL_5: return new Level5(getSuperManager(), LEVEL_5);
            case LEVEL_SUCCESS_1: return new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_1, LEVEL_LAUNCH_1, "BASICS");
            case LEVEL_SUCCESS_2: return new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_2, LEVEL_LAUNCH_2, "ADVANCED");
            case LEVEL_SUCCESS_3: return new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_3, LEVEL_LAUNCH_3, "EXPERT");
            case LEVEL_SUCCESS_4: return new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_4, LEVEL_LAUNCH_4, "HERO");
            case LEVEL_SUCCESS_5: return new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_5, LEVEL_LAUNCH_5, "GOD");
            default: return new Main(getSuperManager(), MAIN);
        }
    }

}
