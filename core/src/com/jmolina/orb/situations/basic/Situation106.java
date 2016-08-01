package com.jmolina.orb.situations.basic;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation106 extends SideWalls {

    public Situation106(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        Element platformLeft = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                11, 1, 3, 5, 45);

        float px = 11.0f / (2 * (float) Math.sqrt(2)) + 1.0f / (2 * (float) Math.sqrt(2)) + 3;
        float py = 11.0f / (2 * (float) Math.sqrt(2)) - 1.0f / (2 * (float) Math.sqrt(2)) + 5;

        Element platformLeftOver = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                11, 1, px - 11/2.0f, py + 1/2.0f, 0);

        Element platformRight = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                11, 1, 9, 14, -45);

        Element platformRightOver = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                11, 1, px + 3, py + 1/2.0f + 9, 0);

        addElement(platformLeft);
        addElement(platformLeftOver);
        addElement(platformRight);
        addElement(platformRightOver);
    }

}
