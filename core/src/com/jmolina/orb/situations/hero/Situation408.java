package com.jmolina.orb.situations.hero;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Propeller;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation408 extends SideWalls {

    public Situation408(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Heater heater1 = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                4, 18, 2, 9
        );

        Heater heater2 = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                4, 18, 10, 9
        );

        Propeller propeller1 = new Propeller(getAssetManager(), getWorld(), getPixelsPerMeter(),
                18, 4, 9, -90,
                2, Magnetic.Polarity.REPULSIVE
        );

        Propeller propeller2 = new Propeller(getAssetManager(), getWorld(), getPixelsPerMeter(),
                18, 8, 9, 90,
                2, Magnetic.Polarity.REPULSIVE
        );

        Element destroyerL = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 18, 0.75f, 9, 0
        );

        Element destroyerR = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                0.5f, 18, 11.25f, 9, 0
        );

        addElement(heater1);
        addElement(heater2);
        addElement(propeller1);
        addElement(propeller2);
        addElement(destroyerL);
        addElement(destroyerR);
    }

}
