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


public class Situation205CCW extends SideWalls {

    public Situation205CCW(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable triangle = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                6, 6, 6, 9, 0
        );

        Movable destroyerLeftBottom = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 0, 0, 45
        );

        Movable destroyerLeftTop = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 0, 18, 45
        );

        Movable destroyerRightBottom = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 12, 0, 45
        );

        Movable destroyerRightTop = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 12, 18, 45
        );

        triangle.addRotation(0.125f, false);
        destroyerLeftBottom.addDisplacement(0.25f, 0, 18);
        destroyerLeftTop.addDisplacement(0.25f, 0, -18);
        destroyerRightBottom.addDisplacement(0.25f, 0, 18);
        destroyerRightTop.addDisplacement(0.25f, 0, -18);

        addElement(triangle);
        addElement(destroyerLeftBottom);
        addElement(destroyerLeftTop);
        addElement(destroyerRightBottom);
        addElement(destroyerRightTop);
    }

}
