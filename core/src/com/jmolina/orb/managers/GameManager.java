package com.jmolina.orb.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.jmolina.orb.interfaces.PlayServices;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.data.Attempt;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.ui.Rating;

import java.util.ArrayList;

public class GameManager {

    public enum Fx { Back, Button, ElementCollision, WallCollision, Destroy, Error, Exit, Fling, Init, Option, Tap, Tick, Warning }
    public enum Track { Menu, Game, Success }
    public enum Length { Short, Medium, Long }

    public static final int RATING_DEVELOPER = 4;
    public static final int RATING_GOLD = 3;
    public static final int RATING_SILVER = 2;
    public static final int RATING_BRONZE = 1;
    public static final int RATING_UNRATED = 0;
    public static final float ACHIEVEMENT_KENNY_TIME = 5f;
    public static final float ACHIEVEMENT_ROBOCOP_TIME = 180f;
    public static final float ACHIEVEMENT_OVER9000_DISTANCE = 9000f;
    public static final float ACHIEVEMENT_HYPERDRIVE_SPEED2 = 60000f;

    private final float PIXELS_PER_METER = Var.GRID_CELL_SIZE;
    private final float ZOOM_MULTIPLIER = 1.66f;
    private final int VIBRATION_SHORT = 5;
    private final int VIBRATION_MEDIUM = 30;
    private final int VIBRATION_LONG = 300;
    private final float SOUND_VOLUME = 1f;
    private final float MUSIC_VOLUME = 0.6f;

    private SuperManager superManager;
    private PrefsManager prefsManager;
    private PlayServices serviceManager;
    private Attempt cachedAttempt;
    private int cachedRank;
    private boolean vibration = true;
    private boolean sound = true;
    private boolean music = true;
    private boolean online = true;
    private int zoom = PrefsManager.OPTION_ZOOM_DEFAULT;
    private Sound backSound, buttonSound, elementCollisionSound, wallCollisionSound, destroySound,
            errorSound, exitSound, flingSound, initSound, optionSound, tapSound, tickSound, warningSound;
    private Music menuMusic, gameMusic, successMusic;
    private ArrayList<ArrayList<Float>> times;

    /**
     * Constructor
     */
    public GameManager (SuperManager sm) {
        cachedRank = 0;
        prefsManager = sm.getPrefsManager();
        serviceManager = sm.getServiceManager();
        superManager = sm;
        cachedAttempt = new Attempt();
        cachedAttempt.setSuccessful(true);
        createSounds();
        createMusic();
        fetchOptions();
        createTimes();
    }

    public void dispose() {
        backSound.dispose();
        buttonSound.dispose();
        elementCollisionSound.dispose();
        wallCollisionSound.dispose();
        destroySound.dispose();
        errorSound.dispose();
        exitSound.dispose();
        flingSound.dispose();
        initSound.dispose();
        optionSound.dispose();
        tapSound.dispose();
        tickSound.dispose();
        warningSound.dispose();
        menuMusic.dispose();
        gameMusic.dispose();
    }

    /**
     * Se llama desde {@link com.jmolina.orb.screens.Options} y desde {@link GameManager}.
     */
    public void fetchOptions() {
        vibration = prefsManager.getOptionVibration();
        sound = prefsManager.getOptionSound();
        music = prefsManager.getOptionMusic();
        online = prefsManager.getOptionOnline();
        zoom = MathUtils.clamp(prefsManager.getOptionZoom(), PrefsManager.OPTION_ZOOM_MIN, PrefsManager.OPTION_ZOOM_MAX);
    }

    public void cache(Attempt attempt, int rank) {
        if (attempt != null) {
            cachedAttempt.setDistance(attempt.getDistance());
            cachedAttempt.setTime(attempt.getTime());
            cachedRank = rank;
        }
    }

    public Attempt getCachedAttempt() {
        return cachedAttempt;
    }

    public int getCachedRank() {
        return cachedRank;
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
        if (!vibration) return;

        Gdx.input.vibrate(duration);
    }

    public float getPixelsPerMeter() {
        switch (zoom) {
            case PrefsManager.OPTION_ZOOM_MIN: return PIXELS_PER_METER / ZOOM_MULTIPLIER;
            case PrefsManager.OPTION_ZOOM_DEFAULT: return PIXELS_PER_METER;
            case PrefsManager.OPTION_ZOOM_MAX: return PIXELS_PER_METER * ZOOM_MULTIPLIER;
            default: return PIXELS_PER_METER;
        }
    }

    private void createSounds() {
        backSound = getAsset(Asset.SOUND_BACK, Sound.class);
        buttonSound = getAsset(Asset.SOUND_BUTTON, Sound.class);
        elementCollisionSound = getAsset(Asset.SOUND_ELEMENT_COLLISION, Sound.class);
        wallCollisionSound = getAsset(Asset.SOUND_WALL_COLLISION, Sound.class);
        destroySound = getAsset(Asset.SOUND_DESTROY, Sound.class);
        errorSound = getAsset(Asset.SOUND_ERROR, Sound.class);
        exitSound = getAsset(Asset.SOUND_EXIT, Sound.class);
        flingSound = getAsset(Asset.SOUND_FLING, Sound.class);
        initSound = getAsset(Asset.SOUND_INIT, Sound.class);
        optionSound = getAsset(Asset.SOUND_OPTION, Sound.class);
        tapSound = getAsset(Asset.SOUND_TAP, Sound.class);
        tickSound = getAsset(Asset.SOUND_TICK, Sound.class);
        warningSound = getAsset(Asset.SOUND_WARNING, Sound.class);
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
        if (!sound) return;

        switch (fx) {
            case Back: backSound.play(SOUND_VOLUME); break;
            case Button: buttonSound.play(SOUND_VOLUME); break;
            case ElementCollision: elementCollisionSound.play(SOUND_VOLUME); break;
            case WallCollision: wallCollisionSound.play(SOUND_VOLUME); break;
            case Destroy: destroySound.play(SOUND_VOLUME); break;
            case Error: errorSound.play(SOUND_VOLUME); break;
            case Exit: exitSound.play(SOUND_VOLUME); break;
            case Fling: flingSound.play(SOUND_VOLUME); break;
            case Init: initSound.play(SOUND_VOLUME); break;
            case Option: optionSound.play(SOUND_VOLUME); break;
            case Tap: tapSound.play(SOUND_VOLUME); break;
            case Tick: tickSound.play(SOUND_VOLUME); break;
            case Warning: warningSound.play(SOUND_VOLUME); break;
            default:
        }
    }

    private void playTrack(Track track) {
        if (!music) return;

        if (track == Track.Menu) {
            if (!menuMusic.isPlaying()) {
                successMusic.stop();
                gameMusic.stop();
                menuMusic.play();
            }
        } else if (track == Track.Game) {
            if (!gameMusic.isPlaying()) {
                successMusic.stop();
                menuMusic.stop();
                gameMusic.play();
            }
        } else if (track == Track.Success) {
            if (!successMusic.isPlaying()) {
                gameMusic.stop();
                menuMusic.stop();
                successMusic.play();
            }
        }
    }

    private synchronized <T> T getAsset (String fileName, Class<T> type) {
        return superManager.getAssetManager().get(fileName, type);
    }

    public void stopMusic() {
        gameMusic.stop();
        menuMusic.stop();
        successMusic.stop();
    }

    private void createTimes() {
        times = new ArrayList<ArrayList<Float>>();

        ArrayList<Float> level1 = new ArrayList<Float>();
        ArrayList<Float> level2 = new ArrayList<Float>();
        ArrayList<Float> level3 = new ArrayList<Float>();
        ArrayList<Float> level4 = new ArrayList<Float>();
        ArrayList<Float> level5 = new ArrayList<Float>();

        level1.add(Var.TIME_LEVEL_1_DEV);
        level1.add(Var.TIME_LEVEL_1_GOLD);
        level1.add(Var.TIME_LEVEL_1_SILVER);
        level1.add(Var.TIME_LEVEL_1_BRONZE);
        level2.add(Var.TIME_LEVEL_2_DEV);
        level2.add(Var.TIME_LEVEL_2_GOLD);
        level2.add(Var.TIME_LEVEL_2_SILVER);
        level2.add(Var.TIME_LEVEL_2_BRONZE);
        level3.add(Var.TIME_LEVEL_3_DEV);
        level3.add(Var.TIME_LEVEL_3_GOLD);
        level3.add(Var.TIME_LEVEL_3_SILVER);
        level3.add(Var.TIME_LEVEL_3_BRONZE);
        level4.add(Var.TIME_LEVEL_4_DEV);
        level4.add(Var.TIME_LEVEL_4_GOLD);
        level4.add(Var.TIME_LEVEL_4_SILVER);
        level4.add(Var.TIME_LEVEL_4_BRONZE);
        level5.add(Var.TIME_LEVEL_5_DEV);
        level5.add(Var.TIME_LEVEL_5_GOLD);
        level5.add(Var.TIME_LEVEL_5_SILVER);
        level5.add(Var.TIME_LEVEL_5_BRONZE);

        times.add(level1);
        times.add(level2);
        times.add(level3);
        times.add(level4);
        times.add(level5);
    }

    /**
     *
     *
     * @param levelIndex Índice de nivel en número natural: {1,5}
     * @return
     */
    public ArrayList<Float> getTimes(int levelIndex) {
        levelIndex = MathUtils.clamp(levelIndex-1, Rating.MIN, Rating.MAX);
        return times.get(levelIndex);
    }

    public boolean isEnabledMusic() {
        return music;
    }

    /**
     * Obtiene el rating numérico
     *
     * @param levelIndex
     * @param time
     * @return Rating numérico
     */
    public int getNumericRating(int levelIndex, float time) {
        ArrayList<Float> levelTimes = getTimes(levelIndex);

        if (time < levelTimes.get(0)) return RATING_DEVELOPER;
        else if (time < levelTimes.get(1)) return RATING_GOLD;
        else if (time < levelTimes.get(2)) return RATING_SILVER;
        else if (time < levelTimes.get(3)) return RATING_BRONZE;
        else return RATING_UNRATED;
    }

    /**
     * Desbloquea un logro de nivel (nivel X completado).
     *
     * @param level Nivel
     */
    public void unlockLevelAchievement(ScreenManager.Key level) {
        switch (level) {
            case LEVEL_1: unlockAchievement(PlayServices.Achievement.KnowHow); break;
            case LEVEL_2: unlockAchievement(PlayServices.Achievement.TheRealDeal); break;
            case LEVEL_3: unlockAchievement(PlayServices.Achievement.BecomingAnExpert); break;
            case LEVEL_4: unlockAchievement(PlayServices.Achievement.AHeroWasBorn); break;
            case LEVEL_5: unlockAchievement(PlayServices.Achievement.OneAboveAll); break;
            default:
        }
    }

    /**
     * Desbloquea un logro "Fast & Furious" (mejor tiempo que el desarrollador en el nivel X).
     *
     * @param level Nivel
     */
    public void unlockFastFuriousAchievement(ScreenManager.Key level) {
        switch (level) {
            case LEVEL_1: unlockAchievement(PlayServices.Achievement.FastFurious1); break;
            case LEVEL_2: unlockAchievement(PlayServices.Achievement.FastFurious2); break;
            case LEVEL_3: unlockAchievement(PlayServices.Achievement.FastFurious3); break;
            case LEVEL_4: unlockAchievement(PlayServices.Achievement.FastFurious4); break;
            case LEVEL_5: unlockAchievement(PlayServices.Achievement.FastFurious5); break;
            default:
        }
    }

    /**
     * Desbloquea un logro de Google Play Games.
     *
     * @param achievement Logro
     */
    public void unlockAchievement(PlayServices.Achievement achievement) {
        if (!online) return;

        serviceManager.unlockAchievement(achievement);
    }

    /**
     * Sube a Google Play Games el tiempo realizado en un nivel.
     *
     * @param level
     * @param time
     */
    public void submitTime(ScreenManager.Key level, float time) {
        if (!online) return;

        long score = (long) (1000 * time);

        switch (level) {
            case LEVEL_1: serviceManager.submitScore(PlayServices.Leaderboard.Level1, score); break;
            case LEVEL_2: serviceManager.submitScore(PlayServices.Leaderboard.Level2, score); break;
            case LEVEL_3: serviceManager.submitScore(PlayServices.Leaderboard.Level3, score); break;
            case LEVEL_4: serviceManager.submitScore(PlayServices.Leaderboard.Level4, score); break;
            case LEVEL_5: serviceManager.submitScore(PlayServices.Leaderboard.Level5, score); break;
            default:
        }
    }

    public void showTime(ScreenManager.Key level) {
        if (!online) return;

        switch (level) {
            case LEVEL_1: serviceManager.showScore(PlayServices.Leaderboard.Level1); break;
            case LEVEL_2: serviceManager.showScore(PlayServices.Leaderboard.Level2); break;
            case LEVEL_3: serviceManager.showScore(PlayServices.Leaderboard.Level3); break;
            case LEVEL_4: serviceManager.showScore(PlayServices.Leaderboard.Level4); break;
            case LEVEL_5: serviceManager.showScore(PlayServices.Leaderboard.Level5); break;
            default:
        }
    }

}
