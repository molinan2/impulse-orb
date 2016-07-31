package com.jmolina.orb.situations.basic;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.BottomWalls;


public class Situation100 extends BottomWalls {

    public Situation100(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Element up = new Up(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 3
        );

        Element platform = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 1, 6, 6, 0);

        Element platformRight = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 10.5f, 12, 0);

        Element platformLeft = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 1.5f, 12, 0);

        addElement(up);
        addElement(platform);
        addElement(platformLeft);
        addElement(platformRight);

        super.createInnerElements();
    }

}
