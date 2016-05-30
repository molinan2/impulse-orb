package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.utils.Grid;

import static com.jmolina.orb.Orb.Name.MAIN;

public class LoadScreen extends BaseScreen {

    private Image splash;
    private Texture defaultSplashTexture;
    private float elapsed;
    private boolean acting;

    public LoadScreen() {
        defaultSplashTexture = new Texture(Gdx.files.internal("splash.png"));
        setSplash(defaultSplashTexture);
        elapsed = 0.0f;
        acting = false;
    }

    public void setSplash(Texture splashTexture) {
        splash = new Image(splashTexture);
        splash.setPosition(Grid.cellX(2), Grid.cellY(11));
        addMainActor(splash);
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

        if (elapsed > 0.5f && !acting) {
            acting = true;
            switchToScreen(MAIN, Hierarchy.LOWER);
        }
    }

    @Override
    public void show() {
    }

}
