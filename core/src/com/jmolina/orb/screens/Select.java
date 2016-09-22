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

package com.jmolina.orb.screens;

import com.badlogic.gdx.Preferences;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.interfaces.Visitor;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.ui.Card;

import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_1;
import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_2;
import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_3;
import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_4;
import static com.jmolina.orb.managers.ScreenManager.Key.LAUNCH_5;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_1;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_2;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_3;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_4;
import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_5;
import static com.jmolina.orb.screens.BaseScreen.Hierarchy.LOWER;

/**
 * Pantalla de seleccion de nivel
 */
public class Select extends Menu {

    /** Fichas de nivel */
    private Card level1, level2, level3, level4, level5;

    /**
     * Constructor
     *
     * @param superManager SuperManager
     */
    public Select(SuperManager superManager) {
        super(superManager);

        setPreviousScreen(ScreenManager.Key.MAIN);
        setTitle("SELECT");

        level1 = new Card(getAssetManager(), getGameManager(), "BASIC", getFormattedBestTime(LEVEL_1), LEVEL_1);
        level2 = new Card(getAssetManager(), getGameManager(), "ADVANCE", getFormattedBestTime(LEVEL_2), LEVEL_2);
        level3 = new Card(getAssetManager(), getGameManager(), "EXPERT", getFormattedBestTime(LEVEL_3), LEVEL_3);
        level4 = new Card(getAssetManager(), getGameManager(), "HERO", getFormattedBestTime(LEVEL_4), LEVEL_4);
        level5 = new Card(getAssetManager(), getGameManager(), "GOD", getFormattedBestTime(LEVEL_5), LEVEL_5);

        level1.setOnClickOperation(visitor(LAUNCH_1));
        level2.setOnClickOperation(visitor(LAUNCH_2));
        level3.setOnClickOperation(visitor(LAUNCH_3));
        level4.setOnClickOperation(visitor(LAUNCH_4));
        level5.setOnClickOperation(visitor(LAUNCH_5));

        level1.unlock();
        if (getPrefsManager().isLevelCompleted(LEVEL_1)) level2.unlock();
        if (getPrefsManager().isLevelCompleted(LEVEL_2)) level3.unlock();
        if (getPrefsManager().isLevelCompleted(LEVEL_3)) level4.unlock();
        if (getPrefsManager().isLevelCompleted(LEVEL_4)) level5.unlock();

        add(level1);
        add(level2);
        add(level3);
        add(level4);
        add(level5);
    }

    /**
     * Devuelve un visitor que ejecuta un cambio de pantalla
     *
     * @param screen Pantalla a la que cambiar
     */
    private Visitor visitor(final ScreenManager.Key screen) {
        return new Visitor() {
            @Override
            public void run() {
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(screen, LOWER);
            }
        };
    }

    /**
     * Devuelve formateado el mejor tiempo del nivel
     *
     * @param key Clave de pantalla del nivel
     */
    private String getFormattedBestTime(ScreenManager.Key key) {
        float time = getBestTime(key);

        if (time > 0)
            return Utils.formatTime(getBestTime(key));
        else
            return "--";
    }

    /**
     * Devuelve el mejor tiempo del nivel
     *
     * @param key Clave de nivel
     */
    private Float getBestTime(ScreenManager.Key key) {
        Preferences prefs = getPrefsManager().getPrefs();

        switch (key) {
            case LEVEL_1: return prefs.getFloat(PrefsManager.LADDER_1_1);
            case LEVEL_2: return prefs.getFloat(PrefsManager.LADDER_2_1);
            case LEVEL_3: return prefs.getFloat(PrefsManager.LADDER_3_1);
            case LEVEL_4: return prefs.getFloat(PrefsManager.LADDER_4_1);
            case LEVEL_5: return prefs.getFloat(PrefsManager.LADDER_5_1);
            default: return 0f;
        }
    }

}
