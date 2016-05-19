package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LevelLaunchScreen extends MenuScreen {

    // private LevelCover cover;
    // private Ladder ladderLocal;
    // private Ladder ladderOnline;
    // private MainButton start;

    private Texture coverTexture;
    private Image cover;
    private Texture ladderLocalTexture;
    private Image ladderLocal;
    private Image ladderOnline;
    private Texture startTexture;
    private Image start;

    public LevelLaunchScreen() {
        super();

        coverTexture = new Texture(Gdx.files.internal("levelcover.png"));
        cover = new Image(new TextureRegionDrawable(new TextureRegion(coverTexture)));
        setPositionRelative(0.5f, 0.75f, cover);
        getStage().addActor(cover);

        ladderLocalTexture = new Texture(Gdx.files.internal("ladder.png"));
        ladderLocal = new Image(new TextureRegionDrawable(new TextureRegion(ladderLocalTexture)));
        setPositionRelative(0.1f, 0.45f, ladderLocal);
        getStage().addActor(ladderLocal);

        ladderOnline = new Image(new TextureRegionDrawable(new TextureRegion(ladderLocalTexture)));
        setPositionRelative(0.9f, 0.45f, ladderOnline);
        getStage().addActor(ladderOnline);

        startTexture = new Texture(Gdx.files.internal("mainbutton.png"));
        start = new Image(new TextureRegionDrawable(new TextureRegion(startTexture)));
        setPositionRelative(0.5f, 0.15f, start);
        getStage().addActor(start);
    }

    @Override
    public void dispose() {
        coverTexture.dispose();
        ladderLocalTexture.dispose();
        startTexture.dispose();
        super.dispose();
    }
}
