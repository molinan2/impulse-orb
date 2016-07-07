package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.screens.Credits;
import com.jmolina.orb.screens.Level1;
import com.jmolina.orb.screens.LevelLaunch;
import com.jmolina.orb.screens.LevelSelect;
import com.jmolina.orb.screens.Load;
import com.jmolina.orb.screens.Main;
import com.jmolina.orb.screens.Options;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.Stats;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

public class ScreenManager {

    /** Todas las pantallas de menu */
    public enum Key {
        LOAD, MAIN,
        OPTIONS, STATS, CREDITS, LEVEL_SELECT,
        LEVEL_LAUNCH_1, LEVEL_LAUNCH_2, LEVEL_LAUNCH_3, LEVEL_LAUNCH_4, LEVEL_LAUNCH_5,
        LEVEL_1
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

    private void setScreen(BaseScreen baseScreen) {
        this.screen = baseScreen;
    }

    public void hideCurrent() {
        if (getCurrent() != null)
            getCurrent().hide();
    }

    public void showCurrent() {
        if (getCurrent() != null)
            getCurrent().show();
    }

    public void render() {
        if (getCurrent() != null) {
            System.out.println("Screen Render 1: " + TimeUtils.nanoTime());
            getCurrent().render(Gdx.graphics.getDeltaTime());
            System.out.println("Screen Render 2: " + TimeUtils.nanoTime());
        }
    }

    public void resize(int width, int height) {
        if (getCurrent() != null)
            getCurrent().resize(width, height);
    }

    public void resume() {
        getCurrent().resume();
    }

    public void pause() {
        getCurrent().pause();
    }

    public void disposeAll() {
        if (getCurrent() != null)
            getCurrent().hide();

        getCurrent().dispose();
    }

    public void back() {
        getCurrent().back();
    }

    public void switchToScreen(Key key, BaseScreen.Hierarchy hierarchy) {
        if (getCurrent() != null){
            hideCurrent();
            System.out.println("Switch 1: " + TimeUtils.nanoTime());
            getCurrent().dispose();
            System.out.println("Switch 2: " + TimeUtils.nanoTime());
        }

        setScreen(newScreen(key));
        System.out.println("Switch 3: " + TimeUtils.nanoTime());
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
            case LEVEL_LAUNCH_1: return new LevelLaunch(getSuperManager(), LEVEL_LAUNCH_1, "BASICS");
            case LEVEL_LAUNCH_2: return new LevelLaunch(getSuperManager(), LEVEL_LAUNCH_2, "ADVANCED");
            case LEVEL_LAUNCH_3: return new LevelLaunch(getSuperManager(), LEVEL_LAUNCH_3, "EXPERT");
            case LEVEL_LAUNCH_4: return new LevelLaunch(getSuperManager(), LEVEL_LAUNCH_4, "HERO");
            case LEVEL_LAUNCH_5: return new LevelLaunch(getSuperManager(), LEVEL_LAUNCH_4, "GOD");
            case LEVEL_1: return new Level1(getSuperManager(), LEVEL_1);
            default: return new Main(getSuperManager(), MAIN);
        }
    }

}
