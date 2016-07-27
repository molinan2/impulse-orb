package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.BaseElement;
import com.jmolina.orb.situations.SideWallSituation;

import static com.jmolina.orb.elements.BaseElement.*;


public class Situation102 extends SideWallSituation {

    public Situation102(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Lateral boxes
        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.GREY, 0, 7, 7, 7, 45
        ));

        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.RED, 2.5f, 9.5f, 7, 0.5f, -45
        ));

        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.GREY, 12, 12, 7, 7, -45
        ));

        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Geometry.SQUARE, Flavor.RED, 9.5f, 14.5f, 7, 0.5f, 45
        ));
    }

}
