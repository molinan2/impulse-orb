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
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;
import com.jmolina.orb.var.Constant;


public class Situation410 extends SideWalls {

    public Situation410(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Element platformL = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 1.5f, 6, 0
        );

        Element platformR = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 10.5f, 6, 0
        );

        Element borderL = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                1.0f / Constant.SQRT_2, 1.0f / Constant.SQRT_2, 3, 6, 45
        );

        Element borderR = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                1.0f / Constant.SQRT_2, 1.0f / Constant.SQRT_2, 9, 6, 45
        );

        Element triangle = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                3, 3, 6, 13.5f, 180
        );

        addElement(platformL);
        addElement(platformR);
        addElement(borderL);
        addElement(borderR);
        addElement(triangle);
    }

}
