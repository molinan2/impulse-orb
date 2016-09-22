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
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.jmolina.orb.ImpulseOrb;
import com.jmolina.orb.data.GameStats;
import com.jmolina.orb.data.TopTimes;
import com.jmolina.orb.var.Var;

/**
 * Manager de preferencias guardadas en almacenamiento externo
 */
public class PrefsManager {

    public static final String FILE_NAME = ImpulseOrb.class.getPackage().getName() + ".settings";
    public static final int OPTION_ZOOM_MIN = 0;
    public static final int OPTION_ZOOM_DEFAULT = 1;
    public static final int OPTION_ZOOM_MAX = 2;

    /** Codigos de texto de cada preferencia */
    private final String VERSION = "version";
    private final String OPTION_MUSIC = "music";
    private final String OPTION_SOUND = "sound";
    private final String OPTION_VIBRATION = "vibration";
    private final String OPTION_ONLINE = "online";
    private final String OPTION_ZOOM = "zoom";
    public static final String STAT_TIME = "time";
    public static final String STAT_DISTANCE = "distance";
    public static final String STAT_FAILS = "fails";
    public static final String STAT_SUCCESSES = "successes";
    public static final String STAT_COMPLETED_ATTEMPTS = "completed_attempts";
    public static final String STAT_MIN_TIME_ALIVE = "min_time";
    public static final String STAT_MAX_TIME_ALIVE = "max_time";
    public static final String STAT_MIN_DISTANCE_ALIVE = "min_distance";
    public static final String STAT_MAX_DISTANCE_ALIVE = "max_distance";
    public static final String STAT_AVG_TIME_ALIVE = "avg_time";
    public static final String STAT_AVG_DISTANCE_ALIVE = "avg_distance";
    public static final String LADDER_1_1 = "ladder_1_1";
    public static final String LADDER_1_2 = "ladder_1_2";
    public static final String LADDER_1_3 = "ladder_1_3";
    public static final String LADDER_2_1 = "ladder_2_1";
    public static final String LADDER_2_2 = "ladder_2_2";
    public static final String LADDER_2_3 = "ladder_2_3";
    public static final String LADDER_3_1 = "ladder_3_1";
    public static final String LADDER_3_2 = "ladder_3_2";
    public static final String LADDER_3_3 = "ladder_3_3";
    public static final String LADDER_4_1 = "ladder_4_1";
    public static final String LADDER_4_2 = "ladder_4_2";
    public static final String LADDER_4_3 = "ladder_4_3";
    public static final String LADDER_5_1 = "ladder_5_1";
    public static final String LADDER_5_2 = "ladder_5_2";
    public static final String LADDER_5_3 = "ladder_5_3";
    public static final String LADDER_T1_1 = "ladder_test1_3";
    public static final String LADDER_TEST1_2 = "ladder_test1_3";
    public static final String LADDER_TEST1_3 = "ladder_test1_3";
    public static final String LADDER_T2_1 = "ladder_test2_3";
    public static final String LADDER_TEST2_2 = "ladder_test2_3";
    public static final String LADDER_TEST2_3 = "ladder_test2_3";

    /** Preferencias */
    private Preferences prefs;

    /**
     * Constructor
     */
    public PrefsManager() {
        this.prefs = Gdx.app.getPreferences(FILE_NAME);
        initialize();
        save();
    }

    /**
     * Devuelve las preferencias
     */
    public Preferences getPrefs() {
        return this.prefs;
    }

    /**
     * Si alguna preferencia no esta configurada, la guarda con un valor por defecto
     */
    private void initialize() {
        if (!prefs.contains(VERSION)) prefs.putString(VERSION, Var.APP_VERSION);
        if (!prefs.contains(OPTION_MUSIC)) prefs.putBoolean(OPTION_MUSIC, true);
        if (!prefs.contains(OPTION_SOUND)) prefs.putBoolean(OPTION_SOUND, true);
        if (!prefs.contains(OPTION_VIBRATION)) prefs.putBoolean(OPTION_VIBRATION, true);
        if (!prefs.contains(OPTION_ONLINE)) prefs.putBoolean(OPTION_ONLINE, true);
        if (!prefs.contains(OPTION_ZOOM)) prefs.putInteger(OPTION_ZOOM, 1);
        if (!prefs.contains(STAT_COMPLETED_ATTEMPTS)) prefs.putInteger(STAT_COMPLETED_ATTEMPTS, 0);
        if (!prefs.contains(STAT_FAILS)) prefs.putInteger(STAT_FAILS, 0);
        if (!prefs.contains(STAT_SUCCESSES)) prefs.putInteger(STAT_SUCCESSES, 0);
        if (!prefs.contains(STAT_TIME)) prefs.putFloat(STAT_TIME, 0f);
        if (!prefs.contains(STAT_DISTANCE)) prefs.putFloat(STAT_DISTANCE, 0f);
        if (!prefs.contains(STAT_MIN_TIME_ALIVE)) prefs.putFloat(STAT_MIN_TIME_ALIVE, 0f);
        if (!prefs.contains(STAT_MAX_TIME_ALIVE)) prefs.putFloat(STAT_MAX_TIME_ALIVE, 0f);
        if (!prefs.contains(STAT_MIN_DISTANCE_ALIVE)) prefs.putFloat(STAT_MIN_DISTANCE_ALIVE, 0f);
        if (!prefs.contains(STAT_MAX_DISTANCE_ALIVE)) prefs.putFloat(STAT_MAX_DISTANCE_ALIVE, 0f);
        if (!prefs.contains(STAT_AVG_TIME_ALIVE)) prefs.putFloat(STAT_AVG_TIME_ALIVE, 0f);
        if (!prefs.contains(STAT_AVG_DISTANCE_ALIVE)) prefs.putFloat(STAT_AVG_DISTANCE_ALIVE, 0f);
    }

    /**
     * Devuelve el valor de la opcion "musica"
     */
    public boolean getOptionMusic() {
        return getPrefs().getBoolean(OPTION_MUSIC);
    }

    /**
     * Devuelve el valor de la opcion "sonido"
     */
    public boolean getOptionSound() {
        return getPrefs().getBoolean(OPTION_SOUND);
    }

    /**
     * Devuelve el valor de la opcion "vibracion"
     */
    public boolean getOptionVibration() {
        return getPrefs().getBoolean(OPTION_VIBRATION);
    }

    /**
     * Devuelve el valor de la opcion "online"
     */
    public boolean getOptionOnline() {
        return getPrefs().getBoolean(OPTION_ONLINE);
    }

    /**
     * Devuelve el valor de la opcion "zoom"
     */
    public int getOptionZoom() {
        return MathUtils.clamp(
                getPrefs().getInteger(OPTION_ZOOM),
                OPTION_ZOOM_MIN,
                OPTION_ZOOM_MAX
        );
    }

    /**
     * Fija el valor de la opcion "musica"
     */
    public void putOptionMusic(boolean isEnabled) {
        getPrefs().putBoolean(OPTION_MUSIC, isEnabled);
    }

    /**
     * Fija el valor de la opcion "sonido"
     */
    public void putOptionSound(boolean isEnabled) {
        getPrefs().putBoolean(OPTION_SOUND, isEnabled);
    }

    /**
     * Fija el valor de la opcion "vibracion"
     */
    public void putOptionVibration(boolean isEnabled) {
        getPrefs().putBoolean(OPTION_VIBRATION, isEnabled);
    }

    /**
     * Fija el valor de la opcion "online"
     */
    public void putOptionOnline(boolean isEnabled) {
        getPrefs().putBoolean(OPTION_ONLINE, isEnabled);
    }

    /**
     * Fija el valor de la opcion "zoom"
     */
    public void putOptionZoom(int zoom) {
        getPrefs().putInteger(OPTION_ZOOM, MathUtils.clamp(
                zoom,
                OPTION_ZOOM_MIN,
                OPTION_ZOOM_MAX
        ));
    }

    /**
     * Guarda las preferencias en el almacenamiento externo
     */
    public void save() {
        getPrefs().flush();
    }

    /**
     * Lee las estadisticas de juego para un nivel y actualiza los valores del almacenamiento externo
     *
     * @param stats Estadisticas de juego
     * @param level Nivel de juego al que corresponden
     *
     * @return El rango conseguido por el tiempo del intento exitoso
     */
    public int saveStats(GameStats stats, ScreenManager.Key level) {
        int rank = 0;

        if (!stats.isEmpty()) {
            prefs.putFloat(STAT_TIME, prefs.getFloat(STAT_TIME) + stats.fullTime());
            prefs.putFloat(STAT_DISTANCE, prefs.getFloat(STAT_DISTANCE) + stats.fullDistance());
            prefs.putInteger(STAT_FAILS, prefs.getInteger(STAT_FAILS) + stats.fails());
            prefs.putInteger(STAT_SUCCESSES, prefs.getInteger(STAT_SUCCESSES) + stats.successes());

            int completedAttempts = stats.completedAttempts().size();

            if (completedAttempts > 0) {
                float minTimeAlive = stats.minTimeAlive();
                float maxTimeAlive = stats.maxTimeAlive();
                float avgTimeAlive = stats.averageTimeAlive();
                float minDistanceAlive = stats.minDistanceAlive();
                float maxDistanceAlive = stats.maxDistanceAlive();
                float avgDistanceAlive = stats.averageDistanceAlive();

                int savedAttempts = prefs.getInteger(STAT_COMPLETED_ATTEMPTS);
                float savedAvgTimeAlive = prefs.getFloat(STAT_AVG_TIME_ALIVE);
                float savedAvgDistanceAlive = prefs.getFloat(STAT_AVG_DISTANCE_ALIVE);

                if (minTimeAlive < prefs.getFloat(STAT_MIN_TIME_ALIVE) || savedAttempts == 0)
                    prefs.putFloat(STAT_MIN_TIME_ALIVE, minTimeAlive);

                if (minDistanceAlive < prefs.getFloat(STAT_MIN_DISTANCE_ALIVE) || savedAttempts == 0)
                    prefs.putFloat(STAT_MIN_DISTANCE_ALIVE, minDistanceAlive);

                if (maxTimeAlive > prefs.getFloat(STAT_MAX_TIME_ALIVE))
                    prefs.putFloat(STAT_MAX_TIME_ALIVE, maxTimeAlive);

                if (maxDistanceAlive > prefs.getFloat(STAT_MAX_DISTANCE_ALIVE))
                    prefs.putFloat(STAT_MAX_DISTANCE_ALIVE, maxDistanceAlive);

                float wAvgTimeAlive = ((float) savedAttempts * savedAvgTimeAlive + completedAttempts * avgTimeAlive) / ((float) savedAttempts + completedAttempts);
                float wAvgDistanceAlive = ((float) savedAttempts * savedAvgDistanceAlive + completedAttempts * avgDistanceAlive) / ((float) savedAttempts + completedAttempts);

                prefs.putFloat(STAT_AVG_TIME_ALIVE, wAvgTimeAlive);
                prefs.putFloat(STAT_AVG_DISTANCE_ALIVE, wAvgDistanceAlive);
                prefs.putInteger(STAT_COMPLETED_ATTEMPTS, prefs.getInteger(STAT_COMPLETED_ATTEMPTS) + completedAttempts);
            }

            // Guarda los mejores tiempos
            TopTimes times = new TopTimes(prefs, level);
            if (stats.getCurrentAttempt() != null)
                rank = times.addAttempt(stats.getCurrentAttempt());

            save();
        }

        return rank;
    }

    /**
     * Indica si un nivel ha sido previamente completado
     *
     * @param level Nivel
     */
    public boolean isLevelCompleted(ScreenManager.Key level) {
        switch (level) {
            case LEVEL_1: if (prefs.contains(LADDER_1_1)) return true;
            case LEVEL_2: if (prefs.contains(LADDER_2_1)) return true;
            case LEVEL_3: if (prefs.contains(LADDER_3_1)) return true;
            case LEVEL_4: if (prefs.contains(LADDER_4_1)) return true;
            case LEVEL_5: if (prefs.contains(LADDER_5_1)) return true;
            default: return false;
        }
    }

}
