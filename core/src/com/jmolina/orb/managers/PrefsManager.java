package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.jmolina.orb.OrbApp;
import com.jmolina.orb.data.GameStats;
import com.jmolina.orb.data.PersonalTimes;
import com.jmolina.orb.var.Var;

public class PrefsManager {

    private final String FILE_NAME = OrbApp.class.getPackage().getName() + ".settings";
    public static final int OPTION_ZOOM_MIN = 0;
    public static final int OPTION_ZOOM_DEFAULT = 1;
    public static final int OPTION_ZOOM_MAX = 2;

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

    private Preferences prefs;

    /**
     * Constructor
     */
    public PrefsManager() {
        setPrefs(Gdx.app.getPreferences(FILE_NAME));
        initialize();
        save();
    }

    public void setPrefs(Preferences prefs) {
        this.prefs = prefs;
    }

    public Preferences getPrefs() {
        return this.prefs;
    }

    /**
     * Si alguna preferencia no esta configurada, la guarda con un valor por defecto
     */
    public void initialize() {
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

    public boolean getOptionMusic() {
        return getPrefs().getBoolean(OPTION_MUSIC);
    }

    public boolean getOptionSound() {
        return getPrefs().getBoolean(OPTION_SOUND);
    }

    public boolean getOptionVibration() {
        return getPrefs().getBoolean(OPTION_VIBRATION);
    }

    public boolean getOptionOnline() {
        return getPrefs().getBoolean(OPTION_ONLINE);
    }

    public int getOptionZoom() {
        return MathUtils.clamp(
                getPrefs().getInteger(OPTION_ZOOM),
                OPTION_ZOOM_MIN,
                OPTION_ZOOM_MAX
        );
    }

    public void putOptionMusic(boolean isEnabled) {
        getPrefs().putBoolean(OPTION_MUSIC, isEnabled);
    }

    public void putOptionSound(boolean isEnabled) {
        getPrefs().putBoolean(OPTION_SOUND, isEnabled);
    }

    public void putOptionVibration(boolean isEnabled) {
        getPrefs().putBoolean(OPTION_VIBRATION, isEnabled);
    }

    public void putOptionOnline(boolean isEnabled) {
        getPrefs().putBoolean(OPTION_ONLINE, isEnabled);
    }

    public void putOptionZoom(int zoom) {
        getPrefs().putInteger(OPTION_ZOOM, MathUtils.clamp(
                zoom,
                OPTION_ZOOM_MIN,
                OPTION_ZOOM_MAX
        ));
    }

    public void save() {
        getPrefs().flush();
    }

    public void saveGameStats(GameStats stats, ScreenManager.Key level) {
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
            PersonalTimes personalTimes = new PersonalTimes(prefs, level);
            personalTimes.addAttempt(stats.getLastAttempt());
            personalTimes.put();

            save();
        }
    }

}
