package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
import com.jmolina.orb.data.PersonalTimes;
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

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.jmolina.orb.managers.PrefsManager.*;


/**
 * TODO
 * Temporalmente, esta pantalla está actuando como GameManager
 */
public class Level extends BaseScreen {

    public static final float INTRO_SEQUENCE_TIME = 1f;
    private static final float HALF_TAP_SQUARE_SIZE = 20.0f;
    private static final float TAP_COUNT_INTERVAL = 0.4f;
    private static final float LONG_PRESS_DURATION = 1.1f;
    private static final float MAX_FLING_DELAY = 0.15f;

    private final Vector2 WORLD_GRAVITY = new Vector2(0, -20f);
    private final float WORLD_TIME_STEP = 1/60f;
    private final int WORLD_OVERSTEP_FACTOR = 4;
    private final int WORLD_VELOCITY_INTERACTIONS = 8;
    private final int WORLD_POSITION_INTERACTIONS = 3;
    private final float ORB_MAX_LOCK_TIME = 0.90f;
    private final float COOLING_RATE = 0.1f;
    private final float OVERLOAD_TIME = 4f;
    private final int INFINITE_Z_INDEX = 32000;
    private final float MAX_IMPULSE = 40f;
    public static final float EXIT_SEQUENCE_TIME = 1f;
    public static final float BACKGROUND_FADE_TIME = 0.5f;

    private float pixelsPerMeter;
    private float impulseFactor;
    private World world;
    private ContactHandler contactHandler;
    private Viewport worldViewport, gestureViewport, hudViewport, parallaxViewport;
    private GestureStage gestureStage;
    private ParallaxStage parallaxStage;
    private HUDStage hudStage;
    private GestureDetector gestureDetector;
    private GestureHandler gestureHandler;
    private SnapshotArray<Situation> situations;
    private Orb orb;
    private float orbLockTimer = 0f;
    private float orbOverloadTimer = 0f;
    private boolean paused = true;
    private boolean locked = false; // If locked = true, game cannot be unpaused
    private Vector2 orbStartPosition, currentOrbPosition, lastOrbPosition;
    private GameStats stats;
    private ScreenManager.Key successScreen = ScreenManager.Key.LEVEL_SELECT;
    // private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();


    private Runnable reset = new Runnable() {
        @Override
        public void run() {
            getOrb().reset(orbStartPosition.x, orbStartPosition.y);
            hudStage.reset();
            stats.newTry();
        }
    };

    private Runnable unpause = new Runnable() {
        @Override
        public void run() {
            paused = false;
        }
    };

    private Runnable unlock = new Runnable() {
        @Override
        public void run() {
            locked = false;
        }
    };

    private Runnable intro = new Runnable() {
        @Override
        public void run() {
            getOrb().getActor().addAction(sequence(
                    parallel(
                            scaleBy(4 * getOrb().getNaturalScale(), 4 * getOrb().getNaturalScale(), 0),
                            rotateTo(0, 0),
                            alpha(0)
                    ),
                    parallel(
                            rotateTo(360, INTRO_SEQUENCE_TIME, Interpolation.exp5),
                            scaleTo(getOrb().getNaturalScale(), getOrb().getNaturalScale(), INTRO_SEQUENCE_TIME, Interpolation.pow2),
                            fadeIn(INTRO_SEQUENCE_TIME, Interpolation.pow2)
                    )
            ));

            getGameManager().play(GameManager.Fx.Init);
        }
    };

    private Runnable fadeInBackground = new Runnable() {
        @Override
        public void run() {
            getBackgroundStage().addAction(fadeIn(UIAction.DURATION, Interpolation.pow2));
        }
    };

    private Runnable fadeOutBackground = new Runnable() {
        @Override
        public void run() {
            getBackgroundStage().addAction(fadeOut(BACKGROUND_FADE_TIME));
        }
    };

    private Runnable destroy = new Runnable() {
        @Override
        public void run() {
            getOrb().destroy();
        }
    };



    /**
     * Constructor
     * @param sm SuperManager
     */
    public Level(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        pixelsPerMeter = sm.getGameManager().getPixelsPerMeter();
        impulseFactor = 1 / getPixelsPerMeter();

        orbStartPosition = new Vector2();
        situations = new SnapshotArray<Situation>();

        float worldWidth = VIEWPORT_WIDTH / getPixelsPerMeter();
        float worldHeight = VIEWPORT_HEIGHT / getPixelsPerMeter();
        worldViewport = new FitViewport(worldWidth, worldHeight);

        gestureViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        parallaxViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        hudViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        hudStage = new HUDStage(getAssetManager(), this, hudViewport);
        gestureStage = new GestureStage(getAssetManager(), gestureViewport, getPixelsPerMeter());
        parallaxStage = new ParallaxStage(getAssetManager(), parallaxViewport, getPixelsPerMeter(), getGameManager().getZoom());
        stats = new GameStats();
        currentOrbPosition = new Vector2();
        lastOrbPosition = new Vector2();

        world = new World(WORLD_GRAVITY, true);
        orb = new Orb(getAssetManager(), getWorld(), getPixelsPerMeter());
        contactHandler = new ContactHandler(this);
        world.setContactListener(contactHandler);

        addOrb(orb);

        gestureHandler = new GestureHandler(this);
        gestureDetector = new GestureDetector(
                HALF_TAP_SQUARE_SIZE,
                TAP_COUNT_INTERVAL,
                LONG_PRESS_DURATION,
                MAX_FLING_DELAY,
                gestureHandler
        );

        // Los primeros Processor reciben antes los eventos
        addProcessor(hudStage);
        addProcessor(gestureStage);
        addProcessor(gestureDetector);
    }


    @Override
    public void render(float delta) {
        clearColor();
        act();
        syncBodies();
        stepWorld();
        followCamera();
        syncActors();
        updateLockTime();
        updateHeat();
        updateTimer();
        updateGameStats();
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
        orb.disposing = true;
        hudStage.dispose();
        gestureStage.dispose();
        parallaxStage.dispose();
        world.dispose();
        super.dispose();
    }

    @Override
    public void show() {
        unsetInputProcessor();
        getBackgroundStage().addAction(alpha(1));
        stats.newTry();
        getGameManager().play(GameManager.Track.Game);

        hudStage.addAction(sequence(
                alpha(0),
                scaleTo(UIAction.SMALL, UIAction.SMALL),
                transition(Flow.ENTERING, getHierarchy()),
                run(fadeOutBackground),
                run(intro),
                delay(Math.max(BACKGROUND_FADE_TIME, INTRO_SEQUENCE_TIME)),
                run(UIRunnable.setInputProcessor(getProcessor())),
                run(unpause)
        ));
    }

    @Override
    public void hide() {
        super.hide();
        getGameManager().play(GameManager.Track.Menu);
        // TODO: Se estan ejecutando 2 plays seguidos distintos cuando se cambia a SUCCESS SCREEN
    }

    @Override
    public void pause() {
        super.pause();
        pauseGame();
    }

    @Override
    public void back() {
        if (!isGamePaused()) pauseGame();
        else resumeGame();
    }

    @Override
    public void switchToScreen(final ScreenManager.Key key, final Hierarchy hierarchy) {
        hudStage.addAction(sequence(
                run(fadeInBackground),
                delay(UIAction.DURATION),
                transition(Flow.LEAVING, hierarchy),
                run(UIRunnable.setScreen(getScreenManager(), key, hierarchy))
        ));
    }


    /**
     * Class methods
     */

    public void setSuccessScreen(ScreenManager.Key key) {
        this.successScreen = key;
    }

    public ScreenManager.Key getSuccessScreen() {
        return this.successScreen;
    }

    public void setOrbStartPosition (float x, float y) {
        orbStartPosition.set(x, y);
        getOrb().setPosition(orbStartPosition.x, orbStartPosition.y);
        currentOrbPosition.set(x, y);
        lastOrbPosition.set(x, y);
    }

    public boolean isGamePaused() {
        return paused;
    }

    public void addOrb(Orb orb) {
        addMainActor(orb.getActor());
        orb.syncActor(worldViewport);
        orb.getActor().setZIndex(INFINITE_Z_INDEX);
    }

    public Orb getOrb () {
        return this.orb;
    }

    public void addSituation (Situation situation) {
        this.situations.add(situation);
        int size = this.situations.size;
        situation.setPosition(size-1);

        for (Element element : situation.getElements()) {
            addMainActor(element.getActor());
            element.syncActor(worldViewport);
        }

        orb.getActor().setZIndex(INFINITE_Z_INDEX);
    }

    public SnapshotArray<Situation> getSituations () {
        return this.situations;
    }

    public float getPixelsPerMeter() {
        return pixelsPerMeter;
    }

    public World getWorld() {
        return world;
    }

    /**
     * Render methods
     */

    public void stepWorld() {
        for (int i=0; i<WORLD_OVERSTEP_FACTOR; i++) {
            if (!isGamePaused()) {
                world.step(
                        WORLD_TIME_STEP / (float) WORLD_OVERSTEP_FACTOR,
                        WORLD_VELOCITY_INTERACTIONS,
                        WORLD_POSITION_INTERACTIONS
                );
            }
        }
    }

    private void syncBodies() {
        // Todos los cuerpos
        // TODO

        // Orb
        orb.syncBody();
    }

    public void syncActors() {
        for (Situation situation : situations ) {
            for (Element element : situation.getElements()) {
                element.syncActor(worldViewport);
            }
        }

        orb.syncActor(worldViewport);
    }

    private void followCamera() {
        worldViewport.getCamera().position.x = orb.getBody().getPosition().x;
        worldViewport.getCamera().position.y = orb.getBody().getPosition().y;
        worldViewport.getCamera().update();
    }

    private void act() {
        parallaxStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getMainStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        gestureStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getBackgroundStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        hudStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
    }

    private void draw() {
        parallaxStage.draw(worldViewport.getCamera().position.x, worldViewport.getCamera().position.y);
        getMainStage().draw();
        gestureStage.draw();
        getBackgroundStage().draw();
        hudStage.draw();
        // debugRenderer.render(world, worldViewport.getCamera().combined);
    }

    /**
     * Desbloquea el Orb transcurrido el tiempo de bloqueo.
     */
    private void updateLockTime() {
        if (getOrb().isLocked() && !isGamePaused()) {
            if (orbLockTimer > ORB_MAX_LOCK_TIME) {
                getOrb().unlock();
                orbLockTimer = 0f;
            }
            else {
                orbLockTimer += Gdx.graphics.getRawDeltaTime();
            }
        }
    }

    public void updateHeat() {
        if (!isGamePaused()) {
            hudStage.setGaugeLevel(getOrb().getHeat());

            if (getOrb().isOverloaded()) {
                orbOverloadTimer = MathUtils.clamp(orbOverloadTimer - Gdx.graphics.getRawDeltaTime(), 0f, OVERLOAD_TIME);
                if (orbOverloadTimer == 0f) {
                    getOrb().setOverloaded(false);
                    hudStage.setGaugeOverload(false);
                }
            } else {
                float decrement = COOLING_RATE * Gdx.graphics.getRawDeltaTime();
                getOrb().decreaseHeat(decrement);
            }
        }
    }

    public void updateTimer() {
        if (!isGamePaused()) {
            hudStage.updateTimer();
        }
    }

    public void updateGameStats() {
        if (!isGamePaused()) {
            float distance;

            currentOrbPosition = getOrb().getPosition();
            distance = Utils.distance(currentOrbPosition, lastOrbPosition);
            lastOrbPosition = currentOrbPosition;

            stats.addTime(Gdx.graphics.getRawDeltaTime());
            stats.addDistance(distance);
            hudStage.setDistanceValue(stats.getCurrentDistance());
            hudStage.setFullDistanceValue(stats.fullDistance());
            hudStage.setFullTimeValue(stats.fullTime());
            hudStage.setFullDestroyedValue(stats.fails());
        }
    }
    

    /**
     * Metodos de GameManager
     */

    public void pauseGame() {
        if (!isGamePaused()) {
            paused = true;
            hudStage.pause();
            getGameManager().play(GameManager.Fx.Back);
        }
    }

    public void resumeGame() {
        if (!locked) {
            hudStage.resume(unpause);
            getGameManager().play(GameManager.Fx.Button);
        }
    }

    public void restartGame() {
        hudStage.restart(reset, intro, unpause);
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
        stats.setSuccessfull(true);
        getPrefsManager().saveGameStats(stats, getKey());
        getGameManager().cacheAttempt(stats.getLastAttempt());
        unsetInputProcessor();
        getGameManager().play(GameManager.Fx.Exit);
        paused = true;
        locked = true;

        getOrb().getActor().addAction(sequence(
                parallel(
                        rotateBy(360, EXIT_SEQUENCE_TIME, Interpolation.exp5Out),
                        scaleTo(4 * getOrb().getNaturalScale(), 4 * getOrb().getNaturalScale(), EXIT_SEQUENCE_TIME, Interpolation.pow2),
                        fadeOut(EXIT_SEQUENCE_TIME, Interpolation.pow2)
                ),
                run(new Runnable() {
                    @Override
                    public void run() {
                        switchToScreen(getSuccessScreen(), Hierarchy.HIGHER);
                    }
                })
        ));
    }

    /**
     * Eventos
     */

    public void collision() {
        getGameManager().play(GameManager.Fx.Collision);
        getGameManager().vibrateShort();
    }

    public void tap() {
        if (!isGamePaused()) {
            getOrb().increaseHeat();
            gestureStage.drawTap();
            orbLockTimer = 0f;
            getOrb().lock();

            if (getOrb().isOverloaded()) {
                destroy();
            }

            if (getOrb().getHeat() == Orb.HEAT_MAX) {
                getOrb().setOverloaded(true);
                hudStage.setGaugeOverload(true);
                orbOverloadTimer = OVERLOAD_TIME;
            }

            getGameManager().vibrateMedium();
            getGameManager().play(GameManager.Fx.Tap);
        }
    }

    public void fling (float velocityX, float velocityY) {
        float impulseX = MathUtils.clamp(velocityX * impulseFactor, -MAX_IMPULSE, MAX_IMPULSE);
        float impulseY = MathUtils.clamp(-velocityY * impulseFactor, -MAX_IMPULSE, MAX_IMPULSE);

        if (!isGamePaused()) {
            getOrb().unlock();
            getOrb().getBody().applyLinearImpulse(
                    impulseX,
                    impulseY,
                    getOrb().getBody().getPosition().x,
                    getOrb().getBody().getPosition().y,
                    true
            );

            gestureStage.drawFling();
            getGameManager().play(GameManager.Fx.Fling);
        }
    }

    public void destroy() {
        paused = true;
        locked = true;
        stats.setFailed(true);
        getGameManager().vibrateLong();
        getGameManager().play(GameManager.Fx.Destroy);
        hudStage.destroyAndRestart(destroy, reset, intro, unlock, unpause);
    }

}
