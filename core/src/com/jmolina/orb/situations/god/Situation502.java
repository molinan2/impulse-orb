package com.jmolina.orb.situations.god;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.LinearMagnetic;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation502 extends SideWalls {

    public Situation502(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Movable blockL = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 4.5f, 2.5f, 2.25f, 0
        );

        Movable blockR = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 4.5f, 9.5f, 2.25f, 0
        );

        Movable destroyerL = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 4.5f, 5.75f, 2.25f, 0
        );

        Movable destroyerR = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 4.5f, 6.25f, 2.25f, 0
        );

        Movable blockL2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 4.5f, -0.5f, 11.25f, 0
        );

        Movable blockR2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 4.5f, 12.5f, 11.25f, 0
        );

        Movable destroyerL2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 4.5f, 2.75f, 11.25f, 0
        );

        Movable destroyerR2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 4.5f, 9.25f, 11.25f, 0
        );

        blockL.addDisplacement(0.33f, -3);
        blockR.addDisplacement(0.33f, 3);
        destroyerL.addDisplacement(0.33f, -3);
        destroyerR.addDisplacement(0.33f, 3);
        blockL2.addDisplacement(0.33f, 3);
        blockR2.addDisplacement(0.33f, -3);
        destroyerL2.addDisplacement(0.33f, 3);
        destroyerR2.addDisplacement(0.33f, -3);

        addElement(destroyerL);
        addElement(destroyerR);
        addElement(blockL);
        addElement(blockR);
        addElement(destroyerL2);
        addElement(destroyerR2);
        addElement(blockL2);
        addElement(blockR2);

    }

}
