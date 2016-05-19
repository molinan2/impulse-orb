package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainScreen extends BaseScreen {

    private Texture logoTexture;
    private Image logo;
    private Table table;
    private Table container;

    public MainScreen() {
        super();

        logoTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        logo = new Image(new TextureRegionDrawable(new TextureRegion(logoTexture)));

        getStage().addActor(logo);
    }

    @Override
    public void dispose() {
        logoTexture.dispose();
        super.dispose();
    }

}
