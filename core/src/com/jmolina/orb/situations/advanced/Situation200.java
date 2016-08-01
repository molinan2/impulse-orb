package com.jmolina.orb.situations.advanced;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.BottomWalls;


public class Situation200 extends BottomWalls {

    public Situation200(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable destroyer = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.RED,
                2, 2, 2, 2, 0);

        destroyer.addDisplacement(0.125f, 8);
        destroyer.addRotation(0.5f);

        Element up = new Up(getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 5
        );

        Element platform = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, 6, 8, 0);

        Movable gateLeft = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 0.5f, 0, 14, 0
        );

        Movable gateRight = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 0.5f, 12, 14, 0
        );

        Element gateLeftOver = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 1, 1.5f, 14, 0
        );

        Element gateRightOver = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                3, 1, 10.5f, 14, 0
        );

        gateLeft.addDisplacement(0.25f, 3);
        gateRight.addDisplacement(0.25f, -3);

        addElement(destroyer);
        addElement(up);
        addElement(platform);
        addElement(gateLeft);
        addElement(gateLeftOver);
        addElement(gateRight);
        addElement(gateRightOver);

        super.createInnerElements();
    }

}
