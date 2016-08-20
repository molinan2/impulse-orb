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
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.interfaces.Backable;
import com.jmolina.orb.stages.BackgroundStage;
import com.jmolina.orb.stages.MainStage;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

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
    private Viewport mainViewport, backgroundViewport;
    private MainStage mainStage;
    private BackgroundStage backgroundStage;
    private Hierarchy hierarchy;
    private InputMultiplexer multiplexer;
    private ScreenManager.Key previousKey, thisKey;
    private ScreenFlag screenFlag;
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


    /**
     * Screen methods
     */

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
     * Class methods
     */

    public void setThisKey(ScreenManager.Key key) {
        thisKey = key;
    }

    public ScreenManager.Key getThisKey() {
        return thisKey;
    }

    protected void clear() {
        Gdx.gl.glClearColor(0.980392157f, 0.980392157f, 0.980392157f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected SuperManager getSuperManager() {
        return this.superManager;
    }

    protected PrefsManager getPrefsManager() {
        return getSuperManager().getPrefsManager();
    }

    public AssetManager getAssetManager() {
        return getSuperManager().getAssetManager();
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return getAssetManager().get(fileName, type);
    }

    public ScreenManager getScreenManager() {
        return getSuperManager().getScreenManager();
    }

    protected void setPreviousScreen(ScreenManager.Key key) {
        this.previousKey = key;
    }

    protected ScreenManager.Key getPreviousScreen() {
        return this.previousKey;
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Hierarchy getHierarchy() {
        return this.hierarchy;
    }

    public void setAsInputProcessor() {
        Gdx.input.setInputProcessor(this.mainStage);
    }

    public void unsetInputProcessor() {
        Gdx.input.setInputProcessor(null);
    }

    protected InputProcessor getProcessor () {
        return this.multiplexer;
    }

    protected void addProcessor (InputProcessor processor) {
        this.multiplexer.addProcessor(processor);
    }

    /**
     * Ejecuta la animacion de salida y cambia a otra pantalla
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

    private Runnable getExitRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                Gdx.app.exit();
            }
        };
    }

    private Runnable getBackRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                back();
            }
        };
    }

    protected Runnable getSetAsInputProcessorRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                Gdx.input.setInputProcessor(getProcessor());
            }
        };
    }

    protected void clearRootActions() {
        mainStage.getRoot().clearActions();
    }

    private void addMainAction(Action action) {
        mainStage.getRoot().addAction(action);
    }

    /**
     * Ejecuta una animacion de transicion
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

    private Action transitionEntering(Hierarchy hierarchy) {
        switch (hierarchy) {
            case LOWER: return fromInsideAction();
            case HIGHER: return fromOutsideAction();
            default: return appearAction();
        }
    }

    private Action transitionLeaving(Hierarchy hierarchy) {
        switch (hierarchy) {
            case LOWER: return toOutsideAction();
            case HIGHER: return toInsideAction();
            default: return disappearAction();
        }
    }

    private Action toOutsideAction() {
        return new SequenceAction(parallel(
                fadeOut(TRANSITION_DURATION, INTERPOLATION_IN),
                scaleTo(SIZE_LARGE, SIZE_LARGE, TRANSITION_DURATION, INTERPOLATION_IN)
        ));
    }

    private Action toInsideAction() {
        return new SequenceAction(parallel(
                fadeOut(TRANSITION_DURATION, INTERPOLATION_IN),
                scaleTo(SIZE_SMALL, SIZE_SMALL, TRANSITION_DURATION, INTERPOLATION_IN)
        ));
    }

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

    private Action appearAction() {
        return new SequenceAction(
                alpha(0f),
                scaleTo(SIZE_LARGE, SIZE_LARGE),
                fadeIn(TRANSITION_DURATION, INTERPOLATION_IN)
        );
    }

    private Action disappearAction() {
        return new SequenceAction(
                alpha(1f),
                scaleTo(1f, 1f),
                fadeOut(TRANSITION_DURATION, INTERPOLATION_IN)
        );
    }

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

    protected Stage getMainStage() {
        return mainStage;
    }

    protected Stage getBackgroundStage() {
        return backgroundStage;
    }

    protected GameManager getGameManager() {
        return getSuperManager().getGameManager();
    }

}
