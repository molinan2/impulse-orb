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
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.data.ScreenFlag;
import com.jmolina.orb.interfaces.Backable;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.stages.BackgroundStage;
import com.jmolina.orb.stages.MainStage;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Clase base para todas las pantallas de la aplicación. Renderiza los elementos y se ocupa de las
 * transiciones entre pantallas.
 */
public class BaseScreen extends ScreenAdapter implements Backable {

    public static final float SIZE_LARGE = 1.35f;
    public static final float SIZE_SMALL = 1 / SIZE_LARGE;
    public static final float VIEWPORT_WIDTH = Var.SCREEN_WIDTH;
    public static final float VIEWPORT_HEIGHT = Var.SCREEN_HEIGHT;

    private final Interpolation INTERPOLATION_IN = Interpolation.pow2In;
    private final Interpolation INTERPOLATION_OUT = Interpolation.pow2Out;
    protected final float TRANSITION_DURATION = 0.3f;
    protected final float MIN_DELTA_TIME = Var.FPS;

    /** Jerarquía de esta pantalla respecto de la siguiente */
    public enum Hierarchy { LOWER, HIGHER }

    /** Flujo de navegación de la pantalla */
    public enum Flow { ENTERING, LEAVING }

    private final float PERIODIC_TASK_TIME = 1f;

    private SuperManager superManager;

    /** Viewports de las stages Main y Background */
    private Viewport mainViewport, backgroundViewport;

    /** Stage principal */
    private MainStage mainStage;

    /** Stage del fondo estatico */
    private BackgroundStage backgroundStage;

    /** Jerarquia de la pantalla */
    private Hierarchy hierarchy;

    /** Multiplexor de procesador de entrada */
    private InputMultiplexer multiplexer;

    /** Claves de la pantalla anterior y esta*/
    private ScreenManager.Key previousKey, thisKey;

    /** Bandera de cambio de pantalla */
    private ScreenFlag screenFlag;

    /** Cronometro de eventos periodicos */
    private float periodicTimer;


    /**
     * Constructor
     */
    public BaseScreen(SuperManager sm) {
        superManager = sm;
        periodicTimer = 0f;
        screenFlag = new ScreenFlag();
        mainViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        backgroundViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mainStage = new MainStage(mainViewport, getBackRunnable());
        backgroundStage = new BackgroundStage(getAssetManager(), backgroundViewport);
        multiplexer = new InputMultiplexer();

        mainStage.getRoot().setOrigin(VIEWPORT_WIDTH * 0.5f, VIEWPORT_HEIGHT * 0.5f);
        mainStage.getRoot().setSize(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mainStage.getRoot().setScale(1f);
        mainStage.getRoot().setPosition(0f, 0f);

        addProcessor(mainStage);
        hierarchy = Hierarchy.LOWER;
    }

    @Override
    public void show() {
        clearRootActions();
        unsetInputProcessor();
        addMainAction(sequence(
                getTransitionAction(Flow.ENTERING, getHierarchy()),
                run(getSetAsInputProcessorRunnable())
        ));
    }

    @Override
    public void render(float delta) {
        clear();

        backgroundStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        backgroundStage.draw();
        mainStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        mainStage.draw();

        update();
        checkSwitching();
    }

    @Override
    public void resize(int width, int height) {
        mainViewport.update(width, height);
        backgroundViewport.update(width, height);
    }

    @Override
    public void hide() {
        clearRootActions();
    }

    @Override
    public void dispose() {
        mainStage.dispose();
        backgroundStage.dispose();
    }

    @Override
    public void back() {
        getGameManager().play(GameManager.Fx.Back);
        switchToScreen(this.previousKey, Hierarchy.HIGHER);
    }

    private void update() {
        periodicTimer += Gdx.graphics.getDeltaTime();
        if (periodicTimer > PERIODIC_TASK_TIME) {
            periodicTimer = 0;
            periodicTask();
        }
    }


    /**
     * Este método permite a las clases derivadas ejecutar cualquier tarea con una periodicidad de
     * {@link #PERIODIC_TASK_TIME}.
     */
    protected void periodicTask() {}

    /**
     * Fija la clave de la pantalla
     *
     * @param key Clave de pantalla
     */
    public void setThisKey(ScreenManager.Key key) {
        thisKey = key;
    }

    /**
     * Devuelve la clave de la pantalla
     */
    public ScreenManager.Key getThisKey() {
        return thisKey;
    }

    /**
     * Limpia la pantalla (antes de dibujar)
     */
    protected void clear() {
        Gdx.gl.glClearColor(0.980392157f, 0.980392157f, 0.980392157f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Devuelve el SuperManager
     */
    protected SuperManager getSuperManager() {
        return this.superManager;
    }

    /**
     * Devuelve el PrefsManager
     */
    protected PrefsManager getPrefsManager() {
        return getSuperManager().getPrefsManager();
    }

    /**
     * Devuelve el AssetManager
     */
    public AssetManager getAssetManager() {
        return getSuperManager().getAssetManager();
    }

    /**
     * Devuelve un asset determinado
     *
     * @param fileName Descriptor de fichero del asset
     * @param type Tipo de asset
     */
    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return getAssetManager().get(fileName, type);
    }

    /**
     * Devuelve el ScreenManager
     */
    public ScreenManager getScreenManager() {
        return getSuperManager().getScreenManager();
    }

    /**
     * Fija la clave de la pantalla anterior
     *
     * @param key Clave de pantalla
     */
    protected void setPreviousScreen(ScreenManager.Key key) {
        this.previousKey = key;
    }

    /**
     * Devuelve la clave de la pantalla anterior
     */
    protected ScreenManager.Key getPreviousScreen() {
        return this.previousKey;
    }

    /**
     * Fija la jerarquía de la pantalla
     *
     * @param hierarchy Jerarquia
     */
    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     * Devuelve la jerarquia de la pantalla
     */
    public Hierarchy getHierarchy() {
        return this.hierarchy;
    }

    /**
     * Configura esta pantalla como InputProcessor (solo puede haber un InputProcessor)
     */
    public void setAsInputProcessor() {
        Gdx.input.setInputProcessor(this.mainStage);
    }

    /**
     * Desconfigura el InputProcessor
     */
    public void unsetInputProcessor() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Devuelve el multiplexor de procesadores de entradas (multiplexor)
     */
    protected InputProcessor getProcessor () {
        return this.multiplexer;
    }

    /**
     * Añade al multiplexor un procesador de entrada
     *
     * @param processor Procesador de entrada
     */
    protected void addProcessor (InputProcessor processor) {
        this.multiplexer.addProcessor(processor);
    }

    /**
     * Ejecuta la animacion de salida y cambia a otra pantalla
     *
     * @param screen Name Nombre de la siguiente pantalla
     * @param hierarchy Hierarchy Jerarquía de la siguiente pantalla respecto de la actual
     */
    public void switchToScreen(final ScreenManager.Key screen, final Hierarchy hierarchy) {
        clearRootActions();
        unsetInputProcessor();

        Runnable flagSwitch = new Runnable() {
            @Override
            public void run() {
                flagSwitch(screen, hierarchy);
            }
        };

        addMainAction(sequence(
                getTransitionAction(Flow.LEAVING, hierarchy),
                run(flagSwitch)
        ));
    }

    /**
     * Señaliza un cambio de pantalla
     *
     * @param screen Pantalla destino
     * @param hierarchy Jerarquia de la pantalla destino respecto de la actual
     */
    protected void flagSwitch(ScreenManager.Key screen, Hierarchy hierarchy) {
        screenFlag.enable(screen, hierarchy);
    }

    /**
     * Ejecuta un cambio inmediato de pantalla. Este método debe llamarse al final del
     * {@link #render(float)} para evitar excepciones de acceso a memoria. El cambio sólo se
     * ejecutará si se ha marcado la {@link #screenFlag}.
     */
    protected void checkSwitching() {
        if (screenFlag.isEnabled())
            getScreenManager().switchToScreen(screenFlag.getScreen(), screenFlag.getHierarchy());
    }

    /**
     * Ejecuta la animacion de salida y termina la aplicacion
     */
    public void exitApplication() {
        clearRootActions();
        addMainAction(sequence(
                getTransitionAction(Flow.LEAVING, Hierarchy.HIGHER),
                run(getExitRunnable())
        ));
    }

    /**
     * Devuelve el ejecutable de salida de la aplicacion
     */
    private Runnable getExitRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                Gdx.app.exit();
            }
        };
    }

    /**
     * Devuelve el ejecutable de ir hacia atras
     */
    private Runnable getBackRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                back();
            }
        };
    }

    /**
     * Devuelve el ejecutable de configurar esta pantalla como InputProcessor
     */
    protected Runnable getSetAsInputProcessorRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                Gdx.input.setInputProcessor(getProcessor());
            }
        };
    }

    /**
     * Limpia las acciones de la stage principal
     */
    protected void clearRootActions() {
        mainStage.getRoot().clearActions();
    }

    /**
     * Añade una accion a la stage principal
     *
     * @param action Accion
     */
    private void addMainAction(Action action) {
        mainStage.getRoot().addAction(action);
    }

    /**
     * Devuelve una animacion de transicion
     *
     * @param flow Flow Flujo de la pantalla actual
     * @param hierarchy Hierarchy Jerarquía de la siguiente pantalla respecto de la actual
     */
    protected Action getTransitionAction(Flow flow, Hierarchy hierarchy) {
        switch (flow) {
            case ENTERING: return transitionEntering(hierarchy);
            case LEAVING: return transitionLeaving(hierarchy);
            default: return resetAction();
        }
    }

    /**
     * Devuelve una animacion de transicion de entrada
     *
     * @param hierarchy Jerarquia de la pantalla actual respecto de la anterior
     */
    private Action transitionEntering(Hierarchy hierarchy) {
        switch (hierarchy) {
            case LOWER: return fromInsideAction();
            case HIGHER: return fromOutsideAction();
            default: return appearAction();
        }
    }

    /**
     * Devuelve una animacion de transicion de salida
     *
     * @param hierarchy Jerarquia de la pantalla actual respecto de la siguiente
     */
    private Action transitionLeaving(Hierarchy hierarchy) {
        switch (hierarchy) {
            case LOWER: return toOutsideAction();
            case HIGHER: return toInsideAction();
            default: return disappearAction();
        }
    }

    /**
     * Devuelve una accion de transicion hacia afuera
     */
    private Action toOutsideAction() {
        return new SequenceAction(parallel(
                fadeOut(TRANSITION_DURATION, INTERPOLATION_IN),
                scaleTo(SIZE_LARGE, SIZE_LARGE, TRANSITION_DURATION, INTERPOLATION_IN)
        ));
    }

    /**
     * Devuelve una accion de transicion hacia adentro
     */
    private Action toInsideAction() {
        return new SequenceAction(parallel(
                fadeOut(TRANSITION_DURATION, INTERPOLATION_IN),
                scaleTo(SIZE_SMALL, SIZE_SMALL, TRANSITION_DURATION, INTERPOLATION_IN)
        ));
    }

    /**
     * Devuelve una accion de transicion desde dentro
     */
    private Action fromInsideAction() {
        return new SequenceAction(
                alpha(0f),
                scaleTo(SIZE_SMALL, SIZE_SMALL),
                parallel(
                        fadeIn(TRANSITION_DURATION, INTERPOLATION_OUT),
                        scaleTo(1f, 1f, TRANSITION_DURATION, INTERPOLATION_OUT)
                )
        );
    }

    /**
     * Devuelve una accion de transicion desde fuera
     */
    private Action fromOutsideAction() {
        return new SequenceAction(
                alpha(0f),
                scaleTo(SIZE_LARGE, SIZE_LARGE),
                parallel(
                        fadeIn(TRANSITION_DURATION, INTERPOLATION_OUT),
                        scaleTo(1f, 1f, TRANSITION_DURATION, INTERPOLATION_OUT)
                )
        );
    }

    /**
     * Devuelve una accion de aparicion (por defecto)
     */
    private Action appearAction() {
        return new SequenceAction(
                alpha(0f),
                scaleTo(SIZE_LARGE, SIZE_LARGE),
                fadeIn(TRANSITION_DURATION, INTERPOLATION_IN)
        );
    }

    /**
     * Devuelve una accion de desaparicion (por defecto)
     */
    private Action disappearAction() {
        return new SequenceAction(
                alpha(1f),
                scaleTo(1f, 1f),
                fadeOut(TRANSITION_DURATION, INTERPOLATION_IN)
        );
    }

    /**
     * Devuelve una accion de reseteo de escala y canal alpha
     */
    private Action resetAction() {
        return new SequenceAction(
                alpha(1f),
                scaleTo(1f, 1f)
        );
    }

    /**
     * Añade un Actor a la Stage principal
     */
    public void addMainActor(Actor actor) {
        mainStage.addActor(actor);
    }

    /**
     * Devuelve la stage principal
     */
    protected Stage getMainStage() {
        return mainStage;
    }

    /**
     * Devuelve la stage de fondo estatico
     */
    protected Stage getBackgroundStage() {
        return backgroundStage;
    }

    /**
     * Devuelve el GameManager
     */
    protected GameManager getGameManager() {
        return getSuperManager().getGameManager();
    }

}
