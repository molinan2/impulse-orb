package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.jmolina.orb.OrbApp;

public class PreferenceManager {

    private final int ZOOM_MIN = 0;
    private final int ZOOM_MAX = 2;
    private final String MUSIC = "music";
    private final String SOUND = "sound";
    private final String VIBRATION = "vibration";
    private final String ONLINE = "online";
    private final String ZOOM = "zoom";

    private final String FILE_NAME = OrbApp.class.getName();

    private Preferences preferences;

    /**
     * Constructor
     */
    public PreferenceManager() {
        setPreferences(Gdx.app.getPreferences(FILE_NAME));
        checkPrefs();
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public Preferences getPreferences() {
        return this.preferences;
    }

    /**
     * Si alguna preferencia no esta configurada, la guarda con un valor por defecto
     */
    public void checkPrefs() {
        if (!preferences.contains(MUSIC))
            preferences.putBoolean(MUSIC, true);

        if (!preferences.contains(SOUND))
            preferences.putBoolean(SOUND, true);

        if (!preferences.contains(VIBRATION))
            preferences.putBoolean(VIBRATION, true);

        if (!preferences.contains(ONLINE))
            preferences.putBoolean(ONLINE, true);

        if (!preferences.contains(ZOOM))
            preferences.putInteger(ZOOM, 1);
    }

    public boolean getOptionMusic() {
        return getPreferences().getBoolean(MUSIC);
    }

    public boolean getOptionSound() {
        return getPreferences().getBoolean(SOUND);
    }

    public boolean getOptionVibration() {
        return getPreferences().getBoolean(VIBRATION);
    }

    public boolean getOptionOnline() {
        return getPreferences().getBoolean(ONLINE);
    }

    public int getOptionZoom() {
        return MathUtils.clamp(
                getPreferences().getInteger(ZOOM),
                ZOOM_MIN,
                ZOOM_MAX
        );
    }

    public void putOptionMusic(boolean isEnabled) {
        getPreferences().putBoolean(MUSIC, isEnabled);
    }

    public void putOptionSound(boolean isEnabled) {
        getPreferences().putBoolean(SOUND, isEnabled);
    }

    public void putOptionVibration(boolean isEnabled) {
        getPreferences().putBoolean(VIBRATION, isEnabled);
    }

    public void putOptionOnline(boolean isEnabled) {
        getPreferences().putBoolean(ONLINE, isEnabled);
    }

    public void putOptionZoom(int zoom) {
        getPreferences().putInteger(ZOOM, MathUtils.clamp(
                zoom,
                ZOOM_MIN,
                ZOOM_MAX
        ));
    }

    public void save() {
        getPreferences().flush();
    }

    public int getOptionZoomMin () {
        return ZOOM_MIN;
    }

    public int getOptionZoomMax () {
        return ZOOM_MAX;
    }

}
