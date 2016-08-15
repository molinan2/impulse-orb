package com.jmolina.orb.situations.basic;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation109 extends SideWalls {

    public Situation109(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        Element squareLeft = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8.5f, 6, 4.25f, 3.75f, 0);

        Element squareRight = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8.5f, 6, 7.75f, 12.75f, 0);

        addElement(squareLeft);
        addElement(squareRight);
    }

}
