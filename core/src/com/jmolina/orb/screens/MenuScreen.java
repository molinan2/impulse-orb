package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuScreen extends BaseScreen {

    private Texture backTitleTexture;
    private Image backTitle;

    // private BackTitle backTitle;

    public MenuScreen() {
        super();
        backTitleTexture = new Texture(Gdx.files.internal("backtitle.png"));
        backTitle = new Image(new TextureRegionDrawable(new TextureRegion(backTitleTexture)));
        setPositionRelative(0.5f, 0.95f, backTitle);
        getStage().addActor(backTitle);
    }

    @Override
    public void dispose() {
        backTitleTexture.dispose();
        super.dispose();
    }
}
