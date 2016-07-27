package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.BaseElement;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation103 extends SideWalledSituation {

    public Situation103(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Obstacle bars
        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), BaseElement.Geometry.SQUARE, BaseElement.Flavor.GREY, 4.5f, 3.5f, 9, 1, 0
        ));

        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), BaseElement.Geometry.SQUARE, BaseElement.Flavor.GREY, 7.5f, 7.5f, 9, 1, 0
        ));

        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), BaseElement.Geometry.SQUARE, BaseElement.Flavor.GREY, 4.5f, 11.5f, 9, 1, 0
        ));

        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), BaseElement.Geometry.SQUARE, BaseElement.Flavor.GREY, 7.5f, 15.5f, 9, 1, 0
        ));
    }

}
