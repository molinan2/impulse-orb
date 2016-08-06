package com.jmolina.orb.situations.expert;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation306 extends SideWalls {

    public Situation306(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        RadialMagnetic radial11 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 4, 3,
                4, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic radial12 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 8, 3,
                4, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic radial21 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 4, 9,
                4, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic radial22 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 8, 9,
                4, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic radial31 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 4, 15,
                4, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic radial32 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 8, 15,
                4, Magnetic.Polarity.REPULSIVE
        );

        addElement(radial11);
        addElement(radial12);
        addElement(radial21);
        addElement(radial22);
        addElement(radial31);
        addElement(radial32);
    }

}
