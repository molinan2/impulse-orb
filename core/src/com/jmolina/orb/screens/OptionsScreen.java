package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.Orb;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.OptionWidget;
import com.jmolina.orb.widgets.MultiOptionWidget;

public class OptionsScreen extends MenuScreen {

    private OptionWidget music;
    private OptionWidget sound;
    private OptionWidget vibration;
    private OptionWidget online;
    private MultiOptionWidget zoom;
    private Texture musicTexture;
    private Texture soundTexture;
    private Texture vibrationTexture;
    private Texture onlineTexture;
    private Texture zoomTexture;
    // private String username;

    private Preferences prefs;

    public OptionsScreen() {
        super();

        setReturningScreen(Orb.Name.MAIN);

        musicTexture = new Texture(Gdx.files.internal("option_music.png"));
        soundTexture = new Texture(Gdx.files.internal("option_sound.png"));
        vibrationTexture = new Texture(Gdx.files.internal("option_vibration.png"));
        onlineTexture = new Texture(Gdx.files.internal("option_online.png"));
        zoomTexture = new Texture(Gdx.files.internal("option_zoom.png"));

        music = new OptionWidget(musicTexture);
        sound = new OptionWidget(soundTexture);
        vibration = new OptionWidget(vibrationTexture);
        online = new OptionWidget(onlineTexture);
        zoom = new MultiOptionWidget(zoomTexture);

        addRow(music);
        addRow(sound);
        addRow(vibration);
        addRow(online);
        addRow(zoom);

        music.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.toggleCheckbox();
                putOption(Var.OPTION_MUSIC, music.isChecked());
            }
        });

        sound.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.toggleCheckbox();
                putOption(Var.OPTION_SOUND, sound.isChecked());
            }
        });

        vibration.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                vibration.toggleCheckbox();
                putOption(Var.OPTION_VIBRATION, vibration.isChecked());
            }
        });

        online.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                online.toggleCheckbox();
                putOption(Var.OPTION_ONLINE, online.isChecked());
            }
        });

    }

    @Override
    public void dispose () {
        super.dispose();
        music.dispose();
    }

    @Override
    public void hide () {
        super.hide();
        prefs.flush();
    }

    public void setPrefs (Preferences prefs) {
        this.prefs = prefs;
        updateOptions();
    }

    private void updateOptions () {
        music.setChecked(prefs.getBoolean(Var.OPTION_MUSIC));
        sound.setChecked(prefs.getBoolean(Var.OPTION_SOUND));
        vibration.setChecked(prefs.getBoolean(Var.OPTION_VIBRATION));
        online.setChecked(prefs.getBoolean(Var.OPTION_ONLINE));
    }

    private void putOption (String option, boolean value) {
        prefs.putBoolean(option, value);
    }

    private void putOption (String option, int value) {
        prefs.putInteger(option, value);
    }

    /**
     * TODO
     * Implementar MultiOption como un text + 3 checkbox
     * Implementar lógica para que siempre haya sólo una opción marcada
     */

}
