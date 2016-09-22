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

package com.jmolina.orb.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.widgets.ui.StaticBackground;

/**
 * Stage de fondo estatico, utilizada en las pantallas de menu y en el fundido de las pantallas de
 * menu con las de nivel.
 */
public class BackgroundStage extends Stage {

    /** Fondo estatico */
    private StaticBackground staticBackground;

    /**
     * Constructor
     *
     * @param assetManager AssetManager
     * @param viewport Viewport de pantalla
     */
    public BackgroundStage(AssetManager assetManager, Viewport viewport) {
        super(viewport);
        staticBackground = new StaticBackground(assetManager, viewport);
        addActor(staticBackground);
    }

}
