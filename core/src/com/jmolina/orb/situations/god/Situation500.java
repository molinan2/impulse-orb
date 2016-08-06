package com.jmolina.orb.situations.god;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.BottomWalls;


public class Situation500 extends BottomWalls {

    public Situation500(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        Element up = new Up(getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 4
        );

        Element destroyer = new Element(getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                12, 1, 6, 1f, 0
        );

        addElement(up);
        addElement(destroyer);

        super.createInnerElements();
    }

}
