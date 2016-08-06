package com.jmolina.orb.situations.advanced;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation206 extends SideWalls {

    public Situation206(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable doorBottomLeft = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 0.5f, 3, 4.5f, 0
        );

        Movable doorBottomRight = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 0.5f, 9, 4.5f, 0
        );

        doorBottomLeft.addRotation(0.25f, false);
        doorBottomRight.addRotation(0.25f, true);

        Movable doorTopLeft = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 0.5f, 3, 13.5f, 0
        );

        Movable doorTopRight = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 0.5f, 9, 13.5f, 0
        );

        doorTopLeft.addRotation(0.25f, true);
        doorTopRight.addRotation(0.25f, false);

        addElement(doorBottomLeft);
        addElement(doorBottomRight);
        addElement(doorTopLeft);
        addElement(doorTopRight);
    }

}
