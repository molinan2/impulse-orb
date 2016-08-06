package com.jmolina.orb.situations.god;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation503 extends SideWalls {

    public Situation503(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable destroyer = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.RED,
                2, 2, 2, 15, 0
        );

        Propeller propeller = new Propeller(getAssetManager(), getWorld(), getPixelsPerMeter(),
                12, 6, 13, 180,
                13, Magnetic.Polarity.ATTRACTIVE
        );

        Element platform = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 1, 6, 9, 0
        );

        destroyer.addDisplacement(0.25f, 8);
        destroyer.addRotation(0.4f);

        addElement(destroyer);
        addElement(propeller);
        addElement(platform);
    }

}
