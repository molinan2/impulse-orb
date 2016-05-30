package com.jmolina.orb.screens;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.Orb;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.Option;
import com.jmolina.orb.widgets.MultiOption;

public class OptionsScreen extends MenuScreen {

    private Option music;
    private Option sound;
    private Option vibration;
    private Option online;
    private MultiOption zoom;
    // private String username;

    private Preferences prefs;

    public OptionsScreen() {
        super();

        setReturningScreen(Orb.Name.MAIN);
        setTitle("OPTIONS");

        music = new Option("Background music");
        sound = new Option("Sound effects");
        vibration = new Option("Vibration");
        online = new Option("Online play");
        zoom = new MultiOption("Zoom level");

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

        zoom.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (event.getTarget() != null) {
                    String actorName = event.getTarget().getName();
                    if (actorName != null) {
                        int value = Integer.parseInt(actorName);
                        zoom.setValue(value);
                        putOption(Var.OPTION_ZOOM, value);
                    }

                }
            }
        });
    }

    @Override
    public void show() {
        super.show();
        updateOptions();
    }

    @Override
    public void dispose () {
        super.dispose();
        music.dispose();
        sound.dispose();
        vibration.dispose();
        online.dispose();
        zoom.dispose();
    }

    @Override
    public void hide () {
        super.hide();
        prefs.flush();

        /**
         * TODO
         * ¿Qué pasa si cierro la aplicación? ¿Se guardan las prefs? ¿Llama a hide()?
         */
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
        zoom.setValue(MathUtils.clamp(
                prefs.getInteger(Var.OPTION_ZOOM),
                Var.OPTION_ZOOM_MIN,
                Var.OPTION_ZOOM_MAX));
    }

    private void putOption (String option, boolean value) {
        prefs.putBoolean(option, value);
    }

    private void putOption (String option, int value) {
        prefs.putInteger(option, value);
    }

}
