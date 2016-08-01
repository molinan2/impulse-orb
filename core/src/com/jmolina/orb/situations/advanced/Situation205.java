package com.jmolina.orb.situations.advanced;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.Walls;


public class Situation205 extends Walls {

    public Situation205(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable triangle = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.GREY,
                6, 6, 6, 9, 0
        );

        triangle.addRotation(0.125f);

        Movable destroyerLeftBottom = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 0, 0, 45
        );

        Movable destroyerLeftTop = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 0, 18, 45
        );

        destroyerLeftBottom.addDisplacement(0.25f, 0, 18);
        destroyerLeftTop.addDisplacement(0.25f, 0, -18);

        Movable destroyerRightBottom = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 12, 0, 45
        );

        Movable destroyerRightTop = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 12, 18, 45
        );

        destroyerRightBottom.addDisplacement(0.25f, 0, 18);
        destroyerRightTop.addDisplacement(0.25f, 0, -18);

        addElement(triangle);
        addElement(destroyerLeftBottom);
        addElement(destroyerLeftTop);
        addElement(destroyerRightBottom);
        addElement(destroyerRightTop);
    }

}
