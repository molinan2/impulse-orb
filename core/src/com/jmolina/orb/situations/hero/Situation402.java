package com.jmolina.orb.situations.hero;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation402 extends SideWalls {

    public Situation402(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Heater heater = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                5.95f, 17.95f, 6, 10.5f
        );

        Element cube1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 6, 6, 4.5f, 0
        );

        Element platform1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 10.5f, 2f, 0
        );

        Element destroyer1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                3, 1, 10.5f, 3f, 0
        );

        Element cube2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 6, 6, 13.5f, 0
        );

        Element platform2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 1.5f, 11, 0
        );

        Element destroyer2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                3, 1, 1.5f, 12, 0
        );

        Movable destroyerL = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                3f, 1, 4.75f, 4.5f, 0
        );

        Movable destroyerR = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                3f, 1, 7.25f, 13.5f, 0
        );

        destroyerL.addDisplacement(0.25f, -2.75f);
        destroyerR.addDisplacement(0.25f, 2.75f);

        addElement(destroyerL);
        addElement(destroyerR);
        addElement(heater);
        addElement(cube1);
        addElement(cube2);
        addElement(platform1);
        addElement(platform2);
        addElement(destroyer1);
        addElement(destroyer2);
    }

}
