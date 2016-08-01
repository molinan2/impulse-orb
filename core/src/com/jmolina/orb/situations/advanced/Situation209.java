package com.jmolina.orb.situations.advanced;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation209 extends SideWalls {

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

        Element squareBottomLeft = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2.5f, 2.5f, 0.5f, 4.5f, 45
        );

        Element squareBottomRight = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2.5f, 2.5f, 11.5f, 4.5f, 45
        );

        Element squareTopLeft = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                2.5f, 2.5f, 0.5f, 13.5f, 45
        );

        Element squareTopRight = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
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
