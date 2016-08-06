package com.jmolina.orb.situations.hero;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;


public class Situation405 extends SideWalls {

    public Situation405(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Heater heater = new Heater(getAssetManager(), getWorld(), getPixelsPerMeter(),
                5.95f, 18, 3, 9
        );

        Element cube1 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 6, 3, 3, 0
        );

        Element cube2 = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.GREY,
                6, 6, 3, 15, 0
        );

        Movable destroyer = new Movable(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.RED,
                3.33f, 3.33f, 9, 9, 0
        );

        destroyer.addRotation(0.25f);
        // destroyer.addDisplacement(0.25f, 0, 6);

        addElement(destroyer);
        addElement(heater);
        addElement(cube1);
        addElement(cube2);
    }

}
