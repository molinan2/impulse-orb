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
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation105 extends SideWalls {

    public Situation105(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        Element circleCenter1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                4, 4, 6, 2, 0);

        Element circleLeft1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                2, 2, 0.5f, 6, 0);

        Element circleRight1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                2, 2, 11.5f, 6, 0);

        Element circleCenter2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                4, 4, 6, 11, 0);

        Element circleLeft2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                2, 2, 0.5f, 15, 0);

        Element circleRight2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                2, 2, 11.5f, 15, 0);

        addElement(circleCenter1);
        addElement(circleLeft1);
        addElement(circleRight1);
        addElement(circleCenter2);
        addElement(circleLeft2);
        addElement(circleRight2);
    }

}
