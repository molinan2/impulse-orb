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
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Vortex;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation307 extends SideWalls {

    public Situation307(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Vortex radial = new Vortex(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 9,
                4, Magnetic.Polarity.REPULSIVE
        );

        Movable triangle11 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                4, 4, 1, 3, 0);

        Movable triangle12 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                4, 4, 11, 3, 0);

        Movable triangle21 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                4, 4, 1, 15, 0);

        Movable triangle22 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                4, 4, 11, 15, 0);

        radial.addRotation(0.5f);
        triangle11.addRotation(0.25f, false);
        triangle12.addRotation(0.25f, true);
        triangle21.addRotation(0.25f, true);
        triangle22.addRotation(0.25f, false);

        addElement(radial);
        addElement(triangle11);
        addElement(triangle12);
        addElement(triangle21);
        addElement(triangle22);
    }

}
