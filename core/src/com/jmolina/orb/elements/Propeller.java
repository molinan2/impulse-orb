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

/**
 * Impulsor. Es un elemento magnético lineal sin cuerpo.
 */
public class Propeller extends LinearMagnetic {

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param ppm Ratio de conversion pixeles/metros
     * @param w Anchura
     * @param x Coordenada X de la posicion en unidades del mundo
     * @param y Coordenada Y de la posicion en unidades del mundo
     */
    public Propeller(AssetManager am, World world, float ppm, float w, float x, float y, float angle, float threshold, Polarity polarity) {
        super(am, world, ppm, Flavor.AIR, w, 0, x, y, angle, threshold, polarity);
    }

}
