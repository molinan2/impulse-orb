package com.jmolina.orb.situations.advanced;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.Walls;


public class Situation208 extends Walls {

    public Situation208(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable platform1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 4.5f, 0, 0
        );

        Movable platform2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 7.5f, 4.5f, 0
        );

        Movable platform3 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 4.5f, 9, 0
        );

        Movable platform4 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                8, 1, 7.5f, 13.5f, 0
        );

        platform1.addDisplacement(0.25f, 3);
        platform2.addDisplacement(0.25f, -3);
        platform3.addDisplacement(0.25f, 3);
        platform4.addDisplacement(0.25f, -3);

        addElement(platform1);
        addElement(platform2);
        addElement(platform3);
        addElement(platform4);
    }

}
