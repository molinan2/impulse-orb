package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

    public enum Track {
        Menu, Game, Success
    }

    public static final int VIBRATION_SHORT = 5;
    public static final int VIBRATION_MEDIUM = 30;
    public static final int VIBRATION_LONG = 300;
    public static final float RATIO_METER_PIXEL = 0.015625f; // Grid: 12x18.5, 64 pixel/metro
    private final float ZOOM_RATIO = 1.66f;
    private final float VOLUME_SOUND = 1f;
    private final float VOLUME_MUSIC = 0.3f;

    private PrefsManager prefsManager;
    private SuperManager superManager;
    private Level currentLevel;
    private Attempt lastSuccessfulAttempt;
    private boolean vibration = true;
    private boolean sound = true;
    private boolean music = true;
    private int zoom = PrefsManager.OPTION_ZOOM_DEFAULT;

    private Sound backSound, buttonSound, collisionSound, destroySound, exitSound, flingSound, initSound, optionSound, tapSound;
    private Music menuMusic, gameMusic, successMusic;

    /**
     * Constructor
     */
    public GameManager (SuperManager sm) {
        prefsManager = sm.getPrefsManager();
        superManager = sm;
        lastSuccessfulAttempt = new Attempt();
        lastSuccessfulAttempt.setSuccessful(true);
        createSounds();
        createMusic();
        updateOptions();
    }

    public void dispose() {
        backSound.dispose();
        buttonSound.dispose();
        collisionSound.dispose();
        destroySound.dispose();
        exitSound.dispose();
        flingSound.dispose();
        initSound.dispose();
        optionSound.dispose();
        tapSound.dispose();
        menuMusic.dispose();
        gameMusic.dispose();
    }

    public void updateOptions() {
        vibration = prefsManager.getOptionVibration();
        sound = prefsManager.getOptionSound();
        music = prefsManager.getOptionMusic();
        zoom = MathUtils.clamp(prefsManager.getOptionZoom(), PrefsManager.OPTION_ZOOM_MIN, PrefsManager.OPTION_ZOOM_MAX);

        // TODO
        // Esto asume que se llama siempre desde el menu
        if (!music) stopMusic();
        else playMusic(Track.Menu);
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

    private void createMusic() {
        menuMusic = getAsset(Asset.MUSIC_MENU, Music.class);
        gameMusic = getAsset(Asset.MUSIC_GAME, Music.class);
        successMusic = getAsset(Asset.MUSIC_SUCCESS, Music.class);
        menuMusic.setVolume(VOLUME_MUSIC);
        gameMusic.setVolume(VOLUME_MUSIC);
        successMusic.setVolume(VOLUME_MUSIC);
    }

    public void playFx(Fx fx) {
        if (sound) {
            switch (fx) {
                case Back: backSound.play(VOLUME_SOUND); break;
                case Button: buttonSound.play(VOLUME_SOUND); break;
                case Collision: collisionSound.play(VOLUME_SOUND); break;
                case Destroy: destroySound.play(VOLUME_SOUND); break;
                case Exit: exitSound.play(VOLUME_SOUND); break;
                case Fling: flingSound.play(VOLUME_SOUND); break;
                case Init: initSound.play(VOLUME_SOUND); break;
                case Option: optionSound.play(VOLUME_SOUND); break;
                case Tap: tapSound.play(VOLUME_SOUND); break;
            }
        }
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return superManager.getAssetManager().get(fileName, type);
    }

    public void stopMusic() {
        gameMusic.stop();
        menuMusic.stop();
        successMusic.stop();
    }

    public void playMusic(Track track) {
        if (music) {
            if (track == Track.Menu) {
                successMusic.stop();
                gameMusic.stop();
                menuMusic.play();
            }
            else if (track == Track.Game) {
                successMusic.stop();
                menuMusic.stop();
                gameMusic.play();
            }
            else if (track == Track.Success) {
                gameMusic.stop();
                menuMusic.stop();
                successMusic.play();
            }
        }
    }

}
