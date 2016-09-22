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
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation502 extends SideWalls {

    public Situation502(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable blockL = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 4.5f, 2.5f, 2.25f, 0
        );

        Movable blockR = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 4.5f, 9.5f, 2.25f, 0
        );

        Movable destroyerL = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 4.5f, 5.75f, 2.25f, 0
        );

        Movable destroyerR = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 4.5f, 6.25f, 2.25f, 0
        );

        Movable blockL2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 4.5f, -0.5f, 11.25f, 0
        );

        Movable blockR2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 4.5f, 12.5f, 11.25f, 0
        );

        Movable destroyerL2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 4.5f, 2.75f, 11.25f, 0
        );

        Movable destroyerR2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 4.5f, 9.25f, 11.25f, 0
        );

        blockL.addDisplacement(0.33f, -3);
        blockR.addDisplacement(0.33f, 3);
        destroyerL.addDisplacement(0.33f, -3);
        destroyerR.addDisplacement(0.33f, 3);
        blockL2.addDisplacement(0.33f, 3);
        blockR2.addDisplacement(0.33f, -3);
        destroyerL2.addDisplacement(0.33f, 3);
        destroyerR2.addDisplacement(0.33f, -3);

        addElement(destroyerL);
        addElement(destroyerR);
        addElement(blockL);
        addElement(blockR);
        addElement(destroyerL2);
        addElement(destroyerR2);
        addElement(blockL2);
        addElement(blockR2);

    }

}
