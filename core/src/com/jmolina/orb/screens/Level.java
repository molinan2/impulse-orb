package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.actions.UIAction;
import com.jmolina.orb.data.GameStats;
import com.jmolina.orb.data.Tick;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.listeners.GestureHandler;
import com.jmolina.orb.listeners.ContactHandler;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.runnables.UIRunnable;
import com.jmolina.orb.situations.Situation;
import com.jmolina.orb.situations.SituationFactory;
import com.jmolina.orb.stages.GestureStage;
import com.jmolina.orb.stages.HUDStage;
import com.jmolina.orb.stages.ParallaxStage;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.misc.DebugTime;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


/**
 * {@inheritDoc}
 * Clase base para los niveles del juego. Permite su construcción a partir de {@link Situation}s.
 * Renderiza todos los elementos del juego y los sincroniza con el mundo físico. Controla los
 * eventos del juego.
 */
public class Level extends BaseScreen {

    private enum Adjacency { TOP, BOTTOM }
    private enum Frontier { BOUNDARY, MIDDLE }

    private final boolean DEBUG_WORLD = false;
    private final boolean DEBUG_TIME = false;
    private final boolean DEBUG_INVULNERABLE = false;
    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private DebugTime debugTime = new DebugTime(getAssetManager());

    public static final float INTRO_SEQUENCE_TIME = 1f;

    private final float BACKGROUND_FADE_TIME = 0.5f;
    private final float GESTURE_HALF_TAP_SQUARE_SIZE = 14.0f;
    private final float GESTURE_TAP_COUNT_INTERVAL = 0.4f;
    private final float GESTURE_LONG_PRESS_DURATION = 1.1f;
    private final float GESTURE_MAX_FLING_DELAY = 0.1f;
    private final Vector2 WORLD_GRAVITY = new Vector2(0, -20f);
    private final float WORLD_TIME_STEP = Var.FPS;
    private final int WORLD_STEP_MULTIPLIER = 4;
    private final int WORLD_VELOCITY_ITERATIONS = 8;
    private final int WORLD_POSITION_ITERATIONS = 3;
    private final boolean WOLRD_ACCUMULATED_STEP = false;
    private final int Z_INDEX_ORB = 20000;
    private final int Z_INDEX_BLACK = 10000;
    private final float MAGNETIC_FACTOR = 0.2f;
    private final float IMPULSE_FACTOR = 0.6f;
    private final float IMPULSE_MAX = 50f;

    private Tick tick;
    private float pixelsPerMeter, impulse, physicsStepAccumulator;
    private boolean locked, ticking;
    private World world;
    private ContactHandler contactHandler;
    private Viewport worldViewport, gestureViewport, hudViewport, parallaxViewport;
    private GestureStage gestureStage;
    private GestureDetector gestureDetector;
    private GestureHandler gestureHandler;
    private ParallaxStage parallaxStage;
    private HUDStage hudStage;
    private Orb orb;
    private Vector2 orbStartPosition, lastOrbPosition;
    private GameStats stats;
    private ScreenManager.Key successScreen = ScreenManager.Key.LEVEL_SELECT;
    private Runnable orbIntro, orbDestroy, reset, unlock, toSuccess;
    private SituationFactory situationFactory;

    private ArrayList<Class> situations;
    private Situation currentSituation, adjacentSituation;
    private Adjacency adjacencyNow, adjacencyLast;


    /**
     * Constructor
     *
     * @param sm SuperManager
     */
    public Level(SuperManager sm) {
        super(sm);

        tick = new Tick();
        pixelsPerMeter = getGameManager().getPixelsPerMeter();
        impulse = IMPULSE_FACTOR / getPixelsPerMeter();
        orbStartPosition = new Vector2();
        lastOrbPosition = new Vector2();
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
        physicsStepAccumulator = 0f;
        setOrb(new Orb(getAssetManager(), getWorld(), getPixelsPerMeter()));

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

        situationFactory = new SituationFactory(getAssetManager(), getWorld(), getPixelsPerMeter());
        situations = new ArrayList<Class>();
        currentSituation = null;
        adjacentSituation = null;

        addProcessor(hudStage);
        addProcessor(gestureStage);
        addProcessor(gestureDetector);
        createRunnables();
        lock();
        disableTicking();
    }

    /**
     * Crea los runables que se utilizarán como callbacks
     */
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

                if (currentSituation != null) currentSituation.dispose();
                if (adjacentSituation != null) adjacentSituation.dispose();
                currentSituation = null;
                adjacentSituation = null;
            }
        };

        unlock = new Runnable() {
            @Override
            public void run() {
                unlock();
            }
        };

        orbDestroy = new Runnable() {
            @Override
            public void run() {
                getOrb().destroy();
            }
        };

        toSuccess = new Runnable() {
            @Override
            public void run() {
                switchToScreen(getSuccessScreen(), Hierarchy.LOWER);
            }
        };
    }

    /**
     * Renderiza el nivel de juego
     *
     * @param delta Tiempo de frame
     */
    @Override
    public void render(float delta) {
        if (DEBUG_TIME) debugTime.start();

        clear();
        updateVisibility();
        syncActors();
        act(delta);
        syncBodies();
        preUpdate();
        stepPhysics(delta);
        followCamera();
        syncActors();
        postUpdate();
        draw();
        checkSwitching();

        if (DEBUG_TIME) debugTime.end();
    }

    /**
     * Actualiza las situaciones visibles.
     */
    private void updateVisibility() {
        float altitude = getOrb().getPosition().y / Situation.HEIGHT;
        int current = (int) altitude;
        adjacencyLast = adjacencyNow;
        adjacencyNow = findAdjacency(altitude);

        int adjacent = calculateAdjacent(current);

        if (noVisibility())
            initializeVisibility(current, adjacent);

        if (crossedFrontier()) {
            switch (findFrontier(altitude)) {
                case MIDDLE: newAdjacent(adjacent); break;
                case BOUNDARY: swapSituations(); break;
            }
        }
    }

    /**
     * Calcula el índice adyacente en función del actual
     *
     * @param current Índice de la situación actual
     */
    private int calculateAdjacent(int current) {
        int adjacent;

        if (adjacencyNow == Adjacency.TOP)
            adjacent = current + 1;
        else
            adjacent = current - 1;

        return adjacent;
    }

    /**
     * Devuelve si no hay visibilidad. Esto ocurre si la situación actual no está inicializada, por
     * ejemplo al iniciar el nivel.
     */
    private boolean noVisibility() {
        return currentSituation == null;
    }

    /**
     * Inicializa la visibilidad: crea la situación actual y la añade al nivel. Si procede, crea la
     * situación adyacente y la añade al nivel.
     *
     * @param current Índice de la situación actual
     * @param adjacent Índice de la situación adyacente
     */
    private void initializeVisibility(int current, int adjacent) {
        Class currentClass = situations.get(current);
        currentSituation = situationFactory.newSituation(currentClass);
        addSituation(currentSituation, current);

        if (adjacent >= 0 && adjacent <= situations.size()-1) {
            Class adjacentClass = situations.get(adjacent);
            adjacentSituation = situationFactory.newSituation(adjacentClass);
            addSituation(adjacentSituation, adjacent);
        }
    }

    /**
     * Detecta el paso del Orb por una {@link Frontier}.
     */
    private boolean crossedFrontier() {
        return adjacencyLast != adjacencyNow;
    }

    /**
     * Calcula la {@link Adjacency}, es decir, si la situación adyacente se encuentra por arriba o por debajo.
     *
     * @param altitude Altitud de la cámara (en unidades del mundo)
     */
    private Adjacency findAdjacency(float altitude) {
        float decimalPart = Utils.decimalPart(altitude);

        if (decimalPart >= 0.5f)
            return Adjacency.TOP;
        else
            return Adjacency.BOTTOM;
    }

    /**
     * Calcula la {@link Frontier} más cercana. Se asume que la velocidad del Orb no va a ser tan
     * alta como para, en un tiempo de frame, cruzar una frontera y colocarse más cerca de OTRA
     * frontera.
     *
     * @param altitude Altitud del {@link Orb} (en unidades del mundo)
     */
    private Frontier findFrontier(float altitude) {
        float decimalPart = Utils.decimalPart(altitude);

        if (decimalPart > 0.25f && decimalPart < 0.75f)
            return Frontier.MIDDLE;
        else
            return Frontier.BOUNDARY;
    }

    /**
     * Se destruye la anterior adyacente, se instancia una nueva y se añade al nivel.
     *
     * @param adjacent Índice de la situación adyacente
     */
    private void newAdjacent(int adjacent) {
        if (adjacentSituation != null)
            adjacentSituation.dispose();

        adjacentSituation = null;

        if (adjacent >= 0 && adjacent <= situations.size()-1) {
            Class adjacentClass = situations.get(adjacent);
            adjacentSituation = situationFactory.newSituation(adjacentClass);
            addSituation(adjacentSituation, adjacent);
        }
    }

    /**
     * Se intercambian las situaciones adyacente y actual.
     */
    private void swapSituations() {
        Situation temporal = currentSituation;
        currentSituation = adjacentSituation;
        adjacentSituation = temporal;
    }



    /**
     * {@inheritDoc}
     * Actualiza los viewports del juego
     */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        worldViewport.update(width, height);
        gestureViewport.update(width, height);
        hudViewport.update(width, height);
        parallaxViewport.update(width, height);
    }

    /**
     * {@inheritDoc}
     * Libera la memoria
     */
    @Override
    public void dispose() {
        if (currentSituation != null) currentSituation.dispose();
        if (adjacentSituation != null) adjacentSituation.dispose();
        getHUDStage().dispose();
        getGestureStage().dispose();
        getParallaxStage().dispose();
        getWorld().dispose();
        super.dispose();
    }

    /**
     * Ejecuta la transición de entrada en el nivel e inicializa las estadísticas
     */
    @Override
    public void show() {
        unsetInputProcessor();
        firstGame();
        getGameManager().play(GameManager.Track.Game);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        super.hide();
    }

    /**
     * {@inheritDoc}
     * Inicia el menú de pausa
     */
    @Override
    public void pause() {
        super.pause();
        pauseGame();
    }

    /**
     * Si el juego estaba en marcha, lo pausa y muestra el menú de pausa. Si estaba pausado, lo
     * despausa y continúa.
     */
    @Override
    public void back() {
        if (!isLocked()) pauseGame();
        else resumeGame();
    }

    /**
     * Cambia a la pantalla indicada
     *
     * @param screen Identificador de la siguiente pantalla
     * @param hierarchy Jerarquía de la siguiente pantalla respecto de la actual
     */
    @Override
    public void switchToScreen(final ScreenManager.Key screen, final Hierarchy hierarchy) {

        Runnable flagSwitch = new Runnable() {
            @Override
            public void run() {
                flagSwitch(screen, hierarchy);
            }
        };

        getHUDStage().addAction(sequence(
                Actions.addAction(fadeIn(UIAction.DURATION, Interpolation.pow2), getBackgroundStage().getRoot()),
                delay(UIAction.DURATION),
                transition(Flow.LEAVING, hierarchy),
                run(flagSwitch)
        ));
    }

    /**
     * Devuelve el valor actual de {@link #pixelsPerMeter}
     *
     * @return {@link #pixelsPerMeter}
     */
    public float getPixelsPerMeter() {
        return pixelsPerMeter;
    }

    /**
     * Devuelve el mundo Box2D
     *
     * @return {@link #world}
     */
    public World getWorld() {
        return world;
    }

    /**
     * Devuelve la Stage del fondo de scroll parallax
     *
     * @return {@link #parallaxStage}
     */
    public ParallaxStage getParallaxStage() {
        return parallaxStage;
    }

    /**
     * Devuelve la Stage de dibujado de gestos
     *
     * @return {@link #gestureStage}
     */
    public GestureStage getGestureStage() {
        return gestureStage;
    }

    /**
     * Devuelve la Stage del HUD
     *
     * @return {@link #hudStage}
     */
    public HUDStage getHUDStage() {
        return hudStage;
    }

    /**
     * Devuelve las estadísticas del juego en curso
     *
     * @return {@link #stats}
     */
    public GameStats getStats() {
        return stats;
    }

    /**
     * Guarda la pantalla de éxito correspondiente a este nivel
     */
    public void setSuccessScreen(ScreenManager.Key key) {
        successScreen = key;
    }

    /**
     * Obtiene la pantalla de éxito correspondiente a este nivel
     *
     * @return {@link #successScreen}
     */
    public ScreenManager.Key getSuccessScreen() {
        return successScreen;
    }

    /**
     * Setea el {@link #orb} a la Stage principal
     *
     * @param orb {@link Orb}
     */
    public void setOrb(Orb orb) {
        addMainActor(orb.getActor());
        orb.syncActor(worldViewport);
        orb.getActor().setZIndex(Z_INDEX_ORB);
        this.orb = orb;
    }

    /**
     * Obtiene el {@link Orb} de la pantalla de juego actual
     *
     * @return {@link #orb}
     */
    public Orb getOrb () {
        return orb;
    }

    /**
     * Setea la posición de incio del {@link #orb} en el nivel actual
     *
     * @param x Coordenada x del mundo
     * @param y Coordenada y del mundo
     */
    public void setOrbStartPosition (float x, float y) {
        getOrb().setPosition(x, y);
        orbStartPosition.set(x, y);
        lastOrbPosition.set(x, y);
    }

    /**
     * TODO
     *
     * @param clazz
     */
    protected void addSituation(Class clazz) {
        // addSituation(situationFactory.newSituation(clazz));

        situations.add(clazz);
    }

    private void addSituation (Situation situation, int positionY) {
        // situations.add(situation);
        situation.setPositionY(positionY);

        for (Element element : situation.getElements()) {
            addMainActor(element.getActor());
            element.syncActor(worldViewport);
        }

        correctZIndexes();
    }

    /**
     * Corrige los Z index de los elementos BLACK (muros) y el Orb. Los elementos BLACK deben
     * permanecer siempre por encima de los demás. El Orb debe permanecer por encima de todos.
     */
    private void correctZIndexes() {
        for (Situation situation : getVisibleSituations()) {
            if (situation == null) continue;
            for (Element element : situation.getElements()) {
                if (element.getFlavor() == WorldElement.Flavor.BLACK)
                    element.getActor().setZIndex(Z_INDEX_BLACK);
            }
        }

        getOrb().getActor().setZIndex(Z_INDEX_ORB);
    }

    /**
     * Devuelve el array de situaciones del nivel actual
     *
     * @return SnapshotArray de {@link Situation}s
     */
    public SnapshotArray<Situation> getVisibleSituations() {
        SnapshotArray<Situation> situations = new SnapshotArray<Situation>();
        situations.add(currentSituation);
        situations.add(adjacentSituation);

        return situations;
    }


    /**
     * Avanza la simulación usando el método del acumulador. Este método es útil para entornos con
     * pocos recursos, en los que es habitual que el tiempo de frame supere el tiempo máximo por
     * frame (1/60 a 60 fps), ya que permite "avanzar más" la simulación hasta alcanzar el tiempo
     * de frame. Tiene la desventaja de que quedan residuos temporales en el acumulador, que pueden
     * provocar saltos en frames posteriores (aliasing temporal).
     *
     * Si sobran los recursos, es más recomendable usar un timestep fijo.
     */
    public void stepPhysics(float delta) {
        if (isLocked()) return;

        if (WOLRD_ACCUMULATED_STEP) accumulatedPhysics(delta);
        else multiStepPhysics();
    }

    /**
     * Acumula el tiempo de frame y realiza pasos de la simulación hasta alcanzarlo. Es más apropiado
     * cuando la ejecución es lenta (más de 16 ms de tiempo de frame).
     *
     * @param delta
     */
    private void accumulatedPhysics(float delta) {
        float frameTime = Math.min(delta, 0.166666f);
        physicsStepAccumulator += frameTime;
        while (physicsStepAccumulator >= WORLD_TIME_STEP) {
            world.step(WORLD_TIME_STEP, WORLD_VELOCITY_ITERATIONS, WORLD_POSITION_ITERATIONS);
            physicsStepAccumulator -= WORLD_TIME_STEP;
        }
    }

    /**
     * Realiza {@link #WORLD_STEP_MULTIPLIER} pasos de la simulación física por cada fotograma. Un
     * mayor número de pasos aumenta la precisión de las colisiones, a costa de mayor consumo de
     * recursos. Es más apropiado cuando la ejecución es muy rápida (menos de 16 ms de tiempo de
     * frame).
     */
    private void multiStepPhysics() {
        for (int i=0; i<WORLD_STEP_MULTIPLIER; i++) {
            world.step(
                    WORLD_TIME_STEP / (float) WORLD_STEP_MULTIPLIER,
                    WORLD_VELOCITY_ITERATIONS,
                    WORLD_POSITION_ITERATIONS
            );
        }
    }

    /**
     * Sincroniza la posición y rotación de los cuerpos con las de sus actores.
     * No es necesaria en el caso de elementos no móviles
     */
    private void syncBodies() {
        for (Situation situation : getVisibleSituations()) {
            if (situation == null) continue;
            for (Element element : situation.getElements()) {
                if (element instanceof Movable)
                    element.syncBody(worldViewport);
            }
        }

        getOrb().syncBody(worldViewport, false, true);
    }

    /**
     * Sincroniza la posición y rotación de los actores con las de sus cuerpos.
     * Es necesaria en todos los casos, para que los actores se correspondan con el scroll.
     */
    public void syncActors() {
        for (Situation situation : getVisibleSituations()) {
            if (situation == null) continue;
            for (Element element : situation.getElements()) {
                element.syncActor(worldViewport);
            }
        }

        getOrb().syncActor(worldViewport);
    }

    /**
     * Centra la cámara en el Orb
     */
    private void followCamera() {
        worldViewport.getCamera().position.x = getOrb().getBody().getPosition().x;
        worldViewport.getCamera().position.y = getOrb().getBody().getPosition().y;
        worldViewport.getCamera().update();
    }

    /**
     * Ejecuta un paso más las Actions de cada actor
     */
    private void act(float delta) {
        if (!isLocked())
            getMainStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        else
            getOrb().getActor().act(delta);

        getGestureStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getBackgroundStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getHUDStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
    }

    /**
     * Dibuja todos los actores
     */
    private void draw() {
        getParallaxStage().draw(worldViewport.getCamera());
        getMainStage().draw();
        getGestureStage().draw();
        getBackgroundStage().draw();
        if (DEBUG_WORLD) debugRenderer.render(world, worldViewport.getCamera().combined);
        getHUDStage().draw();
    }

    /**
     * Render update
     * Computa las fuerzas sobre el orbe, si el juego no está bloqueado
     */
    private void preUpdate() {
        if (isLocked()) return;

        computeForces();
    }

    /**
     * Render update
     * Comprueba y actualiza datos y estados, si el juego no está bloqueado
     */
    private void postUpdate() {
        if (isLocked()) return;

        updateHeat();
        updateFreeze();
        updateTimer();
        updateStats();
    }

    /**
     * Actualiza el calor y su indicador. El calor puede estar generado por un
     * {@link GestureHandler#tap(float, float, int, int)} o por {@link #ticking}.
     */
    private void updateHeat() {
        if (isTicking()) {
            tick.update(Gdx.graphics.getRawDeltaTime());

            if (tick.expired()) {
                tick.reset();
                tick();
            }
        }

        getOrb().updateHeat();
        getHUDStage().setGaugeLevel(getOrb().getHeat());
        getHUDStage().setGaugeOverload(getOrb().isOverloaded());
    }

    /**
     * Actualiza el tiempo de congelación del Orb
     */
    private void updateFreeze() {
        getOrb().updateFreezeTime();
    }

    /**
     * Actualiza el cronómetro
     */
    private void updateTimer() {
        getHUDStage().updateTimer();
    }

    /**
     * Actualiza las estadísticas y su visualización
     */
    private void updateStats() {
        float distance = Utils.distance(getOrb().getPosition(), lastOrbPosition);
        lastOrbPosition = getOrb().getPosition();

        getStats().addTime(Gdx.graphics.getRawDeltaTime());
        getStats().addDistance(distance);
        getHUDStage().setDistanceValue(stats.getCurrentDistance());
        getHUDStage().setFullDistanceValue(stats.fullDistance());
        getHUDStage().setFullTimeValue(stats.fullTime());
        getHUDStage().setFullDestroyedValue(stats.fails());
    }

    /**
     * Calcula las fuerzas de atracción y repulsión activas en el orbe y las aplica.
     */
    private void computeForces() {
        Vector2 force = new Vector2(0, 0);

        for (Situation situation : getVisibleSituations()) {
            if (situation == null) continue;
            for (Element element : situation.getElements()) {
                if (element instanceof Magnetic) {
                    Vector2 partial = ((Magnetic)element).force(getOrb().getPosition());
                    force.add(partial);
                }
            }
        }

        force.scl(MAGNETIC_FACTOR);
        getOrb().getBody().applyLinearImpulse(force, getOrb().getPosition(), true);
    }

    private void firstGame() {
        stats.newTry();
        getBackgroundStage().addAction(alpha(1));
        getOrb().getActor().addAction(alpha(0));

        getHUDStage().addAction(sequence(
                alpha(0),
                scaleTo(UIAction.SMALL, UIAction.SMALL),
                transition(Flow.ENTERING, getHierarchy()),
                Actions.addAction(sequence(alpha(1), fadeOut(BACKGROUND_FADE_TIME)), getBackgroundStage().getRoot()),
                delay(0.5f * BACKGROUND_FADE_TIME),
                run(orbIntro),
                delay(INTRO_SEQUENCE_TIME),
                run(UIRunnable.setInputProcessor(getProcessor())),
                run(unlock)
        ));
    }

    /**
     * Inicia el menú de pausa del juego
     */
    public void pauseGame() {
        if (!isLocked()) {
            lock();
            getHUDStage().pause();
            getGameManager().play(GameManager.Fx.Back);
        }
    }

    /**
     * Reanuda el juego desde el menú de pausa
     */
    public void resumeGame() {
        if (isLocked()) {
            getHUDStage().resume(unlock);
            getGameManager().play(GameManager.Fx.Button);
        }
    }

    /**
     * Reinicia el juego desde el menú de pausa
     */
    public void restartGame() {
        getHUDStage().restart(reset, orbIntro, unlock);
        getGameManager().play(GameManager.Fx.Button);
    }

    /**
     * Abandona el juego desde el menú de pausa
     */
    public void leaveGame() {
        unsetInputProcessor();
        getPrefsManager().saveStats(stats, getThisKey());
        getGameManager().play(GameManager.Fx.Back);
        switchToScreen(getPreviousScreen(), Hierarchy.HIGHER);
    }

    /**
     * Completa el juego, guardando las estadísticas y lanzando la pantalla de Success
     */
    public void successGame() {
        lock();
        getStats().setSuccessfull(true);

        if (getStats().getLastAttempt() != null) {
            int rank = getPrefsManager().saveStats(getStats(), getThisKey());
            getGameManager().cache(getStats().getLastAttempt(), rank);
        }

        unsetInputProcessor();
        getGameManager().play(GameManager.Fx.Exit);
        getOrb().outro(toSuccess);
    }

    /**
     * Bloquea el juego.
     *
     * Mientras el juego está bloqueado, no se pueden actualizar datos ni estados, ni activar o
     * desactivar el menú de pausa.
     */
    public void lock() {
        locked = true;
    }

    /**
     * Desbloquea el juego
     */
    public void unlock() {
        locked = false;
    }

    /**
     * Devuelve true si el juego está bloqueado
     *
     * @return {@link #locked}
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Reproduce el sonido y la vibración correspondientes a una colisión
     *
     * @param wall Indica si se colisiona contra un muro
     */
    public void collide(boolean wall) {
        getGameManager().vibrate(GameManager.Length.Short);

        if (wall)
            getGameManager().play(GameManager.Fx.WallCollision);
        else
            getGameManager().play(GameManager.Fx.ElementCollision);
    }

    public void collide() {
        collide(false);
    }

    /**
     * Ejecuta un freeze sobre el {@link Orb} y dibuja su animación
     */
    public void freeze() {
        if (!isLocked()) {
            getOrb().freeze();
            getOrb().increaseHeat();
            getGestureStage().drawTap();
            getGameManager().vibrate(GameManager.Length.Medium);
            getGameManager().play(GameManager.Fx.Tap);

            if (getOrb().isOverloaded())
                destroy();
            else if (getOrb().isHeatMaxed())
                overload();
        }
    }

    /**
     * Ejecuta un impulso sobre el {@link Orb} y dibuja su animación
     *
     * @param velocityX Velocidad recogida por el {@link GestureHandler} para la coordenada x
     * @param velocityY Velocidad recogida por el {@link GestureHandler} para la coordenada y
     */
    public void impulse(float velocityX, float velocityY) {
        float impulseX = MathUtils.clamp(velocityX * impulse, -IMPULSE_MAX, IMPULSE_MAX);
        float impulseY = MathUtils.clamp(-velocityY * impulse, -IMPULSE_MAX, IMPULSE_MAX);

        if (!isLocked()) {
            getOrb().unfreeze();
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

    /**
     * Destruye el {@link Orb}, dibuja su animación y reinicia el juego
     */
    public void destroy() {
        if (DEBUG_INVULNERABLE) return;

        lock();
        getStats().setFailed(true);
        getGameManager().vibrate(GameManager.Length.Long);
        getGameManager().play(GameManager.Fx.Destroy);
        getHUDStage().destroy(orbDestroy, reset, orbIntro, unlock);
    }

    /**
     * Activa el incremento continuo de calor del {@link Orb} (ticking). Al empezar (entrar en una
     * zona caliente), siempre ocurre un tick.
     */
    public void enableTicking(Tick tick) {
        this.tick.amount = tick.amount;
        this.tick.period = tick.period;
        this.tick.reset();
        ticking = true;
        tick();
    }

    /**
     * Desactiva el ticking.
     */
    public void disableTicking() {
        ticking = false;
        tick.reset();
    }

    /**
     * Devuelve true si el {@link Orb} está en una zona caliente ({@link #ticking}).
     */
    private boolean isTicking() {
        return ticking;
    }

    /**
     * Ejecuta un tick de calentamiento en el {@link Orb}. Este tick puede implicar overload y
     * destroy.
     */
    private void tick() {
        getGameManager().play(GameManager.Fx.Tick);

        if (getOrb().isOverloaded()) {
            destroy();
            return;
        }

        getOrb().increaseHeat(tick.amount);

        if (getOrb().isHeatMaxed())
            overload();
    }

    /**
     * Activa la sobrecarga (overload) del Orb y actualizando la visualización del HUD.
     */
    private void overload() {
        getOrb().setOverloaded(true);
        getHUDStage().setGaugeOverload(true);
        getGameManager().play(GameManager.Fx.Warning);
    }

}
