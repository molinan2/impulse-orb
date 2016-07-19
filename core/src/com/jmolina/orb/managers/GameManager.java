package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.data.Attempt;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.var.Var;
import com.sun.glass.ui.Window;

/**
 * TODO
 * Clase para la Musica, que lleve la cuenta de la pista en reproduccion actual
 * y sea capaz de parar el resto, ver si hay alguna en reproduccion y si concuerda con la pantalla.
 * Capaz de hacer fadeIn y fadeOut.
 *
 * TODO
 * Trasladar aqui los metodos de control implementados en Level: pause, resume, restart,
 * destroyEvent, increaseGauge gauge, decrease gauge,...
 */
public class GameManager {

    public enum Fx { Back, Button, Collision, Destroy, Exit, Fling, Init, Option, Tap }
    public enum Track { Menu, Game, Success }
    public enum Length { Short, Medium, Long }

    private final float PIXELS_PER_METER = Var.GRID_CELL_SIZE;
    private final float ZOOM_MULTIPLIER = 1.66f;
    private final int VIBRATION_SHORT = 5;
    private final int VIBRATION_MEDIUM = 30;
    private final int VIBRATION_LONG = 300;
    private final float SOUND_VOLUME = 1f;
    private final float MUSIC_VOLUME = 0.3f;

    private SuperManager superManager;
    private PrefsManager prefsManager;
    private Attempt cachedAttempt; // Cachea las stats para mostrarlas en Success
    private boolean vibration = true;
    private boolean sound = true;
    private boolean music = true;
    private int zoom = PrefsManager.OPTION_ZOOM_DEFAULT;
    private Sound backSound, buttonSound, collisionSound, destroySound, exitSound, flingSound, initSound, optionSound, tapSound;
    private Music menuMusic, gameMusic, successMusic;
    private Level currentLevel;

    /**
     * Constructor
     */
    public GameManager (SuperManager sm) {
        prefsManager = sm.getPrefsManager();
        superManager = sm;
        cachedAttempt = new Attempt();
        cachedAttempt.setSuccessful(true);
        createSounds();
        createMusic();
        fetchOptions();
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
    public void fetchOptions() {
        vibration = prefsManager.getOptionVibration();
        sound = prefsManager.getOptionSound();
        music = prefsManager.getOptionMusic();
        zoom = MathUtils.clamp(prefsManager.getOptionZoom(), PrefsManager.OPTION_ZOOM_MIN, PrefsManager.OPTION_ZOOM_MAX);

        if (!music) stopMusic();
        else playTrack(Track.Menu);
    }

    public void cacheAttempt(Attempt attempt) {
        if (attempt != null) {
            cachedAttempt.setDistance(attempt.getDistance());
            cachedAttempt.setTime(attempt.getTime());
        }
    }

    public Attempt getCachedAttempt() {
        return cachedAttempt;
    }

    public void vibrate(Length length) {
        switch (length) {
            case Short: vibrate(VIBRATION_SHORT); break;
            case Medium: vibrate(VIBRATION_MEDIUM); break;
            case Long: vibrate(VIBRATION_LONG); break;
            default: vibrate(VIBRATION_MEDIUM); break;
        }
    }

    private void vibrate(int duration) {
        if (vibration)
            Gdx.input.vibrate(duration);
    }

    public float getPixelsPerMeter() {
        switch (zoom) {
            case 0: return PIXELS_PER_METER / ZOOM_MULTIPLIER;
            case 1: return PIXELS_PER_METER;
            case 2: return PIXELS_PER_METER * ZOOM_MULTIPLIER;
            default: return PIXELS_PER_METER;
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

        Music.OnCompletionListener replay = new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                music.play();
            }
        };

        menuMusic.setOnCompletionListener(replay);
        gameMusic.setOnCompletionListener(replay);
        successMusic.setOnCompletionListener(replay);
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

    private synchronized <T> T getAsset (String fileName, Class<T> type) {
        return superManager.getAssetManager().get(fileName, type);
    }

    private void stopMusic() {
        gameMusic.stop();
        menuMusic.stop();
        successMusic.stop();
    }

    public void setCurrentLevel(Level level) {
        currentLevel = level;
    }

    

}
