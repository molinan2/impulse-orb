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

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Fondo del HUD
 */
public class HUDBackground extends BaseGroup {

    /** Borde y fondo semitransparente */
    private Image border, overlay;

    /**
     * Constructor
     *
     * @param am AssetManager
     */
    public HUDBackground(AssetManager am) {
        super(am);

        overlay = new Image(findRegion(Atlas.HUD_BACKGROUND_OVERLAY));
        overlay.setPosition(0, 0);

        border = new Image(findRegion(Atlas.HUD_BACKGROUND_BORDER));
        border.setPosition(0, 0);

        addActor(overlay);
        addActor(border);

        overlay.setScale(24, 28);
        border.setScaleX(24);
        // setSize(Utils.cell(24), Utils.cell(2.5f));
        setOrigin(Utils.cell(12), Utils.cell(14));
    }

}
