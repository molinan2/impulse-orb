package com.jmolina.orb.situations.expert;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation308L extends SideWalls {

    public Situation308L(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        LinearMagnetic magnet1 = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                9, 1, 11, 4.5f, 90,
                6, Magnetic.Polarity.REPULSIVE
        );

        LinearMagnetic magnet2 = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                9, 1, 11, 13.5f, 90,
                6, Magnetic.Polarity.REPULSIVE
        );

        Element destroyer1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                9, 1, 1, 4.5f, 90
        );

        Element destroyer2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                9, 1, 1, 13.5f, 90
        );

        addElement(magnet1);
        addElement(destroyer1);
        addElement(magnet2);
        addElement(destroyer2);
    }

}
