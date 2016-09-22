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
import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


/**
 * El pasillo de la muerte xD
 */
public class Situation505 extends SideWalls {

    public Situation505(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable destroyer1 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                11.5f, 4f, 1.5f, 6.75f, 0
        );

        Movable blockL1 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                12, 4.5f, 1.5f, 6.75f, 0
        );

        Movable blockR1 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                12, 4.5f, 16.5f, 6.75f, 0
        );

        LinearMagnetic magnetL1 = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4.5f, 0.5f, 7.5f, 6.75f, -90,
                1.5f, Magnetic.Polarity.REPULSIVE
        );

        LinearMagnetic magnetR1 = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4.5f, 0.5f, 10.5f, 6.75f, 90,
                1.5f, Magnetic.Polarity.REPULSIVE
        );

        Movable blockL2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                12, 4.5f, -4.5f, 11.25f, 0
        );

        Movable blockR2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                12, 4.5f, 10.5f, 11.25f, 0
        );

        Movable destroyer2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                11.5f, 4f, 10.5f, 11.25f, 0
        );

        LinearMagnetic magnetL2 = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4.5f, 0.5f, 1.5f, 11.25f, -90,
                1.5f, Magnetic.Polarity.REPULSIVE
        );

        LinearMagnetic magnetR2 = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4.5f, 0.5f, 4.5f, 11.25f, 90,
                1.5f, Magnetic.Polarity.REPULSIVE
        );

        destroyer1.addDisplacement(0.33f, 0, -4.5f);
        blockL1.addDisplacement(0.33f, 0, -4.5f);
        blockR1.addDisplacement(0.33f, 0, -4.5f);
        magnetL1.addDisplacement(0.33f, 0, -4.5f);
        magnetR1.addDisplacement(0.33f, 0, -4.5f);
        blockL2.addDisplacement(0.33f, 0, 4.5f);
        blockR2.addDisplacement(0.33f, 0, 4.5f);
        destroyer2.addDisplacement(0.33f, 0, 4.5f);
        magnetL2.addDisplacement(0.33f, 0, 4.5f);
        magnetR2.addDisplacement(0.33f, 0, 4.5f);

        addElement(destroyer1);
        addElement(destroyer2);
        addElement(blockL1);
        addElement(blockR1);
        addElement(magnetL1);
        addElement(magnetR1);
        addElement(blockL2);
        addElement(blockR2);
        addElement(magnetL2);
        addElement(magnetR2);
    }

}
