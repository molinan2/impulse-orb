package com.jmolina.orb.situations.expert;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.BottomWalls;
import com.jmolina.orb.situations.SideWalls;


public class Situation301 extends SideWalls {

    public Situation301(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        LinearMagnetic magnetLeft = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                9, 1, 0, 4.5f, -90,
                6, Magnetic.Polarity.REPULSIVE
        );

        LinearMagnetic magnetRight = new LinearMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.VIOLET,
                9, 1, 12, 13.5f, 90,
                6, Magnetic.Polarity.REPULSIVE
        );

        Element box1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                1, 1, 6, 0, 45);

        Element box2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                1, 1, 6, 9, 45);

        addElement(magnetLeft);
        addElement(magnetRight);
        addElement(box1);
        addElement(box2);
    }

}
