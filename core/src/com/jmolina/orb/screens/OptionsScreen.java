package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.var.Vars;
import com.jmolina.orb.widgets.CheckWidget;

public class OptionsScreen extends MenuScreen {

    // private CheckOption music;
    // private CheckOption sound;
    // private CheckOption vibration;
    // private CheckOption online;
    // private SelectOption zoom;

    private CheckWidget music;
    private CheckWidget sound;
    private CheckWidget vibration;
    private CheckWidget online;
    private Texture musicTexture;
    private Texture soundTexture;
    private Texture vibrationTexture;
    private Texture onlineTexture;
    // private MultiCheckWidget zoom;

    public OptionsScreen() {
        super();

        musicTexture = new Texture(Gdx.files.internal("option_music.png"));
        soundTexture = new Texture(Gdx.files.internal("option_sound.png"));
        vibrationTexture = new Texture(Gdx.files.internal("option_vibration.png"));
        onlineTexture = new Texture(Gdx.files.internal("option_online.png"));

        music = new CheckWidget(musicTexture);
        sound = new CheckWidget(soundTexture);
        vibration = new CheckWidget(vibrationTexture);
        online = new CheckWidget(onlineTexture);

        addRow(music);
        addRow(sound);
        addRow(vibration);
        addRow(online);

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
