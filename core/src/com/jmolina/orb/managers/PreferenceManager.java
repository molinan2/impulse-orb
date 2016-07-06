package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.jmolina.orb.OrbApp;
import com.jmolina.orb.data.GameStats;

public class PreferenceManager {

    private final String FILE_NAME = OrbApp.class.getName();
    private final int OPTION_ZOOM_MIN = 0;
    private final int OPTION_ZOOM_MAX = 2;

    /** Opciones */
    private final String OPTION_MUSIC = "music";
    private final String OPTION_SOUND = "sound";
    private final String OPTION_VIBRATION = "vibration";
    private final String OPTION_ONLINE = "online";
    private final String OPTION_ZOOM = "zoom";

    /** Estadisticas */
    public static final String STAT_ATTEMPTS = "attempts";
    public static final String STAT_TIME = "time";
    public static final String STAT_DISTANCE = "distance";
    public static final String STAT_FAILS = "fails";
    public static final String STAT_MIN_TIME_ALIVE = "min_time";
    public static final String STAT_MAX_TIME_ALIVE = "max_time";
    public static final String STAT_MIN_DISTANCE_ALIVE = "min_distance";
    public static final String STAT_MAX_DISTANCE_ALIVE = "max_distance";
    public static final String STAT_AVERAGE_TIME_ALIVE = "average_time";
    public static final String STAT_AVERAGE_DISTANCE_ALIVE = "average_distance";

    private Preferences prefs;

    /**
     * Constructor
     */
    public PreferenceManager() {
        setPrefs(Gdx.app.getPreferences(FILE_NAME));
        checkPrefsValues();
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
    public void checkPrefsValues() {
        if (!prefs.contains(OPTION_MUSIC))
            prefs.putBoolean(OPTION_MUSIC, true);

        if (!prefs.contains(OPTION_SOUND))
            prefs.putBoolean(OPTION_SOUND, true);

        if (!prefs.contains(OPTION_VIBRATION))
            prefs.putBoolean(OPTION_VIBRATION, true);

        if (!prefs.contains(OPTION_ONLINE))
            prefs.putBoolean(OPTION_ONLINE, true);

        if (!prefs.contains(OPTION_ZOOM))
            prefs.putInteger(OPTION_ZOOM, 1);

        if (!prefs.contains(STAT_ATTEMPTS))
            prefs.putInteger(STAT_ATTEMPTS, 0);

        if (!prefs.contains(STAT_FAILS))
            prefs.putInteger(STAT_FAILS, 0);

        if (!prefs.contains(STAT_TIME))
            prefs.putFloat(STAT_TIME, 0f);

        if (!prefs.contains(STAT_DISTANCE))
            prefs.putFloat(STAT_DISTANCE, 0f);

        if (!prefs.contains(STAT_MIN_TIME_ALIVE))
            prefs.putFloat(STAT_MIN_TIME_ALIVE, 0f);

        if (!prefs.contains(STAT_MAX_TIME_ALIVE))
            prefs.putFloat(STAT_MAX_TIME_ALIVE, 0f);

        if (!prefs.contains(STAT_MIN_DISTANCE_ALIVE))
            prefs.putFloat(STAT_MIN_DISTANCE_ALIVE, 0f);

        if (!prefs.contains(STAT_MAX_DISTANCE_ALIVE))
            prefs.putFloat(STAT_MAX_DISTANCE_ALIVE, 0f);

        if (!prefs.contains(STAT_AVERAGE_TIME_ALIVE))
            prefs.putFloat(STAT_AVERAGE_TIME_ALIVE, 0f);

        if (!prefs.contains(STAT_AVERAGE_DISTANCE_ALIVE))
            prefs.putFloat(STAT_AVERAGE_DISTANCE_ALIVE, 0f);
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

    public int getOptionZoomMin () {
        return OPTION_ZOOM_MIN;
    }

    public int getOptionZoomMax () {
        return OPTION_ZOOM_MAX;
    }

    public void mergeGameStats (GameStats gameStats) {
        if (!gameStats.isEmpty()) {
            int attempts, fails;
            float time, distance, minTime, maxTime, minDistance, maxDistance, averageTimeAlive, averageDistanceAlive;

            time = gameStats.fullTime();
            distance = gameStats.fullDistance();
            fails = gameStats.fails();

            minTime = gameStats.minTimeAlive();
            minDistance = gameStats.minDistanceAlive();
            maxTime = gameStats.maxTimeAlive();
            maxDistance = gameStats.maxDistanceAlive();

            attempts = gameStats.countCompletedAttempts();
            averageTimeAlive = gameStats.averageTimeAlive();
            averageDistanceAlive = gameStats.averageDistanceAlive();

            prefs.putFloat(
                    STAT_TIME,
                    prefs.getFloat(STAT_TIME) + time
            );

            prefs.putFloat(
                    STAT_DISTANCE,
                    prefs.getFloat(STAT_DISTANCE) + distance
            );

            prefs.putInteger(
                    STAT_FAILS,
                    prefs.getInteger(STAT_FAILS) + fails
            );


        }

        // calcula las nuevas stats
        // put stats
        // save
    }

}
