package com.jmolina.orb.situations.expert;

import com.badlogic.gdx.assets.AssetManager;
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
                4, 6, 4.5f, 0,
                9, Magnetic.Polarity.REPULSIVE
        );

        Propeller magnetTopLeft = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                4, 2, 13.5f, 180,
                9, Magnetic.Polarity.REPULSIVE
        );

        Propeller magnetTopRight = new Propeller(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                4, 10, 13.5f, 180,
                9, Magnetic.Polarity.REPULSIVE
        );

        addElement(magnet);
        addElement(magnetTopLeft);
        addElement(magnetTopRight);
    }

}
