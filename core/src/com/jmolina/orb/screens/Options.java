package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.ui.Option;
import com.jmolina.orb.widgets.ui.MultiOption;

public class Options extends Menu {

    private Option music;
    private Option sound;
    private Option vibration;
    private Option online;
    private MultiOption zoom;

    private PrefsManager prefsManager;

    public Options(SuperManager superManager) {
        super(superManager);

        this.prefsManager = superManager.getPrefsManager();
        setPreviousScreen(ScreenManager.Key.MAIN);
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
                getGameManager().play(GameManager.Fx.Option);
                prefsManager.putOptionMusic(music.isChecked());
                prefsManager.save();
                getGameManager().fetchOptions();

                if (getGameManager().isEnabledMusic())
                    getGameManager().play(GameManager.Track.Menu);
                else
                    getGameManager().stopMusic();
            }
        });

        sound.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.toggle();
                prefsManager.putOptionSound(sound.isChecked());
                prefsManager.save();
                getGameManager().fetchOptions();
                getGameManager().play(GameManager.Fx.Option);
            }
        });

        vibration.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                vibration.toggle();
                getGameManager().play(GameManager.Fx.Option);
                prefsManager.putOptionVibration(vibration.isChecked());

                if (vibration.isChecked())
                    getGameManager().vibrate(GameManager.Length.Medium);
            }
        });

        online.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                online.toggle();
                getGameManager().play(GameManager.Fx.Option);
                prefsManager.putOptionOnline(online.isChecked());
                prefsManager.save();
                getGameManager().fetchOptions();

                if (getPrefsManager().getOptionOnline())
                    getSuperManager().getGameManager().signIn();
                else
                    getSuperManager().getGameManager().signOut();
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
                        getGameManager().play(GameManager.Fx.Option);
                        prefsManager.putOptionZoom(value);
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
    public void hide () {
        prefsManager.save();
        getGameManager().fetchOptions();
        super.hide();
    }

    private void updateOptions() {
        music.setChecked(prefsManager.getOptionMusic());
        sound.setChecked(prefsManager.getOptionSound());
        vibration.setChecked(prefsManager.getOptionVibration());
        online.setChecked(prefsManager.getOptionOnline());
        zoom.setValue(prefsManager.getOptionZoom());
    }

    /**
     * Sincroniza periódicamente el valor de la checkbox "online", ya que no se conoce a priori
     * el resultado de la petición de login.
     */
    @Override
    protected void periodicTask() {
        online.setChecked(prefsManager.getOptionOnline());
    }

}
