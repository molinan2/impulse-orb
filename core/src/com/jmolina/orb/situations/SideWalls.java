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
 * Situacion basica de conveniencia. Contiene muros laterales.
 */
public abstract class SideWalls extends Situation {

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param pixelsPerMeter Factor de correcion pixeles/metros
     */
    public SideWalls(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    /**
     * Crea los elementos interiores de la situacion
     */
    protected abstract void createInnerElements();

    @Override
    protected void createElements () {
        createInnerElements();

        // Muro izquierdo
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 19, -5.5f, 9, 0
        ));

        // Muro derecho
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 19, 17.5f, 9, 0
        ));
    }

}
