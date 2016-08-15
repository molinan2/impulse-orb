package com.jmolina.orb.situations.god;

import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation503 extends SideWalls {

    public Situation503(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable destroyer = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.RED,
                4, 4, 2, 14, 0
        );

        Propeller propeller = new Propeller(getAssetManager(), getWorld(), getPixelsPerMeter(),
                12, 6, 11, 180,
                13, Magnetic.Polarity.ATTRACTIVE
        );

        Element platform = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 1, 6, 6, 0
        );

        Element platform1L = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 1, 3.5f, 0.5f, 0
        );

        Element platform1R = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 1, 8.5f, 0.5f, 0
        );

        Element platform2L = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 1, 1, 10.5f, 0
        );

        Element platform2R = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 1, 11, 10.5f, 0
        );

        destroyer.addDisplacement(0.25f, 8);
        destroyer.addRotation(0.4f);

        addElement(propeller);
        addElement(platform);
        addElement(platform1L);
        addElement(platform1R);
        addElement(platform2L);
        addElement(platform2R);
        addElement(destroyer);
    }

}
