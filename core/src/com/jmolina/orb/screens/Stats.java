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
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.ui.Heading;
import com.jmolina.orb.widgets.ui.Stat;

import static com.jmolina.orb.managers.PrefsManager.STAT_AVG_DISTANCE_ALIVE;
import static com.jmolina.orb.managers.PrefsManager.STAT_AVG_TIME_ALIVE;
import static com.jmolina.orb.managers.PrefsManager.STAT_COMPLETED_ATTEMPTS;
import static com.jmolina.orb.managers.PrefsManager.STAT_DISTANCE;
import static com.jmolina.orb.managers.PrefsManager.STAT_FAILS;
import static com.jmolina.orb.managers.PrefsManager.STAT_MAX_DISTANCE_ALIVE;
import static com.jmolina.orb.managers.PrefsManager.STAT_MAX_TIME_ALIVE;
import static com.jmolina.orb.managers.PrefsManager.STAT_MIN_DISTANCE_ALIVE;
import static com.jmolina.orb.managers.PrefsManager.STAT_MIN_TIME_ALIVE;
import static com.jmolina.orb.managers.PrefsManager.STAT_SUCCESSES;
import static com.jmolina.orb.managers.PrefsManager.STAT_TIME;

/**
 * Pantalla de estadisticas
 */
public class Stats extends Menu {

    /** Widgets de estadisticas */
    private Stat played, distance, fails, successes, avgTimeAlive, avgDistanceAlive,
            minTimeAlive, minDistanceAlive, maxTimeAlive, maxDistanceAlive;

    /** Widgets de encabezados */
    private Heading generalHeading, attemptsHeading, timeHeading, distanceHeading;

    /** Preferencias */
    private Preferences prefs;

    /**
     * Constructor
     *
     * @param superManager SuperManager
     */
    public Stats(SuperManager superManager) {
        super(superManager);

        setPreviousScreen(ScreenManager.Key.MAIN);
        setTitle("STATS");
        prefs = superManager.getPrefsManager().getPrefs();

        generalHeading = new Heading(getAssetManager(), "All attempts", Align.center, Heading.Weight.Bold);
        played = new Stat(getAssetManager(), "Time played");
        distance = new Stat(getAssetManager(), "Distance traveled");
        successes = new Stat(getAssetManager(), "Successful attempts");
        fails = new Stat(getAssetManager(), "Failed attempts");

        attemptsHeading = new Heading(getAssetManager(), "Completed attempts", Align.center, Heading.Weight.Bold);
        timeHeading = new Heading(getAssetManager(), "Time alive", Align.center);
        minTimeAlive = new Stat(getAssetManager(), "Minimum");
        avgTimeAlive = new Stat(getAssetManager(), "Average");
        maxTimeAlive = new Stat(getAssetManager(), "Maximum");

        distanceHeading = new Heading(getAssetManager(), "Distance alive", Align.center);
        minDistanceAlive = new Stat(getAssetManager(), "Minimum");
        avgDistanceAlive = new Stat(getAssetManager(), "Average");
        maxDistanceAlive = new Stat(getAssetManager(), "Maximum");

        updateStats();

        add(generalHeading, 0);
        add(played, 0);
        add(distance, 0);
        add(successes, 0);
        add(fails, 0.5f);

        add(attemptsHeading, 0);
        add(timeHeading, 0);
        add(minTimeAlive, 0);
        add(avgTimeAlive, 0);
        add(maxTimeAlive, 0.5f);

        add(distanceHeading, 0);
        add(minDistanceAlive, 0);
        add(avgDistanceAlive, 0);
        add(maxDistanceAlive, 0.5f);
    }

    @Override
    public void show() {
        updateStats();
        super.show();
    }

    /**
     * Actualiza los widgets de estadisticas, cargando desde el almacenamiento externo
     */
    private void updateStats() {
        played.setValue(prefs.getFloat(STAT_TIME), "s", false);
        distance.setValue(prefs.getFloat(STAT_DISTANCE), "m", false);
        fails.setValue(prefs.getInteger(STAT_FAILS));
        successes.setValue(prefs.getInteger(STAT_SUCCESSES));

        if (prefs.getInteger(STAT_COMPLETED_ATTEMPTS) == 0) {
            minTimeAlive.setNullValue("s");
            maxTimeAlive.setNullValue("s");
            avgTimeAlive.setNullValue("s");
            minDistanceAlive.setNullValue("m");
            maxDistanceAlive.setNullValue("m");
            avgDistanceAlive.setNullValue("m");
        }
        else {
            minTimeAlive.setValue(prefs.getFloat(STAT_MIN_TIME_ALIVE), "s");
            maxTimeAlive.setValue(prefs.getFloat(STAT_MAX_TIME_ALIVE), "s");
            avgTimeAlive.setValue(prefs.getFloat(STAT_AVG_TIME_ALIVE), "s");
            minDistanceAlive.setValue(prefs.getFloat(STAT_MIN_DISTANCE_ALIVE), "m");
            maxDistanceAlive.setValue(prefs.getFloat(STAT_MAX_DISTANCE_ALIVE), "m");
            avgDistanceAlive.setValue(prefs.getFloat(STAT_AVG_DISTANCE_ALIVE), "m");
        }
    }

}
