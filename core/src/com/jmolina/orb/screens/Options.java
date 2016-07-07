package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.Option;
import com.jmolina.orb.widgets.MultiOption;

public class Options extends Menu {

    private Option music;
    private Option sound;
    private Option vibration;
    private Option online;
    private MultiOption zoom;
    // private String username;

    private PrefsManager prefsManager;

    public Options(SuperManager superManager) {
        super(superManager);

        this.prefsManager = superManager.getPrefsManager();
        setPreviousScreenKey(ScreenManager.Key.MAIN);
        setTitle("OPTIONS");

        music = new Option(getAssetManager(), "Background music");
        sound = new Option(getAssetManager(), "Sound effects");
        vibration = new Option(getAssetManager(), "Vibration");
        online = new Option(getAssetManager(), "Online play");
        zoom = new MultiOption(getAssetManager(), "Zoom level");

        add(music);
        add(sound);
        add(vibration);
        add(online);
        add(zoom);

        music.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.toggle();
                prefsManager.putOptionMusic(music.isChecked());
            }
        });

        sound.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.toggle();
                prefsManager.putOptionSound(sound.isChecked());
            }
        });

        vibration.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                vibration.toggle();
                prefsManager.putOptionVibration(vibration.isChecked());
            }
        });

        online.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                online.toggle();
                prefsManager.putOptionOnline(online.isChecked());
            }
        });

        // TODO
        // Que el evento lo tire MultiOption, indicando el dato en algun campo de event
        zoom.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (event.getTarget() != null) {
                    String actorName = event.getTarget().getName();

                    if (actorName != null) {
                        int value = Integer.parseInt(actorName);
                        zoom.setValue(value);
                        prefsManager.putOptionZoom(value);
                    }
                }
            }
        });
    }

    @Override
    public void show() {
        super.show();
        updateScreenOptions();
    }

    @Override
    public void dispose () {
        music.dispose();
        sound.dispose();
        vibration.dispose();
        online.dispose();
        zoom.dispose();
        super.dispose();
    }

    @Override
    public void hide () {
        prefsManager.save();
        super.hide();
    }

    private void updateScreenOptions() {
        music.setChecked(prefsManager.getOptionMusic());
        sound.setChecked(prefsManager.getOptionSound());
        vibration.setChecked(prefsManager.getOptionVibration());
        online.setChecked(prefsManager.getOptionOnline());
        zoom.setValue(prefsManager.getOptionZoom());
    }

}
