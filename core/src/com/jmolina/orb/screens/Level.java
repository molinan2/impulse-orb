package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.actions.UIAction;
import com.jmolina.orb.data.GameStats;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.listeners.GestureHandler;
import com.jmolina.orb.listeners.ContactHandler;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.runnables.UIRunnable;
import com.jmolina.orb.situations.Situation;
import com.jmolina.orb.stages.GestureStage;
import com.jmolina.orb.stages.HUDStage;
import com.jmolina.orb.stages.ParallaxStage;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class Level extends BaseScreen {

    public static final float INTRO_SEQUENCE_TIME = 1f;
    public static final float EXIT_SEQUENCE_TIME = 1.2f;
    public static final float BACKGROUND_FADE_TIME = 0.5f;

    private final float GESTURE_HALF_TAP_SQUARE_SIZE = 20.0f;
    private final float GESTURE_TAP_COUNT_INTERVAL = 0.4f;
    private final float GESTURE_LONG_PRESS_DURATION = 1.1f;
    private final float GESTURE_MAX_FLING_DELAY = 0.1f;
    private final float GESTURE_MAX_FLING_IMPULSE = 40f;
    private final Vector2 WORLD_GRAVITY = new Vector2(0, -20f);
    private final float WORLD_TIME_STEP = Var.FPS;
    private final int WORLD_OVERSTEP_FACTOR = 1;
    private final int WORLD_VELOCITY_INTERACTIONS = 8;
    private final int WORLD_POSITION_INTERACTIONS = 3;
    private final int INFINITE_Z_INDEX = 32000;

    private float pixelsPerMeter, impulseFactor;
    private boolean locked;
    private World world;
    private ContactHandler contactHandler;
    private Viewport worldViewport, gestureViewport, hudViewport, parallaxViewport;
    private GestureStage gestureStage;
    private GestureDetector gestureDetector;
    private GestureHandler gestureHandler;
    private ParallaxStage parallaxStage;
    private HUDStage hudStage;
    private SnapshotArray<Situation> situations;
    private Orb orb;
    private Vector2 orbStartPosition, currentOrbPosition, lastOrbPosition;
    private GameStats stats;
    private ScreenManager.Key successScreen = ScreenManager.Key.LEVEL_SELECT;
    private Runnable orbIntro, reset, unlock, orbDestroy, fadeInBackground, fadeOutBackground, switchToSuccess;


    /**
     * Constructor
     * @param sm SuperManager
     */
    public Level(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        pixelsPerMeter = getGameManager().getPixelsPerMeter();
        impulseFactor = 1 / getPixelsPerMeter();
        orbStartPosition = new Vector2();
        currentOrbPosition = new Vector2();
        lastOrbPosition = new Vector2();
        situations = new SnapshotArray<Situation>();
        stats = new GameStats();

        float worldWidth = VIEWPORT_WIDTH / getPixelsPerMeter();
        float worldHeight = VIEWPORT_HEIGHT / getPixelsPerMeter();
        worldViewport = new FitViewport(worldWidth, worldHeight);
        gestureViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        parallaxViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        hudViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        hudStage = new HUDStage(getAssetManager(), this, hudViewport);
        gestureStage = new GestureStage(getAssetManager(), gestureViewport, getPixelsPerMeter());
        parallaxStage = new ParallaxStage(getAssetManager(), parallaxViewport, getPixelsPerMeter());
        world = new World(WORLD_GRAVITY, true);
        orb = new Orb(getAssetManager(), getWorld(), getPixelsPerMeter());

        contactHandler = new ContactHandler(this);
        world.setContactListener(contactHandler);
        gestureHandler = new GestureHandler(this);
        gestureDetector = new GestureDetector(
                GESTURE_HALF_TAP_SQUARE_SIZE,
                GESTURE_TAP_COUNT_INTERVAL,
                GESTURE_LONG_PRESS_DURATION,
                GESTURE_MAX_FLING_DELAY,
                gestureHandler
        );

        // Los primeros Processor reciben antes los eventos
        addProcessor(hudStage);
        addProcessor(gestureStage);
        addProcessor(gestureDetector);
        addOrb(orb);

        createRunnables();
        lockGame();
    }


    private void createRunnables() {
        orbIntro = new Runnable() {
            @Override
            public void run() {
                getOrb().intro();
                getGameManager().play(GameManager.Fx.Init);
            }
        };

        reset = new Runnable() {
            @Override
            public void run() {
                getOrb().reset(orbStartPosition.x, orbStartPosition.y);
                getHUDStage().reset();
                stats.newTry();
            }
        };

        unlock = new Runnable() {
            @Override
            public void run() {
                unlockGame();
            }
        };

        orbDestroy = new Runnable() {
            @Override
            public void run() {
                getOrb().destroy();
            }
        };

        fadeInBackground = new Runnable() {
            @Override
            public void run() {
                getBackgroundStage().addAction(fadeIn(UIAction.DURATION, Interpolation.pow2));
            }
        };

        fadeOutBackground = new Runnable() {
            @Override
            public void run() {
                getBackgroundStage().addAction(alpha(1));
                getBackgroundStage().addAction(fadeOut(BACKGROUND_FADE_TIME));
            }
        };

        switchToSuccess = new Runnable() {
            @Override
            public void run() {
                switchToScreen(getSuccessScreen(), Hierarchy.HIGHER);
            }
        };
    }

    @Override
    public void render(float delta) {
        clearColor();
        act();
        syncBodies();
        stepWorld();
        followCamera();
        syncActors();
        updateOrbLockTime();
        updateHeat();
        updateTimer();
        updateStats();
        draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        worldViewport.update(width, height);
        gestureViewport.update(width, height);
        hudViewport.update(width, height);
        parallaxViewport.update(width, height);
    }

    @Override
    public void dispose() {
        getOrb().disposing = true;
        getHUDStage().dispose();
        getGestureStage().dispose();
        getParallaxStage().dispose();
        getWorld().dispose();
        super.dispose();
    }

    @Override
    public void show() {
        unsetInputProcessor();
        getBackgroundStage().addAction(alpha(1));
        stats.newTry();
        getGameManager().play(GameManager.Track.Game);
        getOrb().getActor().addAction(alpha(0));

        getHUDStage().addAction(sequence(
                alpha(0),
                scaleTo(UIAction.SMALL, UIAction.SMALL),
                transition(Flow.ENTERING, getHierarchy()),
                run(fadeOutBackground),
                delay(0.5f * BACKGROUND_FADE_TIME),
                run(orbIntro),
                delay(Math.max(BACKGROUND_FADE_TIME, INTRO_SEQUENCE_TIME)),
                run(UIRunnable.setInputProcessor(getProcessor())),
                run(unlock)
        ));
    }

    @Override
    public void hide() {
        super.hide();
        getGameManager().play(GameManager.Track.Menu);
        // TODO: Se ejecutan 2 plays seguidos distintos cuando se cambia a SUCCESS SCREEN
    }

    @Override
    public void pause() {
        super.pause();
        pauseGame();
    }

    @Override
    public void back() {
        if (!isGameLocked()) pauseGame();
        else resumeGame();
    }

    @Override
    public void switchToScreen(final ScreenManager.Key key, final Hierarchy hierarchy) {
        getHUDStage().addAction(sequence(
                run(fadeInBackground),
                delay(UIAction.DURATION),
                transition(Flow.LEAVING, hierarchy),
                run(UIRunnable.setScreen(getScreenManager(), key, hierarchy))
        ));
    }


    /**
     * Class methods
     */

    public float getPixelsPerMeter() {
        return pixelsPerMeter;
    }

    public World getWorld() {
        return world;
    }

    public ParallaxStage getParallaxStage() {
        return parallaxStage;
    }

    public GestureStage getGestureStage() {
        return gestureStage;
    }

    public HUDStage getHUDStage() {
        return hudStage;
    }

    public GameStats getStats() {
        return stats;
    }

    public void setSuccessScreen(ScreenManager.Key key) {
        this.successScreen = key;
    }

    public ScreenManager.Key getSuccessScreen() {
        return this.successScreen;
    }

    public void addOrb(Orb orb) {
        addMainActor(orb.getActor());
        orb.syncActor(worldViewport);
        orb.getActor().setZIndex(INFINITE_Z_INDEX);
    }

    public Orb getOrb () {
        return orb;
    }

    public void setOrbStartPosition (float x, float y) {
        orbStartPosition.set(x, y);
        getOrb().setPosition(x, y);
        currentOrbPosition.set(x, y);
        lastOrbPosition.set(x, y);
    }

    public void addSituation (Situation situation) {
        situations.add(situation);
        int size = situations.size;
        situation.setPosition(size-1);

        for (Element element : situation.getElements()) {
            addMainActor(element.getActor());
            element.syncActor(worldViewport);
        }

        getOrb().getActor().setZIndex(INFINITE_Z_INDEX);
    }

    public SnapshotArray<Situation> getSituations () {
        return situations;
    }


    /**
     * Render methods
     */

    public void stepWorld() {
        for (int i=0; i<WORLD_OVERSTEP_FACTOR; i++) {
            if (!isGameLocked()) {
                world.step(
                        WORLD_TIME_STEP / (float) WORLD_OVERSTEP_FACTOR,
                        WORLD_VELOCITY_INTERACTIONS,
                        WORLD_POSITION_INTERACTIONS
                );
            }
        }
    }

    private void syncBodies() {
        // TODO: Sincronizar elementos

        // Orb
        orb.syncBody();
    }

    public void syncActors() {
        for (Situation situation : situations ) {
            for (Element element : situation.getElements()) {
                element.syncActor(worldViewport);
            }
        }

        getOrb().syncActor(worldViewport);
    }

    private void followCamera() {
        worldViewport.getCamera().position.x = getOrb().getBody().getPosition().x;
        worldViewport.getCamera().position.y = getOrb().getBody().getPosition().y;
        worldViewport.getCamera().update();
    }

    private void act() {
        getMainStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getGestureStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getBackgroundStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getHUDStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
    }

    private void draw() {
        getParallaxStage().draw(worldViewport.getCamera().position.x, worldViewport.getCamera().position.y);
        getMainStage().draw();
        getGestureStage().draw();
        getBackgroundStage().draw();
        getHUDStage().draw();
    }

    /**
     * Render checks and updates
     */

    private void updateOrbLockTime() {
        if (!isGameLocked()) {
            getOrb().updateLockTime();
        }
    }

    private void updateHeat() {
        if (!isGameLocked()) {
            getOrb().updateHeat();
            getHUDStage().setGaugeLevel(getOrb().getHeat());
            getHUDStage().setGaugeOverload(getOrb().isOverloaded());
        }
    }

    private void updateTimer() {
        if (!isGameLocked()) {
            getHUDStage().updateTimer();
        }
    }

    private void updateStats() {
        if (!isGameLocked()) {
            currentOrbPosition = getOrb().getPosition();
            float distance = Utils.distance(currentOrbPosition, lastOrbPosition);
            lastOrbPosition = currentOrbPosition;

            getStats().addTime(Gdx.graphics.getRawDeltaTime());
            getStats().addDistance(distance);
            getHUDStage().setDistanceValue(stats.getCurrentDistance());
            getHUDStage().setFullDistanceValue(stats.fullDistance());
            getHUDStage().setFullTimeValue(stats.fullTime());
            getHUDStage().setFullDestroyedValue(stats.fails());
        }
    }

    /**
     * Metodos de GameManager
     */

    public void pauseGame() {
        if (!isGameLocked()) {
            lockGame();
            getHUDStage().pause();
            getGameManager().play(GameManager.Fx.Back);
        }
    }

    public void resumeGame() {
        if (isGameLocked()) {
            getHUDStage().resume(unlock);
            getGameManager().play(GameManager.Fx.Button);
        }
    }

    public void restartGame() {
        getHUDStage().restart(reset, orbIntro, unlock);
        getGameManager().play(GameManager.Fx.Button);
    }

    public void leaveGame() {
        unsetInputProcessor();
        getPrefsManager().saveGameStats(stats, getKey());
        getGameManager().play(GameManager.Fx.Back);
        getGameManager().play(GameManager.Track.Menu);
        switchToScreen(getPreviousScreen(), Hierarchy.HIGHER);
    }

    public void successGame() {
        lockGame();
        getStats().setSuccessfull(true);
        getPrefsManager().saveGameStats(stats, getKey());
        getGameManager().cacheAttempt(stats.getLastAttempt());
        unsetInputProcessor();
        getGameManager().play(GameManager.Fx.Exit);
        getOrb().outro(switchToSuccess);
    }

    public void lockGame() {
        locked = true;
    }

    public void unlockGame() {
        locked = false;
    }

    public boolean isGameLocked() {
        return locked;
    }


    /**
     * Eventos
     */

    public void collisionEvent() {
        getGameManager().play(GameManager.Fx.Collision);
        getGameManager().vibrate(GameManager.Length.Short);
    }

    public void tapEvent() {
        if (!isGameLocked()) {
            getOrb().lock();
            getOrb().increaseHeat();
            getGestureStage().drawTap();
            getGameManager().vibrate(GameManager.Length.Medium);
            getGameManager().play(GameManager.Fx.Tap);

            if (getOrb().isOverloaded()) {
                destroyEvent();
            }
            else if (getOrb().isHeatMaxed()) {
                getOrb().setOverloaded(true);
                getHUDStage().setGaugeOverload(true);
            }
        }
    }

    public void flingEvent(float velocityX, float velocityY) {
        float impulseX = MathUtils.clamp(velocityX * impulseFactor, -GESTURE_MAX_FLING_IMPULSE, GESTURE_MAX_FLING_IMPULSE);
        float impulseY = MathUtils.clamp(-velocityY * impulseFactor, -GESTURE_MAX_FLING_IMPULSE, GESTURE_MAX_FLING_IMPULSE);

        if (!isGameLocked()) {
            getOrb().unlock();
            getOrb().getBody().applyLinearImpulse(
                    impulseX,
                    impulseY,
                    getOrb().getBody().getPosition().x,
                    getOrb().getBody().getPosition().y,
                    true
            );

            getGestureStage().drawFling();
            getGameManager().play(GameManager.Fx.Fling);
        }
    }

    public void destroyEvent() {
        lockGame();
        getStats().setFailed(true);
        getGameManager().vibrate(GameManager.Length.Long);
        getGameManager().play(GameManager.Fx.Destroy);
        getHUDStage().destroy(orbDestroy, reset, orbIntro, unlock);
    }

}
