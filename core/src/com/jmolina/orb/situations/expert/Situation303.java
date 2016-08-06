package com.jmolina.orb.situations.expert;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation303 extends SideWalls {

    public Situation303(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        float octave = 1.5f;

        Element cube11 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 3, 1 * octave, 1.5f, 0);

        Element cube12 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 3, 5 * octave, 1.5f, 0);

        Movable cube21 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 3, 1 * octave, 4.5f, 0);

        Movable cube22 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 3, 7 * octave, 4.5f, 0);

        Element cube31 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 3, 1 * octave, 7.5f, 0);

        Element cube32 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 3, 5 * octave, 7.5f, 0);

        Movable cube41 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 3, 3 * octave, 10.5f, 0);

        Movable cube42 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 3, 7 * octave, 13.5f, 0);

        Movable cube51 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 3, 1 * octave, 13.5f, 0);

        Element cube52 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 3, 5 * octave, 13.5f, 0);

        Movable cube6 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 3, 1 * octave, 16.5f, 0);

        Movable destroyer1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                1.5f, 1.5f, 1 * octave, 7.5f, 0);

        Movable destroyer2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                1.5f, 1.5f, 7 * octave, 1.5f, 0);

        Movable cube21destroyer = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2.75f, 2.75f, 1 * octave, 4.5f, 0);

        Movable cube22destroyer = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2.75f, 2.75f, 7 * octave, 4.5f, 0);

        Movable cube6destroyer = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2.75f, 2.75f, 1 * octave, 16.5f, 0);

        cube21.addDisplacement(0.25f, 3);
        cube22.addDisplacement(0.25f, -3);
        cube21destroyer.addDisplacement(0.25f, 3);
        cube22destroyer.addDisplacement(0.25f, -3);
        cube41.addDisplacement(0.16f, 0, 3);
        cube42.addDisplacement(0.33f, 0, -6);
        cube51.addDisplacement(0.16f, -3, 0);
        cube6.addDisplacement(0.16f, 6, 0);
        cube6destroyer.addDisplacement(0.16f, 6, 0);
        destroyer1.addDisplacement(0.25f, 0, 3);
        destroyer2.addDisplacement(0.125f, -3, 0);
        destroyer1.addRotation(0.5f);
        destroyer2.addRotation(0.5f);

        addElement(destroyer1);
        addElement(destroyer2);
        addElement(cube21destroyer);
        addElement(cube22destroyer);
        addElement(cube6destroyer);
        addElement(cube11);
        addElement(cube12);
        addElement(cube21);
        addElement(cube22);
        addElement(cube31);
        addElement(cube32);
        addElement(cube41);
        addElement(cube42);
        addElement(cube51);
        addElement(cube52);
        addElement(cube6);
    }

}
