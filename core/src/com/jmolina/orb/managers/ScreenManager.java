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
import com.jmolina.orb.screens.levels.LevelTest1;
import com.jmolina.orb.screens.levels.LevelTest2;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

public class ScreenManager {

    /** Todas las pantallas de menu */
    public enum Key {
        LOAD, MAIN,
        OPTIONS, STATS, CREDITS, LEVEL_SELECT,
        LEVEL_LAUNCH_1, LEVEL_LAUNCH_2, LEVEL_LAUNCH_3, LEVEL_LAUNCH_4, LEVEL_LAUNCH_5, LEVEL_LAUNCH_T1, LEVEL_LAUNCH_T2,
        LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_T1, LEVEL_T2,
        LEVEL_SUCCESS_1, LEVEL_SUCCESS_2, LEVEL_SUCCESS_3, LEVEL_SUCCESS_4, LEVEL_SUCCESS_5, LEVEL_SUCCESS_T1, LEVEL_SUCCESS_T2;

        @Override
        public String toString() {
            return super.toString().replace("_", " ");
        }
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

        setScreen(createScreen(key));
        getCurrent().setAsInputProcessor();
        getCurrent().setHierarchy(hierarchy);

        if (getCurrent() != null) {
            showCurrent();
            resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    /**
     *
     */
    public BaseScreen createScreen(Key key) {
        switch (key) {
            case LOAD:
                Load load = new Load(getSuperManager());
                load.setThisKey(LOAD);
                return load;

            case MAIN:
                Main main = new Main(getSuperManager());
                main.setThisKey(MAIN);
                return main;

            case OPTIONS:
                Options options = new Options(getSuperManager());
                options.setThisKey(OPTIONS);
                return options;

            case STATS:
                Stats stats = new Stats(getSuperManager());
                stats.setThisKey(STATS);
                return stats;

            case CREDITS:
                Credits credits = new Credits(getSuperManager());
                credits.setThisKey(CREDITS);
                return credits;

            case LEVEL_SELECT:
                LevelSelect levelSelect = new LevelSelect(getSuperManager());
                levelSelect.setThisKey(LEVEL_SELECT);
                return levelSelect;

            case LEVEL_LAUNCH_1:
                LevelLaunch levelLaunch1 = new LevelLaunch(getSuperManager(), LEVEL_1, "BASIC");
                levelLaunch1.setThisKey(LEVEL_LAUNCH_1);
                return levelLaunch1;

            case LEVEL_LAUNCH_2:
                LevelLaunch levelLaunch2 = new LevelLaunch(getSuperManager(), LEVEL_2, "ADVANCED");
                levelLaunch2.setThisKey(LEVEL_LAUNCH_2);
                return levelLaunch2;

            case LEVEL_LAUNCH_3:
                LevelLaunch levelLaunch3 = new LevelLaunch(getSuperManager(), LEVEL_3, "EXPERT");
                levelLaunch3.setThisKey(LEVEL_LAUNCH_3);
                return levelLaunch3;

            case LEVEL_LAUNCH_4:
                LevelLaunch levelLaunch4 = new LevelLaunch(getSuperManager(), LEVEL_4, "HERO");
                levelLaunch4.setThisKey(LEVEL_LAUNCH_4);
                return levelLaunch4;

            case LEVEL_LAUNCH_5:
                LevelLaunch levelLaunch5 = new LevelLaunch(getSuperManager(), LEVEL_5, "GOD");
                levelLaunch5.setThisKey(LEVEL_LAUNCH_5);
                return levelLaunch5;

            case LEVEL_LAUNCH_T1:
                LevelLaunch levelLaunchT1 = new LevelLaunch(getSuperManager(), LEVEL_T1, "TEST1");
                levelLaunchT1.setThisKey(LEVEL_LAUNCH_T1);
                return levelLaunchT1;

            case LEVEL_LAUNCH_T2:
                LevelLaunch levelLaunchT2 = new LevelLaunch(getSuperManager(), LEVEL_T2, "TEST2");
                levelLaunchT2.setThisKey(LEVEL_LAUNCH_T2);
                return levelLaunchT2;

            case LEVEL_1:
                Level1 level1 = new Level1(getSuperManager());
                level1.setThisKey(LEVEL_1);
                return level1;

            case LEVEL_2:
                Level2 level2 = new Level2(getSuperManager());
                level2.setThisKey(LEVEL_2);
                return level2;

            case LEVEL_3:
                Level3 level3 = new Level3(getSuperManager());
                level3.setThisKey(LEVEL_3);
                return level3;

            case LEVEL_4:
                Level4 level4 = new Level4(getSuperManager());
                level4.setThisKey(LEVEL_4);
                return level4;

            case LEVEL_5:
                Level5 level5 = new Level5(getSuperManager());
                level5.setThisKey(LEVEL_5);
                return level5;

            case LEVEL_T1:
                LevelTest1 levelTest1 = new LevelTest1(getSuperManager());
                levelTest1.setThisKey(LEVEL_T1);
                return levelTest1;

            case LEVEL_T2:
                LevelTest2 levelTest2 = new LevelTest2(getSuperManager());
                levelTest2.setThisKey(LEVEL_T2);
                return levelTest2;

            case LEVEL_SUCCESS_1:
                LevelSuccess levelSuccess1 = new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_1, "BASIC");
                return levelSuccess1;

            case LEVEL_SUCCESS_2:
                LevelSuccess levelSuccess2 = new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_2, "ADVANCED");
                return levelSuccess2;

            case LEVEL_SUCCESS_3:
                LevelSuccess levelSuccess3 = new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_3, "EXPERT");
                return levelSuccess3;

            case LEVEL_SUCCESS_4:
                LevelSuccess levelSuccess4 = new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_4, "HERO");
                return levelSuccess4;

            case LEVEL_SUCCESS_5:
                LevelSuccess levelSuccess5 = new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_5, "GOD");
                return levelSuccess5;

            case LEVEL_SUCCESS_T1:
                LevelSuccess levelSuccessT1 = new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_T1, "TEST1");
                return levelSuccessT1;

            case LEVEL_SUCCESS_T2:
                LevelSuccess levelSuccessT2 = new LevelSuccess(getSuperManager(), LEVEL_SUCCESS_T1, "TEST2");
                return levelSuccessT2;

            default:
                Main main2 = new Main(getSuperManager());
                main2.setThisKey(MAIN);
                return main2;
        }
    }

}
