package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ArrayMap;
import com.jmolina.orb.Orb;
import com.jmolina.orb.screens.Credits;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.screens.Level1;
import com.jmolina.orb.screens.LevelLaunch;
import com.jmolina.orb.screens.LevelSelect;
import com.jmolina.orb.screens.Load;
import com.jmolina.orb.screens.Main;
import com.jmolina.orb.screens.Options;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.Stats;

public class ScreenManager {

    /** Todas las pantallas de menu */
    public enum Key {
        LOAD, MAIN,
        OPTIONS, STATS, CREDITS, LEVEL_SELECT,
        LEVEL_LAUNCH_1, LEVEL_LAUNCH_2, LEVEL_LAUNCH_3, LEVEL_LAUNCH_4,
        LEVEL,
        LEVEL_1
    }

    private Orb orb;
    private BaseScreen screen;
    private ArrayMap<Key, BaseScreen> screens;


    public ScreenManager(Orb orb) {
        this.orb = orb;
        screens = new ArrayMap<Key, BaseScreen>();
    }

    private Orb getSuperManager() {
        return this.orb;
    }

    private BaseScreen getCurrentScreen() {
        return this.screen;
    }

    private void setScreen(BaseScreen baseScreen) {
        this.screen = baseScreen;
    }

    public void hideCurrentScreen() {
        if (getCurrentScreen() != null)
            getCurrentScreen().hide();
    }

    public void showCurrentScreen() {
        if (getCurrentScreen() != null)
            getCurrentScreen().show();
    }

    public void render() {
        if (getCurrentScreen() != null)
            getCurrentScreen().render(Gdx.graphics.getDeltaTime());
    }

    public void resize(int width, int height) {
        if (getCurrentScreen() != null)
            getCurrentScreen().resize(width, height);
    }

    public void resume() {
        getCurrentScreen().resume();
    }

    public void pause() {
        getCurrentScreen().pause();
    }

    public void disposeAll() {
        if (getCurrentScreen() != null)
            getCurrentScreen().hide();

        for (Key key : Key.values())
            getScreen(key).dispose();
    }

    public void createLoadScreen() {
        screens.put(Key.LOAD, new Load(getSuperManager()));
    }

    public void createMenuScreens() {
        screens.put(Key.MAIN, new Main(getSuperManager()));
        screens.put(Key.OPTIONS, new Options(getSuperManager()));
        screens.put(Key.STATS, new Stats(getSuperManager()));
        screens.put(Key.CREDITS, new Credits(getSuperManager()));
        screens.put(Key.LEVEL_SELECT, new LevelSelect(getSuperManager()));
        screens.put(Key.LEVEL_LAUNCH_1, new LevelLaunch(getSuperManager(), "BASICS"));
        screens.put(Key.LEVEL_LAUNCH_2, new LevelLaunch(getSuperManager(), "ADVANCED"));
        screens.put(Key.LEVEL_LAUNCH_3, new LevelLaunch(getSuperManager(), "EXPERT"));
        screens.put(Key.LEVEL_LAUNCH_4, new LevelLaunch(getSuperManager(), "HERO"));
        screens.put(Key.LEVEL, new Level(getSuperManager()));
        screens.put(Key.LEVEL_1, new Level1(getSuperManager()));
    }

    public void back() {
        getCurrentScreen().back();
    }

    public void switchToScreen(Key key, BaseScreen.Hierarchy hierarchy) {
        if (getCurrentScreen() != null)
            hideCurrentScreen();

        setScreen(screens.get(key));
        getCurrentScreen().setAsInputProcessor();
        getCurrentScreen().setHierarchy(hierarchy);

        if (getCurrentScreen() != null) {
            showCurrentScreen();
            resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    public BaseScreen getScreen(Key key) {
        return this.screens.get(key);
    }

}
