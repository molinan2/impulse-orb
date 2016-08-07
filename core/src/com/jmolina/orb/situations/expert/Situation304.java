package com.jmolina.orb.situations.expert;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation304 extends SideWalls {

    public Situation304(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Propeller magnet = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 6, 4.5f, 0,
                9, Magnetic.Polarity.REPULSIVE
        );

        Propeller magnetTopLeft = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                3, 2, 13.5f, 180,
                9, Magnetic.Polarity.REPULSIVE
        );

        Propeller magnetTopRight = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                3, 10, 13.5f, 180,
                9, Magnetic.Polarity.REPULSIVE
        );

        Element wall1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                9, 0.5f, 3.25f, 9, 90);

        Element wall2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                9, 0.5f, 8.75f, 9, 90);

        addElement(magnet);
        addElement(magnetTopLeft);
        addElement(magnetTopRight);
        addElement(wall1);
        addElement(wall2);
    }

}