package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.Orb;
import com.jmolina.orb.widgets.ParragraphWidget;

public class CreditsScreen extends MenuScreen {

    private ParragraphWidget application;
    private ParragraphWidget authors;
    private ParragraphWidget license;
    private ParragraphWidget libraries;
    private ParragraphWidget resources;
    private ParragraphWidget thanks;

    private Texture headerTexture;
    private Texture bodyTexture;

    public CreditsScreen() {
        super();

        setReturningScreen(Orb.Name.MAIN);
        setTitle("CREDITS");

        headerTexture = new Texture(Gdx.files.internal("credits_header.png"));
        bodyTexture = new Texture(Gdx.files.internal("credits_body.png"));

        application = new ParragraphWidget(headerTexture, bodyTexture);
        authors = new ParragraphWidget(headerTexture, bodyTexture);
        license = new ParragraphWidget(headerTexture, bodyTexture);
        libraries = new ParragraphWidget(headerTexture, bodyTexture);
        resources = new ParragraphWidget(headerTexture, bodyTexture);
        thanks = new ParragraphWidget(headerTexture, bodyTexture);

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
