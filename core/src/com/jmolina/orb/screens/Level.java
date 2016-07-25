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
import com.jmolina.orb.data.ScreenFlag;
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


/**
 * {@inheritDoc}
 * Clase base para los niveles del juego. Permite su construcción a partir de {@link Situation}s.
 * Renderiza todos los elementos del juego y los sincroniza con el mundo físico. Controla los
 * eventos del juego.
 */
public class Level extends BaseScreen {

    public static final float INTRO_SEQUENCE_TIME = 1f;
    public static final float EXIT_SEQUENCE_TIME = 1.2f;
    public static final float BACKGROUND_FADE_TIME = 0.5f;

    private final float GESTURE_HALF_TAP_SQUARE_SIZE = 10.0f;
    private final float GESTURE_TAP_COUNT_INTERVAL = 0.4f;
    private final float GESTURE_LONG_PRESS_DURATION = 1.1f;
    private final float GESTURE_MAX_FLING_DELAY = 0.1f;
    private final float GESTURE_MAX_FLING_IMPULSE = 40f;
    private final Vector2 WORLD_GRAVITY = new Vector2(0, -20f);
    private final float WORLD_TIME_STEP = Var.FPS;
    private final int WORLD_STEP_MULTIPLIER = 2;
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
    private Vector2 orbStartPosition, lastOrbPosition;
    private GameStats stats;
    private ScreenManager.Key successScreen = ScreenManager.Key.LEVEL_SELECT;
    private Runnable orbIntro, orbDestroy, reset, unlock, toSuccess;
    private ScreenFlag screenFlag;

    // private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();


    /**
     * Constructor
     *
     * @param sm SuperManager
     * @param key Clave correspondiente a la pantalla actual
     */
    public Level(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        screenFlag = new ScreenFlag();
        pixelsPerMeter = getGameManager().getPixelsPerMeter();
        impulseFactor = 1 / getPixelsPerMeter();
        orbStartPosition = new Vector2();
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

        // Los primeros Processor reciben antes los eventos
        addProcessor(hudStage);
        addProcessor(gestureStage);
        addProcessor(gestureDetector);

        createRunnables();
        lock();
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
        clear();
        syncActors();
        act();
        syncBodies();
        step();
        followCamera();
        syncActors();
        update();
        draw();

        checkScreenSwitch();
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
        getGameManager().play(GameManager.Track.Game);
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
                delay(Math.max(BACKGROUND_FADE_TIME, INTRO_SEQUENCE_TIME)),
                run(UIRunnable.setInputProcessor(getProcessor())),
                run(unlock)
        ));
    }

    /**
     * {@inheritDoc}
     * Reproduce la música de pantallas de menú
     *
     * TODO: Se ejecutan 2 plays seguidos cuando se cambia a SUCCESS SCREEN
     */
    @Override
    public void hide() {
        super.hide();
        getGameManager().play(GameManager.Track.Menu);
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
     * Cambia a la pantalla {@link #key}
     *
     * @param screen Identificador de la siguiente pantalla
     * @param hierarchy Jerarquía de la siguiente pantalla respecto de la actual
     */
    @Override
    public void switchToScreen(final ScreenManager.Key screen, final Hierarchy hierarchy) {

        Runnable flag = new Runnable() {
            @Override
            public void run() {
                screenFlag.enable(screen, hierarchy);
            }
        };

        getHUDStage().addAction(sequence(
                Actions.addAction(fadeIn(UIAction.DURATION, Interpolation.pow2), getBackgroundStage().getRoot()),
                delay(UIAction.DURATION),
                transition(Flow.LEAVING, hierarchy),
                run(flag)
        ));
    }

    /**
     * Ejecuta un cambio inmediato de pantalla. Este método debe llamarse al final del
     * {@link #render(float)} para evitar excepciones de acceso a memoria. El cambio sólo se
     * ejecutará si se ha marcado la {@link #screenFlag}.
     */
    private void checkScreenSwitch() {
        if (screenFlag.isEnabled())
            getScreenManager().switchToScreen(screenFlag.getScreen(), screenFlag.getHierarchy());
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
        this.successScreen = key;
    }

    /**
     * Obtiene la pantalla de éxito correspondiente a este nivel
     *
     * @return {@link #successScreen}
     */
    public ScreenManager.Key getSuccessScreen() {
        return this.successScreen;
    }

    /**
     * Setea el {@link #orb} a la Stage principal
     *
     * @param orb {@link Orb}
     */
    public void setOrb(Orb orb) {
        addMainActor(orb.getActor());
        orb.syncActor(worldViewport);
        orb.getActor().setZIndex(INFINITE_Z_INDEX);
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
     * Añade una situación al nivel actual y añade todos sus actores. El {@link Orb} siempre queda
     * por encima.
     *
     * @param situation {@link Situation}
     */
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

    /**
     * Devuelve el array de situaciones del nivel actual
     *
     * @return SnapshotArray de {@link Situation}s
     */
    public SnapshotArray<Situation> getSituations () {
        return situations;
    }


    /**
     * Avanza la simulación
     *
     * Se calcularán {@link #WORLD_STEP_MULTIPLIER} pasos por cada frame
     */
    public void step() {
        for (int i = 0; i< WORLD_STEP_MULTIPLIER; i++) {
            if (!isLocked()) {
                world.step(
                        WORLD_TIME_STEP / (float) WORLD_STEP_MULTIPLIER,
                        WORLD_VELOCITY_INTERACTIONS,
                        WORLD_POSITION_INTERACTIONS
                );
            }
        }
    }

    /**
     * Sincroniza la posición y rotación de los cuerpos con las de sus actores
     */
    private void syncBodies() {
        for (Situation situation : situations) {
            for (Element element : situation.getElements()) {
                element.syncBody(worldViewport);
            }
        }

        orb.syncBody(worldViewport);
    }

    /**
     * Sincroniza la posición y rotación de los actores con las de sus cuerpos
     */
    public void syncActors() {
        for (Situation situation : situations) {
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
    private void act() {
        getMainStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getGestureStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getBackgroundStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        getHUDStage().act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
    }

    /**
     * Dibuja todos los actores
     */
    private void draw() {
        getParallaxStage().draw(worldViewport.getCamera().position.x, worldViewport.getCamera().position.y);
        getMainStage().draw();
        getGestureStage().draw();
        getBackgroundStage().draw();
        // debugRenderer.render(world, worldViewport.getCamera().combined);
        getHUDStage().draw();
    }

    /**
     * Render update
     * Comprueba y actualiza datos y estados, si el juego no está bloqueado
     */
    private void update() {
        if (isLocked()) return;

        updateHeat();
        updateFreeze();
        updateTimer();
        updateStats();
    }

    /**
     * Actualiza el calor y su indicador
     */
    private void updateHeat() {
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
        getPrefsManager().saveGameStats(stats, getKey());
        getGameManager().play(GameManager.Fx.Back);
        getGameManager().play(GameManager.Track.Menu);
        switchToScreen(getPreviousScreen(), Hierarchy.HIGHER);
    }

    /**
     * Completa el juego, guardando las estadísticas y lanzando la pantalla de Success
     */
    public void successGame() {
        lock();
        getStats().setSuccessfull(true);
        getPrefsManager().saveGameStats(stats, getKey());
        getGameManager().cacheAttempt(stats.getLastAttempt());
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
     */
    public void collide() {
        getGameManager().play(GameManager.Fx.Collision);
        getGameManager().vibrate(GameManager.Length.Short);
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

            if (getOrb().isOverloaded()) {
                destroy();
            }
            else if (getOrb().isHeatMaxed()) {
                getOrb().setOverloaded(true);
                getHUDStage().setGaugeOverload(true);
            }
        }
    }

    /**
     * Ejecuta un impulso sobre el {@link Orb} y dibuja su animación
     *
     * @param velocityX Velocidad recogida por el {@link GestureHandler} para la coordenada x
     * @param velocityY Velocidad recogida por el {@link GestureHandler} para la coordenada y
     */
    public void impulse(float velocityX, float velocityY) {
        float impulseX = MathUtils.clamp(velocityX * impulseFactor, -GESTURE_MAX_FLING_IMPULSE, GESTURE_MAX_FLING_IMPULSE);
        float impulseY = MathUtils.clamp(-velocityY * impulseFactor, -GESTURE_MAX_FLING_IMPULSE, GESTURE_MAX_FLING_IMPULSE);

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
        lock();
        getStats().setFailed(true);
        getGameManager().vibrate(GameManager.Length.Long);
        getGameManager().play(GameManager.Fx.Destroy);
        getHUDStage().destroy(orbDestroy, reset, orbIntro, unlock);
    }

}
