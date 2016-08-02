package com.jmolina.orb.situations.expert;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.Vortex;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation311 extends SideWalls {

    public Situation311(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        RadialMagnetic radial = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 2, 4.5f,
                4, Magnetic.Polarity.ATTRACTIVE
        );

        Propeller propeller1 = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                12, 6, 13.5f, 0,
                4, Magnetic.Polarity.ATTRACTIVE
        );

        Propeller propeller2 = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                12, 6, 13.5f, 180,
                4, Magnetic.Polarity.ATTRACTIVE
        );

        radial.addDisplacement(0.25f, 8);

        addElement(radial);
        addElement(propeller1);
        addElement(propeller2);
    }

}
