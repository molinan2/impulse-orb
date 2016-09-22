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
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation311 extends SideWalls {

    public Situation311(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        RadialMagnetic radial = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 2, 4.5f,
                4, Magnetic.Polarity.ATTRACTIVE
        );

        Propeller propeller1 = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                12, 6, 13.5f, 0,
                4, Magnetic.Polarity.ATTRACTIVE
        );

        Propeller propeller2 = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                12, 6, 13.5f, 180,
                4, Magnetic.Polarity.ATTRACTIVE
        );

        radial.addDisplacement(0.25f, 8);

        addElement(radial);
        addElement(propeller1);
        addElement(propeller2);
    }

}
