package com.jmolina.orb.situations.basic;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.Walls;


public class Situation105 extends Walls {

    public Situation105(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        Element circleCenter1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                2, 2, 6, 2, 0);

        Element circleLeft1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                2, 2, 3, 6, 0);

        Element circleRight1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                2, 2, 9, 6, 0);

        Element circleCenter2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                2, 2, 6, 11, 0);

        Element circleLeft2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                2, 2, 3, 15, 0);

        Element circleRight2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY,
                2, 2, 9, 15, 0);

        addElement(circleCenter1);
        addElement(circleLeft1);
        addElement(circleRight1);
        addElement(circleCenter2);
        addElement(circleLeft2);
        addElement(circleRight2);
    }

}
