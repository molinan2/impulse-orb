package com.jmolina.orb.situations.test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.situations.Walls;


public class SituationT104 extends Walls {

    public SituationT104(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Obstacle balls
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.CIRCLE, Element.Flavor.GREY, 4, 4, 4f, 5f, 0
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.CIRCLE, Element.Flavor.GREY, 4, 4, 8f, 12f, 0
        ));
    }

}
