package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation101 extends SideWalledSituation {

    public Situation101(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Obstacle ball
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 14, 4, 4, 0,
                WorldElement.Geometry.CIRCLE, WorldElement.Flavor.GREY
        ));

        // Lateral red boxes
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                0.75f, 10, 0.5f, 4, 0,
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED
        ));

        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                11.25f, 10, 0.5f, 4, 0,
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED
        ));
    }

}
