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
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation404 extends SideWalls {

    public Situation404(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Heater heater = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                12, 6, 6, 3
        );

        Movable gateTopL = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 0.5f, 0, 6, 0
        );

        Movable gateTopR = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 0.5f, 12, 6, 0
        );

        Element platformTopL = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 1, 1.5f, 6, 0
        );

        Element platformTopR = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 1, 10.5f, 6, 0
        );

        Movable gateBottomL = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 0.5f, 0, 0, 0
        );

        Movable gateBottomR = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 0.5f, 12, 0, 0
        );

        Element platformBottomL = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 1, 1.5f, 0, 0
        );

        Element platformBottomR = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 1, 10.5f, 0, 0
        );

        Element triangle = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                3, 3, 6, 13.5f, 180
        );

        gateTopL.addDisplacement(0.25f, 3);
        gateTopR.addDisplacement(0.25f, -3);
        gateBottomL.addDisplacement(0.5f, 3);
        gateBottomR.addDisplacement(0.5f, -3);

        addElement(heater);
        addElement(gateTopL);
        addElement(gateTopR);
        addElement(platformTopL);
        addElement(platformTopR);
        addElement(gateBottomL);
        addElement(gateBottomR);
        addElement(platformBottomL);
        addElement(platformBottomR);
        addElement(triangle);
    }

}
