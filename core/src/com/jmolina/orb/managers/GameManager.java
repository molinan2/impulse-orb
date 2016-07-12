package com.jmolina.orb.managers;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.data.Attempt;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.screens.Level;

public class GameManager {

    // interface: pause, resume, restart, destroySound, increaseGauge gauge, decrease gauge?
    // data: gauge,

    public enum Fx {
        Back, Button, Collision, Destroy, Exit, Fling, Init, Option, Tap
    }

    public static final int VIBRATION_SHORT = 5;
    public static final int VIBRATION_MEDIUM = 30;
    public static final int VIBRATION_LONG = 300;
    public static final float RATIO_METER_PIXEL = 0.015625f; // Grid: 12x18.5, 64 pixel/metro
    private final float ZOOM_RATIO = 1.66f;
    private final float DEFAULT_SOUND_VOLUME = 0.2f;

    private PrefsManager prefsManager;
    private SuperManager superManager;
    private Level currentLevel;
    private Attempt lastSuccessfulAttempt;
    private boolean vibration = true;
    private boolean sound = true;
    private boolean music = true;
    private int zoom = PrefsManager.OPTION_ZOOM_DEFAULT;

    private Sound backSound, buttonSound, collisionSound, destroySound, exitSound, flingSound, initSound, optionSound, tapSound;

    /**
     * Constructor
     */
    public GameManager (SuperManager sm) {
        prefsManager = sm.getPrefsManager();
        superManager = sm;
        lastSuccessfulAttempt = new Attempt();
        lastSuccessfulAttempt.setSuccessful(true);
        updateOptions();
        createSounds();
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

    private void createSounds() {
        backSound = getAsset(Asset.SOUND_BACK, Sound.class);
        buttonSound = getAsset(Asset.SOUND_BUTTON, Sound.class);
        collisionSound = getAsset(Asset.SOUND_COLLISION, Sound.class);
        destroySound = getAsset(Asset.SOUND_DESTROY, Sound.class);
        exitSound = getAsset(Asset.SOUND_EXIT, Sound.class);
        flingSound = getAsset(Asset.SOUND_FLING, Sound.class);
        initSound = getAsset(Asset.SOUND_INIT, Sound.class);
        optionSound = getAsset(Asset.SOUND_OPTION, Sound.class);
        tapSound = getAsset(Asset.SOUND_TAP, Sound.class);
    }

    public void play(Fx fx) {
        if (sound) {
            switch (fx) {
                case Back: backSound.play(DEFAULT_SOUND_VOLUME); break;
                case Button: buttonSound.play(DEFAULT_SOUND_VOLUME); break;
                case Collision: collisionSound.play(DEFAULT_SOUND_VOLUME); break;
                case Destroy: destroySound.play(DEFAULT_SOUND_VOLUME); break;
                case Exit: exitSound.play(DEFAULT_SOUND_VOLUME); break;
                case Fling: flingSound.play(DEFAULT_SOUND_VOLUME); break;
                case Init: initSound.play(DEFAULT_SOUND_VOLUME); break;
                case Option: optionSound.play(DEFAULT_SOUND_VOLUME); break;
                case Tap: tapSound.play(DEFAULT_SOUND_VOLUME); break;
            }
        }
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return superManager.getAssetManager().get(fileName, type);
    }

}
