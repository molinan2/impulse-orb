/*
 * IMPULSE ORB
 * Copyright (C) 2016 Juan M. Molina
 *
 * This file is part of the IMPULSE ORB source code.
 *
 * IMPULSE ORB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IMPULSE ORB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.Credits;
import com.jmolina.orb.screens.Launch;
import com.jmolina.orb.screens.Load;
import com.jmolina.orb.screens.Main;
import com.jmolina.orb.screens.Options;
import com.jmolina.orb.screens.Select;
import com.jmolina.orb.screens.Stats;
import com.jmolina.orb.screens.Success;
import com.jmolina.orb.screens.levels.Level1;
import com.jmolina.orb.screens.levels.Level2;
import com.jmolina.orb.screens.levels.Level3;
import com.jmolina.orb.screens.levels.Level4;
import com.jmolina.orb.screens.levels.Level5;
import com.jmolina.orb.screens.levels.LevelTest1;
import com.jmolina.orb.screens.levels.LevelTest2;

import static com.jmolina.orb.managers.ScreenManager.Key.CREDITS;
import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_1;
import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_2;
import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_3;
import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_4;
import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_5;
import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_T1;
import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_T2;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_1;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_2;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_3;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_4;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_5;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_SELECT;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_T1;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_T2;
import static com.jmolina.orb.managers.ScreenManager.Key.LOAD;
import static com.jmolina.orb.managers.ScreenManager.Key.MAIN;
import static com.jmolina.orb.managers.ScreenManager.Key.OPTIONS;
import static com.jmolina.orb.managers.ScreenManager.Key.STATS;
import static com.jmolina.orb.managers.ScreenManager.Key.SUCCESS_1;
import static com.jmolina.orb.managers.ScreenManager.Key.SUCCESS_2;
import static com.jmolina.orb.managers.ScreenManager.Key.SUCCESS_3;
import static com.jmolina.orb.managers.ScreenManager.Key.SUCCESS_4;
import static com.jmolina.orb.managers.ScreenManager.Key.SUCCESS_5;
import static com.jmolina.orb.managers.ScreenManager.Key.SUCCESS_T1;

/**
 * Manager de pantallas de la aplicacion
 */
public class ScreenManager {

    /** Claves de todas las pantallas */
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

    /** Pantalla actual */
    private BaseScreen screen;


    /**
     * Constructor
     *
     * @param superManager SuperManager
     */
    public ScreenManager(SuperManager superManager) {
        this.superManager = superManager;
        switchToScreen(ScreenManager.Key.LOAD, BaseScreen.Hierarchy.LOWER);
    }

    /**
     * Devuelve el SuperManager
     */
    private SuperManager getSuperManager() {
        return this.superManager;
    }

    /**
     * Devuelve la pantalla actual
     */
    private BaseScreen getCurrent() {
        return this.screen;
    }

    /**
     * Fija la pantalla actual
     *
     * @param baseScreen Pantalla
     */
    private void setScreen(BaseScreen baseScreen) {
        this.screen = baseScreen;
    }

    /**
     * Oculta la pantalla actual
     */
    private void hideCurrent() {
        if (getCurrent() != null) getCurrent().hide();
    }

    /**
     * Muestra la pantalla actual
     */
    private void showCurrent() {
        if (getCurrent() != null) getCurrent().show();
    }

    /**
     * Renderiza la pantalla actual
     */
    public void render() {
        if (getCurrent() != null) getCurrent().render(Gdx.graphics.getDeltaTime());
    }

    /**
     * Cambia el tamaño de la pantalla actual. Esta llamada se hace desde el sistema operativo.
     *
     * @param width Ancho en pixeles
     * @param height Alto en pixeles
     */
    public void resize(int width, int height) {
        if (getCurrent() != null) getCurrent().resize(width, height);
    }

    /**
     * Reanuda la pantalla actual
     */
    public void resume() {
        getCurrent().resume();
    }

    /**
     * Pausa la pantalla actual
     */
    public void pause() {
        getCurrent().pause();
    }

    /**
     * Oculta la pantalla actual y libera sus recursos
     */
    public void dispose() {
        if (getCurrent() != null) getCurrent().hide();
        getCurrent().dispose();
    }

    /**
     * Cambia a una nueva pantalla. La antigua se destruye y la nueva se instancia
     *
     * @param key Clave de la nueva pantalla
     * @param hierarchy Jerarquia de la nueva pantalla respecto de la actual
     */
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
     * Crea una pantalla en funcion de la clave
     *
     * @param key Clave de pantalla
     */
    private BaseScreen createScreen(Key key) {
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
                return new Success(getSuperManager(), SUCCESS_1, "BASIC COMPLETED!");

            case SUCCESS_2:
                return new Success(getSuperManager(), SUCCESS_2, "ADVANCE COMPLETED!");

            case SUCCESS_3:
                return new Success(getSuperManager(), SUCCESS_3, "EXPERT COMPLETED!");

            case SUCCESS_4:
                return new Success(getSuperManager(), SUCCESS_4, "HERO COMPLETED!");

            case SUCCESS_5:
                return new Success(getSuperManager(), SUCCESS_5, "GOD COMPLETED!");

            case SUCCESS_T1:
                return new Success(getSuperManager(), SUCCESS_T1, "TEST1 COMPLETED!");

            case SUCCESS_T2:
                return new Success(getSuperManager(), SUCCESS_T1, "TEST2 COMPLETED!");

            default:
                Main main2 = new Main(getSuperManager());
                main2.setThisKey(MAIN);
                return main2;
        }
    }

}
