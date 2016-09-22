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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.ui.Ladder;
import com.jmolina.orb.widgets.ui.LaunchCover;
import com.jmolina.orb.widgets.ui.MainButton;

import static com.jmolina.orb.managers.ScreenManager.Key.LEVEL_SELECT;

/**
 * Pantalla de lanzamiento de un nivel de juego
 */
public class Launch extends Menu {

    /** Portada */
    private LaunchCover cover;

    /** Boton de accion */
    private MainButton button;

    /** Ranking de tiempos locales */
    private Ladder ladder;

    /**
     * Constructor
     *
     * @param superManager SuperManager
     * @param level Nivel al que corresponde la pantalla
     * @param title Titulo de la pantalla
     */
    public Launch(SuperManager superManager, final ScreenManager.Key level, String title) {
        super(superManager);

        setPreviousScreen(LEVEL_SELECT);
        setTitle(title);

        cover = new LaunchCover(getAssetManager(), getTexture(level));
        button = new MainButton(getAssetManager(), "GO!", MainButton.Type.SUCCESS);
        ladder = new Ladder(getAssetManager(), getPrefsManager(), getGameManager(), level, "Top times");

        add(cover);
        add(ladder);
        add(button, 1f, 8f);

        button.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                button.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(level, Hierarchy.LOWER);
            }
        });
    }

    /**
     * Devuelve una textura de portada dependiendo del nivel
     *
     * @param level Nivel
     */
    private Texture getTexture(ScreenManager.Key level) {
        switch (level) {
            case LEVEL_1: return getAsset(Asset.UI_LAUNCH_COVER_1, Texture.class);
            case LEVEL_2: return getAsset(Asset.UI_LAUNCH_COVER_2, Texture.class);
            case LEVEL_3: return getAsset(Asset.UI_LAUNCH_COVER_3, Texture.class);
            case LEVEL_4: return getAsset(Asset.UI_LAUNCH_COVER_4, Texture.class);
            case LEVEL_5: return getAsset(Asset.UI_LAUNCH_COVER_5, Texture.class);
            default: return getAsset(Asset.UI_LAUNCH_COVER_1, Texture.class);
        }
    }

}
