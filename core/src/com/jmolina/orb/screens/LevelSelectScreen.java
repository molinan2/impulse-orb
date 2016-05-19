package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LevelSelectScreen extends MenuScreen {

    // private LevelCard level1;
    // private LevelCard level2;
    // private LevelCard level3;
    // private LevelCard level4;
    // private LevelCard level5;
    // private LevelCard level6;

    private Texture levelTexture;
    private Image level1;
    private Image level2;
    private Image level3;
    private Image level4;

    public LevelSelectScreen() {
        super();

        levelTexture = new Texture(Gdx.files.internal("level.png"));
        level1 = new Image(new TextureRegionDrawable(new TextureRegion(levelTexture)));
        setPositionRelative(0.083f, 0.65f, level1);
        getStage().addActor(level1);

        level2 = new Image(new TextureRegionDrawable(new TextureRegion(levelTexture)));
        setPositionRelative(1.0f - 0.083f, 0.65f, level2);
        getStage().addActor(level2);

        level3 = new Image(new TextureRegionDrawable(new TextureRegion(levelTexture)));
        setPositionRelative(0.083f, 0.25f, level3);
        getStage().addActor(level3);

        level4 = new Image(new TextureRegionDrawable(new TextureRegion(levelTexture)));
        setPositionRelative(1.0f - 0.083f, 0.25f, level4);
        getStage().addActor(level4);
    }

    @Override
    public void dispose() {
        levelTexture.dispose();
        super.dispose();
    }

}
