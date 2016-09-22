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
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


/**
 * El pasillo de la muerte xD
 */
public class Situation509 extends SideWalls {

    public Situation509(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable doorL = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                12, 1, -6, 1.5f, 0
        );

        Movable doorR = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                12, 1, 9, 1.5f, 0
        );

        RadialMagnetic destroyer = new RadialMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1.5f, 6, 3.5f,
                6, Magnetic.Polarity.ATTRACTIVE
        );

        RadialMagnetic magnetL = new RadialMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 0.5f, 11.5f,
                6, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic magnetR = new RadialMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 11.5f, 11.5f,
                6, Magnetic.Polarity.REPULSIVE
        );

        doorL.addDisplacement(0.2f, 9);
        doorR.addDisplacement(0.2f, 9);
        destroyer.addRotation(0.33f);

        addElement(destroyer);
        addElement(magnetL);
        addElement(magnetR);
        addElement(doorL);
        addElement(doorR);

    }

}
