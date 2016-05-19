package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LoadScreen extends BaseScreen {

    private Texture splashTexture;
    private Image splash;
    // private ProgressBar progressBar;

    public LoadScreen() {
        super();
        splashTexture = new Texture(Gdx.files.internal("splash.png"));
        splash = new Image(new TextureRegionDrawable(new TextureRegion(splashTexture)));
        setPositionRelative(0.5f, 0.75f, splash);
        getStage().addActor(splash);
    }

    @Override
    public void dispose() {
        splashTexture.dispose();
        super.dispose();
    }
}
