package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.jmolina.orb.data.Attempt;
import com.jmolina.orb.screens.Level;

public class GameManager {

    // interface: pause, resume, restart, destroy, increaseGauge gauge, decrease gauge?
    // data: gauge,

    public static final int VIBRATION_SHORT = 5;
    public static final int VIBRATION_MEDIUM = 30;
    public static final int VIBRATION_LONG = 300;
    public static final float RATIO_METER_PIXEL = 0.015625f; // Grid: 12x18.5, 64 pixel/metro
    private final float ZOOM_RATIO = 1.66f;

    private PrefsManager prefsManager;
    private Level currentLevel;
    private Attempt lastSuccessfulAttempt;
    private boolean vibration = true;
    private boolean sound = true;
    private boolean music = true;
    private int zoom = PrefsManager.OPTION_ZOOM_DEFAULT;

    public GameManager (PrefsManager pm) {
        prefsManager = pm;
        lastSuccessfulAttempt = new Attempt();
        lastSuccessfulAttempt.setSuccessful(true);
        updateOptions();
    }

    public void updateOptions() {
        vibration = prefsManager.getOptionVibration();
        sound = prefsManager.getOptionSound();
        music = prefsManager.getOptionMusic();
        zoom = MathUtils.clamp(prefsManager.getOptionZoom(), PrefsManager.OPTION_ZOOM_MIN, PrefsManager.OPTION_ZOOM_MAX);
    }

    public void setCurrentLevel (Level screen) {
        currentLevel = screen;
    }

    public void setLastSuccessfulAttempt (Attempt attempt) {
        if (attempt != null) {
            lastSuccessfulAttempt.setDistance(attempt.getDistance());
            lastSuccessfulAttempt.setTime(attempt.getTime());
        }
    }

    public Attempt getLastSuccessfulAttempt () {
        return lastSuccessfulAttempt;
    }

    public void vibrateShort() {
        vibrate(VIBRATION_SHORT);
    }

    public void vibrateMedium() {
        vibrate(VIBRATION_MEDIUM);
    }

    public void vibrateLong() {
        vibrate(VIBRATION_LONG);
    }

    private void vibrate(int duration) {
        if (vibration)
            Gdx.input.vibrate(duration);
    }

    public float getRatioMeterPixel() {
        switch (zoom) {
            case 0: return RATIO_METER_PIXEL * ZOOM_RATIO;
            case 1: return RATIO_METER_PIXEL;
            case 2: return RATIO_METER_PIXEL / ZOOM_RATIO;
            default: return RATIO_METER_PIXEL;
        }
    }

    public float getZoomRatio () {
        switch (zoom) {
            case 0: return 1 / ZOOM_RATIO;
            case 1: return 1;
            case 2: return ZOOM_RATIO;
            default: return 1;
        }
    }

}
