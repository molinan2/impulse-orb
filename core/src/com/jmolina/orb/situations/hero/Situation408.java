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
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation408 extends SideWalls {

    public Situation408(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Heater heater1 = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                4, 18, 2, 9
        );

        Heater heater2 = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                4, 18, 10, 9
        );

        Propeller propeller1 = new Propeller(getAssetManager(), getWorld(), getPixelsPerMeter(),
                18, 4, 9, -90,
                2, Magnetic.Polarity.REPULSIVE
        );

        Propeller propeller2 = new Propeller(getAssetManager(), getWorld(), getPixelsPerMeter(),
                18, 8, 9, 90,
                2, Magnetic.Polarity.REPULSIVE
        );

        Element destroyerL = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 18, 0.75f, 9, 0
        );

        Element destroyerR = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 18, 11.25f, 9, 0
        );

        addElement(heater1);
        addElement(heater2);
        addElement(propeller1);
        addElement(propeller2);
        addElement(destroyerL);
        addElement(destroyerR);
    }

}
