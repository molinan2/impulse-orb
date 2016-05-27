package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.OrbGame;
import com.jmolina.orb.actions.UIAction;
import com.jmolina.orb.runnables.UIRunnable;
import com.jmolina.orb.var.Vars;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class BaseScreen implements Screen {

    /**
     * Jerarquía de esta pantalla respecto la siguiente
     * LOWER: inferior. HIGHER: superior.
     */
    public enum Hierarchy {
        LOWER, HIGHER
    }

    public enum Flow {
        ENTERING, LEAVING
    }

    // TODO: No me gusta que cualquier pantalla acceda a los metodos de OrbGame, pero aceptamos barco por ahora
    protected OrbGame manager;

    private Texture bgTexture;
    private Image background;
    private Viewport viewport;
    private Stage mainStage;
    private Stage bgStage;
    private Hierarchy hierarchy;


    /**
     * Constructor
     */
    public BaseScreen() {
        hierarchy = Hierarchy.LOWER;
        viewport = new FitViewport(Vars.VIEWPORT_WIDTH, Vars.VIEWPORT_HEIGHT);
        mainStage = new Stage(viewport);
        getRoot().setOrigin(Vars.VIEWPORT_WIDTH * 0.5f, Vars.VIEWPORT_HEIGHT * 0.5f);
        // mainStage.getRoot().setScale(1f, 1f);
        // mainStage.getRoot().setSize(Vars.VIEWPORT_WIDTH, Vars.VIEWPORT_HEIGHT);
        // mainStage.getRoot().setPosition(0, 0);

        bgStage = new Stage(viewport);
        bgTexture = new Texture(Gdx.files.internal("background.png"));
        background = new Image(bgTexture);
        bgStage.addActor(background);
    }

    /**
     * Animaciones de entrada
     * Dependen por el parametro hierarchy de switchToScreen, segun indicara la pantalla que pidio cambiar hacia ésta
     *
     * TODO
     * Resetear las posiciones y estados de los elementos
     * O instanciar la pantalla de nuevo (descartado por ahora e implica dispose)
     */
    @Override
    public void show() {
        clearActions();
        unsetInputProcessor();

        addRootAction(sequence(
                transition(Flow.ENTERING, hierarchy),
                run(UIRunnable.setInputProcessor(mainStage))
        ));
    }

    @Override
    public void render(float delta) {
        clearColor();

        bgStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        bgStage.draw();

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

    /**
     * TODO
     * ¿Por que no meter aqui las animaciones de salida?
     * Porque al final estamos en las mismas: la action es asincrona y el cambio de pantalla ocurre de golpe
     * Para poder animar al salir, es la pantalla la que debe ejecutar el cambio de pantalla despues de haberse animado
     *
     * Aqui sí se podrían meter los reseteos de los elementos
     */
    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        mainStage.dispose();
    }

    /**
     * TODO Cuando esté claro qué se usa de Stage, eliminarla y crear una API
     * @return Stage
     */
    protected Stage getMainStage() {
        return mainStage;
    }

    public void setAsInputProcessor() {
        Gdx.input.setInputProcessor(this.mainStage);
    }

    public void unsetInputProcessor() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * TODO
     * La idea es que el parametro sea una clase ScreenManager especifica
     *
     * @param game OrbGame
     */
    public void setManager(OrbGame game) {
        this.manager = game;
    }

    /**
     * TODO
     * Definir un CustomRunnable que implemente Runnable
     *
     * Ejecuta la animacion de salida de pantalla, y cambia a otra pantalla
     * @param name Name
     * @param hierarchy Hierarchy Jerarquía de la siguiente pantalla respecto de la actual
     */
    public void switchToScreen(final OrbGame.Name name, final Hierarchy hierarchy) {
        clearActions();

        addRootAction(sequence(
                transition(Flow.LEAVING, hierarchy),
                run(UIRunnable.setScreen(manager, name, hierarchy))
        ));
    }

    public void exitApplication() {
        clearActions();
        addRootAction(sequence(
                transition(Flow.LEAVING, Hierarchy.HIGHER),
                run(UIRunnable.exit())
        ));
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     * Devuelve el actor raíz (grupo) de la Stage principal
     * @return Group
     */
    public Group getRoot() {
        return getMainStage().getRoot();
    }

    public void clearActions() {
        getRoot().clearActions();
    }

    /**
     * TODO
     * ¿Es confuso el parametro hierarchy?
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

    public void addRootAction(Action action) {
        getRoot().addAction(action);
    }

    private void clearColor() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}
