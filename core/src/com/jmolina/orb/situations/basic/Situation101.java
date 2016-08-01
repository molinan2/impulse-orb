package com.jmolina.orb.situations.basic;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.Walls;


public class Situation101 extends Walls {

    public Situation101(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        Element platform1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 1, 6, 0, 0);

        Element platformLeft1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 1.5f, 4.5f, 0);

        Element platformRight1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 10.5f, 4.5f, 0);

        Element platform2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 1, 6, 9, 0);

        Element platformLeft2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 1.5f, 13.5f, 0);

        Element platformRight2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 10.5f, 13.5f, 0);

        addElement(platform1);
        addElement(platformRight1);
        addElement(platformLeft1);
        addElement(platform2);
        addElement(platformLeft2);
        addElement(platformRight2);
    }

}