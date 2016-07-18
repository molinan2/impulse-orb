package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.data.Attempt;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.screens.Level;

/**
 * TODO
 * Clase para la Musica, que lleve la cuenta de la pista en reproduccion actual
 * y sea capaz de parar el resto, ver si hay alguna en reproduccion y si concuerda con la pantalla.
 * Capaz de hacer fadeIn y fadeOut.
 *
 * TODO
 * Trasladar aqui los metodos de control implementados en Level: pause, resume, restart,
 * destroy, increaseGauge gauge, decrease gauge,...
 */
public class GameManager {

    public enum Fx { Back, Button, Collision, Destroy, Exit, Fling, Init, Option, Tap }
    public enum Track { Menu, Game, Success }

    public static final int VIBRATION_SHORT = 5;
    public static final int VIBRATION_MEDIUM = 30;
    public static final int VIBRATION_LONG = 300;
    public static final float RATIO_METER_PIXEL = 0.015625f;
    public static final float PIXELS_PER_METER = 64f;
    private final float ZOOM_STEP = 1.66f;
    private final float SOUND_VOLUME = 1f;
    private final float MUSIC_VOLUME = 0.3f;

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

    /**
     * Sólo se llama desde el menú (Options)
     */
    public void updateOptions() {
        vibration = prefsManager.getOptionVibration();
        sound = prefsManager.getOptionSound();
        music = prefsManager.getOptionMusic();
        zoom = MathUtils.clamp(prefsManager.getOptionZoom(), PrefsManager.OPTION_ZOOM_MIN, PrefsManager.OPTION_ZOOM_MAX);

        if (!music) stopMusic();
        else playTrack(Track.Menu);
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
            case 0: return RATIO_METER_PIXEL * ZOOM_STEP;
            case 1: return RATIO_METER_PIXEL;
            case 2: return RATIO_METER_PIXEL / ZOOM_STEP;
            default: return RATIO_METER_PIXEL;
        }
    }

    public float getPixelsPerMeter() {
        switch (zoom) {
            case 0: return PIXELS_PER_METER / ZOOM_STEP;
            case 1: return PIXELS_PER_METER;
            case 2: return PIXELS_PER_METER * ZOOM_STEP;
            default: return RATIO_METER_PIXEL;
        }
    }

    public float getZoom() {
        switch (zoom) {
            case 0: return 1 / ZOOM_STEP;
            case 1: return 1;
            case 2: return ZOOM_STEP;
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
        menuMusic.setVolume(MUSIC_VOLUME);
        gameMusic.setVolume(MUSIC_VOLUME);
        successMusic.setVolume(MUSIC_VOLUME);

        menuMusic.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                music.play();
            }
        });

        gameMusic.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                music.play();
            }
        });

        successMusic.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                music.play();
            }
        });
    }

    public void play(Fx fx) {
        playFx(fx);
    }

    public void play(Track track) {
        playTrack(track);
    }

    private void playFx(Fx fx) {
        if (sound) {
            switch (fx) {
                case Back: backSound.play(SOUND_VOLUME); break;
                case Button: buttonSound.play(SOUND_VOLUME); break;
                case Collision: collisionSound.play(SOUND_VOLUME); break;
                case Destroy: destroySound.play(SOUND_VOLUME); break;
                case Exit: exitSound.play(SOUND_VOLUME); break;
                case Fling: flingSound.play(SOUND_VOLUME); break;
                case Init: initSound.play(SOUND_VOLUME); break;
                case Option: optionSound.play(SOUND_VOLUME); break;
                case Tap: tapSound.play(SOUND_VOLUME); break;
            }
        }
    }

    private void playTrack(Track track) {
        if (music) {
            switch (track) {
                case Menu:
                    successMusic.stop();
                    gameMusic.stop();
                    menuMusic.play();
                    break;
                case Game:
                    successMusic.stop();
                    menuMusic.stop();
                    gameMusic.play();
                    break;
                case Success:
                    gameMusic.stop();
                    menuMusic.stop();
                    successMusic.play();
                    break;
                default:
                    gameMusic.stop();
                    menuMusic.stop();
                    successMusic.stop();
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

}
