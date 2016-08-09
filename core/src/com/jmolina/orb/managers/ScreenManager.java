package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.screens.Credits;
import com.jmolina.orb.screens.levels.Level1;
import com.jmolina.orb.screens.Launch;
import com.jmolina.orb.screens.Select;
import com.jmolina.orb.screens.Success;
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
        LAUNCH_1, LAUNCH_2, LAUNCH_3, LAUNCH_4, LAUNCH_5, LAUNCH_T1, LAUNCH_T2,
        LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_T1, LEVEL_T2,
        SUCCESS_1, SUCCESS_2, SUCCESS_3, SUCCESS_4, SUCCESS_5, SUCCESS_T1, SUCCESS_T2;

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
                Select select = new Select(getSuperManager());
                select.setThisKey(LEVEL_SELECT);
                return select;

            case LAUNCH_1:
                Launch launch1 = new Launch(getSuperManager(), LEVEL_1, "BASIC");
                launch1.setThisKey(LAUNCH_1);
                return launch1;

            case LAUNCH_2:
                Launch launch2 = new Launch(getSuperManager(), LEVEL_2, "ADVANCE");
                launch2.setThisKey(LAUNCH_2);
                return launch2;

            case LAUNCH_3:
                Launch launch3 = new Launch(getSuperManager(), LEVEL_3, "EXPERT");
                launch3.setThisKey(LAUNCH_3);
                return launch3;

            case LAUNCH_4:
                Launch launch4 = new Launch(getSuperManager(), LEVEL_4, "HERO");
                launch4.setThisKey(LAUNCH_4);
                return launch4;

            case LAUNCH_5:
                Launch launch5 = new Launch(getSuperManager(), LEVEL_5, "GOD");
                launch5.setThisKey(LAUNCH_5);
                return launch5;

            case LAUNCH_T1:
                Launch launchT1 = new Launch(getSuperManager(), LEVEL_T1, "TEST1");
                launchT1.setThisKey(LAUNCH_T1);
                return launchT1;

            case LAUNCH_T2:
                Launch launchT2 = new Launch(getSuperManager(), LEVEL_T2, "TEST2");
                launchT2.setThisKey(LAUNCH_T2);
                return launchT2;

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

            case SUCCESS_1:
                Success success1 = new Success(getSuperManager(), SUCCESS_1, "BASIC COMPLETED!");
                return success1;

            case SUCCESS_2:
                Success success2 = new Success(getSuperManager(), SUCCESS_2, "ADVANCE COMPLETED!");
                return success2;

            case SUCCESS_3:
                Success success3 = new Success(getSuperManager(), SUCCESS_3, "EXPERT COMPLETED!");
                return success3;

            case SUCCESS_4:
                Success success4 = new Success(getSuperManager(), SUCCESS_4, "HERO COMPLETED!");
                return success4;

            case SUCCESS_5:
                Success success5 = new Success(getSuperManager(), SUCCESS_5, "GOD COMPLETED!");
                return success5;

            case SUCCESS_T1:
                Success successT1 = new Success(getSuperManager(), SUCCESS_T1, "TEST1 COMPLETED!");
                return successT1;

            case SUCCESS_T2:
                Success successT2 = new Success(getSuperManager(), SUCCESS_T1, "TEST2 COMPLETED!");
                return successT2;

            default:
                Main main2 = new Main(getSuperManager());
                main2.setThisKey(MAIN);
                return main2;
        }
    }

}
