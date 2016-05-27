package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.OrbGame;
import com.jmolina.orb.widgets.CheckWidget;
import com.jmolina.orb.widgets.MultiCheckWidget;

public class OptionsScreen extends MenuScreen {

    private CheckWidget music;
    private CheckWidget sound;
    private CheckWidget vibration;
    private CheckWidget online;
    private MultiCheckWidget zoom;
    private Texture musicTexture;
    private Texture soundTexture;
    private Texture vibrationTexture;
    private Texture onlineTexture;
    private Texture zoomTexture;
    // private String username;

    /**
     * TODO
     * Debe permitir introducir, modificar el Username
     * Por defecto, leerlo del móvil o del perfil del SO
     */

    public OptionsScreen() {
        super();

        setReturningScreen(OrbGame.Name.MAIN);

        musicTexture = new Texture(Gdx.files.internal("option_music.png"));
        soundTexture = new Texture(Gdx.files.internal("option_sound.png"));
        vibrationTexture = new Texture(Gdx.files.internal("option_vibration.png"));
        onlineTexture = new Texture(Gdx.files.internal("option_online.png"));
        zoomTexture = new Texture(Gdx.files.internal("option_zoom.png"));

        music = new CheckWidget(musicTexture);
        sound = new CheckWidget(soundTexture);
        vibration = new CheckWidget(vibrationTexture);
        online = new CheckWidget(onlineTexture);
        zoom = new MultiCheckWidget(zoomTexture);

        addRow(music);
        addRow(sound);
        addRow(vibration);
        addRow(online);
        addRow(zoom);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
