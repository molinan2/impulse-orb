package com.jmolina.orb.situations.hero;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation406 extends SideWalls {

    public Situation406(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        float octave = 18f / 8;

        Heater heater = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                2.95f, 17.8f, 10.5f, 9
        );

        Movable cube1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                9, 4.5f, -1.5f, 1 * octave, 0
        );

        Element cube2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                6, 4.5f, 9, 3 * octave, 0
        );

        Movable cube3 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                9, 4.5f, -1.5f, 5 * octave, 0
        );

        Element cube4 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                6, 4.5f, 9, 7 * octave, 0
        );

        LinearMagnetic magnet1 = new LinearMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4.5f, 0.5f, 0.75f, 3 * octave, -90,
                4, Magnetic.Polarity.ATTRACTIVE
        );

        LinearMagnetic magnet2 = new LinearMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4.5f, 0.5f, 0.75f, 7 * octave, -90,
                4, Magnetic.Polarity.ATTRACTIVE
        );

        cube1.addDisplacement(0.25f, 6);
        cube3.addDisplacement(0.25f, 6);

        addElement(heater);
        addElement(cube1);
        addElement(cube2);
        addElement(cube3);
        addElement(cube4);
        addElement(magnet1);
        addElement(magnet2);
    }

}
