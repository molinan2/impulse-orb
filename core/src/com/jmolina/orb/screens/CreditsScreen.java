package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CreditsScreen extends MenuScreen {

    // private TextSection application;
    // private TextSection authors;
    // private TextSection license;
    // private TextSection libraries;
    // private TextSection resources;
    // private TextSection thanks;

    private Texture applicationTexture;
    private Image application;
    private Image authors;
    private Image license;

    public CreditsScreen() {
        super();

        applicationTexture = new Texture(Gdx.files.internal("section.png"));
        application = new Image(new TextureRegionDrawable(new TextureRegion(applicationTexture)));
        setPositionRelative(0.5f, 0.65f, application);
        getStage().addActor(application);

        authors = new Image(new TextureRegionDrawable(new TextureRegion(applicationTexture)));
        setPositionRelative(0.5f, 0.25f, authors);
        getStage().addActor(authors);

        license = new Image(new TextureRegionDrawable(new TextureRegion(applicationTexture)));
        setPositionRelative(0.5f, -0.15f, license);
        getStage().addActor(license);
    }

    @Override
    public void dispose() {
        applicationTexture.dispose();
        super.dispose();
    }

}
