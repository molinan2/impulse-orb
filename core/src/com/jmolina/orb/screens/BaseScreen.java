package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.OrbGame;


public class BaseScreen implements Screen {

    protected OrbGame screenManager; // TODO: 24/05/2016 No me gusta que cualquier pantalla acceda a los metodos de OrbGame, pero aceptamos barco por ahora
    private Texture backgroundTexture;
    private Image background;
    private Viewport viewport;
    private Stage stage;

    public BaseScreen() {
        viewport = new FitViewport(768.0f, 1184.0f);
        stage = new Stage(viewport);

        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        background = new Image(backgroundTexture);
        stage.addActor(background);
    }

    @Override
    public void show() {
        System.out.println("Screen: Show: " + this.getClass());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
