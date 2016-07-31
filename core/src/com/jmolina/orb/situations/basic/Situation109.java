package com.jmolina.orb.situations.basic;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.Walls;


public class Situation109 extends Walls {

    public Situation109(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        Element squareLeft = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                10, 7.5f, 5, 3.75f, 0);

        Element squareRight = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                10, 7.5f, 7, 12.75f, 0);

        addElement(squareLeft);
        addElement(squareRight);
    }

}
