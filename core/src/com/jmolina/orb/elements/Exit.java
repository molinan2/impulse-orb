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

package com.jmolina.orb.elements;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;

/**
 * Elemento de salida
 */
public class Exit extends Element {

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param pixelsPerMeter Ratio de conversion pixeles/metros
     * @param x Coordenada X de la posición en unidades del mundo
     * @param y Coordenada Y de la posición en unidades del mundo
     */
    public Exit(AssetManager am, World world, float pixelsPerMeter, float x, float y) {
        super(am, world, am.getGameAtlas().findRegion(Atlas.GAME_EXIT), pixelsPerMeter,
                Geometry.SQUARE, Flavor.BLUE,
                10, 4, x, y, 0
        );

        setAsSensor(true);
        setEffect(Effect.EXIT);
    }

}
