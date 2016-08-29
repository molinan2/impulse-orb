package com.jmolina.orb.situations.advanced;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation205CW extends SideWalls {

    public Situation205CW(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable triangle = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                6, 6, 6, 9, 0
        );

        Movable destroyerLeftBottom = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 0, 0, 45
        );

        Movable destroyerLeftTop = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 0, 18, 45
        );

        Movable destroyerRightBottom = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 12, 0, 45
        );

        Movable destroyerRightTop = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 12, 18, 45
        );

        triangle.addRotation(0.125f, true);
        destroyerLeftBottom.addDisplacement(0.25f, 0, 18);
        destroyerLeftTop.addDisplacement(0.25f, 0, -18);
        destroyerRightBottom.addDisplacement(0.25f, 0, 18);
        destroyerRightTop.addDisplacement(0.25f, 0, -18);

        addElement(triangle);
        addElement(destroyerLeftBottom);
        addElement(destroyerLeftTop);
        addElement(destroyerRightBottom);
        addElement(destroyerRightTop);
    }

}
