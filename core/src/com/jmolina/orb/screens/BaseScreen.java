package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.OrbGame;
import com.jmolina.orb.actions.UIAction;
import com.jmolina.orb.var.Vars;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.jmolina.orb.var.Vars.ScreenName.SCREEN_CREDITS;


public class BaseScreen implements Screen {

    public enum Flow {
        INNER, OUTER
    }

    protected OrbGame screenManager; // TODO: 24/05/2016 No me gusta que cualquier pantalla acceda a los metodos de OrbGame, pero aceptamos barco por ahora
    private Texture backgroundTexture;
    private Image background;
    private Viewport viewport;
    private Stage stage;
    private Stage backgroundStage;
    private Flow flow;


    public BaseScreen() {
        flow = Flow.INNER;
        viewport = new FitViewport(Vars.VIEWPORT_WIDTH, Vars.VIEWPORT_HEIGHT);
        stage = new Stage(viewport);
        stage.getRoot().setOrigin(Vars.VIEWPORT_WIDTH * 0.5f, Vars.VIEWPORT_HEIGHT * 0.5f);
        // stage.getRoot().setScale(1f, 1f);
        // stage.getRoot().setSize(Vars.VIEWPORT_WIDTH, Vars.VIEWPORT_HEIGHT);
        // stage.getRoot().setPosition(0, 0);

        backgroundStage = new Stage(viewport);
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        background = new Image(backgroundTexture);
        backgroundStage.addActor(background);
    }

    /**
     * Animaciones de entrada
     * Dependen por el parametro flow de goToScreen, segun indicara la pantalla que pidio cambiar hacia ésta
     */
    @Override
    public void show() {
        stage.getRoot().setTouchable(Touchable.disabled);
        stage.getRoot().clearActions();

        switch (flow) {
            case INNER:
                stage.getRoot().setScale(1 / Vars.ANIMATION_SCALE_FACTOR, 1 / Vars.ANIMATION_SCALE_FACTOR);
                break;
            case OUTER:
                stage.getRoot().setScale(Vars.ANIMATION_SCALE_FACTOR, Vars.ANIMATION_SCALE_FACTOR);
                break;
            default:
                stage.getRoot().setScale(1f, 1f);
        }

        stage.getRoot().addAction(sequence(
                fadeOut(0f),
                parallel(
                        scaleTo(1f, 1f, Vars.ANIMATION_DURATION),
                        fadeIn(Vars.ANIMATION_DURATION, Interpolation.pow2)),
                run(new Runnable() {
                    @Override
                    public void run() {
                        stage.getRoot().setTouchable(Touchable.enabled);
                    }
                })
        ));


        // TODO: 26/05/2016 Resetear las posiciones de todo (o crear pantalla desde cero)
        // TODO: 26/05/2016 Setear parametro para indicar si la animacion SE VA o VIENE (pantalla entra o sale), o DISABLED
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        backgroundStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        backgroundStage.draw();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
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
     */
    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        stage.dispose();
    }

    /**
     * TODO Cuando esté claro qué se usa de Stage, eliminarla y crear una API
     * @return Stage
     */
    protected Stage getStage() {
        return stage;
    }

    public void setAsInputProcessor() {
        Gdx.input.setInputProcessor(this.stage);
    }

    /**
     * TODO: 24/05/2016 La idea es que el parametro sea una clase ScreenManager especifica
     * @param game OrbGame
     */
    public void setScreenManager(OrbGame game) {
        this.screenManager = game;
    }

    /**
     * TODO Definir un CustomRunnable que implemente Runnable
     * Ejecuta la animacion de salida de pantalla, y cambia a otra pantalla
     * @param name
     * @param flow
     */
    public void goToScreen(final Vars.ScreenName name, final Flow flow) {
        Action action;

        getStage().getRoot().clearActions();

        switch (flow) {
            case INNER:
                action = UIAction.toOutside();
                break;
            case OUTER:
                action = UIAction.toInside();
                break;
            default:
                action = UIAction.toOutside();
        }

        getStage().getRoot().addAction(sequence(
                action,
                run(new Runnable() {
                    @Override
                    public void run() {
                        screenManager.setScreenByKey(name, flow);
                    }
                })
        ));
    }

    public void exitApplication() {
        getStage().getRoot().clearActions();
        getStage().getRoot().addAction(sequence(
                UIAction.toInside(),
                run(new Runnable() {
                    @Override
                    public void run() {
                        Gdx.app.exit();
                    }
                })
        ));
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }
}
