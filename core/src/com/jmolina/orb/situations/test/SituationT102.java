package com.jmolina.orb.situations.test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.situations.SideWalls;

import static com.jmolina.orb.elements.Element.*;


public class SituationT102 extends SideWalls {

    public SituationT102(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Lateral boxes
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.GREY, 7, 7, 0, 7, 45
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.RED, 7, 0.5f, 2.5f, 9.5f, -45
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.GREY, 7, 7, 12, 12, -45
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.RED, 7, 0.5f, 9.5f, 14.5f, 45
        ));
    }

}
