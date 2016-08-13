package com.jmolina.orb.situations.basic;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;


public class Situation102L extends SideWalls {

    public Situation102L(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        Element platform1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 8, 2, 0);

        Element platform2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 4, 6.5f, 0);

        Element platform3 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 8, 11f, 0);

        Element platform4 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 4, 15.5f, 0);

        addElement(platform1);
        addElement(platform2);
        addElement(platform3);
        addElement(platform4);
    }

}