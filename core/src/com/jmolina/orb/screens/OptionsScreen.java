package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class OptionsScreen extends MenuScreen {

    // private CheckOption music;
    // private CheckOption sound;
    // private CheckOption vibration;
    // private CheckOption online;
    // private SelectOption zoom;

    private Texture musicTexture;
    private Image music;
    private Image sound;
    private Image vibration;
    private Image online;

    public OptionsScreen() {
        super();

        musicTexture = new Texture(Gdx.files.internal("check.png"));
        music = new Image(new TextureRegionDrawable(new TextureRegion(musicTexture)));
        setPositionRelative(0.5f, 0.65f, music);
        getStage().addActor(music);

        sound = new Image(new TextureRegionDrawable(new TextureRegion(musicTexture)));
        setPositionRelative(0.5f, 0.55f, sound);
        getStage().addActor(sound);

        vibration = new Image(new TextureRegionDrawable(new TextureRegion(musicTexture)));
        setPositionRelative(0.5f, 0.45f, vibration);
        getStage().addActor(vibration);

        online = new Image(new TextureRegionDrawable(new TextureRegion(musicTexture)));
        setPositionRelative(0.5f, 0.45f, online);
        getStage().addActor(online);
    }

    @Override
    public void dispose() {
        musicTexture.dispose();
        super.dispose();
    }
}
