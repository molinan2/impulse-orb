package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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
import com.jmolina.orb.stages.OverlayStage;
import com.jmolina.orb.stages.ParallaxStage;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.jmolina.orb.managers.PrefsManager.*;


/**
 * TODO
 * Temporalmente, esta pantalla está actuando como GameManager
 *
 * Reset: reiniciar elementos de la pantalla
 * Restart: reiniciar juego
 */
public class Level extends BaseScreen {

    /**
     * Constants
     */
    private static final float HALF_TAP_SQUARE_SIZE = 20.0f;
    private static final float TAP_COUNT_INTERVAL = 0.4f;
    private static final float LONG_PRESS_DURATION = 1.1f;
    private static final float MAX_FLING_DELAY = 0.15f;
    private final Vector2 WORLD_GRAVITY = new Vector2(0, -20f);
    private final float WORLD_TIME_STEP = 1/60f;
    private final int WORLD_OVERSTEP_FACTOR = 8;
    private final int WORLD_VELOCITY_INTERACTIONS = 8;
    private final int WORLD_POSITION_INTERACTIONS = 3;
    private final float ORB_MAX_LOCK_TIME = 0.5f;
    private final float COOLING_RATE = 0.1f;
    private final float OVERLOAD_TIME = 4f;
    private final int INFINITE_Z_INDEX = 32000;
    public static final float ORB_INTRO_SEQUENCE_TIME = 1f;
    public static final float ORB_EXIT_SEQUENCE_TIME = 1f;



    /**
     * Fields
     */
    public float ratioMeterPixel; // Grid: 12x18.5, 64 pixel/metro
    private float impulseFactor;
    private float impulseMax;

    private World world;
    private ContactHandler contactHandler;
    private Viewport worldViewport, gestureViewport, hudViewport, parallaxViewport;
    private GestureStage gestureStage;
    private ParallaxStage parallaxStage;
    private OverlayStage overlayStage;
    private HUDStage hudStage;
    private GestureDetector gestureDetector;
    private GestureHandler gestureHandler;
    private SnapshotArray<Situation> situations;
    private Orb orb;
    private float orbLockTimer = 0f;
    private float orbOverloadTimer = 0f;
    private boolean paused = true;
    private boolean locked = false; // If locked = true, game cannot be unpaused
    private Vector2 orbStartPosition;
    // private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private GameStats stats;
    private Vector2 currentOrbPosition;
    private Vector2 lastOrbPosition;
    private ScreenManager.Key successScreen = ScreenManager.Key.LEVEL_SELECT;

    /**
     * Runnables
     */

    private Runnable restart = new Runnable() {
        @Override
        public void run() {
            getOrb().setPosition(orbStartPosition.x, orbStartPosition.y);
            getOrb().resetAngle();
            getOrb().resetHeat();
            getOrb().resetVelocity();
            getOrb().resetFragments();
            getOrb().setOverloaded(false);
            hudStage.resetTimer();
            hudStage.resetGauge();
            stats.newTry();
        }
    };

    private Runnable reset = new Runnable() {
        @Override
        public void run() {
            getOrb().setPosition(orbStartPosition.x, orbStartPosition.y);
            getOrb().resetHeat();
            getOrb().resetVelocity();
            hudStage.resetTimer();
            stats.reset();

        }
    };

    private Runnable unpause = new Runnable() {
        @Override
        public void run() {
            paused = false;
        }
    };

    private Runnable orbIntro = new Runnable() {
        @Override
        public void run() {
            getOrb().getFragmentedActor().addAction(sequence(
                    parallel(
                            scaleBy(4 * getOrb().getOriginalScale(), 4 * getOrb().getOriginalScale(), 0),
                            rotateTo(0, 0),
                            alpha(0)
                    ),
                    parallel(
                            rotateTo(360, ORB_INTRO_SEQUENCE_TIME, Interpolation.exp5),
                            scaleTo(getOrb().getOriginalScale(), getOrb().getOriginalScale(), ORB_INTRO_SEQUENCE_TIME, Interpolation.pow2),
                            fadeIn(ORB_INTRO_SEQUENCE_TIME, Interpolation.pow2)
                    )
            ));

            getGameManager().playFx(GameManager.Fx.Init);
        }
    };

    private Runnable orbExit = new Runnable() {
        @Override
        public void run() {
            getOrb().getFragmentedActor().addAction(sequence(
                    parallel(
                            rotateBy(360, ORB_INTRO_SEQUENCE_TIME, Interpolation.exp5Out),
                            scaleTo(4, 4, ORB_INTRO_SEQUENCE_TIME, Interpolation.pow2),
                            fadeOut(ORB_INTRO_SEQUENCE_TIME, Interpolation.pow2)
                    )
            ));
        }
    };


    /**
     * Constructor
     * @param sm SuperManager
     */
    public Level(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        ratioMeterPixel = sm.getGameManager().getRatioMeterPixel();
        impulseFactor = 1 * getRatioMeterPixel();
        impulseMax = 40.0f;

        orbStartPosition = new Vector2();
        situations = new SnapshotArray<Situation>();
        worldViewport = new FitViewport(getWorldWidth(), getWorldHeight(), new OrthographicCamera());
        gestureViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        parallaxViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        hudViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        hudStage = new HUDStage(getAssetManager(), this, hudViewport);
        overlayStage = new OverlayStage(getAssetManager(), hudViewport); // todo Vp especifico?
        gestureStage = new GestureStage(gestureViewport, getAssetManager(), getRatioMeterPixel(), getGameManager().getZoomRatio());
        parallaxStage = new ParallaxStage(getAssetManager(), parallaxViewport, getRatioMeterPixel(), getGameManager().getZoomRatio());
        stats = new GameStats();
        currentOrbPosition = new Vector2();
        lastOrbPosition = new Vector2();

        world = new World(WORLD_GRAVITY, true);
        orb = new Orb(getAssetManager(), getWorld(), getRatioMeterPixel());
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

        hudStage.addAction(sequence(
                alpha(0),
                scaleTo(1/1.35f, 1/1.35f, 0)
        ));
    }


    /**
     * Screen Overrides
     */

    /**
     * TODO
     * Este método es muy lento para Android y se ralentiza.
     * Los métodos más lentos son syncActors() [4] y draw() [20]
     */
    @Override
    public void render(float delta) {
        clearColor();
        act();
        syncBodies();
        stepSimulation();
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
        overlayStage.dispose();
        hudStage.dispose();
        gestureStage.dispose();
        parallaxStage.dispose();
        world.dispose();
        super.dispose();
    }

    @Override
    public void show() {
        // super.show();
        unsetInputProcessor();
        hudStage.addAction(sequence(
                run(new Runnable() {
                    @Override
                    public void run() {
                        overlayStage.addAction(sequence(
                                alpha(1)
                        ));
                    }
                }),
                transition(Flow.ENTERING, getHierarchy()),
                run(new Runnable() {
                    @Override
                    public void run() {
                        overlayStage.addAction(fadeOut(0.5f));
                    }
                }),
                run(orbIntro),
                delay(Math.max(0.5f, ORB_INTRO_SEQUENCE_TIME)),
                run(UIRunnable.setInputProcessor(getProcessor())),
                run(new Runnable() {
                    @Override
                    public void run() {
                        paused = false;
                    }
                })
        ));

        stats.newTry();
        getGameManager().playMusic(GameManager.Track.Game);
    }

    @Override
    public void hide() {
        super.hide();

        // TODO Ojo: no se puede guardar 2 veces la misma stat
        // saveGameStats();
    }

    @Override
    public void pause() {
        super.pause();
        pauseGame();
    }


    /**
     * Backable methods
     */

    @Override
    public void back() {
        if (!isGamePaused()) pauseGame();
        else resumeGame();
    }


    /**
     * Class methods
     */

    public float getRatioMeterPixel() {
        return ratioMeterPixel;
    }

    public float getPixelsPerMeter() {
        return 1 / getRatioMeterPixel();
    }

    public float getWorldWidth() {
        return VIEWPORT_WIDTH * getRatioMeterPixel();
    }

    public float getWorldHeight() {
        return VIEWPORT_HEIGHT * getRatioMeterPixel();
    }

    public World getWorld() {
        return world;
    }

    public void stepSimulation() {
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

    /**
     * TODO
     * Iguala la posicion y rotacion de los Bodies a las de sus Actors
     */
    private void syncBodies() {
        // Todos los cuerpos
        // Por hacer

        // Orb
        orb.syncBody();
    }

    /**
     * Iguala la posicion y rotacion de los Actors a las de sus Bodies
     */
    public void syncActors() {
        for (Situation situation : situations ) {
            for (Element element : situation.getElements()) {
                element.syncActor(worldViewport, getWorldWidth(), getWorldHeight(), getPixelsPerMeter());
            }
        }

        orb.syncActor(worldViewport, getWorldWidth(), getWorldHeight(), getPixelsPerMeter());
    }

    private void syncActor(Element element) {
        element.syncActor(worldViewport, getWorldWidth(), getWorldHeight(), getPixelsPerMeter());
    }

    public void addOrb(Orb orb) {
        //addMainActor(orb.getActor());
        addMainActor(orb.getFragmentedActor());
        orb.syncActor(worldViewport, getWorldWidth(), getWorldHeight(), getPixelsPerMeter());
        orb.getActor().setZIndex(INFINITE_Z_INDEX);
    }

    public Orb getOrb () {
        return this.orb;
    }

    public void addSituation (Situation situation) {
        this.situations.add(situation);
        int size = this.situations.size;
        situation.setOrder(size-1);

        for (Element element : situation.getElements()) {
            addMainActor(element.getActor());
            syncActor(element);
        }

        orb.getActor().setZIndex(INFINITE_Z_INDEX);
        orb.getFragmentedActor().setZIndex(INFINITE_Z_INDEX);
    }

    public SnapshotArray<Situation> getSituations () {
        return this.situations;
    }

    /**
     * Hace que la camara siga al Orb, igualando su posicion
     */
    private void followCamera() {
        worldViewport.getCamera().position.x = orb.getBody().getPosition().x;
        worldViewport.getCamera().position.y = orb.getBody().getPosition().y;
        worldViewport.getCamera().update();
    }

    private void act() {
        parallaxStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getMainStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        gestureStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        overlayStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        hudStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
    }

    private void draw() {
        parallaxStage.draw(worldViewport.getCamera().position.x, worldViewport.getCamera().position.y);
        // parallaxStage.draw();
        getMainStage().draw();
        gestureStage.draw();
        overlayStage.draw();
        hudStage.draw();
        // debugRenderer.render(world, worldViewport.getCamera().combined);
    }

    /**
     * Metodos de GameManager
     *
     * TODO
     * Mover a GameManager
     */

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

    public void pauseGame() {
        if (!isGamePaused()) {
            paused = true;
            hudStage.pause();
            getGameManager().playFx(GameManager.Fx.Back);
        }
    }

    public void resumeGame() {
        if (!locked) {
            hudStage.resume(unpause);
            getGameManager().playFx(GameManager.Fx.Button);
        }
    }

    public void restartGame() {
        hudStage.restart(restart, unpause, orbIntro);
        getGameManager().playFx(GameManager.Fx.Button);
    }

    /**
     * Abandono manual del juego.
     * Sólo puede ocurrir desde el menú de pausa
     */
    public void leaveGame() {
        unsetInputProcessor();
        saveGameStats();
        getGameManager().playFx(GameManager.Fx.Back);
        overlayStage.addAction(sequence(
                run(new Runnable() {
                    @Override
                    public void run() {
                        hudStage.getHUDBackground().setPositionGrid(-12, -18);
                        hudStage.getHUDBackground().addAction(scaleBy(4,4));
                    }
                }),
                //fadeIn(0.35f, Interpolation.pow2),
                run(new Runnable() {
                    @Override
                    public void run() {
                        switchToScreen(getPreviousScreen(), Hierarchy.HIGHER);
                        getGameManager().playMusic(GameManager.Track.Menu);
                    }
                })
        ));
    }

    @Override
    public void switchToScreen(final ScreenManager.Key key, final Hierarchy hierarchy) {
        hudStage.addAction(sequence(
                run(new Runnable() {
                    @Override
                    public void run() {
                        overlayStage.addAction(fadeIn(0.35f, Interpolation.pow2));
                    }
                }),
                delay(0.35f),
                /*run(new Runnable() {
                    @Override
                    public void run() {
                        hudStage.getHUDBackground().addAction(fadeOut(0.3f * 0.5f, Interpolation.pow2));
                    }
                }),*/
                transition(Flow.LEAVING, hierarchy),
                run(UIRunnable.setScreen(getScreenManager(), key, hierarchy))
        ));
    }

    /**
     * El orbe ha alcanzado la salida
     */
    public void successGame() {
        stats.setAsSuccess(true);
        saveGameStats();
        saveLastAttempt();
        unsetInputProcessor();
        paused = true;
        locked = true;

        overlayStage.addAction(sequence(
                run(new Runnable() {
                    @Override
                    public void run() {
                        getGameManager().playFx(GameManager.Fx.Exit);
                    }
                }),
                run(new Runnable() {
                    @Override
                    public void run() {
                        getOrb().getFragmentedActor().addAction(sequence(
                                parallel(
                                        rotateBy(360, ORB_EXIT_SEQUENCE_TIME, Interpolation.exp5Out),
                                        scaleTo(4, 4, ORB_EXIT_SEQUENCE_TIME, Interpolation.pow2),
                                        fadeOut(ORB_EXIT_SEQUENCE_TIME, Interpolation.pow2)
                                )
                        ));
                    }
                }),
                delay(ORB_INTRO_SEQUENCE_TIME),
                run(new Runnable() {
                    @Override
                    public void run() {
                        switchToScreen(getSuccessScreen(), Hierarchy.HIGHER);
                    }
                })
        ));
    }

    /**
     * Acciones cuando se detecte una colision normal
     */
    public void collision() {
        getGameManager().playFx(GameManager.Fx.Collision);
        getGameManager().vibrateShort();
    }

    public boolean isGamePaused() {
        return paused;
    }

    public void setOrbStartPosition (float x, float y) {
        orbStartPosition.set(x, y);
        getOrb().setPosition(orbStartPosition.x, orbStartPosition.y);
        currentOrbPosition.set(x, y);
        lastOrbPosition.set(x, y);
    }

    public void tap() {
        if (!isGamePaused()) {
            getOrb().increaseHeat();
            gestureStage.drawTap();
            getOrb().lock();

            if (getOrb().isOverloaded()) {
                destroyOrb();
            }

            if (getOrb().getHeat() == Orb.HEAT_MAX) {
                getOrb().setOverloaded(true);
                hudStage.setGaugeOverload(true);
                orbOverloadTimer = OVERLOAD_TIME;
            }

            getGameManager().vibrateMedium();
            getGameManager().playFx(GameManager.Fx.Tap);
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

    public void fling (float velocityX, float velocityY) {
        float impulseX = MathUtils.clamp(velocityX * impulseFactor, -impulseMax, impulseMax);
        float impulseY = MathUtils.clamp(-velocityY * impulseFactor, -impulseMax, impulseMax);

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
            getGameManager().playFx(GameManager.Fx.Fling);
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
            distance = distance(currentOrbPosition, lastOrbPosition);
            lastOrbPosition = currentOrbPosition;

            stats.addTime(Gdx.graphics.getRawDeltaTime());
            stats.addDistance(distance);
            hudStage.setDistanceValue(stats.getCurrentDistance());
            hudStage.setFullDistanceValue(stats.fullDistance());
            hudStage.setFullTimeValue(stats.fullTime());
            hudStage.setFullDestroyedValue(stats.fails());
        }
    }

    public void destroyOrb() {
        Runnable unlock = new Runnable() {
            @Override
            public void run() {
                locked = false;
            }
        };

        Runnable destroyOrb = new Runnable() {
            @Override
            public void run() {
                getOrb().destroy();
            }
        };

        paused = true;
        locked = true;
        getGameManager().vibrateLong();
        getGameManager().playFx(GameManager.Fx.Destroy);
        stats.setAsFail(true);
        hudStage.destroyAndRestart(restart, destroyOrb, unlock, unpause, orbIntro);
    }

    /**
     * Distancia entre 2 puntos
     */
    private float distance(Vector2 pointA, Vector2 pointB) {
        Vector2 inter = new Vector2(
                pointB.x - pointA.x,
                pointB.y - pointA.y
        );

        return (float) Math.sqrt(Math.pow(inter.x, 2) + Math.pow(inter.y, 2));
    }

    /**
     * TODO
     * Esto debería hacerse en el PrefsManager
     */
    private void saveGameStats() {
        Preferences prefs = getPrefsManager().getPrefs();

        if (!stats.isEmpty()) {
            prefs.putFloat(STAT_TIME, prefs.getFloat(STAT_TIME) + stats.fullTime());
            prefs.putFloat(STAT_DISTANCE, prefs.getFloat(STAT_DISTANCE) + stats.fullDistance());
            prefs.putInteger(STAT_FAILS, prefs.getInteger(STAT_FAILS) + stats.fails());
            prefs.putInteger(STAT_SUCCESSES, prefs.getInteger(STAT_SUCCESSES) + stats.successes());

            int completedAttempts = stats.completedAttempts().size();

            if (completedAttempts > 0) {
                float minTimeAlive = stats.minTimeAlive();
                float minDistanceAlive = stats.minDistanceAlive();
                float maxTimeAlive = stats.maxTimeAlive();
                float maxDistanceAlive = stats.maxDistanceAlive();
                float avgTimeAlive = stats.averageTimeAlive();
                float avgDistanceAlive = stats.averageDistanceAlive();

                int savedAttempts = prefs.getInteger(STAT_COMPLETED_ATTEMPTS);
                float savedAvgTimeAlive = prefs.getFloat(STAT_AVG_TIME_ALIVE);
                float savedAvgDistanceAlive = prefs.getFloat(STAT_AVG_DISTANCE_ALIVE);

                if (minTimeAlive < prefs.getFloat(STAT_MIN_TIME_ALIVE) || savedAttempts == 0)
                    prefs.putFloat(STAT_MIN_TIME_ALIVE, minTimeAlive);

                if (minDistanceAlive < prefs.getFloat(STAT_MIN_DISTANCE_ALIVE) || savedAttempts == 0)
                    prefs.putFloat(STAT_MIN_DISTANCE_ALIVE, minDistanceAlive);

                if (maxTimeAlive > prefs.getFloat(STAT_MAX_TIME_ALIVE))
                    prefs.putFloat(STAT_MAX_TIME_ALIVE, maxTimeAlive);

                if (maxDistanceAlive > prefs.getFloat(STAT_MAX_DISTANCE_ALIVE))
                    prefs.putFloat(STAT_MAX_DISTANCE_ALIVE, maxDistanceAlive);

                float wAvgTimeAlive = ((float) savedAttempts * savedAvgTimeAlive + completedAttempts * avgTimeAlive) / ((float) savedAttempts + completedAttempts);
                float wAvgDistanceAlive = ((float) savedAttempts * savedAvgDistanceAlive + completedAttempts * avgDistanceAlive) / ((float) savedAttempts + completedAttempts);

                prefs.putFloat(STAT_AVG_TIME_ALIVE, wAvgTimeAlive);
                prefs.putFloat(STAT_AVG_DISTANCE_ALIVE, wAvgDistanceAlive);
                prefs.putInteger(STAT_COMPLETED_ATTEMPTS, prefs.getInteger(STAT_COMPLETED_ATTEMPTS) + completedAttempts);
            }

            // Guarda los mejores tiempos
            PersonalTimes personalTimes = new PersonalTimes(prefs, getKey());
            personalTimes.addAttempt(stats.getLastAttempt());
            personalTimes.save();
        }

        getPrefsManager().save();
    }

    public void setSuccessScreen(ScreenManager.Key key) {
        this.successScreen = key;
    }

    public ScreenManager.Key getSuccessScreen() {
        return this.successScreen;
    }

    private void saveLastAttempt () {
        getGameManager().setLastSuccessfulAttempt(stats.getLastAttempt());
    }

}
