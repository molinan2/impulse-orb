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

package com.jmolina.orb.situations.test;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;

import static com.jmolina.orb.elements.Element.Flavor;
import static com.jmolina.orb.elements.Element.Geometry;


public class SituationT102 extends SideWalls {

    public SituationT102(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Lateral boxes
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.GREY, 7, 7, 0, 7, 45
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.RED, 7, 0.5f, 2.5f, 9.5f, -45
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.GREY, 7, 7, 12, 12, -45
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.RED, 7, 0.5f, 9.5f, 14.5f, 45
        ));
    }

}
