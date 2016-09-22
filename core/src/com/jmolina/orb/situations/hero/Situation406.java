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
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation406 extends SideWalls {

    public Situation406(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        float octave = 18f / 8;

        Heater heater = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                2.95f, 17.8f, 10.5f, 9
        );

        Movable cube1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                9, 4.5f, -1.5f, 1 * octave, 0
        );

        Element cube2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                6, 4.5f, 9, 3 * octave, 0
        );

        Movable cube3 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                9, 4.5f, -1.5f, 5 * octave, 0
        );

        Element cube4 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                6, 4.5f, 9, 7 * octave, 0
        );

        Propeller magnet1 = new Propeller(getAssetManager(), getWorld(), getPixelsPerMeter(),
                4.5f, 6, 3 * octave, 90,
                4, Magnetic.Polarity.REPULSIVE
        );

        Propeller magnet2 = new Propeller(getAssetManager(), getWorld(), getPixelsPerMeter(),
                4.5f, 6, 7 * octave, 90,
                4, Magnetic.Polarity.REPULSIVE
        );

        cube1.addDisplacement(0.25f, 6);
        cube3.addDisplacement(0.25f, 6);

        addElement(heater);
        addElement(cube1);
        addElement(cube2);
        addElement(cube3);
        addElement(cube4);
        addElement(magnet1);
        addElement(magnet2);
    }

}
