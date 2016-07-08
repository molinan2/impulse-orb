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
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.interfaces.Backable;
import com.jmolina.orb.runnables.UIRunnable;
import com.jmolina.orb.stages.BackgroundStage;
import com.jmolina.orb.stages.BaseStage;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class BaseScreen extends ScreenAdapter implements Backable {

    public final static float VIEWPORT_HEIGHT = 1184.0f;
    public final static float VIEWPORT_WIDTH = 768.0f;
    public final float MIN_DELTA_TIME = 1/60f;

    /** Jerarquía de esta pantalla respecto de la siguiente */
    public enum Hierarchy { LOWER, HIGHER }

    /** Flujo de navegación de la pantalla */
    public enum Flow { ENTERING, LEAVING }

    private SuperManager superManager;
    private Viewport mainViewport, backgroundViewport;
    private BaseStage mainStage;
    private BackgroundStage backgroundStage;
    private Hierarchy hierarchy;
    private SnapshotArray<Actor> actors;
    private InputMultiplexer multiplexer;
    private ScreenManager.Key previousKey;
    private ScreenManager.Key key;


    /**
     * Constructor
     */
    public BaseScreen(SuperManager sm, ScreenManager.Key key) {
        superManager = sm;
        this.key = key;

        actors = new SnapshotArray<Actor>();
        mainViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        backgroundViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mainStage = new BaseStage(mainViewport, UIRunnable.backOperation(this));
        backgroundStage = new BackgroundStage(getAssetManager(), backgroundViewport);
        multiplexer = new InputMultiplexer();

        mainStage.getRoot().setOrigin(VIEWPORT_WIDTH * 0.5f, VIEWPORT_HEIGHT * 0.5f);
        mainStage.getRoot().setScale(1f, 1f);
        mainStage.getRoot().setSize(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
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
        clearColor();

        // backgroundStage.getViewport().apply();
        backgroundStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        backgroundStage.draw();
        // mainStage.getViewport().apply();
        mainStage.act(Math.min(Gdx.graphics.getDeltaTime(), MIN_DELTA_TIME));
        mainStage.draw();
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
        switchToScreen(this.previousKey, Hierarchy.HIGHER);
    }


    /**
     * Class methods
     */

    public ScreenManager.Key getKey() {
        return this.key;
    }

    protected void clearColor() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private SuperManager getSuperManager() {
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

    protected void setPreviousKey(ScreenManager.Key key) {
        this.previousKey = key;
    }

    protected ScreenManager.Key getPreviousKey() {
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
     * @param key Name Nombre de la siguiente pantalla
     * @param hierarchy Hierarchy Jerarquía de la siguiente pantalla respecto de la actual
     */
    public void switchToScreen(final ScreenManager.Key key, final Hierarchy hierarchy) {
        clearRootActions();
        unsetInputProcessor();
        addMainAction(sequence(
                transition(Flow.LEAVING, hierarchy),
                run(UIRunnable.setScreen(getScreenManager(), key, hierarchy))
        ));
    }

    /**
     * Ejecuta la animacion de salida y termina la aplicacion
     *
     * TODO
     * Las pantallas no deberian salir por si mismas, sino solicitar a un controller salir de
     * la aplicacion, y el controller debe devolver una respuesta. Si es positiva, la pantalla
     * anima su salida; si no, deberia mostrar algun feedback. Pero no va a ser asi...
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
        Action action;

        switch (flow) {
            case ENTERING: action = transitionEntering(hierarchy); break;
            case LEAVING: action = transitionLeaving(hierarchy); break;
            default: action = UIAction.reset();
        }

        return action;
    }

    private Action transitionEntering(Hierarchy hierarchy) {
        Action action;

        switch (hierarchy) {
            case LOWER: action = UIAction.fromInside(); break;
            case HIGHER: action = UIAction.fromOutside(); break;
            default: action = UIAction.appear();
        }

        return action;
    }

    private Action transitionLeaving(Hierarchy hierarchy) {
        Action action;

        switch (hierarchy) {
            case LOWER: action = UIAction.toOutside(); break;
            case HIGHER: action = UIAction.toInside(); break;
            default: action = UIAction.disappear();
        }

        return action;
    }

    /**
     * Añade un Actor a la Stage principal
     */
    public void addMainActor(Actor actor) {
        mainStage.addActor(actor);
        registerActor(actor);
    }

    /**
     * Registra un actor para poder realizar operaciones automaticas sobre el
     * @param actor Actor
     */
    protected void registerActor(Actor actor) {
        this.actors.add(actor);
    }

    protected Stage getMainStage() {
        return mainStage;
    }

}
