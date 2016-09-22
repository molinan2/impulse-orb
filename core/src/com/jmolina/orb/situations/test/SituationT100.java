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
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class SituationT100 extends SideWalls {

    public SituationT100(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        // Test Rotable
        Movable movable = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.GREY,
                2, 2, 6, 4, 0
        );

        movable.addRotation(0.5f);
        movable.addDisplacement(0.25f, 4);
        addElement(movable);



        // Test Hot
        Heater heater = new Heater(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                10, 10, 0, 0
        );

        addElement(heater);



        // Test platform
        Movable platform = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.GREY,
                10, 1, 8, 7, 0
        );

        addElement(platform);



        // Test triangle
        Movable m2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.TRIANGLE, Element.Flavor.RED,
                3, 3, 10, 2, 0
        );

        m2.addRotation(0.25f);
        addElement(m2);




        // Test magnetic radial
        RadialMagnetic magnetic = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 6, 16,
                2, Magnetic.Polarity.ATTRACTIVE
        );

        magnetic.addRotation(0.25f);
        addElement(magnetic);




        // Test magnetic linear
        /*LinearMagnetic linear = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                6, 10f, 4, 0.5f, 30,
                4, Magnetic.Polarity.REPULSIVE
        );

        linear.addRotation(0.25f);
        addElement(linear);*/



        // Test propeller
        Propeller propeller = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                5, 6, 8, 0,
                2, Magnetic.Polarity.REPULSIVE
        );

        addElement(propeller);






        // Up
        addElement(new Up(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 4
        ));

        // Bottom walls
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 18, 6, -9 + 0.5f, 0
        ));

        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 18, -6 + 0.5f, -9 + 0.5f, 0
        ));

        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 18, 18 - 0.5f, -9 + 0.5f, 0
        ));
    }

}
