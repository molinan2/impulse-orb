package com.jmolina.orb.situations.expert;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation310 extends SideWalls {

    public Situation310(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        RadialMagnetic radial1 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 6, 4.5f,
                5, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic radial2 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 6, 13.5f,
                5, Magnetic.Polarity.REPULSIVE
        );

        addElement(radial1);
        addElement(radial2);
    }

}
