package com.jmolina.orb.situations.god;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.SideWalls;
import com.jmolina.orb.var.Constant;


/**
 * El pasillo de la muerte xD
 */
public class Situation511 extends SideWalls {

    public Situation511(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Element platform1L = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, -0.5f, 0.5f, 0
        );

        Element platform1C = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                3, 1, 6, 0.5f, 0
        );

        Element platform1R = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, 12.5f, 0.5f, 0
        );

        Element destroyer1L = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                6, 5, 1.5f, 6.5f, 0
        );

        Element destroyer1R = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                6, 5, 10.5f, 6.5f, 0
        );

        Element platform2L = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, 2.5f, 9.5f, 0
        );

        Element platform2R = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 1, 9.5f, 9.5f, 0
        );

        Element destroyer2L = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                4, 5, -0.5f, 15.5f, 0
        );

        Element destroyer2C = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                3, 5, 6, 15.5f, 0
        );

        Element destroyer2R = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                4, 5, 12.5f, 15.5f, 0
        );

        addElement(platform1L);
        addElement(platform1C);
        addElement(platform1R);
        addElement(platform2L);
        addElement(platform2R);

        addElement(destroyer1L);
        addElement(destroyer1R);
        addElement(destroyer2L);
        addElement(destroyer2C);
        addElement(destroyer2R);
    }

}
