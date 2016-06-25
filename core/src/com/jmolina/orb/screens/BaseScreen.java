package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
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
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.interfaces.Backable;
import com.jmolina.orb.runnables.UIRunnable;
import com.jmolina.orb.stages.BackStage;
import com.jmolina.orb.widgets.Background;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class BaseScreen implements Screen, Backable {

    protected final static float VIEWPORT_HEIGHT = 1184.0f;
    protected final static float VIEWPORT_WIDTH = 768.0f;

    /** Jerarquía de esta pantalla respecto de la siguiente */
    public enum Hierarchy {
        LOWER, HIGHER
    }

    /** Flujo de navegación de la pantalla */
    public enum Flow {
        ENTERING, LEAVING
    }

    private SuperManager superManager;
    private Background background;
    private Viewport mainViewport;
    private Viewport backgroundViewport;
    private BackStage mainStage;
    private Stage backgroundStage;
    private Hierarchy hierarchy;
    private SnapshotArray<Actor> actors;
    private ScreenManager.Key returningScreen;
    private InputMultiplexer multiplexer;


    /**
     * Constructor
     */
    public BaseScreen(SuperManager superManager) {
        this.superManager = superManager;

        actors = new SnapshotArray<Actor>();
        hierarchy = Hierarchy.LOWER;
        mainViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mainStage = new BackStage(mainViewport, UIRunnable.backOperation(this));

        mainStage.getRoot().setOrigin(VIEWPORT_WIDTH * 0.5f, VIEWPORT_HEIGHT * 0.5f);
        mainStage.getRoot().setScale(1f, 1f);
        mainStage.getRoot().setSize(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mainStage.getRoot().setPosition(0f, 0f);

        background = new Background(getAssetManager(), VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        backgroundViewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        backgroundStage = new Stage(backgroundViewport);
        backgroundStage.addActor(background);

        multiplexer = new InputMultiplexer();
        addProcessor(mainStage);
    }


    @Override
    public void show() {
        clearRootActions();
        unsetInputProcessor();
        addRootAction(sequence(
                transition(Flow.ENTERING, getHierarchy()),
                run(UIRunnable.setInputProcessor(getProcessor()))
        ));
    }

    @Override
    public void render(float delta) {
        clearColor();

        // backgroundStage.getViewport().apply();
        backgroundStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        backgroundStage.draw();
        // mainStage.getViewport().apply();
        mainStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mainViewport.update(width, height);
        backgroundViewport.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        clearActions();
        clearRootActions();
    }

    @Override
    public void dispose() {
        mainStage.dispose();
        backgroundStage.dispose();
    }

    protected void setReturningScreen(ScreenManager.Key key) {
        this.returningScreen = key;
    }

    @Override
    public void back() {
        switchToScreen(this.returningScreen, Hierarchy.HIGHER);
    }


    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    public AssetManager getAssetManager() {
        return getSuperManager().getAssetManager();
    }

    public ScreenManager getScreenManager() {
        return getSuperManager().getScreenManager();
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return getAssetManager().get(fileName, type);
    }

    private SuperManager getSuperManager() {
        return this.superManager;
    }

    public void setAsInputProcessor() {
        Gdx.input.setInputProcessor(this.mainStage);
    }

    public void unsetInputProcessor() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Ejecuta la animacion de salida y cambia a otra pantalla
     * @param key Name Nombre de la siguiente pantalla
     * @param hierarchy Hierarchy Jerarquía de la siguiente pantalla respecto de la actual
     */
    public void switchToScreen(final ScreenManager.Key key, final Hierarchy hierarchy) {
        clearRootActions();
        unsetInputProcessor();

        addRootAction(sequence(
                transition(Flow.LEAVING, hierarchy),
                run(UIRunnable.setScreen(getScreenManager(), key, hierarchy))
        ));
    }

    /**
     * Ejecuta la animacion de salida y termina la aplicacion
     *
     * TODO Las pantallas no deberian salir por si mismas, sino solicitar a un controller salir de
     * TODO la aplicacion, y el controller debe devolver una respuesta. Si es positiva, la pantalla
     * TODO anima su salida; si no, deberia mostrar algun feedback. Pero no va a ser asi...
     */
    public void exitApplication() {
        clearRootActions();
        addRootAction(sequence(
                transition(Flow.LEAVING, Hierarchy.HIGHER),
                run(UIRunnable.exit())
        ));
    }

    public void clearRootActions() {
        mainStage.getRoot().clearActions();
    }

    /**
     * Ejecuta una animacion de transicion
     *
     * @param flow Flow Flujo de la pantalla actual
     * @param hierarchy Hierarchy Jerarquía de la siguiente pantalla respecto de la actual
     * @return
     */
    protected Action transition(Flow flow, Hierarchy hierarchy) {
        Action action;

        switch (flow) {
            case ENTERING:
                action = transitionEntering(hierarchy);
                break;

            case LEAVING:
                action = transitionLeaving(hierarchy);
                break;

            default:
                action = UIAction.reset();
        }

        return action;
    }

    private Action transitionEntering(Hierarchy hierarchy) {
        Action action;

        switch (hierarchy) {
            case LOWER:
                action = UIAction.fromInside();
                break;
            case HIGHER:
                action = UIAction.fromOutside();
                break;
            default:
                action = UIAction.appear();
        }

        return action;
    }

    private Action transitionLeaving(Hierarchy hierarchy) {
        Action action;

        switch (hierarchy) {
            case LOWER:
                action = UIAction.toOutside();
                break;
            case HIGHER:
                action = UIAction.toInside();
                break;
            default:
                action = UIAction.disappear();
        }

        return action;
    }

    public void addRootAction(Action action) {
        mainStage.getRoot().addAction(action);
    }

    protected void clearColor() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

    /**
     * Resetea todas las acciones en los actores registrados
     */
    private void clearActions() {
        for (Actor actor : actors) {
            actor.clearActions();

            if (actor instanceof BaseGroup) {
                ((BaseGroup) actor).reset();
            }
        }
    }

    protected Stage getMainStage() {
        return mainStage;
    }

    protected Stage getBackgroundStage() {
        return backgroundStage;
    }

    public Hierarchy getHierarchy() {
        return this.hierarchy;
    }

    protected InputProcessor getProcessor () {
        return this.multiplexer;
    }

    protected void addProcessor (InputProcessor processor) {
        this.multiplexer.addProcessor(processor);
    }

}
