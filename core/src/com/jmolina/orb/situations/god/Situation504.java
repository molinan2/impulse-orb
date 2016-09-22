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
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


/**
 * El pasillo de la muerte xD
 */
public class Situation504 extends SideWalls {

    public Situation504(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Element blockL = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 16, 1, 8, 0
        );

        Element blockR = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 16, 11, 8, 0
        );

        Element blockC = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                4, 16, 6, 8, 0
        );

        Movable crusher1 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2, 2, -2, 1, 0
        );

        Movable destroyer1 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                1.5f, 1.5f, -2, 1, 0
        );

        Movable crusher2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2, 2, 14, 5, 0
        );

        Movable destroyer2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                1.5f, 1.5f, 14, 5, 0
        );

        Movable crusher1b = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2, 2, -2, 9, 0
        );

        Movable destroyer1b = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                1.5f, 1.5f, -2, 9, 0
        );

        Movable crusher2b = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2, 2, 14, 13, 0
        );

        Movable destroyer2b = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                1.5f, 1.5f, 14, 13, 0
        );

        crusher1.addDisplacement(0.25f, 16);
        destroyer1.addDisplacement(0.25f, 16);
        crusher2.addDisplacement(0.4f, -16);
        destroyer2.addDisplacement(0.4f, -16);
        crusher1b.addDisplacement(0.2f, 16);
        destroyer1b.addDisplacement(0.2f, 16);
        crusher2b.addDisplacement(0.5f, -16);
        destroyer2b.addDisplacement(0.5f, -16);

        addElement(destroyer1);
        addElement(destroyer2);
        addElement(destroyer1b);
        addElement(destroyer2b);
        addElement(crusher1);
        addElement(crusher2);
        addElement(crusher1b);
        addElement(crusher2b);
        addElement(blockL);
        addElement(blockR);
        addElement(blockC);
    }

}
