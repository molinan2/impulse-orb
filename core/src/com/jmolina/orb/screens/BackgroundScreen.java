package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.OrbGame;
import com.jmolina.orb.var.Vars;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;


public class BackgroundScreen implements Screen {

    private Texture backgroundTexture;
    private Image background;
    private Viewport viewport;
    private Stage stage;

    public BackgroundScreen() {
        viewport = new FitViewport(768.0f, 1184.0f);
        stage = new Stage(viewport);
        stage.getRoot().setOrigin(Vars.VIEWPORT_WIDTH * 0.5f, Vars.VIEWPORT_HEIGHT * 0.5f);
        // stage.getRoot().setScale(1f, 1f);
        // stage.getRoot().setSize(Vars.VIEWPORT_WIDTH, Vars.VIEWPORT_HEIGHT);
        // stage.getRoot().setPosition(0, 0);

        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        background = new Image(backgroundTexture);
        stage.addActor(background);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
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

}
