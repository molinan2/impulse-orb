package com.jmolina.orb.situations.god;

import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Exit;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.situations.TopWalls;


public class Situation599B extends TopWalls {

    public Situation599B(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    @Override
    protected void createInnerElements () {
        Element platform = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.BLACK,
                2, 3, 6, 1.5f, 0
        );

        Element destroyerL = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                5, 6, 2.5f, 9f, 0
        );

        Element destroyerR = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                5, 6, 9.5f, 9f, 0
        );

        Up up = new Up(getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 1.5f
        );

        Exit exit = new Exit(getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 15
        );

        addElement(up);
        addElement(exit);
        addElement(platform);
        addElement(destroyerL);
        addElement(destroyerR);

        super.createInnerElements();
    }

}
