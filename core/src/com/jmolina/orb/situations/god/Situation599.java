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
import com.jmolina.orb.elements.Exit;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.TopWalls;


public class Situation599 extends TopWalls {

    public Situation599(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    @Override
    protected void createInnerElements () {
        Movable destroyer = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.RED,
                4, 4, 3, 10, 0
        );

        destroyer.addRotation(0.4f, false);
        destroyer.addDisplacement(0.16f, 6);

        addElement(destroyer);

        addElement(new Up(getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 2
        ));

        addElement(new Exit(getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 15
        ));

        super.createInnerElements();
    }

}
