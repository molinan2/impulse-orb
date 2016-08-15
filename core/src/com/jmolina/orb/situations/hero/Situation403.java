package com.jmolina.orb.situations.hero;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation403 extends SideWalls {

    public Situation403(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Heater heater2 = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                12, 8.5f, 6, 13.75f
        );

        LinearMagnetic platformL = new LinearMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4, 1, 2, 0.5f, 0,
                8, Magnetic.Polarity.REPULSIVE
        );

        LinearMagnetic platformR = new LinearMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4, 1, 10, 0.5f, 0,
                8, Magnetic.Polarity.REPULSIVE
        );

        LinearMagnetic platformC = new LinearMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                4, 1, 6, 9.5f, 180,
                8, Magnetic.Polarity.ATTRACTIVE
        );


        addElement(heater2);
        addElement(platformL);
        addElement(platformR);
        addElement(platformC);
    }

}
