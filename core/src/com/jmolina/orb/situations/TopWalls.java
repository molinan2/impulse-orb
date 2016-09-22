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

package com.jmolina.orb.situations;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.managers.AssetManager;

/**
 * Situacion basica de conveniencia. Contiene muros laterales y superiores.
 */
public class TopWalls extends SideWalls {

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param pixelsPerMeter Factor de correcion pixeles/metros
     */
    public TopWalls(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        // Muro arriba centro
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                13, 18, 6, 27, 0
        ));

        // Muro arriba izquierda
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                13, 19, -5.5f, 27, 0
        ));

        // Muro arriba derecha
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                13, 19, 17.5f, 27, 0
        ));
    }

}
