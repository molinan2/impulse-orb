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

package com.jmolina.orb.situations.expert;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation308R extends SideWalls {

    public Situation308R(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        LinearMagnetic magnet1 = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                9, 1, 1, 4.5f, -90,
                6, Magnetic.Polarity.REPULSIVE
        );

        LinearMagnetic magnet2 = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                9, 1, 1, 13.5f, -90,
                6, Magnetic.Polarity.REPULSIVE
        );

        Element destroyer1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                9, 1, 11, 4.5f, 90
        );

        Element destroyer2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                9, 1, 11, 13.5f, 90
        );

        addElement(magnet1);
        addElement(destroyer1);
        addElement(magnet2);
        addElement(destroyer2);
    }

}
