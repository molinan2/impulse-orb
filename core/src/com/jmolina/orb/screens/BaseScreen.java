package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.Orb;
import com.jmolina.orb.actions.UIAction;
import com.jmolina.orb.managers.OrbAssetManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseWidget;
import com.jmolina.orb.interfaces.AndroidBack;
import com.jmolina.orb.runnables.UIRunnable;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class BaseScreen implements Screen, AndroidBack {



    /**
     * Jerarquía de esta pantalla respecto de la siguiente
     */
    public enum Hierarchy {
        LOWER, HIGHER
    }

    /**
     * Flujo de navegación de la pantalla
     */
    public enum Flow {
        ENTERING, LEAVING
    }

    private OrbAssetManager assetManager;
    protected Orb screenManager;
    private Image bg;
    private Viewport viewport;
    private Stage mainStage;
    private Stage bgStage;
    private Hierarchy hierarchy;
    private SnapshotArray<Actor> actors;

    /**
     * Constructor
     */
    public BaseScreen(OrbAssetManager am) {
        setAssetManager(am);

        actors = new SnapshotArray<Actor>();

        hierarchy = Hierarchy.LOWER;
        viewport = new FitViewport(Var.VIEWPORT_WIDTH, Var.VIEWPORT_HEIGHT);
        mainStage = new Stage(viewport);
        getRoot().setOrigin(Var.VIEWPORT_WIDTH * 0.5f, Var.VIEWPORT_HEIGHT * 0.5f);
        getRoot().setScale(1f, 1f);
        getRoot().setSize(Var.VIEWPORT_WIDTH, Var.VIEWPORT_HEIGHT);
        getRoot().setPosition(0f, 0f);

        bg = new Image(getAssetManager().get(Asset.UI_BACKGROUND, Texture.class));

        bgStage = new Stage(viewport);
        bgStage.addActor(bg);
    }

    @Override
    public void show() {
        clearRootActions();
        unsetInputProcessor();
        addRootAction(sequence(
                transition(Flow.ENTERING, hierarchy),
                run(UIRunnable.setInputProcessor(mainStage))
        ));
    }

    @Override
    public void render(float delta) {
        clearColor();

        bgStage.getViewport().apply();
        bgStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        bgStage.draw();
        mainStage.getViewport().apply();
        mainStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        clearRegisteredActions();
        clearRootActions();
    }

    @Override
    public void dispose() {
        mainStage.dispose();
        bgStage.dispose();
    }

    public void setAsInputProcessor() {
        Gdx.input.setInputProcessor(this.mainStage);
    }

    public void unsetInputProcessor() {
        Gdx.input.setInputProcessor(null);
    }

    public void setScreenManager(Orb game) {
        this.screenManager = game;
    }

    public Orb getScreenManager() {
        return this.screenManager;
    }

    /**
     * Ejecuta la animacion de salida y cambia a otra pantalla
     * @param name Name Nombre de la siguiente pantalla
     * @param hierarchy Hierarchy Jerarquía de la siguiente pantalla respecto de la actual
     */
    public void switchToScreen(final Orb.Name name, final Hierarchy hierarchy) {
        clearRootActions();

        addRootAction(sequence(
                transition(Flow.LEAVING, hierarchy),
                run(UIRunnable.setScreen(screenManager, name, hierarchy))
        ));
    }

    /**
     * Ejecuta la animacion de salida y termina la aplicacion
     */
    public void exitApplication() {
        clearRootActions();
        addRootAction(sequence(
                transition(Flow.LEAVING, Hierarchy.HIGHER),
                run(UIRunnable.exit())
        ));
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     * Devuelve el Actor raíz de la Stage principal
     * @return Group
     */
    public Group getRoot() {
        return mainStage.getRoot();
    }

    public void clearRootActions() {
        getRoot().clearActions();
    }

    /**
     * Ejecuta una animacion de transicion
     *
     * @param flow Flow Flujo de la pantalla actual
     * @param hierarchy Hierarchy Jerarquía de la siguiente pantalla respecto de la actual
     * @return
     */
    private Action transition(Flow flow, Hierarchy hierarchy) {
        Action action;

        switch (flow) {
            case ENTERING:
                action = transitionEntering(hierarchy);
                break;

            case LEAVING:
                action = transitionLeaving(hierarchy);
                break;

            default:
                action = UIAction.dummy();
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
        getRoot().addAction(action);
    }

    private void clearColor() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Añade un Actor a la Stage principal
     */
    public void addMainActor(Actor actor) {
        mainStage.addActor(actor);
        register(actor);
    }

    /**
     * Registra un actor para poder realizar operaciones automaticas sobre el
     * @param actor
     */
    protected void register(Actor actor) {
        this.actors.add(actor);
    }

    /**
     * Resetea todas las acciones en los actores registrados
     */
    private void clearRegisteredActions() {
        for (Actor actor : actors) {
            actor.clearActions();

            if (actor instanceof BaseWidget) {
                ((BaseWidget) actor).reset();
            }
        }
    }

    @Override
    public void back() {
    }

    public void setAssetManager(OrbAssetManager am) {
        this.assetManager = am;
    }

    public OrbAssetManager getAssetManager() {
        return this.assetManager;
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return getAssetManager().get(fileName, type);
    }

}
