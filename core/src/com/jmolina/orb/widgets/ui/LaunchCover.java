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
 * Portada de la pantalla de lanzamiento de nivel
 */
public class LaunchCover extends BaseGroup {

    /** Portada y marco */
    private Image cover, frame;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param texture Textura de la portada
     */
    public LaunchCover(AssetManager am, Texture texture) {
        super(am);

        frame = new Image(getAsset(Asset.UI_LAUNCH_FRAME, Texture.class));
        cover = new Image(texture);

        addActor(cover);
        addActor(frame);

        setSize(Utils.cell(10), Utils.cell(6.5f));
    }

}
