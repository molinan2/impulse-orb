package com.jmolina.orb.situations.test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.situations.Walls;


public class SituationT107 extends Walls {

    public SituationT107(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Grey stripe
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.GREY, 10, 1, 3.5f, 5, 45
        ));

        // Red stripe
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.RED, 10, 1, 8.5f, 13, -45
        ));
    }

}
