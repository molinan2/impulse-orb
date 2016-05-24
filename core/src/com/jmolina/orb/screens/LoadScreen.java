package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Vars;

import static com.jmolina.orb.var.Vars.ScreenNames.SCREEN_MAIN;

/**
 * Descartada la barra de progreso
 */
public class LoadScreen extends BaseScreen {

    private Image splash;
    private Texture defaultSplashTexture;
    private float elapsed;

    public LoadScreen() {
        defaultSplashTexture = new Texture(Gdx.files.internal("splash.png"));
        setSplash(defaultSplashTexture);
        elapsed = 0.0f;
    }

    public void setSplash(Texture splashTexture) {
        splash = new Image(splashTexture);
        splash.setPosition(Utils.xGrid(2), Utils.yGrid(11));
        getStage().addActor(splash);
    }

    @Override
    public void dispose() {
        super.dispose();
        defaultSplashTexture.dispose();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        elapsed = elapsed + delta;

        if (elapsed > 1.5f) {
            screenManager.setScreenByKey(SCREEN_MAIN);
        }
    }
}
