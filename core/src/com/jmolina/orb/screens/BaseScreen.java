package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.var.Vars;


public class BaseScreen implements Screen {

    Texture backgroundTexture;
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
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

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

    // TODO Sustituir y eliminar
    public void setPositionRelative(float x, float y, Image image) {
        image.setPosition(
                x * (Vars.VIEWPORT_WIDTH - image.getWidth()),
                y * (Vars.VIEWPORT_HEIGHT - image.getHeight())
        );
    }

    public void setAsInputProcessor() {
        Gdx.input.setInputProcessor(this.stage);
    }

}
