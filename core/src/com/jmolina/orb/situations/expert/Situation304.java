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
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation304 extends SideWalls {

    public Situation304(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Propeller magnet = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 6, 4.5f, 0,
                9, Magnetic.Polarity.REPULSIVE
        );

        Propeller magnetTopLeft = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                3, 2, 13.5f, 180,
                9, Magnetic.Polarity.REPULSIVE
        );

        Propeller magnetTopRight = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                3, 10, 13.5f, 180,
                9, Magnetic.Polarity.REPULSIVE
        );

        Element wall1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                9, 0.5f, 3.25f, 9, 90);

        Element wall2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                9, 0.5f, 8.75f, 9, 90);

        addElement(magnet);
        addElement(magnetTopLeft);
        addElement(magnetTopRight);
        addElement(wall1);
        addElement(wall2);
    }

}
