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
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;
import com.jmolina.orb.var.Constant;


public class Situation210 extends SideWalls {

    public Situation210(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable destroyer = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                Constant.SQRT_2, Constant.SQRT_2, 6, 9, 0
        );

        destroyer.addRotation(0.25f);

        Movable inter = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2, 1, 6, 0, 0
        );

        Movable platform1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                5, 1, 9.5f, 0, 0
        );

        Movable platform2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                5, 1, 2.5f, 18, 0
        );

        platform1.addDisplacement(0.125f, 0, 18);
        platform2.addDisplacement(0.125f, 0, -18);
        inter.addDisplacement(0.25f, 0, 18);

        Element wallLeft1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 0.5f, 0, 0
        );

        Element wallLeft2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 0.5f, 4.5f, 0
        );

        Element wallLeft3 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 0.5f, 9, 0
        );

        Element wallLeft4 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 0.5f, 13.5f, 0
        );

        Element wallLeft5 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 0.5f, 18, 0
        );

        Element wallRight1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 11.5f, 0, 0
        );

        Element wallRight2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 11.5f, 4.5f, 0
        );

        Element wallRight3 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 11.5f, 9, 0
        );

        Element wallRight4 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 11.5f, 13.5f, 0
        );

        Element wallRight5 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2, 11.5f, 18, 0
        );

        addElement(platform1);
        addElement(platform2);
        addElement(inter);
        addElement(wallLeft1);
        addElement(wallLeft2);
        addElement(wallLeft3);
        addElement(wallLeft4);
        addElement(wallLeft5);
        addElement(wallRight1);
        addElement(wallRight2);
        addElement(wallRight3);
        addElement(wallRight4);
        addElement(wallRight5);
        addElement(destroyer);
    }

}
