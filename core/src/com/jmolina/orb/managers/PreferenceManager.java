package com.jmolina.orb.managers;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class PreferenceManager {

    public final int ZOOM_MIN = 0;
    public final int ZOOM_MAX = 2;
    public final String MUSIC = "music";
    public final String SOUND = "sound";
    public final String VIBRATION = "vibration";
    public final String ONLINE = "online";
    public final String ZOOM = "zoom";

    private Preferences preferences;

    public PreferenceManager(Preferences preferences) {
        setPreferences(preferences);
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
