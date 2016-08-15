package com.jmolina.orb.situations.hero;

import com.badlogic.gdx.math.MathUtils;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;
import com.jmolina.orb.var.Constant;
import com.jmolina.orb.var.Var;


public class Situation409CW extends SideWalls {

    public Situation409CW(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Element platform1L = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 1.5f, 6, 0
        );

        Element platform1R = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 10.5f, 6, 0
        );

        Element platform2L = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 1.5f, 12, 0
        );

        Element platform2R = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 10.5f, 12, 0
        );

        Movable door = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                5.5f * Constant.SQRT_2, 0.5f, 6, 9, 0
        );

        Element circle = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                1, 1, 6, 9, 0
        );

        door.addRotation(0.33f, true);

        addElement(platform1L);
        addElement(platform1R);
        addElement(platform2L);
        addElement(platform2R);
        addElement(door);
        addElement(circle);
    }

}
