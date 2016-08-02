package com.jmolina.orb.situations.expert;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.Vortex;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation307 extends SideWalls {

    public Situation307(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Vortex radial = new Vortex(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 9,
                5, Magnetic.Polarity.ATTRACTIVE
        );

        Movable triangle11 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                3, 3, 3, 3, 0);

        Movable triangle12 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                3, 3, 9, 3, 0);

        Movable triangle21 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                3, 3, 3, 15, 0);

        Movable triangle22 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                3, 3, 9, 15, 0);

        radial.addRotation(0.5f);
        triangle11.addRotation(0.25f, false);
        triangle12.addRotation(0.25f, true);
        triangle21.addRotation(0.25f, true);
        triangle22.addRotation(0.25f, false);

        addElement(radial);
        addElement(triangle11);
        addElement(triangle12);
        addElement(triangle21);
        addElement(triangle22);
    }

}
