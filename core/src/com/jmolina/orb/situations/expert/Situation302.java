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
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation302 extends SideWalls {

    public Situation302(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        RadialMagnetic radial11 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 0.5f, 3f,
                4, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic radial12 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 11.5f, 3f,
                4, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic radial = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 6, 9f,
                6, Magnetic.Polarity.ATTRACTIVE
        );

        RadialMagnetic radial21 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 0.5f, 15f,
                4, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic radial22 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 11.5f, 15f,
                4, Magnetic.Polarity.REPULSIVE
        );

        addElement(radial11);
        addElement(radial12);
        addElement(radial21);
        addElement(radial22);
        addElement(radial);
    }

}
