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

package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Cortina blanca usada en la transicion entre pantallas del menu y de juego
 */
public class Curtain extends BaseGroup {

    /** Imagen de la cortina */
    private Image image;

    /**
     * Constructor
     *
     * @param am AssetManager
     */
    public Curtain(AssetManager am) {
        super(am);

        TextureRegion region = findRegion(Atlas.GAME_SQUARE_WHITE);
        float scaleX = BaseScreen.VIEWPORT_WIDTH / region.getRegionWidth();
        float scaleY = BaseScreen.VIEWPORT_HEIGHT / region.getRegionHeight();

        image = new Image(region);
        image.setPosition(0, 0);
        image.setScale(scaleX, scaleY);

        setTransform(false);
        addActor(image);
        setSize(BaseScreen.VIEWPORT_WIDTH, BaseScreen.VIEWPORT_HEIGHT);
    }

}
