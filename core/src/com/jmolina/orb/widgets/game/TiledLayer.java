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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Una capa basada en tiles, del tamaño del nivel. Actualmente, se dibuja una dimensión
 * constante que cubre todos los niveles (en lugar de adaptarse a su tamaño).
 */
public class TiledLayer extends BaseGroup {

    private final int TIMES_X = 2; // LEVEL_LAT * SIT_W + 1
    private final int TIMES_Y = 16; // LEVEL_ALT * SIT_H * SPEED1 + 2

    /** Imagen tilable */
    private Image image;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param region Regin de textura
     * @param viewport Viewport
     */
    public TiledLayer(AssetManager am, TextureRegion region, Viewport viewport) {
        super(am);

        int viewportTilesX = MathUtils.round(viewport.getWorldWidth() / region.getRegionWidth());
        int viewportTilesY = MathUtils.round(viewport.getWorldHeight() / region.getRegionHeight());

        TiledDrawable tiledDrawable = new TiledDrawable(region);
        tiledDrawable.setMinWidth(TIMES_X * viewportTilesX * region.getRegionWidth());
        tiledDrawable.setMinHeight(TIMES_Y * viewportTilesY * region.getRegionHeight());

        image = new Image(tiledDrawable);
        image.setPosition(0, 0);

        addActor(image);
    }

}
