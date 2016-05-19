package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainScreen extends BaseScreen {

    // private MainButton playButton;
    // private MainButton optionsButton;
    // private MainButton statsButton;
    // private MainButton creditsButton;
    // private MainButton exitButton;

    private Texture headerTexture;
    private Image header;
    private String author;
    private String version;

    public MainScreen() {
        super();

        headerTexture = new Texture(Gdx.files.internal("header.png"));
        header = new Image(new TextureRegionDrawable(new TextureRegion(headerTexture)));
        setPositionRelative(0.5f, 0.95f, header);
        getStage().addActor(header);
    }

    @Override
    public void dispose() {
        headerTexture.dispose();
        super.dispose();
    }

}
