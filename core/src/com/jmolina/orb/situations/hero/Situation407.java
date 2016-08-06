package com.jmolina.orb.situations.hero;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation407 extends SideWalls {

    public Situation407(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Heater heater = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                11.95f, 8.95f, 6, 9
        );

        Movable cubeL = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                11, 9, -5, 9, 0
        );

        Movable cubeR = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                11, 9, 9, 9, 0
        );

        Element platformL = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                4.5f, 1, 2.25f, 14, 0
        );

        Element platformR = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                4.5f, 1, 9.75f, 14, 0
        );

        cubeL.addDisplacement(0.25f, 8);
        cubeR.addDisplacement(0.25f, 8);

        addElement(heater);
        addElement(cubeL);
        addElement(cubeR);
        addElement(platformL);
        addElement(platformR);
    }

}
