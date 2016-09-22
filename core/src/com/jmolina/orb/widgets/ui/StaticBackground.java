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

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Background tilable ajustable y estatico
 */
public class StaticBackground extends BaseGroup {

    /** Imagen del background */
    private Image image;

    /** Imagen tilada */
    private TiledDrawable tiledDrawable;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param viewport Viewport
     */
    public StaticBackground(AssetManager am, Viewport viewport) {
        super(am);

        TextureRegion region = findRegion(Atlas.UI_BACKGROUND);
        tiledDrawable = new TiledDrawable(region);
        tiledDrawable.setMinWidth(viewport.getWorldWidth());
        tiledDrawable.setMinHeight(viewport.getWorldHeight());

        image = new Image(tiledDrawable);
        image.setPosition(0, 0);

        addActor(image);
    }

}
