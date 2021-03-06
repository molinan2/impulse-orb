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
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation503 extends SideWalls {

    public Situation503(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable destroyer = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.RED,
                4, 4, 2, 14, 0
        );

        Propeller propeller = new Propeller(getAssetManager(), getWorld(), getPixelsPerMeter(),
                12, 6, 11, 180,
                13, Magnetic.Polarity.ATTRACTIVE
        );

        Element platform = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 1, 6, 6, 0
        );

        Element platform1L = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 1, 3.5f, 0.5f, 0
        );

        Element platform1R = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 1, 8.5f, 0.5f, 0
        );

        Element platform2L = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 1, 1, 10.5f, 0
        );

        Element platform2R = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 1, 11, 10.5f, 0
        );

        destroyer.addDisplacement(0.25f, 8);
        destroyer.addRotation(0.4f);

        addElement(propeller);
        addElement(platform);
        addElement(platform1L);
        addElement(platform1R);
        addElement(platform2L);
        addElement(platform2R);
        addElement(destroyer);
    }

}
