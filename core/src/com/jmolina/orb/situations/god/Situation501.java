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

package com.jmolina.orb.situations.god;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation501 extends SideWalls {

    public Situation501(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        RadialMagnetic radial11 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 0.5f, 4.5f,
                5, Magnetic.Polarity.ATTRACTIVE
        );

        RadialMagnetic radial12 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 11.5f, 4.5f,
                5, Magnetic.Polarity.ATTRACTIVE
        );

        RadialMagnetic radial21 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 0.5f, 13.5f,
                5, Magnetic.Polarity.ATTRACTIVE
        );

        RadialMagnetic radial22 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 11.5f, 13.5f,
                5, Magnetic.Polarity.ATTRACTIVE
        );

        radial11.addDisplacement(0.5f, 0, 9);
        radial12.addDisplacement(0.5f, 0, 9);
        radial21.addDisplacement(0.5f, 0, -9);
        radial22.addDisplacement(0.5f, 0, -9);
        radial11.addRotation(0.25f);
        radial12.addRotation(0.25f);
        radial21.addRotation(0.25f);
        radial22.addRotation(0.25f);

        addElement(radial11);
        addElement(radial12);
        addElement(radial21);
        addElement(radial22);
    }

}
