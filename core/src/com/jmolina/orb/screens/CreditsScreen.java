package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.OrbGame;
import com.jmolina.orb.widgets.TextSectionWidget;

public class CreditsScreen extends MenuScreen {

    private TextSectionWidget application;
    private TextSectionWidget authors;
    private TextSectionWidget license;
    private TextSectionWidget libraries;
    private TextSectionWidget resources;
    private TextSectionWidget thanks;

    private Texture headerTexture;
    private Texture bodyTexture;

    public CreditsScreen() {
        super();

        setReturningScreen(OrbGame.Name.MAIN);

        headerTexture = new Texture(Gdx.files.internal("credits_header.png"));
        bodyTexture = new Texture(Gdx.files.internal("credits_body.png"));

        application = new TextSectionWidget(headerTexture, bodyTexture);
        authors = new TextSectionWidget(headerTexture, bodyTexture);
        license = new TextSectionWidget(headerTexture, bodyTexture);
        libraries = new TextSectionWidget(headerTexture, bodyTexture);
        resources = new TextSectionWidget(headerTexture, bodyTexture);
        thanks = new TextSectionWidget(headerTexture, bodyTexture);

        addRow(application);
        addRow(authors);
        addRow(license);
        addRow(libraries);
        addRow(resources);
        addRow(thanks);
    }

    @Override
    public void dispose() {
        headerTexture.dispose();
        bodyTexture.dispose();
        super.dispose();
    }

}
