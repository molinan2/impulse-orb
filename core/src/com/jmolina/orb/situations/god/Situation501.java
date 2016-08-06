package com.jmolina.orb.situations.god;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.BottomWalls;
import com.jmolina.orb.situations.SideWalls;


public class Situation501 extends SideWalls {

    public Situation501(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        RadialMagnetic radial11 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 0.5f, 4.5f,
                5, Magnetic.Polarity.ATTRACTIVE
        );

        RadialMagnetic radial12 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 11.5f, 4.5f,
                5, Magnetic.Polarity.ATTRACTIVE
        );

        RadialMagnetic radial21 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 0.5f, 13.5f,
                5, Magnetic.Polarity.ATTRACTIVE
        );

        RadialMagnetic radial22 = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1, 11.5f, 13.5f,
                5, Magnetic.Polarity.ATTRACTIVE
        );

        radial11.addDisplacement(0.5f, 0, 9);
        radial12.addDisplacement(0.5f, 0, 9);
        radial21.addDisplacement(0.5f, 0, -9);
        radial22.addDisplacement(0.5f, 0, -9);
        radial11.addRotation(0.25f);
        radial12.addRotation(0.25f);
        radial21.addRotation(0.25f);
        radial22.addRotation(0.25f);

        addElement(radial11);
        addElement(radial12);
        addElement(radial21);
        addElement(radial22);
    }

}
