package com.jmolina.orb.situations.advanced;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.Walls;


public class Situation209 extends Walls {

    public Situation209(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable triangle = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.RED,
                6, 6, 6, 9, 0
        );

        triangle.addRotation(0.125f, false);

        Movable squareBottomLeft = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2.5f, 2.5f, 0.5f, 4.5f, 45
        );

        Movable squareBottomRight = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2.5f, 2.5f, 11.5f, 4.5f, 45
        );

        Movable squareTopLeft = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2.5f, 2.5f, 0.5f, 13.5f, 45
        );

        Movable squareTopRight = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2.5f, 2.5f, 11.5f, 13.5f, 45
        );

        addElement(triangle);
        addElement(squareBottomLeft);
        addElement(squareBottomRight);
        addElement(squareTopLeft);
        addElement(squareTopRight);
    }

}
