package com.jmolina.orb.situations.advanced;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.Walls;


public class Situation203 extends Walls {

    public Situation203(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        float octave = HEIGHT / 8;

        Movable platform1 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 1, 3, 4 * octave, 0);

        Movable platform2 = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 1, 9, 4 * octave, 0);

        Element destroyerLeft1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 0, 1 * octave, 45);

        Element destroyerLeft2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 0, 3 * octave, 45);

        Element destroyerLeft3 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 0, 5 * octave, 45);

        Element destroyerLeft4 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 0, 7 * octave, 45);

        Element destroyerRight1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 12, 1 * octave, 45);

        Element destroyerRight2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 12, 3 * octave, 45);

        Element destroyerRight3 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 12, 5 * octave, 45);

        Element destroyerRight4 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                2, 2, 12, 7 * octave, 45);

        platform1.addDisplacement(0.125f, 0, -4 * octave);
        platform2.addDisplacement(0.125f, 0, 4 * octave);

        addElement(platform1);
        addElement(platform2);
        addElement(destroyerLeft1);
        addElement(destroyerLeft2);
        addElement(destroyerLeft3);
        addElement(destroyerLeft4);
        addElement(destroyerRight1);
        addElement(destroyerRight2);
        addElement(destroyerRight3);
        addElement(destroyerRight4);
    }

}
