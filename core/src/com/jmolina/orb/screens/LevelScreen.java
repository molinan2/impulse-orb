package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.data.GameStats;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.listeners.GestureHandler;
import com.jmolina.orb.listeners.ContactHandler;
import com.jmolina.orb.situations.Situation;
import com.jmolina.orb.stages.GestureStage;
import com.jmolina.orb.stages.HUDStage;
import com.jmolina.orb.stages.ParallaxStage;

import static com.jmolina.orb.managers.PrefsManager.*;


/**
 * TODO
 *
 * Temporalmente, esta pantalla está actuando como GameManager
 *
 * Solo se pueden añadir elementos mediante Situations, no directamente.
 * World camera is always centered at Orb position
 */
public class LevelScreen extends BaseScreen {

    /**
     * Constants
     */
    public final static float RATIO_METER_PIXEL = 0.015625f; // Grid: 12x18.5, 64 pixel/metro
    public final static float PIXELS_PER_METER = 1 / RATIO_METER_PIXEL;
    private final static float WORLD_WIDTH = VIEWPORT_WIDTH * RATIO_METER_PIXEL;
    private final static float WORLD_HEIGHT = VIEWPORT_HEIGHT * RATIO_METER_PIXEL;
    private static final float HALF_TAP_SQUARE_SIZE = 20.0f;
    private static final float TAP_COUNT_INTERVAL = 0.4f;
    private static final float LONG_PRESS_DURATION = 1.1f;
    private static final float MAX_FLING_DELAY = 0.15f;
    private final Vector2 WORLD_GRAVITY = new Vector2(0, -20f);
    private final float WORLD_TIME_STEP = 1/60f;
    private final float WORLD_PRECISION_FACTOR = 1;
    private final int WORLD_VELOCITY_INTERACTIONS = 8;
    private final int WORLD_POSITION_INTERACTIONS = 3;
    private final float ORB_MAX_LOCK_TIME = 0.5f;
    private final float COOLING_RATE = 0.1f;
    private final int INFINITE_Z_INDEX = 32000;
    private final float MAX_IMPULSE = 40.0f;
    private final float IMPULSE_FACTOR_X = RATIO_METER_PIXEL;
    private final float IMPULSE_FACTOR_Y = -RATIO_METER_PIXEL;

    /**
     * Fields
     */
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
    private boolean paused = true;
    private Vector2 orbStartPosition;
    // private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private GameStats stats;
    private Vector2 currentOrbPosition;
    private Vector2 lastOrbPosition;

    /**
     * Runnables
     */

    private Runnable reset = new Runnable() {
        @Override
        public void run() {
            getOrb().setPosition(orbStartPosition.x, orbStartPosition.y);
            getOrb().resetHeat();
            getOrb().resetVelocity();
            hudStage.resetTimer();
            stats.newTry();
        }
    };

    private Runnable unpause = new Runnable() {
        @Override
        public void run() {
            paused = false;
        }
    };


    /**
     * Constructor
     * @param superManager SuperManager
     */
    public LevelScreen(SuperManager superManager) {
        super(superManager);

        orbStartPosition = new Vector2();
        situations = new SnapshotArray<Situation>();
        worldViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, new OrthographicCamera());
        gestureViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        parallaxViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        hudViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());
        hudStage = new HUDStage(getAssetManager(), this, hudViewport);
        gestureStage = new GestureStage(gestureViewport, getAssetManager());
        parallaxStage = new ParallaxStage(getAssetManager(), parallaxViewport);
        stats = new GameStats();
        currentOrbPosition = new Vector2();
        lastOrbPosition = new Vector2();

        world = new World(WORLD_GRAVITY, true);
        orb = new Orb(getAssetManager(), getWorld());
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
        super.dispose();
        hudStage.dispose();
        gestureStage.dispose();
        parallaxStage.dispose();
    }

    @Override
    public void show() {
        super.show();
        firstStartGame();
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

    public World getWorld() {
        return world;
    }

    public void stepSimulation() {
        if (!isGamePaused()) {
            for (int i=0; i<WORLD_PRECISION_FACTOR; i++) {
                world.step(
                        WORLD_TIME_STEP / WORLD_PRECISION_FACTOR,
                        WORLD_VELOCITY_INTERACTIONS,
                        WORLD_POSITION_INTERACTIONS
                );
            }
        }
    }

    /**
     * Iguala la posicion y rotacion de los Actors a las de sus Bodies
     */
    public void syncActors() {
        for (Situation situation : situations ) {
            for (Element element : situation.getElements()) {
                element.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, PIXELS_PER_METER);
            }
        }

        orb.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, PIXELS_PER_METER);
    }

    /**
     * TODO
     * Iguala la posicion y rotacion de los Bodies a la de sus Actors
     */
    private void syncBodies() {

    }

    public void syncActor(Element element) {
        element.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, PIXELS_PER_METER);
    }

    public void addOrb(Orb orb) {
        addMainActor(orb.getActor());
        orb.syncActor(worldViewport, WORLD_WIDTH, WORLD_HEIGHT, PIXELS_PER_METER);
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
        hudStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
    }

    private void draw() {
        parallaxStage.draw(worldViewport.getCamera().position.x, worldViewport.getCamera().position.y);
        getMainStage().draw();
        gestureStage.draw();
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
        }
    }

    public void resumeGame() {
        hudStage.resume(unpause);
    }

    public void firstStartGame() {
        hudStage.firstStart(reset, unpause);
    }

    public void restartGame() {
        hudStage.restart(reset, unpause);
    }

    /**
     * Abandono manual del juego
     */
    public void leaveGame() {
        saveGameStats();
        // pausa (ya estaba en pausa)
        // stage -> secuencia de salida
        switchToScreen(getPreviousScreenKey(), Hierarchy.HIGHER);
    }

    /**
     * El orbe ha alcanzado la salida
     */
    public void successGame() {
        stats.setSuccessful(true);
        saveGameStats();
        // pausa
        // stage -> secuencia de exito
        // pantalla de success
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

    public void tap () {
        if (!isGamePaused()) {
            getOrb().lock();
            getOrb().increaseHeat();
            gestureStage.drawTap();
        }
    }

    public void fling (float velocityX, float velocityY) {
        float impulseX = MathUtils.clamp(velocityX * IMPULSE_FACTOR_X, -MAX_IMPULSE, MAX_IMPULSE);
        float impulseY = MathUtils.clamp(velocityY * IMPULSE_FACTOR_Y, -MAX_IMPULSE, MAX_IMPULSE);

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
        }
    }

    public void updateHeat() {
        if (!isGamePaused()) {
            float decrement = COOLING_RATE * Gdx.graphics.getRawDeltaTime();
            getOrb().decreaseHeat(decrement);
            hudStage.setGaugeLevel(getOrb().getHeat());
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
        stats.setFailed(true);

        // pausa
        // secuencia de destruccion
        // restart level
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
        }

        getPrefsManager().save();
    }

}
