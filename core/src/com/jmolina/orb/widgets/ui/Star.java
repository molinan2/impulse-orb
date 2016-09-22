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

package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Estrella de puesto en el podio.
 */
public class Star extends BaseGroup {

    /** Imagen de la estrella */
    private Image image;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param rank Rango
     */
    public Star(AssetManager am, int rank) {
        super(am);

        image = new Image(getTexture(rank));
        image.setPosition(0f, 0f);
        addActor(image);
        setSize(Utils.cell(3.25f), Utils.cell(3));

        if (rank >= 1 && rank <= 3)
            image.setVisible(true);
        else
            image.setVisible(false);
    }

    /**
     * Devuelve la textura de la estrella correspondiente al rango indicado
     *
     * @param rank Rango
     */
    private Texture getTexture(int rank) {
        switch (rank) {
            case 1: return getAsset(Asset.UI_STAR_1ST, Texture.class);
            case 2: return getAsset(Asset.UI_STAR_2ND, Texture.class);
            case 3: return getAsset(Asset.UI_STAR_3RD, Texture.class);
            default: return getAsset(Asset.UI_STAR_1ST, Texture.class);
        }
    }

}
