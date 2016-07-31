package com.jmolina.orb.situations.basic;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.Walls;


public class Situation107 extends Walls {

    public Situation107(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        Element platform1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, 6, 3, 0);

        Element platform2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, 6, 9, 0);

        Element platform3 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, 6, 15, 0);

        addElement(platform1);
        addElement(platform2);
        addElement(platform3);
    }

}
