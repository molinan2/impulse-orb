package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.actions.UIAction;
import com.jmolina.orb.data.ScreenFlag;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.interfaces.Backable;
import com.jmolina.orb.runnables.UIRunnable;
import com.jmolina.orb.stages.BackgroundStage;
import com.jmolina.orb.stages.BackableStage;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Clase base para todas las pantallas de la aplicación. Renderiza los elementos y se ocupa de las
 * transiciones entre pantallas.
 */
public class BaseScreen extends ScreenAdapter implements Backable {

    public final static float VIEWPORT_WIDTH = Var.SCREEN_WIDTH;
    public final static float VIEWPORT_HEIGHT = Var.SCREEN_HEIGHT;
    public final float MIN_DELTA_TIME = Var.FPS;

    /** Jerarquía de esta pantalla respecto de la siguiente */
    public enum Hierarchy { LOWER, HIGHER }

    /** Flujo de navegación de la pantalla */
    public enum Flow { ENTERING, LEAVING }

    private SuperManager superManager;
    private Viewport mainViewport, backgroundViewport;
    private BackableStage mainStage;
    private BackgroundStage backgroundStage;
    private Hierarchy hierarchy;
    private SnapshotArray<Actor> actors;
    private InputMultiplexer multiplexer;
    private ScreenManager.Key previousKey, thisKey;
    private ScreenFlag screenFlag;


    /**
     * Constructor
     */
    public BaseScreen(SuperManager sm) {
        superManager = sm;
        screenFlag = new ScreenFlag();
        actors = new SnapshotArray<Actor>();
        mainViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        backgroundViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mainStage = new BackableStage(mainViewport, UIRunnable.backOperation(this));
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
                transition(Flow.ENTERING, getHierarchy()),
                run(UIRunnable.setInputProcessor(getProcessor()))
        ));
    }

    @Override
    public void render(float delta) {
        clear();

        backgroundStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        backgroundStage.draw();
        mainStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        mainStage.draw();

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


    /**
     * Backable methods
     */

    @Override
    public void back() {
        getGameManager().play(GameManager.Fx.Back);
        switchToScreen(this.previousKey, Hierarchy.HIGHER);
    }


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
        Gdx.gl.glClearColor(0.19921875f, 0.19921875f, 0.19921875f, 1.0f);
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
                transition(Flow.LEAVING, hierarchy),
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
                transition(Flow.LEAVING, Hierarchy.HIGHER),
                run(UIRunnable.exit())
        ));
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
    protected Action transition(Flow flow, Hierarchy hierarchy) {
        switch (flow) {
            case ENTERING: return transitionEntering(hierarchy);
            case LEAVING: return transitionLeaving(hierarchy);
            default: return UIAction.reset();
        }
    }

    private Action transitionEntering(Hierarchy hierarchy) {
        switch (hierarchy) {
            case LOWER: return UIAction.fromInside();
            case HIGHER: return UIAction.fromOutside();
            default: return UIAction.appear();
        }
    }

    private Action transitionLeaving(Hierarchy hierarchy) {
        switch (hierarchy) {
            case LOWER: return UIAction.toOutside();
            case HIGHER: return UIAction.toInside();
            default: return UIAction.disappear();
        }
    }

    /**
     * Añade un Actor a la Stage principal
     */
    public void addMainActor(Actor actor) {
        mainStage.addActor(actor);
        registerActor(actor);
    }

    /**
     * Registra un actor para poder realizarle operaciones automáticas
     * @param actor Actor
     */
    protected void registerActor(Actor actor) {
        this.actors.add(actor);
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
