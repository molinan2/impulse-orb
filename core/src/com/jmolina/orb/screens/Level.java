/*
 * IMPULSE ORB
 * Copyright (C) 2016 Juan M. Molina
 *
 * This file is part of the IMPULSE ORB source code.
 *
 * IMPULSE ORB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IMPULSE ORB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.data.GameStats;
import com.jmolina.orb.data.Tick;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.interfaces.LevelManager;
import com.jmolina.orb.interfaces.PlayServices;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.listeners.GestureHandler;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.managers.SituationManager;
import com.jmolina.orb.managers.WorldManager;
import com.jmolina.orb.situations.Situation;
import com.jmolina.orb.stages.GestureStage;
import com.jmolina.orb.stages.HUDStage;
import com.jmolina.orb.stages.ParallaxStage;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.debug.DebugTime;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


/**
 * Clase base para los niveles del juego. Permite su construcción a partir de {@link Situation}s.
 * Renderiza todos los elementos del juego y los sincroniza con el mundo físico. Controla los
 * eventos del juego.
 */
public class Level extends BaseScreen implements LevelManager {

    private final boolean DEBUG_WORLD = Var.DEBUG_RENDERER;
    private final boolean DEBUG_TIME = Var.DEBUG_FRAME_TIME;
    private final boolean DEBUG_INVULNERABLE = Var.DEBUG_INVULNERABILITY;
    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private DebugTime debugTime = new DebugTime(getAssetManager());

    private final float GESTURE_HALF_TAP_SQUARE_SIZE = 13.0f;
    private final float GESTURE_TAP_COUNT_INTERVAL = 0.4f;
    private final float GESTURE_LONG_PRESS_DURATION = 1.1f;
    private final float GESTURE_MAX_FLING_DELAY = 0.1f;
    private final float IMPULSE_FACTOR = 0.6f;
    private final float IMPULSE_MAX = 50f;

    private Tick tick;
    private float pixelsPerMeter, impulse;
    private boolean locked, ticking, achievedRobocop, achievedItsOver9000, achievedHyperdrive;
    private Viewport worldViewport, gestureViewport, hudViewport, parallaxViewport;
    private GestureStage gestureStage;
    private ParallaxStage parallaxStage;
    private HUDStage hudStage;
    private Orb orb;
    private Vector2 lastOrbPosition;
    private GameStats stats;
    private ScreenManager.Key successScreen = ScreenManager.Key.LEVEL_SELECT;
    private Runnable orbIntro, orbDestroy, reset, unlock, toSuccess;
    private SituationManager situationManager;
    private WorldManager worldManager;

    /**
     * Constructor
     *
     * @param sm SuperManager
     */
    public Level(SuperManager sm) {
        super(sm);

        achievedRobocop = false;
        achievedItsOver9000 = false;
        achievedHyperdrive = false;

        tick = new Tick();
        pixelsPerMeter = getGameManager().getPixelsPerMeter();
        impulse = IMPULSE_FACTOR / getPixelsPerMeter();
        lastOrbPosition = new Vector2();
        stats = new GameStats();

        float worldWidth = VIEWPORT_WIDTH / getPixelsPerMeter();
        float worldHeight = VIEWPORT_HEIGHT / getPixelsPerMeter();
        worldViewport = new FitViewport(worldWidth, worldHeight);
        gestureViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        parallaxViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        hudViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        hudStage = new HUDStage(this, getAssetManager(), hudViewport);
        gestureStage = new GestureStage(getAssetManager(), gestureViewport, getPixelsPerMeter());
        parallaxStage = new ParallaxStage(getAssetManager(), parallaxViewport, getPixelsPerMeter());

        worldManager = new WorldManager();
        setOrb(new Orb(getAssetManager(), worldManager.getWorld(), getPixelsPerMeter()));
        worldManager.bindContactHandler(this, getOrb());

        GestureDetector gestureDetector = new GestureDetector(
                GESTURE_HALF_TAP_SQUARE_SIZE,
                GESTURE_TAP_COUNT_INTERVAL,
                GESTURE_LONG_PRESS_DURATION,
                GESTURE_MAX_FLING_DELAY,
                new GestureHandler(this)
        );

        situationManager = new SituationManager(
                getAssetManager(),
                worldManager.getWorld(),
                getOrb(),
                getPixelsPerMeter(),
                getMainStage(),
                worldViewport
        );

        addProcessor(hudStage);
        addProcessor(gestureStage);
        addProcessor(gestureDetector);
        createRunnables();
        disableTicking();
        lock();
    }

    /**
     * Crea los runables que se utilizarán como callbacks
     */
    private void createRunnables() {
        orbIntro = new Runnable() {
            @Override
            public void run() {
                getOrb().applyIntroAction();
                getGameManager().play(GameManager.Fx.Init);
            }
        };

        reset = new Runnable() {
            @Override
            public void run() {
                getOrb().reset();
                getHUDStage().reset();
                stats.newTry();
                lastOrbPosition = getOrb().getStartPosition();
                situationManager.removeAll();
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
        updateSituations();
        syncActors();
        act(delta);
        syncBodies();
        updateForces();
        stepPhysics(delta);
        followCamera();
        syncActors();
        updateWidgets();
        draw();
        checkAchievements();
        checkSwitching();

        if (DEBUG_TIME) debugTime.end();
    }

    @Override
    protected void clear() {
        Gdx.gl.glClearColor(0.19921875f, 0.19921875f, 0.19921875f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
        situationManager.dispose();
        getHUDStage().dispose();
        getGestureStage().dispose();
        getParallaxStage().dispose();
        worldManager.dispose();
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
                Actions.addAction(fadeIn(TRANSITION_DURATION, Interpolation.pow2), getBackgroundStage().getRoot()),
                delay(TRANSITION_DURATION),
                getTransitionAction(Flow.LEAVING, hierarchy),
                run(flagSwitch)
        ));
    }

    private void stepPhysics(float delta) {
        if (isLocked()) return;

        worldManager.step(delta);
    }

    /**
     * Devuelve el valor actual de {@link #pixelsPerMeter}
     *
     * @return {@link #pixelsPerMeter}
     */
    private float getPixelsPerMeter() {
        return pixelsPerMeter;
    }

    /**
     * Devuelve la Stage del fondo de scroll parallax
     *
     * @return {@link #parallaxStage}
     */
    private ParallaxStage getParallaxStage() {
        return parallaxStage;
    }

    /**
     * Devuelve la Stage de dibujado de gestos
     *
     * @return {@link #gestureStage}
     */
    private GestureStage getGestureStage() {
        return gestureStage;
    }

    /**
     * Devuelve la Stage del HUD
     *
     * @return {@link #hudStage}
     */
    private HUDStage getHUDStage() {
        return hudStage;
    }

    /**
     * Devuelve las estadísticas del juego en curso
     *
     * @return {@link #stats}
     */
    private GameStats getStats() {
        return stats;
    }

    /**
     * Guarda la pantalla de éxito correspondiente a este nivel
     */
    protected void setSuccessScreen(ScreenManager.Key key) {
        successScreen = key;
    }

    /**
     * Obtiene la pantalla de éxito correspondiente a este nivel
     *
     * @return {@link #successScreen}
     */
    private ScreenManager.Key getSuccessScreen() {
        return successScreen;
    }

    /**
     * Setea el {@link #orb} a la Stage principal
     *
     * @param orb {@link Orb}
     */
    private void setOrb(Orb orb) {
        addMainActor(orb.getActor());
        orb.syncActor(worldViewport);
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
    protected void setOrbStartPosition (float x, float y) {
        getOrb().setPosition(x, y);
        getOrb().setStartPosition(x, y);
        lastOrbPosition.set(x, y);
    }

    /**
     * Añade una clase {@link Situation} al array de situaciones del nivel.
     *
     * @param clazz Una clase de tipo {@link Situation}
     */
    protected void addSituation(Class clazz) {
        situationManager.addSituation(clazz);
    }



    /**
     * Sincroniza la posición y rotación de los cuerpos con las de sus actores.
     * No es necesaria en el caso de elementos no móviles
     */
    private void syncBodies() {
        for (Situation situation : situationManager.getVisible()) {
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
    private void syncActors() {
        for (Situation situation : situationManager.getVisible()) {
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
        if (DEBUG_WORLD) debugRenderer.render(worldManager.getWorld(), worldViewport.getCamera().combined);
        getHUDStage().draw();
    }

    /**
     * Render update
     * Actualiza las situaciones visibles. No se puede pausar la actualizacion de situaciones visibles
     * cuando se bloquee el juego, ya que eso impide que se dibujen las situaciones iniciales al empezar.
     */
    private void updateSituations() {
        situationManager.update();
    }

    /**
     * Render update
     * Computa las fuerzas sobre el orbe, si el juego no está bloqueado
     */
    private void updateForces() {
        if (isLocked()) return;

        computeMagneticFoces();
    }

    /**
     * Render update
     * Comprueba y actualiza datos y estados, si el juego no está bloqueado
     */
    private void updateWidgets() {
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
     * Calcula las fuerzas de atracción y repulsión activas sobre el orbe y las aplica.
     */
    private void computeMagneticFoces() {
        Vector2 force = new Vector2(0, 0);

        for (Situation situation : situationManager.getVisible()) {
            for (Element element : situation.getElements()) {
                if (element instanceof Magnetic) {
                    Vector2 partial = ((Magnetic)element).getForce(getOrb().getPosition());
                    force.add(partial);
                }
            }
        }

        getOrb().getBody().applyLinearImpulse(force, getOrb().getPosition(), true);
    }

    /**
     * Comprobación y desbloqueo de los logros que se obtienen en mitad del gameplay.
     */
    private void checkAchievements() {
        checkRobocopAchievement();
        checkOver9000Achievement();
        checkHyperdriveAchievement();
    }

    private void checkRobocopAchievement() {
        if (achievedRobocop) return;

        if (getStats().getCurrentTime() > GameManager.ACHIEVEMENT_ROBOCOP_TIME) {
            if (getThisKey() != ScreenManager.Key.LEVEL_1) {
                achievedRobocop = true;
                getGameManager().unlockAchievement(PlayServices.Achievement.Robocop);
            }
        }
    }

    private void checkOver9000Achievement() {
        if (achievedItsOver9000) return;

        if (getStats().getCurrentDistance() > GameManager.ACHIEVEMENT_OVER9000_DISTANCE) {
            achievedItsOver9000 = true;
            getGameManager().unlockAchievement(PlayServices.Achievement.ItsOver9000);
        }
    }

    private void checkHyperdriveAchievement() {
        if (achievedHyperdrive) return;

        if (getOrb().getBody().getLinearVelocity().len2() > GameManager.ACHIEVEMENT_HYPERDRIVE_SPEED2) {
            achievedHyperdrive = true;
            getGameManager().unlockAchievement(PlayServices.Achievement.Hyperdrive);
        }
    }

    /**
     * Desbloquea el logro de nivel correspondiente a este nivel
     */
    public void unlockLevelAchievement() {
        switch (getThisKey()) {
            case LEVEL_1: getGameManager().unlockAchievement(PlayServices.Achievement.KnowHow); break;
            case LEVEL_2: getGameManager().unlockAchievement(PlayServices.Achievement.TheRealDeal); break;
            case LEVEL_3: getGameManager().unlockAchievement(PlayServices.Achievement.BecomingAnExpert); break;
            case LEVEL_4: getGameManager().unlockAchievement(PlayServices.Achievement.AHeroWasBorn); break;
            case LEVEL_5: getGameManager().unlockAchievement(PlayServices.Achievement.OneAboveAll); break;
            default:
        }
    }

    @Override
    public void firstGame() {
        stats.newTry();
        getBackgroundStage().addAction(alpha(1));
        getOrb().getActor().addAction(alpha(0));
        getHUDStage().init(
                getTransitionAction(Flow.ENTERING, getHierarchy()),
                getBackgroundStage(),
                orbIntro,
                getSetAsInputProcessorRunnable(),
                unlock
        );
    }

    @Override
    public void pauseGame() {
        if (!isLocked()) {
            lock();
            getHUDStage().pause();
            getGameManager().play(GameManager.Fx.Back);
        }
    }

    @Override
    public void resumeGame() {
        if (isLocked()) {
            getHUDStage().resume(unlock);
            getGameManager().play(GameManager.Fx.Button);
        }
    }

    @Override
    public void restartGame() {
        getHUDStage().restart(reset, orbIntro, unlock);
        getGameManager().play(GameManager.Fx.Button);
    }

    @Override
    public void leaveGame() {
        unsetInputProcessor();
        getPrefsManager().saveStats(stats, getThisKey());
        getGameManager().play(GameManager.Fx.Back);
        switchToScreen(getPreviousScreen(), Hierarchy.HIGHER);
    }

    @Override
    public void successGame() {
        lock();
        getStats().setSuccessfull(true);

        if (getStats().getCurrentAttempt() != null) {
            int rank = getPrefsManager().saveStats(getStats(), getThisKey());
            getGameManager().cache(getStats().getCurrentAttempt(), rank);
            getGameManager().submitTime(getThisKey(), getStats().getCurrentTime());
        }

        unsetInputProcessor();
        getGameManager().play(GameManager.Fx.Exit);
        unlockLevelAchievement();
        getOrb().applyOutroAction(toSuccess);
    }

    @Override
    public void lock() {
        locked = true;
    }

    @Override
    public void unlock() {
        locked = false;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public void collide(boolean wall) {
        getGameManager().vibrate(GameManager.Length.Short);

        if (wall)
            getGameManager().play(GameManager.Fx.WallCollision);
        else
            getGameManager().play(GameManager.Fx.ElementCollision);
    }

    @Override
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

    @Override
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

    @Override
    public void destroy() {
        if (DEBUG_INVULNERABLE) return;

        lock();
        getStats().setFailed(true);

        if (getStats().getCurrentTime() < GameManager.ACHIEVEMENT_KENNY_TIME)
            getGameManager().unlockAchievement(PlayServices.Achievement.Kenny);

        getGameManager().vibrate(GameManager.Length.Long);
        getGameManager().play(GameManager.Fx.Destroy);
        getHUDStage().destroy(orbDestroy, reset, orbIntro, unlock);
    }

    @Override
    public void enableTicking(Tick tick) {
        this.tick.amount = tick.amount;
        this.tick.period = tick.period;
        this.tick.reset();
        ticking = true;
        tick();
    }

    @Override
    public void disableTicking() {
        ticking = false;
        tick.reset();
    }

    @Override
    public boolean isTicking() {
        return ticking;
    }

    @Override
    public void tick() {
        getGameManager().play(GameManager.Fx.Tick);

        if (getOrb().isOverloaded()) {
            destroy();
            return;
        }

        getOrb().increaseHeat(tick.amount);

        if (getOrb().isHeatMaxed())
            overload();
    }

    @Override
    public void overload() {
        getOrb().setOverloaded(true);
        getHUDStage().setGaugeOverload(true);
        getGameManager().play(GameManager.Fx.Warning);
    }

}
