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

package com.jmolina.orb.situations.advanced;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation208 extends SideWalls {

    public Situation208(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable platform1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 4.5f, 0, 0
        );

        Movable platform2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 7.5f, 4.5f, 0
        );

        Movable platform3 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 4.5f, 9, 0
        );

        Movable platform4 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 7.5f, 13.5f, 0
        );

        platform1.addDisplacement(0.25f, 3);
        platform2.addDisplacement(0.25f, -3);
        platform3.addDisplacement(0.25f, 3);
        platform4.addDisplacement(0.25f, -3);

        addElement(platform1);
        addElement(platform2);
        addElement(platform3);
        addElement(platform4);
    }

}
