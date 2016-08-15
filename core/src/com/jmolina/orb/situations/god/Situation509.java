package com.jmolina.orb.situations.god;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


/**
 * El pasillo de la muerte xD
 */
public class Situation509 extends SideWalls {

    public Situation509(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable doorL = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                12, 1, -6, 1.5f, 0
        );

        Movable doorR = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                12, 1, 9, 1.5f, 0
        );

        RadialMagnetic destroyer = new RadialMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.RED,
                1.5f, 6, 3.5f,
                6, Magnetic.Polarity.ATTRACTIVE
        );

        RadialMagnetic magnetL = new RadialMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 0.5f, 11.5f,
                6, Magnetic.Polarity.REPULSIVE
        );

        RadialMagnetic magnetR = new RadialMagnetic(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                1, 11.5f, 11.5f,
                6, Magnetic.Polarity.REPULSIVE
        );

        doorL.addDisplacement(0.2f, 9);
        doorR.addDisplacement(0.2f, 9);
        destroyer.addRotation(0.33f);

        addElement(destroyer);
        addElement(magnetL);
        addElement(magnetR);
        addElement(doorL);
        addElement(doorR);

    }

}
