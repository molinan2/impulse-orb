/*
 * IMPULSE ORB
 * Copyright (C) 2016 Juan M. Molina
 *
 * This file is part of the IMPULSE ORB source code.
 *
 * IMPULSE ORB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IMPULSE ORB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.ui.MultiOption;
import com.jmolina.orb.widgets.ui.Option;

public class Options extends Menu {

    private Option music, sound, vibration, online;
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
                prefsManager.save();
                getGameManager().fetchOptions();
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
