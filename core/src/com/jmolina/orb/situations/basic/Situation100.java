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

package com.jmolina.orb.situations.basic;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.BottomWalls;


public class Situation100 extends BottomWalls {

    public Situation100(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Element up = new Up(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 3
        );

        Element platform = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, 6, 6, 0);

        Element platformLeft = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, 2f, 12, 0);

        Element platformRight = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, 10f, 12, 0);

        addElement(up);
        addElement(platform);
        addElement(platformLeft);
        addElement(platformRight);

        super.createInnerElements();
    }

}
