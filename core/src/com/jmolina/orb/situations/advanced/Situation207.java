package com.jmolina.orb.situations.advanced;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation207 extends SideWalls {

    public Situation207(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable circle = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                7, 7, 6, 5.5f, 0
        );

        Movable blade1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                11, 0.5f, 6, 5.5f, 0
        );

        Movable blade2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                11, 0.5f, 6, 5.5f, 120
        );

        Movable blade3 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                11, 0.5f, 6, 5.5f, 240
        );

        circle.addRotation(0.125f);
        blade1.addRotation(0.125f);
        blade2.addRotation(0.125f);
        blade3.addRotation(0.125f);

        Movable platform = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, 2.5f, 14.5f, 0
        );

        platform.addRotation(0.5f);
        platform.addDisplacement(0.25f, 7);

        addElement(circle);
        addElement(blade1);
        addElement(blade2);
        addElement(blade3);
        addElement(platform);
    }

}
