package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.OrbGame;
import com.jmolina.orb.var.Vars;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class BaseScreen implements Screen {

    protected OrbGame screenManager; // TODO: 24/05/2016 No me gusta que cualquier pantalla acceda a los metodos de OrbGame, pero aceptamos barco por ahora
    private Texture backgroundTexture;
    private Image background;
    private Viewport viewport;
    private Stage stage;
    private Stage backgroundStage;

    public BaseScreen() {
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

    @Override
    public void show() {
        System.out.println("Screen: Show: " + this.getClass());
        stage.getRoot().setTouchable(Touchable.disabled);
        stage.getRoot().clearActions();
        stage.getRoot().setScale(0.8f, 0.8f);
        stage.getRoot().addAction(fadeOut(0f));
        stage.getRoot().addAction(sequence(
                parallel(
                    scaleTo(1f, 1f, 0.5f),
                    fadeIn(0.5f, Interpolation.pow2)),
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
        System.out.println("Screen: Resize: " + this.getClass());
    }

    @Override
    public void pause() {
        System.out.println("Screen: Pause: " + this.getClass());
    }

    @Override
    public void resume() {
        System.out.println("Screen: Resume: " + this.getClass());
    }

    @Override
    public void hide() {
        System.out.println("Screen: Hide: " + this.getClass());
    }

    @Override
    public void dispose() {
        System.out.println("Screen: Dispose: " + this.getClass());
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
     * @param game
     */
    public void setScreenManager(OrbGame game) {
        this.screenManager = game;
    }

}
