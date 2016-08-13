package com.jmolina.orb.situations.expert;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.BottomWalls;


public class Situation300 extends BottomWalls {

    public Situation300(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Element up = new Up(getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 5
        );

        LinearMagnetic magnet = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                12, 0.5f, 6, 0.75f, 0,
                6, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic radial = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 6, 12.5f,
                6, Magnetic.Polarity.ATTRACTIVE
        );

        addElement(magnet);
        addElement(radial);
        addElement(up);

        super.createInnerElements();
    }

}
