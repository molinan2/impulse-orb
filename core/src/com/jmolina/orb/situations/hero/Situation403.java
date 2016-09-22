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

package com.jmolina.orb.situations.hero;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation403 extends SideWalls {

    public Situation403(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Heater heater2 = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                12, 8.5f, 6, 13.75f
        );

        LinearMagnetic platformL = new LinearMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4, 1, 2, 0.5f, 0,
                8, Magnetic.Polarity.REPULSIVE
        );

        LinearMagnetic platformR = new LinearMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4, 1, 10, 0.5f, 0,
                8, Magnetic.Polarity.REPULSIVE
        );

        LinearMagnetic platformC = new LinearMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4, 1, 6, 9.5f, 180,
                8, Magnetic.Polarity.ATTRACTIVE
        );


        addElement(heater2);
        addElement(platformL);
        addElement(platformR);
        addElement(platformC);
    }

}
