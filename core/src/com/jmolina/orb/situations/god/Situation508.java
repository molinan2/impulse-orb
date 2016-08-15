package com.jmolina.orb.situations.god;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


/**
 * El pasillo de la muerte xD
 */
public class Situation508 extends SideWalls {

    public Situation508(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        float hexa = 18.0f / 16;

        Element destroyerL = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 18, 0.75f, 9, 0
        );

        Element destroyerR = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 18, 11.25f, 9, 0
        );

        Movable lever1 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                1, 2.25f, 1, 1 * hexa, 0
        );

        Movable lever2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                1, 2.25f, 11, 5 * hexa, 0
        );

        Movable lever3 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                1, 2.25f, 1, 9 * hexa, 0
        );

        Movable lever4 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                1, 2.25f, 11, 13 * hexa, 0
        );

        Element center1 = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2.25f, 6, 3 * hexa, 0
        );

        Element center2 = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2.25f, 6, 7 * hexa, 0
        );

        Element center3 = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2.25f, 6, 11 * hexa, 0
        );

        Element center4 = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                1, 2.25f, 6, 15 * hexa, 0
        );

        lever1.addDisplacement(0.2f, 10);
        lever2.addDisplacement(0.2f, -10);
        lever3.addDisplacement(0.2f, 10);
        lever4.addDisplacement(0.2f, -10);

        addElement(lever1);
        addElement(lever2);
        addElement(lever3);
        addElement(lever4);
        addElement(center1);
        addElement(center2);
        addElement(center3);
        addElement(center4);
        addElement(destroyerL);
        addElement(destroyerR);
    }

}
