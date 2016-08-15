package com.jmolina.orb.situations.hero;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.BottomWalls;
import com.jmolina.orb.situations.SideWalls;


public class Situation401 extends SideWalls {

    public Situation401(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Element platformBottomC = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                5, 0.5f, 6, 3f, 0
        );

        Element platformBottomL = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 0.5f, 1.5f, 6, 0
        );

        Element platformBottomR = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 0.5f, 10.5f, 6, 0
        );

        Element slalom1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                9, 0.5f, 8, 9, 0
        );

        Element slalom2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                9, 0.5f, 4, 12, 0
        );

        Element slalom3 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                9, 0.5f, 8, 15, 0
        );

        Element platformTopL = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 0.5f, 1.5f, 18, 0
        );

        Element platformTopR = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                4, 0.5f, 10.5f, 18, 0
        );

        Heater heater = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                12, 6, 6, 12
        );

        addElement(heater);
        addElement(platformBottomC);
        addElement(platformBottomL);
        addElement(platformBottomR);
        addElement(platformTopL);
        addElement(platformTopR);
        addElement(slalom1);
        addElement(slalom2);
        addElement(slalom3);
    }

}
